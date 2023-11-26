package org.example;


import java.util.ArrayList;
import java.util.List;

public class Menu {
    InOutManager inOut = new InOutManager();
    public void menuLoop(SQLRequests req) {
        ArrayList<String> menuOptions = new ArrayList<>(List.of(
                "Show data",
                "Search data",
                "Add data",
                "Modify data",
                "Delete data",
                "Choose different table",
                "Exit"));

        int currentTable = inOut.choiceOperator(req.getTableNames(), "Select table to work on");

        actionLoop: while (true) {
            switch (inOut.choiceOperator((menuOptions), "Choose what You want to do:")) {
                case 0:
                    req.select(currentTable, true);
                    break;
                case 1:
                    req.select(currentTable, false);
                    break;
                case 2:
                    req.insert(currentTable);
                    break;
                case 3:req.update(currentTable);
                    break;
                case 4:
                    req.delete(currentTable);
                    break;
                case 5:
                    currentTable = inOut.choiceOperator(req.getTableNames(), "Select table to work on");
                    break;
                case 6:
                    break actionLoop;
            }
        }
    }
}