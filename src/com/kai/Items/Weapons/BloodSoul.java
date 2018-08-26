package com.kai.Items.Weapons;

import com.kai.Draw;
import com.kai.Player;

import java.awt.*;

public class BloodSoul extends Weapon {

    private int shotCount;

    public BloodSoul(int x, int y) {
        super("Blood Soul", "Rare", 0,0,2,40,1,0,x,y,true,Draw.loadImage("BloodSoul"),Draw.loadImage("BloodSoulProj"),5,16,16,16,768,1,8,1.5);
        shotCount = 1;
    }

    @Override
    public void shoot(int x, int y) {
        float dx = x - Player.getX();
        float dy = y - Player.getY();
        float angle = (float) Math.atan2(dy, dx);
        float vx = (float) (projSpeed * Math.cos(angle));
        float vy = (float) (projSpeed * Math.sin(angle));
        shotCount++;
        if (shotCount % 4 == 0) {
            shotCount = 1;
            projectiles.add(new Projectile(10,48,(int) (Player.getX() + (0.5 * Player.width) - 0.5 * 32), (int) (Player.getY() + (0.5 * Player.height) - 0.5 * 32), range, 32, 32, vx, vy, Draw.loadImage("BloodSoulProj2")));
        } else {
            projectiles.add(new Projectile(minDamage, maxDamage,(int) (Player.getX() + (0.5 * Player.width) - 0.5 * projWidth), (int) (Player.getY() + (0.5 * Player.height) - 0.5 * projHeight), range, projWidth, projHeight, vx, vy, myProjectileSprite));
        }
    }

    @Override
    public void drawHUD(Graphics g) {
        super.drawHUD(g);
        g.setColor(new Color(50, 78, 105));
        g.fillRect(10,lineY2-18,305,40);

        g.setColor(Color.red);
        g.drawString("Deals 3x damage every 3rd shot", 25, lineY2+10);
    }
}
