package View.WinScreen;

import java.awt.event.ActionEvent;

import View.YatzyScreen;

/**
 * WinScreen is a view panel showing the final scores and the winner of a game.
 * Possible ActionCommands listeners may recieve from this class include:
 *     Change:      When the panel has changed
 * @author Isak
 */
public class WinScreen extends YatzyScreen {
    /**
     * Initiates the GUI components of this panel
     */
    @Override
    protected void initDefaultGUI() {
        // TODO
    }

    /**
     * Resets the GUI components of this panel
     */
    @Override
    public void reset() {
        super.reset();

        // TODO

        this.fireActionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Change"));
    }
}
