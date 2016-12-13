package Controller;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.*;

import Model.Dice.DiceCombination;
import Model.Dice.YatzyDiceCollection;
import Model.Player.YatzyPlayer;
import Model.ScoreBoard.ScoreBoardCell;
import Model.ScoreBoard.YatzyScoreBoard;
import View.GameScreen.CombinationButton;
import View.GameScreen.DiceButton;
import View.GameScreen.PlayerPanel;
import View.YatzyWindow;

/**
 * Yatzy is a controller class for a game of Yahtzee. It combines models and views to create a
 * fully functioning game.
 * @author Isak
 * @author Marianna
 * @author Gustav
 */
public class Yatzy {
    /**
     * Listener for actions in the game view
     */
    private ActionListener gameScreenListener = event -> {
        switch (event.getActionCommand().toLowerCase()) {
            case "roll":
                this.onRoll();
                break;
            case "combination":
                this.onCombinationPick((CombinationButton) event.getSource());
                break;
            case "mode":
                this.onModeChange();
                break;
            case "dice":
            case "change":
                System.out.println("2");
                this.view.update();
                break;
        }
    };

    /**
     * Listener for actions in the player view
     */
    private ActionListener playerScreenListener = event -> {
        switch (event.getActionCommand().toLowerCase()) {
            case "ok":
                this.onPlayerPick();
                break;
            case "add":
            case "remove":
            case "change":
                this.view.update();
                break;
        }
    };

    /**
     * Listener for actions in the game over view
     */
    private ActionListener winScreenListener = event -> {
        switch (event.getActionCommand().toLowerCase()) {
            case "change":
                this.view.update();
                break;
        }
    };

    /**
     * Listener for actions in the window
     */
    private ActionListener windowListener = event -> {
        switch (event.getActionCommand().toLowerCase()) {
            case "newgame":
                this.onNewGame();
                break;
            case "exit":
                this.onExit();
                break;
            case "about":
                // TODO
                break;
            case "help":
                // TODO
                break;
            case "change":
                this.view.update();
                break;
        }
    };

    private YatzyWindow view;
    private ArrayList<YatzyPlayer> players;
    private YatzyPlayer currentPlayer;
    private YatzyDiceCollection dice;

    /**
     * Class default constructor
     */
    public Yatzy() {
        this.view = new YatzyWindow();
        this.players = new ArrayList<YatzyPlayer>();
        this.dice = new YatzyDiceCollection();

        this.onStart();
    }

    /**
     * Called when a new game starts
     */
    private void onStart() {
        this.view.reset();
        this.view.gameScreen.setDice(this.dice);

        this.view.addActionListener(this.windowListener);
        this.view.gameScreen.addActionListener(this.gameScreenListener);
        this.view.playerScreen.addActionListener(this.playerScreenListener);
        this.view.winScreen.addActionListener(this.winScreenListener);

        // this.view.winScreen.reset(); // needed?
        this.view.setScreen(this.view.playerScreen);
    }

    /**
     * Called when user starts a new game
     */
    private void onNewGame() {
        int response = JOptionPane.showConfirmDialog(
            this.view,
            "Are you sure?",
            "New game confirmation",
            JOptionPane.YES_NO_OPTION
        );

        if (response == JOptionPane.OK_OPTION) {
            this.onStart();
        }
    }

    /**
     * Called when a game ends
     */
    private void onEnd() {
        // update views
        this.view.gameScreen.reset();
        this.view.setScreen(this.view.winScreen);

        // TODO provide winScreen with necessary data
        // this.view.winScreen.setPlayers()
        // this.view.winScreen.setWinner()
    }

    /**
     * Called when program is exiting
     */
    private void onExit() {
        int response = JOptionPane.showConfirmDialog(
            this.view,
            "Are you sure?",
            "Exit confirmation",
            JOptionPane.YES_NO_OPTION
        );

        if (response == JOptionPane.OK_OPTION) {
            this.view.dispose();
        }
    }

    /**
     * Called when the user changes action mode
     */
    private void onModeChange() {
        if (this.currentPlayer.hasAllRollsLeft()) {
            // user hasn't rolled yet, don't do anything
            return;
        }

        this.updateCombinationButtons();
    }

    /**
     * Called when a combinationButton has been clicked in the game view. Crosses or gives score
     * to the associated scoreboard field of the pick for the current player. Also updates the
     * game view, and starts a new turn.
     * @param pick the clicked combinationButton
     */
    private void onCombinationPick(CombinationButton pick) {
        DiceCombination combination = pick.getCombination();
        YatzyScoreBoard scoreBoard = this.currentPlayer.getScoreBoard();
        PlayerPanel playerPanel = this.view.gameScreen.getPlayerPanels().get(this.currentPlayer);
        PlayerAction action = this.view.gameScreen.getSelectedAction();

        switch (action) {
            case CROSS:
                scoreBoard.getCell(combination).setCrossed(true);
                break;
            case SCORE:
                int score = this.dice.getScore(combination);
                scoreBoard.getCell(combination).setValue(score);
                break;
        }

        // update totals and bonus
        int bonus = scoreBoard.getBonus();
        playerPanel.getBonusTextField().setText(Integer.toString(bonus));

        int upperTotal = scoreBoard.getUpperSection().getSum();
        playerPanel.getUpperTotalTextField().setText(Integer.toString(upperTotal));

        int total = scoreBoard.getTotalScore();
        playerPanel.getTotalTextField().setText(Integer.toString(total));

        // update display of player scoreboard
        playerPanel.update();

        this.onNewTurn();
    }

