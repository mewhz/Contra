package test;

import javax.swing.*;

public class NoGunSoldier {
    private JPanel noGunSoldier;
    private Icon noGunSoldierimg;
    private JLabel noGunSoldierlabel;

    public NoGunSoldier(){
        noGunSoldierimg = new ImageIcon("img/NoGunSoldier/npc1.png");
        noGunSoldier = new JPanel();
        noGunSoldierlabel = new JLabel(noGunSoldierimg);
        noGunSoldier.add(noGunSoldierlabel);
        noGunSoldier.setOpaque(false);
    }

    public JPanel getNoGunSoldier() {
        return noGunSoldier;
    }

    public void setNoGunSoldier(JPanel noGunSoldier) {
        this.noGunSoldier = noGunSoldier;
    }

    public Icon getNoGunSoldierimg() {
        return noGunSoldierimg;
    }

    public void setNoGunSoldierimg(Icon noGunSoldierimg) {
        this.noGunSoldierimg = noGunSoldierimg;
    }

    public JLabel getNoGunSoldierlabel() {
        return noGunSoldierlabel;
    }

    public void setNoGunSoldierlabel(JLabel noGunSoldierlabel) {
        this.noGunSoldierlabel = noGunSoldierlabel;
    }
}
