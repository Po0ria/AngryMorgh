package GameLogic.Menu;

import GameUI.GameWindow.GameInterface;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;

/**
 * Created with IntelliJ IDEA.
 * User: farzadshbfn
 * Date: 2014-12-21
 * Time: 4:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class Button
        implements GameInterface {

    protected Rectangle rectangle;
    private String text;

    private Color buttonColor;
    private Color textColor;
    private Font textFont;
    protected static final Font defaultFont = Font.getFont(Font.SANS_SERIF);


    public Button(Rectangle rectangle, String text, Color buttonColor, Color textColor) {
        this(rectangle, text, buttonColor, textColor, defaultFont);
    }


    public Button(Rectangle rectangle, String text, Color buttonColor, Color textColor, Font textFont) {
        this.rectangle = rectangle;
        this.text = text;
        this.buttonColor = buttonColor;
        this.textColor = textColor;
        this.textFont = textFont;
    }


    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Color getButtonColor() {
        return buttonColor;
    }

    public void setButtonColor(Color buttonColor) {
        this.buttonColor = buttonColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public Font getTextFont() {
        return textFont;
    }

    public void setTextFont(Font textFont) {
        this.textFont = textFont;
    }


    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(buttonColor);
        g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);


        g.setColor(textColor);
        g.setFont(textFont);


        FontMetrics fm = g.getFontMetrics();
        Rectangle2D r = fm.getStringBounds(text, g);
        int x = rectangle.x + (rectangle.width - (int) r.getWidth()) / 2;
        int y = rectangle.y + (rectangle.height - (int) r.getHeight()) / 2 + fm.getAscent();
        g.drawString(text, x, y);
    }
}
