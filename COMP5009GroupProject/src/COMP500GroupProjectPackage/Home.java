package COMP500GroupProjectPackage;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;

public class Home extends JFrame {

    private JButton bookingButton;
    private JButton amendButton;
    private JButton cancelButton;
    private JButton payNewButton;  //This is for the creation of buttons
    private JButton confirmationButton;
    private JButton discountButton;
    private JButton bookingSummaryButton;


    public Home() {

        setTitle("Airport Booking System");  //sets the window application window size, title and layout

        setSize(1100, 700);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes when you close the application

        setLayout(new BorderLayout());


        //Main background panel for application

        JPanel backgroundPanel =
                new JPanel(new BorderLayout());

        backgroundPanel.setBackground(
                new Color(245, 245, 245)
        );


        //Sidebar section for the application

        JPanel sidebar =
                new JPanel();

        sidebar.setPreferredSize(
                new Dimension(260, getHeight())
        );

        sidebar.setBackground(
                new Color(230, 230, 230)
        );

        sidebar.setLayout(
                new BoxLayout(
                        sidebar,
                        BoxLayout.Y_AXIS
                )
        );

        sidebar.setBorder(
                new EmptyBorder(30, 20, 30, 20)
        );


        //Airport icon

        ImageIcon airportIcon =
                new ImageIcon(
                        "src/icons/Airport.png"
                );

        Image airportImage =
                airportIcon.getImage().getScaledInstance(
                        70,
                        70,
                        Image.SCALE_SMOOTH
                );

        JLabel airportLogo =
                new JLabel(
                        new ImageIcon(airportImage)
                );

        airportLogo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        //Application title

        JLabel logo =
                new JLabel(
                        "<html><center>Airport<br>System</center></html>"  //icon!
                );

        logo.setForeground(
                new Color(30, 30, 30)
        );

        logo.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        24
                )
        );

        logo.setAlignmentX(
                Component.CENTER_ALIGNMENT  //Make it centered!
        );


        sidebar.add(airportLogo);

        sidebar.add(
                Box.createRigidArea(
                        new Dimension(0, 15)
                )
        );

        sidebar.add(logo);
                                       //Creating a sidebar and center and add icons
        sidebar.add(
                Box.createRigidArea(
                        new Dimension(0, 40)
                )
        );


        //Buttons

        bookingButton =
                createSidebarButton(
                        "Make New Booking",
                        "src/icons/NewBooking.png"
                );

        amendButton =
                createSidebarButton(
                        "Amend Booking",
                        "src/icons/AmendBooking.png"
                );

        cancelButton =
                createSidebarButton(
                        "Cancel Booking",
                        "src/icons/CancelBooking.png"
                );

        payNewButton =
                createSidebarButton(
                        "Pay for Booking",
                        "src/icons/Payment.png"
                );

        confirmationButton =
                createSidebarButton(
                        "Confirmation Message",
                        "src/icons/Booking.png"
                );

        discountButton =
                createSidebarButton(
                        "Offer Discount",
                        "src/icons/Booking.png"
                );

        bookingSummaryButton =
                createSidebarButton(
                        "Booking Summary",
                        "src/icons/TicketSummary.png"
                );


        //Adds buttons

        sidebar.add(bookingButton);

        sidebar.add(
                Box.createRigidArea(
                        new Dimension(0, 13)
                )
        );

        sidebar.add(amendButton);

        sidebar.add(
                Box.createRigidArea(
                        new Dimension(0, 13)
                )
        );

        sidebar.add(cancelButton);

        sidebar.add(
                Box.createRigidArea(
                        new Dimension(0, 13)
                )
        );

        sidebar.add(payNewButton);

        sidebar.add(
                Box.createRigidArea(
                        new Dimension(0, 13)
                )
        );

        sidebar.add(confirmationButton);

        sidebar.add(
                Box.createRigidArea(
                        new Dimension(0, 13)
                )
        );

        sidebar.add(discountButton);

        sidebar.add(
                Box.createRigidArea(
                        new Dimension(0, 13)
                )
        );

        sidebar.add(bookingSummaryButton);


        //Dashboard section

        JPanel dashboardPanel =
                new JPanel();

        dashboardPanel.setBackground(
                new Color(245, 245, 245)
        );

        dashboardPanel.setLayout(
                new BorderLayout()
        );

        dashboardPanel.setBorder(
                new EmptyBorder(25, 25, 25, 25)
        );


        //Heading

        JLabel heading =
                new JLabel(
                        "Airport Booking Dashboard"
                );

        heading.setForeground(
                new Color(30, 30, 30)
        );

        heading.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        34
                )
        );


        JLabel subtitle =
                new JLabel(
                        "Manage bookings, payments and customer journeys"
                );

        subtitle.setForeground(
                new Color(90, 90, 90)
        );

        subtitle.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        16
                )
        );


        JPanel titlePanel =
                new JPanel();

        titlePanel.setBackground(
                new Color(245, 245, 245)
        );

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        titlePanel.add(heading);

        titlePanel.add(
                Box.createRigidArea(
                        new Dimension(0, 10)
                )
        );

        titlePanel.add(subtitle);

        dashboardPanel.add(
                titlePanel,
                BorderLayout.NORTH
        );


        //Dashboard cards

        JPanel cardsPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                2,
                                20,
                                20
                        )
                );

        cardsPanel.setBackground(
                new Color(245, 245, 245)
        );

        cardsPanel.setBorder(
                new EmptyBorder(40, 0, 0, 0)
        );


        //Current user

        UserAccount currentUser =
                DataManager.getCurrentUser();

        String welcomeTitle;

        String welcomeDescription;


        if (currentUser == null) {

            welcomeTitle =
                    "No User Logged In";

            welcomeDescription =
                    "Please sign in to continue";
        }

        else if (currentUser instanceof GuestAccount) {

            welcomeTitle =
                    "Guest Session";

            welcomeDescription =
                    "Currently browsing as a guest user";
        }

        else {

            welcomeTitle =
                    "Welcome, "
                    + currentUser.getUsername();

            welcomeDescription =
                    "Logged in using "
                    + currentUser.getEmail();
        }


        //Latest booking

        Booking latestBooking =
        		
                DataManager.getLatestBooking();

        String latestBookingText;

        String bookingStatusText;


        if (latestBooking != null) {

            latestBookingText =

                    latestBooking.getDisplayBookingID()
                    + " • "
                    + latestBooking.getAirport();


            if (latestBooking.isCancelled()) {

                bookingStatusText =
                        "Cancelled";
            }

            else if (latestBooking.isConfirmed()) {

                bookingStatusText =
                        "Confirmed";
            }

            else {

                bookingStatusText =
                        "Pending";
            }

        }

        else {

            latestBookingText =
                    "No bookings found";

            bookingStatusText =
                    "No booking status";
        }


        //Cards

        cardsPanel.add(
                createDashboardCard(
                        welcomeTitle,
                        welcomeDescription
                )
        );

        cardsPanel.add(
                createDashboardCard(
                        "Latest Booking",
                        latestBookingText
                )
        );

        cardsPanel.add(
                createDashboardCard(
                        "Booking Status",
                        bookingStatusText
                )
        );

        cardsPanel.add(
                createDashboardCard(

                        "Booking Summary",

                        latestBooking != null

                        ?

                        latestBooking.getPickupLocation()
                        + " → "
                        + latestBooking.getAirport()

                        :

                        "No active bookings"
                )
        );


        dashboardPanel.add(
                cardsPanel,
                BorderLayout.CENTER
        );


        //Adds everything

        backgroundPanel.add(
                sidebar,
                BorderLayout.WEST
        );

        backgroundPanel.add(
                dashboardPanel,
                BorderLayout.CENTER
        );

        add(backgroundPanel);


        //Booking button

        bookingButton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e
                    ) {

                        setVisible(false);

                        new NewBooking();
                    }
                });


        //Amend button

        amendButton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e
                    ) {

                        if (DataManager.getLatestBooking() == null) {

                            JOptionPane.showMessageDialog(
                                    null,
                                    "Please create a booking first"
                            );

                        }

                        else {
                        	
                        	DataManager.setCurrentBooking( 
                        			DataManager.getLatestBooking() 
                        			);

                            setVisible(false);

                            new AmendBooking();
                        }
                    }
                });


        //Booking summary button

        bookingSummaryButton.addActionListener(e -> {

            if (DataManager.getCurrentBooking() == null) {

                JOptionPane.showMessageDialog(
                        null,
                        "Please create a booking first"
                );
            }

            else {

                JOptionPane.showMessageDialog(
                        null,
                        "Booking Summary screen is currently unavailable."
                );
            }
        });


        //Cancel booking button

      
        cancelButton.addActionListener(e -> {

            if (DataManager.getLatestBooking() == null) {

                JOptionPane.showMessageDialog(
                        null,
                        "Please create a booking first"
                );
            }

            else {

                DataManager.setCurrentBooking(
                        DataManager.getLatestBooking()
                );

                setVisible(false);

                new CancelBooking();
            }

        });
      


        //Payment button

        payNewButton.addActionListener(e -> {

            if (DataManager.getCurrentBooking() == null) {

                JOptionPane.showMessageDialog(
                        null,
                        "Please create a booking first"
                );
            }

            else {

                setVisible(false);

                new PayNewBooking(
                        DataManager.getCurrentBooking()
                );
            }
        });


        //Confirmation button

        confirmationButton.addActionListener(e -> {

            if (DataManager.getCurrentBooking() == null) {

                JOptionPane.showMessageDialog(
                        null,
                        "Please create a booking first"
                );
            }

            else {

                JOptionPane.showMessageDialog(
                        null,
                        "Confirmation appears after payment"
                );
            }
        });


        //Discount button

        discountButton.addActionListener(e -> {

            setVisible(false);

            new OfferDiscount();

        });


        setVisible(true);

    }


    //Creates sidebar buttons

    private JButton createSidebarButton(

            String text,
            String iconPath

    ) {

        ImageIcon icon =
                new ImageIcon(iconPath);

        Image scaledImage =
                icon.getImage().getScaledInstance(
                        36,
                        36,
                        Image.SCALE_SMOOTH
                );

        JButton button =
                new JButton(
                        text,
                        new ImageIcon(scaledImage)
                );

        button.setMaximumSize(
                new Dimension(220, 68)
        );

        button.setPreferredSize(          //sidebar buttons dimensions and warping the icons to fit
                new Dimension(220, 68)
        );

        button.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.setIconTextGap(15);

        button.setBackground(
                new Color(155, 109, 255)
        );

        button.setForeground(Color.WHITE);

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );


        //Hover effect

        button.addMouseListener(
                new MouseAdapter() {

                    public void mouseEntered(
                            MouseEvent e
                    ) {

                        button.setBackground(
                                new Color(125, 85, 230)
                        );
                    }

                    public void mouseExited(
                            MouseEvent e
                    ) {

                        button.setBackground(
                                new Color(155, 109, 255)
                        );
                    }
                });

        return button;
    }


    //Creates dashboard cards

    private JPanel createDashboardCard(

            String title,
            String description

    ) {

        JPanel card =
                new JPanel();

        card.setLayout(
                new BorderLayout()
        );

        card.setBackground(Color.WHITE);

        card.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(        //card panel boarders
                                new Color(220, 220, 220),
                                1,
                                true
                        ),

                        new EmptyBorder(25, 25, 25, 25)
                )
        );


        JLabel titleLabel =
                new JLabel(title);             //labels

        titleLabel.setForeground(
                new Color(30, 30, 30)
        );

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        22
                )
        );


        JLabel descriptionLabel =
                new JLabel(

                        "<html><div style='width:220px'>"
                                + description
                                + "</div></html>"
                );

        descriptionLabel.setForeground(
                new Color(90, 90, 90)
        );

        descriptionLabel.setFont(
                new Font(                         //creates cards that can be used as a dashboard and have data put into dynamically
                        "SansSerif",
                        Font.PLAIN,
                        15
                )
        );


        card.add(
                titleLabel,
                BorderLayout.NORTH
        );

        card.add(
                descriptionLabel,
                BorderLayout.CENTER
        );

        return card;
    }


    public static void main(String[] args) {

        try {

            FlatLightLaf.setup(); //fkatlaf

        }

        catch (Exception e) {
//if there is an invalid data stop it
            e.printStackTrace();
        }


        DataManager.createTable();

        DataManager.createUserTable();
//ensures data can be stored

        DataManager.setCurrentBooking(null);  //if you log on there should be no bookings

        new SignUpOrLogin();   //startes from the signup or login page
    }
}


