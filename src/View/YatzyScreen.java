package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.EventListenerList;

/**
 * YatzyScreen is a base class for view panels in YatzyWindow.
 * @author Isak
 */
public abstract class YatzyScreen extends JPanel {
    private EventListenerList listenerList = new EventListenerList();

    /**
     * Class default constructor
     */
    public YatzyScreen() {
        this.initDefaultGUI();
    }

    /**
     * Adds an ActionListener to the list of listeners
     * @param listener the listener
     */
    public void addActionListener(ActionListener listener) {
        this.listenerList.add(ActionListener.class, listener);
    }

    /**
     * Removes an ActionListener from the list of listeners
     * @param listener the listener
     */
    public void removeActionListener(ActionListener listener) {
        this.listenerList.remove(ActionListener.class, listener);
    }

    /**
     * Clears the list of listeners
     */
    public void reset() {
        this.listenerList = new EventListenerList();
    }

    /**
     * Fires an event and calls all ActionListeners
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

    /**
     * Initiates the GUI components of this panel
     */
    protected abstract void initDefaultGUI();
}
