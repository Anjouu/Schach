package gui;

import game.Game;
import game.Move;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Luecx on 08.08.2017.
 */
public class GamePanel extends JPanel{

    private JButton[][] buttons = new JButton[8][8];

    private Game g;

    public GamePanel(Game g) {
        super();

        this.g = g;
        this.setLayout(new GridLayout(8,8));

        for(int i = 0; i < 8; i++) {
            for(int n = 0; n < 8; n++) {
                JButton b = new JButton();

                b.setLayout(null);
                b.setBorder(new LineBorder(Color.black, 2));
                b.setFocusPainted(false);
                b.setOpaque(true);


                final int x = i;
                final int y = n;
                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        click(x,y);
                    }
                });

                this.add(b);
                buttons[x][y] = b;
            }
        }
        resetBackground();
    }

    private void resetBackground(){
        for(int i = 0; i < 8; i++) {
            for(int n = 0; n < 8; n++) {
                buttons[i][n].setBackground((i % 2 == 1 & n % 2 == 0 || i % 2 == 0 & n % 2 == 1) ? Color.gray:Color.white);
            }
        }
    }

    public void updateBackgrounds() {
        for(int i = 0; i < 8; i++) {
            for(int n = 0; n < 8; n++) {
                buttons[i][n].setBackground((i % 2 == 1 && n % 2 == 0 || i % 2 == 0 && n % 2 == 1 ? color_black:color_white)) ;
                //buttons[i][n].setIcon(getIcon(v));
                buttons[i][n].setText(g.getField().getValue(i,n) + "");
            }
        }
        if(selected != -1) {
            for(Move z:g.getPossibleMoves()){
                if(z.von == selected) {
                    if(b.field[z.zu] != 0) {
                        buttons[z.zu].setBackground(color_takeable);
                    }else{
                        buttons[z.zu].setBackground(color_available);
                    }
                }
            }
            buttons[selected].setBackground(color_selected);
        }
    }

    private int selected = -1;

    private void click(int v) {
        if(selected == -1) {
            if(b.field[v] * b.currentPlayer > 0) {
                selected = v;
            }
        }else{
            if(v == selected) {
                selected = -1;
            }
            else if(b.field[v] * b.currentPlayer > 0) {
                selected = v;
            }else{
                for(Zug z: b.availableMoves(b.currentPlayer,false)){
                    if(z.zu == v && z.von == selected) {
                        b.processMove(z);
                        Zug bester = b.getBestMove(-1, 5);
                        if(bester == null) System.exit(-1);
                        b.processMove(bester);

                        selected = -1;
                        break;
                    }
                }
            }
        }
        updateBackgrounds();
    }
}
