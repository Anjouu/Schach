package evaluator;

import game.Game;

import java.math.BigInteger;

public class FinnEv implements Evaluator {

    public static final int PAWN_FACTOR = 3;

    public static final int[][] PAWN_VALUES = new int[][]{
            {0,0,0,0,0,0,0,0},
            {20,20,20,20,20,20,20,20},
            {10,10,10,10,10,10,10,10},
            {0,5,5,10,10,5,5,0},
            {0,0,2,7,7,2,0,0},
            {0,0,1,2,2,1,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
    };

    public static final int[][] BISHOP_VALUES = new int[][]{
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,10,10,10,10,10,10,0},
            {0,35,10,10,10,10,35,0},
            {25,0,0,25,25,0,0,25},
            {0,20,0,25,25,0,20,0},
            {0,0,0,25,25,0,0,0},
            {0,0,0,0,0,0,0,0},
    };

    public static final int[][] ROOK_VALUES = new int[][]{
            {60,60,60,60,60,60,60,60},
            {60,60,60,60,60,60,60,60},
            {20,20,20,20,20,20,20,20},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0}
    };

    public static final int[][] KNIGHT_VALUES = new int[][]{
            {0,0,0,0,0,0,0,0},
            {0,0,60,60,60,60,0,0},
            {0,10,10,50,50,10,10,0},
            {0,0,10,30,30,10,0,0},
            {0,0,0,25,25,0,0,0},
            {0,0,30,14,14,30,0,0},
            {0,0,30,14,14,30,0,0},
            {0,0,0,0,0,0,0,0},
    };

    public static final int[] EVALUATE_PRICE = new int[]{0,100*2,824*2,521*2,572*2,1710*2,10000};
    public static final int[][][] POSITION_PRICE = new int[][][]{PAWN_VALUES, ROOK_VALUES, KNIGHT_VALUES, BISHOP_VALUES};


    @Override
    public int evaluate(Game g, int index) {

        int ev = 0;
        for(int i = 0; i < 8; i++){
            for(int n = 0; n < 8; n++){

                int v = ((g.getField().getValue(i,n)));
                int b = v > 0 ? 1:-1;

                if(Math.abs(v) == 1){
                    for(int[] ar: new int[][]{{1,1},{-1,1},{1,-1},{-1,-1}}){
                        if(i + ar[0] >= 0 && i + ar[0] < 8 && n + ar[1] >= 0 && n + ar[1] < 8){
                            if(g.getField().getValue(i + ar[0], n + ar[1]) * v == 1){
                                ev += 4;
                            }
                        }

                    }
                }

                if(v != 0){
                    ev += b * EVALUATE_PRICE[Math.abs(v)];
                    if(Math.abs(v) < 5){

                        if(v > 0){
                            ev += (Math.abs(v) == 1 ? PAWN_FACTOR: 1) * (b * POSITION_PRICE[Math.abs(v)-1][7-n][i]);
                        }else{
                            ev += (Math.abs(v) == 1 ? PAWN_FACTOR: 1) * (b * POSITION_PRICE[Math.abs(v)-1][n][i]);
                        }
                    }else{
                        if(Math.abs(v) == 5){
                            if(v > 0){
                                ev += (0.3) * (b * POSITION_PRICE[1][7-n][i] + POSITION_PRICE[3][7-n][i]);
                            }else{
                                ev += (0.3) * (b *  POSITION_PRICE[1][n][i] + POSITION_PRICE[3][n][i]);
                            }
                        }
                    }
                }
            }
        }


        return ev;
    }
}
