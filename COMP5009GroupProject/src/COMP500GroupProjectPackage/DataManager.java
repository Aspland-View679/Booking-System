package COMP500GroupProjectPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

	private static final String URL = "jdbc:sqlite:bookingSystem.db"; //this is for the database location and creates it, if it doesn't exists already 

	private static Booking currentBooking;

	public static void setCurrentBooking(Booking booking) {

		currentBooking = booking; //Stores current booking while the application is running 

	}

	public static Booking getCurrentBooking() {

		return currentBooking;  //returns current booking

	}

	/**
	 * connects the application to sqlite
	 * 
	 * @return the database connections
	 */

	public static Connection connect() {

		try {

			Connection conn = DriverManager.getConnection(URL); //this attempts the sql database connection

			System.out.println("Connected to Database");

			return conn;

		}

		catch (SQLException e) {

			System.out.println("Connection Failed"); // these tells us if the connection was successful or not

			e.printStackTrace();

			return null;
		}
	}

	/**
	 * creates the bookings tables if it doesn't exist
	 * 
	 * @return the database connections
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

						+ "totalPrice REAL," //this creates the table and fields for the table

						+ "paymentType TEXT,"

						+ "cardNumber TEXT,"

						+ "billingAddress TEXT,"

						+ "bookingFee REAL,"

						+ "discountCode TEXT,"

						+ "discountPercentage REAL,"

						+ "paid INTEGER DEFAULT 0,"

						+ "amended INTEGER DEFAULT 0,"

						+ "cancelled INTEGER DEFAULT 0,"

						+ "confirmed INTEGER DEFAULT 0"

						+ ");";

		try (

				Connection conn = connect();

				Statement stmt = conn.createStatement() //Create the sql table statements

		) {

			stmt.execute(sql);

			System.out.println("Bookings Table Created");

		}

		catch (SQLException e) {

			e.printStackTrace();

		}
	}

	/**
	 * Inserets the booking into the database
	 * 
	 * generates a booking ID
	 * 
	 * @return booking booking object to insert
	 */

	public static void insertBooking(
	        Booking booking
	) {

	    String sql = //this is to save the booking into the database

	            "INSERT INTO bookings("

	                    + "airport,"
	                    + "pickupLocation,"
	                    + "bookingDate,"
	                    + "bookingTime,"
	                    + "luggageAmount,"
	                    + "estimatedDuration,"
	                    + "totalPrice"

	                    + ") VALUES(?,?,?,?,?,?,?)";

	    try (

	            Connection conn = connect();

	            PreparedStatement pstmt =
	                    conn.prepareStatement(
	                            sql,
	                            Statement.RETURN_GENERATED_KEYS
	                    )

	    ) { //Inserts booking values into the SQL query

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

	        pstmt.executeUpdate(); //runs the sql insert statement

	        ResultSet generatedKeys =
	                pstmt.getGeneratedKeys(); //returns the automatic generated booking

	        if (generatedKeys.next()) {

	            int bookingID =
	                    generatedKeys.getInt(1);

	            booking.setBookingID(
	                    bookingID //Assigns sql booking id back to object
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
	 * Retrieves a booking using booking ID
	 * 
	 * @param bookingID booking ID to search for
	 * @return SQL ResultSet
	 */

	public static ResultSet getBooking(int bookingID) {

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
	
	
	/**
	 * Returns the latest booking stored in the database
	 * 
	 * Used to restore bookings after the application restarted
	 * 
	 * @return latest booking object
	 */

	public static Booking getLatestBooking() {

        String sql =

                "SELECT * FROM bookings "
                + "ORDER BY bookingID DESC "
                + "LIMIT 1";

        try (

                Connection conn = connect();

                Statement stmt =
                        conn.createStatement();

                ResultSet rs =
                        stmt.executeQuery(sql)

        ) {

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

        catch (SQLException e) {

            e.printStackTrace();

        }

        return null;
    }

	/** 
	 * Updates booking details in SQL database.
	 * 
	 * Used when a booking is amended.
	 * 
	 * @param bookingID booking ID
	 * @param airport updated airport
	 * @param pickupLocation updated location
	 * @param bookingDate updated date
	 * @param bookingTime updated time
	 * @param luggageAmount updated luggage amount
	 * @param estimatedDuration updated duration
	 * @param totalPrice updated price
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

				PreparedStatement pstmt = conn.prepareStatement(sql)

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
	 * Updates payment information for a booking.
	 * 
	 * Also marks the booking as paid.
	 * 
	 * @param bookingID booking ID to update
	 * @param paymentType payment method used
	 * @param cardNumber encrypted card number
	 * @param billingAddress billing address
	 * @param bookingFee booking fee amount
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
						+ "paid = 1 "

						+ "WHERE bookingID = ?";

		try (

				Connection conn = connect();

				PreparedStatement pstmt = conn.prepareStatement(sql)

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
	 * Applies a discount code to a booking.
	 * 
	 * @param bookingID booking ID to update
	 * @param discountCode discount code entered
	 * @param discountPercentage discount percentage applied
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

				PreparedStatement pstmt = conn.prepareStatement(sql)

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

	public static void cancelBooking(int bookingID) {

		String sql =

				"UPDATE bookings SET "

						+ "cancelled = 1 "

						+ "WHERE bookingID = ?";

		try (

				Connection conn = connect();

				PreparedStatement pstmt = conn.prepareStatement(sql)

		) {

			pstmt.setInt(1, bookingID);

			pstmt.executeUpdate();

			System.out.println("Booking Cancelled");

		}

		catch (SQLException e) {

			e.printStackTrace();

		}
	}

	/** 
	 * Confirms a booking inside the database.
	 * 
	 * @param bookingID booking ID to confirm
	 */

	public static void confirmBooking(int bookingID) {

		String sql =

				"UPDATE bookings SET "

						+ "confirmed = 1 "

						+ "WHERE bookingID = ?";

		try (

				Connection conn = connect();

				PreparedStatement pstmt = conn.prepareStatement(sql)

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
	 * Deletes a booking completely from the database.
	 * 
	 * @param bookingID booking ID to delete
	 */

	public static void deleteBooking(int bookingID) {

		String sql =

				"DELETE FROM bookings WHERE bookingID = ?";

		try (

				Connection conn = connect();

				PreparedStatement pstmt = conn.prepareStatement(sql)

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