package user;


import game.Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {

    private JPanel login_exit;//放置登录和退出按钮
    private JPanel user_text;//放置用户名
    private JPanel pass_text;//放置密码
    private JPanel userpass;//放置用户名和密码的JPanel
    private JPanel img_panel;//放置图片

    private JLabel user_name;//用户名的文本
    private JLabel pass_name;//密码的文本
    private JLabel title_img;//头部的图片

    private ImageIcon img;//图片

    private static JButton login;//登录
    private static JButton exit;//退出

    private static JTextField user;//用户名
    private static JTextField pass;//密码

    private final Font font = new Font("华文行楷",Font.PLAIN,20);//字体
    private final String url = "img/title.gif";//图片路径

    //构造方法
    public Login(){

        this.setTitle("欢迎登录简易魂斗罗游戏系统");
        this.setSize(new Dimension(800,600));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        button();//生成底部的按钮
        user_pass();//生成输入框和文本
        img();//生成头部图片

        this.add(login_exit,BorderLayout.SOUTH);//南
        this.add(userpass,BorderLayout.CENTER);//中间
        this.add(img_panel,BorderLayout.NORTH);//北

        this.setLocationRelativeTo(null);//使窗口在中央显示

        new ExitButton();//退出按钮优化

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                judge(user.getText(),pass.getText());
            }
        });

        this.setVisible(true);
    }
    //按钮
    private void button(){
        login_exit = new JPanel(new FlowLayout());

        login = new JButton("登录");
        exit  = new JButton("退出");

        login.setFont(font);
        exit.setFont(font);

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
        pass_name = new JLabel("密    码：");

        user = new JTextField(30);
        pass = new JPasswordField(30);

        userpass = new JPanel();

        userpass.setLayout(new FlowLayout(FlowLayout.CENTER, 800, 0));

        user_name.setFont(font);
        pass_name.setFont(font);
        user.setFont(font);
        pass.setFont(font);

        user_text.add(user_name);
        user_text.add(user);

        pass_text.add(pass_name);
        pass_text.add(pass);

        userpass.add(user_text);
        userpass.add(pass_text);
    }
    //图片
    private void img(){
        img = new ImageIcon(url);
        title_img = new JLabel(img);
        img_panel = new JPanel();

        img.setImage(img.getImage().getScaledInstance(800,200,Image.SCALE_DEFAULT));//修改图片大小
        img_panel.add(title_img);

    }

    //判断对错
    public void judge(String user, String pass){
        if(user.equals("root")){
            if(pass.equals("root")){
                JOptionPane.showMessageDialog(null,"登录成功", "我是一个提示框", JOptionPane.PLAIN_MESSAGE);
                this.setVisible(false);
                new Gui();
            }
            else{
                JOptionPane.showMessageDialog(null,"密码错误", "我是一个提示框", JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"用户不存在", "我是一个提示框", JOptionPane.ERROR_MESSAGE);
        }
    }

    //返回登录按钮
    public static JButton getLogin(){
        return login;
    }

    //返回退出按钮
    public static JButton getExit(){
        return exit;
    }

}
