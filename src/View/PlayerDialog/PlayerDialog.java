package View.PlayerDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PlayerDialog extends JDialog {

    public PlayerDialog(ActionListener listener) {
        super();

        this.initDefaultGUI(listener);
    }

    private void initDefaultGUI(ActionListener listener) {
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new GridBagLayout());
        this.add(wrapper);

        JTextField textField = new JTextField();
        wrapper.add(textField);

        JButton CancelButton = new JButton("Cancel");
        CancelButton.addActionListener(event -> this.dispose());
        wrapper.add(CancelButton);

        JButton OkButton = new JButton("OK");
        OkButton.addActionListener(listener);
        wrapper.add(OkButton);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    public String getName() {
        // TODO
        return "HejHopp";
    }
}
