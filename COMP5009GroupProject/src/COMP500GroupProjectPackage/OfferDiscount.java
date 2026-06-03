package COMP500GroupProjectPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * UI for setting global discounts. Intended to be an admin feature.
 * 
 * @author <a href="mailto:njw38@kent.ac.uk">Alexis Williams</a>
 * @version 2026-06-03
 */
public class OfferDiscount extends JFrame {
	private int discountPercentage;
	private int durationInDays;
	private JButton applyButton;
	private JButton homeButton;
	private JTextField percentageField;
	private JTextField durationField;
	
	public OfferDiscount() {
        setTitle("Offer Discount");

        setSize(950, 700);              

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
                new EmptyBorder(30, 40, 10, 40)
        );

        headingPanel.setLayout(
                new BoxLayout(
                        headingPanel,
                        BoxLayout.Y_AXIS
                )
        );
        

        // Title inside the window 
        
        JLabel title =
                new JLabel("Offer a Global Discount");

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

        // Set an informative subtitle
        
        JLabel subtitle =
                new JLabel(
                        "Fill in the details for a global discount you wish to apply."
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
        
        JPanel formPanel =
                new JPanel();

        formPanel.setBackground(Color.WHITE);

        formPanel.setBorder(
                new EmptyBorder(40, 40, 40, 40)
        );

        formPanel.setLayout(
                new java.awt.GridLayout(
                        3,
                        2,
                        20,
                        20
                )
        );


        JLabel percentageLabel =
                new JLabel("Discount Percentage:");

        percentageField =
                new JTextField();


        JLabel durationLabel =
                new JLabel("Duration In Days:");

        durationField =
                new JTextField();


        applyButton =
                new JButton("Apply Discount");

        applyButton.setBackground(
                new Color(155, 109, 255)
        );

        applyButton.setForeground(Color.WHITE);
        
        homeButton =
                new JButton("Return Home");

        homeButton.setBackground(
                new Color(230, 230, 230)
        );


        formPanel.add(percentageLabel);

        formPanel.add(percentageField);

        formPanel.add(durationLabel);

        formPanel.add(durationField);

        formPanel.add(applyButton);

        formPanel.add(homeButton);
        
        
        backgroundPanel.add(
                headingPanel,
                BorderLayout.NORTH
                
        );
        
        backgroundPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        add(backgroundPanel);
        
        applyButton.addActionListener(e -> {

            try {

                double percentage =
                        Double.parseDouble(
                                percentageField.getText()
                        );			//As percentages can be like 19.93% instead of having to be rounded to 19 or 20%

                int duration =
                        Integer.parseInt(
                                durationField.getText()
                        );

                if (percentage <= 0 || percentage > 100) {	//Has to be a percentage which doesn't make the booking more than what the cost is or a negative number (ie negative percentages) 

                    JOptionPane.showMessageDialog(
                            null,
                            "Discount must be between 1 and 100."
                    );

                    return;
                }

                if (duration <= 0) {		//Is the duration set for any amount of time - as we must apply for a discount for a certain period of time

                    JOptionPane.showMessageDialog(
                            null,
                            "Duration must be above 0."
                    );

                    return;
                }

                DataManager.setGlobalDiscount(
                        percentage,
                        duration
                );			//Allows us to actually store all the discount details in our database

                JOptionPane.showMessageDialog(
                        null,
                        "Global discount applied successfully."
                );

            }

            catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        null,
                        "Invalid input."
                );
            }
        });
        
        homeButton.addActionListener(e -> {

            setVisible(false);

            new Home();		//Closes the Frame and diverts user back to the home page

        });

        setVisible(true);
    }
}
	