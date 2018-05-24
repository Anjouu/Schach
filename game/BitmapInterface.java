package game;

public interface BitmapInterface<B extends BitmapInterface<B>> {

    public boolean isValid(int x, int y);

    /**
     * gibt den boolschen wert an einer stelle an
     * @param x
     * @param y
     * @return
     */
    public boolean getValue(int x, int y);


    /**
     * verschiebt einen "true" wert von x1,y1 zu x2,y2
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public void move(int x1, int y1, int x2, int y2);

    /**
     * returnt den index der bitmap
     * (ob für bauer, könig, dame etc.
     * @return
     */
    public int getBitmapIndex();

    public void setBitmapIndex(int index);

    /**
     * erstellt eine neue bitmap mit dem gleichen inhalt
     * @return
     */
    public B copy();

}
