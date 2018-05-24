package game;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by Anwender on 24.05.2018.
 */
public class Game{

    private Bitmap field;
    private int activePlayer;

    private Stack<Move> moves = new Stack<>();

    public Game(){
        field = new Bitmap();
    }


    public int evaluate() {
        return 0;
    }

    public int getActivePlayer() {
        return activePlayer;
    }

    public void move(Move move) {


        if(move.getMap_target() == 0){
            this.field.setValue(move.getX_from(), move.getY_from(),(byte)1);
        }

        this.field[move.getMap_from()].move();


        this.field[move.getBitmapA().getBitmapIndex()] = move.getBitmapA();
        this.field[move.getBitmapB().getBitmapIndex()] = move.getBitmapB();
        this.moves.add(move);
        this.activePlayer = -activePlayer;
    }

    public void undoMove() {
        this.activePlayer = -activePlayer;
        Move m = moves.pop();
        Move prev = moves.get(moves.size()-1);
        this.field[m.getBitmapA().getBitmapIndex()] = pre
    }

    public static void main(String[] args) {
        byte b = 12;
        System.out.println(b);
    }

    public void orderPossibleMoves(LinkedList moves) {

    }

    public LinkedList getPossibleMoves() {
        return null;
    }
}
