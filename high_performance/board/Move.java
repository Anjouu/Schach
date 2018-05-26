package high_performance.board;

import sun.awt.image.ImageWatched;

import java.util.LinkedList;
import java.util.List;

public class Move {

    int from;
    int to;

    int id_from;
    int id_to;

    public static void printMoveList(LinkedList<Move> moves){
        for(Move m:moves){
            System.out.println(m);
        }
        System.out.println("");
    }

    public Move(int from, int to, int id_from, int id_to) {
        this.from = from;
        this.to = to;
        this.id_from = id_from;
        this.id_to = id_to;
    }

    public Move(int from, int to, int id_from) {
        this.from = from;
        this.to = to;
        this.id_from = id_from;
    }

    public Move(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getId_from() {
        return id_from;
    }

    public void setId_from(int id_from) {
        this.id_from = id_from;
    }

    public int getId_to() {
        return id_to;
    }

    public void setId_to(int id_to) {
        this.id_to = id_to;
    }

    @Override
    public String toString() {
        return "Move{" +
                "from=" + from +
                ", to=" + to +
                ", id_from=" + id_from +
                ", id_to=" + id_to +
                '}';
    }
}
