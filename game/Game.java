package game;

import java.util.LinkedList;

/**
 * Created by Anwender on 24.05.2018.
 */
public class Game implements GameInterface<Move>{
    @Override
    public int evaluate() {
        return 0;
    }

    @Override
    public int getActivePlayer() {
        return 0;
    }

    @Override
    public void move(Move move) {

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
