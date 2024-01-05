package GUI;

import GUI.Resources.ButtonConstructor;
import GUI.Resources.Colors;
import GUI.Resources.Icons;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainWindow {
    String currentPage = "1";
    JFrame frame;
    AccountManager accountManager;
    Icons icons = new Icons();

    JPanel pageContent = new JPanel(new CardLayout());

    JLabel bottomInfo;
    MainWindow(AccountManager acc){
        accountManager = acc;
        Colors colorPalette = new Colors();
        frame = new JFrame("WarsztApp");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setMinimumSize(new Dimension(550, 650));
        String lastAction = "Logged in Successfully";

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

        bottomInfo = new JLabel(lastAction);
        bottomInfo.setForeground(Color.white);

        bottomContent.add(accountInfo, BorderLayout.WEST);
        bottomContent.add(bottomInfo, BorderLayout.EAST);
        bottomContent.setBackground(colorPalette.dark1);
        bottomContent.setForeground(Color.white);

        //CENTER CONTENT OF THE APP WINDOW
        JPanel page1 = new JPanel(new GridLayout(1, 3));
            JPanel leftSide1 = new JPanel(new GridLayout(2,1));
                JPanel leftSide1Top = new JPanel(new FlowLayout());
                    JLabel left1 = new JLabel("LEFT SIDE TEST 1");
                    leftSide1Top.add(left1);
                JPanel leftSide2Top = new JPanel(new FlowLayout());
                    JLabel left2 = new JLabel("LEFT SIDE TEST 2");
                    leftSide2Top.add(left2);
                leftSide1.add(leftSide1Top);
                leftSide1.add(leftSide2Top);
            page1.add(leftSide1);

            JPanel centerSide1 = new JPanel();
                JLabel center1 = new JLabel("CENTER TEST");
                centerSide1.add(center1);
            page1.add(centerSide1);

            JPanel rightSide1 = new JPanel();
                JLabel right1 = new JLabel("RIGHT SIDE TEST");
                rightSide1.add(right1);
            page1.add(rightSide1);

        pageContent.add(page1, "1");



        JPanel page2 = new JPanel();
            page2.add(new JLabel("PLACEHOLDER 2"));
            page2.add(new JLabel("**WORK IN PROGRESS**"));
        pageContent.add(page2, "2");



        JPanel page3 = new JPanel();
            page3.add(new JLabel("PLACEHOLDER 3"));
            page3.add(new JLabel("**WORK IN PROGRESS**"));
        pageContent.add(page3, "3");



        JPanel page4 = new JPanel();
        page4.add(new JLabel("PLACEHOLDER 4"));
        page4.add(new JLabel("**WORK IN PROGRESS**"));
        pageContent.add(page4, "4");



        JPanel page5 = new JPanel(new BorderLayout());
            JPanel tablePanel = new JPanel(new BorderLayout());
                JLabel tableTitle = new JLabel("PRACOWNICY");
                    tableTitle.setHorizontalAlignment(SwingConstants.CENTER);
                    tablePanel.add(tableTitle, BorderLayout.NORTH);
                tablePanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
                DefaultTableModel tableModel = new DefaultTableModel(
                        new Object[][]{
                                {"John", "Doe", 28},
                                {"Jane", "Smith", 35},
                                {"Bob", "Johnson", 42}
                        },
                        new Object[]{"First Name", "Last Name", "Age"}
                );

                // Create a JTable with the TableModel
                JTable table = new JTable(tableModel);

                // Add the JTable to a JScrollPane for scrolling
                JScrollPane tableScroll = new JScrollPane(table);
                tablePanel.add(tableScroll, BorderLayout.CENTER);

            JPanel buttonsPanel = new JPanel();
                buttonsPanel.setPreferredSize(new Dimension(200,200));
                buttonsPanel.setLayout(new GridLayout(9, 0, 0, 5));
                buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));

                JButton tableAdd = new JButton("Add element", icons.gearIcon);
                tableAdd.addActionListener(e -> System.out.println(1));
                ButtonConstructor.tableButton(tableAdd);

                JButton tableDelete = new JButton("Delete element", icons.gearIcon);
                tableDelete.addActionListener(e -> System.out.println(2));
                ButtonConstructor.tableButton(tableDelete);

                JButton tableModify = new JButton("Modify element", icons.gearIcon);
                tableModify.addActionListener(e -> System.out.println(3));
                ButtonConstructor.tableButton(tableModify);

                JButton tableSearch = new JButton("Search element", icons.gearIcon);
                tableSearch.addActionListener(e -> System.out.println(4));
                ButtonConstructor.tableButton(tableSearch);

                JButton tableRefresh = new JButton("Refresh", icons.gearIcon);
                tableRefresh.addActionListener(e -> System.out.println(5));
                ButtonConstructor.tableButton(tableRefresh);

                JButton tableChange = new JButton("Change table", icons.gearIcon);
                tableChange.addActionListener(e -> System.out.println(6));
                ButtonConstructor.tableButton(tableChange);

                buttonsPanel.add(new JLabel());
                buttonsPanel.add(tableAdd);
                buttonsPanel.add(tableDelete);
                buttonsPanel.add(tableModify);
                buttonsPanel.add(tableSearch);
                buttonsPanel.add(new JLabel());
                buttonsPanel.add(tableRefresh);
                buttonsPanel.add(tableChange);
                buttonsPanel.add(new JLabel());

            page5.add(tablePanel, BorderLayout.CENTER);
            page5.add(buttonsPanel, BorderLayout.EAST);
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
        bottomInfo.setText("OPENED PAGE " + page);
    }
}
