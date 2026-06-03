package COMP500GroupProjectPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.security.MessageDigest;


/**
 * This handles all SQL database functions for the airport booking system.
 * responsible for:
 * connecting to sqllite database
 * creating and inserting table, updating, storing info, cancelling, confirming and restoring it
 * 
 * @author COMP5009 Group Project
 * @version 1.0
 */

public class DataManager {

	private static final String URL = "jdbc:sqlite:bookingSystem.db";

	private static Booking currentBooking;

	private static UserAccount currentUser;

	private static double globalDiscountPercentage = 0;

	private static int globalDiscountDuration = 0;


	public static void setCurrentUser(
	        UserAccount user
	) {

	    currentUser = user;
	}


	public static UserAccount getCurrentUser() {

	    return currentUser;
	}


	public static void setCurrentBooking(
	        Booking booking
	) {

		currentBooking = booking;
	}


	public static Booking getCurrentBooking() {

		return currentBooking;
	}


	public static void setGlobalDiscount(
	        double percentage,
	        int durationDays
	) {

	    globalDiscountPercentage = percentage;

	    globalDiscountDuration = durationDays;
	}


	public static double getGlobalDiscountPercentage() {

	    return globalDiscountPercentage;
	}


	public static int getGlobalDiscountDuration() {

	    return globalDiscountDuration;
	}


	/**
	 * connects the application to sqlite
	 * 
	 * @return the database connections
	 */

	public static Connection connect() {

		try {

			Connection conn =
			        DriverManager.getConnection(URL);

			System.out.println("Connected to Database");

			return conn;

		}

		catch (SQLException e) {

			System.out.println("Connection Failed");

			e.printStackTrace();

			return null;
		}
	}


	/**
	 * creates the bookings tables if it doesn't exist
	 */

	public static void createTable() {

		String sql =

				"CREATE TABLE IF NOT EXISTS bookings ("

						+ "bookingID INTEGER PRIMARY KEY AUTOINCREMENT,"
						+ "airport TEXT NOT NULL,"
						+ "pickupLocation TEXT NOT NULL,"
						+ "bookingDate TEXT NOT NULL,"
						+ "bookingTime TEXT NOT NULL,"
						+ "luggageAmount INTEGER,"
						+ "estimatedDuration INTEGER,"
						+ "totalPrice REAL,"
						+ "paymentType TEXT,"
						+ "cardNumber TEXT,"
						+ "billingAddress TEXT,"
						+ "bookingFee REAL,"
						+ "discountCode TEXT,"
						+ "discountPercentage REAL,"
						+ "paid INTEGER DEFAULT 0,"
						+ "amended INTEGER DEFAULT 0,"
						+ "cancelled INTEGER DEFAULT 0,"
						+ "confirmed INTEGER DEFAULT 0,"
						+ "userID INTEGER"

						+ ");";

		try (

				Connection conn = connect();

				Statement stmt =
				        conn.createStatement()

		) {

			stmt.execute(sql);

			System.out.println("Bookings Table Created");

		}

		catch (SQLException e) {

			e.printStackTrace();
		}
	}


	public static void createUserTable() {

		String sql =

				"CREATE TABLE IF NOT EXISTS users ("

						+ "userID INTEGER PRIMARY KEY AUTOINCREMENT,"
						+ "username TEXT NOT NULL,"
						+ "email TEXT NOT NULL UNIQUE,"
						+ "password TEXT NOT NULL,"
						+ "accountType TEXT NOT NULL"

						+ ");";

		try (

				Connection conn = connect();

				Statement stmt =
				        conn.createStatement()

		) {

			stmt.execute(sql);

			System.out.println("Users Table Created");

		}

		catch (SQLException e) {

			e.printStackTrace();
		}
	}


	//stores passwords as encrypted
	
