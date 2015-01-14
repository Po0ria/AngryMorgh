package GameLogic.Objects;

import GameUI.GameWindow.GameInterface;
import GameUI.GameWindow.GameInterfaceComponentManager;
import GameUI.GameWindow.GamePanel;

import java.awt.*;

/**
 * Created by ATroskan on 1/13/2015.
 */
public class Morgh implements GameInterfaceComponentManager,GameInterface {
    private Location location;

    public Morgh(Location _location)
    {
        this.location = _location;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void addComponentsTo(GamePanel gamePanel) {

    }

    @Override
    public void removeComponentsFrom(GamePanel gamePanel) {

    }
}
