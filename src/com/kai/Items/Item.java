package com.kai.Items;

import java.awt.*;

public abstract class Item {
    public String type;
    public int x, y;
    public int armorBonus, speedBonus, vitalityBonus, maxhpBonus, strengthBonus, dexterityBonus;

    public Item(String type, int x, int y, int armorBonus, int speedBonus, int vitalityBonus, int maxhpBonus, int strengthBonus, int dexterityBonus) {
        this.x = x;
        this.y = y;
        this.armorBonus = armorBonus;
        this.speedBonus = speedBonus;
        this.vitalityBonus = vitalityBonus;
        this.maxhpBonus = maxhpBonus;
        this.strengthBonus = strengthBonus;
        this.dexterityBonus = dexterityBonus;
        this.type = type;
    }

    public abstract void drawSelf(Graphics g);
    public abstract void drawHUD(Graphics g);

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String isNegative(int x) {
        if (x < 0) {
            return "-";
        }
        return "+";
    }
}
