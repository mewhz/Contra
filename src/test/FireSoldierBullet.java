package test;

import javax.swing.*;

public class FireSoldierBullet {
    private Icon fireSoldierBulletimg;
    private JLabel fireSoldierBulletlabel;
    private JPanel fireSoldierBullet;

    public FireSoldierBullet(){
        fireSoldierBulletimg = new ImageIcon("img/fireSoldier/bullet.png");
        fireSoldierBulletlabel = new JLabel(fireSoldierBulletimg);
        fireSoldierBullet = new JPanel();

        fireSoldierBullet.add(fireSoldierBulletlabel);
        fireSoldierBullet.setOpaque(false);
    }

    public Icon getFireSoldierBulletimg() {
        return fireSoldierBulletimg;
    }

    public void setFireSoldierBulletimg(Icon fireSoldierBulletimg) {
        this.fireSoldierBulletimg = fireSoldierBulletimg;
    }

    public JLabel getFireSoldierBulletlabel() {
        return fireSoldierBulletlabel;
    }

    public void setFireSoldierBulletlabel(JLabel fireSoldierBulletlabel) {
        this.fireSoldierBulletlabel = fireSoldierBulletlabel;
    }

    public JPanel getFireSoldierBullet() {
        return fireSoldierBullet;
    }

    public void setFireSoldierBullet(JPanel fireSoldier) {
        this.fireSoldierBullet = fireSoldier;
    }
}
