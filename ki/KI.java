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

        g.hashes = 0;
        g.calculations = 0;


        minimax(g.getActivePlayer(), depth, -100000000,10000000);
        System.out.println("hashes:" + g.hashes + "   calculations:"+ g.calculations);
        return bestMove;
    }

    static int minimax(int spieler, int tiefe, int alpha, int beta){
        LinkedList<Move> moves = g.getPossibleMoves();
        g.orderPossibleMoves(moves);
        if (tiefe == 0 || moves.size() == 0 || g.gameOver()){
            return g.evaluate() * spieler;
        }
        int max = alpha;
        for(Move m:moves){
            g.move(m);
            int wert = - (int)(1 * minimax(-spieler, tiefe-1,
                    -beta, -max));
            g.undoMove();
            if (wert > max) {
                max = wert;
                if (max >= beta){
                    break;
                }
                if (tiefe == max_depth)
                    bestMove = m;
            }
        }
        return max;
    }


}
