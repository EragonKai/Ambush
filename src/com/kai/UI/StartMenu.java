package com.kai.UI;

import com.kai.Draw;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.awt.Image;

public class StartMenu {
    private static Image self;

    public StartMenu() {
        self = Draw.loadImage("StartMenu");
    }

    public static void drawSelf(Graphics g) {
        g.drawImage(self, 0, 0, null);
        g.setFont(new Font(g.getFont().getFontName(), Font.ITALIC, 155));
        g.setColor(new Color(169, 204, 242));
        g.drawString("AMBUSH", 280, 312);
        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 70));
        g.setColor(new Color(58, 58, 188));
        g.drawString("click to play", 428, 600);

    }

}
