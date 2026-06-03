package COMP500GroupProjectPackage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class BookingSummary extends JFrame {

    public BookingSummary(Booking booking) {

        setTitle("Booking Summary");

        setSize(850, 650);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());


        //Main panel

        JPanel backgroundPanel =
                new JPanel(new BorderLayout());

        backgroundPanel.setBackground(
                new Color(245, 245, 245)
        );


        //Heading section

        JPanel headingPanel =
                new JPanel();

        headingPanel.setBackground(
                new Color(245, 245, 245)
        );

        headingPanel.setBorder(
                new EmptyBorder(30, 40, 10, 40)
        );

        headingPanel.setLayout(
                new BoxLayout(
                        headingPanel,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel title =
                new JLabel("Booking Summary");

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        34
                )
        );

        title.setForeground(
                new Color(30, 30, 30)
        );


        JLabel subtitle =
                new JLabel(
                        "Review your booking details below"
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

        headingPanel.add(
                Box.createRigidArea(
                        new Dimension(0, 8)
                )
        );

        headingPanel.add(subtitle);


        //Summary card

        JPanel summaryCard =
                new JPanel();

        summaryCard.setLayout(
                new GridBagLayout()
        );

        summaryCard.setBackground(
                new Color(255, 255, 255)
        );

        summaryCard.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                new Color(220, 220, 220),
                                1,
                                true
                        ),

                        new EmptyBorder(35, 35, 35, 35)
                )
        );


        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(18, 18, 18, 18);

        gbc.anchor =
                GridBagConstraints.WEST;


        Font labelFont =
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        16
                );

        Font valueFont =
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        16
                );

        Color purple =
                new Color(155, 109, 255);


        //Booking ID

        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel bookingIDLabel =
                new JLabel("Booking ID:");

        bookingIDLabel.setFont(labelFont);

        bookingIDLabel.setForeground(purple);

        summaryCard.add(
                bookingIDLabel,
                gbc
        );

        gbc.gridx = 1;

        JLabel bookingIDValue =
                new JLabel(
                        booking.getDisplayBookingID()
                );

        bookingIDValue.setFont(valueFont);

        summaryCard.add(
                bookingIDValue,
                gbc
        );


        //Airport

        gbc.gridx = 0;
        gbc.gridy++;

        JLabel airportLabel =
                new JLabel("Airport:");

        airportLabel.setFont(labelFont);

        airportLabel.setForeground(purple);

        summaryCard.add(
                airportLabel,
                gbc
        );

        gbc.gridx = 1;

        JLabel airportValue =
                new JLabel(
                        booking.getAirport()
                );

        airportValue.setFont(valueFont);

        summaryCard.add(
                airportValue,
                gbc
        );


        //Pickup Location

        gbc.gridx = 0;
        gbc.gridy++;

        JLabel pickupLabel =
                new JLabel("Pickup Location:");

        pickupLabel.setFont(labelFont);

        pickupLabel.setForeground(purple);

        summaryCard.add(
                pickupLabel,
                gbc
        );

        gbc.gridx = 1;

        JLabel pickupValue =
                new JLabel(
                        booking.getPickupLocation()
                );

        pickupValue.setFont(valueFont);

        summaryCard.add(
                pickupValue,
                gbc
        );


        //Booking Date

        gbc.gridx = 0;
        gbc.gridy++;

        JLabel bookingDateLabel =
                new JLabel("Booking Date:");

        bookingDateLabel.setFont(labelFont);

        bookingDateLabel.setForeground(purple);

        summaryCard.add(
                bookingDateLabel,
                gbc
        );

        gbc.gridx = 1;

        JLabel bookingDateValue =
                new JLabel(
                        booking.getBookingDate()
                );

        bookingDateValue.setFont(valueFont);

        summaryCard.add(
                bookingDateValue,
                gbc
        );


        //Booking Time

        gbc.gridx = 0;
        gbc.gridy++;

        JLabel bookingTimeLabel =
                new JLabel("Booking Time:");

        bookingTimeLabel.setFont(labelFont);

        bookingTimeLabel.setForeground(purple);

        summaryCard.add(
                bookingTimeLabel,
                gbc
        );

        gbc.gridx = 1;

        JLabel bookingTimeValue =
                new JLabel(
                        booking.getBookingTime()
                );

        bookingTimeValue.setFont(valueFont);

        summaryCard.add(
                bookingTimeValue,
                gbc
        );


        //Luggage Amount

        gbc.gridx = 0;
        gbc.gridy++;

        JLabel luggageLabel =
                new JLabel("Luggage Amount:");

        luggageLabel.setFont(labelFont);

        luggageLabel.setForeground(purple);

        summaryCard.add(
                luggageLabel,
                gbc
        );

        gbc.gridx = 1;

        JLabel luggageValue =
                new JLabel(
                        String.valueOf(
                                booking.getLuggageAmount()
                        )
                );

        luggageValue.setFont(valueFont);

        summaryCard.add(
                luggageValue,
                gbc
        );


        //Estimated Duration

        gbc.gridx = 0;
        gbc.gridy++;

        JLabel durationLabel =
                new JLabel("Estimated Duration:");

        durationLabel.setFont(labelFont);

        durationLabel.setForeground(purple);

        summaryCard.add(
                durationLabel,
                gbc
        );

        gbc.gridx = 1;

        JLabel durationValue =
                new JLabel(
                        booking.getEstimatedDuration()
                        + " minutes"
                );

        durationValue.setFont(valueFont);

        summaryCard.add(
                durationValue,
                gbc
        );


        //Total Price

        gbc.gridx = 0;
        gbc.gridy++;

        JLabel priceLabel =
                new JLabel("Total Price:");

        priceLabel.setFont(labelFont);

        priceLabel.setForeground(purple);

        summaryCard.add(
                priceLabel,
                gbc
        );

        gbc.gridx = 1;

        JLabel priceValue =
                new JLabel(
                        "£"
                        + String.format(
                                "%.2f",
                                booking.getTotalPrice()
                        )
                );

        priceValue.setFont(valueFont);

        summaryCard.add(
                priceValue,
                gbc
        );


        JPanel wrapper =
                new JPanel(new BorderLayout());

        wrapper.setBackground(
                new Color(245, 245, 245)
        );

        wrapper.setBorder(
                new EmptyBorder(20, 40, 40, 40)
        );

        wrapper.add(summaryCard);


        //Buttons

        JPanel buttonPanel =
                new JPanel();

        buttonPanel.setBackground(
                new Color(245, 245, 245)
        );


        JButton homeButton =
                new JButton("Return Home");

        homeButton.setFocusPainted(false);

        homeButton.setBorderPainted(false);

        homeButton.setBackground(
                new Color(230, 230, 230)
        );

        homeButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );


        JButton payButton =
                new JButton("Proceed To Payment");

        payButton.setFocusPainted(false);

        payButton.setBorderPainted(false);

        payButton.setBackground(
                new Color(155, 109, 255)
        );

        payButton.setForeground(Color.WHITE);

        payButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );


        buttonPanel.add(homeButton);

        buttonPanel.add(
                Box.createRigidArea(
                        new Dimension(15, 0)
                )
        );

        buttonPanel.add(payButton);


        homeButton.addActionListener(e -> {

            setVisible(false);

            new Home();

        });


        payButton.addActionListener(e -> {

            setVisible(false);

            new PayNewBooking(booking);

        });


        //Adds everything

        backgroundPanel.add(
                headingPanel,
                BorderLayout.NORTH
        );

        backgroundPanel.add(
                wrapper,
                BorderLayout.CENTER
        );

        backgroundPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        add(backgroundPanel);

        setVisible(true);
    }
}

