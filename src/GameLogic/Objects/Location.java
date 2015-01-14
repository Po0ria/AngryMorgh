package GameLogic.Objects;

/**
 * Created by ATroskan on 1/13/2015.
 */
public class Location implements Comparable<Location>{

    int x, y;

    public int compareTo(Location p) {
        if (this.x == p.x) {
            return this.y - p.y;
        } else {
            return this.x - p.x;
        }
    }
}
