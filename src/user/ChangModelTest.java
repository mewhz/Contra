package user;

import game.Gui;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ChangModelTest extends JFrame implements KeyListener{
    private int i = 1;                       //切换选人数的时候的flag
    private int flagOK = 1;                  //选双人模式的flag
    private int x = 800;
    boolean start = true;
    private Timer timer = new Timer();

    private JPanel jpa ;

    private ImageIcon iconChange ;
    private ImageIcon iconGif;

    private JLabel jlaChange ;
    private JLabel jlaGif;

    private MyTimerTask myTimerTask = new MyTimerTask(this);

    public ChangModelTest() {
        sounds at = new sounds("sound/backgroudMusic.wav");
        at.start();
        this.setTitle("简易魂斗罗");
        jpa = new JPanel(null);
        setBounds(300,100,800,600);

        jlaChange = new JLabel();
        jlaGif = new JLabel();

        iconChange = new ImageIcon("img/imageC/change1.jpg");
        iconGif = new ImageIcon("img/imageOK/changeOK.gif");

        jlaGif.setIcon(iconGif);
        jlaChange.setIcon(iconChange);

        jpa.add(jlaChange);
        jpa.add(jlaGif);

        jlaChange.setBounds(x,-4,800,600);


        Runnable run = new Runnable(){              //开始的时候图片的移动
            @Override
            public void run() {
                if (x != 0) {
                    x--;
                    jlaChange.setBounds(x, 0, 800, 600);
                }
            }
        };
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(run, 10, 10, TimeUnit.MILLISECONDS);

        this.addKeyListener(this);

        this.add(jpa);
        this.setSize(800,630);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {            //键盘监听

        //    ↓ ↓ ↓ 判断在使用上下左右键的时候切换模式 ↓ ↓ ↓

        int keycode = e.getKeyCode();
        if (keycode == KeyEvent.VK_RIGHT || keycode == KeyEvent.VK_LEFT || keycode == KeyEvent.VK_DOWN || keycode == KeyEvent.VK_UP) {
            i++;
            if (i % 2 == 1) {
                jlaChange.setIcon(new ImageIcon("img/imageC/change1.jpg"));
                flagOK = 1;
            } else {
                jlaChange.setIcon(new ImageIcon("img/imageC/change2.jpg"));
                flagOK = 2;
            }
        }
        //     ↓ ↓ ↓ 判断在输入回车键的时候触发事件 ↓ ↓ ↓
        if (keycode == KeyEvent.VK_ENTER) {
            if (flagOK == 1) {                           //当选择单人模式的时候进入游戏，闪烁动画
                start = true;
                jlaChange.setVisible(false);
                jlaGif.setBounds(0,-4,800,600);
            }
            else {                                       //当选择双人模式的时候触发提示框
                JOptionPane.showMessageDialog(null, "      暂未开放，敬请期待", "根本就是懒得做了", JOptionPane.ERROR_MESSAGE);
                start=false;
            }
            if (start) {
                timer.schedule(myTimerTask,3000);
                System.out.println("正在停止");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    public class MyTimerTask extends TimerTask {
        private JFrame re;
        public MyTimerTask(JFrame re){
            super();
            this.re = re;
        }
        @Override
        public void run() {
            new Gui();
            re.setVisible(false);

        }
    }

}