package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Gui extends JFrame implements KeyListener {

    private JPanel map;//地图
    private JPanel player;//玩家

    private Map mapclass = new Map();//地图类
    private Player playerclass = new Player();//主角类

    private boolean left = false;//判断左右,false再右,true再左
    private int up = 0;//判断是否向上
    private int i = 0;//用于切换图片
    private int mapx = 0;//地图的X轴坐标
    private int playerx = 100;//人物的X轴坐标
    private int playery = 170;//人物的Y轴坐标
    private boolean start = false;//游戏是否开始

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

        map.setBounds(mapx,-5,mapclass.getMapimg().getIconWidth(),mapclass.getMapimg().getIconHeight());
        player.setBounds(playerx,playery,playerclass.getPlayerimg().getIconWidth(),playerclass.getPlayerimg().getIconHeight());

        this.addKeyListener(this);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    //按键被按下时触发
    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_D:
                mapx-=5;
                i++;
                up = 0;
                left = false;
                start = true;
                break;//左
            case KeyEvent.VK_A:
                playerx-=5;
                i++;
                up = 0;
                left = true;
                start = true;
                break;//右
            case KeyEvent.VK_W:
                start = true;
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
                start = true;
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
        if(playerx<=300&&e.getKeyCode()!=KeyEvent.VK_A&&start&&e.getKeyCode()!=KeyEvent.VK_S&&up==0){
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
        if(e.getKeyCode()==KeyEvent.VK_D){
            playerclass.setPlayerimg(new ImageIcon("img/player/PR/player0.png"));
        }
        else    if(e.getKeyCode()==KeyEvent.VK_A){
            playerclass.setPlayerimg(new ImageIcon("img/player/PL/player0.png"));
        }
        if(e.getKeyCode()==KeyEvent.VK_S&&left){
            playerclass.setPlayerimg(new ImageIcon("img/player/PL/player0.png"));
        }
        else    if(e.getKeyCode()==KeyEvent.VK_S&&!left){
            playerclass.setPlayerimg(new ImageIcon("img/player/PR/player0.png"));
        }
        if(e.getKeyCode()==KeyEvent.VK_W&&left){
            playerclass.setPlayerimg(new ImageIcon("img/player/PL/player0.png"));
        }
        else    if(e.getKeyCode()==KeyEvent.VK_W&&!left){
            playerclass.setPlayerimg(new ImageIcon("img/player/PR/player0.png"));
        }
        playerclass.getPlayerlabel().setIcon(playerclass.getPlayerimg());
        player.setBounds(playerx,playery,playerclass.getPlayerimg().getIconWidth(),playerclass.getPlayerimg().getIconHeight());
    }
}
