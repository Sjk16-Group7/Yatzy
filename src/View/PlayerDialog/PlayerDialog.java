package View.PlayerDialog;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PlayerDialog extends JDialog {

    public static void main(String[] args) {
        new PlayerDialog(System.out::println);
    }

    private static final int MAX_TEXTFIELDS = 8;

    private JPanel textFieldsPanel;

    public PlayerDialog(ActionListener listener) {
        super();

        this.initDefaultGUI(listener);
    }

    private void initDefaultGUI(ActionListener listener) {
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BorderLayout());
        this.add(wrapper);

        JPanel playerPanelWrapper = new JPanel();
        playerPanelWrapper.setLayout(new FlowLayout(FlowLayout.CENTER));
        wrapper.add(playerPanelWrapper, BorderLayout.CENTER);

        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        playerPanelWrapper.add(playerPanel);

        JPanel instructionPanel = new JPanel();
        instructionPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        playerPanel.add(instructionPanel);

        JLabel instructionLabel = new JLabel();
        instructionLabel.setText("Enter player names:");
        instructionPanel.add(instructionLabel);

        this.textFieldsPanel = new JPanel();
        textFieldsPanel.setLayout(new BoxLayout(textFieldsPanel, BoxLayout.Y_AXIS));
        playerPanel.add(textFieldsPanel);

        JPanel addRemovePanel = new JPanel();
        addRemovePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        playerPanel.add(addRemovePanel);

        JButton addButton = new JButton("+");
        addButton.addActionListener(event -> this.addTextField(""));
        addRemovePanel.add(addButton);

        JButton removeButton = new JButton("-");
        removeButton.addActionListener(event -> this.removeTextField());
        addRemovePanel.add(removeButton);

        JPanel confirmationPanel = new JPanel();
        confirmationPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        wrapper.add(confirmationPanel, BorderLayout.SOUTH);

        JButton OkButton = new JButton("Play!");
        OkButton.addActionListener(listener);
        confirmationPanel.add(OkButton);

        this.addTextField("");

        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private void updateView() {
        Component[] components = this.textFieldsPanel.getComponents();

        for (int i = 0; i < components.length; i++) {
            Component component = components[i];

            if (i == components.length - 1) {
                continue;
            }

            if (component instanceof JTextField) {
                JTextField textField = (JTextField) component;

                if (textField.getText().isEmpty()) {
                    continue;
                }

                JPanel labelPanel = this.convertToLabelPanel(textField);

                this.textFieldsPanel.remove(i);
                this.textFieldsPanel.add(labelPanel, i);
            }
        }

        this.textFieldsPanel.updateUI();
        this.pack();
    }

    private JPanel convertToLabelPanel(JTextField textField) {
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JLabel label = new JLabel();
        label.setHorizontalTextPosition(JLabel.LEFT);
        label.setText(textField.getText());
        labelPanel.add(label);

        return labelPanel;
    }

    private void addTextField(String text) {
        Component[] components = this.textFieldsPanel.getComponents();

        if (components.length > MAX_TEXTFIELDS) {
            return;
        }

        if (components.length > 0) {
            Component lastItem = components[components.length - 1];

            if (lastItem instanceof JTextField) {
                if (((JTextField) lastItem).getText().isEmpty()) {
                    return;
                }
            }
        }

        JTextField textField = new JTextField();
        textField.setText(text);
        textField.setColumns(20);
//        textField.setPreferredSize(new Dimension(200, 25));
        this.textFieldsPanel.add(textField);

        this.updateView();
        textField.grabFocus();
    }

    private void removeTextField() {
        Component[] components = this.textFieldsPanel.getComponents();

        if (components.length == 1) {
            return;
        }

        this.textFieldsPanel.remove(components.length - 1);
        this.textFieldsPanel.remove(components.length - 2);

        Component secondLastComponent = components[components.length - 2];

        if (secondLastComponent instanceof JPanel) {
            secondLastComponent = ((JPanel) secondLastComponent).getComponent(0);

            if (secondLastComponent instanceof JLabel) {
                this.addTextField(((JLabel) secondLastComponent).getText());
            }
        }

        this.updateView();
    }

    public String getNames() {
        // TODO
        return "HejHopp";
    }
}
