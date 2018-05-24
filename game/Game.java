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
        reset();
    }

    public void reset(){
        field = new Bitmap();
        field.setValue(0,0, (byte) 2);
        field.setValue(1,0, (byte) 3);
        field.setValue(2,0, (byte) 4);
        field.setValue(3,0, (byte) 5);
        field.setValue(4,0, (byte) 6);
        field.setValue(5,0, (byte) 4);
        field.setValue(6,0, (byte) 3);
        field.setValue(7,0, (byte) 2);
        field.setValue(0,1, (byte) 1);
        field.setValue(1,1, (byte) 1);
        field.setValue(2,1, (byte) 1);
        field.setValue(3,1, (byte) 1);
        field.setValue(4,1, (byte) 1);
        field.setValue(5,1, (byte) 1);
        field.setValue(6,1, (byte) 1);
        field.setValue(7,1, (byte) 1);
        field.setValue(0,7, (byte) -2);
        field.setValue(1,7, (byte) -3);
        field.setValue(2,7, (byte) -4);
        field.setValue(3,7, (byte) -5);
        field.setValue(4,7, (byte) -6);
        field.setValue(5,7, (byte) -4);
        field.setValue(6,7, (byte) -3);
        field.setValue(7,7, (byte) -2);
        field.setValue(0,6, (byte) -1);
        field.setValue(1,6, (byte) -1);
        field.setValue(2,6, (byte) -1);
        field.setValue(3,6, (byte) -1);
        field.setValue(4,6, (byte) -1);
        field.setValue(5,6, (byte) -1);
        field.setValue(6,6, (byte) -1);
        field.setValue(7,6, (byte) -1);

    }

    public int evaluate() {
        return (int)(1000 * Math.random());
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

    public void orderPossibleMoves(LinkedList<Move> moves) {

    }

    public LinkedList<Move> getPossibleMoves() {
        LinkedList<Move> moves = new LinkedList<>();

        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if (Math.abs(field.getValue(i, j)) == (1 * activePlayer)){
                    if (j != 7 || j != 0){
                        if (field.getValue(i, j+activePlayer) == 0){
                            //moves.add(Move());
                        }
                    }
                }
            }
        }

        return moves;
    }





    public Bitmap getField() {
        return field;
    }

    public Stack<Move> getMoves() {
        return moves;
    }
}
