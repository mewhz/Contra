package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.Year;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ImgAdd extends JFrame {
    private Icon player = new ImageIcon("img/player/PR/player0.png");
    private Icon map = new ImageIcon("img/MAP/map.jpeg");

    private JLabel lplayer = new JLabel(player);
    private JLabel lmap = new JLabel(map);

    private JPanel pplayer = new JPanel();
    private JPanel pmap = new JPanel();

    private int i = 0;
    private int j = 0;

    private boolean flag = false;//false代表右,true代表左
    private boolean jump = false;
    private boolean flag1 = false;
    private int K = 0;
    private int k = 0;
    private int[] VK = {KeyEvent.VK_D,KeyEvent.VK_A,KeyEvent.VK_W,KeyEvent.VK_S,KeyEvent.VK_K};

    private int x = 100;
    private int x1 = 0;
    private int y = 170;

    public ImgAdd(){
        this.setSize(800,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        pplayer.setOpaque(false);//设置透明度
        pmap.add(lmap);
        pplayer.add(lplayer);


        pmap.setBounds(0,-5     , map.getIconWidth(),map.getIconHeight());
        pplayer.setBounds(100,170, player.getIconWidth(),player.getIconHeight());
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

            }
            //按下
            @Override
            public void keyPressed(KeyEvent e) {
                int q = 0;
                for(q = 0;q<VK.length;q++){
                    if(e.getKeyCode()==VK[q]){
                        break;
                    }
                }
                if(q==VK.length){
                    return;
                }
                if(e.getKeyCode()==KeyEvent.VK_K){
                    Runnable runnable = new Runnable() {
                        int count = 0;
                        @Override
                        public void run() {
                            if(count<16){
                                j++;
                                j = j%8;
                                player = new ImageIcon("img/player/PL/jump"+j+".png");
                                lplayer.setIcon(player);
                                pplayer.setBounds(x, y, player.getIconWidth(),player.getIconHeight());
                                count++;
                                jump = true;
                                y = 100;
                            }
                            if(count==16){
                                count++;
                                jump = false;
                                y = 170;
                                player = new ImageIcon("img/player/PR/player0.png");
                                lplayer.setIcon(player);
                                pplayer.setBounds(x, y, player.getIconWidth(),player.getIconHeight());
                            }
                            System.out.println("123");
                        }
                    };
                    ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
                    service.scheduleAtFixedRate(runnable, 30, 30, TimeUnit.MILLISECONDS);

                }
                switch(e.getKeyCode()){
                    case KeyEvent.VK_D:
                        i++;
                        k = 0;
                        flag = false;
                        break;//左
                    case KeyEvent.VK_A:
                        x-=5;
                        i++;
                        k = 0;
                        flag = true;
                        break;//右
                    case KeyEvent.VK_W:
                        if(flag){
                            player = new ImageIcon("img/player/PL/up.png");
                            lplayer.setIcon(player);
                            k = 1;

                        }
                        else{
                            player = new ImageIcon("img/player/PR/up.png");
                            lplayer.setIcon(player);
                            k = 1;
                        }
                        break;//上
                    case KeyEvent.VK_S:
                        if(flag){
                            player = new ImageIcon("img/player/PL/down.png");
                            lplayer.setIcon(player);
                            k = 1;

                        }
                        else{
                            player = new ImageIcon("img/player/PR/down.png");
                            lplayer.setIcon(player);
                            k = 1;
                        }
                        break;//下
                }
                i = i%12;
                System.out.println(i);
                if(!flag&&k!=1&&!jump){
                    System.out.println("kkk");
                    player = new ImageIcon("img/player/PR/player"+i+".png");
                    lplayer.setIcon(player);
                }
                else    if(flag&&k!=1&&!jump){
                    System.out.println("ooo");
                    player = new ImageIcon("img/player/PL/player"+i+".png");
                    lplayer.setIcon(player);
                }
                if(x<=0){
                    x=0;
                }
                if(e.getKeyCode()== KeyEvent.VK_D){
                    x1-=5;
                }
                else    if(e.getKeyCode()==KeyEvent.VK_W){

                    y-=5;
                }
                if(x<=400&&e.getKeyCode()==KeyEvent.VK_D){
                    x+=5;
                    x1+=5;
                    System.out.println("hello");
                }
                System.out.println(x1);
                pplayer.setBounds(x,y,player.getIconWidth(), player.getIconHeight());
                pmap.setBounds(x1,-5,map.getIconWidth(),map.getIconHeight());
            }

            //松开
            @Override
            public void keyReleased(KeyEvent e) {
                Thread thread = new Thread();
                if(e.getKeyCode()==KeyEvent.VK_D){
                    player = new ImageIcon("img/player/PR/player0.png");
                    lplayer.setIcon(player);
                }
                else    if(e.getKeyCode()==KeyEvent.VK_A){
                    player = new ImageIcon("img/player/PL/player0.png");
                    lplayer.setIcon(player);
                }
                else    if(e.getKeyCode()==KeyEvent.VK_K){
                    player = new ImageIcon("img/player/PR/player0.png");
                    lplayer.setIcon(player);

                }
                y = 170;
                pplayer.setBounds(x,y,player.getIconWidth(), player.getIconHeight());
                jump = false;
            }
        });

        this.add(pmap,-1);
        this.add(pplayer,0);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new ImgAdd();
    }

}
