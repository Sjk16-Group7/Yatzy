package View.GameScreen;

import java.awt.*;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import Model.Dice.DiceCombination;
import Model.Player.YatzyPlayer;
import Model.ScoreBoard.ScoreBoardCell;
import Model.ScoreBoard.YatzyScoreBoard;
import View.ViewHelper;

/**
 * PlayerPanel is an extension of JPanel, customized to have a YatzyPlayer associated with it.
 * It has a bunch of added components added to it to display the players name and its'
 * personal scoreboard.
 * @author Isak
 */
public class PlayerPanel extends JPanel {
    private static final ImageIcon currentPlayerIcon = new ImageIcon(PlayerPanel.class.getResource("/PlayerIcon.png"));

    private YatzyPlayer player;
    private JLabel currentPlayerLabel;
    private JTextField bonusTextField;
    private JTextField upperTotalTextField;
    private JTextField totalTextField;
    private HashMap<DiceCombination, JTextField> textFields = new HashMap<>();

    /**
     * Class constructor specifying the YatzyPlayer to associate with it
     * @param player the YatzyPlayer
     */
    public PlayerPanel(YatzyPlayer player) {
        super();

        this.player = player;
        this.initAppearance();
        this.update();
    }

    /**
     * Gets the associated YatzyPlayer
     * @return the YatzyPlayer
     */
    public YatzyPlayer getPlayer() {
        return this.player;
    }

    /**
     * Updates the graphics of the panel. (this needs to be explicitly called to update, there
     * may be a better way to do this...)
     */
    public void update() {
        YatzyScoreBoard scoreBoard = this.player.getScoreBoard();

        for (DiceCombination combination : this.textFields.keySet()) {
            JTextField textField = this.textFields.get(combination);
            ScoreBoardCell cell = scoreBoard.getCell(combination);

            if (cell.isCrossed()) {
                textField.setText("X");
            } else if (cell.isEmpty()) {
                textField.setText("");
            } else {
                textField.setText(Integer.toString(cell.getValue()));
            }
        }

        // update totals and bonus
        int bonus = scoreBoard.getBonus();
        this.bonusTextField.setText(Integer.toString(bonus));

        int upperTotal = scoreBoard.getUpperSection().getSum();
        this.upperTotalTextField.setText(Integer.toString(upperTotal));

        int total = scoreBoard.getTotalScore();
        this.totalTextField.setText(Integer.toString(total));
    }

    /**
     * Gets all the textfields in a hashmap with their corresponding DiceCombination.
     * @return the textfields
     */
    public HashMap<DiceCombination, JTextField> getTextFields() {
        return this.textFields;
    }

    /**
     * Sets whether it's a players turn or not
     * @param active setting this to true will display an icon above the players name
     */
    public void setActive(boolean active) {
        if (active) {
            this.currentPlayerLabel.setIcon(currentPlayerIcon);
        } else {
            this.currentPlayerLabel.setIcon(null);
        }
    }

    /**
     * Initiates the appearance of this Component
     */
    public void initAppearance() {
        this.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        this.add(topPanel, BorderLayout.NORTH);

        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(imagePanel, BorderLayout.NORTH);

        JLabel imageLabel = new JLabel();
        imagePanel.add(imageLabel);
        this.currentPlayerLabel = imageLabel;
        this.currentPlayerLabel.setPreferredSize(new Dimension(
            currentPlayerIcon.getIconWidth(),
            currentPlayerIcon.getIconHeight())
        );

        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        namePanel.setBorder(new EtchedBorder());
        topPanel.add(namePanel, BorderLayout.CENTER);

        JLabel nameLabel = new JLabel();
        nameLabel.setText(this.player.getName());
        namePanel.add(nameLabel);

        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new GridLayout(0, 1, 0, 2));
        this.add(scorePanel, BorderLayout.CENTER);

        for (DiceCombination combination : DiceCombination.values()) {
            JTextField textField = ViewHelper.createCustomHeightTextField(25);
            textField.setEditable(false);
            textField.setHorizontalAlignment(JTextField.CENTER);
            this.textFields.put(combination, textField);
            scorePanel.add(textField);

            if (combination == DiceCombination.SIXES) {
                // add bonus and upper total after sixes
                this.bonusTextField = ViewHelper.createCustomHeightTextField(25);
                this.bonusTextField.setText("0");
                this.bonusTextField.setHorizontalAlignment(JTextField.CENTER);
                this.bonusTextField.setEditable(false);
                scorePanel.add(this.bonusTextField);

                this.upperTotalTextField = ViewHelper.createCustomHeightTextField(25);
                this.upperTotalTextField.setText("0");
                this.upperTotalTextField.setHorizontalAlignment(JTextField.CENTER);
                this.upperTotalTextField.setEditable(false);
                scorePanel.add(this.upperTotalTextField);
            }
        }

        // add total last
        this.totalTextField = ViewHelper.createCustomHeightTextField(25);
        this.totalTextField.setText("0");
        this.totalTextField.setHorizontalAlignment(JTextField.CENTER);
        this.totalTextField.setEditable(false);
        scorePanel.add(this.totalTextField);

        this.setActive(false);
    }
}
