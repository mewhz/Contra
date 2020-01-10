package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GuiTest extends JFrame implements KeyListener {

    private final int bulletlength = 1000;//子弹数量
    private final int fireSoldierlength = 100;//子弹小兵数量
    private final int[] VK = {KeyEvent.VK_D,KeyEvent.VK_A,KeyEvent.VK_W,KeyEvent.VK_S,KeyEvent.VK_K,KeyEvent.VK_J};//有效的按键

    private JPanel map;//地图
    private JPanel player;//玩家
    private JPanel fireSoldierBullet;//子弹小兵子弹
    private JPanel[] boom = new JPanel[6];//爆炸类

    private Map mapclass = new Map();//地图类
    private Player playerclass = new Player();//主角类
    private Bullet bulletclass = new Bullet();//子弹类
    private FireSoldier fireSoldierclass = new FireSoldier();//子弹小兵类
    private FireSoldierBullet fireSoldierBulletclass = new FireSoldierBullet();//子弹小兵子弹类
    private Boom boomclass = new Boom("img/Boom/boom.gif");

    private JPanel[] bullerarr = new JPanel[bulletlength];
    private BulletThread[] BulletThreadsarr = new BulletThread[bulletlength];
    private FireSoldierThread[] fireSoldierThreadarr = new FireSoldierThread[fireSoldierlength];

    private int[] bullerXarr = new int[bulletlength];//子弹的X轴坐标数组

    private boolean left = false;//判断左右,false再右,true再左
    private int up = 0;//判断是否向上
    private boolean lefgbool = false;//是否按下左
    private boolean rightbool = false;//是否按下右
    private boolean jumpbool = false;//是否按下跳
    private int i = 0;//用于切换移动图片
    private int j = 0;//用于切换跳跃图片
    private int b = 0;//用于判断第几发子弹
    private int bo = 0;//判断第几个爆炸
    private int mapX = 0;//地图的X轴坐标
    private int playerX = 100;//人物的X轴坐标
    private int playerY = 170;//人物的Y轴坐标
    private int bullerY = 200;//子弹的Y轴坐标
    private int fireSoldierX = 700;//子弹小兵X轴
    private JPanel fireSoldier;//子弹小兵
    private int fireSoldierY = 190;//子弹小兵的Y轴坐标
    private int fireSoldierBulletX = 0;//子弹小兵子弹的X轴坐标
    private int fireSoldierBulletY = 0;//子弹小兵子弹的Y轴坐标

    public GuiTest(){
        this.setTitle("简易魂斗罗");
        this.setSize(new Dimension(800,600));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);//使窗口在中央显示
        this.setLayout(null);
        imgadd();//添加图片
//        fSadd();
        this.setVisible(true);
    }

    public void imgadd(){
        map = mapclass.getMap();
        player = playerclass.getPlayer();
        for(int i = 0;i<bulletlength;i++){
            bullerarr[i] = bulletclass.getBullet();
            BulletThreadsarr[i] = new BulletThread();
            this.add(bullerarr[i]);
        }
        for(int i = 0;i<fireSoldierlength;i++){
            fireSoldierThreadarr[i] = new FireSoldierThread();
        }

        fireSoldier = fireSoldierclass.getFireSoldier();

        fireSoldierBullet = fireSoldierBulletclass.getFireSoldierBullet();

        for(int i = 0;i<6;i++){
            boom[i] = boomclass.getBoom();

            this.add(boom[i],0);
        }


        this.add(player,0);
        this.add(map,-1);


        start();

        map.setBounds(mapX,-5,mapclass.getMapimg().getIconWidth(),mapclass.getMapimg().getIconHeight());
        player.setBounds(playerX,playerY,playerclass.getPlayerimg().getIconWidth(),playerclass.getPlayerimg().getIconHeight());

        this.addKeyListener(this);
    }

    public void start(){
        Runnable startrun = new Runnable() {
            int count = 0;
            int playerY = 0;
            @Override
            public void run() {
                if(count<42){
                    j++;
                    j = j%8;
                    count++;
                    playerY += 5;//跳跃的高度
                    System.out.println("playerY="+playerY);
                    playerclass.setPlayerimg(new ImageIcon("img/player/PR/jump"+j+".png"));
                    playerclass.getPlayerlabel().setIcon(playerclass.getPlayerimg());
//                    map.setBounds(mapX,-5,mapclass.getMapimg().getIconWidth(),mapclass.getMapimg().getIconHeight());
                    player.setBounds(playerX,playerY, playerclass.getPlayerimg().getIconWidth(), playerclass.getPlayerimg().getIconHeight());
                }
                else    if(count==42){
                    count++;
                    this.playerY = 170;
                    playerclass.setPlayerimg(new ImageIcon("img/player/PR/player0.png"));
                    playerclass.getPlayerlabel().setIcon(playerclass.getPlayerimg());
//                    map.setBounds(mapX,-5,mapclass.getMapimg().getIconWidth(),mapclass.getMapimg().getIconHeight());
                    player.setBounds(playerX,this.playerY, playerclass.getPlayerimg().getIconWidth(), playerclass.getPlayerimg().getIconHeight());
                }
            }
        };
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(startrun, 30,30, TimeUnit.MILLISECONDS);//线程循环执行
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    //按键被按下时触发
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("playerX="+playerX);
        System.out.println("playerY="+playerY);
        System.out.println("fireSoldierX="+fireSoldierX);
        if(e.getKeyCode()==KeyEvent.VK_Q){
            System.out.println("创建小兵");
            fSadd();
        }
        int flag;
        for(flag = 0;flag<VK.length;flag++){
            if(e.getKeyCode()==VK[flag]){
                break;
            }
        }
        if(flag==VK.length){
            return;
        }
        switch(e.getKeyCode()){
            case KeyEvent.VK_D:
                rightbool = true;
                mapX-=5;
                i++;
                up = 0;
                left = false;
                if(mapX==-1000){
                    System.out.println("地图X轴坐标="+mapX);
                    fSadd();
                }
                break;//左
            case KeyEvent.VK_A:

                lefgbool = true;
                playerX-=5;
                i++;
                up = 0;
                left = true;
                break;//右
            case KeyEvent.VK_W:
                playerY=140;
                if(left){
                    playerclass.setPlayerimg(new ImageIcon("img/player/PL/up.png"));
                    up = 1;

                }
                else{
                    playerclass.setPlayerimg(new ImageIcon("img/player/PR/up.png"));
                    up = 1;
                }
                break;//上
            case KeyEvent.VK_S:
                if(left){
                    playerclass.setPlayerimg(new ImageIcon("img/player/PL/down.png"));
                    up = 1;
                    playerY=230;
                }
                else{
                    playerclass.setPlayerimg(new ImageIcon("img/player/PR/down.png"));
                    up = 1;
                    playerY=230;
                }
                break;//下
            case KeyEvent.VK_K:
                jumpbool = true;
                Runnable run = new Runnable() {
                    int count = 0;
                    @Override
                    public void run() {
                        if(count<20){
                            j++;
                            j = j%8;
                            count++;
                            playerY -= 5;//跳跃的高度
                            System.out.println(playerY);
                            //同时向右与跳跃
                            if(rightbool&&jumpbool){
                                System.out.println("Hello");
                                mapX-=50;
                                playerX+=50;
                                jumpbool = false;
                            }
                            else    if(lefgbool&&jumpbool){
                                System.out.println("World");
                                playerX-=50;
                            }
                            if(left){
                                playerclass.setPlayerimg(new ImageIcon("img/player/PL/jump"+j+".png"));
                            }
                            else{
                                playerclass.setPlayerimg(new ImageIcon("img/player/PR/jump"+j+".png"));
                            }
                            playerclass.getPlayerlabel().setIcon(playerclass.getPlayerimg());
                            player.setBounds(playerX,playerY, playerclass.getPlayerimg().getIconWidth(), playerclass.getPlayerimg().getIconHeight());
                        }
                        else    if(count<40){
                            j++;
                            j = j%8;
                            count++;
                            playerY += 5;//跳跃的高度
                            System.out.println(playerY);
                            if(left){
                                playerclass.setPlayerimg(new ImageIcon("img/player/PL/jump"+j+".png"));
                            }
                            else{
                                playerclass.setPlayerimg(new ImageIcon("img/player/PR/jump"+j+".png"));
                            }
                            playerclass.getPlayerlabel().setIcon(playerclass.getPlayerimg());
                            map.setBounds(mapX,-5,mapclass.getMapimg().getIconWidth(),mapclass.getMapimg().getIconHeight());
                            player.setBounds(playerX,playerY, playerclass.getPlayerimg().getIconWidth(), playerclass.getPlayerimg().getIconHeight());
                        }
                        else    if(count==40){
                            count++;
                            playerY = 170;//回到原本高度
                            if(left&&!jumpbool){
                                playerclass.setPlayerimg(new ImageIcon("img/player/PL/player0.png"));
                            }
                            else    if(!jumpbool){
                                playerclass.setPlayerimg(new ImageIcon("img/player/PR/player0.png"));
                            }
                            playerclass.getPlayerlabel().setIcon(playerclass.getPlayerimg());
                            player.setBounds(playerX,playerY, playerclass.getPlayerimg().getIconWidth(), playerclass.getPlayerimg().getIconHeight());
                        }
                    }
                };
                ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
                service.scheduleAtFixedRate(run, 30,30, TimeUnit.MILLISECONDS);//线程循环执行
                break;//跳
            case KeyEvent.VK_J:
                bulletremove();
                fireSoldierbulletMove();
                break;//攻击
        }
        i = i%12;

        //向右
        if(!left&&up!=1&&playerY==170){
            playerclass.setPlayerimg(new ImageIcon("img/player/PR/player"+i+".png"));
        }
        //向左
        else    if(left&&up!=1&&playerY==170){
            playerclass.setPlayerimg(new ImageIcon("img/player/PL/player"+i+".png"));
        }
        if(playerX<=0){
            playerX=0;
        }
        if(playerX<=300&&e.getKeyCode()==KeyEvent.VK_D){
            playerX+=5;
            mapX+=5;
            fireSoldierX-=5;
        }
        player.setBounds(playerX,playerY,playerclass.getPlayerimg().getIconWidth(),playerclass.getPlayerimg().getIconHeight());
        playerclass.getPlayerlabel().setIcon(playerclass.getPlayerimg());
//        fireSoldier.setBounds(fireSoldierX,fireSoldierY,fireSoldierclass.getFireSoldierimg().getIconWidth(),fireSoldierclass.getFireSoldierimg().getIconHeight());
        map.setBounds(mapX,-5,mapclass.getMapimg().getIconWidth(),mapclass.getMapimg().getIconHeight());
        System.out.println("mapX="+mapX);
        System.out.println("playerX="+playerX);
        System.out.println("playerY="+playerY);
    }


    //按键松开时触发
    @Override
    public void keyReleased(KeyEvent e) {
        playerY=170;
        //右
        if(e.getKeyCode()==KeyEvent.VK_D){
            playerclass.setPlayerimg(new ImageIcon("img/player/PR/player0.png"));
            rightbool = false;
        }//左
        else    if(e.getKeyCode()==KeyEvent.VK_A){
            playerclass.setPlayerimg(new ImageIcon("img/player/PL/player0.png"));
            lefgbool = false;
        }
        //下
        if(e.getKeyCode()==KeyEvent.VK_S&&left){
            playerclass.setPlayerimg(new ImageIcon("img/player/PL/player0.png"));
        }
        else    if(e.getKeyCode()==KeyEvent.VK_S&&!left){
            playerclass.setPlayerimg(new ImageIcon("img/player/PR/player0.png"));
        }
        //上
        if(e.getKeyCode()==KeyEvent.VK_W&&left){
            playerclass.setPlayerimg(new ImageIcon("img/player/PL/player0.png"));
        }
        else    if(e.getKeyCode()==KeyEvent.VK_W&&!left) {
            playerclass.setPlayerimg(new ImageIcon("img/player/PR/player0.png"));
        }
        //跳
        if(e.getKeyCode()==KeyEvent.VK_K&&left){
            playerclass.setPlayerimg(new ImageIcon("img/player/PL/player0.png"));
            jumpbool = false;
        }
        else    if(e.getKeyCode()==KeyEvent.VK_K&&!left){
            playerclass.setPlayerimg(new ImageIcon("img/player/PR/player0.png"));
            jumpbool = false;
        }
        playerclass.getPlayerlabel().setIcon(playerclass.getPlayerimg());
        player.setBounds(playerX,playerY,playerclass.getPlayerimg().getIconWidth(),playerclass.getPlayerimg().getIconHeight());
    }

    /**
     * 发射子弹
     */
    public void bulletremove(){
        ScheduledExecutorService service1 = Executors.newSingleThreadScheduledExecutor();
        service1.scheduleAtFixedRate(BulletThreadsarr[b], 5, 5, TimeUnit.MILLISECONDS);
        b++;
        bullerarr[b].setVisible(true);
    }

    /**
     * 线程类
     */
    public class BulletThread extends Thread{                                 //发射子弹
        private final Object lock = new Object();
        private int i = playerX+50;

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
                i++;
                bullerarr[b].setBounds(i,bullerY,600,600);          //i是x轴
                bullerXarr[b] = i;
            }
            if(i==800){
                onPause();
            }
