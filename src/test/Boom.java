package test;

import javax.swing.*;

public class Boom {
    private Icon boomimg;
    private JLabel boomlabel;
    private JPanel boom;

    public Boom(){}
    public Boom(String url){
        boomimg = new ImageIcon(url);
        boomlabel = new JLabel(boomimg);
        boom = new JPanel();

        boom.add(boomlabel);
        boom.setOpaque(false);
    }

    public Icon getBoomimg() {
        return boomimg;
    }

    public void setBoomimg(Icon boomimg) {
        this.boomimg = boomimg;
    }

    public JLabel getBoomlabel() {
        return boomlabel;
    }

    public void setBoomlabel(JLabel boomlabel) {
        this.boomlabel = boomlabel;
    }

    public JPanel getBoom() {
        return boom;
    }

    public void setBoom(JPanel boom) {
        this.boom = boom;
    }
}