	public static String hashPassword(
	        String password
	) {

		try {

			MessageDigest md =
			        MessageDigest.getInstance("SHA-256");

			byte[] hashedBytes =
			        md.digest(
			                password.getBytes()
			        );

			StringBuilder sb =
			        new StringBuilder();

			for (byte b : hashedBytes) {

				sb.append(
						String.format(
								"%02x",
								b
						)
				);
			}

			return sb.toString();

		}

		catch (Exception e) {

			e.printStackTrace();
//if there is an error ot will return null
			return null;
		}
	}

	// Create A User table

	public static void createUser(

	        String username,
	        String email,
	        String password,
	        String accountType

	) {

		String sql =

				"INSERT INTO users("

						+ "username,"
						+ "email,"
						+ "password,"  //all the data being stored
						+ "accountType"

						+ ") VALUES(?,?,?,?)";

		try (

				Connection conn = connect();
//ensures a connection to sql
				PreparedStatement pstmt =
				        conn.prepareStatement(sql)

		) {

			pstmt.setString(1, username);

			pstmt.setString(2, email);  //sets the data into the right field

			pstmt.setString(
					3,
					hashPassword(password)
			);

			pstmt.setString(4, accountType);

			pstmt.executeUpdate();

			System.out.println("User Created");  //allows the user to be created
		}

		catch (SQLException e) {

			e.printStackTrace();
		}
	}


	public static UserAccount loginUser(

	        String emailOrUsername,
	        String password

	) {

		String sql =

				"SELECT * FROM users "

				+ "WHERE "

				+ "(email = ? OR username = ?) "
 //stores data from table to be used annd called for login
				+ "AND password = ?";

		try (

				Connection conn = connect();

				PreparedStatement pstmt =
				        conn.prepareStatement(sql)

		) {

			pstmt.setString(1, emailOrUsername);

			pstmt.setString(2, emailOrUsername);

			pstmt.setString(
					3,
					hashPassword(password)
			);

			ResultSet rs =
			        pstmt.executeQuery();

			if (rs.next()) {

				UserAccount user =
				        new UserAccount(

				                rs.getString("username"),
				                rs.getString("email"),
				                rs.getString("password"),
				                rs.getString("accountType")
				        );

				user.setUserID(
						rs.getInt("userID")
				);

				return user;
			}

		}

		catch (SQLException e) {

			e.printStackTrace();
		}

		return null;
	}


	/**
	 * Inserts the booking into database
	 */

	public static void insertBooking(
	        Booking booking
	) {

	    String sql =

	            "INSERT INTO bookings("

	                    + "airport,"
	                    + "pickupLocation,"
	                    + "bookingDate,"
	                    + "bookingTime,"
	                    + "luggageAmount,"
	                    + "estimatedDuration,"
	                    + "totalPrice,"
	                    + "userId"

	                    + ") VALUES(?,?,?,?,?,?,?,?)";

	    try (

	            Connection conn = connect();

	            PreparedStatement pstmt =
	                    conn.prepareStatement(
	                            sql,
	                            Statement.RETURN_GENERATED_KEYS
	                    )

	    ) {

	        pstmt.setString(
	                1,
	                booking.getAirport()
	        );

	        pstmt.setString(
	                2,
	                booking.getPickupLocation()
	        );

	        pstmt.setString(
	                3,
	                booking.getBookingDate()
	        );

	        pstmt.setString(
	                4,
	                booking.getBookingTime()
	        );

	        pstmt.setInt(
	                5,
	                booking.getLuggageAmount()
	        );

	        pstmt.setInt(
	                6,
	                booking.getEstimatedDuration()
	        );

	        pstmt.setDouble(
	                7,
	                booking.getTotalPrice()
	        );

	        pstmt.setInt(
	                8,
	                currentUser.getUserID()
	        );

	        pstmt.executeUpdate();

	        ResultSet generatedKeys =
	                pstmt.getGeneratedKeys();

	        if (generatedKeys.next()) {

	            int bookingID =
	                    generatedKeys.getInt(1);

	            booking.setBookingID(
	                    bookingID
	            );

	            System.out.println(
	                    "Booking Added With ID: "
	                    + bookingID
	            );
	        }

	    }

	    catch (SQLException e) {

	        e.printStackTrace();
	    }
	}


