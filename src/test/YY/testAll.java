package test.YY;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class testAll extends JFrame {
    private int soldierMY = -55;
    private int RunSoldier = 10;
    private int soldierFlag = 1;              //跑的小兵的flag
    private int mapX = 0;                     //地图的坐标flag
    private int soldierX = 500;               //打枪的小兵的坐标flag
    private int i = 498;                      //子弹的坐标flag
    private int bulletX = 498;                //子弹的移动flag

    private JPanel jpa;

    private JLabel jla;
    private JLabel jla1;
    private JLabel jlaMap;
    private JLabel jlaMS;

    private ImageIcon icon;
    private ImageIcon icon1;
    private ImageIcon map;
    private ImageIcon iconMS;

    public testAll(){
        jpa = new JPanel(null);             //小兵面板

        jla = new JLabel();             //开枪小兵的标签
        jla1 = new JLabel();            //子弹的标签
        icon1 = new ImageIcon("img/fireSoldier/bullet.png");      //子弹图片

        jla1.setIcon(icon1);

        jpa.add(jla1);


        jla.setBounds(soldierX,195,100,100);

        setBounds(300,50,800,630);

        Runnable run1 = new Runnable(){                                 //发射子弹
            @Override
            public void run() {
                i--;
                jla1.setBounds(i,166,100,100);           //i是x轴
                if (i == 0){
                    i = i + bulletX;
                }
            }
        };
        ScheduledExecutorService service1 = Executors.newSingleThreadScheduledExecutor();
        service1.scheduleAtFixedRate(run1, 5, 5, TimeUnit.MILLISECONDS);


//        this.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//
//            }
//            //按下
//            @Override
//            public void keyPressed(KeyEvent e) {
//                switch (e.getKeyCode()){
//                    case KeyEvent.VK_D:
//                        mapX -= 5;
//                        soldierX -= 5;
//                        bulletX -= 5;
//                        jla1.setBounds(i,166,100,100);
//                        jla.setBounds(soldierX,195,100,100);
//                        jlaMap.setBounds(mapX,0,8921,600);
//                        break;
//                }
//            }
//            //松开
//            @Override
//            public void keyReleased(KeyEvent e) {
//
//            }
//
//        });

        this.add(jpa);
        this.setSize(800,630);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        testAll b = new testAll();
    }
}
