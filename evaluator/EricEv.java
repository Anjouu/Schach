package evaluator;

import game.Bitmap;
import game.Game;

public class EricEv implements Evaluator {

    private Game game;
    private Bitmap field;
    private int activePlayer;
    private int totalScore;

    private static int BAUERN_VALUE = 14;
    private static int TURM_VALUE = 90;
    private static int SPRINGER_VALUE = 25;
    private static int LAEUFER_VALUE = 35;
    private static int DAMEN_VALUE = 150;
    private static int KOENIG_VALUE = 10000;

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

        score += countFigure(1, this.field, this.activePlayer) * 2;
        for(int i = 0; i<8; i++){
            for(int j = 0; j<8; j++){
                if (field.getValue(i, j ) == 1){
                    if (activePlayer == 1){
                        score += j;
                    }
                    else{
                        score -= j;
                    }
                }
                if (field.getValue(i, j) == -1){
                    if (activePlayer == 1){
                        score += j;
                    }
                    else{
                        score -= j;
                    }
                }
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
}
