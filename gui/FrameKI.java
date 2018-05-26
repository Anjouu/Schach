package gui;

import evaluator.FinnEv;
import game.Game;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Luecx on 08.08.2017.
 */
public class FrameKI extends JFrame {

    private KIPanel gamePanel;

    public FrameKI() {
        super();

        gamePanel = new KIPanel(new Game(new FinnEv()));

        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setSize(800,800);
        this.add(gamePanel);


        this.setVisible(true);
    }

    public static void main(String[] args) {
        FrameKI f = new FrameKI();
    }

}
