package COMP500GroupProjectPackage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SignUpOrLoginTest {

    @Test
    public void testGuestAccountCreation() {          //Tests the fields for valid inputs and outputs like guest username, email, account so it is only temporarily 

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
