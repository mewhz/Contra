package test;

import javafx.scene.text.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextTest extends JFrame {
    private JTextField user = new JTextField(30);
    private JButton jButton = new JButton("登录");
    public TextTest(){
        this.setSize(800,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(user.getText());
            }
        });
        this.add(user);
        this.add(jButton);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new TextTest();
    }
}
