package COMP500GroupProjectPackage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {  //tests to see if the login feature can hold inputted data

    @BeforeEach
    public void setup() {

        DataManager.createUserTable();  //creates a tabel in database
    }

    @Test
    public void testUserLoginSuccess() {

        DataManager.createUser(
                "alex",
                "alex@test.com",  //this creates the user data that can be used to test the success
                "password123",
                "User"
        );

        UserAccount user =
                DataManager.loginUser(
                        "alex",  // if the user is there it is allowed
                        "password123"
                );

        assertNotNull(user);  //no valid inputs, invalid
    }

    @Test
    public void testUserLoginWrongPassword() {

        DataManager.createUser(
                "jamie",
                "jamie@test.com",
                "password123",   //If it doesnt match the password originally inputted won't let them get on and fail
                "User"
        );

        UserAccount user =
                DataManager.loginUser(
                        "jamie",  //see if a see is in the database
                        "wrongpassword"
                );

        assertNull(user);  //stops if not
    }

    @Test
    public void testUserLoginWithEmail() {

        DataManager.createUser(
                "kane",
                "kane@test.com",  
                "password123",
                "User"
        );

        UserAccount user =               //this tests to ensure the email field is working
                DataManager.loginUser(
                        "kane@test.com",
                        "password123"
                );

        assertNotNull(user);
    }

    @Test
    public void testHashPassword() {

        String hashed =
                DataManager.hashPassword("password123");  //this encrypts the passwords as passwords are senstive and should have some security

        assertNotNull(hashed);

        assertNotEquals(
                "password123",
                hashed
        );
    }
}

