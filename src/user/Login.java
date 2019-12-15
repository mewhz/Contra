package user;

import javax.swing.*;
import java.awt.*;


public class Login extends JFrame {
    private JPanel login_exit;//放置登录和退出按钮
    private JPanel user_pass;//放置用户名和密码文本框

    private JButton login;//登录
    private JButton exit;//退出


    private JTextField user;//用户名
    private JTextField pass;//密码
    //构造方法
    public Login(){
        this.setTitle("欢迎登录XX游戏系统");
        this.setSize(new Dimension(800,600));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        button();
        text();
        this.add(login_exit,BorderLayout.SOUTH);
        this.add(user_pass ,BorderLayout.NORTH);
        this.setLocationRelativeTo(null);//使窗口在中央显示
        this.setVisible(true);


    }
    //按钮
    private void button(){
        login_exit = new JPanel(new FlowLayout());
        login = new JButton("登录");
        exit  = new JButton("退出");
        login.setPreferredSize(new Dimension(100,50));
        exit.setPreferredSize(new Dimension(100,50));
        login_exit.add(login);
        login_exit.add(exit);
    }
    private void text(){
        user = new JTextField(20);
        pass = new JTextField(20);
        user_pass = new JPanel(new GridLayout(2,1));
        user_pass.setPreferredSize(new Dimension(50,50 ));
        user_pass.add(user);
        user_pass.add(pass);
    }
}
