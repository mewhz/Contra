package main;

import game.Gui;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import user.Login;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args){
        try
       {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;//普通不透明
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible", false);

        }
       catch(Exception e)
        {
            //
        }
        new Login();
//        new Gui();
    }
}
