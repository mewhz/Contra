package test;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ImgAdd extends JFrame {
    private Icon player = new ImageIcon("img/player/PR/player0.png");
    private Icon map = new ImageIcon("img/MAP/map.jpeg");

    private JLabel lplayer = new JLabel(player);
    private JLabel lmap = new JLabel(map);

    private JPanel pplayer = new JPanel();
    private JPanel pmap = new JPanel();

    private int i = 0;
    private boolean flag = false;//false代表右,true代表左
    private int k = 0;

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
                            k = 1;

                        }
                        else{
                            player = new ImageIcon("img/player/PR/up.png");
                            k = 1;
                        }
                        break;//上
                    case KeyEvent.VK_S:
                        if(flag){
                            player = new ImageIcon("img/player/PL/down.png");
                            k = 1;

                        }
                        else{
                            player = new ImageIcon("img/player/PR/down.png");
                            k = 1;
                        }
                        break;//下
                }
                i = i%12;
                System.out.println(i);
                if(!flag&&k!=1){
                    player = new ImageIcon("img/player/PR/player"+i+".png");
                }
                else    if(flag&&k!=1){
                    player = new ImageIcon("img/player/PL/player"+i+".png");
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
                if(x<=400&&e.getKeyCode()!=KeyEvent.VK_A){
                    x+=5;
                    x1+=5;
                }
                System.out.println(x1);
                pplayer.setBounds(x,y,player.getIconWidth(), player.getIconHeight());
                lplayer.setIcon(player);
                pmap.setBounds(x1,-5,map.getIconWidth(),map.getIconHeight());
            }

            //松开
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_D){
                    player = new ImageIcon("img/player/PR/player0.png");
                }
                else    if(e.getKeyCode()==KeyEvent.VK_A){
                    player = new ImageIcon("img/player/PL/player0.png");
                }
                lplayer.setIcon(player);
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
