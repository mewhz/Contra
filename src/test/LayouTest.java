package test;

import javax.swing.*;
import java.awt.*;

public class LayouTest extends JFrame {
    private JPanel user = new JPanel();
    private JPanel pass = new JPanel();
    private JLabel user_name = new JLabel("用户名");
    private JPanel user_pass1 = new JPanel();
    private JTextField user1 = new JTextField(20);
    private JTextField pass1 = new JTextField(20);
    private JButton jButton = new JButton("测试");
    public LayouTest(){
        this.setSize(new Dimension(800,600));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        user_pass();
        this.add(user_pass1,BorderLayout.CENTER);
        this.add(jButton,BorderLayout.SOUTH);
        this.setVisible(true);
    }
    private void user_pass(){
        user.add(user_name);
        user.add(user1);
        pass.add(pass1);
//        user2.setName("");
        user_pass1.add(user);
        user_pass1.add(pass1);
        user_pass1.setLayout(new FlowLayout(FlowLayout.CENTER,800,0));
    }
    public static void main(String[] args){
        new LayouTest();
    }
}