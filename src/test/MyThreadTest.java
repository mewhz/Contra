package test;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MyThreadTest extends JFrame {
    private Bullet bulletclass;//子弹类
    private JPanel[] bullet = new JPanel[10];//子弹
    private JPanel map;
    private Map mapclass;
    private int bulletx =100;//子弹坐标轴X
    private int bullety;//子弹坐标轴Y
    private boolean bulletbool = false;//子弹发射
    private boolean pause = false;
    private int i = 0;
    private int k = 0;
    private int count = 0;
    private MyThread[] run1 = new MyThread[10];
    public MyThreadTest(){
        this.setSize(800,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        bulletclass = new Bullet();
        for(int i = 0;i<10;i++){
            bullet[i] = bulletclass.getBullet();
        }

        mapclass = new Map();
        map = mapclass.getMap();
        for(int i = 0;i<10;i++){
            run1[i] = new MyThread();
        }
//        run1.start();
        imgadd();

        this.setVisible(true);

    }
    public void imgadd(){


        for(int i = 0;i<10;i++){
            this.add(bullet[i]);
        }
        this.add(map);

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_J){
                    i = 0;
                    count++;
                    System.out.println("hello");
                    bulletmove();
                    System.out.println("count="+count);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        for(int i = 0;i<10;i++){
            bullet[i].setBounds(100+i*10,100,bulletclass.getBulletimg().getIconWidth(),bulletclass.getBulletimg().getIconHeight());
        }

        map.setBounds(0,-5,mapclass.getMapimg().getIconWidth(),mapclass.getMapimg().getIconHeight());
//        bulletmove();
//        bullet.setBounds(100,100,200,200);
    }
    public class MyThread extends Thread{                                 //发射子弹
        private final Object lock = new Object();
        private int i = 0;

        /**
         调用该方法实现线程的暂停
         */
        void pauseThread(){
            pause = true;
        }


        /**
         调用该方法实现恢复线程的运行
         */
        void resumeThread(){
            pause =false;
            synchronized (lock){
                lock.notify();
            }
        }

        /**
         * 这个方法只能在run 方法中实现，不然会阻塞主线程，导致页面无响应
         */
        void onPause() {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void run() {
            if(i!=800){
                bulletbool = true;
                i++;
                bullet[count].setBounds(i,166,500,500);          //i是x轴


            }
            if(i==800){
                onPause();
            }
            System.out.println(i);
        }
    }
    public void bulletmove(){
//        run1.resumeThread();
            ScheduledExecutorService service1 = Executors.newSingleThreadScheduledExecutor();
            service1.scheduleAtFixedRate(run1[k], 5, 5, TimeUnit.MILLISECONDS);
            k++;
        System.out.println("k="+k);
    }

    public static void main(String[] args) {
        new MyThreadTest();
    }
}
