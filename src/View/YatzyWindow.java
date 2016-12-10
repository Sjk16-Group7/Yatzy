package View;

import java.awt.*;

import javax.swing.*;

import View.GameScreen.GameScreen;
import View.PlayerScreen.PlayerScreen;
import View.WinScreen.WinScreen;

public class YatzyWindow extends JFrame {
    public final GameScreen gameScreen = new GameScreen();
    public final PlayerScreen playerScreen = new PlayerScreen();
    public final WinScreen winScreen = new WinScreen();

    private final JPanel wrapper = new JPanel();

    public YatzyWindow() {
        super("Yatzy");

        this.initDefaultGUI();
    }

    public void changeScreenTo(JPanel panel) {
        this.wrapper.removeAll();
        this.wrapper.add(panel, BorderLayout.CENTER);
        this.pack();
    }

    private void initDefaultGUI() {
        this.wrapper.setLayout(new BorderLayout(10, 10));
        this.add(wrapper);

        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}
