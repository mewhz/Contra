package game;

import javax.swing.*;

public class GameEnd extends JFrame {
    private JPanel jpa ;

    private JLabel jlaEnd;

    private ImageIcon iconEnd;
    public GameEnd(){
        jpa = new JPanel(null);
        jlaEnd = new JLabel();
        iconEnd = new ImageIcon("img/foot.gif");
        jlaEnd.setIcon(iconEnd);
        jpa.add(jlaEnd);
        jlaEnd.setBounds(0,0,800,600);

        setBounds(300,100,800,600);

//        this.setLocationRelativeTo(null);//使窗口在中央显示
        this.add(jpa);
        this.setSize(800,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

    }
}
