package test;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MapTest extends JFrame {
    private Icon icon = new ImageIcon("img/MAP/map.jpeg");
    private JLabel mapp = new JLabel(icon);
    private JPanel maps = new JPanel();
    private int x = 0;
    private int y = 0;

    public MapTest(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,600);
        this.setLayout(null);
        maps.add(mapp);
        maps.setBounds(x,y,icon.getIconWidth(),icon.getIconHeight());
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

            }
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()== KeyEvent.VK_D){
                    x-=5;
                }
                else    if(e.getKeyCode()==KeyEvent.VK_W){

                    y-=5;
                }
                System.out.println(x);
                maps.setBounds(x,-5,icon.getIconWidth(),icon.getIconHeight());
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        this.add(maps);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MapTest();
    }
}
