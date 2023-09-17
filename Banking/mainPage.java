import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class mainPage {

    private JFrame frame;
    private JPanel panel;
    private JLabel titleLabel, name;
    private JLabel balanceLabel;
    private JButton withdrawButton;
    private JButton depositButton;
    private JButton transferButton;
    private JButton miniStatement;
    private JButton PinChange;
    private JButton backButton;

    public mainPage() {
        // ...

        try (BufferedReader br = new BufferedReader(new FileReader("data.csv"))) {
            String line;
            br.readLine();
            if ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                if (row.length >= 3) {
                    String id = row[0];
                    String password = row[1];
                    String balance = row[2];

                    balanceLabel = new JLabel("Current Balance: " + balance);
                    balanceLabel.setFont(new Font("Arial", Font.BOLD, 26));
                    balanceLabel.setHorizontalAlignment(JLabel.CENTER);
                    balanceLabel.setForeground(Color.white);
                    balanceLabel.setBounds(50, 100, 800, 50);
            
                    // Create ID label
                    name = new JLabel("ID: " + id);
                    name.setFont(new Font("Arial", Font.BOLD, 20));
                    name.setHorizontalAlignment(JLabel.CENTER);
                    name.setForeground(Color.white);
                    name.setBounds(320, 150, 250, 50);

                } else {
                   System.out.println("File is empty");

                }
            }
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // Create JFrame
        frame = new JFrame("ATM");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        // Create JPanel
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(0, 0, 0, 0));
        panel.setBounds(0, 0, 800, 800);

        // Create title label
        titleLabel = new JLabel("Welcome to the ATM");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(Color.white);
        titleLabel.setBounds(60, 20, 800, 50);

        // Create balance label
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 26));
        balanceLabel.setHorizontalAlignment(JLabel.CENTER);
        balanceLabel.setForeground(Color.white);
        balanceLabel.setBounds(50, 100, 800, 50);

        // Create ID label
        name.setFont(new Font("Arial", Font.BOLD, 20));
        name.setHorizontalAlignment(JLabel.CENTER);
        name.setForeground(Color.white);
        name.setBounds(320, 150, 250, 50);
        panel.add(name);

        // Create buttons

        miniStatement = new JButton("Mini Statement");
        miniStatement.setFont(new Font("Arial", Font.BOLD, 20));
        miniStatement.setBackground(Color.BLACK);
        miniStatement.setForeground(Color.white);
        miniStatement.setBounds(0, 400, 200, 50);
        miniStatement.setFocusable(false);
        miniStatement.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == miniStatement) {
                    MiniStatement miniStatement = new MiniStatement();
                    frame.dispose();

                }
            }

        });

        PinChange = new JButton("Pin Change");
        PinChange.setFont(new Font("Arial", Font.BOLD, 20));
        PinChange.setBackground(Color.BLACK);
        PinChange.setForeground(Color.white);
        PinChange.setBounds(600, 400, 200, 50);
        PinChange.setFocusable(false);
        PinChange.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                PinChange pin = new PinChange();
                frame.dispose();
            }
        });

        withdrawButton = new JButton("Withdraw");
        withdrawButton.setFont(new Font("Arial", Font.BOLD, 20));
        withdrawButton.setBackground(Color.BLACK);
        withdrawButton.setForeground(Color.white);
        withdrawButton.setBounds(600, 500, 200, 50);
        withdrawButton.setFocusable(false);
        withdrawButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                Withdraw withdraw = new Withdraw();
                frame.dispose();
            }
        });

        depositButton = new JButton("Deposit");
        depositButton.setFont(new Font("Arial", Font.BOLD, 20));
        depositButton.setBackground(Color.BLACK);
        depositButton.setForeground(Color.white);
        depositButton.setBounds(600, 600, 200, 50);
        depositButton.setFocusable(false);
        depositButton.addActionListener(e -> {
            Deposit deposit = new Deposit();
            frame.dispose();
        });

        transferButton = new JButton("Transfer");
        transferButton.setFont(new Font("Arial", Font.BOLD, 20));
        transferButton.setBackground(Color.BLACK);
        transferButton.setForeground(Color.white);
        transferButton.setBounds(0, 500, 200, 50);
        transferButton.setFocusable(false);
        transferButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Transfer transfer = new Transfer();
                frame.dispose();
            }
        });

        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 20));
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.white);
        backButton.setBounds(0, 600, 200, 50);
        backButton.setFocusable(false);
        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPage FirstPage = new LoginPage();
                frame.dispose();

            }

        });

        // Add components to the panel
        panel.add(PinChange);
        panel.add(miniStatement);
        panel.add(titleLabel);
        panel.add(balanceLabel);
        panel.add(withdrawButton);
        panel.add(depositButton);
        panel.add(transferButton);
        panel.add(backButton);

        // Add panel to the frame
        // frame.add(panel);

        // Set frame visible

        ImageIcon imageIcon2 = new ImageIcon(getClass().getResource("/image/Robo.jpg"));
        Image originalImage = imageIcon2.getImage();

        int desiredWidth = 800;
        int desiredHeight = 800;

        Image resizedImage = originalImage.getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);

        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel background = new JLabel(resizedIcon);
        background.setBounds(0, 0, desiredWidth, desiredHeight);

        frame.setSize(desiredWidth, desiredHeight);

        panel.add(background);
        frame.add(panel);
        frame.setVisible(true);

    }

//    public static void main(String[] args) {
//    	  SwingUtilities.invokeLater(new Runnable() {
//    	    public void run() {
//    	      new mainPage(); 
//    	    }
//    	  });
//    	}
}
