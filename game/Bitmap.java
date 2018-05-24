package game;

/**
 * Created by Anwender on 24.05.2018.
 */
public class Bitmap implements BitmapInterface<Bitmap> {


    private boolean[][] bitmap;
    private int bitmap_index;

    public Bitmap(int index) {
        bitmap = new boolean[8][8];
        this.bitmap_index = index;
    }

    @Override
    public boolean isValid(int x, int y) {
        return (x >= 0 && x < 8 && y >= 0 && y < 8);
    }

    @Override
    public boolean getValue(int x, int y) {
        return bitmap[x][y];
    }

    @Override
    public void move(int x1, int y1, int x2, int y2) {
        bitmap[x1][y1] = false;
        bitmap[x2][y2] = true;
    }

    @Override
    public int getBitmapIndex() {
        return this.bitmap_index;
    }

    @Override
    public void setBitmapIndex(int index) {
        this.bitmap_index = index;
    }

    @Override
    public Bitmap copy() {
        Bitmap b = new Bitmap(this.bitmap_index);
        for(int i = 0; i < 8; i++){
            for(int n = 0; n < 8; n++){
                b.bitmap[i][n] = bitmap[i][n];
            }
        }
        return b;
    }
}
