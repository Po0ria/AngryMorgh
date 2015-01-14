package GameLogic.Map;

import GameUI.GameWindow.GameInterface;
import GameUI.GameWindow.GameInterfaceComponentManager;
import GameUI.GameWindow.GamePanel;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: farzadshbfn
 * Date: 2014-12-20
 * Time: 4:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class Map
            implements GameInterfaceComponentManager, GameInterface {

    ArrayList<Polygon> hills;


    public Map(ArrayList<Polygon> hills) {
        this.hills = hills;
    }

    public ArrayList<Polygon> getHills() {
        return hills;
    }

    public void setHills(ArrayList<Polygon> hills) {
        this.hills = hills;
    }

    @Override
    public void addComponentsTo(GamePanel gamePanel) {
    }

    @Override
    public void removeComponentsFrom(GamePanel gamePanel) {
    }

    @Override
    public void update() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void draw(Graphics g) {
        for (Polygon polygon: hills) {
            g.setColor(Color.green);
            g.fillPolygon(polygon);
        }
    }
}

// zadi dg dari kos migiaaa koonet mizaram :|