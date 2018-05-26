package game;

import evaluator.EricEv;

import evaluator.FinnEv;

import java.util.BitSet;

/**
 * Created by Anwender on 24.05.2018.
 */
public class Bitmap{


    private byte[][] bitmap;
    private BitSet[][][] bitSets;

    public Bitmap() {
        bitmap = new byte[8][8];
        bitSets = new BitSet[8][8][12];
        for(int i = 0; i < 8; i++){
            for(int n = 0; n < 8; n++){
                for(int j = 0; j < 12; j++){
                    bitSets[i][n][j] = randomBitSet(64);
                }
            }
        }
    }

    public boolean isValid(int x, int y) {
        return (x >= 0 && x < 8 && y >= 0 && y < 8);
    }

    public byte getValue(int x, int y) {
        return bitmap[x][y];
    }

    public void setValue(int x, int y, byte val){
        bitmap[x][y] = val;
    }

    public Bitmap copy() {
        Bitmap b = new Bitmap();
        for(int i = 0; i < 8; i++){
            for(int n = 0; n < 8; n++){
                b.bitmap[i][n] = bitmap[i][n];
            }
        }
        return b;
    }

    public BitSet randomBitSet(int size){
        BitSet out = new BitSet(size);
        for(int i = 0; i < size; i++){
            out.set(i, Math.random() > 0.5 ? true:false);
        }
        return out;
    }

    public BitSet hash(){
        BitSet out = new BitSet(64);
        for(int i = 0; i < 8; i++){
            for(int n = 0; n < 8; n++){
                if(bitmap[i][n] > 0){
                    out.xor(bitSets[i][n][(int)bitmap[i][n]]);
                }else if (bitmap[i][n] < 0){
                    out.xor(bitSets[i][n][12+(int)bitmap[i][n]]);
                }
            }
        }
        return out;
    }

    public static void printBitSet(BitSet bitSet){
        System.out.print(bitSet.size() + " ");
        for(int i = 0; i < bitSet.size(); i++){
            System.out.print(bitSet.get(i) ? 1:0);
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        Game g = new Game(new FinnEv());

        printBitSet(g.getField().hash());
    }
}
