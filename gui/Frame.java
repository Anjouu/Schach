package gui;

import evaluator.EricEv;
import evaluator.FinnEv;
import game.Game;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Luecx on 08.08.2017.
 */
public class Frame extends JFrame implements KeyListener {

    private GamePanel gamePanel;

    public Frame() {
        super();

        gamePanel = new GamePanel(new Game(new FinnEv()), this);

        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setSize(800,800);
        this.add(gamePanel);

        this.addKeyListener(this);
        this.gamePanel.addKeyListener(this);

        this.setVisible(true);
    }

    public static void main(String[] args) {Frame f = new Frame();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        gamePanel.undo();
        System.out.println("awduhwd");

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
