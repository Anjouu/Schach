package gui;

import javax.swing.*;

/**
 * Created by Luecx on 08.08.2017.
 */
public class Frame extends JFrame{

    public Frame() {
        super();

        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setSize(800,800);
        this.add(new GamePanel());

        this.setVisible(true);
    }

    public static void main(String[] args) {
        Frame f = new Frame();
    }

}
