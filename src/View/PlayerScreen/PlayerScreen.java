package View.PlayerScreen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import View.YatzyScreen;

import static View.ViewHelper.createSquareButton;

/**
 * PlayerScreen is a view panel showing the start screen and player selection.
 * Possible ActionCommands listeners may recieve include:
 *     Ok:        When the OK button is pressed
 *     Add:       When a name is added
 *     Remove:    When a name is removed
 *     Change:    When the panel has changed
 * @author Isak
 */
public class PlayerScreen extends YatzyScreen {
    private static final int MAX_PLAYERS = 8;

    private ArrayList<String> playerNames = new ArrayList<String>();
    private JButton OkButton;
    private JPanel addPanel;
    private JPanel textFieldPanel;

    /**
     * Gets the currently added player names
     * @return the names
     */
    public ArrayList<String> getPlayerNames() {
        return this.playerNames;
    }

    /**
     * Resets the GUI components of this panel
     */
    @Override
    public void reset() {
        super.reset();

        for (String name : this.playerNames) {
            // remove all panels
            this.textFieldPanel.remove(0);
        }

        // remove all names
        this.playerNames.clear();

        this.fireActionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Change"));
    }

    /**
     * Initiates the GUI components of this panel
     */
    @Override
    protected void initDefaultGUI() {
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BorderLayout());
        this.add(wrapper, BorderLayout.CENTER);

        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        wrapper.add(playerPanel, BorderLayout.CENTER);

        this.textFieldPanel = new JPanel();
        this.textFieldPanel.setLayout(new GridLayout(0, 1, 0, 5));
        this.textFieldPanel.setBorder(
            new CompoundBorder(
                new TitledBorder("Enter player names: "),
                new EmptyBorder(20, 20, 20, 20)
            )
        );
        playerPanel.add(this.textFieldPanel);

        this.addPanel = this.createPlayerEditPanel(true, "");
        this.textFieldPanel.add(this.addPanel);

        JPanel confirmationPanel = new JPanel();
        confirmationPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        wrapper.add(confirmationPanel, BorderLayout.SOUTH);

        this.OkButton = new JButton("Play!");
        this.OkButton.setActionCommand("Ok");
        this.OkButton.addActionListener(this::fireActionPerformed);
        confirmationPanel.add(this.OkButton);
    }

    /**
     * Clears the add textfield
     */
    private void clearAddPanel() {
        Component component = this.addPanel.getComponent(0);

        if (component instanceof JTextField) {
            ((JTextField) component).setText("");
        }
    }

    /**
     * Sets focus to the add textfield
     */
    private void focusAddPanel() {
        Component component = this.addPanel.getComponent(0);

        if (component instanceof JTextField) {
            ((JTextField) component).grabFocus();
        }
    }

    /**
     * Adds a name and panel including a disabled textfield with a players name and a delete
     * button associated with it, but only if validation succeeds.
     * @param name the name
     */
    private void addPlayer(String name) {
        boolean error = false;
        String errorMessage = "";

        if (name.trim().isEmpty()) {
            errorMessage = "A players' name cannot be nothing!";
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

    /**
     * Removes a name and the panel associated with it
     * @param name the name
     */
    private void removePlayer(String name) {
        this.playerNames.remove(name);

        for (Component textFieldPanel : this.textFieldPanel.getComponents()) {
            if (textFieldPanel instanceof JPanel) {
                Component textField = ((JPanel) textFieldPanel).getComponent(0);
                if (textField instanceof JTextField) {
                    String text = ((JTextField) textField).getText();

                    if (!text.equals(name)) {
                        continue;
                    }

                    this.removePlayerPanel((JPanel) textFieldPanel);
                    return;
                }
            }
        }
    }

    /**
     * Adds a player panel to the view
     * @param panel the panel
     */
    private void addPlayerPanel(JPanel panel) {
        this.textFieldPanel.add(panel, this.playerNames.size());

        this.clearAddPanel();
        this.focusAddPanel();

        this.updateUI();
    }

    /**
     * Removes a player panel from the view
     * @param panel the panel
     */
    private void removePlayerPanel(JPanel panel) {
        this.textFieldPanel.remove(panel);

        this.focusAddPanel();

        this.updateUI();
    }

    /**
     * Creates a "player panel" consisting of a textfield with a name, and a button either removing
     * the corresponding player, or adding a new player based on what's in the textfield. This is
     * determined by if the panel is enabled or not.
     * @param enabled a boolean indicating whether panel should be enabled or disabled
     * @param playerName the name of the player
     * @return the panel
     */
    private JPanel createPlayerEditPanel(boolean enabled, String playerName) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(5, 0));

        JTextField textField = new JTextField(20);
        textField.setText(playerName);
        textField.setEditable(enabled);
        panel.add(textField, BorderLayout.CENTER);

        JButton button = createSquareButton(enabled ? "+" : "-", 20);
        panel.add(button, BorderLayout.EAST);

        ActionListener listener;

        if (enabled) {
            listener = event -> this.addPlayer(textField.getText());
        } else {
            listener = event -> this.removePlayer(textField.getText());
        }

        textField.addActionListener(event -> button.doClick());
        button.setActionCommand(enabled ? "Add" : "Remove");
        button.addActionListener(this::fireActionPerformed);
        button.addActionListener(listener);

        return panel;
    }
}
