package game;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by Anwender on 24.05.2018.
 */
public class Game implements GameInterface<Move>{

    Bitmap[] field = new Bitmap[12];
    int activePlayer;

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
        this.bitmaps[move.getBitmapA().getBitmapIndex()] = move.getBitmapA();
        this.bitmaps[move.getBitmapB().getBitmapIndex()] = move.getBitmapB();
        this.moves.add(move);
        this.currentPlayer = -currentPlayer;
    }

    @Override
    public void undoMove() {

    }

    @Override
    public void orderPossibleMoves(LinkedList moves) {

    }

    @Override
    public LinkedList getPossibleMoves() {
        return null;
    }
}
