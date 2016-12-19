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
import View.AboutDialog.AboutDialog;
import View.GameScreen.CombinationButton;
import View.GameScreen.DiceButton;
import View.GameScreen.PlayerPanel;
import View.RulesDialog.RulesDialog;
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
                this.onRollClicked();
                break;
            case "combination":
                this.onCombinationClicked((CombinationButton) event.getSource());
                break;
            case "mode":
                this.onModeClicked();
                break;
            //case "dice":
            case "change":
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
                this.onPlayersPicked();
                break;
            case "add":
            case "remove":
                this.view.pack();
                break;
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
                this.onNewGameClicked();
                break;
            case "exit":
                this.onExitClicked();
                break;
            case "about":
                new AboutDialog(this.view);
                break;
            case "help":
                new RulesDialog(this.view);
                break;
            case "change":
                this.view.update();
                break;
        }
    };

    private YatzyWindow view = new YatzyWindow();
    private YatzyDiceCollection dice = new YatzyDiceCollection();
    private ArrayList<YatzyPlayer> players = new ArrayList<YatzyPlayer>();
    private YatzyPlayer currentPlayer;

    /**
     * Starts a new game
     */
    public void start() {
        this.reset();
        this.initView();

        this.view.setScreen(this.view.playerScreen);
        this.view.gameScreen.setDice(this.dice);
    }

    /**
     * Adds action listeners to view
     */
    private void initView() {
        this.view.addActionListener(this.windowListener);
        this.view.gameScreen.addActionListener(this.gameScreenListener);
        this.view.playerScreen.addActionListener(this.playerScreenListener);
        this.view.winScreen.addActionListener(this.winScreenListener);
    }

    /**
     * Resets the game
     */
    private void reset() {
        this.currentPlayer = null;
        this.players.clear();

        this.view.reset();
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
     * Updates the player indicator
     */
    private void updatePlayerIndicator() {
        HashMap<YatzyPlayer, PlayerPanel> playerPanels = this.view.gameScreen.getPlayerPanels();

        for (PlayerPanel panel : playerPanels.values()) {
            panel.setActive(false);
        }

        playerPanels.get(this.currentPlayer).setActive(true);
    }

    /**
     * Updates the action mode radio buttons. Will select the cross radio button if user can't score
     */
    private void updateActionMode() {
        if (this.currentPlayer.hasRollsLeft()) {
            return;
        }

        HashMap<DiceCombination, CombinationButton> combinationButtons = this.view.gameScreen.getCombinationButtons();
        boolean isNoneValid = true;

        for (DiceCombination combination : combinationButtons.keySet()) {
            if (combinationButtons.get(combination).isEnabled()) {
                isNoneValid = false;
            }
        }

        if (isNoneValid) {
            // if no combination is valid, switch to cross mode
            this.view.gameScreen.setSelectedAction(PlayerAction.CROSS);
            this.updateCombinationButtons();
        }
    }

    /**
     * Updates the combination buttons according to the chosen action mode
     */
    private void updateCombinationButtons() {
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
     * Updates the dice buttons
     */
    private void updateDiceButtons() {
        boolean rollsLeft = this.currentPlayer.hasRollsLeft();

        for (DiceButton diceButton : this.view.gameScreen.getDiceButtons()) {
            // disable button if player has no rolls left
            diceButton.setEnabled(rollsLeft);

            // update the image of the button
            diceButton.update();
        }
    }

    /**
     * Updates the player panel of the current player
     */
    private void updatePlayerPanels() {
        YatzyPlayer player = this.currentPlayer;
        PlayerPanel playerPanel = this.view.gameScreen.getPlayerPanels().get(player);

        // update display of player scoreboard
        playerPanel.update();
    }

    /**
     * Updates the roll button
     */
    private void updateRollButton() {
        JButton rollButton = this.view.gameScreen.getRollButton();

        if (!this.currentPlayer.hasRollsLeft()) {
            // disable button if user has no rolls left
            rollButton.setEnabled(false);
        }
    }

    /**
     * Called when user starts a new game
     */
    private void onNewGameClicked() {
        int response = JOptionPane.showConfirmDialog(
            this.view,
            "Are you sure?",
            "New game confirmation",
            JOptionPane.YES_NO_OPTION
        );

        if (response == JOptionPane.OK_OPTION) {
            this.start();
        }
    }

    /**
     * Called when program is exiting
     */
    private void onExitClicked() {
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
     * Called when a combinationButton has been clicked in the game view. Crosses or gives score
     * to the associated scoreboard field of the pick for the current player. Also updates the view.
     * @param source the clicked combinationButton
     */
    private void onCombinationClicked(CombinationButton source) {
        DiceCombination combination = source.getCombination();
        YatzyScoreBoard scoreBoard = this.currentPlayer.getScoreBoard();
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

        this.updateCombinationButtons();
        this.updatePlayerPanels();

        this.onNewTurn();
    }

    /**
     * Called when the user changes action mode
     */
    private void onModeClicked() {
        if (this.currentPlayer.hasAllRollsLeft()) {
            return;
        }

        this.updateCombinationButtons();
    }

    /**
     * Called when a rollButton has been clicked. Rolls non-locked dice and updates the game view.
     */
    private void onRollClicked() {
        this.currentPlayer.roll();

        for (DiceButton diceButton : this.view.gameScreen.getDiceButtons()) {
            if (!diceButton.isSelected()) {
                // roll non-selected dice
                diceButton.getDice().roll();
            }
        }

        this.updateRollButton();
        this.updateDiceButtons();
        this.updateCombinationButtons();
        this.updateActionMode();
    }

    /**
     * Called when the OK button is clicked in the player view. Adds inputted players and
     * start the game if validation succeeds.
     */
    private void onPlayersPicked() {
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
        this.view.setScreen(this.view.gameScreen);
        this.view.gameScreen.setPlayers(this.players);

        this.onNewTurn();
    }

    /**
     * Called when it's a new turn
     */
    private void onNewTurn() {
        this.resetGameView();

        if (this.isGameOver()) {
            this.onGameOver();
        } else {
            this.switchToNextPlayer();
            this.updatePlayerIndicator();
        }
    }

    /**
     * Called when game is over
     */
    private void onGameOver() {
        YatzyPlayer winner = this.getWinner();

        this.view.setScreen(this.view.winScreen);
        this.view.winScreen.setPlayers(this.players);
        this.view.winScreen.setWinner(winner);
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
     * Sets a new current player. Will pick a random player if there currently is
     * no active player.
     */
    private void switchToNextPlayer() {
        int newPlayerIndex;

        if (this.currentPlayer == null) {
            // first turn, no current player, so get a random one
            newPlayerIndex = new Random().nextInt(this.players.size());
        } else {
            // get the next player in the list, or the first one if current player is last in list
            int currentPlayerIndex = this.players.indexOf(this.currentPlayer);
            newPlayerIndex = (currentPlayerIndex + 1) % this.players.size();
        }

        this.currentPlayer = this.players.get(newPlayerIndex);
        this.currentPlayer.resetRollsLeft();
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
