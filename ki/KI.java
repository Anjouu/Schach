package ki;

import game.Game;
import game.Move;

import java.util.LinkedList;

public abstract class KI {


    private static Move bestMove;
    private static int max_depth;

    private static Game g;

    public static Move getBestMove(Game g, int depth){
        bestMove = null;
        KI.max_depth = depth;
        KI.g = g;

        if(g.getActivePlayer() == 1){
            max(depth, -10000000,100000000);
        }else{
            min(depth, 10000000,-100000000);
        }
        return bestMove;
    }


    static int max(int tiefe, int alpha, int beta) {
        LinkedList<Move> moves = g.getPossibleMoves();
        if (tiefe == 0 || moves.size() == 0)
        return g.evaluate();
        int maxWert = alpha;
        for(Move m:moves){
            g.move(m);
            int wert = min(tiefe-1,
                    maxWert, beta);
            g.undoMove();
            if (wert > maxWert) {
                maxWert = wert;
                if (maxWert >= beta)
                    break;
                if (tiefe == max_depth)
                    bestMove = m;
            }
        }
        return maxWert;
    }

    static int min(int tiefe, int alpha, int beta) {
        LinkedList<Move> moves = g.getPossibleMoves();
        if (tiefe == 0 || moves.size() == 0)
            return g.evaluate();
        int minwert = beta;
        for(Move m:moves){
            g.move(m);
            int wert = max(tiefe-1,
                    alpha, minwert);
            g.undoMove();
            if (wert < minwert) {
                minwert = wert;
                if (minwert <= alpha    )
                    break;
                if (tiefe == max_depth)
                    bestMove = m;
            }
        }
        return minwert;
    }


}
