package GameUI.GameWindow;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: farzadshbfn
 * Date: 2014-12-09
 * Time: 1:52 AM
 * To change this template use File | Settings | File Templates.
 */
public interface GameInterface {

    /**
     * this function get's called before drwaing every time.
     * if you want to do some changes and actions, do inside this function
     */
    public void update();

    /**
     * this function is for drawing, and everything you want to do ONLY with drawing
     */
    public void draw(Graphics g);
}
