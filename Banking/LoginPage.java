import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class LoginPage implements ActionListener {

    public static JFrame frame;
    public static JPanel panel;
    public static JPanel panel2;
    public static JLabel identity;
    public static JLabel pass;

    public static JLabel label;
    public static JButton button;
    public static JTextField ID;
    public static JTextField password;
    public static JButton login;
    public static JButton exit;
    public static String[] details = new String[2];
    public static String filePath = "data.csv";

    public LoginPage() {

        frame = new JFrame();
        panel = new JPanel();
        label = new JLabel();
        button = new JButton();
        ID = new JTextField();
        password = new JTextField();
        login = new JButton();
        exit = new JButton();

        // frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);

        panel.setLayout(null);
        panel.setBounds(0, 0, 800, 800);

        panel2 = new JPanel();
        panel2.setLayout(null);
        panel2.setBackground(new Color(0, 0, 0, 0));
        panel2.setBounds(200, 100, 400, 400);
        frame.add(panel2);
        frame.add(panel);

        label.setText("Login Page");
        label.setFont(new Font("Arial", Font.BOLD, 40));
        label.setForeground(Color.white);
        label.setBounds(300, 20, 800, 50);

        identity = new JLabel();
        identity.setText("ID");
        identity.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        identity.setForeground(Color.WHITE);
        identity.setBounds(160, 200, 800, 50);

        pass = new JLabel();
        pass.setText("Pass");
        pass.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        pass.setForeground(Color.WHITE);
        pass.setBounds(150, 250, 800, 50);

        ID.setText("");
        ID.setBounds(20, 100, 355, 50);
        ID.setFont(new Font("Serif", Font.PLAIN, 22));

        password.setBounds(20, 150, 355, 50);
        password.setFont(new Font("Serif", Font.PLAIN, 22));

        button.setText("Login");
        button.setBounds(230, 250, 130, 50);
        button.setBackground(Color.WHITE);
        button.addActionListener(this);

        exit.setText("Exit");
        exit.setBounds(30, 250, 130, 50);
        exit.setBackground(Color.WHITE);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/image/Girl.jpg"));
        Image originalImage = imageIcon.getImage();

        int desiredWidth = 800;
        int desiredHeight = 900;

        Image resizedImage = originalImage.getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);

        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel background = new JLabel(resizedIcon);
        background.setBounds(0, 0, desiredWidth, desiredHeight);

        frame.setSize(desiredWidth, desiredHeight);

        frame.add(panel2);
        frame.add(panel);
        panel.add(background);

        panel.add(identity);
        panel.add(pass);

        panel2.add(ID);

        panel2.add(password);

        panel2.add(button);

        panel2.add(exit);

        panel.add(label);

        panel.add(background);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            String text1 = ID.getText();
            String text2 = password.getText();

            // Check if the ID and Password match any records in the CSV file
            boolean loginSuccessful = checkLoginCredentials(text1, text2);

            if (loginSuccessful) {
                System.out.println("Login successful");
                // Proceed to the main page or perform any other action
                mainPage MainPage = new mainPage();
                frame.dispose();
            } else {
                System.out.println("Invalid ID or Password");
                JOptionPane.showMessageDialog(null, "Invalid ID or Password");
            }
        }
    }

    private boolean checkLoginCredentials(String id, String password) {
        boolean loginSuccessful = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                String storedId = row[0];
                String storedPassword = row[1];

                if (id.equals(storedId) && password.equals(storedPassword)) {
                    loginSuccessful = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return loginSuccessful;
    }
}
