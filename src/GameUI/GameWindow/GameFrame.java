package GameUI.GameWindow;

import GameLogic.GameController;
import GameLogic.Level;
import GameLogic.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created with IntelliJ IDEA.
 * User: farzadshbfn
 * Date: 2014-12-09
 * Time: 1:46 AM
 * To change this template use File | Settings | File Templates.
 */
public final class GameFrame extends JFrame {
    public static int width;
    public static int height;
    private static GameFrame instance;
    public static GamePanel gamePanel;
    static Level level1;

    public static GameFrame getInstance() {
        if (instance == null)
            instance = new GameFrame(width, height);
        return instance;
    }

    private GameFrame(int width, int height) {
        super("Paranoid");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        gamePanel = new GamePanel(width, height);
        gamePanel.setOpaque(true); //content panes must be opaque
        this.setContentPane(gamePanel);

        //Display the window.
        this.setMaximumSize(new Dimension(gamePanel.getAreaRectangle().width + 25, gamePanel.getAreaRectangle().height + 43));
        this.pack();
        gamePanel.runGameLoop();


        // after here, everything is gameController hands
        GameController gameController = new GameController(this);

    }

    /**
     * this graphic will be added in front of page
     * @param gi
     */
    public static void addNewGameInterface(GameInterface gi) {
        addNewGameInterface(gi, Double.MAX_VALUE);
    }

    /**
     * this graphic will be added in @layer
     * @param gi
     * @param layer
     */
    public static void addNewGameInterface(GameInterface gi, double layer) {
        addNewGameInterface(gi, new Double(layer));
    }

    public static void addNewGameInterface(GameInterface gi, Double layer) {
        gamePanel.addNewGameInterface(gi, layer);
    }


    public static void removeGameInterface(GameInterface giToRemove) {
        gamePanel.removeGameInterface(giToRemove);
    }


    // TODO everything that relates to mouse must be added to scrollPanel inside gamePanel too
    @Override
    public synchronized void addMouseMotionListener(MouseMotionListener l) {
        super.addMouseMotionListener(l);    //To change body of overridden methods use File | Settings | File Templates.
        gamePanel.scroller.addMouseMotionListener(l);
    }

    @Override
    public synchronized void addMouseListener(MouseListener l) {
        super.addMouseListener(l);    //To change body of overridden methods use File | Settings | File Templates.
        gamePanel.scroller.addMouseListener(l);
    }

    @Override
    public synchronized void addKeyListener(KeyListener l) {
        super.addKeyListener(l);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public synchronized void removeKeyListener(KeyListener l) {
        super.removeKeyListener(l);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
