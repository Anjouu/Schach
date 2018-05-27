package evaluator;

import game.Bitmap;
import game.Game;

public class EricEv implements Evaluator {

    private Game game;
    private Bitmap field;
    private int activePlayer;
    private int totalScore;

    private static int BAUERN_VALUE = 45;
    private static int TURM_VALUE = 160;
    private static int SPRINGER_VALUE = 95;
    private static int LAEUFER_VALUE = 105;
    private static int DAMEN_VALUE = 285;
    private static int KOENIG_VALUE = 100000;

    private static void print_bits(int[][] number) {
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

    private static int[][] TURM_POSITIONING = new int[][] {
            {-20,-15,0,7,7,0,-15,-20},
            {-10,-1,5,10,10,5,-1,-10},
            {-5,0,7,13,13,7,0,-5},
            {0,4,10,18,18,10,4,0},
            {0,4,10,18,18,10,4,0},
            {-5,0,7,13,13,7,0,-5},
            {-10,-1,5,10,10,5,-1,-10},
            {-20,-15,0,7,7,0,-15,-20}
    };

    private static int[][] SPRINGER_POSITIONING = new int[][] {
            {-3,-3,-3,-3,-3,-3,-3,-3},
            {-3,4,4,4,4,4,4,-3},
            {-3,4,9,9,9,9,4,-3},
            {-3,4,9,13,13,9,4,-3},
            {-3,4,9,13,13,9,4,-3},
            {-3,4,9,9,9,9,4,-3},
            {-3,4,4,4,4,4,4,-3},
            {-3,-3,-3,-3,-3,-3,-3,-3}
    };

    private static int[][] LAEUFER_POSITIONING = new int[][] {
            {0,2,2,3,3,2,1,-10},
            {0,2,4,7,6,4,2,-10},
            {0,2,6,9,8,6,4,-10},
            {0,2,8,12,10,8,6,-10},
            {0,2,8,12,10,8,6,-10},
            {0,2,6,9,8,6,4,-10},
            {0,2,4,7,6,4,2,-10},
            {0,2,2,3,3,2,1,-10}
    };

    private static int[][] DAMEN_POSITIONING = new int[][] {
            {0,2,2,3,3,2,1,-10},
            {0,2,4,7,6,4,2,-10},
            {0,2,6,9,8,6,4,-10},
            {0,2,8,12,10,8,6,-10},
            {0,2,8,12,10,8,6,-10},
            {0,2,6,9,8,6,4,-10},
            {0,2,4,7,6,4,2,-10},
            {0,2,2,3,3,2,1,-10}
    };

    private static int[][] KOENIG_POSITIONING = new int[][] {
            {40,30,20,20,20,20,30,40},
            {30,15,15,15,15,15,15,30},
            {20,15,-2,-2,-2,-2,15,20},
            {20,15,-2,-10,-10,-2,15,20},
            {20,15,-2,-10,-10,-2,15,20},
            {20,15,-2,-2,-2,-2,15,20},
            {30,15,15,15,15,15,15,30},
            {40,30,20,20,20,20,30,40},
    };

    private static int[][][] POSITIONINGS = new int[][][]{
            BAUERN_POSITIONING,
            TURM_POSITIONING,
            SPRINGER_POSITIONING,
            LAEUFER_POSITIONING,
            DAMEN_POSITIONING,
            KOENIG_POSITIONING
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

    public int PositioningScore(int figure){
        int score = 0;
        for (int i = 0; i<8; i++){
            for (int j = 0; j<8; j++){
                if (field.getValue(i, j) == figure * activePlayer){
                    score += POSITIONINGS[figure-1][i][j] * activePlayer;
                    score -= swap(POSITIONINGS[figure-1])[i][j] * activePlayer;
                }
            }
        }

        return score;
    }

    public int specialities(){
        int score = 0;
        for(int figure:new int[]{2,3,4}){
            switch (countFigure(figure * activePlayer, this.field, this.activePlayer)){
                case 0: score -= 10; break;
                case 1: score -= 2; break;
                case 2: score += 15;
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
        for(int i=1; i <= 6; i++){
            this.totalScore += PositioningScore(i);
        }
        this.totalScore += specialities();

        return this.totalScore * activePlayer;
    }

    public static void main(String[] args){
        EricEv test = new EricEv();
        print_bits(test.TURM_POSITIONING);
        print_bits(swap(test.TURM_POSITIONING));
    }
}
