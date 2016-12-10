package View.PlayerDialog;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class PlayerDialog extends JDialog {

    public static void main(String[] args) {
        PlayerDialog dialog = new PlayerDialog();

        ActionListener listener = e -> {
            ArrayList<String> names = dialog.getPlayerNames();

            if (names.isEmpty()) {
                JOptionPane.showMessageDialog(
                    dialog,
                    "You must have at least 1 player!",
                    "Oops",
                    JOptionPane.INFORMATION_MESSAGE
                );

                return;
            }

            for (String name : names) {
                System.out.println(name);
            }

            dialog.dispose();
        };

        dialog.addActionListener(listener);
    }

    private static final int MAX_PLAYERS = 8;

    private ArrayList<String> playerNames = new ArrayList<String>();
    private JPanel textFieldPanel = new JPanel();
    private JButton OkButton = new JButton("Play!");
    private JPanel addPanel = this.createPlayerEditPanel(true, "");

    public PlayerDialog() {
        super();

        this.initDefaultGUI();
    }

    public ArrayList<String> getPlayerNames() {
        return this.playerNames;
    }

    private void addActionListener(ActionListener listener) {
        this.OkButton.addActionListener(listener);
    }

    private void removeActionListener(ActionListener listener) {
        this.OkButton.removeActionListener(listener);
    }

    private void initDefaultGUI() {
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BorderLayout());
        this.add(wrapper);

        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        wrapper.add(playerPanel, BorderLayout.CENTER);

        this.textFieldPanel.setLayout(new GridLayout(0, 1, 0, 5));
        this.textFieldPanel.setBorder(
            new CompoundBorder(
                new TitledBorder("Enter player names: "),
                new EmptyBorder(20, 20, 20, 20)
            )
        );
        playerPanel.add(this.textFieldPanel);

        this.textFieldPanel.add(this.addPanel);

        JPanel confirmationPanel = new JPanel();
        confirmationPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        wrapper.add(confirmationPanel, BorderLayout.SOUTH);
        confirmationPanel.add(this.OkButton);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private void clearAddPanel() {
        Component component = this.addPanel.getComponent(0);

        if (component instanceof JTextField) {
            ((JTextField) component).setText("");
        }
    }

    private void focusAddPanel() {
        Component component = this.addPanel.getComponent(0);

        if (component instanceof JTextField) {
            ((JTextField) component).grabFocus();
        }
    }

    private void addPlayer(String name) {
        boolean error = false;
        String errorMessage = "";

        if (name.isEmpty()) {
            errorMessage = "Player name cannot be empty";
            error = true;
        } else if (this.playerNames.size() >= MAX_PLAYERS) {
            errorMessage = "Maximum amount of players reached!";
            error = true;
        } else if (this.playerNames.contains(name)) {
            errorMessage = name + " is already listed as a player!";
            error = true;
        }

        if (error) {
            JOptionPane.showMessageDialog(
                this,
                errorMessage,
                "Oops",
                JOptionPane.INFORMATION_MESSAGE
            );

            this.focusAddPanel();

            return;
        }

        JPanel panel = this.createPlayerEditPanel(false, name);
        this.addPlayerPanel(panel);
        this.playerNames.add(name);
    }

    private void removePlayer(String name, JPanel associatedPanel) {
        this.playerNames.remove(name);
        this.removePlayerPanel(associatedPanel);
    }

    private void addPlayerPanel(JPanel panel) {
        this.textFieldPanel.add(panel, this.playerNames.size());

        this.clearAddPanel();
        this.focusAddPanel();

        this.pack();
    }

    private void removePlayerPanel(JPanel panel) {
        this.textFieldPanel.remove(panel);

        this.focusAddPanel();

        this.pack();
    }

    private JPanel createPlayerEditPanel(boolean enabled, String text) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(5, 0));

        JTextField textField = new JTextField(20);
        textField.setText(text);
        textField.setEditable(enabled);
        panel.add(textField, BorderLayout.CENTER);

        JButton button = this.createSquareButton(enabled ? "+" : "-", 20);
        panel.add(button, BorderLayout.EAST);

        ActionListener listener;

        if (enabled) {
            listener = e -> this.addPlayer(textField.getText());
        } else {
            listener = e -> this.removePlayer(textField.getText(), panel);
        }

        button.addActionListener(listener);
        textField.addActionListener(event -> button.doClick());

        return panel;
    }

    private JButton createSquareButton(String text, int size) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(size, size));
        button.setMargin(new Insets(0, 0, 0, 0));

        return button;
    }
}
