package View;

import java.awt.*;

import javax.swing.*;

/**
 * ViewHelper is a helper class for view related tasks
 * @author Isak
 */
public abstract class ViewHelper {

    /**
     * Creates a new JButton that is perfectly square
     * @param text the text of the button
     * @param size the size of the button
     * @return a new JButton
     */
    public static JButton createSquareButton(String text, int size) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(size, size));
        button.setMargin(new Insets(0, 0, 0, 0));

        return button;
    }
}
