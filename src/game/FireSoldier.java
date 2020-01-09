package game;

import javax.swing.*;

public class FireSoldier {
    private Icon fireSoldierimg;
    private JLabel fireSoldierlabel;
    private JPanel fireSoldier;

    public FireSoldier(){
        fireSoldierimg = new ImageIcon("img/fireSoldier/Soldier.png");
        fireSoldierlabel = new JLabel(fireSoldierimg);
        fireSoldier = new JPanel();

        fireSoldier.add(fireSoldierlabel);
        fireSoldier.setOpaque(false);
    }

    public Icon getFireSoldierimg() {
        return fireSoldierimg;
    }

    public void setFireSoldierimg(Icon fireSoldierimg) {
        this.fireSoldierimg = fireSoldierimg;
    }

    public JLabel getFireSoldierlabel() {
        return fireSoldierlabel;
    }

    public void setFireSoldierlabel(JLabel fireSoldierlabel) {
        this.fireSoldierlabel = fireSoldierlabel;
    }

    public JPanel getFireSoldier() {
        return fireSoldier;
    }

    public void setFireSoldier(JPanel fireSoldier) {
        this.fireSoldier = fireSoldier;
    }
}
