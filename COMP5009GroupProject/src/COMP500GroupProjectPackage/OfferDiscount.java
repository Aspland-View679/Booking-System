package COMP500GroupProjectPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
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
	
	public OfferDiscount {
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
        
        
	}
}