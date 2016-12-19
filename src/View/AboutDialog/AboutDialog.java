package View.AboutDialog;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 * AboutDialog is a dialog showing information about the application and its'
 * authors.
 * @author Marianna
 */
public class AboutDialog extends JDialog {
    private static final String aboutText = "<html>Version 1.0.0<br>Licensed under MIT</html>";
    private static final String authorsText = "<html>© 2016<br>Isak Wertwein<br>Gustav Malm<br>Marianna Khachadourian</html>";

    public AboutDialog(JFrame owner) {
        super(owner, "About", true);

        this.initDefaultGUI();
    }

    protected void initDefaultGUI() {
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.add(wrapper);

        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        upperPanel.setBorder(new TitledBorder("About"));
        wrapper.add(upperPanel);

        JLabel upperText = new JLabel();
        upperText.setText(aboutText);
        upperPanel.add(upperText);

        JPanel lowerPanel = new JPanel();
        upperPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        lowerPanel.setBorder(new TitledBorder("Authors"));
        wrapper.add(lowerPanel);

        JLabel lowerText = new JLabel();
        lowerText.setText(authorsText);
        lowerPanel.add(lowerText);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        wrapper.add(buttonPanel);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(event -> this.dispose());
        buttonPanel.add(closeButton, BorderLayout.EAST);

        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}