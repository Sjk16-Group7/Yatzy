package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.EventListenerList;

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
 *     Change:  When the frame has changed
 * @author Isak
 * @author Gustav
 */
public class YatzyWindow extends JFrame {
    private final static Image windowIcon = new ImageIcon(YatzyWindow.class.getResource("/Dice5active.png")).getImage();

    public final GameScreen gameScreen = new GameScreen();       // game board view
    public final PlayerScreen playerScreen = new PlayerScreen(); // player view
    public final WinScreen winScreen = new WinScreen();          // game over view

    private JPanel wrapper = new JPanel();
    private EventListenerList listenerList = new EventListenerList();

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
        this.setVisible(false);
        this.wrapper.removeAll();
        this.wrapper.add(screen, BorderLayout.CENTER);
        this.fireActionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Change"));
    }

    /**
     * Updates the view
     */
    public void update() {
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Resets the GUI components of this window
     */
    public void reset() {
        this.setVisible(false);
        this.gameScreen.reset();
        this.playerScreen.reset();
        this.winScreen.reset();

        this.wrapper.removeAll();
        this.listenerList = new EventListenerList();

        this.fireActionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Change"));
    }

    /**
     * Initiates the GUI components of the window
     */
    private void initDefaultGUI() {
        this.wrapper.setLayout(new BorderLayout(10, 10));
        this.add(wrapper);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("Help");

        JMenuItem newGameMenuItem = new JMenuItem("New Game");
        newGameMenuItem.setActionCommand("NewGame");
        newGameMenuItem.addActionListener(this::fireActionPerformed);
        fileMenu.add(newGameMenuItem);

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setActionCommand("Exit");
        exitMenuItem.addActionListener(this::fireActionPerformed);
        fileMenu.add(exitMenuItem);

        JMenuItem rulesMenuItem = new JMenuItem("Rules");
        rulesMenuItem.setActionCommand("About");
        rulesMenuItem.addActionListener(this::fireActionPerformed);
        helpMenu.add(rulesMenuItem);

        JMenuItem helpMenuItem = new JMenuItem("About");
        helpMenuItem.setActionCommand("Help");
        helpMenuItem.addActionListener(this::fireActionPerformed);
        helpMenu.add(helpMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        this.setJMenuBar(menuBar);
        this.setIconImage(windowIcon);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    /**
     * Adds an ActionListener to the list of listeners
     *
     * @param listener the listener
     */
    public void addActionListener(ActionListener listener) {
        this.listenerList.add(ActionListener.class, listener);
    }

    /**
     * Removes an ActionListener from the list of listeners
     *
     * @param listener the listener
     */
    public void removeActionListener(ActionListener listener) {
        this.listenerList.remove(ActionListener.class, listener);
    }

    /**
     * Fires an event and calls all ActionListeners
     *
     * @param event the event
     */
    protected void fireActionPerformed(ActionEvent event) {
        Object[] listeners = listenerList.getListenerList();

        for (Object listener : listeners) {
            if (listener instanceof ActionListener) {
                ((ActionListener) listener).actionPerformed(event);
            }
        }
    }
}
