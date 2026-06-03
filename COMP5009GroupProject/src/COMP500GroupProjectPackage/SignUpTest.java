package COMP500GroupProjectPackage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SignUpTest {

    @BeforeEach
    public void setup() {

        DataManager.createUserTable();
    }

    @Test
    public void testCreateUser() {

        DataManager.createUser(
                "rosie",                         //Tests if a user can sign up or login with the right details and compare the input fields match the fields there and are valid
                "rosie@test.com",
                "mypassword",
                "User"
        );

        UserAccount user =
                DataManager.loginUser(
                        "rosie",
                        "mypassword"
                );

        assertNotNull(user);
    }

    @Test
    public void testPasswordHashing() {

        String hashed =
                DataManager.hashPassword(
                        "mypassword"
                );

        assertNotEquals(
                "mypassword",
                hashed
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

