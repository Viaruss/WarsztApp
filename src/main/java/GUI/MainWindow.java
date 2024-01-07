package GUI;

import GUI.Resources.ButtonConstructor;
import GUI.Resources.Colors;
import GUI.Resources.Icons;
import org.backend.SQLRequests;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class MainWindow {
    String currentPage = "1";
    JFrame frame;
    AccountManager accountManager;
    Icons icons = new Icons();

    JPanel pageContent = new JPanel(new CardLayout());

    JLabel bottomInfo;
    JButton[] topButtons;
    JTable table;
    MainWindow(AccountManager acc){
        accountManager = acc;
        SQLRequests req = acc.getReq();
        Colors colorPalette = new Colors();
        frame = new JFrame("WarsztApp");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setMinimumSize(new Dimension(500, 750));
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
            homeButton.addActionListener(e -> {changePage("p1"); homeButton.setBackground(colorPalette.dark3.brighter());});
            ButtonConstructor.topButton(homeButton);
            topMenuPanel.add(homeButton, "tb1");

        JButton contactButton = new JButton("Contact", icons.phoneIcon);
            contactButton.addActionListener(e -> {changePage("p2"); contactButton.setBackground(colorPalette.dark3.brighter());});
            ButtonConstructor.topButton(contactButton);
            topMenuPanel.add(contactButton, "tb2");

        JButton bookingsButton = new JButton("Bookings", icons.calendarIcon);
            bookingsButton.addActionListener(e -> {changePage("p3"); bookingsButton.setBackground(colorPalette.dark3.brighter());});
            ButtonConstructor.topButton(bookingsButton);
            topMenuPanel.add(bookingsButton, "tb3");

        JButton partInfoButton = new JButton("Part Info", icons.gearIcon);
            partInfoButton.addActionListener(e -> {changePage("p4"); partInfoButton.setBackground(colorPalette.dark3.brighter());});
            ButtonConstructor.topButton(partInfoButton);
            topMenuPanel.add(partInfoButton, "tb4");

        JButton jobArchiveButton = new JButton("Data Archives", icons.archiveIcon);
            jobArchiveButton.addActionListener(e -> {changePage("p5"); jobArchiveButton.setBackground(colorPalette.dark3.brighter());});
            ButtonConstructor.topButton(jobArchiveButton);
            topMenuPanel.add(jobArchiveButton, "tb5");

        topButtons = new JButton[] {homeButton, contactButton, bookingsButton, partInfoButton, jobArchiveButton};

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

        pageContent.add(page1, "p1");



        JPanel page2 = new JPanel();
            page2.add(new JLabel("PLACEHOLDER 2"));
            page2.add(new JLabel("**WORK IN PROGRESS**"));
        pageContent.add(page2, "p2");



        JPanel page3 = new JPanel();
            page3.add(new JLabel("PLACEHOLDER 3"));
            page3.add(new JLabel("**WORK IN PROGRESS**"));
        pageContent.add(page3, "p3");



        JPanel page4 = new JPanel();
            page4.add(new JLabel("PLACEHOLDER 4"));
            page4.add(new JLabel("**WORK IN PROGRESS**"));
        pageContent.add(page4, "p4");

        JPanel page5 = new JPanel(new BorderLayout());
            JPanel tablePanel = new JPanel(new BorderLayout());
                JLabel tableTitle = new JLabel("DANE MECHANIKOW");
                    tableTitle.setHorizontalAlignment(SwingConstants.CENTER);
                    tablePanel.add(tableTitle, BorderLayout.NORTH);
                tablePanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
                DefaultTableModel tableModel = req.getTableModel("Dane_mechanikow");
                table = makeTable(tableModel);
                JScrollPane tableScroll = new JScrollPane(table);
                tablePanel.add(tableScroll, BorderLayout.CENTER);

            JPanel buttonsPanel = new JPanel();
                buttonsPanel.setPreferredSize(new Dimension(200,200));
                buttonsPanel.setLayout(new GridLayout(9, 0, 0, 5));
                buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));

                JButton tableAdd = new JButton("Add element", icons.addIcon);
                tableAdd.addActionListener(e -> System.out.println(1));
                ButtonConstructor.tableButton(tableAdd);

                JButton tableDelete = new JButton("Delete element", icons.deleteIcon);
                tableDelete.addActionListener(e -> System.out.println(2));
                ButtonConstructor.tableButton(tableDelete);

                JButton tableModify = new JButton("Modify element", icons.editIcon);
                tableModify.addActionListener(e -> {
                    int selectedRow = table.getSelectedRow();

                    if (selectedRow != -1) {
                        showUpdateDialog(frame, table, selectedRow);
                        bottomInfo.setText("DB: OK - Updated data at row " + table.getSelectedRow()+1);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please select a row to update.");
                    }
                });
                ButtonConstructor.tableButton(tableModify);

                JButton tableSearch = new JButton("Search element", icons.searchIcon);
                tableSearch.addActionListener(e -> System.out.println(4));
                ButtonConstructor.tableButton(tableSearch);

                JButton tableRefresh = new JButton("Refresh", icons.refreshIcon);
                tableRefresh.addActionListener(e -> {
                    tablePanel.remove(1);
                    table = makeTable(req.getTableModel(tableTitle.getText().replace(" ", "_").toUpperCase()));
                    tablePanel.add(new JScrollPane(table));
                    bottomInfo.setText("Data synchronised with Database");
                    frame.revalidate();
                    frame.repaint();
                });
                ButtonConstructor.tableButton(tableRefresh);

                JButton tableChange = new JButton("Change table", icons.changeTableIcon);
                tableChange.addActionListener(e -> {
                    JPanel inputDialog = new JPanel();
                        inputDialog.setLayout(new BoxLayout(inputDialog, BoxLayout.Y_AXIS));
                        inputDialog.add(new JLabel("Chose Table to show:"));
                        JComboBox<String> dropDownTableNames = new JComboBox<>();
                            for (String tableName : req.getTableNames()){
                                dropDownTableNames.addItem(tableName);
                            }
                        inputDialog.add(dropDownTableNames);
                    int result = JOptionPane.showOptionDialog(
                            frame,
                            inputDialog,
                            "Input Dialog",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            null,
                            null);

                    if (result == JOptionPane.OK_OPTION) {
                        String selectedTable = (String) dropDownTableNames.getSelectedItem();
                        tablePanel.remove(1);
                        table = makeTable(req.getTableModel(selectedTable));
                        tablePanel.add(new JScrollPane(table));
                        assert selectedTable != null;
                        tableTitle.setText(selectedTable.replace("_", " ").toUpperCase());
                        bottomInfo.setText("Data table changed");
                        frame.revalidate();
                        frame.repaint();
                    }
                });
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
        pageContent.add(page5, "p5");


        //PUTTING IT ALL TOGETHER
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(topContent, BorderLayout.NORTH);
        frame.getContentPane().add(pageContent, BorderLayout.CENTER);
        frame.getContentPane().add(bottomContent, BorderLayout.SOUTH);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void changePage(String page){
        for (JButton button : topButtons){
            button.setBackground(new Colors().dark3);
        }

        currentPage = page;
        CardLayout cardLayout = (CardLayout) pageContent.getLayout();
        cardLayout.show(pageContent, page);
        bottomInfo.setText("OPENED PAGE " + page);
    }

    public JTable makeTable(DefaultTableModel tableModel){
        JTable table = new JTable(tableModel);
        table.setAutoCreateRowSorter(true);
        table.getTableHeader().setReorderingAllowed(false);
        table.setFillsViewportHeight(true);
        return table;
    }
    private static void showUpdateDialog(JFrame parentFrame, JTable table, int selectedRow) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        Vector rowData = tableModel.getDataVector().elementAt(selectedRow);

        JDialog dialog = new JDialog(parentFrame, "Update Element", true);
        dialog.setLayout(new GridLayout(rowData.size()+1, 2));
        Vector<JTextField> dialogTextFields = new Vector<>();
        for(int i = 0; i < table.getColumnCount(); i++){
            dialog.add(new JLabel(table.getColumnName(i)));
            dialogTextFields.add(new JTextField(rowData.get(i).toString()));
            dialog.add(dialogTextFields.get(i));
        }
        JButton updateButton = new JButton("Update", new Icons().editIcon);
        updateButton.addActionListener(e -> {
            for(int i = 0; i < table.getColumnCount(); i++){
                rowData.set(i, dialogTextFields.get(i).getText());
            }
            tableModel.fireTableRowsUpdated(selectedRow, selectedRow);
            dialog.dispose();
        });

        dialog.add(updateButton);

        dialog.setSize(550, 300);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }
}
