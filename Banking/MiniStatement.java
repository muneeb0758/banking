import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.*;
import com.toedter.calendar.JDateChooser;

public class MiniStatement {

    JDateChooser dateChooser;
    JTextArea statementArea;
    Font font = new Font("San Francisco Text", Font.BOLD, 35);

    public MiniStatement() {
        // Create a JFrame
        JFrame frame = new JFrame(" Mini Statement");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); // Set layout to null

        // Create a JPanel
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 800, 800);
        panel.setLayout(null); // Set layout to null

        // Create and position the components
        JLabel headingLabel = new JLabel("Mini Statement");
        headingLabel.setBounds(270, 20, 300, 30);
        headingLabel.setFont(font);
        headingLabel.setForeground(Color.WHITE);

        JButton statementButton = new JButton("Get Mini Statement");
        statementButton.setBounds(250, 150, 200, 30);
        statementButton.setFont(new Font("San Francisco Text", Font.PLAIN, 15));
        statementButton.setBackground(Color.WHITE);
        statementButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == statementButton) {
                    getMiniStatement();
                }
            }
        });

        statementArea = new JTextArea();
        statementArea.setBounds(100, 200, 600, 400);
        statementArea.setEditable(false);
        statementArea.setBackground(new Color(0, 0, 0, 0)); // Set the background color to white

        JLabel dateLabel = new JLabel("Select Date:");
        dateLabel.setFont(new Font("Arial", Font.BOLD, 22));
        dateLabel.setBounds(100, 100, 150, 30);
        dateLabel.setForeground(Color.WHITE);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(250, 100, 200, 30);

        JButton backButton = new JButton("Back");
        backButton.setBounds(20, 700, 200, 30);
        backButton.setFont(new Font("San Francisco Text", Font.PLAIN, 15));
        backButton.setBackground(Color.WHITE);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == backButton) {
                    mainPage page = new mainPage();
                }
            }
        });

        ImageIcon imageIcon2 = new ImageIcon(getClass().getResource("/image/Robo.jpg"));
        Image originalImage = imageIcon2.getImage();

        int desiredWidth = 800;
        int desiredHeight = 800;

        Image resizedImage = originalImage.getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);

        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel background = new JLabel(resizedIcon);
        background.setBounds(0, 0, desiredWidth, desiredHeight);
        frame.setSize(desiredWidth, desiredHeight);

        // Add the components to the panel
        panel.add(headingLabel);
        panel.add(statementButton);
        panel.add(backButton);
        // panel.add(statementArea);
        panel.add(dateLabel);
        panel.add(dateChooser);
        panel.add(background);

        // Add the panel to the frame
        frame.add(panel);

        // Make the frame visible
        frame.setVisible(true);
    }

    private void getMiniStatement() {

        JFrame frame = new JFrame("Mini Statement");
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 800, 800);
        panel.setLayout(null);

        JLabel headingLabel = new JLabel("Mini Statement");
        headingLabel.setBounds(270, 20, 400, 30);
        headingLabel.setFont(font);

        JButton backButton = new JButton("Back");
        backButton.setBounds(20, 700, 200, 30);
        backButton.setFont(new Font("San Francisco Text", Font.PLAIN, 15));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == backButton) {
                    MiniStatement page = new MiniStatement();
                }
            }
        });

        Date selectedDate = dateChooser.getDate();

        if (selectedDate == null) {
            JOptionPane.showMessageDialog(null, "Please select a date.");
            return;
        }

        SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
        String monthString = monthFormat.format(selectedDate);

        String path = "statements/" + monthString + ".txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            StringBuilder statementBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                statementBuilder.append(line);
                statementBuilder.append("\n");
            }
            statementArea.setText(statementBuilder.toString());
        } catch (Exception e) {
            statementArea.setText("No statement found for the selected date.");
            e.printStackTrace();
        }
        panel.add(headingLabel);
        panel.add(statementArea);
        panel.add(backButton);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.add(panel);
        frame.setVisible(true);

    }

}
