package com.kai.Items.Weapons;

import com.kai.Draw;
import com.kai.Enemies.Enemy;
import com.kai.Player;
import com.kai.RoomHandler;

import java.awt.*;

public class SnowBlaze extends Weapon {

    public SnowBlaze(int x, int y) {
        super("Snow Blaze", "Common",0,1,0,0,1,0,x,y,false,Draw.loadImage("SnowBlaze"),Draw.loadImage("SnowBlazeProj"),7,8,16,16,768,1,16,6);
    }

    @Override
    public void hit(Enemy i, int m, Projectile p) {
        i.takeDamage(RoomHandler.rand.nextInt(p.maxDamage - p.minDamage) + p.minDamage, Player.getCurrentWeapon().projectiles.get(m), 0.5);
    }

    @Override
    public void drawHUD(Graphics g) {
        super.drawHUD(g);
        g.setColor(new Color(50, 78, 105));
        g.fillRect(10,lineY2-18,275,50);

        g.setColor(Color.cyan);
        g.drawString("Slows enemies by 0.5 on hit", 25, lineY2+10);
    }
}
