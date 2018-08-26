package com.kai;
import com.kai.Items.Item;
import com.kai.Items.Rings.MarbleRing;
import com.kai.Items.Rings.StartingRing;
import com.kai.Items.Weapons.*;
import com.kai.UI.HUD;

import java.awt.Graphics;
import java.awt.Image;

import java.util.Random;

public class Player {
    private static int x, y;
    private int speed;
    public static int width, height;
    private static boolean moveLeft, moveRight, moveUp, moveDown;

    //used to measure aps on the weapon
    private static int shotCounter, shotCounter2, vitalityCount;
    private int animationTick, animationSpeed;

    public static int energy, health, maxHealth, maxEnergy;
    public static int ARMOR, VITALITY, SPEED, DEXTERITY, STRENGTH;

    Image playerIdle, player1, player2;
    Image currentImage;

    public static Item[] inventory = new Item[6];
    public static Weapon currentWeapon;

    public static String killedBy;
    public static int Score;

    Random rand;

    public Player() {
        playerIdle = Draw.loadImage("playerIdle");
        player1 = Draw.loadImage("player1");
        player2 = Draw.loadImage("player2");


        currentImage = playerIdle;
        x = 80;
        y = 80;
        width = 64;
        height = 64;
        speed = 4;
        health = 100;
        maxHealth = 100;
        energy = 100;
        maxEnergy = 100;
        animationSpeed = 8;
        shotCounter = -1;
        rand = new Random();

        //between 1 and 2
        ARMOR = rand.nextInt(2)+1;
        //between 5 and 6
        SPEED = rand.nextInt(2)+5;
        //between 2 and 2
        VITALITY  = 2;
        //between 1 and 2
        STRENGTH = rand.nextInt(2)+1;
        //between 1 and 2
        DEXTERITY = rand.nextInt(2)+1;

        if (VITALITY > 0) {
            vitalityCount = 2*(100/VITALITY);
        }

        Player.equipItem(0,null,new StartingGun(HUD.INVENTORY1XPOS, HUD.INVENTORY1YPOS));
        Player.equipItem(1,new StartingRing(HUD.inventoryPos[1][0], HUD.inventoryPos[1][1]),null);
        Player.equipItem(2,new StartingRing(HUD.inventoryPos[2][0], HUD.inventoryPos[2][1]),null);

    }

    public void drawMe(Graphics g){

        g.drawImage(currentImage, x, y, null);

        if (health < 1) {
            Screen.GameState = "DEATHSCREEN";
        }

        if (vitalityCount < 0 && health < maxHealth && VITALITY>0) {
            health += 1;
            vitalityCount = 2*(100/VITALITY);
        } else if (vitalityCount < 0 && VITALITY < 0) {
            health -= 1;
            vitalityCount = 2*(100/(-1*VITALITY));
        }

    }

    public static void equipItem(int invSpot, Item item, Weapon weapon, int beingDraggedOGx, int beingDraggedOGy) {
        if (weapon != null) {
            health -= currentWeapon.maxhpBonus;

            Player.ARMOR -= Player.getCurrentWeapon().armorBonus;
            Player.SPEED -= Player.getCurrentWeapon().speedBonus;
            Player.VITALITY -= Player.getCurrentWeapon().vitalityBonus;
            Player.DEXTERITY -= Player.getCurrentWeapon().dexterityBonus;
            Player.STRENGTH -= Player.getCurrentWeapon().strengthBonus;
            Player.maxHealth -= Player.getCurrentWeapon().maxhpBonus;
            Player.getCurrentWeapon().setX(beingDraggedOGx);
            Player.getCurrentWeapon().setY(beingDraggedOGy);
            RoomHandler.itemsOnFloor.add(Player.getCurrentWeapon());
            RoomHandler.itemsOnFloor.remove(weapon);
            Player.setCurrentWeapon(weapon);
            Player.getCurrentWeapon().setX(HUD.INVENTORY1XPOS);
            Player.getCurrentWeapon().setY(HUD.INVENTORY1YPOS);
            Player.ARMOR += Player.getCurrentWeapon().armorBonus;
            Player.SPEED += Player.getCurrentWeapon().speedBonus;
            Player.VITALITY += Player.getCurrentWeapon().vitalityBonus;
            Player.DEXTERITY += getCurrentWeapon().dexterityBonus;
            Player.STRENGTH += getCurrentWeapon().strengthBonus;
            Player.maxHealth += getCurrentWeapon().maxhpBonus;

            health += getCurrentWeapon().maxhpBonus;
        } else {
            health -= inventory[invSpot].maxhpBonus;

            Player.ARMOR -= inventory[invSpot].armorBonus;
            Player.SPEED -= inventory[invSpot].speedBonus;
            Player.VITALITY -= inventory[invSpot].vitalityBonus;
            Player.DEXTERITY -= inventory[invSpot].dexterityBonus;
            Player.STRENGTH -= inventory[invSpot].strengthBonus;
            Player.maxHealth -= inventory[invSpot].maxhpBonus;
            Player.inventory[invSpot].setX(beingDraggedOGx);
            Player.inventory[invSpot].setY(beingDraggedOGy);
            RoomHandler.itemsOnFloor.add(inventory[invSpot]);
            RoomHandler.itemsOnFloor.remove(item);
            Player.inventory[invSpot] = (item);
            Player.inventory[invSpot].setX(HUD.inventoryPos[invSpot][0]);
            Player.inventory[invSpot].setY(HUD.inventoryPos[invSpot][1]);
            Player.ARMOR += Player.inventory[invSpot].armorBonus;
            Player.SPEED += Player.inventory[invSpot].speedBonus;
            Player.VITALITY += Player.inventory[invSpot].vitalityBonus;
            Player.DEXTERITY += Player.inventory[invSpot].dexterityBonus;
            Player.STRENGTH += Player.inventory[invSpot].strengthBonus;
            Player.maxHealth += Player.inventory[invSpot].maxhpBonus;

            health += Player.inventory[invSpot].maxhpBonus;

        }

        if (health > maxHealth) {
            health = maxHealth;
        }

    }



