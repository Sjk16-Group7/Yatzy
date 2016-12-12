package View;

import java.awt.*;

import javax.swing.*;

import View.GameScreen.GameScreen;
import View.PlayerScreen.PlayerScreen;
import View.WinScreen.WinScreen;

/**
 * YatzyWindow is an extension of a JFrame. It's basically an empty window, with just a menu.
 * It can switch between views, or "screens", and is not visible until that's done explicitly.
 * Possible ActionCommands listeners may recieve include:
 *     NewGame: When a new game is pressed in the menu
 *     Exit:    When exit is pressed in the menu
 *     About:   When about is pressed in the menu
 *     Help:    When help is pressed in the menu
 */
public class YatzyWindow extends JFrame {
    public final GameScreen gameScreen = new GameScreen();       // game board view
    public final PlayerScreen playerScreen = new PlayerScreen(); // player view
    public final WinScreen winScreen = new WinScreen();          // game over view

    private final JPanel wrapper = new JPanel();

    /**
     * Class default constructor
     */
    public YatzyWindow() {
        super("Yatzy");

        this.initDefaultGUI();
    }

    /**
     * Sets the view/"screen" of the window
     * @param screen the screen
     * @see          public instance variables
     */
    public void setScreen(YatzyScreen screen) {
        this.wrapper.removeAll();
        this.wrapper.add(screen, BorderLayout.CENTER);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Initiates the GUI components of the window
     */
    private void initDefaultGUI() {
        this.wrapper.setLayout(new BorderLayout(10, 10));
        this.add(wrapper);

        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        // TODO add menu and listeners

        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
