package user.button;

import javax.swing.*;

public class LoginButton {

    private String user;//用户名
    private String pass;//密码

    public LoginButton(String user, String pass) {
        this.user = user;
        this.pass = pass;
        judge();
    }
    public void judge(){
        if(user.equals("root")){
            if(pass.equals("root")){
                JOptionPane.showMessageDialog(null,"登录成功", "我是一个提示框", JOptionPane.PLAIN_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null,"密码错误", "我是一个提示框", JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"用户不存在", "我是一个提示框", JOptionPane.ERROR_MESSAGE);
        }
    }
}