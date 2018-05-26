package evaluator;

import game.Bitmap;
import game.Game;

public class EricEv implements Evaluator {

    private Game game;
    private Bitmap field;
    private int activePlayer;
    private int totalScore;

    private static int BAUERN_VALUE = 24;
    private static int TURM_VALUE = 75;
    private static int SPRINGER_VALUE = 25;
    private static int LAEUFER_VALUE = 35;
    private static int DAMEN_VALUE = 130;
    private static int KOENIG_VALUE = 100000;

    static final void print_bits(int[][] number) {
        for (int i = 7; i >= 0; i--) {
            for (int n = 0; n < 8; n++) {
                //System.out.print(antiDiagonalIndex(squareIndex(i,n)) + " ");
                System.out.print(number[n][i] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static int[][] swap(int[][] field){
        int[][] swappedField = new int[8][8];
        for (int i = 0; i<8; i++){
            for (int j = 0; j<8; j++){
                swappedField[7 - j][7 - i] = field[j][i];
            }
        }
        return swappedField;
    }



    private static int[][] BAUERN_POSITIONING = new int[][] {
            {0,2,2,3,3,2,1,-10},
            {0,2,4,7,6,4,2,-10},
            {0,2,6,9,8,6,4,-10},
            {0,2,8,12,10,8,6,-10},
            {0,2,8,12,10,8,6,-10},
            {0,2,6,9,8,6,4,-10},
            {0,2,4,7,6,4,2,-10},
            {0,2,2,3,3,2,1,-10}
    };

    public static int countFigure(int figure, Bitmap field, int activePlayer){
        int number = 0;
        for(byte i = 0; i<8; i++) {
            for (byte j = 0; j < 8; j++) {
                if (field.getValue(i, j) == figure * activePlayer){
                    number += 1;
                }
            }
        }
        return number;
    }

    public int material(){
        int score = 0;
        for(int i = 0; i<8; i++){
            for(int j = 0; j<8; j++){
                switch (field.getValue(i, j) * activePlayer){
                    case 1: score += BAUERN_VALUE; break;
                    case -1: score -= BAUERN_VALUE;  break;
                    case 2: score += TURM_VALUE; break;
                    case -2: score -= TURM_VALUE; break;
                    case 3: score += SPRINGER_VALUE; break;
                    case -3: score -= SPRINGER_VALUE; break;
                    case 4: score += LAEUFER_VALUE; break;
                    case -4: score -= LAEUFER_VALUE; break;
                    case 5: score += DAMEN_VALUE; break;
                    case -5: score -= DAMEN_VALUE; break;
                    case 6: score += KOENIG_VALUE; break;
                    case -6: score -= KOENIG_VALUE;  break;
                }
            }
        }
        return score;
    }

    public int bauernScore(){
        int score = 0;
        for (int i = 0; i<8; i++){
            for (int j = 0; j<8; j++){

            }
        }

        return score;
    }

    @Override
    public int evaluate(Game g, int index) {
        this.game = g;
        this.activePlayer = index;
        this.field = g.getField();
        this.totalScore = 0;

        this.totalScore += material();
        this.totalScore += bauernScore();

        return this.totalScore * activePlayer;
    }

    public static void main(String[] args){
        EricEv test = new EricEv();
        print_bits(test.BAUERN_POSITIONING);
        print_bits(swap(test.BAUERN_POSITIONING));
    }
}
