package View.RulesDialog;

import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import javax.swing.*;

/**
 * RulesDialog is a dialof showing information about the game yahtzee and its' rules
 * @author Marianna
 */
public class RulesDialog extends JDialog {
    private static final String instructions = "The game consists of a number of rounds "
            + " In each round, a player gets three \n rolls of the dice"
            + "although they can choose to end their turn after one or two rolls \n"
            + "after the first roll the player can save any dice they want and re-roll the other dice.\n\n"
            + " This procedure is repeated after the second roll. "
            + " The player has complete \n choice as to which dice to roll."
            + " They can re-roll a die for the third roll that \nwas not rolled on the second roll.\n"
            + " The Yahtzee scorecard contains 13 different category boxes divided between \n two sections:\nThe upper section and the lower section and in each round,"
            + "after the third roll, the player must choose one of these categories."
            + " The score entered in the box depends on how well the five dice match the \n scoring rule for the category."
            + "As an example, one of the categories is called \n Three of a Kind.\n"
            + "The scoring rule for this category means that a player only scores if at least \n three of the five dice are the same value.\n\n"
            + " The game is completed after 13 rounds by each player,"
            + "with each of the 13 boxes filled. The \ntotal score is calculated by summing all 13 boxes,"
            + "together with any bonuses.\n\n" + " UPPER SECTION: \n"
            + "In the upper section there are six boxes. The score in "
            + "each of these boxes is \n determined by adding the total number of dice matching that box."
            + "If a \n player scores a total of 63 or more points in these six boxes,"
            + "a bonus of 35 \n is added to the upper section score. Although 63 points"
            + "corresponds to scoring \n exactly three-of-a-kind for each of the six boxes, a common way to get the bonus is by scoring four-of-a-kind "
            + "for some numbers so that fewer of other numbers \n are needed. A player can earn the bonus even if they score a (0) in an upper section box."
            + "In order to gauge how well a player is doing in the upper section, "
            + " they often refer to being (up) or (down) compared to the average of three required for each box."
            + "So that if a player scores four (sixes) they will be (6 up); "
            + "while if they then score just two (twos) they will then be only (4 up). Similarly, if a player starts with two (twos) they will be ()2 down \n\n"
            + " LOWER SECTION\n"
            + "The lower section contains a number of poker-themed categories with specific point values:\n"
            + "Some players count a Yahtzee as being a valid Full House. However the official rule is "
            + "that a Full \nHouse is (three of one number and two of another)."
            + "If a category is chosen but the dice do not match the requirements of the category the player scores 0 in that category.\n"
            + "Some combinations offer the player a choice as to which category to score them under; e.g., a full house"
            + "could be scored in the Full House, the Three-Of-A-Kind, or the Chance categories. \nThe Chance"
            + "category is often used for a turn that will not score well in any other category.\n\n"
            + " YAHTZEE BONUSES AND JOKER RULES\n"
            + "A Yahtzee occurs when all five dice are the same. If a player throws a Yahtzee but the Yahtzee category has already been used, special rules apply.\n"
            + "If the player throws a Yahtzee and has already filled the Yahtzee box with a score of 50,\n"
            + "they score a Yahtzee bonus and get an extra 100 points. However, if they throw a Yahtzee \n"
            + "and have filled the Yahtzee category with a score of 0, they do not get a Yahtzee bonus.\n"
            + "In either case they then select a category, as usual. Scoring is the same as normal except that,\n"
            + " if the Upper Section box corresponding to the Yahtzee has been used, the Full House, \n"
            + "Small Straight and Large Straight categories can be used to score 25, 30 or 40 (respectively) \n"
            + "even though the dice do not meet the normal requirement for those categories.\n"
            + "In this case the Yahtzee is said to act as a (Joker)\n\n"
            + "You can also click on one of the links bellow to watch a step-by-step explanation for the game: \n"
            + "  https://www.youtube.com/watch?v=AHDgpuEzopc&t=2s \n"
            + "  https://www.youtube.com/watch?v=6kxYPfRoHlY&t=115s ";

    public RulesDialog(JFrame owner) {
        super(owner, "Rules", true);

        this.initDefaultGUI();
    }

    protected void initDefaultGUI() {
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.add(wrapper);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        textPanel.setBorder(new TitledBorder("Rules"));
        wrapper.add(textPanel);

        JTextArea rulesText = new JTextArea(instructions, 30, 40);
        rulesText.setWrapStyleWord(true);
        rulesText.setLineWrap(true);
        rulesText.setEditable(false);

        JScrollPane scroll = new JScrollPane(rulesText);
        textPanel.add(scroll);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        wrapper.add(buttonPanel);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(event -> this.dispose());
        buttonPanel.add(closeButton, BorderLayout.EAST);

        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}