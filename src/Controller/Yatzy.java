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
            case "dice":
            case "change":
                this.view.pack();
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
                this.view.pack();
                break;
        }
    };

    /**
     * Listener for actions in the game over view
     */
    private ActionListener winScreenListener = event -> {
        switch (event.getActionCommand().toLowerCase()) {
            case "change":
                this.view.pack();
                break;
        }
    };

    // TODO listener for menu, run onStart() on new Game, dispose on Exit et.c.

    private YatzyWindow view;
    private ArrayList<YatzyPlayer> players;
    private YatzyPlayer currentPlayer;
    private YatzyDiceCollection dice;

    /**
     * Class default constructor
     */
    public Yatzy() {
        this.players = new ArrayList<YatzyPlayer>();
        this.dice = new YatzyDiceCollection();

        this.onStart();
    }

    /**
     * Called when a new game starts
     */
    private void onStart() {
        this.view = new YatzyWindow();
        this.view.gameScreen.setDice(this.dice);
        this.view.gameScreen.setPlayers(this.players);

        this.view.gameScreen.addActionListener(this.gameScreenListener);
        this.view.playerScreen.addActionListener(this.playerScreenListener);
        this.view.winScreen.addActionListener(this.winScreenListener);

        // this.view.winScreen.reset(); // needed?
        this.view.setScreen(this.view.playerScreen);
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
     * Called when a combinationButton has been clicked in the game view. Crosses or gives score
     * to the associated scoreboard field of the pick for the current player. Also updates the
     * game view, and starts a new turn.
     * @param pick the clicked combinationButton
     */
    private void onCombinationPick(CombinationButton pick) {
        System.out.println("comb");
        // TODO calculate points or cross

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

        HashMap<DiceCombination, CombinationButton> combinationButtons = this.view.gameScreen.getCombinationButtons();

        for (DiceCombination combination : combinationButtons.keySet()) {
            YatzyScoreBoard scoreBoard = this.currentPlayer.getScoreBoard();
            ScoreBoardCell cell = scoreBoard.getCell(combination);

            // check if corresponding cell is filled or crossed
            boolean matches = this.dice.match(combination);

            // check if corresponding cell is filled or crossed
            boolean alreadyFilled = !cell.isEmpty() || cell.isCrossed();

            // disable combinationButton if it doesn't match or is already filled
            combinationButtons.get(combination).setEnabled(matches && alreadyFilled);
        }

        // TODO enable all if none matches and be ready to cross instead of count points
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

        for (String name : names) {
            this.players.add(new YatzyPlayer(name));
        }

        // update views
        this.view.playerScreen.reset();
        this.view.setScreen(this.view.gameScreen);

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

        /*
         * Set new current player
         */
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

        /*
         * Reset the view
         */
        this.view.gameScreen.getRollButton().setEnabled(true);

        for (DiceButton diceButton : this.view.gameScreen.getDiceButtons()) {
            diceButton.reset();
        }

        for (CombinationButton combinationButton : this.view.gameScreen.getCombinationButtons().values()) {
            combinationButton.setEnabled(false);
        }

        // TODO update view with new current player
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
