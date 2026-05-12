import javax.swing.*;

public class NewBooking extends JFrame {

    public NewBooking() {

        setTitle("Booking Page");
        setSize(500, 400);
        setLayout(null);

        JLabel airportLabel = new JLabel("Airport:");
        airportLabel.setBounds(50, 50, 100, 30);

        String[] airports = {
            "London Heathrow - UK",
            "London Gatwick - UK",
            "Manchester - UK",
            "Stansted - UK",
            "Dublin - Ireland",
            "Cork - Ireland",
            "Shannon - Ireland",
            "JFK New York - USA",
            "Los Angeles LAX - USA",
            "Chicago O'Hare - USA",
            "Miami International - USA",
            "Toronto Pearson - Canada",
            "Vancouver International - Canada",
            "Calgary International - Canada",
            "Paris Charles de Gaulle - France",
            "Nice Côte d'Azur - France",
            "Rome Fiumicino - Italy",
            "Milan Malpensa - Italy",
            "Venice Marco Polo - Italy",
            "Madrid Barajas - Spain",
            "Barcelona El Prat - Spain",
            "Malaga Airport - Spain",
            "Sydney Kingsford Smith - Australia",
            "Melbourne Airport - Australia",
            "Brisbane Airport - Australia"
        };

        JComboBox airportBox = new JComboBox(airports);
        airportBox.setBounds(150, 50, 250, 30);

        add(airportLabel);
        add(airportBox);

        JLabel luggageLabel = new JLabel("Luggage:");
        luggageLabel.setBounds(50, 100, 100, 30);

        JTextField luggageField = new JTextField();
        luggageField.setBounds(150, 100, 250, 30);

        JLabel pickupLabel = new JLabel("Pickup Location:");
        pickupLabel.setBounds(50, 150, 120, 30);

        JTextField pickupField = new JTextField();
        pickupField.setBounds(170, 150, 230, 30);

        JButton bookButton = new JButton("Book Your Flight");
        bookButton.setBounds(150, 230, 150, 40);

        add(luggageLabel);
        add(luggageField);

        add(pickupLabel);
        add(pickupField);

        add(bookButton);

        setVisible(true);
    }


}
