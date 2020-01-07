package test;

import javax.swing.*;

public class ImgTest extends JFrame {
    private JLabel jLabel = new JLabel();
    private Icon icon = new ImageIcon("img/title.gif");

    public ImgTest(){
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(800,600);
        this.setVisible(true);
        jLabel.setIcon(icon);
        jLabel.setLayout(null);
        jLabel.setBounds(100,100,500,500);
        this.add(jLabel);
    }

    public static void main(String[] args) {
        new ImgTest();
    }
}