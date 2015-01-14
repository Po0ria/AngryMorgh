package GameLogic;

import GameLogic.Menu.GameMenu;
import GameUI.GameWindow.GameFrame;
import GameUI.GameWindow.GameInterface;
import GameUI.GameWindow.GameInterfaceComponentManager;
import GameUI.GameWindow.GamePanel;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created with IntelliJ IDEA.
 * User: farzadshbfn
 * Date: 2014-12-21
 * Time: 11:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameController{

    private GameScreen gameScreen;

    private GameMenu gameMenu;
    private Level level1;

    public GameFrame gameFrame;

    public GameController(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        gameScreen = GameScreen.MENU;
        gameMenu = new GameMenu(this);
        GameInterfaceComponentManager cigm = (GameInterfaceComponentManager)gameMenu;
        cigm.addComponentsTo(gameFrame.gamePanel);
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    public void startANewGame() {
        if (gameScreen == GameScreen.MENU) {
            gameScreen = GameScreen.GAME;

            GameInterfaceComponentManager cigm = (GameInterfaceComponentManager)gameMenu;
            cigm.removeComponentsFrom(gameFrame.gamePanel);

            level1 = new Level(this);
        }
    }

    public void addMouseMotionListerner(MouseMotionListener l) {
        gameFrame.addMouseMotionListener(l);
    }

    public void addMouseListener(MouseListener l) {
        gameFrame.addMouseListener(l);
    }

    public void addKeyListener(KeyListener k) {
        gameFrame.addKeyListener(k);
    }
}
