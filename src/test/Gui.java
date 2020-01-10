package test;

import test.YY.testAll;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Gui extends JFrame implements KeyListener {

    private final int bulletlength = 1000;//子弹数量
    private final int fireSoldierlength = 100;//子弹小兵数量
    private final int[] VK = {KeyEvent.VK_D,KeyEvent.VK_A,KeyEvent.VK_W,KeyEvent.VK_S,KeyEvent.VK_K,KeyEvent.VK_J};//有效的按键

    private JPanel map;//地图
    private JPanel player;//玩家
    private JPanel fireSoldier;//子弹小兵
    private JPanel fireSoldierBullet;//子弹小兵子弹
    private JPanel boom;//爆炸
    private JPanel noGunSoldier;//盗贼

    private Map mapclass = new Map();//地图类
    private Player playerclass = new Player();//主角类
    private Bullet bulletclass = new Bullet();//子弹类
    private FireSoldier fireSoldierclass = new FireSoldier();//子弹小兵类
    private FireSoldierBullet fireSoldierBulletclass = new FireSoldierBullet();//子弹小兵子弹类
    private Boom boomclass = new Boom("img/Boom/boom.gif");//爆炸类
    private NoGunSoldier noGunSoldierclass = new NoGunSoldier();//盗贼类

    private JPanel[] bullerarr = new JPanel[bulletlength];

    private BulletThread[] BulletThreadsarr = new BulletThread[bulletlength];
    private FireSoldierThread[] fireSoldierThreadarr = new FireSoldierThread[fireSoldierlength];
    private FireSoldierBulletThread fireSoldierBulletThread = new FireSoldierBulletThread();
    private NoGunSoldierThread noGunSoldierThread = new NoGunSoldierThread();
    private noGunSoldiermove noGunSoldiermove = new noGunSoldiermove();

    private int[] bullerXarr = new int[bulletlength];//子弹的X轴坐标数组

    private boolean left = false;//判断左右,false再右,true再左
    private int up = 0;//判断是否向上
    private boolean lefgbool = false;//是否按下左
    private boolean rightbool = false;//是否按下右
    private boolean jumpbool = false;//是否按下跳
    private boolean fireSoldierbool = true;//小兵是否存活
    private int i = 0;//用于切换移动图片
    private int j = 0;//用于切换跳跃图片
    private int b = 0;//用于判断第几发子弹
    private int d = 0;
    private int mapX = 0;//地图的X轴坐标
    private int playerX = 100;//人物的X轴坐标
    private int playerY = 170;//人物的Y轴坐标
    private int bullerY = 200;//子弹的Y轴坐标
    private int fireSoldierX = 500;//子弹小兵的X轴坐标
    private int fireSoldierY = 190;//子弹小兵的Y轴坐标
    private int fireSoldierBulletX = 0;//子弹小兵子弹的X轴坐标
    private int fireSoldierBulletY = 220;//子弹小兵子弹的Y轴坐标
    private int noGunSoldierX = 0;//盗贼X轴坐标
    private int noGunSoldierY = 200;//盗贼Y轴坐标


    public Gui(){
        this.setTitle("简易魂斗罗");
        this.setSize(new Dimension(800,600));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);//使窗口在中央显示
        this.setLayout(null);
        imgadd();//添加图片
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
//        fireSoldierBullet.setBounds(fireSoldierX,fireSoldierY,fireSoldierclass.getFireSoldierimg().getIconWidth(),fireSoldierclass.getFireSoldierimg().getIconHeight());
        fireSoldier.setBounds(fireSoldierX,fireSoldierY,fireSoldierclass.getFireSoldierimg().getIconWidth(),fireSoldierclass.getFireSoldierimg().getIconHeight());
        boom = boomclass.getBoom();
        noGunSoldier = noGunSoldierclass.getNoGunSoldier();

        this.add(boom,0);
        this.add(player,0);
        this.add(map,-1);
        this.add(fireSoldier,0);
        this.add(fireSoldierBullet,0);

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
                d++;
                if(d==20&&fireSoldierbool){
                    ScheduledExecutorService service1 = Executors.newSingleThreadScheduledExecutor();
                    service1.scheduleAtFixedRate(fireSoldierBulletThread, 5, 5, TimeUnit.MILLISECONDS);
                    d = 0;
                }
                if(mapX==-600){
                    noGunSoldieradd();
                    ScheduledExecutorService service1 = Executors.newSingleThreadScheduledExecutor();
                    service1.scheduleAtFixedRate(noGunSoldierThread, 5, 5, TimeUnit.MILLISECONDS);
                }
//                new testAll();
                break;//右
            case KeyEvent.VK_A:
                lefgbool = true;
                playerX-=5;
                i++;
                up = 0;
                left = true;
                break;//左
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
                            //同时向右与跳跃
                            if(rightbool&&jumpbool){
                                mapX-=50;
                                playerX+=50;
                                jumpbool = false;
                            }
                            else    if(lefgbool&&jumpbool){
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
                ScheduledExecutorService service1 = Executors.newSingleThreadScheduledExecutor();
                service1.scheduleAtFixedRate(noGunSoldiermove, 30,30, TimeUnit.MILLISECONDS);//线程循环执行
                break;//攻击
        }
        i = i%12;

        //向右
        if(!left&&up!=1&&playerY==170&&e.getKeyCode()!=KeyEvent.VK_J){
            playerclass.setPlayerimg(new ImageIcon("img/player/PR/player"+i+".png"));
        }
        //向左
        else    if(left&&up!=1&&playerY==170&&e.getKeyCode()!=KeyEvent.VK_J){
            playerclass.setPlayerimg(new ImageIcon("img/player/PL/player"+i+".png"));
        }
        if(playerX<=0){
            playerX=0;
        }
        if(playerX<=300&&e.getKeyCode()==KeyEvent.VK_D){
            playerX+=5;
            mapX+=5;
        }
        player.setBounds(playerX,playerY,playerclass.getPlayerimg().getIconWidth(),playerclass.getPlayerimg().getIconHeight());
        playerclass.getPlayerlabel().setIcon(playerclass.getPlayerimg());
        map.setBounds(mapX,-5,mapclass.getMapimg().getIconWidth(),mapclass.getMapimg().getIconHeight());
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
    public class FireSoldierBulletThread extends Thread{
        private final Object lock = new Object();
        private int i = 350;

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
            if(i!=-110){
                i--;
                fireSoldierBullet.setBounds(i,fireSoldierBulletY,200,200);
            }
            if(i==-110){
                i = 350;
                fireSoldierBullet.setBounds(-110,fireSoldierBulletY,200,200);
                onPause();
            }
        }
    }

    /**
     * 线程类
     */
    public class BulletThread extends Thread{                                 //发射子弹
        private final Object lock = new Object();
        private int i  = 0;

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
            if(i==0){
                i = playerX + 50;
            }
            if(i!=800){
                i++;
                System.out.println("playerX="+playerX);
                System.out.println("i="+i);

                bullerarr[b].setBounds(i,bullerY,600,600);          //i是x轴
                bullerXarr[b] = i;
            }
            if(i==800){
                onPause();
            }
        }
    }

