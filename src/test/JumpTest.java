package test;

import com.sun.javaws.jnl.JARUpdater;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.omg.CORBA.INTERNAL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class JumpTest extends JFrame implements KeyListener {
    private Icon playerimg;
    private JLabel playerlabel;
    private JPanel player;
    private int jumpi = 8;
    private int i = 0;
    private boolean jump = false;
    private int k = 0;

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


        }



    //按键松开
    @Override
    public void keyReleased(KeyEvent e) {
//        if(jump&&(e.getKeyCode()==KeyEvent.VK_K)){
//            jump = false;
//            playerimg = new ImageIcon("img/player/PR/player0.png");
//            playerlabel.setIcon(playerimg);
//            player.setBounds(100,100,playerimg.getIconWidth(),playerimg.getIconHeight());
//        }
    }
}
