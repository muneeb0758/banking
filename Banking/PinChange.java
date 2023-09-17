import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PinChange {
    private JFrame frame;
    private JPanel panel;
    private JTextField currentPinField;
    private JTextField newPinField;
    private JButton changePinButton;
    private JButton backButton;
    private JLabel currentP;
    private JLabel newP;
    private JLabel title;
    private JLabel ID;
    private JTextField identity;

    public static String filePath = "data.csv";

    public PinChange() {
        frame = new JFrame("PIN Change");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(0, 0, 0, 0));
        panel.setBounds(0, 0, 800, 800);

        title = new JLabel("PIN Change");
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(Color.white);
        title.setBounds(320, 20, 800, 50);

        currentPinField = new JTextField(10);
        currentPinField.setBounds(300, 200, 250, 50);
        currentPinField.setFont(new Font("San Francisco Text", Font.BOLD, 20));

        newPinField = new JTextField(10);
        newPinField.setBounds(300, 300, 250, 50);
        newPinField.setFont(new Font("San Francisco Text", Font.BOLD, 20));

        changePinButton = new JButton("Save");
        changePinButton.setBounds(560, 400, 150, 50);
        changePinButton.setBackground(Color.white);
        changePinButton.setFont(new Font("San Francisco Text", Font.BOLD, 15));

        currentP = new JLabel("Current PIN: ");
        currentP.setBounds(180, 200, 250, 50);
        currentP.setFont(new Font("San Francisco Text", Font.BOLD, 20));
        currentP.setForeground(Color.white);

        newP = new JLabel("New PIN: ");
        newP.setBounds(200, 300, 250, 50);
        newP.setFont(new Font("San Francisco Text", Font.BOLD, 20));
        newP.setForeground(Color.white);

        ID = new JLabel("ID: ");
        ID.setBounds(250, 100, 250, 50);
        ID.setFont(new Font("San Francisco Text", Font.BOLD, 20));
        ID.setForeground(Color.white);

        identity = new JTextField();
        identity.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        identity.setForeground(Color.BLACK);
        identity.setBounds(300, 100, 250, 50);

        backButton = new JButton("Back");
        backButton.setBounds(60, 700, 150, 50);
        backButton.setBackground(Color.white);
        backButton.setFont(new Font("San Francisco Text", Font.BOLD, 15));
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

        // Set up the panel layout
        panel.setLayout(null);
        panel.add(title);
        panel.add(currentP);
        panel.add(newP);
        panel.add(currentPinField);
        panel.add(newPinField);
        panel.add(new JLabel()); // empty label for spacing
        panel.add(changePinButton);
        panel.add(backButton);
        panel.add(ID);
        panel.add(identity);
        panel.add(background);

        // Add action listener to the changePinButton
        changePinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentPin = currentPinField.getText();
                String newPin = newPinField.getText();

                if (currentPin.isEmpty() || newPin.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid PIN");
                }

                if (currentPin.equals(newPin)) {
                    JOptionPane.showMessageDialog(null, "New PIN cannot be the same as the current PIN");

                }

                else {
                    StringBuilder fileContent = new StringBuilder();
                    try (BufferedReader br = new BufferedReader(new FileReader("data.csv"))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            String[] row = line.split(",");
                            String storedId = row[0];
                            String storedPassword = row[1];
                            String balance = row[2];

                            if (currentPin.equals(storedPassword) && storedId.equals(identity.getText())) {
                                storedPassword = newPinField.getText();
                            }

                            fileContent.append(storedId).append(",").append(storedPassword).append(",")
                                    .append(balance).append("\n");
                        }

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
                        bw.write(fileContent.toString());
                        bw.flush();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }

                }

                // System.out.println("New PIN: " + newPin);
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }

}