//    public void fSadd(){
//        this.add(fireSoldier,0);
//        this.add(fireSoldierBullet,0);
//        fireSoldierBullet.setBounds(fireSoldierX,fireSoldierY,fireSoldierclass.getFireSoldierimg().getIconWidth(),fireSoldierclass.getFireSoldierimg().getIconHeight());
//        fireSoldier.setBounds(fireSoldierX,fireSoldierY,fireSoldierclass.getFireSoldierimg().getIconWidth(),fireSoldierclass.getFireSoldierimg().getIconHeight());
//    }

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
                boom.setBounds(fireSoldier.getX(),fireSoldierY,115,85 );
                fireSoldier.setVisible(false);
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                fireSoldierbool = false;
                boom.setVisible(false);
                bullerarr[b].setVisible(false);
                onPause();
            }
        }
    }

    public void fireSoldierbulletMove(){
        ScheduledExecutorService service1 = Executors.newSingleThreadScheduledExecutor();
        service1.scheduleAtFixedRate(fireSoldierThreadarr[b], 5, 5, TimeUnit.MILLISECONDS);
    }

    public class NoGunSoldierThread extends Thread{
        private final Object lock = new Object();
        private int i = 0;

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
            i++;
            i = i%9;
            noGunSoldierX+=2;
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            noGunSoldierclass.setNoGunSoldierimg(new ImageIcon("img/NoGunSoldier/npc"+i+".png"));
            noGunSoldier.setBounds(noGunSoldierX,noGunSoldierY,noGunSoldierclass.getNoGunSoldierimg().getIconWidth(),noGunSoldierclass.getNoGunSoldierimg().getIconHeight());
            noGunSoldierclass.getNoGunSoldierlabel().setIcon(new ImageIcon("img/NoGunSoldier/npc"+i+".png"));
        }
    }

    public void noGunSoldieradd(){
        this.add(noGunSoldier,0);
        noGunSoldier.setBounds(noGunSoldierX,noGunSoldierY,noGunSoldierclass.getNoGunSoldierimg().getIconWidth(),noGunSoldierclass.getNoGunSoldierimg().getIconHeight());
    }

    public class noGunSoldiermove extends Thread{
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
            Rectangle noGunSoldierRectangle = new Rectangle(noGunSoldierX,noGunSoldierY,noGunSoldierclass.getNoGunSoldierimg().getIconWidth(),noGunSoldierclass.getNoGunSoldierimg().getIconHeight());
            Rectangle bulletRectangle       = new Rectangle(bullerXarr[b],bullerY,bulletclass.getBulletimg().getIconWidth(),bulletclass.getBulletimg().getIconHeight());
            boolean flag = noGunSoldierRectangle.intersects(bulletRectangle);

            if(flag){

                boom.setVisible(true);
                boom.setBounds(fireSoldier.getX(),fireSoldierY,115,85 );
                noGunSoldier.setVisible(false);
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boom.setVisible(false);
                bullerarr[b].setVisible(false);
                onPause();
            }
        }
    }
}
