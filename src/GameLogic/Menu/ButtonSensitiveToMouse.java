package GameLogic.Menu;

import GameUI.GameWindow.GameInterface;

import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created with IntelliJ IDEA.
 * User: farzadshbfn
 * Date: 2014-12-21
 * Time: 9:21 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class ButtonSensitiveToMouse extends Button
        implements GameInterface, MouseInputListener {

    private Color buttonColor;
    private Color textColor;

    private Color buttonColorMarked;
    private Color textColorMarked;

    protected boolean mouseIsOn;

    public ButtonSensitiveToMouse(Rectangle rectangle, String text, Color buttonColor, Color textColor, Color buttonColorMarked, Color textColorMarked) {
        this(rectangle, text, buttonColor, textColor, buttonColorMarked, textColorMarked, Button.defaultFont);
    }

    public ButtonSensitiveToMouse(Rectangle rectangle, String text, Color buttonColor, Color textColor, Color buttonColorMarked, Color textColorMarked , Font textFont) {
        super(rectangle, text, buttonColor, textColor, textFont);

        this.buttonColor = buttonColor;
        this.textColor = textColor;

        this.buttonColorMarked = buttonColorMarked;
        this.textColorMarked = textColorMarked;
    }

    @Override
    public void update() {
        if (! mouseIsOn) {
            super.setButtonColor(buttonColor);
            super.setTextColor(textColor);
        } else {
            super.setButtonColor(buttonColorMarked);
            super.setTextColor(textColorMarked);
        }
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }



    @Override
    public void mouseDragged(MouseEvent e) { }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX() - 3; // idk why 3! but it needed it to get fixed
        int y = e.getY() - 5; // idk why 5! but it needed it to get fixed

        if (x >= rectangle.x && x <= rectangle.x + rectangle.width &&
                y >= rectangle.y && y <= rectangle.y + rectangle.height) {
            mouseIsOn = true;
        } else {
            mouseIsOn = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
