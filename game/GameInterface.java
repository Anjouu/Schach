package game;

import java.util.ArrayList;
import java.util.LinkedList;

public interface GameInterface {

    /**
     * bewertet die stellung
     * positiv ist besser für spieler 1
     * negativ ist besser für spieler -1
     * @return
     */
    public int evaluate();

    /**
     * gibt den aktuellen spieler wieder.
     * entweder 1 oder -1
     */
    public int getActivePlayer();

    /**
     * führt einen zug aus
     * @param move
     */
    public void move(Move move);

    /**
     * selbsterklärend.
     * tipp: benutze einen Stack von Zügen :)
     *
     */
    public void undoMove();

    /**
     * sortiert die Züge nach relevanz
     * Wichtig damit die suche effizient wird.
     * @param moves
     */
    public void orderPossibleMoves(LinkedList<Move> moves);

    /**
     * gibt die möglichen Züge für den aktuellen spieler wieder
     * @return
     */
    public LinkedList<Move> getPossibleMoves();

}
