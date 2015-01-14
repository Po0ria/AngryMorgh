package GameLogic.Menu;

import GameLogic.GameController;
import GameUI.GameWindow.GameInterface;
import GameUI.GameWindow.GameInterfaceComponentManager;
import GameUI.GameWindow.GamePanel;

import java.awt.*;
import java.awt.event.MouseEvent;

public class GameMenu
        implements GameInterfaceComponentManager{

    private GameController gameController;

    private NewGameButton newGameButton;
    private ExitGameButton exitGameButton;

    public GameMenu(GameController gameController) {
        this.gameController = gameController;

        newGameButton = new NewGameButton(new Rectangle(200, 200, 250, 50), "New Game", Color.blue, Color.white, Color.red, Color.black);
        exitGameButton = new ExitGameButton(new Rectangle(200, 400, 250, 50), "Exit Game", Color.blue, Color.white, Color.red, Color.black);

        gameController.addMouseListener(newGameButton);
        gameController.addMouseMotionListerner(newGameButton);

        gameController.addMouseListener(exitGameButton);
        gameController.addMouseMotionListerner(exitGameButton);

    }

    private class NewGameButton extends ButtonSensitiveToMouse {
        private NewGameButton(Rectangle rectangle, String text, Color buttonColor, Color textColor, Color buttonColorMarked, Color textColorMarked) {
            super(rectangle, text, buttonColor, textColor, buttonColorMarked, textColorMarked);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);    //To change body of overridden methods use File | Settings | File Templates.
            if (mouseIsOn)
                gameController.startANewGame();
        }
    }


    private class ExitGameButton extends ButtonSensitiveToMouse {
        private ExitGameButton(Rectangle rectangle, String text, Color buttonColor, Color textColor, Color buttonColorMarked, Color textColorMarked) {
            super(rectangle, text, buttonColor, textColor, buttonColorMarked, textColorMarked);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            if (mouseIsOn)
                System.exit(0);
        }
    }


    @Override
    public void addComponentsTo(GamePanel gamePanel) {
        gamePanel.addNewGameInterface(newGameButton);
        gamePanel.addNewGameInterface(exitGameButton);
    }

    @Override
    public void removeComponentsFrom(GamePanel gamePanel) {
        gamePanel.removeGameInterface(newGameButton);
        gamePanel.removeGameInterface(exitGameButton);
    }
}
