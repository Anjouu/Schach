package game;

/**
 * Created by Anwender on 24.05.2018.
 */
public class Bitmap{


    private byte[][] bitmap;

    public Bitmap() {
        bitmap = new byte[8][8];
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
}
