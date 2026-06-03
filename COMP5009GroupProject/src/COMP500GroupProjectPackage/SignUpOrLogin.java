package COMP500GroupProjectPackage;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class SignUpOrLogin extends JFrame {    

    public SignUpOrLogin() {

        setTitle("Airport Booking System");

        setSize(850, 600);           //Creates the UI for the sign up or login page

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //Closes the window if you close it

        setLayout(new BorderLayout());


        //Main background panel

        JPanel backgroundPanel =
                new JPanel(new BorderLayout());

        backgroundPanel.setBackground(
                new Color(245, 245, 245)
        );


        //Top heading section

        JPanel headingPanel =
                new JPanel();

        headingPanel.setBackground(
                new Color(245, 245, 245)
        );

        headingPanel.setBorder(
                new EmptyBorder(40, 40, 20, 40)
        );

        headingPanel.setLayout(
                new BoxLayout(
                        headingPanel,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel title =
                new JLabel(
                        "Airport Booking System"  //The title of the page
                );

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        36
                )
        );

        title.setForeground(
                new Color(30, 30, 30)
        );


        JLabel subtitle =
                new JLabel(
                        "Login, sign up or continue as a guest"  //Subtitle to let users know which account is to sign up, be a guest or are you a user
                );

        subtitle.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        16
                )
        );

        subtitle.setForeground(
                new Color(90, 90, 90)
        );


        headingPanel.add(title);
                                   //grid ui layout for a smoother feature
        headingPanel.add(
                Box.createRigidArea(
                        new Dimension(0, 10)
                )
        );

        headingPanel.add(subtitle);


        //Main options panel

        JPanel optionsPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                25,
                                25
                        )
                );

        optionsPanel.setBackground(
                new Color(245, 245, 245)
        );

        optionsPanel.setBorder(
                new EmptyBorder(30, 40, 40, 40)
        );


        //Login card

        JPanel loginCard =
                createOptionCard(

                        "Login",
                        "Access existing bookings and account details",  //This section calls data from the DB to see if that user exists
                        "src/icons/UserAccount.png"
                );


        JButton loginButton =
                new JButton("Login");

        styleButton(loginButton);

        loginCard.add(
                loginButton,
                BorderLayout.SOUTH
        );


        //Signup card

        JPanel signupCard =
                createOptionCard(

                        "Sign Up",
                        "Create a new account for booking management",
                        "src/icons/SignUp.png"            //This sections allows users to create an account
                );


        JButton signupButton =
                new JButton("Create Account");

        styleButton(signupButton);

        signupCard.add(
                signupButton,
                BorderLayout.SOUTH
        );


        //Guest card

        JPanel guestCard =
                createOptionCard(

                        "Guest",
                        "Continue without creating an account",
                        "src/icons/GuestAccount.png"     //Allows users to book as a guest
                );


        JButton guestButton =
                new JButton("Continue As Guest");

        styleButton(guestButton);

        guestCard.add(
                guestButton,
                BorderLayout.SOUTH
        );


        //Adds cards

        optionsPanel.add(loginCard);

        optionsPanel.add(signupCard);

        optionsPanel.add(guestCard);


        //Adds everything to frame

        backgroundPanel.add(
                headingPanel,
                BorderLayout.NORTH
        );

        backgroundPanel.add(
                optionsPanel,
                BorderLayout.CENTER
        );

        add(backgroundPanel);


        //Login action

        loginButton.addActionListener(e -> {

            setVisible(false);

            new Login();
        });


        //Signup action

        signupButton.addActionListener(e -> {

            setVisible(false);

            new SignUp();
        });


        //Guest action

        guestButton.addActionListener(e -> {

            GuestAccount guest =
                    new GuestAccount();
            
            guest.setUserID(-1);

            DataManager.setCurrentUser(guest);

            setVisible(false);

            new Home();
        });


        setVisible(true);
    }


    //Creates option cards

    private JPanel createOptionCard(

            String title,
            String description,
            String iconPath

    ) {

        JPanel card =
                new JPanel(
                        new BorderLayout()
                );

        card.setBackground(Color.WHITE);

        card.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                new Color(220, 220, 220),
                                1,
                                true
                        ),

                        new EmptyBorder(30, 30, 30, 30)
                )
        );


        //Icons for pretty images

        ImageIcon icon =
                new ImageIcon(iconPath);

        Image scaledImage =
                icon.getImage().getScaledInstance(

                        90,
                        90,
                        Image.SCALE_SMOOTH
                );

        JLabel iconLabel =
                new JLabel(
                        new ImageIcon(scaledImage)
                );

        iconLabel.setHorizontalAlignment(
                SwingConstants.CENTER
        );


        //Title

        JLabel titleLabel =
                new JLabel(
                        title
                );

        titleLabel.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        24
                )
        );

        titleLabel.setForeground(
                new Color(30, 30, 30)
        );


        //Description for each panel

        JLabel descriptionLabel =
                new JLabel(

                        "<html><div style='text-align:center;'>"
                        + description
                        + "</div></html>"
                );

        descriptionLabel.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        descriptionLabel.setForeground(
                new Color(90, 90, 90)
        );

        descriptionLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        15
                )
        );


        JPanel contentPanel =
                new JPanel();

        contentPanel.setBackground(Color.WHITE);

        contentPanel.setLayout(
                new BoxLayout(
                        contentPanel,
                        BoxLayout.Y_AXIS
                )
        );


        contentPanel.add(iconLabel);

        contentPanel.add(
                Box.createRigidArea(
                        new Dimension(0, 20)
                )
        );

        contentPanel.add(titleLabel);

        contentPanel.add(
                Box.createRigidArea(
                        new Dimension(0, 15)
                )
        );

        contentPanel.add(descriptionLabel);


        card.add(
                contentPanel,
                BorderLayout.CENTER
        );

        return card;
    }


    //Styles buttons to ensure it is wrapped properly

    private void styleButton(
            JButton button
    ) {

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setBackground(
                new Color(155, 109, 255)
        );

        button.setForeground(Color.WHITE);

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        15
                )
        );

        button.setPreferredSize(
                new Dimension(180, 50)
        );
    }


    public static void main(String[] args) {

        try {

            FlatLightLaf.setup();

        } catch (Exception e) {

            e.printStackTrace();
        }

        new SignUpOrLogin();  //Flow structure for the application
    }
}
