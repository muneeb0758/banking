import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Transfer extends JFrame {

    private JPanel panel;
    private JLabel fromAccountLabel, toAccountLabel, amountLabel;
    private JTextField fromAccountField, toAccountField, amountField;
    private JButton transferButton;
    private String filename = "data.csv";

    public Transfer() {
        initializeUI();
        setupEventListeners();
        setupBackground();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initializeUI() {
        panel = new JPanel();
        panel.setLayout(null);

        fromAccountLabel = new JLabel("From Account:");
        fromAccountLabel.setBounds(150, 50, 150, 50);
        fromAccountLabel.setFont(new Font("Serif", Font.BOLD, 20));
        fromAccountLabel.setForeground(Color.WHITE);
        panel.add(fromAccountLabel);

        fromAccountField = new JTextField();
        fromAccountField.setBounds(300, 50, 200, 45);
        fromAccountField.setFont(new Font("Serif", Font.PLAIN, 19));
        panel.add(fromAccountField);

        toAccountLabel = new JLabel("To Account:");
        toAccountLabel.setBounds(150, 100, 150, 50);
        toAccountLabel.setFont(new Font("Serif", Font.BOLD, 20));
        toAccountLabel.setForeground(Color.WHITE);
        panel.add(toAccountLabel);

        toAccountField = new JTextField();
        toAccountField.setBounds(300, 100, 200, 45);
        toAccountField.setFont(new Font("Serif", Font.PLAIN, 19));
        panel.add(toAccountField);

        amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(150, 150, 150, 50);
        amountLabel.setFont(new Font("Serif", Font.BOLD, 22));
        amountLabel.setForeground(Color.WHITE);
        panel.add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(300, 150, 200, 45);
        amountField.setFont(new Font("Serif", Font.PLAIN, 19));
        panel.add(amountField);

        JButton backButton = new JButton("Back");
        backButton.setBounds(20, 550, 120, 40);
        backButton.setBackground(Color.WHITE);
        backButton.setFont(new Font("Serif", Font.BOLD, 20));
        backButton.addActionListener(e -> {
            mainPage page = new mainPage();
            dispose();
        });
        panel.add(backButton);

        transferButton = new JButton("Transfer");
        transferButton.setBounds(340, 250, 120, 40);
        transferButton.setBackground(Color.WHITE);
        transferButton.setFont(new Font("Serif", Font.BOLD, 20));
        panel.add(transferButton);

        add(panel);
        setSize(800, 800);
        setTitle("Money Transfer");
        setVisible(true);
    }

    private void setupEventListeners() {
        transferButton.addActionListener(e -> {
            String fromAccount = fromAccountField.getText();
            String toAccount = toAccountField.getText();
            String amountText = amountField.getText();

            if (fromAccount.isEmpty() || toAccount.isEmpty() || amountText.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Please fill in all the fields.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            int amount = Integer.parseInt(amountText);

            try {
                BufferedReader br = new BufferedReader(new FileReader(filename));
                StringBuilder fileContent = new StringBuilder();

                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    String accountNumber = values[0];

                    if (accountNumber.equals(fromAccount) || accountNumber.equals(toAccount)) {
                        int balance = Integer.parseInt(values[2]);

                        if (accountNumber.equals(fromAccount)) {
                            balance -= amount;
                        } else {
                            balance += amount;
                        }

                        values[2] = String.valueOf(balance);
                    }

                    fileContent.append(String.join(",", values)).append("\n");
                }

                br.close();

                BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
                bw.write(fileContent.toString());
                bw.close();

                JOptionPane.showMessageDialog(panel, "Transfer Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void setupBackground() {
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/image/Robo.jpg"));
        Image originalImage = imageIcon.getImage();

        int desiredWidth = 800;
        int desiredHeight = 800;

        Image resizedImage = originalImage.getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);

        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel background = new JLabel(resizedIcon);
        background.setBounds(0, 0, desiredWidth, desiredHeight);
        panel.add(background);
    }

}
