package com.kai.Enemies;

import com.kai.Draw;
import com.kai.Items.Rings.StarSandRing;
import com.kai.Items.Weapons.BloodSoul;
import com.kai.Items.Weapons.Projectile;
import com.kai.Player;
import com.kai.RoomHandler;

import java.util.Random;

public class BloodTurret extends ProjectileEnemy {
    String dire;

    public BloodTurret(int x, int y, String dir){
        super("Blood Turret", Draw.loadImage("BloodTurret1"), Draw.loadImage("BloodTurret2"),10,10,x,y,25,0.2+(0.7-0.2)*RoomHandler.rand.nextDouble(),0,64,64,Draw.loadImage("BloodTurretProj"),16,16,4,384,1024,0.25);
        if (dir == "down") {
            this.mySprite = Draw.loadImage("BloodTurret2-1");
            this.mySprite2 = Draw.loadImage("BloodTurret2-2");
        }
        this.dire = dir;
    }

    @Override
    public void attack() {
            if (shotCounter < 0) {
                if (dire == "up") {
                    projectiles.add(new Projectile(damage,damage,(int) (x + (0.5 * width)), (int) (y + (0.5 * height)), range, projWidth, projHeight, 0, (-1 * projSpeed), myProjectileImage));
                } else {
                    projectiles.add(new Projectile(damage,damage,(int) (x + (0.5 * width)), (int) (y + (0.5 * height)), range, projWidth, projHeight, 0, (projSpeed), myProjectileImage));
                }
                shotCounter = (int)(100 / attacksPerSecond);
            }

            checkBulletCollisions();
    }


    public void dropLoot() {
        if (!dead) {
            dead = true;
            int dropChoice = RoomHandler.rand.nextInt(999) + 1;
            if (dropChoice > 995) { //995
                RoomHandler.addItemDrop(new StarSandRing(x, y));
            }
        }
    }

}
