package test;

import javax.swing.*;

public class NoGunSoldierTest extends JFrame {
    private NoGunSoldier noGunSoldierclass = new NoGunSoldier();
    private JPanel NoGunSoldier = noGunSoldierclass.getNoGunSoldier();

    public NoGunSoldierTest(){
        this.setSize(800,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.add(NoGunSoldier);
        NoGunSoldier.setBounds(100,100,200,200);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new NoGunSoldierTest();
    }
}
