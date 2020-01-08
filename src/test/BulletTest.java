package test;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BulletTest extends JFrame {
    private Bullet bulletclass;//子弹类
    private JPanel bullet;//子弹
    private JPanel map;
    private Map mapclass;
    private int bulletx =100;//子弹坐标轴X
    private int bullety;//子弹坐标轴Y
    private boolean bulletbool = false;//子弹发射
    private int i = 0;
    public BulletTest(){
        this.setSize(800,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        imgadd();
        this.setVisible(true);

    }
    public void imgadd(){
        bulletclass = new Bullet();
        bullet = bulletclass.getBullet();
        mapclass = new Map();
        map = mapclass.getMap();
        this.add(bullet);
        this.add(map);
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_J&&!bulletbool){
                    i = 0;
                    System.out.println("hello");
                    bulletmove();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        bullet.setBounds(100,100,bulletclass.getBulletimg().getIconWidth(),bulletclass.getBulletimg().getIconHeight());
        map.setBounds(0,-5,mapclass.getMapimg().getIconWidth(),mapclass.getMapimg().getIconHeight());
//        bulletmove();
//        bullet.setBounds(100,100,200,200);
    }
    public void bulletmove(){
        Thread run1 = new Thread(){                                 //发射子弹
            @Override
            public void run() {
                if(i!=800){
                    bulletbool = true;
                    i++;
                    bullet.setBounds(i,166,500,500);          //i是x轴

                }
                System.out.println(i);
            }
        };
        Thread k = new Thread(run1);
        ScheduledExecutorService service1 = Executors.newSingleThreadScheduledExecutor();
        service1.scheduleAtFixedRate(run1, 5, 500, TimeUnit.MILLISECONDS);
        System.out.println("kkkk");
    }

    public static void main(String[] args) {
        new BulletTest();
    }
}
