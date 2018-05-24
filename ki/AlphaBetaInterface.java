package ki;

import game.MoveInterface;

public interface AlphaBetaInterface<M extends MoveInterface> {

    public M getBestMove(int depth);

}
