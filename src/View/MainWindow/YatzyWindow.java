package View.MainWindow;

import Model.Dice.Dice;
import Model.Dice.DiceCollection;
import Model.Dice.DiceCombination;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class YatzyWindow extends JFrame {
    protected EventListenerList listenerList = new EventListenerList();

    private ArrayList<DiceButton> diceButtons = new ArrayList<DiceButton>();
    private HashMap<DiceCombination, JButton> combinationButtons = new HashMap<DiceCombination, JButton>();
    private String[] names;
    private JButton rollButton;

    public YatzyWindow(DiceCollection dice) {
        super("Yatzy");

        this.initDefaultGUI(dice);
    }

    public void setRollButtonEnabled(boolean enabled) {
        this.rollButton.setEnabled(enabled);
    }

    public void setCombinationEnabled(DiceCombination combination, boolean enabled) {
        this.combinationButtons.get(combination).setEnabled(enabled);
    }

    public boolean[] getDiceButtonStatus() {
        // TODO
        return new boolean[] {};
    }

    public void updateDiceButtons() {
        for (DiceButton button : this.diceButtons) {
            button.update();
        }
    }

    private void initDefaultGUI(DiceCollection dice) {
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.X_AXIS));
        this.add(wrapper);

        JPanel scoreBoardWrapper = new JPanel();
        scoreBoardWrapper.setLayout(new BoxLayout(scoreBoardWrapper, BoxLayout.X_AXIS));
        wrapper.add(scoreBoardWrapper);

        JPanel combinationPanel = new JPanel();
        combinationPanel.setLayout(new BoxLayout(combinationPanel, BoxLayout.Y_AXIS));
        scoreBoardWrapper.add(combinationPanel);

        for (DiceCombination combination : DiceCombination.values()) {
            CombinationButton combinationButton = new CombinationButton(combination);
            combinationButton.addActionListener(this::fireActionPerformed);
            combinationPanel.add(combinationButton);
        }

        combinationPanel.add(new JSeparator(JSeparator.VERTICAL));

        JTable scoreBoardTable = new JTable(10, 5);
        //scoreBoardTable.setModel(model);
        JTableHeader header = scoreBoardTable.getTableHeader();
        header.setPreferredSize(new Dimension(100, 26));
        scoreBoardTable.setRowHeight(26);
        scoreBoardTable.setLayout(new BorderLayout());
        scoreBoardWrapper.add(new JScrollPane(scoreBoardTable));

        JPanel diceWrapper = new JPanel();
        diceWrapper.setLayout(new BoxLayout(diceWrapper, BoxLayout.Y_AXIS));
        wrapper.add(diceWrapper);

        JPanel dicePanel = new JPanel();
        dicePanel.setLayout(new BoxLayout(dicePanel, BoxLayout.Y_AXIS));
        diceWrapper.add(dicePanel);

        //JPanel rollPanel = new JPanel();
        //rollPanel.setLayout(new FlowLayout());
        //diceWrapper.add(rollPanel);

        for (Dice die : dice) {
            DiceButton diceButton = new DiceButton(die);
            diceButton.addActionListener(this::fireActionPerformed);
            dicePanel.add(diceButton);
            this.diceButtons.add(diceButton);
        }

        dicePanel.add(new JSeparator(JSeparator.VERTICAL));

        JButton rollButton = new JButton("Roll");
        rollButton.addActionListener(this::fireActionPerformed);
        this.rollButton = rollButton;
        //rollPanel.add(rollButton);

        //diceWrapper.add(new JSeparator(JSeparator.VERTICAL));
        //scoreBoardWrapper.add(new JSeparator(JSeparator.VERTICAL));

        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private void fireActionPerformed(ActionEvent event) {
        Object[] listeners = listenerList.getListenerList();

        for (Object listener : listeners) {
            if (listener instanceof ActionListener) {
                ((ActionListener) listener).actionPerformed(event);
            }
        }
    }

    public void addActionListener(ActionListener listener) {
        this.listenerList.add(ActionListener.class, listener);
    }

    public void removeActionListener(ActionListener listener) {
        this.listenerList.remove(ActionListener.class, listener);
    }
}
