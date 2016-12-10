package View;

import java.awt.*;

import javax.swing.*;

public class ViewHelper {
    public static JButton createSquareButton(String text, int size) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(size, size));
        button.setMargin(new Insets(0, 0, 0, 0));

        return button;
    }
}
