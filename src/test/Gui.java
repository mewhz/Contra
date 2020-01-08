package test;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Gui extends JFrame implements KeyListener {

    private JPanel map;//地图
    private JPanel player;//玩家

    private Map mapclass = new Map();//地图类
    private Player playerclass = new Player();//主角类

    private boolean left = false;//判断左右,false再右,true再左
    private int up = 0;//判断是否向上
    private boolean rightbool = false;
    private boolean jumpbool = false;
    private int i = 0;//用于切换移动图片
    private int j = 0;//用于切换跳跃图片
    private int mapx = 0;//地图的X轴坐标
    private int playerx = 100;//人物的X轴坐标
    private int playery = 170;//人物的Y轴坐标
    private final int[] VK = {KeyEvent.VK_D,KeyEvent.VK_A,KeyEvent.VK_W,KeyEvent.VK_S,KeyEvent.VK_K};//有效的按键

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

        this.add(map,-1);

        this.add(player,0);
        start();

        map.setBounds(mapx,-5,mapclass.getMapimg().getIconWidth(),mapclass.getMapimg().getIconHeight());
        player.setBounds(playerx,playery,playerclass.getPlayerimg().getIconWidth(),playerclass.getPlayerimg().getIconHeight());

        this.addKeyListener(this);
    }

    public void start(){
        Runnable startrun = new Runnable() {
            int count = 0;
            int playery = 0;
            @Override
            public void run() {
                if(count<42){
                    j++;
                    j = j%8;
                    count++;
                    playery += 5;//跳跃的高度
                    System.out.println("playery="+playery);
                    playerclass.setPlayerimg(new ImageIcon("img/player/PR/jump"+j+".png"));
                    playerclass.getPlayerlabel().setIcon(playerclass.getPlayerimg());
//                    map.setBounds(mapx,-5,mapclass.getMapimg().getIconWidth(),mapclass.getMapimg().getIconHeight());
                    player.setBounds(playerx,playery, playerclass.getPlayerimg().getIconWidth(), playerclass.getPlayerimg().getIconHeight());
                }
                else    if(count==42){
                    count++;
                    this.playery = 170;
                    playerclass.setPlayerimg(new ImageIcon("img/player/PR/player0.png"));
                    playerclass.getPlayerlabel().setIcon(playerclass.getPlayerimg());
//                    map.setBounds(mapx,-5,mapclass.getMapimg().getIconWidth(),mapclass.getMapimg().getIconHeight());
                    player.setBounds(playerx,this.playery, playerclass.getPlayerimg().getIconWidth(), playerclass.getPlayerimg().getIconHeight());
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
                mapx-=5;
                i++;
                up = 0;
                left = false;
                break;//左
            case KeyEvent.VK_A:
                playerx-=5;
                i++;
                up = 0;
                left = true;
                break;//右
            case KeyEvent.VK_W:
                playery=140;
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
                    playery=230;
                }
                else{
                    playerclass.setPlayerimg(new ImageIcon("img/player/PR/down.png"));
                    up = 1;
                    playery=230;
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
                            playery -= 5;//跳跃的高度
                            System.out.println(playery);
                            //同时向右与跳跃
                            if(rightbool&&jumpbool){
                                System.out.println("Hello");
                                mapx-=50;
                                playerx+=50;
                                jumpbool = false;
                            }
                            if(left){
                                playerclass.setPlayerimg(new ImageIcon("img/player/PL/jump"+j+".png"));
                            }
                            else{
                                playerclass.setPlayerimg(new ImageIcon("img/player/PR/jump"+j+".png"));
                            }
                            playerclass.getPlayerlabel().setIcon(playerclass.getPlayerimg());
                            player.setBounds(playerx,playery, playerclass.getPlayerimg().getIconWidth(), playerclass.getPlayerimg().getIconHeight());
                        }
                        else    if(count<40){
                            j++;
                            j = j%8;
                            count++;
                            playery += 5;//跳跃的高度
                            System.out.println(playery);
                            if(left){
                                playerclass.setPlayerimg(new ImageIcon("img/player/PL/jump"+j+".png"));
                            }
                            else{
                                playerclass.setPlayerimg(new ImageIcon("img/player/PR/jump"+j+".png"));
                            }
                            playerclass.getPlayerlabel().setIcon(playerclass.getPlayerimg());
                            map.setBounds(mapx,-5,mapclass.getMapimg().getIconWidth(),mapclass.getMapimg().getIconHeight());
                            player.setBounds(playerx,playery, playerclass.getPlayerimg().getIconWidth(), playerclass.getPlayerimg().getIconHeight());
                        }
                        else    if(count==40){
                            count++;
                            playery = 170;//回到原本高度
                            if(left){
                                playerclass.setPlayerimg(new ImageIcon("img/player/PL/player0.png"));
                            }
                            else{
                                playerclass.setPlayerimg(new ImageIcon("img/player/PR/player0.png"));
                            }
                            playerclass.getPlayerlabel().setIcon(playerclass.getPlayerimg());
                            player.setBounds(playerx,playery, playerclass.getPlayerimg().getIconWidth(), playerclass.getPlayerimg().getIconHeight());
                        }
                    }
                };
                ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
                service.scheduleAtFixedRate(run, 30,30, TimeUnit.MILLISECONDS);//线程循环执行
                break;//跳
        }
        i = i%12;

        //向右
        if(!left&&up!=1){
            playerclass.setPlayerimg(new ImageIcon("img/player/PR/player"+i+".png"));
        }
        //向左
        else    if(left&&up!=1){
            playerclass.setPlayerimg(new ImageIcon("img/player/PL/player"+i+".png"));
        }
        if(playerx<=0){
            playerx=0;
        }
        if(playerx<=300&&e.getKeyCode()==KeyEvent.VK_D){
            playerx+=5;
            mapx+=5;
        }
        player.setBounds(playerx,playery,playerclass.getPlayerimg().getIconWidth(),playerclass.getPlayerimg().getIconHeight());
        playerclass.getPlayerlabel().setIcon(playerclass.getPlayerimg());
        map.setBounds(mapx,-5,mapclass.getMapimg().getIconWidth(),mapclass.getMapimg().getIconHeight());
        System.out.println("mapx="+mapx);
        System.out.println("playerx="+playerx);
        System.out.println("playery="+playery);
    }


    //按键松开时触发
    @Override
    public void keyReleased(KeyEvent e) {
        playery=170;
        //右
        if(e.getKeyCode()==KeyEvent.VK_D){
            playerclass.setPlayerimg(new ImageIcon("img/player/PR/player0.png"));
            rightbool = false;
        }//左
        else    if(e.getKeyCode()==KeyEvent.VK_A){
            playerclass.setPlayerimg(new ImageIcon("img/player/PL/player0.png"));
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
        player.setBounds(playerx,playery,playerclass.getPlayerimg().getIconWidth(),playerclass.getPlayerimg().getIconHeight());
    }
}
