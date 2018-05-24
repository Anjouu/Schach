package game;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by Anwender on 24.05.2018.
 */
public class Game{

    private Bitmap field;
    private byte activePlayer;

    private Stack<Move> moves = new Stack<>();



    private static final int[][] SRINGER_DIRECTIONS = new int[][]{{1,2},{2,1},{1,-2},{-2,1},{-1,2},{2,-1},{-1,-2},{-2,-1}};

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

        return v;
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
            for(byte j=0; j<8; j++){
                if (field.getValue(i, j) == activePlayer){ // Bauern
                    if ((j == 1 && activePlayer == 1 || j == 6 && activePlayer == -1)&&(field.getValue(i, j + 2*activePlayer) == 0)&&(field.getValue(i, j + activePlayer) == 0)){ // Bauer zwei nach vorne
                        moves.add(new Move(i,j,activePlayer,i,(byte)(j + 2*activePlayer),(byte) 0));
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

                if (field.getValue(i, j) == activePlayer * 3){ // Springer
                    for(int[] ar:SRINGER_DIRECTIONS){
                        if(this.field.isValid(i + ar[0], j + ar[1]) && this.field.getValue(i + ar[0],j + ar[1]) * activePlayer <= 0){
                            moves.add(new Move(i,j,(byte)(activePlayer * 3), (byte)(i + ar[0]), (byte)(j + ar[1]), this.field.getValue(i + ar[0],j + ar[1])));
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