	/**
	 * Retrieves booking using booking ID
	 */

	public static ResultSet getBooking(
	        int bookingID
	) {

	    String sql =
	            "SELECT * FROM bookings WHERE bookingID = ?";

	    try {

	        Connection conn = connect();

	        PreparedStatement pstmt =
	                conn.prepareStatement(sql);

	        pstmt.setInt(1, bookingID);

	        return pstmt.executeQuery();

	    }

	    catch (SQLException e) {

	        e.printStackTrace();

	        return null;
	    }
	}


	public static Booking getBookingByDisplayID(
	        String displayID
	) {

	    try {

	        String cleanedID =
	                displayID.replace(
	                        "ABS-",
	                        ""
	                );

	        int bookingID =
	                Integer.parseInt(cleanedID);

	        ResultSet rs =
	                getBooking(bookingID);

	        if (rs.next()) {

	            Booking booking =
	                    new Booking(

	                            rs.getString("airport"),
	                            rs.getString("pickupLocation"),
	                            rs.getString("bookingDate"),
	                            rs.getString("bookingTime"),
	                            rs.getInt("luggageAmount"),
	                            rs.getInt("estimatedDuration"),
	                            rs.getDouble("totalPrice")
	                    );

	            booking.setBookingID(
	                    rs.getInt("bookingID")
	            );

	            booking.setConfirmed(
	                    rs.getInt("confirmed") == 1
	            );

	            booking.setCancelled(
	                    rs.getInt("cancelled") == 1
	            );

	            return booking;
	        }

	    }

	    catch (Exception e) {

	        e.printStackTrace();
	    }

	    return null;
	}


	/**
	 * Returns latest booking for logged in user
	 */

	public static Booking getLatestBooking() {

	    if (currentUser == null) {

	        return null;
	    }

	    String sql =

	    		"SELECT * FROM bookings "
	    				+ "WHERE cancelled = 0 "
	    				+ "AND confirmed = 0 "
	    				+ "AND userID = ? "
	    				+ "ORDER BY bookingID DESC "
	    				+ "LIMIT 1";

	    try (

	            Connection conn = connect();

	            PreparedStatement pstmt =
	                    conn.prepareStatement(sql)

	    ) {

	        pstmt.setInt(
	                1,
	                currentUser.getUserID()
	        );

	        ResultSet rs =
	                pstmt.executeQuery();

	        if (rs.next()) {

	            Booking booking =
	                    new Booking(

	                            rs.getString("airport"),
	                            rs.getString("pickupLocation"),
	                            rs.getString("bookingDate"),
	                            rs.getString("bookingTime"),
	                            rs.getInt("luggageAmount"),
	                            rs.getInt("estimatedDuration"),
	                            rs.getDouble("totalPrice")
	                    );

	            booking.setBookingID(
	                    rs.getInt("bookingID")
	            );

	            booking.setConfirmed(
	                    rs.getInt("confirmed") == 1
	            );

	            booking.setCancelled(
	                    rs.getInt("cancelled") == 1
	            );

	            booking.setAmended(
	                    rs.getInt("amended") == 1
	            );

	            return booking;
	        }

	    }

	    catch (SQLException e) {

	        e.printStackTrace();
	    }

	    return null;
	}


	/**
	 * Updates booking details
	 */

