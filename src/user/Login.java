package user;


import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
    private JPanel login_exit;//放置登录和退出按钮
    private JPanel user_text;//放置用户名
    private JPanel pass_text;//放置密码
    private JPanel userpass;//放置用户名和密码的JPanel
    private JPanel img_panel;//放置图片

    private JLabel user_name;//用户名的文本
    private JLabel pass_name;//密码的文本
    private JLabel title_img;//头部的图片

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

        button();//生成底部的按钮
        user_pass();//生成输入框和文本
        img();

        this.add(login_exit,BorderLayout.SOUTH);
        this.add(userpass,BorderLayout.CENTER);
        this.add(img_panel,BorderLayout.NORTH);

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
    //用户名密码
    private void user_pass(){
        user_text = new JPanel();
        pass_text = new JPanel();

        user_name = new JLabel("用户名：");
        pass_name = new JLabel("密  码：");

        user = new JTextField(30);
        pass = new JPasswordField(30);

        userpass = new JPanel();

        userpass.setLayout(new FlowLayout(FlowLayout.CENTER, 800, 0));

        user_text.add(user_name);
        user_text.add(user);

        pass_text.add(pass_name);
        pass_text.add(pass);

        userpass.add(user_text);
        userpass.add(pass_text);
    }
    //图片
    private void img(){
        title_img = new JLabel(new ImageIcon("/img/title.gif"));
        img_panel = new JPanel();

        img_panel.add(title_img);
        img_panel.setSize(new Dimension(100,200));
    }
}
