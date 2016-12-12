package View.GameScreen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import Model.Dice.Dice;
import Model.Dice.DiceCollection;
import Model.Dice.DiceCombination;
import Model.Player.YatzyPlayer;
import View.YatzyScreen;

/**
 * GameScreen is a view panel showing the game board, including players, scores and dice.
 * Possible ActionCommands listeners may recieve include:
 *     Roll:        When the roll button is pressed
 *     Dice:        When a dice button is pressed
 *     Combination: When a combination button is pressed
 *     Change:      When the panel has changed
 * @author Isak
 */
public class GameScreen extends YatzyScreen {
    private ArrayList<DiceButton> diceButtons;
    private HashMap<DiceCombination, CombinationButton> combinationButtons;
    private JButton rollButton;
    private JPanel dicePanel;
    private JPanel combinationPanel;
    private JPanel tablePanel;

    /**
     * Gets the roll button
     * @return the roll button
     */
    public JButton getRollButton() {
        return this.rollButton;
    }

    /**
     * Gets the combination buttons in a HashMap with their corresponding DiceCombination
     * as keys.
     * @return the combination buttons
     */
    public HashMap<DiceCombination, CombinationButton> getCombinationButtons() {
        return this.combinationButtons;
    }

    /**
     * Gets the dice buttons
     * @return the dice buttons
     */
    public ArrayList<DiceButton> getDiceButtons() {
        return this.diceButtons;
    }

    /**
     * Sets what dice to use to create the DiceButtons. Clears the view and recreates all DiceButtons.
     * @param dice the dice to use as model for the DiceButtons of the view
     */
    public void setDice(DiceCollection dice) {
        this.dicePanel.removeAll();
        this.diceButtons.clear();

        for (Dice d : dice) {
            DiceButton diceButton = new DiceButton(d);
            diceButton.addActionListener(this::fireActionPerformed);
            diceButton.setActionCommand("Dice");
            this.dicePanel.add(diceButton);
            this.diceButtons.add(diceButton);
        }

        this.fireActionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Change"));
    }

    /**
     * Sets what players to use to create the playerPanels. Clears the view and recreates all playerPanels.
     * @param players the players to use as a model for the playerPanels of the view
     */
    public void setPlayers(ArrayList<YatzyPlayer> players) {
        // TODO
    }

    /**
     * Sets what combinations to use to create the CombinationButtons. Clears the view and recreates
     * all CombinationButtons.
     * @param combinations the combinations to use as a model for the CombinationButtons of the view
     */
    public void setCombinations(DiceCombination[] combinations) {
        this.combinationPanel.removeAll();
        this.combinationButtons.clear();

        for (DiceCombination combination : DiceCombination.values()) {
            CombinationButton combinationButton = new CombinationButton(combination);
            combinationButton.addActionListener(this::fireActionPerformed);
            combinationButton.setActionCommand("Combination");
            this.combinationPanel.add(combinationButton);
            this.combinationButtons.put(combination, combinationButton);
        }

        this.fireActionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Change"));
    }

    /**
     * Resets the GUI components of this panel
     */
    @Override
    public void reset() {
        super.reset();

        this.removeAll();
        this.initDefaultGUI();
        this.combinationButtons.clear();
        this.diceButtons.clear();
        // this.table... TODO

        this.fireActionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Change"));
    }

    /**
     * Initiates the GUI components of this panel
     */
    @Override
    protected void initDefaultGUI() {
        this.diceButtons = new ArrayList<DiceButton>();
        this.combinationButtons = new HashMap<DiceCombination, CombinationButton>();

        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BorderLayout());
        this.add(wrapper, BorderLayout.CENTER);

        JPanel combinationPanelWrapper = new JPanel();
        combinationPanelWrapper.setLayout(new BorderLayout());
        wrapper.add(combinationPanelWrapper, BorderLayout.WEST);

        JPanel spacingPanel = new JPanel();
        spacingPanel.setLayout(new FlowLayout());
        spacingPanel.setPreferredSize(new Dimension(0, 66));

        JPanel combinationButtonPanel = new JPanel();
        combinationButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 6, 0));
        combinationPanelWrapper.add(combinationButtonPanel, BorderLayout.CENTER);

        this.combinationPanel = new JPanel();
        this.combinationPanel.setLayout(new GridLayout(0, 1, 0, 2));
        combinationButtonPanel.add(this.combinationPanel);

        JPanel tablePanelWrapper = new JPanel();
        tablePanelWrapper.setLayout(new BorderLayout());
        wrapper.add(tablePanelWrapper, BorderLayout.CENTER);

        this.tablePanel = new JPanel();
        this.tablePanel.setLayout(new GridLayout(1, 0, 2, 0));
        tablePanelWrapper.add(this.tablePanel, BorderLayout.CENTER);

        JPanel dicePanelWrapper = new JPanel();
        dicePanelWrapper.setLayout(new BorderLayout());
        wrapper.add(dicePanelWrapper, BorderLayout.EAST);

        JPanel diceTopPanel = new JPanel();
        diceTopPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        dicePanelWrapper.add(diceTopPanel, BorderLayout.NORTH);

        this.dicePanel = new JPanel();
        this.dicePanel.setLayout(new GridLayout(0, 1, 0, 5));
        diceTopPanel.add(this.dicePanel);

        JPanel diceBottomPanel = new JPanel();
        diceBottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        dicePanelWrapper.add(diceBottomPanel, BorderLayout.SOUTH);

        this.rollButton = new JButton("Roll dice");
        this.rollButton.setActionCommand("Roll");
        this.rollButton.addActionListener(this::fireActionPerformed);
        diceBottomPanel.add(this.rollButton);
        this.setCombinations(DiceCombination.values());
    }

    /**
     * Creates the left panel of the view, containing the combinationButtons
     * @return a new JPanel
     */
    private JPanel createCombinationPanel() {
        // TODO
        return null;
    }

    /**
     * Creates the center panel of the view, containing the playerPanels
     * @return a new JPanel
     */
    private JPanel createTablePanel() {
        // TODO
        return null;
    }

    /**
     * Creates the right panel of the view, containing the diceButtons
     * @return a new JPanel
     */
    private JPanel createDicePanel(DiceCollection dice) {
        // TODO
        return null;
    }
}
