package COMP500GroupProjectPackage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GuestAccountTest {

    @Test
    public void testGuestUsername() {

        GuestAccount guest =
                new GuestAccount();

        assertEquals(
                "Guest",
                guest.getUsername()
        );
    }

    @Test
    public void testGuestEmail() {

        GuestAccount guest =
                new GuestAccount();

        assertEquals(
                "guest@guest.com",
                guest.getEmail()
        );
    }

    @Test
    public void testGuestAccountType() {

        GuestAccount guest =
                new GuestAccount();

        assertEquals(
                "Guest",
                guest.getAccountType()
        );
    }
}
