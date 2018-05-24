package game;

/**
 * Created by Anwender on 24.05.2018.
 */
public class Move implements MoveInterface<Bitmap> {

    private Bitmap bitmapA;
    private Bitmap bitmapB;

    public Move(Bitmap bitmapA, Bitmap bitmapB) {
        this.bitmapA = bitmapA;
        this.bitmapB = bitmapB;
    }

    @Override
    public void setBitmapA(Bitmap bitmap) {
        this.bitmapA = bitmap;
    }

    @Override
    public void setBitmapB(Bitmap bitmap) {
        this.bitmapB = bitmap;
    }

    @Override
    public Bitmap getBitmapA() {
        return bitmapA;
    }

    @Override
    public Bitmap getBitmapB() {
        return bitmapB;
    }
}
