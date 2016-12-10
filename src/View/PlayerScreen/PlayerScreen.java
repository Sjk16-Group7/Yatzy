package View.PlayerScreen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.EventListenerList;

import static View.ViewHelper.createSquareButton;

public class PlayerScreen extends JPanel {
    private static final int MAX_PLAYERS = 8;

    private EventListenerList listenerList = new EventListenerList();
    private ArrayList<String> playerNames = new ArrayList<String>();
    private JPanel textFieldPanel = new JPanel();
    private JButton OkButton = new JButton("Play!");
    private JPanel addPanel = this.createPlayerEditPanel(true, "");

    public PlayerScreen() {
        this.initDefaultGUI();
    }

    public ArrayList<String> getPlayerNames() {
        return this.playerNames;
    }

    public void addActionListener(ActionListener listener) {
        this.listenerList.add(ActionListener.class, listener);
    }

    public void removeActionListener(ActionListener listener) {
        this.listenerList.remove(ActionListener.class, listener);
    }

    public void reset() {
        for (String name : this.playerNames) {
            this.textFieldPanel.remove(0);
        }

        this.playerNames.clear();
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

        this.OkButton.setActionCommand("Ok");
        this.OkButton.addActionListener(this::fireActionPerformed);
        confirmationPanel.add(this.OkButton);
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
            errorMessage = "Player name cannot be empty!";
            error = true;
        } else if (this.playerNames.size() >= MAX_PLAYERS) {
            errorMessage = "Maximum amount of players reached! (" + MAX_PLAYERS + ")";
            error = true;
        } else if (this.playerNames.contains(name)) {
            errorMessage = "'" + name + "' is already listed as a player!";
            error = true;
        }

        if (error) {
            JOptionPane.showMessageDialog(
                this.getParent(),
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

        this.updateUI();
    }

    private void removePlayerPanel(JPanel panel) {
        this.textFieldPanel.remove(panel);

        this.focusAddPanel();

        this.updateUI();
    }

    private JPanel createPlayerEditPanel(boolean enabled, String text) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(5, 0));

        JTextField textField = new JTextField(20);
        textField.setText(text);
        textField.setEditable(enabled);
        panel.add(textField, BorderLayout.CENTER);

        JButton button = createSquareButton(enabled ? "+" : "-", 20);
        panel.add(button, BorderLayout.EAST);

        ActionListener listener;

        if (enabled) {
            listener = event -> this.addPlayer(textField.getText());
        } else {
            listener = event -> this.removePlayer(textField.getText(), panel);
        }

        textField.addActionListener(event -> button.doClick());
        button.setActionCommand(enabled ? "Add" : "Remove");
        button.addActionListener(this::fireActionPerformed);
        button.addActionListener(listener);

        return panel;
    }

    private void fireActionPerformed(ActionEvent event) {
        Object[] listeners = listenerList.getListenerList();

        for (Object listener : listeners) {
            if (listener instanceof ActionListener) {
                ((ActionListener) listener).actionPerformed(event);
            }
        }
    }
}
