package com.kai.Enemies;
import com.kai.Items.Weapons.Projectile;
import com.kai.Player;
import com.kai.RoomHandler;

import java.awt.*;
import java.util.ArrayList;

public abstract class Enemy {
    public String name;
    public Image mySprite, mySprite2, currentImage;
    public int health, maxHealth;
    public int x, y;
    double attacksPerSecond;
    public int damage;
    public double speed;
    public int animationTick, animationSpeed, shotCounter;
    public int height, width;
    public boolean dead;
    public double score;

    public Enemy(String name, Image mySprite, Image mySprite2, int health, int maxHealth, int x, int y, int damage, double attacksPerSecond, double speed, int height, int width, double score) {
        this.name = name;
        this.mySprite = mySprite;
        this.mySprite2 = mySprite2;
        this.health = health;
        this.maxHealth = maxHealth;
        this.x = x;
        this.y = y;
        this.damage = damage;
        this.attacksPerSecond = attacksPerSecond;
        this.speed = speed;
        this.score = score;

        animationSpeed = 8;
        this.width = width;
        this.height = height;

        shotCounter = (int)(100 / attacksPerSecond);
    }

    public void drawSelf(Graphics g) {
        g.drawImage(currentImage, x, y, null);
        g.setColor(Color.red);
        g.fillRect(x + 4, y - 20, (int)(((width-8) * ((double)health / (double)maxHealth))), 15);

        animationTick++;
        if (animationTick%animationSpeed == 0) {
            animationTick = 0;
            if (currentImage == mySprite) {
                currentImage = mySprite2;
            } else if (currentImage == mySprite2) {
                currentImage = mySprite;
            } else {
                currentImage = mySprite;
            }
        }


        shotCounter--;
        if (shotCounter < -1) {
            shotCounter = -1;
        }
    }



    //can override;
    public void chase(int px, int py) {
        float angle = (float) Math.atan2(py - y, px - x);
        float vx = (float) Math.cos(angle);
        float vy = (float) Math.sin(angle);
        if (!RoomHandler.checkCollisions((int) (x + (vx * speed)), (int) (y + (vy * speed)), width, height)) {
                x += vx * speed;
                y += vy * speed;
            }
    }



    public boolean checkCollisions(int px, int py, int pwidth, int pheight) {
        if (px <= x+width && py <= y+height && px+pwidth >= x && py+pheight >= y) {
            return true;
        }
        return false;
    }

    //override for projectile
    public void attack()  {
        if (checkCollisions(Player.getX(), Player.getY(), Player.width, Player.height)) {
            if (shotCounter < 0) {
                Player.takeDamage(damage);
                Player.setKilledBy(name);
                shotCounter = (int)(100 / attacksPerSecond);
            }
        }
    }

    ArrayList<Projectile> alreadyBeenHitBy = new ArrayList<Projectile>();
    public void takeDamage (int dmg, Projectile p, double speedDebuff) {
        if (!alreadyBeenHitBy.contains(p)) {
            health -= dmg;
            speed -= speedDebuff;
            if (speed < 0) {
                speed = 0;
            }
            alreadyBeenHitBy.add(p);
        }
    }


    public void takeDamage(int dmg, Projectile p) {
        if (!alreadyBeenHitBy.contains(p)) {
            health -= dmg;
            alreadyBeenHitBy.add(p);
        }
    }

    public abstract void dropLoot();

}

