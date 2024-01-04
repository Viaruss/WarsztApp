package GUI;

import GUI.Resources.ButtonConstructor;
import GUI.Resources.Colors;
import GUI.Resources.Icons;

import javax.swing.*;
import java.awt.*;

public class MainWindow {
    String currentPage = "1";
    JFrame frame;
    AccountManager accountManager;
    Icons icons = new Icons();

    JPanel pageContent = new JPanel(new CardLayout());
    MainWindow(AccountManager acc){
        accountManager = acc;
        Colors colorPalette = new Colors();
        frame = new JFrame("WarsztApp");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setMinimumSize(new Dimension(550, 650));

        //TOP CONTENT OF THE APP WINDOW

        //App menu bar
        JMenuBar menuBar = new JMenuBar();
            menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
            JMenuItem file = new JMenuItem("File");
            JMenuItem view = new JMenuItem("View");
            JMenuItem settings = new JMenuItem("Settings");

            menuBar.add(file);
            menuBar.add(view);
            menuBar.add(settings);
            menuBar.setVisible(true);

        //  window content selector panel
        //"Home", "Contact", "Bookings", "Part Info", "Job Archive"
        JPanel topMenuPanel = new JPanel(new GridLayout(1,0, 0, 10));
            topMenuPanel.setForeground(colorPalette.dark3);

        JButton homeButton = new JButton("Home", icons.homeIcon);
            homeButton.addActionListener(e -> changePage("1"));
            ButtonConstructor.topButton(homeButton);
            topMenuPanel.add(homeButton);

        JButton contactButton = new JButton("Contact", icons.phoneIcon);
            contactButton.addActionListener(e -> changePage("2"));
            ButtonConstructor.topButton(contactButton);
            topMenuPanel.add(contactButton);

        JButton bookingsButton = new JButton("Bookings", icons.calendarIcon);
            bookingsButton.addActionListener(e -> changePage("3"));
            ButtonConstructor.topButton(bookingsButton);
            topMenuPanel.add(bookingsButton);

        JButton partInfoButton = new JButton("Part Info", icons.gearIcon);
            partInfoButton.addActionListener(e -> changePage("4"));
            ButtonConstructor.topButton(partInfoButton);
            topMenuPanel.add(partInfoButton);

        JButton jobArchiveButton = new JButton("Data Archives", icons.archiveIcon);
            jobArchiveButton.addActionListener(e -> changePage("5"));
            ButtonConstructor.topButton(jobArchiveButton);
            topMenuPanel.add(jobArchiveButton);

        JPanel topContent = new JPanel(new BorderLayout());
            topContent.add(menuBar, BorderLayout.NORTH);
            topContent.add(topMenuPanel, BorderLayout.SOUTH);
            topContent.setBackground(colorPalette.dark2);

        //BOTTOM CONTENT OF THE APP WINDOW
        JPanel bottomContent = new JPanel(new BorderLayout());
        JLabel accountInfo = new JLabel("Logged as: " + accountManager.getActiveAccount().getLogin());
        accountInfo.setForeground(Color.white);
        accountInfo.setAlignmentX(Component.RIGHT_ALIGNMENT);
        accountInfo.setFocusable(false);
        accountInfo.setVisible(true);

        JLabel bottomInfo = new JLabel("PLACEHOLDER: Database info");
        bottomInfo.setForeground(Color.white);

        bottomContent.add(accountInfo, BorderLayout.WEST);
        bottomContent.add(bottomInfo, BorderLayout.EAST);
        bottomContent.setBackground(colorPalette.dark1);
        bottomContent.setForeground(Color.white);

        //CENTER CONTENT OF THE APP WINDOW
        pageContent.setBackground(colorPalette.dark2);

        JPanel page1 = new JPanel(new GridLayout(1, 3));
            page1.add(new JLabel("LEFT SIDE TEST") );
            page1.add(new JLabel("CENTER TEST"));
            page1.add(new JLabel("RIGHT SIDE TEST"));
        pageContent.add(page1, "1");

        JPanel page2 = new JPanel();
            JLabel label2 = new JLabel("asd2");
            page2.add(label2);
        pageContent.add(page2, "2");

        JPanel page3 = new JPanel();
            JLabel label3 = new JLabel("asd3");
            page3.add(label3);
        pageContent.add(page3, "3");

        JPanel page4 = new JPanel();
            JLabel label4 = new JLabel("asd4");
            page4.add(label4);
        pageContent.add(page4, "4");

        JPanel page5 = new JPanel();
            JLabel label5 = new JLabel("asd5");
            page5.add(label5);
        pageContent.add(page5, "5");


        //PUTTING IT ALL TOGETHER
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(topContent, BorderLayout.NORTH);
        frame.getContentPane().add(pageContent, BorderLayout.CENTER);
        frame.getContentPane().add(bottomContent, BorderLayout.SOUTH);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void changePage(String page){
        currentPage = page;
        CardLayout cardLayout = (CardLayout) pageContent.getLayout();
        cardLayout.show(pageContent, page);
        JOptionPane.showMessageDialog(frame, "**WORK IN PROGRESS**\nOPENED PAGE " + page);

    }
}