    public static void equipItem(int invSpot, Item item, Weapon weapon) {
        if (weapon != null) {
            Player.setCurrentWeapon(weapon);
            Player.ARMOR += Player.getCurrentWeapon().armorBonus;
            Player.SPEED += Player.getCurrentWeapon().speedBonus;
            Player.VITALITY += Player.getCurrentWeapon().vitalityBonus;
            Player.DEXTERITY += getCurrentWeapon().dexterityBonus;
            Player.STRENGTH += getCurrentWeapon().strengthBonus;
            Player.maxHealth += getCurrentWeapon().maxhpBonus;
        } else {
            Player.inventory[invSpot] = (item);
            Player.inventory[invSpot].setX(HUD.inventoryPos[invSpot][0]);
            Player.inventory[invSpot].setY(HUD.inventoryPos[invSpot][1]);
            Player.ARMOR += Player.inventory[invSpot].armorBonus;
            Player.SPEED += Player.inventory[invSpot].speedBonus;
            Player.VITALITY += Player.inventory[invSpot].vitalityBonus;
            Player.DEXTERITY += Player.inventory[invSpot].dexterityBonus;
            Player.STRENGTH += Player.inventory[invSpot].strengthBonus;
            Player.maxHealth += Player.inventory[invSpot].maxhpBonus;
        }
    }

    public void move() {
        speed = SPEED;


        if (moveLeft) {
            if (!RoomHandler.checkCollisions(x-speed, y, width, height)) {
                x -= speed;
            }
        }
        if (moveRight) {
            if (!RoomHandler.checkCollisions(x+speed, y, width, height)) {
                x += speed;
            }
        }
        if (moveUp) {
            if (!RoomHandler.checkCollisions(x, y-speed, width, height)) {
                y -= speed;
            }
        }
        if (moveDown) {
            if (!RoomHandler.checkCollisions(x, y+speed, width, height)) {
                y += speed;
            }
        }

        if (x > 1024) {
            RoomHandler.changeRooms("right");
            this.x = 100;
            this.y = 368;
        }
        if (y > 768) {
            RoomHandler.changeRooms("down");
            this.x = 496;
            this.y = 100;
        }
        if (x < 0) {
            RoomHandler.changeRooms("left");
            this.x = 888;
            this.y = 368;
        }
        if (y < 0) {
            RoomHandler.changeRooms("up");
            this.x = 496;
            this.y = 630;
        }

        if (!moveLeft && !moveRight && !moveUp && !moveDown) {
            currentImage = playerIdle;
            animationTick = 0;
        } else {
            animationTick++;
            if (animationTick%animationSpeed == 0) {
                if (currentImage == player1) {
                    currentImage = player2;
                } else if (currentImage == player2) {
                    currentImage = player1;
                } else {
                    currentImage = player1;
                }
            }
        }

        shotCounter--;
        vitalityCount--;
    }

    public static void shoot(int mouseX, int mouseY) {
        if (shotCounter < 0) {
            Player.getCurrentWeapon().shoot(mouseX, mouseY);
            shotCounter = (int)(100 / (getCurrentWeapon().attacksPerSecond + (0.2 * (double)(DEXTERITY))));
        }
    }

    public static void takeDamage(int dmg) {
        int dmg2 = dmg - (ARMOR/2);
        if (dmg < 0) {
            dmg = 0;
        }
        if (dmg2 < (0.3*dmg)) {
            dmg2 = (int)(0.3*dmg);
        }
        health -= dmg2;
    }


    public static void moveLeft(boolean move) {
        moveLeft = move;
    }

    public static void moveRight(boolean move) {
        moveRight = move;
    }

    public static void moveUp(boolean move) {
        moveUp = move;
    }
    public static void moveDown(boolean move) {
        moveDown = move;
    }

    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }

    public static Weapon getCurrentWeapon() {
        return currentWeapon;
    }

    public static void setCurrentWeapon(Weapon newWeapon) {
        currentWeapon = newWeapon;
    }

    public static String getKilledBy() {
        return killedBy;
    }

    public static void setKilledBy(String killedBy) {
        Player.killedBy = killedBy;
    }

    public static int getScore() {
        return Score;
    }

    public static void setScore(int score) {
        Score = score;
    }
}
