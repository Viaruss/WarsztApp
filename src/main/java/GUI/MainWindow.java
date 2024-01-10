package GUI;

import GUI.Resources.ButtonConstructor;
import GUI.Resources.Colors;
import GUI.Resources.Icons;
import org.backend.SQLRequests;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

public class MainWindow {
    String currentPage = "1";
    JFrame frame;
    AccountManager accountManager;
    Icons icons = new Icons();

    JPanel pageContent = new JPanel(new CardLayout());

    JLabel bottomInfo = new JLabel();
    JButton[] topButtons;
    JTable table;
    SQLRequests req;
    MainWindow(AccountManager acc){
        accountManager = acc;
        req = acc.getReq();
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

        JButton raportButton = new JButton("Report Generator", icons.fileIcon);
            raportButton.addActionListener(e -> {changePage("p4"); raportButton.setBackground(colorPalette.dark3.brighter());});
            ButtonConstructor.topButton(raportButton);
            topMenuPanel.add(raportButton, "tb4");

        JButton jobArchiveButton = new JButton("Data Archives", icons.archiveIcon);
            jobArchiveButton.addActionListener(e -> {changePage("p5"); jobArchiveButton.setBackground(colorPalette.dark3.brighter());});
            ButtonConstructor.topButton(jobArchiveButton);
            topMenuPanel.add(jobArchiveButton, "tb5");

        topButtons = new JButton[] {homeButton, contactButton, bookingsButton, raportButton, jobArchiveButton};

        JPanel topContent = new JPanel(new BorderLayout());
            //topContent.add(menuBar, BorderLayout.NORTH);
            topContent.add(topMenuPanel, BorderLayout.SOUTH);
            topContent.setBackground(colorPalette.dark2);

        //BOTTOM CONTENT OF THE APP WINDOW
        JPanel bottomContent = new JPanel(new BorderLayout());
            JLabel accountInfo = new JLabel("Logged as: " + accountManager.getActiveAccount().getLogin());
                accountInfo.setForeground(Color.white);
                accountInfo.setAlignmentX(Component.RIGHT_ALIGNMENT);
                accountInfo.setFocusable(false);
                accountInfo.setVisible(true);

        bottomInfo.setText(lastAction);
        bottomInfo.setForeground(Color.white);

        bottomContent.add(accountInfo, BorderLayout.WEST);
        bottomContent.add(bottomInfo, BorderLayout.EAST);
        bottomContent.setBackground(colorPalette.dark1);
        bottomContent.setForeground(Color.white);

        //CENTER CONTENT OF THE APP WINDOW
        JPanel page1 = new JPanel(new GridLayout(1, 3));
            JPanel leftSide1 = new JPanel(new GridLayout(2,1));
                JPanel leftSide1Top = new JPanel(new FlowLayout());
                    leftSide1Top.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
                    JLabel left1 = new JLabel("LEFT SIDE TEST 1");
                    leftSide1Top.add(left1);
                JPanel leftSide2Top = new JPanel(new FlowLayout());
                    leftSide2Top.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                    JLabel left2 = new JLabel("LEFT SIDE TEST 2");
                    leftSide2Top.add(left2);
                leftSide1.add(leftSide1Top);
                leftSide1.add(leftSide2Top);
            page1.add(leftSide1);

            JPanel centerSide1 = new JPanel();
                centerSide1.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                JLabel center1 = new JLabel("CENTER TEST");
                centerSide1.add(center1);
            page1.add(centerSide1);

            JPanel rightSide1 = new JPanel();
                rightSide1.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
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
                DefaultTableModel tableModel = req.getTableModel("Dane_mechanikow");
                table = makeTable(tableModel);
                JScrollPane tableScroll = new JScrollPane(table);
                tablePanel.add(tableScroll, BorderLayout.CENTER);

            JPanel buttonsPanel = new JPanel();
                buttonsPanel.setPreferredSize(new Dimension(200,200));
                buttonsPanel.setLayout(new GridLayout(9, 0, 0, 5));

                JButton tableAdd = new JButton("Add element", icons.addIcon);
                tableAdd.addActionListener(e -> {
                    showAddRowDialog(frame, table, tableTitle.getText());
                    refreshTable(tablePanel, tableTitle);
                });

                ButtonConstructor.tableButton(tableAdd);

                JButton tableDelete = new JButton("Delete element", icons.deleteIcon);
                tableDelete.addActionListener(e -> {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                       showDeleteConfirmationDialog(frame, table, selectedRow, tableTitle.getText().replace(" ", "_").toLowerCase());
                        refreshTable(tablePanel, tableTitle);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please select a row to delete.");
                    }
                });
                ButtonConstructor.tableButton(tableDelete);

