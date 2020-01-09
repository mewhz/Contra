package test;

import javax.swing.*;

public class Bullet {
    private Icon bulletimg;
    private JLabel bulletlabel;
    private JPanel bullet;

    public Bullet(){

        bulletimg = new ImageIcon("img/bullet/bullet3.png");
        bulletlabel = new JLabel(bulletimg);
        bullet = new JPanel();

        bullet.add(bulletlabel);
        bullet.setOpaque(false);
    }

    public Icon getBulletimg() {
        return bulletimg;
    }

    public void setBulletimg(Icon bulletimg) {
        this.bulletimg = bulletimg;
    }

    public JLabel getBulletlabel() {
        return bulletlabel;
    }

    public void setBulletlabel(JLabel bulletlabel) {
        this.bulletlabel = bulletlabel;
    }

    public JPanel getBullet() {
        return bullet;
    }

    public void setBullet(JPanel bullet) {
        this.bullet = bullet;
    }
}