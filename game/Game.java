package game;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by Anwender on 24.05.2018.
 */
public class Game implements GameInterface<Move>{

    private Bitmap[] field = new Bitmap[12];
    private int activePlayer;

    public Game(){
        for(int i=0; i<12; i++){
            field[i] = new Bitmap(i);
        }
    }


    private Stack<Move> moves = new Stack<>();
    @Override
    public int evaluate() {
        return 0;
    }

    @Override
    public int getActivePlayer() {
        return activePlayer;
    }

    @Override
    public void move(Move move) {
        this.field[move.getBitmapA().getBitmapIndex()] = move.getBitmapA();
        this.field[move.getBitmapB().getBitmapIndex()] = move.getBitmapB();
        this.moves.add(move);
        this.activePlayer = -activePlayer;
    }

    @Override
    public void undoMove() {
        this.activePlayer = -activePlayer;
        Move m = moves.pop();
        Move prev = moves.get(moves.size()-1);
        this.field[m.getBitmapA().getBitmapIndex()] = pre
    }

    @Override
    public void orderPossibleMoves(LinkedList moves) {

    }

    @Override
    public LinkedList getPossibleMoves() {
        return null;
    }
}
