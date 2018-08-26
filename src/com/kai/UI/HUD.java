package com.kai.UI;
import com.kai.Draw;
import com.kai.Input;
import com.kai.Player;

import java.awt.*;

public class HUD {
    private static Image self;

    public static boolean displayWeapon;

    public static int INVENTORY1XPOS = 1158;
    public static int INVENTORY1YPOS = 86;

    public static int[][] inventoryPos = {
            {0,0},
            {1058,454},
            {1158,454}
    };


    public static int HELMET1XPOS = 1058;
    public static int HELMET1YPOS = 534;

    public static int CHESTPLATE1XPOS = 1158;
    public static int CHESTPLATE1YPOS = 534;

    public static int PANTS1XPOS = 1058;
    public static int PANTS1YPOS = 614;

    public static int BOOTS1XPOS = 1158;
    public static int BOOTS1YPOS = 614;

    public HUD() {
        self = Draw.loadImage("HUD");
    }

    public static void drawSelf(Graphics g) {
        g.drawImage(self, 1024, 0, null);
        g.setColor(Color.red);
        g.fillRect(1049, 45 + (int)(210 - (210/(double)Player.maxHealth*Player.health)),50, (int)((210/(double)Player.maxHealth) * Player.health));
        //g.setColor(Color.CYAN);
        //g.fillRect(1149, 45 + (int)(210 - (210/(double)Player.maxEnergy*Player.energy)), 50, (int)((210/(double)Player.maxEnergy) * Player.energy));

        if (displayWeapon) {
            Input.weaponHUDDisplay.drawHUD(g);
        }

        g.setColor(Color.white);
        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 20));
        g.drawString(String.valueOf(Player.health),1055,225);
        g.drawString("-----", 1055,235);
        g.drawString(String.valueOf(Player.maxHealth),1055,250);

        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 20));
        g.setColor(Color.white);
        g.drawString("Armor:", 1060, 310);
        g.drawString("Speed:", 1060, 335);
        g.drawString("Vitality:", 1060, 360);
        g.drawString("Strength:", 1060, 385);
        g.drawString("Dexterity:",1060,410);
        g.setColor(new Color(237, 167,37));
        g.drawString(String.valueOf(Player.ARMOR), 1170, 310);
        g.drawString(String.valueOf(Player.SPEED), 1170, 335);
        g.drawString(String.valueOf(Player.VITALITY), 1170, 360);
        g.drawString(String.valueOf(Player.STRENGTH), 1170, 385);
        g.drawString(String.valueOf(Player.DEXTERITY), 1170, 410);
    }


}
