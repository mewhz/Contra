package user.button;

import user.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitButton implements ActionListener {

    private JButton exit;//退出按钮

    public ExitButton(){
        exit = Login.getExit();
        exit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);//结束当前虚拟机
    }
}
