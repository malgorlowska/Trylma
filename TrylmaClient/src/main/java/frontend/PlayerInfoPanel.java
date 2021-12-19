package frontend;

import board.PlayerColor;

import javax.swing.*;
import java.awt.*;

public class PlayerInfoPanel extends JPanel {
    JLabel playerColorInfo;
    JLabel descriptionPlayer;
    JLabel currentPlayerInfo;
    JLabel descriptionCurrent;

    public PlayerInfoPanel(String playerColor) {
        this.setOpaque(true);
        this.descriptionPlayer = new JLabel("       YOU ARE PLAYING WITH :");
        this.descriptionPlayer.setForeground(Color.WHITE);

        this.playerColorInfo = new JLabel("       " + playerColor);
        this.playerColorInfo.setForeground(Color.LIGHT_GRAY);

        this.descriptionCurrent = new JLabel("       CURRENT PLAYER IS :");
        this.descriptionCurrent.setForeground(Color.WHITE);

        this.currentPlayerInfo = new JLabel();
        this.currentPlayerInfo.setForeground(Color.LIGHT_GRAY);

        this.setLayout(new GridLayout(20,1));
        this.setPreferredSize(new Dimension(200,0));
        this.add(Box.createRigidArea(new Dimension(0, 50)));
        this.add(descriptionPlayer);
        this.add(playerColorInfo);
        this.add(Box.createRigidArea(new Dimension(0, 50)));
        this.add(descriptionCurrent);
        this.add(currentPlayerInfo);
        this.setBackground(Color.DARK_GRAY);
    }

}
