package com.kai.Enemies;

import com.kai.Draw;
import com.kai.Items.Rings.DaredevilRing;
import com.kai.Items.Weapons.MossCoveredWand;
import com.kai.Items.Weapons.Projectile;
import com.kai.Items.Weapons.ShatteredSerpentScepter;
import com.kai.Player;
import com.kai.RoomHandler;

public class VineJellyfish extends ProjectileEnemy {

    public VineJellyfish(int x, int y) {
        super("Vine JellyFish", Draw.loadImage("VineJellyfish1"), Draw.loadImage("VineJellyfish2"),50,50,x,y,7,0.8,6.5,64,64,Draw.loadImage("VineJellyfishProj"),32,32,7,384, 300,6);
    }

    @Override
    public void attack() {
        if (shotCounter < 0) {
            for (int i = 0; i < 6; i++) {
                float dx = Player.getX() - x;
                float dy = Player.getY() - y;
                float angle = (float) Math.atan2(dy, dx);
                float vx = (float) (projSpeed * Math.cos(angle));
                float vy = (float) (projSpeed * Math.sin(angle));
                float vx2 = vx + (RoomHandler.rand.nextInt(11) - 5);
                float vy2 = vy + (RoomHandler.rand.nextInt(11) - 5);
                projectiles.add(new Projectile(damage,damage,(int) (x + (0.5 * width)), (int) (y + (0.5 * height)), range, projWidth, projHeight, vx2, vy2, myProjectileImage));
            }

            shotCounter = (int)(100 / attacksPerSecond);
        }

        checkBulletCollisions();
    }

    public void dropLoot() {
        if (!dead) {
            dead = true;
            int dropChoice = RoomHandler.rand.nextInt(999) + 1;
            if (dropChoice > 875) { //875
                RoomHandler.addItemDrop(new MossCoveredWand(x, y));
            } else if (dropChoice > 575) {
                RoomHandler.addItemDrop(new DaredevilRing(x,y));
            }
        }
    }

}