                JButton tableModify = new JButton("Modify element", icons.editIcon);
                tableModify.addActionListener(e -> {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        showUpdateDialog(frame, table, selectedRow, tableTitle.getText());
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please select a row to update.");
                    }
                });
                ButtonConstructor.tableButton(tableModify);

                JButton tableSearch = new JButton("Search element", icons.searchIcon);
                    tableSearch.addActionListener(e -> showSearchDialog(frame, table));
                ButtonConstructor.tableButton(tableSearch);

                JButton tableRefresh = new JButton("Refresh", icons.refreshIcon);
                tableRefresh.addActionListener(e -> {
                    refreshTable(tablePanel, tableTitle);
                    bottomInfo.setText("Data synchronised with Database");
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

        //Adding key shortcuts
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyCode());
                switch (e.getKeyCode()){
                    case 49:
                        changePage("p1");
                        homeButton.setBackground(colorPalette.dark3.brighter());
                        break;
                    case 50:
                        changePage("p2");
                        contactButton.setBackground(colorPalette.dark3.brighter());
                        break;
                    case 51:
                        changePage("p3");
                        bookingsButton.setBackground(colorPalette.dark3.brighter());
                        break;
                    case 52:
                        changePage("p4");
                        raportButton.setBackground(colorPalette.dark3.brighter());
                        break;
                    case 53:
                        changePage("p5");
                        jobArchiveButton.setBackground(colorPalette.dark3.brighter());
                        break;
                }
            }
        });
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
    private void showUpdateDialog(JFrame parentFrame, JTable table, int selectedRow, String tableTitle) {
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
            dialogTextFields.get(0).setEditable(false);
        JButton updateButton = new JButton("Update", new ImageIcon(new Icons().editIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
            updateButton.addActionListener(e -> {
                Vector<String> dialogTexts = new Vector<>();
                for(JTextField textField : dialogTextFields) dialogTexts.add(textField.getText());
                if(req.update(dialogTexts, tableTitle.replace(" ", "_").toLowerCase())){
                    bottomInfo.setText("DB: OK - Data modified Successfully");
                } else {
                    bottomInfo.setText("DB: ERROR - Nothing Changed");
                }
                dialog.dispose();
            });
        JButton cancelButton = new JButton("Cancel",new ImageIcon(new Icons().cancelIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.add(updateButton);
        dialog.add(cancelButton);

        dialog.setSize(550, 300);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }
    private void showSearchDialog(JFrame parentFrame, JTable table) {
        // Create a dialog for searching the table
        JDialog dialog = new JDialog(parentFrame, "Search Table", true);
            dialog.setLayout(new GridLayout(2, 2));

        JLabel searchLabel = new JLabel("Enter Search Criteria:");
        JTextField searchField = new JTextField();

        JButton searchButton = new JButton("Search", new ImageIcon(new Icons().searchIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
            searchButton.addActionListener(e -> {
                String searchCriteria = searchField.getText().toLowerCase();
                filterTable(table, searchCriteria);
                bottomInfo.setText("Search Completed, matching records: " + table.getRowCount());
                dialog.dispose();
            });
        JButton cancelButton = new JButton("Cancel", new ImageIcon(new Icons().cancelIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
            cancelButton.addActionListener(e -> dialog.dispose());
        dialog.add(searchLabel);
        dialog.add(searchField);
        dialog.add(searchButton);
        dialog.add(cancelButton);

        dialog.setSize(300, 100);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }

    private void refreshTable(JPanel tablePanel, JLabel tableTitle){
        tablePanel.remove(1);
        table = makeTable(req.getTableModel(tableTitle.getText().replace(" ", "_").toUpperCase()));
        tablePanel.add(new JScrollPane(table));
        frame.revalidate();
        frame.repaint();
    }
    private static void filterTable(JTable table, String searchCriteria) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        RowFilter<Object, Object> rowFilter = new RowFilter<>() {
            @Override
            public boolean include(RowFilter.Entry<?, ?> entry) {
                for (int i = entry.getValueCount() - 1; i >= 0; i--) {
                    Object value = entry.getValue(i);
                    String strValue = (value == null) ? "" : value.toString().toLowerCase();
                    if (strValue.contains(searchCriteria)) {
                        return true;
                    }
                }
                return false;
            }
        };

        sorter.setRowFilter(rowFilter);
    }
    private void showDeleteConfirmationDialog(JFrame parentFrame, JTable table, int selectedRow, String tableName) {
        int result = JOptionPane.showConfirmDialog(
                parentFrame,
                "Are you sure you want to delete the selected row?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            if(req.delete(Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString()), tableName)){
                bottomInfo.setText("DB: OK - Data deleted successfully");
            } else {
                bottomInfo.setText("DB: ERROR - Nothing Changed");
            }
        }
    }
    private void showAddRowDialog(JFrame parentFrame, JTable table, String tableTitle) {
        JDialog dialog = new JDialog(parentFrame, "Add New Row", true);
            dialog.setLayout(new GridLayout(table.getColumnCount()+1, 2));
            Vector<JTextField> dialogTextFields = new Vector<>();
            for(int i = 1; i < table.getColumnCount(); i++){
                dialog.add(new JLabel(table.getColumnName(i)));
                dialogTextFields.add(new JTextField());
                dialog.add(dialogTextFields.get(i-1));
            }

            JButton addButton = new JButton("Add",new ImageIcon(new Icons().addIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
                addButton.addActionListener(e -> {
                    Vector<String> dialogTexts = new Vector<>();
                    for(JTextField textField : dialogTextFields) dialogTexts.add(textField.getText());
                    if(req.insert(dialogTexts, tableTitle.replace(" ", "_").toLowerCase())){
                        bottomInfo.setText("DB: OK - Data added Successfully");
                    } else {
                        bottomInfo.setText("DB: ERROR - Nothing Changed");
                    }
                    dialog.dispose();
                });

            JButton cancelButton = new JButton("Cancel",new ImageIcon(new Icons().cancelIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
                cancelButton.addActionListener(e -> dialog.dispose());

        dialog.add(addButton);
        dialog.add(cancelButton);

        dialog.setSize(550, 300);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }
}
