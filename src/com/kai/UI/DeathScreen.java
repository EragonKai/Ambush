package com.kai.UI;

import com.kai.Draw;
import com.kai.Player;

import java.awt.*;

public class DeathScreen {
    public static Image self;

    public DeathScreen() {
        self = Draw.loadImage("DeathScreen");
    }

    public static void drawMe(Graphics g) {
        g.drawImage(self,0,0,null);

        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 22));
        g.setColor(new Color(58, 58, 188));
        g.drawString("You have died!", 468,362);
        g.drawString("Killed By: " + Player.getKilledBy(), 468,392);
        g.drawString("Score: " + Player.getScore(), 468, 422);

    }

}
