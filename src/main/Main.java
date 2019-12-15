package main;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import user.Login;

public class Main {
    public static void main(String[] args){
        try
        {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;//普通不透明
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        }
        catch(Exception e)
        {
            //TODO exception
        }
        new Login();

    }
}
