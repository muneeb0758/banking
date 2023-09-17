import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Deposit extends JFrame {

    private JPanel panel;
    private JLabel toAccountLabel, amountLabel;
    private JTextField toAccountField, amountField;
    private JButton depositButton , backButton;
    private String filename = "data.csv";

    public Deposit() {
        initializeUI();
        setupEventListeners();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setupBackground();
    }

    private void initializeUI() {
        panel = new JPanel();
        panel.setSize(800, 800);
        panel.setLayout(null);

        toAccountLabel = new JLabel("To Account:");
        toAccountLabel.setBounds(150, 50, 150, 50);
        toAccountLabel.setFont(new Font("Serif", Font.BOLD, 20));
        toAccountLabel.setForeground(Color.WHITE);
        panel.add(toAccountLabel);

        toAccountField = new JTextField();
        toAccountField.setBounds(300, 50, 200, 45);
        toAccountField.setFont(new Font("Serif", Font.PLAIN, 19));
        panel.add(toAccountField);

        amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(150, 100, 150, 50);
        amountLabel.setFont(new Font("Serif", Font.BOLD, 22));
        amountLabel.setForeground(Color.WHITE);
        panel.add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(300, 100, 200, 45);
        amountField.setFont(new Font("Serif", Font.PLAIN, 19));
        panel.add(amountField);

        depositButton = new JButton("Deposit");
        depositButton.setBounds(340, 200, 120, 40);
        depositButton.setBackground(Color.WHITE);
        depositButton.setFont(new Font("Serif", Font.BOLD, 20));
        panel.add(depositButton);
        
        
        backButton = new JButton("Back");
        backButton.setBounds(40,650,100,40);
        backButton.setBackground(Color.white);
        backButton.setFont(new Font("Serif", Font.BOLD, 20));
        panel.add(backButton);
        backButton.addActionListener(e -> {
        	
        	mainPage MainPage = new mainPage();
        	this.dispose();
        
    });

        add(panel);
        setSize(800, 800);
        setTitle("Deposit Money");
        setVisible(true);
    }

    private void setupEventListeners() {
        depositButton.addActionListener(e -> {
            String toAccount = toAccountField.getText();
            String amountText = amountField.getText();

            if (toAccount.isEmpty() || amountText.isEmpty()) {
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

                    if (accountNumber.equals(toAccount)) {
                        int balance = Integer.parseInt(values[2]);
                        balance += amount;
                        values[2] = String.valueOf(balance);
                    }

                    fileContent.append(String.join(",", values)).append("\n");
                }

                br.close();

                BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
                bw.write(fileContent.toString());
                bw.close();

                JOptionPane.showMessageDialog(panel, "Deposit Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
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
