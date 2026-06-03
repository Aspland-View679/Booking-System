package COMP500GroupProjectPackage;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class SignUp extends JFrame {  //Takes users if clicked on sign up button on login/signup to this page

    public SignUp() {

        setTitle("Sign Up");

        setSize(750, 600);
                                //Creates and set the UI and title for the page
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());


        JPanel backgroundPanel =
                new JPanel(new BorderLayout());

        backgroundPanel.setBackground(
                new Color(245, 245, 245)
        );


        JPanel mainPanel =
                new JPanel();

        mainPanel.setLayout(
                new GridBagLayout()
        );

        mainPanel.setBackground(Color.WHITE);

        mainPanel.setBorder(
                new EmptyBorder(40, 40, 40, 40)
        );


        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(15, 15, 15, 15);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;   //This sections creates boarders and wraps them so that it is on theme with the rest


        JLabel title =
                new JLabel("Create Account");

        title.setFont(           //creates a new title to let the users know to create the account
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        32
                )
        );


        JTextField usernameField =
                new JTextField();

        JTextField emailField =
                new JTextField();

        JPasswordField passwordField =
                new JPasswordField();

        JPasswordField confirmPasswordField =   //All the input fields for the users to fill out
                new JPasswordField();


        usernameField.setPreferredSize(
                new Dimension(320, 40)
        );

        emailField.setPreferredSize(
                new Dimension(320, 40)
        );

        passwordField.setPreferredSize(
                new Dimension(320, 40)
        );

        confirmPasswordField.setPreferredSize(
                new Dimension(320, 40)
        );


        JButton signupButton =
                new JButton("Create Account");  //let it be created and saved to the DB

        signupButton.setBackground(
                new Color(155, 109, 255)
        );

        signupButton.setForeground(Color.WHITE);

        signupButton.setFocusPainted(false);

        signupButton.setBorderPainted(false);

        signupButton.setPreferredSize(   //Sizing and grid for the sign up buttons
                new Dimension(320, 45)
        );


        gbc.gridx = 0;
        gbc.gridy = 0;

        mainPanel.add(title, gbc);


        gbc.gridy++;

        mainPanel.add(
                new JLabel("Username"),
                gbc
        );


        gbc.gridy++;

        mainPanel.add(usernameField, gbc);


        gbc.gridy++;

        mainPanel.add(
                new JLabel("Email"),
                gbc
        );


        gbc.gridy++;

        mainPanel.add(emailField, gbc);


        gbc.gridy++;

        mainPanel.add(
                new JLabel("Password"),
                gbc
        );


        gbc.gridy++;

        mainPanel.add(passwordField, gbc);


        gbc.gridy++;

        mainPanel.add(
                new JLabel("Confirm Password"),
                gbc
        );


        gbc.gridy++;

        mainPanel.add(confirmPasswordField, gbc);


        gbc.gridy++;

        mainPanel.add(signupButton, gbc);


        backgroundPanel.add(
                mainPanel,
                BorderLayout.CENTER
        );

        add(backgroundPanel);


        signupButton.addActionListener(e -> {

            String username =
                    usernameField.getText();

            String email =
                    emailField.getText();

            String password =
                    String.valueOf(
                            passwordField.getPassword()
                    );

            String confirmPassword =
                    String.valueOf(
                            confirmPasswordField.getPassword()
                    );


            if (
                    username.isEmpty()
                    || email.isEmpty()
                    || password.isEmpty()
            ) {                                   //If the user know if anything is empty

                JOptionPane.showMessageDialog(
                        null,
                        "Please Fill In All Fields"
                );

                return;
            }


            if (!password.equals(confirmPassword)) {

                JOptionPane.showMessageDialog(
                        null,
                        "Passwords Do Not Match"   //Mismatching passwords and can't be confirmed
                );

                return;
            }


            DataManager.createUser(

                    username,
                    email,
                    password,  //Saves all data to the DB
                    "User"
            );


            JOptionPane.showMessageDialog(
                    null,               //Confirmation for the account has been verfied
                    "Account Created"
            );

            setVisible(false);

            new Login();

        });


        setVisible(true);
    }


    public static void main(String[] args) {

        try {

            FlatLightLaf.setup();

        }

        catch (Exception e) {

            e.printStackTrace();
        }

        new SignUp(); //Ends it and loops to the next page
    }
}