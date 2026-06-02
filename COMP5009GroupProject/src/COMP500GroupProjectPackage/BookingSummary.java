
package COMP500GroupProjectPackage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;

public class BookingSummary extends JFrame {

	JButton payButton;

	public BookingSummary(Booking booking) {

		setTitle("Booking Summary");

        setSize(900, 650);    // This is to create a summary ticket before the user continues

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Main background panel

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


        JLabel titleLabel =
                new JLabel("Booking Summary");

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        34
                )
        );

        titleLabel.setForeground(
                new Color(30, 30, 30)
        );


        JLabel subtitle =
                new JLabel(
                        "Review all customer booking details before payment"
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


        headingPanel.add(titleLabel);

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
                new BoxLayout(
                        summaryCard,
                        BoxLayout.Y_AXIS
                )
        );

        summaryCard.setBackground(Color.WHITE);

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


        JPanel cardWrapper =
                new JPanel(new BorderLayout());

        cardWrapper.setBackground(
                new Color(245, 245, 245)
        );

        cardWrapper.setBorder(
                new EmptyBorder(10, 40, 40, 40)
        );

        cardWrapper.add(summaryCard);


        //Booking information labels

        JLabel airportLabel =
                createSummaryLabel(
                        "Airport",
                        booking.getAirport()
                );


        JLabel pickupLabel =
                createSummaryLabel(
                        "Pickup Location",
                        booking.getPickupLocation()
                );


        JLabel dateLabel =
                createSummaryLabel(
                        "Date",
                        booking.getBookingDate()
                );


        JLabel timeLabel =
                createSummaryLabel(
                        "Time",
                        booking.getBookingTime()
                );


        JLabel luggageLabel =
                createSummaryLabel(
                        "Luggage Amount",
                        String.valueOf(
                                booking.getLuggageAmount()
                        )
                );


        JLabel durationLabel =
                createSummaryLabel(
                        "Estimated Duration",
                        booking.getEstimatedDuration()
                        + " minutes"
                );


        JLabel totalPriceLabel =
                createSummaryLabel(
                        "Total Price",
                        "£" + booking.getTotalPrice()
                );


        //Displays all the information from the booking to display in the booking summary

        summaryCard.add(airportLabel);

        summaryCard.add(
                Box.createRigidArea(
                        new Dimension(0, 15)
                )
        );

        summaryCard.add(pickupLabel);

        summaryCard.add(
                Box.createRigidArea(
                        new Dimension(0, 15)
                )
        );

        summaryCard.add(dateLabel);

        summaryCard.add(
                Box.createRigidArea(
                        new Dimension(0, 15)
                )
        );

        summaryCard.add(timeLabel);

        summaryCard.add(
                Box.createRigidArea(
                        new Dimension(0, 15)
                )
        );

        summaryCard.add(luggageLabel);

        summaryCard.add(
                Box.createRigidArea(
                        new Dimension(0, 15)
                )
        );

        summaryCard.add(durationLabel);

        summaryCard.add(
                Box.createRigidArea(
                        new Dimension(0, 15)
                )
        );

        summaryCard.add(totalPriceLabel);


        //Button section

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                20,
                                20
                        )
                );

        buttonPanel.setBackground(
                new Color(245, 245, 245)
        );


        //Proceed to payment button

        payButton =
                new JButton("Proceed To Payment");

        payButton.setPreferredSize(
                new Dimension(220, 50)
        );

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
                        15
                )
        );


        //Cancel booking button

        JButton cancelButton =
        		new JButton("Cancel Booking");

        cancelButton.setPreferredSize(
                new Dimension(180, 50)
        );

        cancelButton.setFocusPainted(false);

        cancelButton.setBorderPainted(false);

        cancelButton.setBackground(
                new Color(230, 230, 230)
        );

        cancelButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        15
                )
        );


        //Amend booking button

        JButton amendButton =
                new JButton("Amend Booking");

        amendButton.setPreferredSize(
                new Dimension(180, 50)
        );

        amendButton.setFocusPainted(false);

        amendButton.setBorderPainted(false);

        amendButton.setBackground(
                new Color(230, 230, 230)
        );

        amendButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        15
                )
        );


        //Adds buttons to panel

        buttonPanel.add(payButton);

        buttonPanel.add(cancelButton);

        buttonPanel.add(amendButton);


        //Adds everything to frame

        backgroundPanel.add(
                headingPanel,
                BorderLayout.NORTH
        );

        backgroundPanel.add(
                cardWrapper,
                BorderLayout.CENTER
        );

        backgroundPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        add(backgroundPanel);


        amendButton.addActionListener(e -> {

            setVisible(false);

            new AmendBooking();

        });


        payButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);

                PayNewBooking payment = new PayNewBooking(
            		   booking
            	);
            }

            //On being clicked, hides the JFrame and opens payment frame
        });


        cancelButton.addActionListener(e -> {

        	DataManager.cancelBooking(
        	        booking.getBookingID()
        	);

        	DataManager.setCurrentBooking(null);

            JOptionPane.showMessageDialog(
                    null,
                    "Booking Cancelled"
            );


            setVisible(false);

            new Home();

        });


        setVisible(true);
    }


    //Creates booking summary labels

    private JLabel createSummaryLabel(

            String title,
            String value

    ) {

        JLabel label =
                new JLabel(

                        "<html>"
                        + "<span style='font-size:16px; color:#555555;'>"
                        + title
                        + "</span><br>"
                        + "<span style='font-size:20px; font-weight:bold; color:#1E1E1E;'>"
                        + value
                        + "</span>"
                        + "</html>"

                );

        return label;
    }
}

