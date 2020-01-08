package YY;

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
        jlaMap = new JLabel();          //地图的标签
        jlaMS = new JLabel();           //移动小兵的标签

        icon = new ImageIcon("fireSoldier/fire.png");         //地图图片
        icon1 = new ImageIcon("fireSoldier/bullet.png");      //子弹图片
        map = new ImageIcon("img/map01.jpeg");                //地图

        jla.setIcon(icon);
        jla1.setIcon(icon1);
        jlaMS.setIcon(iconMS);
        jlaMap.setIcon(map);

        jpa.add(jlaMS);
        jpa.add(jla1);
        jpa.add(jla);
        jpa.add(jlaMap);


        jla.setBounds(soldierX,195,100,100);
        jlaMap.setBounds(mapX,0,8921,600);

        setBounds(300,50,800,630);
        icon.setImage(icon.getImage().getScaledInstance(80,110, Image.SCALE_DEFAULT));

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


        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

            }
            //按下
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_D:
                        mapX -= 5;
                        soldierX -= 5;
                        bulletX -= 5;
                        jla1.setBounds(i,166,100,100);
                        jla.setBounds(soldierX,195,100,100);
                        jlaMap.setBounds(mapX,0,8921,600);
                        break;
                }
            }
            //松开
            @Override
            public void keyReleased(KeyEvent e) {

            }

        });

        Runnable run = new Runnable(){                                          //小兵的移动
            @Override
            public void run() {
                jlaMS.setBounds(RunSoldier,soldierMY,800,600);        //i是x轴
                if (RunSoldier != 500){                                         //jla1是标签名字
                    RunSoldier = RunSoldier + 10;                               //icon1是图标
                    iconMS = new ImageIcon("NoGunSoldier/"+soldierFlag+".png");      //soldierFlag是图片的名字
                    iconMS.setImage(iconMS.getImage().getScaledInstance(70,90, Image.SCALE_DEFAULT));
                    jlaMS.setIcon(iconMS);
                    soldierFlag++;
                    if (soldierFlag == 10){
                        soldierFlag = 1;
                    }
                }

                Rectangle bulletRectangle = new Rectangle(jla1.getX(),jla.getY(),10,10);
                Rectangle planeRectangle = new Rectangle(jlaMS.getX(),jlaMS.getY()+250,70,90);
                boolean collide= bulletRectangle.intersects(planeRectangle);



                if (collide == true){
                    soldierMY = 100;
                    System.out.println("打中了");
                }

            }
        };
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(run, 70, 70, TimeUnit.MILLISECONDS);







        this.add(jpa);
        this.setSize(800,630);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        testAll b = new testAll();
    }
}
