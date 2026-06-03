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
    private JButton payNewButton;
    private JButton confirmationButton;  //This is for the creation of buttons
    private JButton discountButton;
    private JButton bookingSummaryButton;

    public Home() {

        setTitle("Airport Booking System"); //sets the window application window size, title and layout

        setSize(1100, 700);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes when you close the application

        setLayout(new BorderLayout());


        //Main background panel for the application

        JPanel backgroundPanel =
                new JPanel(new BorderLayout());

        backgroundPanel.setBackground(
                new Color(245, 245, 245)
        );


        //Sidebar section of the application

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
                        "<html><center>Airport<br>System</center></html>"
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

        logo.setAlignmentX(Component.CENTER_ALIGNMENT);


        sidebar.add(airportLogo);

        sidebar.add(
                Box.createRigidArea(
                        new Dimension(0, 15)
                )
        );

        sidebar.add(logo);

        sidebar.add(
                Box.createRigidArea(
                        new Dimension(0, 40)
                )
        );


        //sets up the booking button

        bookingButton =
                createSidebarButton(
                        "Make New Booking",
                        "src/icons/NewBooking.png"
                );


        //sets up the amend button

        amendButton =
                createSidebarButton(
                        "Amend Booking",
                        "src/icons/AmendBooking.png"
                );


        //sets up the cancel button

        cancelButton =
                createSidebarButton(
                        "Cancel Booking",
                        "src/icons/CancelBooking.png"
                );


        //sets up the payment button

        payNewButton =
                createSidebarButton(
                        "Pay for Booking",
                        "src/icons/Payment.png"
                );


        //sets up the confirmation button

        confirmationButton =
                createSidebarButton(
                        "Confirmation Message",
                        "src/icons/Booking.png"
                );


        //sets up the discount button

        discountButton =
                createSidebarButton(
                        "Offer Discount",
                        "src/icons/Booking.png"
                );


        //sets up the booking summary button

        bookingSummaryButton =
                createSidebarButton(
                        "Booking Summary",
                        "src/icons/TicketSummary.png"
                );


        //creates the buttons to the application

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


        //Main dashboard section

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


        //Main heading

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


        //Sub heading text

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


        cardsPanel.add(
                createDashboardCard(
                        "New Booking",
                        "Create a new airport transfer booking"
                )
        );

        cardsPanel.add(
                createDashboardCard(
                        "Amend Booking",
                        "Update dates, times and booking details"
                )
        );

        cardsPanel.add(
                createDashboardCard(
                        "Payments",
                        "Manage payment processing and booking fees"
                )
        );

        cardsPanel.add(
                createDashboardCard(
                        "Booking Summary",
                        "Review all booking information"
                )
        );

        dashboardPanel.add(
                cardsPanel,
                BorderLayout.CENTER
        );


        //Adds everything to the application

        backgroundPanel.add(
                sidebar,
                BorderLayout.WEST
        );

        backgroundPanel.add(
                dashboardPanel,
                BorderLayout.CENTER
        );

        add(backgroundPanel);


        bookingButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false); // does not call the JPanel

                //allows user to click and go onto making a booking

                new NewBooking();
            }
        });
        
        discountButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false); // does not call the JPanel

                //allows user to click and go onto making a booking

                new OfferDiscount();
            }
        });

        amendButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (DataManager.getCurrentBooking() == null) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Please create a booking first"
                    );

                } else {

                    setVisible(false);

                    new AmendBooking();
                }
            }
        });

        bookingSummaryButton.addActionListener(e -> {

            if (DataManager.getCurrentBooking() == null) {

                JOptionPane.showMessageDialog(
                        null,
                        "Please create a booking first" //This two sections of code stops the user going further if they haven't made a booking to review or amend
                );

            } else {

                setVisible(false);

                new BookingSummary(DataManager.getCurrentBooking()); //this if you made a booking calls it back
            }
        });

        setVisible(true); //Calls the JPanel
    }


    //Creates modern sidebar buttons

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

        button.setPreferredSize(
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


        //Creates hover effect for sidebar buttons

        button.addMouseListener(new MouseAdapter() {

            public void mouseEntered(MouseEvent e) {

                button.setBackground(
                        new Color(125, 85, 230)
                );
            }

            public void mouseExited(MouseEvent e) {

                button.setBackground(
                        new Color(155, 109, 255)
                );
            }
        });

        return button;
    }


    //Creates dashboard information cards

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

                        BorderFactory.createLineBorder(
                                new Color(220, 220, 220),
                                1,
                                true
                        ),

                        new EmptyBorder(25, 25, 25, 25)
                )
        );


        JLabel titleLabel =
                new JLabel(title);

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
                new Font(
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

            FlatLightLaf.setup();

        } catch (Exception e) {

            e.printStackTrace();
        }

        DataManager.createTable(); //connects to sqlite to check if the booking table exists and creates if there isn't a booking table

        Booking latestBooking =
                DataManager.getLatestBooking(); //searchs the database to find the most recent booking and make it a booking object

        DataManager.setCurrentBooking(
                latestBooking //this stores booking into ram so it can be called back even when restarting the application
        );

        new Home(); //relaunches the homepage once the backend is complete.
    }
}
