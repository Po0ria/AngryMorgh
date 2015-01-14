package GameLogic.Map;

import GameUI.GameWindow.GameFrame;
import com.sun.javafx.geom.Vec2d;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * Created with IntelliJ IDEA.
 * User: farzadshbfn
 * Date: 2014-12-20
 * Time: 4:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class MapMaker {

    /**
     * !!! it's hard to generate map for all values of n! so for simplicity n changes to 3 in bigger case
     * width and height are area's info
     * @param n
     * @param width
     * @param height
     * @return
     */
    public static Map generateMap(int n, int width, int height) {

        n = 3;

        // devide area into 3 parts
        ArrayList<Polygon> hills = new ArrayList<Polygon>();
        hills.add(makePolygonWith((width)/6, 3*height/4, (width - new Random().nextInt(50)) / 6, (height - new Random().nextInt(30))/4));
        hills.add(makePolygonWith((width)/2, height/4, (width - new Random().nextInt(50)) / 6, (height - new Random().nextInt(30))/4));
        hills.add(makePolygonWith((5*width)/6, 3*height/4, (width - new Random().nextInt(50)) / 6, (height - new Random().nextInt(30))/4));

        return new Map(hills);
    }

    /**
     * generates a polygon that looks like oval
     * @param xCenter
     * @param yCenter
     * @param widthRadius
     * @param heightRadius
     * @return
     */
    private static Polygon makePolygonWith(int xCenter, int yCenter, int widthRadius, int heightRadius) {
        Point p[] = new Point[1000];

        int cnt = 0;
        while (cnt < 1000) {
            int randomX = new Random().nextInt(2 * (int)(0.95*widthRadius));
            int randomY = new Random().nextInt(2 * (int)(0.95*heightRadius));

            Point newPoint = new Point();
            newPoint.x = xCenter - widthRadius + randomX;
            newPoint.y = yCenter - heightRadius + randomY;

            double topX = (newPoint.x - xCenter);
            double topY = (newPoint.y - yCenter);
            topX *= topX;
            topY *= topY;
            double x = topX / (double)(widthRadius) / (double)(widthRadius);
            double y = topY / (double)(heightRadius) / (double)(heightRadius);

            if (x + y <= 1)
                p[cnt++] = newPoint;
        }

        Point[] hull = ConvexHull.convex_hull(p).clone();
        int xPoints[] = new int[hull.length];
        int yPoints[] = new int[hull.length];

        for (int i = 0; i < hull.length; i++) {
            xPoints[i] = hull[i].x;
            yPoints[i] = hull[i].y;
        }
        return new Polygon(xPoints, yPoints, hull.length);
    }


    static class Point implements Comparable<Point> {
        int x, y;

        public int compareTo(Point p) {
            if (this.x == p.x) {
                return this.y - p.y;
            } else {
                return this.x - p.x;
            }
        }
//
//        public String toString() {
//            return "("+x + "," + y+")";
//        }

    }

    public static class ConvexHull {

        public static long cross(Point O, Point A, Point B) {
            return (A.x - O.x) * (B.y - O.y) - (A.y - O.y) * (B.x - O.x);
        }

        public static Point[] convex_hull(Point[] P) {

            if (P.length > 1) {
                int n = P.length, k = 0;
                Point[] H = new Point[2 * n];

                Arrays.sort(P);

                // Build lower hull
                for (int i = 0; i < n; ++i) {
                    while (k >= 2 && cross(H[k - 2], H[k - 1], P[i]) <= 0)
                        k--;
                    H[k++] = P[i];
                }

                // Build upper hull
                for (int i = n - 2, t = k + 1; i >= 0; i--) {
                    while (k >= t && cross(H[k - 2], H[k - 1], P[i]) <= 0)
                        k--;
                    H[k++] = P[i];
                }
                if (k > 1) {
                    H = Arrays.copyOfRange(H, 0, k - 1); // remove non-hull vertices after k; remove k - 1 which is a duplicate
                }
                return H;
            } else if (P.length <= 1) {
                return P;
            } else{
                return null;
            }
        }
    }
}
