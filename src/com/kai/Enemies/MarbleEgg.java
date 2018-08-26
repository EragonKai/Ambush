package com.kai.Enemies;

import com.kai.Draw;
import com.kai.Items.Weapons.Projectile;
import com.kai.Player;
import com.kai.RoomHandler;

public class MarbleEgg extends ProjectileEnemy {

    public MarbleEgg(int x, int y) {
        super("Marble Egg", Draw.loadImage("Marble Egg"), Draw.loadImage("Marble Egg 2"),35,35,x,y,25,0.4+(1.8-0.4)*RoomHandler.rand.nextDouble(),0,64,64,Draw.loadImage("Marble Egg Proj"),16,16,6,600,512,5);
        shotCounter = RoomHandler.rand.nextInt(200)+50;
    }
/*
    @Override
    public void attack() {
        if (shotCounter < 0) {
            float dx = Player.getX() - x;
            float dy = Player.getY() - y;
            float angle = (float)Math.atan2(dy, dx);
            float vx = (float)(projSpeed * Math.cos(angle));
            float vy = (float)(projSpeed * Math.sin(angle));
            projectiles.add(new Projectile(damage, damage,(int)(x+(0.5*width)), (int)(y+(0.5*height)), range, projWidth, projHeight, vx-1, vy-1, myProjectileImage));
            projectiles.add(new Projectile(damage, damage,(int)(x+(0.5*width)), (int)(y+(0.5*height)), range, projWidth, projHeight, vx+1, vy+1, myProjectileImage));
            shotCounter = (int)(100 / attacksPerSecond);
        }

        checkBulletCollisions();
    }*/

    public void dropLoot() {
        if (!dead) {
            dead = true;
            RoomHandler.enemies.add(new MarbleDarken(x,y));
        }
    }

}
