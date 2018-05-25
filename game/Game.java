package game;

import java.util.*;

/**
 * Created by Anwender on 24.05.2018.
 */
public class Game{

    private Bitmap field;
    private byte activePlayer;

    private Stack<Move> moves = new Stack<>();
    private HashMap<BitSet, Integer> evaluations = new HashMap<>();

    public int hashes;
    public int calculations;

    private static final int[][] SRINGER_DIRECTIONS = new int[][]{{1,2},{2,1},{1,-2},{-2,1},{-1,2},{2,-1},{-1,-2},{-2,-1}};

    private static final int[] TURM_DIRECTIONS = new int[]{1,-1};
    private static final int[][] LAEUFER_DIRECTIONS = new int[][]{{1,1},{1,-1},{-1,1},{-1,-1}};

    private static final int[] EVALUATE_PRICE = new int[]{0,100,824,521,572,1710,10000};
    private static final int EVALUATE_ROOK_ATTACK_KING_FILE = 51;
    private static final int EVALUATE_ROOK_7TH_RANK = 30;



    public Game(){
        reset();
    }

    public void reset(){
        field = new Bitmap();
        activePlayer = 1;
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

        BitSet hash = this.field.hash();
        if(evaluations.containsKey(hash)){
            hashes ++;
            return evaluations.get(hash);
        }else{
            int v = 0;

            for(int i = 0; i < 8; i++){
                for(int n = 0; n <8; n++){
                    if(field.getValue(i,n) != 0){
                        v += EVALUATE_PRICE[Math.abs(field.getValue(i,n))] * (field.getValue(i,n) > 0 ? 1:-1);

                        if(Math.abs(v) == 2){
                            if(v * n == 6 || v * n == -1) v+=1;
                        }
                    }
                }
            }

            calculations ++;
            evaluations.put(hash, v);
            return v;
        }

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

        for(byte i=0; i<8; i++){
            for(byte j=0; j<8; j++) {
                if (field.getValue(i, j) == activePlayer) { // Bauern
                    if ((j == 1 && activePlayer == 1 || j == 6 && activePlayer == -1) && (field.getValue(i, j + 2 * activePlayer) == 0) && (field.getValue(i, j + activePlayer) == 0)) { // Bauer zwei nach vorne
                        moves.add(new Move(i, j, activePlayer, i, (byte) (j + 2 * activePlayer), (byte) 0));
                    }
                    if (j != 0 && j != 7) {
                        if (field.getValue(i, j + activePlayer) == 0) { // Bauer nach vorne ohne Schlagen
                            moves.add(new Move(i, j, activePlayer, i, (byte) (j + activePlayer), (byte) 0));
                        }

                        if (i != 0) {
                            if (field.getValue(i - 1, j + activePlayer) * activePlayer < 0) { // Bauer schlägt nach links
                                moves.add(new Move(i, j, activePlayer, (byte) (i - 1), (byte) (j + activePlayer), field.getValue(i - 1, j + activePlayer)));
                            }
                        }
                        if (i != 7) {
                            if (field.getValue(i + 1, j + activePlayer) * activePlayer < 0) { // Bauer schlägt nach rechts
                                moves.add(new Move(i, j, activePlayer, (byte) (i + 1), (byte) (j + activePlayer), field.getValue(i + 1, j + activePlayer)));
                            }
                        }
                    }
                }

                if (field.getValue(i, j) == activePlayer * 3) { // Springer
                    for (int[] ar : SRINGER_DIRECTIONS) {
                        if (this.field.isValid(i + ar[0], j + ar[1]) && this.field.getValue(i + ar[0], j + ar[1]) * activePlayer <= 0) {
                            moves.add(new Move(i, j, (byte) (activePlayer * 3), (byte) (i + ar[0]), (byte) (j + ar[1]), this.field.getValue(i + ar[0], j + ar[1])));
                        }
                    }
                }

                if (field.getValue(i, j) == activePlayer * 2) { // Türme
                    for (int dir : TURM_DIRECTIONS) {
                        int k = dir;
                        while (i + k < 8 && i + k >= 0 && field.getValue(i + k, j) == 0) { // Turmbewegung auf x-Achse
                            moves.add(new Move(i, j, (byte) (activePlayer * 2), (byte) (i + k), j, (byte) 0));
                            k += dir;
                        }
                        if (i + k < 8 && i + k >= 0 && field.getValue(i + k, j) * activePlayer < 0) { // Turm schlägt auf x-Achse
                            moves.add(new Move(i, j, (byte) (activePlayer * 2), (byte) (i + k), j, field.getValue(i + k, j)));
                        }
                        k = dir;
                        while (j + k < 8 && j + k >= 0 && field.getValue(i, j + k) == 0) { // Turmbewegung auf y-Achse
                            moves.add(new Move(i, j, (byte) (activePlayer * 2), i, (byte) (j + k), (byte) 0));
                            k += dir;
                        }
                        if (j + k < 8 && j + k >= 0 && field.getValue(i, j + k) * activePlayer < 0) { // Turm schlägt auf y-Achse
                            moves.add(new Move(i, j, (byte) (activePlayer * 2), i, (byte) (j + k), field.getValue(i, j + k)));
                        }
                        k = dir;
                    }
                }

                if (field.getValue(i, j) == activePlayer * 4) { // Läufer
                    for (int[] dir : LAEUFER_DIRECTIONS) {
                        int kx = dir[0];
                        int ky = dir[1];
                        while (i + kx < 8 && i + kx >= 0 && j + ky < 8 && j + ky >= 0 && field.getValue(i + kx, j + ky) == 0){
                            moves.add(new Move(i, j,(byte)(activePlayer * 4), (byte) (i + kx), (byte) (j + ky), (byte) 0));
                            kx += dir[0];
                            ky += dir[1];
                        }
                        if (i + kx < 8 && i + kx >= 0 && j + ky < 8 && j + ky >= 0 && field.getValue(i + kx, j + ky) * activePlayer < 0){
                            moves.add(new Move(i, j,(byte)(activePlayer * 4), (byte) (i + kx), (byte) (j + ky), field.getValue(i + kx, j + ky)));
                        }
                    }
                }

                if (field.getValue(i, j) == activePlayer * 5) { // Dame
                    for (int dir : TURM_DIRECTIONS) {
                        int k = dir;
                        while (i + k < 8 && i + k >= 0 && field.getValue(i + k, j) == 0) { // Turmbewegung auf x-Achse
                            moves.add(new Move(i, j, (byte) (activePlayer * 5), (byte) (i + k), j, (byte) 0));
                            k += dir;
                        }
                        if (i + k < 8 && i + k >= 0 && field.getValue(i + k, j) * activePlayer < 0) { // Turm schlägt auf x-Achse
                            moves.add(new Move(i, j, (byte) (activePlayer * 5), (byte) (i + k), j, field.getValue(i + k, j)));
                        }
                        k = dir;
                        while (j + k < 8 && j + k >= 0 && field.getValue(i, j + k) == 0) { // Turmbewegung auf y-Achse
                            moves.add(new Move(i, j, (byte) (activePlayer * 5), i, (byte) (j + k), (byte) 0));
                            k += dir;
                        }
                        if (j + k < 8 && j + k >= 0 && field.getValue(i, j + k) * activePlayer < 0) { // Turm schlägt auf y-Achse
                            moves.add(new Move(i, j, (byte) (activePlayer * 5), i, (byte) (j + k), field.getValue(i, j + k)));
                        }
                        k = dir;
                    }
                    for (int[] dir : LAEUFER_DIRECTIONS) {
                        int kx = dir[0];
                        int ky = dir[1];
                        while (i + kx < 8 && i + kx >= 0 && j + ky < 8 && j + ky >= 0 && field.getValue(i + kx, j + ky) == 0){
                            moves.add(new Move(i, j,(byte)(activePlayer * 5), (byte) (i + kx), (byte) (j + ky), (byte) 0));
                            kx += dir[0];
                            ky += dir[1];
                        }
                        if (i + kx < 8 && i + kx >= 0 && j + ky < 8 && j + ky >= 0 && field.getValue(i + kx, j + ky) * activePlayer < 0){
                            moves.add(new Move(i, j,(byte)(activePlayer * 5), (byte) (i + kx), (byte) (j + ky), field.getValue(i + kx, j + ky)));
                        }
                    }
                }
                if (field.getValue(i, j) == activePlayer * 6){
                    for (int dir : TURM_DIRECTIONS){
                        if(i + dir < 8 && i + dir >= 0 && field.getValue(i + dir, j) * activePlayer <= 0){
                            moves.add(new Move(i, j,(byte)(activePlayer * 6), (byte) (i + dir), j, field.getValue(i + dir, j)));
                        }
                        if(j + dir < 8 && j + dir>= 0 && field.getValue(i, j + dir) * activePlayer <= 0){
                            moves.add(new Move(i, j,(byte)(activePlayer * 6), i, (byte)(j + dir), field.getValue(i , j + dir)));
                        }
                    }
                    for (int[] dir : LAEUFER_DIRECTIONS){
                        if (i + dir[0] < 8 && i + dir[0] >= 0 && j + dir[1] < 8 && j + dir[1] >= 0 && field.getValue(i + dir[0], j + dir[1]) * activePlayer <= 0){
                            moves.add(new Move(i, j, (byte)(activePlayer * 6), (byte)(i + dir[0]),(byte)(j + dir[1]), field.getValue(i + dir[0], j + dir[1])));
                        }
                    }
                }
            }
        }

        return moves;
    }


    @Override
    public String toString() {
        String s = "";
        for(int i = 0; i < 8; i++){

            for(int n = 0; n < 8; n++){
                s+=this.field.getValue(n,i);
            }

            s+="\n";
        }
        return s;
    }

    public Bitmap getField() {
        return field;
    }

    public Stack<Move> getMoves() {
        return moves;
    }
}
