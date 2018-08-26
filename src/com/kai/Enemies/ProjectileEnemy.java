package com.kai.Enemies;
import com.kai.Items.Weapons.Projectile;
import com.kai.Player;
import com.kai.RoomHandler;

import java.awt.Graphics;
import java.awt.Image;
import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class ProjectileEnemy extends Enemy {
    public Image myProjectileImage;
    public ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
    public int projWidth, projHeight, projSpeed, range, aggroRange, permAggro;

    public ProjectileEnemy(String name, Image mySprite, Image mySprite2, int health, int maxHealth, int x, int y, int damage, double attacksPerSecond, double speed, int width, int height, Image myProjectileImage, int projWidth, int projHeight, int projSpeed, int range, int aggroRange, double score) {
        super(name, mySprite, mySprite2, health, maxHealth, x, y, damage, attacksPerSecond, speed, width, height, score);
        this.myProjectileImage = myProjectileImage;
        this.projWidth = projWidth;
        this.projHeight = projHeight;
        this.projSpeed = projSpeed;
        this.range = range;
        this.permAggro = aggroRange;
        this.aggroRange = aggroRange;
    }

    @Override
    public void drawSelf(Graphics g) {
        super.drawSelf(g);
        for (Projectile i: projectiles) {
            i.drawMe(g);
        }


    }

    @Override
    public void chase(int px, int py) {
        double a = Math.abs(x - px);
        double b = Math.abs(y - py);
        double distance = Math.sqrt((a * a) + (b * b));
        float angle = (float) Math.atan2(py - y, px - x);
        float vx = (float) Math.cos(angle);
        float vy = (float) Math.sin(angle);
        if (aggroRange < distance) {

            if (!RoomHandler.checkCollisions((int) (x + (vx * speed)), (int) (y + (vy * speed)), width, height)) {
                x += vx * speed;
                y += vy * speed;
                aggroRange = permAggro;
            } else {
                super.chase(px, py);
            }
        } else if (RoomHandler.checkCollisions((int) (x + (vx * speed)), (int) (y + (vy * speed)), width, height)) {
            aggroRange = 0;
       // } else if (!RoomHandler.checkCollisions((int) (x - (vx * speed)), (int) (y - (vy * speed)), width, height)) {
         //   x -= vx * speed;
        //    y -= vy * speed;
        }
    }

    @Override
    public void attack() {
        if (shotCounter < 0) {
            float dx = Player.getX() - x;
            float dy = Player.getY() - y;
            float angle = (float)Math.atan2(dy, dx);
            float vx = (float)(projSpeed * Math.cos(angle));
            float vy = (float)(projSpeed * Math.sin(angle));
            projectiles.add(new Projectile(damage, damage,(int)(x+(0.5*width)), (int)(y+(0.5*height)), range, projWidth, projHeight, vx, vy, myProjectileImage));
            shotCounter = (int)(100 / attacksPerSecond);
        }

       checkBulletCollisions();
    }

    //fix for projectiles
    public void checkBulletCollisions() {
        ArrayList<Projectile> toRemove = new ArrayList<Projectile>();
        for (Projectile i: projectiles) {
            if (i.checkCollisions(Player.getX(), Player.getY(), Player.width, Player.height)) {
                Player.takeDamage(damage);
                Player.setKilledBy(name);
                toRemove.add(i);
            }
            if (!RoomHandler.checkCollisions(i.x, i.y, i.width, i.height)) {
                i.move();
            } else {
                toRemove.add(i);
            }
            if (i.currentRange > i.maxRange || i.x > 1024) {
                toRemove.add(i);
            }
        }
        projectiles.removeAll(toRemove);
    }

    public abstract void dropLoot();
}
