package game;

/**
 * Created by Anwender on 24.05.2018.
 */
public class Bitmap implements BitmapInterface<Bitmap> {
    @Override
    public boolean isValid(int x, int y) {
        return false;
    }

    @Override
    public boolean getValue(int x, int y) {
        return false;
    }

    @Override
    public void move(int x1, int y1, int x2, int y2) {

    }

    @Override
    public int getBitmapIndex() {
        return 0;
    }

    @Override
    public void setBitmapIndex(int index) {

    }

    @Override
    public Bitmap copy() {
        return null;
    }
}
