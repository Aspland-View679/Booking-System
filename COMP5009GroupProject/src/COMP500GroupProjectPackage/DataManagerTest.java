package COMP500GroupProjectPackage;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

/**
 * Tests SQL database functionality.
 * 
 * These tests verify that bookings
 * can be inserted, retrieved and cancelled.
 * 
 * @author COMP5009 Group Project
 * @version 1.0
 */

public class DataManagerTest {

	/**
	 * Tests that inserting a booking
	 * generates a valid booking ID.
	 */

    @Test
    public void testInsertBookingCreatesID() {

        DataManager.createTable();

        Booking booking = new Booking(
                "London Heathrow - UK",
                "Ashford",
                "20/05/2026",
                "14:00",
                2,
                90,
                120.0
        );

        DataManager.insertBooking(booking);

        assertTrue(
                booking.getBookingID() > 0
        );
    }

    /**
	 * Tests retrieving a booking
	 * from the SQL database.
	 */

    @Test
    public void testGetBookingReturnsCorrectAirport()
            throws SQLException {

        DataManager.createTable();

        Booking booking = new Booking(
                "Manchester - UK",
                "Canterbury",
                "21/05/2026",
                "15:00",
                1,
                70,
                100.0
        );

        DataManager.insertBooking(booking);

        ResultSet rs =
                DataManager.getBooking(
                        booking.getBookingID()
                );

        rs.next();

        assertEquals(
                "Manchester - UK",
                rs.getString("airport")
        );

        rs.close(); 
    }

    /**
	 * Tests cancelling a booking
	 * inside the SQL database.
	 */

    @Test
    public void testCancelBooking()
            throws SQLException {

        DataManager.createTable();

        Booking booking = new Booking(
                "Gatwick Airport - UK",
                "Dover",
                "22/05/2026",
                "10:00",
                3,
                80,
                150.0
        );

        DataManager.insertBooking(booking);

        DataManager.cancelBooking(
                booking.getBookingID()
        );

        ResultSet rs =
                DataManager.getBooking(
                        booking.getBookingID()
                );

        rs.next();

        assertEquals(
                1,
                rs.getInt("cancelled")
        );

        rs.close(); 
    }
}