	public static void updateBooking(

	        int bookingID,
	        String airport,
	        String pickupLocation,
	        String bookingDate,
	        String bookingTime,
	        int luggageAmount,
	        int estimatedDuration,
	        double totalPrice

	) {

		String sql =

				"UPDATE bookings SET "

						+ "airport = ?,"
						+ "pickupLocation = ?,"
						+ "bookingDate = ?,"
						+ "bookingTime = ?,"
						+ "luggageAmount = ?,"
						+ "estimatedDuration = ?,"
						+ "totalPrice = ?,"
						+ "amended = 1 "

						+ "WHERE bookingID = ?";

		try (

				Connection conn = connect();

				PreparedStatement pstmt =
				        conn.prepareStatement(sql)

		) {

			pstmt.setString(1, airport);

			pstmt.setString(2, pickupLocation);

			pstmt.setString(3, bookingDate);

			pstmt.setString(4, bookingTime);

			pstmt.setInt(5, luggageAmount);

			pstmt.setInt(6, estimatedDuration);

			pstmt.setDouble(7, totalPrice);

			pstmt.setInt(8, bookingID);

			pstmt.executeUpdate();

			System.out.println("Booking Updated");

		}

		catch (SQLException e) {

			e.printStackTrace();
		}
	}


	/**
	 * Updates payment information
	 */

	public static void updatePayment(

	        int bookingID,
	        String paymentType,
	        String cardNumber,
	        String billingAddress,
	        double bookingFee

	) {

		String sql =

				"UPDATE bookings SET "

						+ "paymentType = ?,"
						+ "cardNumber = ?,"
						+ "billingAddress = ?,"
						+ "bookingFee = ?,"
						+ "paid = 1,"
						+ "confirmed = 1 "

						+ "WHERE bookingID = ?";

		try (

				Connection conn = connect();

				PreparedStatement pstmt =
				        conn.prepareStatement(sql)

		) {

			pstmt.setString(1, paymentType);

			pstmt.setString(2, cardNumber);

			pstmt.setString(3, billingAddress);

			pstmt.setDouble(4, bookingFee);

			pstmt.setInt(5, bookingID);

			pstmt.executeUpdate();

			System.out.println("Payment Updated");

		}

		catch (SQLException e) {

			e.printStackTrace();
		}
	}


	/**
	 * Applies discount code
	 */

	public static void applyDiscount(

	        int bookingID,
	        String discountCode,
	        double discountPercentage

	) {

		String sql =

				"UPDATE bookings SET "

						+ "discountCode = ?,"
						+ "discountPercentage = ? "

						+ "WHERE bookingID = ?";

		try (

				Connection conn = connect();

				PreparedStatement pstmt =
				        conn.prepareStatement(sql)

		) {

			pstmt.setString(1, discountCode);

			pstmt.setDouble(2, discountPercentage);

			pstmt.setInt(3, bookingID);

			pstmt.executeUpdate();

			System.out.println("Discount Applied");

		}

		catch (SQLException e) {

			e.printStackTrace();
		}
	}


	public static void cancelBooking(
	        int bookingID
	) {

		String sql =

				"UPDATE bookings SET "

						+ "cancelled = 1 "

						+ "WHERE bookingID = ?";

		try (

				Connection conn = connect();

				PreparedStatement pstmt =
				        conn.prepareStatement(sql)

		) {

			pstmt.setInt(1, bookingID);

			pstmt.executeUpdate();

			System.out.println("Booking Cancelled");

		}

		catch (SQLException e) {

			e.printStackTrace();
		}
	}


	public static void confirmBooking(
	        int bookingID
	) {

		String sql =

				"UPDATE bookings SET "

						+ "confirmed = 1 "

						+ "WHERE bookingID = ?";

		try (

				Connection conn = connect();

				PreparedStatement pstmt =
				        conn.prepareStatement(sql)

		) {

			pstmt.setInt(1, bookingID);

			pstmt.executeUpdate();

			System.out.println("Booking Confirmed");

		}

		catch (SQLException e) {

			e.printStackTrace();
		}
	}


	/**
	 * Deletes booking completely
	 */

	public static void deleteBooking(
	        int bookingID
	) {

		String sql =

				"DELETE FROM bookings WHERE bookingID = ?";

		try (

				Connection conn = connect();

				PreparedStatement pstmt =
				        conn.prepareStatement(sql)

		) {

			pstmt.setInt(1, bookingID);

			pstmt.executeUpdate();

			System.out.println("Booking Deleted");

		}

		catch (SQLException e) {

			e.printStackTrace();
		}
	}
}