package game;

import javax.swing.*;

public class Player {

    private int i = 0;
    private Icon playerimg;
    private JLabel playerlabel;
    private JPanel player;

    public Player(){

        playerimg = new ImageIcon("img/player/PR/player"+i+".png");
        playerlabel = new JLabel(playerimg);
        player = new JPanel();

        player.add(playerlabel);
        player.setOpaque(false);
    }


    public JPanel getPlayer() {
        return player;
    }

    public Icon getPlayerimg() {
        return playerimg;
    }

    public JLabel getPlayerlabel() {
        return playerlabel;
    }

    public void setPlayerimg(Icon playerimg) {
        this.playerimg = playerimg;
    }

    public void setPlayerlabel(JLabel playerlabel) {
        this.playerlabel = playerlabel;
    }
}