    /**
     * Called when a rollButton has been clicked. Rolls non-locked dice and updates the game view.
     */
    private void onRoll() {
        // decrement current players' rolls left
        this.currentPlayer.roll();

        for (DiceButton diceButton : this.view.gameScreen.getDiceButtons()) {
            // disable buttons if player has no rolls left
            diceButton.setEnabled(this.currentPlayer.hasRollsLeft());

            if (!diceButton.isSelected()) {
                // roll non-selected dice
                diceButton.getDice().roll();
            }

            // has to update the button to show the new image
            diceButton.update();
        }

        if (!this.currentPlayer.hasRollsLeft()) {
            // disable roll button if user has no rolls left
            this.view.gameScreen.getRollButton().setEnabled(false);
        }

        this.updateCombinationButtons();
    }

    /**
     * Called when the OK button is clicked in the player view. Adds inputted players and
     * start the game if validation succeeds.
     */
    private void onPlayerPick() {
        ArrayList<String> names = this.view.playerScreen.getPlayerNames();

        if (names.isEmpty()) {
            // no names inputted
            JOptionPane.showMessageDialog(
                this.view,
                "You must have at least 1 player!",
                "Oops",
                JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }

        this.players.clear();

        for (String name : names) {
            this.players.add(new YatzyPlayer(name));
        }

        // update views
        this.view.playerScreen.reset();
        this.view.setScreen(this.view.gameScreen);
        this.view.gameScreen.setPlayers(this.players);

        this.onNewTurn();
    }

    /**
     * Called when it's time for a new turn. Checks whether the game is over and acts accordingly.
     * Sets a new current player and resets the game view if game isn't over.
     */
    private void onNewTurn() {
        if (this.isGameOver()) {
            YatzyPlayer winner = this.getWinner();
            this.onEnd(); // show game over view
            return;
        }

        // set new current player
        int newPlayerIndex;

        if (this.currentPlayer == null) {
            // first turn, no current player, so get a random one
            newPlayerIndex = new Random().nextInt(this.players.size());
        } else {
            // get the next player in the list, or the first on if current player is last in list
            int currentPlayerIndex = this.players.indexOf(this.currentPlayer);
            newPlayerIndex = (currentPlayerIndex + 1) % this.players.size();
        }

        this.currentPlayer = this.players.get(newPlayerIndex);
        this.currentPlayer.resetRollsLeft();

        this.resetGameView();

        // update current player
        HashMap<YatzyPlayer, PlayerPanel> playerPanels = this.view.gameScreen.getPlayerPanels();

        for (PlayerPanel panel : playerPanels.values()) {
            panel.setActive(false);
        }

        playerPanels.get(this.currentPlayer).setActive(true);
    }

    /**
     * Resets the game view
     */
    private void resetGameView() {
        this.view.gameScreen.getRollButton().setEnabled(true);
        this.view.gameScreen.setSelectedAction(PlayerAction.SCORE);

        for (DiceButton diceButton : this.view.gameScreen.getDiceButtons()) {
            diceButton.reset();
        }

        for (CombinationButton combinationButton : this.view.gameScreen.getCombinationButtons().values()) {
            combinationButton.setEnabled(false);
        }
    }

    /**
     * Updates the combination buttons according to the chosen action mode
     */
    private void updateCombinationButtons() {
        if (this.currentPlayer.hasAllRollsLeft()) {
            return;
        }

        YatzyScoreBoard scoreBoard = this.currentPlayer.getScoreBoard();
        PlayerAction action = this.view.gameScreen.getSelectedAction();

        HashMap<DiceCombination, CombinationButton> combinationButtons = this.view.gameScreen.getCombinationButtons();

        for (DiceCombination combination : combinationButtons.keySet()) {
            ScoreBoardCell cell = scoreBoard.getCell(combination);

            // check if corresponding cell matches combination
            boolean matches = this.dice.match(combination);

            // check if corresponding cell is filled or crossed
            boolean alreadyFilled = !cell.isEmpty() || cell.isCrossed();

            // check if button should be on or off
            boolean valid = false;

            switch (action) {
                case CROSS:
                    valid = !alreadyFilled;
                    break;
                case SCORE:
                    valid = matches && !alreadyFilled;
                    break;
            }

            combinationButtons.get(combination).setEnabled(valid);
        }
    }

    /**
     * Checks whether the game is over
     * @return boolean indicating if game is over
     */
    private boolean isGameOver() {
        for (YatzyPlayer player : this.players) {
            YatzyScoreBoard scoreBoard = player.getScoreBoard();

            // if everyones personal scoreBoard isn't full, game is not over
            if (!scoreBoard.isFull()) {
                return false;
            }
        }

        return true;
    }

    /**
     * Gets the winning player. May be null if it's a tie.
     * @return the winning player
     */
    private YatzyPlayer getWinner() {
        int leadingScore = 0;
        ArrayList<YatzyPlayer> winners = new ArrayList<YatzyPlayer>();

        for (YatzyPlayer player : this.players) {
            int score = player.getScoreBoard().getTotalScore();

            // someone has a higher score, start over
            if (score > leadingScore) {
                winners.clear();
                leadingScore = score;
            }

            if (score >= leadingScore) {
                winners.add(player);
            }
        }

        // it's not a tie
        if (winners.size() == 1) {
            return winners.get(0);
        }

        // it's a tie
        return null;
    }
}
