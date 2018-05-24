package game;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by Anwender on 24.05.2018.
 */
public class Game{

    private Bitmap field;
    private byte activePlayer;

    private Stack<Move> moves = new Stack<>();

    public Game(){
        field = new Bitmap();
    }


    public int evaluate() {
        return 0;
    }

    public byte getActivePlayer() {
        return activePlayer;
    }

    public void move(Move move) {
        this.field.setValue(move.getX_from(), move.getY_from(),(byte)0);
        this.field.setValue(move.getX_to(), move.getY_to(),move.getMap_from());

        this.moves.add(move);
        this.activePlayer = (byte)-activePlayer;
    }

    public void undoMove() {
        this.activePlayer = (byte)-activePlayer;
        Move move = moves.pop();

        this.field.setValue(move.getX_from(), move.getY_from(),move.getMap_from());
        this.field.setValue(move.getX_to(), move.getY_to(),move.getMap_target());
    }

    public void orderPossibleMoves(LinkedList moves) {

    }

    public LinkedList getPossibleMoves() {
        return null;
    }
}
