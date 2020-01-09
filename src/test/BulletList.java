package test;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BulletList extends JFrame implements KeyListener {
    private ArrayList<JPanel> bulletarr = new ArrayList<JPanel>();//子弹
    private ArrayList<MyThread> myThreadsarr = new ArrayList<MyThread>();
    private Bullet bulletclass = new Bullet();//子弹类
    private int bulletY = 200;//子弹的Y轴坐标
    private int PlayerX = 0;//人物的X轴坐标
    private int b = 0;//用于判断第几发子弹
    private int k = 0;//计算发射了几发子弹

    public BulletList(){
        this.setSize(800,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);

        PlayerX = 20;
        bulletarr.add(bulletclass.getBullet());
        myThreadsarr.add(new MyThread());
        this.add(bulletarr.get(b));
//        service.scheduleAtFixedRate(myThreadsarr.get(b), 3,3, TimeUnit.MILLISECONDS);//线程循环执行
        this.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_J) {
            b++;
            k++;
            bulletarr.add(bulletclass.getBullet());
            myThreadsarr.add(new MyThread());
            this.add(bulletarr.get(b));
            System.out.println("k="+k);
            System.out.println("b="+b);
            ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
            service.scheduleAtFixedRate(myThreadsarr.get(b), 3, 3, TimeUnit.MILLISECONDS);//线程循环执行
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public class MyThread extends Thread{                                 //发射子弹
        private final Object lock = new Object();
        private int i = PlayerX+50;

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
                System.out.println("ssss");
                i++;
                System.out.println(i);
                bulletarr.get(b).setBounds(i,bulletY,600,600);          //i是x轴
            }
            if(i==800){
                int a = b - k;
                System.out.println("a="+a);
                bulletarr.remove(a);
                k--;
                b--;
//                onPause();
            }
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        new BulletList();
    }
}

