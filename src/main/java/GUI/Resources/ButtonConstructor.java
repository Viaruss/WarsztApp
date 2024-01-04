package GUI.Resources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonConstructor {
    public static void topButton(JButton button){
        Colors colorPalette = new Colors();
        button.setBackground(colorPalette.dark3);
        button.setForeground(Color.white);
        button.setFocusable(false);
        button.setToolTipText("go to " + button.getText() + " page");
        button.setIconTextGap(0);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(colorPalette.dark3.brighter());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(colorPalette.dark3);
            }
        });
    }
}
