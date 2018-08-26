package com.kai.Items.Weapons;
import com.kai.Enemies.Enemy;
import com.kai.Items.Item;
import com.kai.Player;
import com.kai.RoomHandler;
import sun.security.x509.DeltaCRLIndicatorExtension;

import java.awt.*;
import java.util.ArrayList;

public class Weapon extends Item {
    public String name, rarity;
    public Color crarity;
    public Image mySprite, myProjectileSprite;
    public int minDamage, maxDamage;
    public int projWidth, projHeight;
    public int range;
    public int numberOfShots;
    public boolean piercing;
    //public int accuracy;
    public double projSpeed;
    public double attacksPerSecond;
    public ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
    int lineY2 = 350;


    public Weapon(String name, String rarity, int armorBonus, int speedBonus, int vitalityBonus, int maxhpBonus, int strengthBonus, int dexterityBonus, int x, int y, boolean piercing, Image mySprite, Image myProjectileSprite, int minDamage, int maxDamage, int projWidth, int projHeight, int range, int numberOfShots, double projSpeed, double attacksPerSecond) {
        super("Weapon", x, y,armorBonus,speedBonus,vitalityBonus,maxhpBonus,strengthBonus,dexterityBonus);
        this.name = name;
        this.rarity = rarity;
        this.piercing = piercing;
        this.mySprite = mySprite;
        this.myProjectileSprite = myProjectileSprite;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.projWidth = projWidth;
        this.projHeight = projHeight;
        this.range = range;
        this.numberOfShots = numberOfShots;
        this.projSpeed = projSpeed;
        this.attacksPerSecond = attacksPerSecond;

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

        if (armorBonus != 0) {
            lineY2 += 30;
        }
        if (speedBonus != 0) {
            lineY2 += 30;
        }
        if (vitalityBonus != 0) {
            lineY2 += 30;
        }
        if (maxhpBonus != 0) {
            lineY2 += 30;
        }
        if (strengthBonus != 0) {
            lineY2 += 30;
        }
        if (dexterityBonus != 0) {
            lineY2 += 30;
        }

    }

    public void hit(Enemy i, int m, Projectile p) {
        i.takeDamage(RoomHandler.rand.nextInt(p.maxDamage - p.minDamage) +p.minDamage, Player.getCurrentWeapon().projectiles.get(m));
    }

    public void drawSelf(Graphics g) {
        ArrayList<Projectile> toRemove = new ArrayList<Projectile>();

        g.drawImage(mySprite, x, y, null);
        for (Projectile i: projectiles) {
            i.drawMe(g);
            if (!RoomHandler.checkCollisions(i.x,i.y,i.width,i.height)) {
                i.move();
            } else {
                toRemove.add(i);
            }
            if (i.currentRange > i.maxRange || i.x>1024) {
                toRemove.add(i);
            }
        }

        projectiles.removeAll(toRemove);
    }

    public void drawHUD(Graphics g) {
        g.setColor(new Color(50, 78, 105));

        int length = 530;
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
        g.fillRect(10,10,275,length);

        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 30));
        g.setColor(crarity);
        g.drawString(name, 25, 45);
        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 20));
        g.setColor(Color.white);
        g.drawString("Damage: " + String.valueOf(minDamage) + " - " + String.valueOf(maxDamage), 25, 80);
        g.drawString("Range: " + String.valueOf(range), 25, 110);
        g.drawString("Attacks Per Second: " + String.valueOf(attacksPerSecond), 25, 140);
        g.drawString("# of Projectiles: " + String.valueOf(numberOfShots), 25, 170);
        g.drawString("Bullet Size: " + String.valueOf(projWidth), 25, 200);
        g.drawString("Bullet Speed: " + String.valueOf(projSpeed), 25, 230);
        g.drawString("Piercing: " + String.valueOf(piercing), 25, 260);
        g.setColor(crarity);
        g.drawString("Rarity: " + rarity, 25, 290);
        g.setColor(new Color(237, 167,37));
        int lineY = 350;
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
            g.drawString(  this.isNegative(strengthBonus)+ strengthBonus + " Strength", 25, lineY);
            lineY += 30;
        }
        if (dexterityBonus != 0) {
            g.drawString(  isNegative(dexterityBonus)+ dexterityBonus + " Dexterity", 25, lineY);
            lineY += 30;
        }


    }



    //override this if necessary
    public void shoot(int x, int y) {
        if (numberOfShots == 1) {
            float dx = x - Player.getX();
            float dy = y - Player.getY();
            float angle = (float) Math.atan2(dy, dx);
            float vx = (float) (projSpeed * Math.cos(angle));
            float vy = (float) (projSpeed * Math.sin(angle));
            projectiles.add(new Projectile((int)(minDamage*(1+((double)(Player.STRENGTH)/2 * 0.1))), (int)(maxDamage*(1+((double)(Player.STRENGTH)/2 * 0.1))),(int) (Player.getX() + (0.5 * Player.width) - 0.5 * projWidth), (int) (Player.getY() + (0.5 * Player.height) - 0.5 * projHeight), range, projWidth, projHeight, vx, vy, myProjectileSprite));
        } else {
            for (int i = 0; i < numberOfShots; i++) {
                float dx = x - Player.getX();
                float dy = y - Player.getY();
                float angle = (float) Math.atan2(dy, dx);
                float vx = (float) (projSpeed * Math.cos(angle));
                float vy = (float) (projSpeed * Math.sin(angle));
                float vx2 = vx + (RoomHandler.rand.nextInt(11) - 5);
                float vy2 = vy + (RoomHandler.rand.nextInt(11) - 5);
                projectiles.add(new Projectile((int)(minDamage*(1+((double)(Player.STRENGTH)/2 * 0.1))), (int)(maxDamage*(1+((double)(Player.STRENGTH)/2 * 0.1))),(int) (Player.getX() + (0.5 * Player.width) - 0.5 * projWidth), (int) (Player.getY() + (0.5 * Player.height) - 0.5 * projHeight), range, projWidth, projHeight, vx2, vy2, myProjectileSprite));
            }
        }
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
