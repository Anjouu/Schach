package game;

/**
 * enthällt die bitmaps die verändert wurden.
 * können maximal zwei bitmaps sein.
 * jede bitmap hat einen bitmap index welche benutzt werden können wenn der zug rückgängig gemacht werden soll.
 * hier kann dann einfach die aktuelle bitmap ausgetauscht werden.
 * @param <B>
 */

public interface MoveInterface<B extends BitmapInterface> {






    public void setBitmapA(B b);

    public void setBitmapB(B b);


    public B getBitmapA();

    public B getBitmapB();





}
