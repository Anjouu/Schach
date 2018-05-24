package game;

/**
 * Created by Anwender on 24.05.2018.
 */
public class Move {

    private byte x_from;
    private byte y_from;
    private byte map_from;

    private byte x_to;
    private byte y_to;
    private byte map_target;


    public Move(byte x_from, byte y_from, byte map_from, byte x_to, byte y_to, byte map_target) {
        this.x_from = x_from;
        this.y_from = y_from;
        this.map_from = map_from;
        this.x_to = x_to;
        this.y_to = y_to;
        this.map_target = map_target;
    }

    public byte getX_from() {
        return x_from;
    }

    public void setX_from(byte x_from) {
        this.x_from = x_from;
    }

    public byte getY_from() {
        return y_from;
    }

    public void setY_from(byte y_from) {
        this.y_from = y_from;
    }

    public byte getMap_from() {
        return map_from;
    }

    public void setMap_from(byte map_from) {
        this.map_from = map_from;
    }

    public byte getX_to() {
        return x_to;
    }

    public void setX_to(byte x_to) {
        this.x_to = x_to;
    }

    public byte getY_to() {
        return y_to;
    }

    public void setY_to(byte y_to) {
        this.y_to = y_to;
    }

    public byte getMap_target() {
        return map_target;
    }

    public void setMap_target(byte map_target) {
        this.map_target = map_target;
    }
}
