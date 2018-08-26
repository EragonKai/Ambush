package com.kai.Items.Weapons;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class Projectile {
    public int x, y;
    public float vx, vy;
    public int width, height;
    public int minDamage, maxDamage;
    public double maxRange, currentRange;
    public Image mySprite;

    public Projectile(int minDamage, int maxDamage, int x, int y, int range, int width, int height, float vx, float vy, Image mySprite) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.vx = vx;
        this.vy = vy;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        maxRange = range;
        currentRange = 0;
        this.mySprite = mySprite;
    }

    public void drawMe(Graphics g) {
        g.drawImage(mySprite, x,  y, null);
    }

    public void move() {
        currentRange += Math.sqrt(((vx*vx) + (vy*vy)));
        x += vx;
        y += vy;
    }

    public boolean checkCollisions(int px, int py, int pwidth, int pheight) {
        if (px <= x + width && py <= y + height && px + pwidth >= x && py + pheight >= y) {
            return true;
        }
        return false;
    }

}
