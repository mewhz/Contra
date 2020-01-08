package test;

import javax.swing.*;
import java.awt.*;

public class ColorTest extends JFrame {
    private Icon player = new ImageIcon("img/player/PL/player0.png");
    private JLabel lplayer = new JLabel(player);
    private JPanel pplayer = new JPanel();

    public ColorTest(){
        this.setSize(800,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pplayer.add(lplayer);
        this.add(pplayer);
        this.getContentPane().setBackground(Color.red);
        this.setPreferredSize(new Dimension(400, 300));
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new ColorTest();
    }
}
