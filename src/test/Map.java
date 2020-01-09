package test;

import javax.swing.*;

public class Map  {

    private Icon mapimg;
    private JLabel maplabel;
    private JPanel map;

    public Map(){
        mapimg = new ImageIcon("img/MAP/map.png");
        maplabel = new JLabel(mapimg);
        map = new JPanel();
        map.add(maplabel);
    }
    public JPanel getMap(){
        return map;
    }

    public Icon getMapimg() {
        return mapimg;
    }

    public JLabel getMaplabel() {
        return maplabel;
    }

    public void setMaplabel(JLabel maplabel) {
        this.maplabel = maplabel;
    }

    public void setMapimg(Icon mapimg) {
        this.mapimg = mapimg;
    }
}
