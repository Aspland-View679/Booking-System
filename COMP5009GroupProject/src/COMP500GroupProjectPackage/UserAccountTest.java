package COMP500GroupProjectPackage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UserAccountTest {  //This is to call the User account

    @Test
    public void testUserCreation() {

        UserAccount user =
                new UserAccount(
                        "rosie",
                        "rosie@test.com",
                        "password123",   //Does the inputs match the input details
                        "User"
                );

        assertEquals(
                "rosie",  //testing a fake username
                user.getUsername()
        );
    }

    @Test
    public void testUserEmail() {

        UserAccount user =
                new UserAccount(
                        "rosie",
                        "rosie@test.com",
                        "password123",
                        "User"
                );

        assertEquals(
                "rosie@test.com",
                user.getEmail()  //Tests a fake email
        );
    }

    @Test
    public void testUserPassword() {

        UserAccount user =
                new UserAccount(
                        "rosie",
                        "rosie@test.com",
                        "password123",
                        "User"
                );

        assertEquals(
                "password123",
                user.getPassword() //Tests a fake passwords
        );
    }

    @Test
    public void testUserAccountType() {

        UserAccount user =
                new UserAccount(
                        "rosie",
                        "rosie@test.com",
                        "password123",
                        "Admin"
                );

        assertEquals(
                "Admin",
                user.getAccountType()  //Tests if it is a user account
        );
    }

    @Test
    public void testSetUserID() {

        UserAccount user =
                new UserAccount(
                        "rosie",
                        "rosie@test.com",
                        "password123",
                        "User"
                );

        user.setUserID(5);

        assertEquals(
                5,
                user.getUserID()  //If their is an id with it
        );
    }
}
