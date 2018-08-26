package com.kai.Items.Rings;

import com.kai.Items.Item;

import java.awt.*;

public class Ring extends Item {
    public Image mySprite;
    String name, rarity;
    Color crarity;

    public Ring(String name, String rarity, Image mySprite, int x, int y, int armorBonus, int speedBonus, int vitalityBonus, int maxhpBonus, int strengthBonus, int dexterityBonus) {
        super("Ring", x, y, armorBonus, speedBonus, vitalityBonus, maxhpBonus, strengthBonus, dexterityBonus);
        this.mySprite = mySprite;
        this.name = name;
        this.rarity = rarity;

        switch (rarity) {
            case "Common":
                crarity = Color.white;
                break;
            case "Uncommon":
                crarity = Color.CYAN;
                break;
            case "Rare":
                crarity = Color.red;
                break;
            case "Legendary":
                crarity = Color.yellow;
                break;
        }
    }

    public void drawSelf(Graphics g) {
        g.drawImage(mySprite,x,y,null);
    }

    public void drawHUD(Graphics g) {
        g.setColor(new Color(50, 78, 105));

        int length = 265;
        if (armorBonus == 0) {
            length -= 30;
        }
        if (speedBonus == 0) {
            length -= 30;
        }
        if (vitalityBonus == 0) {
            length -= 30;
        }
        if (strengthBonus == 0) {
            length -= 30;
        }
        if (dexterityBonus == 0) {
            length -= 30;
        }
        if (maxhpBonus == 0) {
            length -= 30;
        }

        g.fillRect(10,10,(16*name.length()), 50);
        g.fillRect(10,10,225,length);

        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 30));
        g.setColor(crarity);
        g.drawString(name, 25, 45);
        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 20));
        g.setColor(Color.white);
        g.setColor(crarity);
        g.drawString("Rarity: " + rarity, 25, 75);
        g.setColor(new Color(237, 167,37));
        int lineY = 105;
        if (maxhpBonus != 0) {
            g.drawString(isNegative(maxhpBonus) + maxhpBonus + " Max HP", 25, lineY);
            lineY += 30;
        }
        if (armorBonus != 0) {
            g.drawString(isNegative(armorBonus) + armorBonus + " Armor", 25, lineY);
            lineY += 30;
        }
        if (speedBonus != 0) {
            g.drawString(isNegative(speedBonus) + speedBonus + " Speed", 25, lineY);
            lineY += 30;
        }
        if (vitalityBonus != 0) {
            g.drawString(isNegative(vitalityBonus) + vitalityBonus + " Vitality", 25, lineY);
            lineY += 30;
        }
        if (strengthBonus != 0) {
            g.drawString(  isNegative(strengthBonus)+ strengthBonus + " Strength", 25, lineY);
            lineY += 30;
        }
        if (dexterityBonus != 0) {
            g.drawString(  isNegative(dexterityBonus)+ dexterityBonus + " Dexterity", 25, lineY);
            lineY += 30;
        }
    }



}