//            System.out.println(i);
        }
    }

    public void fSadd(){
        this.add(fireSoldier,0);
        fireSoldier.setVisible(true);
//        boom[bo].setVisible(true);
//        System.out.println("fSadd bo="+bo);
//        this.add(fireSoldierBullet,0);
//        fireSoldierBullet.setBounds(fireSoldierX,fireSoldierY,fireSoldierclass.getFireSoldierimg().getIconWidth(),fireSoldierclass.getFireSoldierimg().getIconHeight());
        fireSoldier.setBounds(fireSoldierX,fireSoldierY,fireSoldierclass.getFireSoldierimg().getIconWidth(),fireSoldierclass.getFireSoldierimg().getIconHeight());
//        f++;
    }

    /**
     * 线程类
     */
    public class FireSoldierThread extends Thread{                                 //发射子弹
        private final Object lock = new Object();
        private int i = playerX+50;

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
            Rectangle fireSoldierRectangle = new Rectangle(fireSoldierX,fireSoldierY,fireSoldierclass.getFireSoldierimg().getIconWidth(),fireSoldierclass.getFireSoldierimg().getIconHeight());
            Rectangle bulletRectangle      = new Rectangle(bullerXarr[b],bullerY,bulletclass.getBulletimg().getIconWidth(),bulletclass.getBulletimg().getIconHeight());
            boolean flag = fireSoldierRectangle.intersects(bulletRectangle);

            if(flag){
                System.out.println("bo="+bo);
                boom[bo].setBounds(fireSoldier.getX(),fireSoldierY,115,85 );
                System.out.println("打中了");
                fireSoldier.setVisible(false);
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boom[bo].setVisible(false);
                bo++;
                bullerarr[b].setVisible(false);
                onPause();
            }
//            System.out.println(i);
        }
    }
    public void fireSoldierbulletMove(){
        System.out.println("进入");
        ScheduledExecutorService service1 = Executors.newSingleThreadScheduledExecutor();
        service1.scheduleAtFixedRate(fireSoldierThreadarr[b], 5, 5, TimeUnit.MILLISECONDS);
    }
}
