package gui;

import evaluator.EricEv;
import evaluator.Evaluator;
import evaluator.FinnEv;
import game.Game;
import game.Move;
import ki.KI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * Created by Luecx on 08.08.2017.
 */
public class KIPanel extends JPanel{

    private JButton[][] buttons = new JButton[8][8];

    private Game g;

    public Color color_black = Color.gray;
    public Color color_white = Color.white;
    public Color color_selected = Color.green;
    public Color color_available = Color.pink;
    public Color color_takeable = Color.red;

    private static ImageIcon[][] icons = new ImageIcon[6][2];

    static {
        for(int i = 0; i < 6; i++) {
            for(int n = 0; n < 2; n++) {
                int a = i;
                if(a == 1 || a== 2){
                    a ++;
                }else if(a == 3){
                    a = 1;
                }
                icons[a][n] = new ImageIcon("src/gui/"+(i + 1)+""+(n +1) +".gif");
                icons[a][n].setImage( icons[a][n].getImage().getScaledInstance(80,80,Image.SCALE_DEFAULT));
            }
        }
    }

    private ImageIcon getIcon(int x, int y) {
        if(g.getField().getValue(x,y) == 0) return null;
        return icons[(int)Math.abs(g.getField().getValue(x,y)) - 1][g.getField().getValue(x,y) > 0 ? 0:1];
    }





    public KIPanel(Game g) {
        super();

        new KIMatch(g,new EricEv(), new FinnEv(), this);

        this.g = g;
        this.setLayout(new GridLayout(8,8));

        for(int i = 7; i >= 0; i--) {
            for(int n = 0; n < 8; n++) {
                JButton b = new JButton();

                b.setLayout(null);
                b.setBorder(new LineBorder(Color.black, 2));
                b.setFocusPainted(false);
                b.setOpaque(true);
                b.setFont(new Font("Arial", 1, 50));

                final int x = n;
                final int y = i;

                this.add(b);
                buttons[x][y] = b;
            }
        }
        resetBackground();
        updateBackgrounds();
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
                buttons[i][n].setIcon(getIcon(i,n));
            }
        }

    }


    class KIMatch extends Thread{
        Evaluator a;
        Evaluator b;

        Game game;
        KIPanel jPanel;

        public KIMatch(Game g, Evaluator a, Evaluator b, KIPanel panel) {
            this.a = a;
            this.b = b;
            this.game = g;
            this.jPanel = panel;
            this.start();
        }

        public void run(){

            while(g.gameOver() == false){
                try {
                    Thread.sleep(1000);
                    game.setEvaluator(a);
                    game.move(KI.getBestMove(game, 5));
                    jPanel.updateBackgrounds();

                    Thread.sleep(1000);
                    game.setEvaluator(b);
                    game.move(KI.getBestMove(game, 5));
                    jPanel.updateBackgrounds();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
