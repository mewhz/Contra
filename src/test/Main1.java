package test;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class Main1 {

    public static void main(final String[] args) {
        Icon player = new ImageIcon("img/player/PL/player0.png");
        JLabel lplayer = new JLabel(player);
        JPanel pplayer = new JPanel();
        JFrame frame = new JFrame("Test");

        pplayer.add(lplayer);
        frame.add(pplayer);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(Color.red);
        frame.setPreferredSize(new Dimension(400, 300));
        frame.pack();
        frame.setVisible(true);
    }

    private Main1() {
    }
}
