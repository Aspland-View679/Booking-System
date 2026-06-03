package COMP500GroupProjectPackage;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class Login extends JFrame {
	
	//Creates frame and application for login

    private JButton backButton; //for the back button for later

    public Login() {

        setTitle("Login");

        setSize(700, 500);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //closes the application

        setLayout(new BorderLayout());


        JPanel backgroundPanel =
                new JPanel(new BorderLayout());  //boarder constraints and setting the colour, format up

        backgroundPanel.setBackground(
                new Color(245, 245, 245)
        );


        JPanel mainPanel =
                new JPanel();  //the main application panel

        mainPanel.setLayout(
                new GridBagLayout()   //grid use
        );

        mainPanel.setBackground(Color.WHITE); //background colour white

        mainPanel.setBorder(
                new EmptyBorder(40, 40, 40, 40)  //padding
        );


        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(15, 15, 15, 15);

        gbc.fill =
                GridBagConstraints.HORIZONTAL; //it goes across and fills up


        JLabel title =
                new JLabel("Login");

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        32
                )
        );


        JLabel userLabel =
                new JLabel("Username Or Email");  //Either enter the email or username


        JTextField userField =  
                new JTextField();

        userField.setPreferredSize(
                new Dimension(300, 40)  //setting the login boxes up
        );


        JLabel passwordLabel =
                new JLabel("Password");  //password box set up


        JPasswordField passwordField =
                new JPasswordField();

        passwordField.setPreferredSize(
                new Dimension(300, 40)
        );


        JButton loginButton =
                new JButton("Login");

        loginButton.setBackground(
                new Color(155, 109, 255)
        );

        loginButton.setForeground(Color.WHITE);

        loginButton.setFocusPainted(false);

        loginButton.setBorderPainted(false);

        loginButton.setPreferredSize(
                new Dimension(300, 45)
        );


        backButton =
                new JButton("Back");  //the back button if you didn't make an account

        backButton.setFocusPainted(false);

        backButton.setBorderPainted(false);

        backButton.setBackground(
                new Color(230, 230, 230)
        );

        backButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );


        gbc.gridx = 0;
        gbc.gridy = 0;

        mainPanel.add(title, gbc);


        gbc.gridy++;

        mainPanel.add(userLabel, gbc);


        gbc.gridy++;

        mainPanel.add(userField, gbc);


        gbc.gridy++;

        mainPanel.add(passwordLabel, gbc);


        gbc.gridy++;

        mainPanel.add(passwordField, gbc);


        gbc.gridy++;

        mainPanel.add(loginButton, gbc);


        gbc.gridy++;

        mainPanel.add(backButton, gbc);


        backgroundPanel.add(
                mainPanel,
                BorderLayout.CENTER
        );

        add(backgroundPanel);


        loginButton.addActionListener(e -> {

            String usernameOrEmail =
                    userField.getText();  //if you have an account it will retreive data from the database

            String password =
                    String.valueOf(
                            passwordField.getPassword() //if you have an account it will retreive data from the database
                    );

            UserAccount user =
                    DataManager.loginUser(
                            usernameOrEmail,  //accepts username or email
                            password
                    );

            if (user != null) {

                DataManager.setCurrentUser(user);  

                JOptionPane.showMessageDialog(  //if there is valid data store it will allow the user to go into their account
                        null,
                        "Login Successful"
                );

                dispose();

                new Home();  // takes the user to home.

            }

            else {

                JOptionPane.showMessageDialog(  //otherwise no user details, it wont be successful and you have to retry 
                        null,
                        "Invalid Login Details"
                );
            }
        });


        backButton.addActionListener(e -> {

            setVisible(false);

            new SignUpOrLogin();  // hides this page and takes you back to the sign up/ login page

        });


        setVisible(true); //shows panel
    }


    public static void main(String[] args) {

        try {

            FlatLightLaf.setup();

        }

        catch (Exception e) {

            e.printStackTrace();  
        }

        new Login(); //hides the current panel if data is valid
    }
}
