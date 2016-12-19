package View.WinScreen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.InputStream;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Model.Dice.DiceCombination;
import Model.Player.YatzyPlayer;
import View.GameScreen.PlayerPanel;
import View.YatzyScreen;

/**
 * WinScreen is a view panel showing the final scores and the winner of a game.
 * Possible ActionCommands listeners may recieve from this class include:
 *     Change:      When the panel has changed
 * @author Isak
 */
public class WinScreen extends YatzyScreen {
    private static final ImageIcon trumpetImage = new ImageIcon(WinScreen.class.getResource("/Trumpets.png"));
    private static final InputStream winSound = WinScreen.class.getResourceAsStream("/TADA.wav");
    private static final InputStream tieSound = WinScreen.class.getResourceAsStream("/CHORD.wav");

    private JPanel playersPanel;
    private JLabel trumpetLabel;

    /**
     * Sets what players scoreboards to show
     * @param players the players
     */
    public void setPlayers(ArrayList<YatzyPlayer> players) {
        for (YatzyPlayer player : players) {
            PlayerPanel panel = new PlayerPanel(player);
            this.playersPanel.add(panel);
        }

        this.fireActionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Change"));
    }

    /**
     * Sets what player to display as the winner
     * @param winner the winning player
     */
    public void setWinner(YatzyPlayer winner) {
        String text = "<html><center>";
        InputStream audio;

        if (winner == null) {
            text += "<b>What a bummer!</b><br>" +
                    "It's a tie!";
            audio = tieSound;
        } else {
            text += "<b>And the winner is...</b><br>" +
                    winner.getName() + "!";
            audio = winSound;
        }

        text += "</center></html>";

        this.trumpetLabel.setText(text);

        // TODO the following throws a EOFException the second time it's run
        // try {
        //     Clip clip = AudioSystem.getClip();
        //     AudioInputStream inputStream = AudioSystem.getAudioInputStream(audio);
        //     clip.open(inputStream);
        //     clip.start();
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

        this.fireActionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Change"));
    }

    /**
     * Initiates the GUI components of this panel
     */
    @Override
    protected void initDefaultGUI() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));

        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        this.add(wrapper);

        JPanel centeringPanel = new JPanel();
        centeringPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        wrapper.add(centeringPanel);

        this.trumpetLabel = new JLabel();
        this.trumpetLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
        this.trumpetLabel.setIcon(trumpetImage);
        this.trumpetLabel.setIconTextGap(-30);
        this.trumpetLabel.setHorizontalTextPosition(JLabel.CENTER);
        this.trumpetLabel.setVerticalTextPosition(JLabel.TOP);
        centeringPanel.add(this.trumpetLabel);

        JPanel playerPanelWrapper = new JPanel();
        playerPanelWrapper.setLayout(new BorderLayout());
        wrapper.add(playerPanelWrapper);

        JPanel combinationPanelWrapper = new JPanel();
        combinationPanelWrapper.setLayout(new BorderLayout());
        playerPanelWrapper.add(combinationPanelWrapper, BorderLayout.WEST);

        JPanel combinationLabelPanel = new JPanel();
        combinationLabelPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 4));
        combinationPanelWrapper.add(combinationLabelPanel, BorderLayout.SOUTH);

        JPanel combinationPanel = new JPanel();
        combinationPanel.setLayout(new GridLayout(0, 1, 0, 2));
        combinationLabelPanel.add(combinationPanel);

        int height = 25;

        for (DiceCombination combination : DiceCombination.values()) {
            JLabel label = new JLabel(combination.getName());
            Dimension size = label.getPreferredSize();
            size.height = height;
            label.setPreferredSize(size);
            combinationPanel.add(label);

            if (combination == DiceCombination.SIXES) {
                // add bonus and upper total after sixes
                JLabel bonusLabel = new JLabel("Bonus");
                bonusLabel.setPreferredSize(size);
                combinationPanel.add(bonusLabel);

                JLabel upperTotalLabel = new JLabel("Total");
                upperTotalLabel.setPreferredSize(size);
                combinationPanel.add(upperTotalLabel);
            }
        }

        // add total last
        JLabel totalLabel = new JLabel("Total");
        Dimension size = totalLabel.getPreferredSize();
        size.height = height;
        totalLabel.setPreferredSize(size);
        combinationPanel.add(totalLabel);

        this.playersPanel = new JPanel();
        this.playersPanel.setBorder(new EmptyBorder(4 , 4, 4, 4));
        this.playersPanel.setLayout(new GridLayout(1, 0));
        playerPanelWrapper.add(this.playersPanel, BorderLayout.CENTER);
    }

    /**
     * Resets the GUI components of this panel
     */
    @Override
    public void reset() {
        super.reset();

        this.playersPanel.removeAll();
        this.trumpetLabel.setText("");

        this.fireActionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Change"));
    }
}
