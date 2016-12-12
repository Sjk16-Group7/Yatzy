package View.GameScreen;

import java.awt.*;

import javax.swing.*;

import Model.Dice.Dice;

public class DiceButton extends JButton {
    private Dice dice;

    private static ImageIcon[] DICE_IMAGES_NORMAL = new ImageIcon[] {
        new ImageIcon("src/Images/Dice1active.png"),
        new ImageIcon("src/Images/Dice2active.png"),
        new ImageIcon("src/Images/Dice3active.png"),
        new ImageIcon("src/Images/Dice4active.png"),
        new ImageIcon("src/Images/Dice5active.png"),
        new ImageIcon("src/Images/Dice6active.png")
    };

    private static ImageIcon[] DICE_IMAGES_HOLD = new ImageIcon[]{
        new ImageIcon("src/Images/Dice1inactive.png"),
        new ImageIcon("src/Images/Dice2inactive.png"),
        new ImageIcon("src/Images/Dice3inactive.png"),
        new ImageIcon("src/Images/Dice4inactive.png"),
        new ImageIcon("src/Images/Dice5inactive.png"),
        new ImageIcon("src/Images/Dice6inactive.png")
    };

    public DiceButton(Dice dice) {
        super();

        this.dice = dice;

        this.initAppearance();
    }

    public Dice getDice() {
        return this.dice;
    }

    public void update() {
        ImageIcon image_normal = DICE_IMAGES_NORMAL[dice.getValue() - 1];
        ImageIcon image_hold   = DICE_IMAGES_HOLD[dice.getValue() - 1];

        this.setIcon(image_normal);
        this.setDisabledIcon(image_hold);
        this.setSelectedIcon(image_hold);
        this.setDisabledSelectedIcon(image_hold);
//        this.setRolloverIcon(image_hold);
//        this.setRolloverSelectedIcon(image_hold);
    }

    private void initAppearance() {
        this.addActionListener(event -> this.setSelected(!this.isSelected()));
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.setBorder(null);
        this.setContentAreaFilled(false);
        this.setMargin(null);
        this.setPreferredSize(new Dimension(80, 80));

        this.reset();
    }

    public void reset() {
        this.setEnabled(false);
        this.setSelected(false);

        this.setIcon(null);
        this.setDisabledIcon(null);
        this.setSelectedIcon(null);
        this.setDisabledSelectedIcon(null);
    }
}