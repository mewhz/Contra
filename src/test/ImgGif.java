package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ImgGif extends JFrame  {
    private int i = 0;
    private boolean flag = false;//false代表右,true代表左
    private int k = 0;

    private Icon icon = new ImageIcon("img/player/PR/player"+i+".png");
    private Icon mapp = new ImageIcon("img/MAP/map.jpeg");

    private JLabel img = new JLabel(icon);
    private JLabel map = new JLabel(mapp);

    private JPanel imgs = new JPanel();
    private JPanel maps = new JPanel();

    private int x = 100;
    private int y = 100;

    private final int h = icon.getIconHeight();
    private final int w = icon.getIconWidth();

    public ImgGif(){
        this.setSize(new Dimension(800,600));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        maps.add(map);
        imgs.add(img);
        this.add(map);
        this.add(imgs);
        this.setLayout(null);
        maps.setBounds(200,200, mapp.getIconWidth(), mapp.getIconHeight());
        imgs.setBounds(x, y,w, h);
        this.addKeyListener(new KeyAdapter() {

            //键盘按下去
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()){
                    case KeyEvent.VK_D:
                        x+=5;
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
                            icon = new ImageIcon("img/player/PL/up.png");
                            k = 1;
                            
                        }
                        else{
                            icon = new ImageIcon("img/player/PR/up.png");
                            k = 1;
                        }
                        break;//上
                    case KeyEvent.VK_S:
                        if(flag){
                            icon = new ImageIcon("img/player/PL/down.png");
                            k = 1;

                        }
                        else{
                            icon = new ImageIcon("img/player/PR/down.png");
                            k = 1;
                        }
                        break;//下
                }
                i = i%12;
                System.out.println(i);
                if(!flag&&k!=1){
                    icon = new ImageIcon("img/player/PR/player"+i+".png");
                }
                else    if(flag&&k!=1){
                    icon = new ImageIcon("img/player/PL/player"+i+".png");
                }
                imgs.setBounds(x,y,icon.getIconWidth(), icon.getIconHeight());
                img.setIcon(icon);
            }

            //键盘松开
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_D){
                    icon = new ImageIcon("img/player/PR/player0.png");
                }
                else    if(e.getKeyCode()==KeyEvent.VK_A){
                    icon = new ImageIcon("img/player/PL/player0.png");
                }
                img.setIcon(icon);
            }
        });


        this.setVisible(true);

    }

    public static void main(String[] args) {
        new ImgGif();
    }
}
