package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class LoginWindow extends JFrame{
    JFrame frame = this;
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    int screenX = toolkit.getScreenSize().width, screenY = toolkit.getScreenSize().height;
    AccountManager accountManager;

    public LoginWindow(AccountManager acc){
        accountManager = acc;
        int frameX = 800, frameY = 500;

        JLabel appLogo = new JLabel("WarsztApp", SwingConstants.CENTER);
        appLogo.setBounds(250, 25, 300, 125);
        appLogo.setFont(new Font("Arial", Font.BOLD, 50));

        JLabel loginLabel = new JLabel("Login");
        loginLabel.setBounds(100, 200, 180, 50);
        loginLabel.setFont(new Font("Arial", Font.PLAIN, 35));
        JTextField loginField = new JTextField(20);
        loginField.setBounds(280, 200, 450, 50);
        loginField.setFont(new Font("Arial", Font.PLAIN, 25));

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(100, 270, 180, 50);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 35));
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(280, 270, 450, 50);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 25));

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(100, 380, 200, 40);
        loginButton.setFont(new Font("Arial", Font.PLAIN, 25));
        loginButton.addActionListener(e -> checkLogInData(loginField, passwordField));
        loginButton.registerKeyboardAction(e -> checkLogInData(loginField, passwordField), KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

        //jestes ameba jebana jutro masz dodac enter do logowania ok dz pozdrawiam


        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(frameX - 300, 380, 200, 40);
        exitButton.setFont(new Font("Arial", Font.PLAIN, 25));
        exitButton.addActionListener(e -> frame.dispose());

        frame.add(appLogo);
        frame.add(loginLabel);
        frame.add(loginField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(loginButton);
        frame.add(exitButton);
        frame.setBounds((screenX - frameX)/2, (screenY - frameY)/2, frameX,frameY);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void checkLogInData(JTextField log, JPasswordField pass){
        if (!accountManager.logIn(log.getText(), new String(pass.getPassword())))
            JOptionPane.showMessageDialog(frame, "Wrong Login or Password");
        else {
            new MainWindow(accountManager);
            frame.dispose();
        }
    }
}
