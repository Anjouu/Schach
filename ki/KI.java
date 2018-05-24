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

        minimax(g.getActivePlayer(), depth, -1000000,1000000);
        return bestMove;
    }

    static int minimax(int spieler, int tiefe, int alpha, int beta){
        LinkedList<Move> moves = g.getPossibleMoves();
        if (tiefe == 0 || moves.size() == 0){
            return g.evaluate() * spieler;
        }
        int max = alpha;
        for(Move m:moves){
            g.move(m);
            int wert = -minimax(-spieler, tiefe-1,
                    -beta, -max);
            g.undoMove();
            if (wert > max) {
                max = wert;
                if (max >= beta)
                    break;
                if (tiefe == max_depth)
                    bestMove = m;
            }
        }
        return max;
    }


}
