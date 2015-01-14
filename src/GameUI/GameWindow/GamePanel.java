package GameUI.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: farzadshbfn
 * Date: 2014-12-09
 * Time: 12:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class GamePanel extends JPanel {

    public int deltaX() {
        return scroller.getViewport().getViewPosition().x;
    }

    public int deltaY() {
        return scroller.getViewport().getViewPosition().y;
    }

    private DrawingPanel drawingPanel;
    private Image background;

    private HashMap<Double, Vector<GameInterface> > gameInterfaces;

    private Vector<GameInterface> toDeleteGameInterfaces;
    private Vector<Map.Entry<GameInterface, Double> > toAddGameInterface;

    public static final int defaultWidth = 800;
    public static final int defaultHeight = 600;

    private int areaWidth;
    private int areaHeight;

    private Rectangle areaRectangle;

    public boolean running = false;
    private int fps = 60;
    private int frameCount = 0;
    public JScrollPane scroller;

    public GamePanel() {
        this(defaultWidth, defaultHeight);
    }

    public GamePanel(int width, int height) {
        this(width, height, new ImageIcon("Images/background1.png").getImage());
    }

    public GamePanel(int width, int height, Image background) {
        super(new BorderLayout());

        toDeleteGameInterfaces = new Vector<GameInterface>();
        toAddGameInterface = new Vector<Map.Entry<GameInterface, Double>>();
        this.background = background;

        areaWidth = background.getWidth(this);
        areaHeight = background.getHeight(this);

        areaRectangle = new Rectangle(0, 0, areaWidth, areaHeight);

        //Set up the drawing area.
        drawingPanel = new DrawingPanel();
        drawingPanel.setPreferredSize(new Dimension(areaWidth, areaHeight));
        drawingPanel.setBackground(null);

//        Put the drawing area in a scroll pane.
        scroller = new JScrollPane(drawingPanel);
        scroller.setPreferredSize(new Dimension(width, height));

        //Lay out this demo.
        add(scroller, BorderLayout.CENTER);
//        add(drawingPanel, BorderLayout.CENTER);

        gameInterfaces = new HashMap<Double, Vector<GameInterface>>();
        // create new map here!
    }


    public Rectangle getAreaRectangle() {
        return areaRectangle;
    }

    /**
     * this graphic will be added in front of page
     * @param gi
     */
    public void addNewGameInterface(GameInterface gi) {
        this.addNewGameInterface(gi, Double.MAX_VALUE);
    }

    /**
     * this graphic will be added in @layer
     * @param gi
     * @param layer
     */
    public void addNewGameInterface(GameInterface gi, double layer) {
        this.addNewGameInterface(gi, new Double(layer));
    }

    public void addNewGameInterface(GameInterface gi, Double layer) {
        toAddGameInterface.add(new AbstractMap.SimpleEntry<GameInterface, Double>(gi, layer));
    }

    private void _addNewGameInterface(GameInterface gi, Double layer) {
        Vector<GameInterface> vgi = gameInterfaces.get(layer);
        if (vgi == null) {
            vgi = new Vector<GameInterface>(0);
            gameInterfaces.put(layer, vgi);
        }
        vgi.add(gi);
    }


    public void removeGameInterface(GameInterface giToRemove) {
        toDeleteGameInterfaces.add(giToRemove);
    }

    private void _removeGameInterface(GameInterface giToRemove) {
        for (Map.Entry<Double, Vector<GameInterface> > entry : gameInterfaces.entrySet()) {
            Vector<GameInterface> gisToDelete = new Vector<GameInterface>();
            for (GameInterface gi : entry.getValue()) {
                if (giToRemove.equals(gi)) {
                    gisToDelete.add(gi);
                }
            }

            for (GameInterface gi: gisToDelete) {
                entry.getValue().remove(gi);
            }
        }
    }

    public Rectangle getScreenRectangle() {
        return new Rectangle(0, 0, areaWidth, areaHeight);
    }

    /** The component inside the scroll pane. */
    public class DrawingPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(background, 0, 0, null);

            for (Map.Entry<Double, Vector<GameInterface> > entry : gameInterfaces.entrySet()) {
                for (GameInterface gi : entry.getValue()) {
                    try {
                        gi.draw(g);
                    } catch (NullPointerException e) {
                    }
                }
            }
        }


        protected void update() {
            for (Map.Entry<Double, Vector<GameInterface> > entry : gameInterfaces.entrySet()) {
                for (GameInterface gi : entry.getValue()) {
                    try {
                        gi.update();
                    } catch (NullPointerException e) {
                    }
                }
            }

            for (GameInterface gi : toDeleteGameInterfaces)
                _removeGameInterface(gi);

            for (Map.Entry<GameInterface, Double> gameInterfaceDoubleEntry : toAddGameInterface)
                _addNewGameInterface(gameInterfaceDoubleEntry.getKey(), gameInterfaceDoubleEntry.getValue());

            toAddGameInterface.clear();
            toDeleteGameInterfaces.clear();
        }
    }


    //Starts a new thread and runs the game loop in it.
    public void runGameLoop() {
        Thread loop = new Thread() {
            public void run() {
                gameLoop();
            }
        };
        loop.start();
        running = true;
    }

    //Only run this in another Thread!
    private void gameLoop() {
        //This value would probably be stored elsewhere.
        final double GAME_HERTZ = 45.0;
        //Calculate how many ns each frame should take for our target game hertz.
        final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
        //At the very most we will update the game this many times before a new render.
        //If you're worried about visual hitches more than perfect timing, set this to 1.
        final int MAX_UPDATES_BEFORE_RENDER = 5;
        //We will need the last update time.
        double lastUpdateTime = System.nanoTime();
        //Store the last time we rendered.
        double lastRenderTime = System.nanoTime();

        //If we are able to get as high as this FPS, don't render again.
        final double TARGET_FPS = 60;
        final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;

        //Simple way of finding FPS.
        int lastSecondTime = (int) (lastUpdateTime / 1000000000);

        while (running) {
            double now = System.nanoTime();
            int updateCount = 0;

            //Do as many game updates as we need to, potentially playing catchup.
            while( now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER ) {
                updateGame();
                lastUpdateTime += TIME_BETWEEN_UPDATES;
                updateCount++;
            }

            //If for some reason an update takes forever, we don't want to do an insane number of catchups.
            //If you were doing some sort of game that needed to keep EXACT time, you would get rid of this.
            if ( now - lastUpdateTime > TIME_BETWEEN_UPDATES) {
                lastUpdateTime = now - TIME_BETWEEN_UPDATES;
            }

            //Render. To do so, we need to calculate interpolation for a smooth render.
            float interpolation = Math.min(1.0f, (float) ((now - lastUpdateTime) / TIME_BETWEEN_UPDATES) );
            drawGame();
            lastRenderTime = now;

            //Update the frames we got.
            int thisSecond = (int) (lastUpdateTime / 1000000000);
            if (thisSecond > lastSecondTime) {
                System.out.println("NEW SECOND " + thisSecond + " " + frameCount);
                fps = frameCount;
                frameCount = 0;
                lastSecondTime = thisSecond;
            }

            //Yield until it has been at least the target time between renders. This saves the CPU from hogging.
            while ( now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS && now - lastUpdateTime < TIME_BETWEEN_UPDATES) {
                Thread.yield();

                //This stops the app from consuming all your CPU. It makes this slightly less accurate, but is worth it.
                //You can remove this line and it will still work (better), your CPU just climbs on certain OSes.
                //FYI on some OS's this can cause pretty bad stuttering. Scroll down and have a look at different peoples' solutions to this. {}

                now = System.nanoTime();
            }
        }
    }

    private void updateGame() {
        drawingPanel.update();
    }

    private void drawGame() {
        drawingPanel.repaint();
    }
}