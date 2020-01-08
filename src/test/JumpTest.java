package test;

import com.sun.javaws.jnl.JARUpdater;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class JumpTest extends JFrame implements KeyListener {
    private Icon playerimg;
    private JLabel playerlabel;
    private JPanel player;
    private int jumpi = 8;
    private int i = 0;
    private boolean jump = false;

    public JumpTest(){
        playerimg = new ImageIcon("img/player/PR/player0.png");
        playerlabel = new JLabel(playerimg);
        player = new JPanel();
        player.add(playerlabel);
        player.setOpaque(false);
        player.setBounds(100,100,playerimg.getIconWidth(),playerimg.getIconHeight());
        this.add(player);
        this.setLayout(null);
        this.setLocationRelativeTo(null);//使窗口在中央显示
        this.setSize(new Dimension(800,600));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);
        this.setVisible(true);
    }


    public static void main(String[] args) {
        new JumpTest();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //按键被按下
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_K){
//            for(int i = 0;i<jumpi;i++){
//                playerimg = new ImageIcon("img/player/PR/jump"+i+".png");
//                playerlabel.setIcon(playerimg);
//                player.setBounds(100,100,playerimg.getIconWidth(),playerimg.getIconHeight());
//                System.out.println(i);
//                this.revalidate();
//            }

            playerimg = new ImageIcon("img/title.gif");
            playerlabel.setIcon(playerimg);
            player.setBounds(100,100,playerimg.getIconWidth(),playerimg.getIconHeight());
            System.out.println("123");
            jump = true;
        }
//        playerimg = new ImageIcon("img/player/PR/player0.png");
//        playerlabel.setIcon(playerimg);
//        player.setBounds(100,100,playerimg.getIconWidth(),playerimg.getIconHeight());


        }

    //按键松开
    @Override
    public void keyReleased(KeyEvent e) {
        if(jump&&(e.getKeyCode()==KeyEvent.VK_K)){
            jump = false;
            playerimg = new ImageIcon("img/player/PR/player0.png");
            playerlabel.setIcon(playerimg);
            player.setBounds(100,100,playerimg.getIconWidth(),playerimg.getIconHeight());
        }
    }
}
