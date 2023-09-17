import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Withdraw {

    JFrame frame;
    JPanel panel;
    JTextField idField; // TextField for ID
    JTextField amount;
    JLabel title;
    JLabel idLabel; // Label for ID
    JLabel quantity;
    JButton done;
    File file = new File("data.csv");

    public Withdraw() {

        frame = new JFrame("Withdraw");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(0, 0, 0, 0));
        panel.setBounds(0, 0, 800, 800);

        title = new JLabel("Withdraw");
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(Color.BLACK);
        title.setBounds(320, 20, 800, 50);

        idLabel = new JLabel("ID");
        idLabel.setBounds(200, 150, 250, 50);
        idLabel.setFont(new Font("Serif", Font.BOLD, 20));
        idLabel.setForeground(Color.BLACK);

        idField = new JTextField();
        idField.setBounds(300, 150, 250, 50);
        idField.setFont(new Font("Serif", Font.BOLD, 20));
        idField.setText("");

        quantity = new JLabel("Quantity");
        quantity.setBounds(200, 250, 250, 50);
        quantity.setFont(new Font("Serif", Font.BOLD, 20));
        quantity.setForeground(Color.BLACK);

        amount = new JTextField();
        amount.setBounds(300, 250, 250, 50);
        amount.setFont(new Font("Serif", Font.BOLD, 20));
        amount.setText("0");

        done = new JButton("Done");
        done.setBounds(560, 400, 150, 50);
        done.setBackground(Color.white);
        done.setFont(new Font("Serif", Font.BOLD, 15));
        done.addActionListener(e -> {

            try {

                StringBuilder fileContent = new StringBuilder();
                boolean idFound = false;

                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

                    String line;

                    while ((line = reader.readLine()) != null) {

                        // fileContent.setLength(0); // reset builder

                        String[] row = line.split(",");
                        String id = row[0];
                        String password = row[1];
                        String balance = row[2];

                        String idInput = idField.getText();
                        int amountInput = Integer.parseInt(amount.getText());

                        if (idInput.equals(id)) {

                            if (amountInput > Integer.parseInt(balance)) {
                                JOptionPane.showMessageDialog(null, "Insufficient Balance");
                            } else {
                                int newBalance = Integer.parseInt(balance) - amountInput;
                                idFound = true;
                                line = id + "," + password + "," + newBalance;
                            }

                        }

                        fileContent.append(line).append("\n");

                    }

                }

                if (!idFound) {
                    JOptionPane.showMessageDialog(null, "ID not found");
                } else {

                    try (FileWriter writer = new FileWriter(file)) {
                        BufferedWriter bw = new BufferedWriter(writer);
                        bw.write(fileContent.toString());
                        bw.flush();
                    }

                }

            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });

        JButton backButton = new JButton("Back");
        backButton.setBounds(20, 700, 200, 35);
        backButton.setFont(new Font("Serif", Font.PLAIN, 17));
        backButton.setBackground(Color.WHITE);
        backButton.addActionListener(new ActionListener() {

            @Override
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


        panel.add(title);
        panel.add(idLabel);
        panel.add(idField);
        panel.add(quantity);
        panel.add(amount);
        panel.add(done);
        panel.add(backButton);
        panel.add(background);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Withdraw();
        });

    }

}
