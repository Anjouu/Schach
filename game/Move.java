package game;

/**
 * Created by Anwender on 24.05.2018.
 */
public class Move {

    private int x_from;
    private int y_from;
    private int map_from;

    private int x_to;
    private int y_to;
    private int map_target;


    public Move(int x_from, int y_from, int map_from, int x_to, int y_to, int map_target) {
        this.x_from = x_from;
        this.y_from = y_from;
        this.map_from = map_from;
        this.x_to = x_to;
        this.y_to = y_to;
        this.map_target = map_target;
    }

    public int getX_from() {
        return x_from;
    }

    public void setX_from(int x_from) {
        this.x_from = x_from;
    }

    public int getY_from() {
        return y_from;
    }

    public void setY_from(int y_from) {
        this.y_from = y_from;
    }

    public int getMap_from() {
        return map_from;
    }

    public void setMap_from(int map_from) {
        this.map_from = map_from;
    }

    public int getX_to() {
        return x_to;
    }

    public void setX_to(int x_to) {
        this.x_to = x_to;
    }

    public int getY_to() {
        return y_to;
    }

    public void setY_to(int y_to) {
        this.y_to = y_to;
    }

    public int getMap_target() {
        return map_target;
    }

    public void setMap_target(int map_target) {
        this.map_target = map_target;
    }
}
