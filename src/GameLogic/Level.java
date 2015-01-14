package GameLogic;

import GameLogic.Map.Map;
import GameLogic.Map.MapMaker;
import GameUI.GameWindow.GameInterface;
import GameUI.GameWindow.GameInterfaceComponentManager;
import GameUI.GameWindow.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created with IntelliJ IDEA.
 * User: farzadshbfn
 * Date: 2014-12-22
 * Time: 10:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class Level
        implements GameInterface, GameInterfaceComponentManager, KeyListener{

    GameController gameController;

    Map gameMap;

    public Level(GameController gameController) {
        this.gameController = gameController;
        gameMap = MapMaker.generateMap(3, gameController.gameFrame.gamePanel.getAreaRectangle().width, gameController.gameFrame.gamePanel.getAreaRectangle().height);
        gameController.gameFrame.gamePanel.addNewGameInterface(gameMap);
    }

    @Override
    public void update() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void draw(Graphics g) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addComponentsTo(GamePanel gamePanel) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void removeComponentsFrom(GamePanel gamePanel) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
