import javax.swing.*;
import java.awt.event.*;

public class Home extends JFrame {

    JButton bookingButton;

    public Home() {
        setTitle("Airport Booking System");
        setSize(400, 300);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        bookingButton = new JButton("Make New Booking");
        bookingButton.setBounds(100, 100, 200, 40);

        add(bookingButton);

        bookingButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                NewBooking bookingPage = new NewBooking();
                bookingPage.setVisible(true);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {

        new Home();
    }
}