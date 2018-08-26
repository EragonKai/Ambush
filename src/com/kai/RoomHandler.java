package com.kai;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import com.kai.Enemies.Enemy;
import com.kai.Items.Item;
import com.kai.Items.Rings.Ring;
import com.kai.Items.Weapons.Projectile;
import com.kai.Items.Weapons.Weapon;
import com.kai.Rooms.*;
import com.kai.UI.HUD;


public class RoomHandler {
    public static Room currentRoom;
    public static ArrayList<RectObstacle> obstacles = new ArrayList<RectObstacle>();
    public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    public static ArrayList<Item> itemsOnFloor = new ArrayList<Item>();

    public static int currentX, currentY;

    //numbers correspond to the threat level of that room
    /*public static int[][] roomArray = {
            {0,1,3,3,4,4,5,6},
            {1,2,2,4,3,4,6,5},
            {1,3,4,5,5,4,5,6},
            {3,2,4,4,5,6,6,5},
            {4,4,5,6,7,5,7,7},
            {5,6,4,5,8,6,8,6},
            {6,5,6,7,8,6,8,9},
            {7,6,7,8,8,9,9,10}
    };*/

    public static int[][] roomArray = new int[8][8];

    public static Random rand;

    public RoomHandler() {
        rand = new Random();
        generateDungeon();

        setRoom(new StartingRoom());
        this.currentX = 0;
        this.currentY = 0;

    }

    public static void generateDungeon() {
        for(int x = 0; x < 8;x++) {
            for (int y = 0;  y < 8; y++){
                if (x == 0 && y == 0) {
                    roomArray[y][x] = 0;
                } else if (x == 7 && y == 7) {
                    roomArray[y][x] = 10;
                } else if ((x == 7 && y == 6) || (x == 6 && y == 7)) {
                    roomArray[y][x] = 9;
                } else {
                    int threatLevel = (x+y)/2 + 1;
                    int deviation = 0;
                    deviation = rand.nextInt(3) - 1;
                    threatLevel += deviation;
                    if (threatLevel == 0) {
                        threatLevel = 1;
                    }
                    roomArray[y][x] = threatLevel;
                }
            }
        }
    }

    public static void drawCurrentRoom(Graphics g) {
        currentRoom.drawSelf(g);

        for (Enemy i: enemies) {
            i.drawSelf(g);
        }

    }

    public static void drawItems(Graphics g) {
        for (Item m: itemsOnFloor) {
            m.drawSelf(g);
        }
        Player.currentWeapon.drawSelf(g);

        for (Item n: Player.inventory) {
            if (n!=null) {
                n.drawSelf(g);
            }
        }
    }

    public static void addItemDrop(Item x) {
        itemsOnFloor.add(x);
    }

    public static Item itemClose(int x, int y) {
        for (Item i: itemsOnFloor) {
            if (Math.sqrt(((i.y - y) * (i.y-y)) + ((i.x - x) * (i.x -x))) < 32) {
                    return i;
            }
        }

        if (x > (HUD.INVENTORY1XPOS-20) && x < (HUD.INVENTORY1XPOS+45) && y > (HUD.INVENTORY1YPOS-20) && y < (HUD.INVENTORY1YPOS + 45)) {
            return Player.getCurrentWeapon();
        } else if (x > (HUD.inventoryPos[1][0]-20) && x < HUD.inventoryPos[1][0]+45 && y > (HUD.inventoryPos[1][1]-20) && y < HUD.inventoryPos[1][1] + 45) {
            return Player.inventory[1];
        } else if (x > (HUD.inventoryPos[2][0]-20) && x < HUD.inventoryPos[2][0]+45 && y > (HUD.inventoryPos[2][1]-20) && y < HUD.inventoryPos[2][1] + 45) {
            return Player.inventory[2];
        }


        return null;
    }

    public static void enemies() {

        ArrayList<Enemy> deadEnemies = new ArrayList<Enemy>();
        for (int l = 0; l< enemies.size() ; l++) {
            Enemy i = enemies.get(l);
            i.chase(Player.getX(), Player.getY());

            i.attack();

            ArrayList<Projectile> toRemove = new ArrayList<Projectile>();
            for (int m = 0; m < Player.getCurrentWeapon().projectiles.size(); m++) {
                Projectile p = Player.getCurrentWeapon().projectiles.get(m);

                if (i.checkCollisions(p.x, p.y, p.width, p.height)) {
                    Player.getCurrentWeapon().hit(i, m, p);
                    if (i.health < 1) {
                        if (!i.dead) {
                            Player.setScore(Player.getScore()+(int)(i.score));
                        }
                        i.dropLoot();
                        deadEnemies.add(i);
                    }
                    if(Player.getCurrentWeapon().piercing == false) {
                        toRemove.add(p);
                    }
                }
            }

            Player.getCurrentWeapon().projectiles.removeAll(toRemove);
        }

        enemies.removeAll(deadEnemies);

        if (enemies.size() == 0) {
            currentRoom.roomCompleted = true;
        }
    }

    public static void drawMapNumbers(Graphics g, int xx, int yy) {


        for (int x = xx; x<=xx+490;x += 70) {
            for (int y = yy+144; y<=634+yy; y+= 70) {
                g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 20));
                g.setColor(Color.white);
                int posX = (x-xx) / 70;
                int posY = (y-yy-144) / 70;
                if (currentX == posX && currentY == posY) {
                    g.setColor(new Color(23, 118,86));
                    g.fillRect(x+4,y+4,62,62);
                    g.setColor(Color.red);
                    g.drawString(String.valueOf(roomArray[posY][posX]), x+27, y+42);
                } else {
                    g.drawString(String.valueOf(roomArray[posY][posX]), x + 27, y + 42);
                }
            }
        }
    }


    private static void setRoom(Room newRoom) {
        currentRoom = newRoom;
        enemies = currentRoom.enemyList();
        obstacles = currentRoom.obstacleList();
        itemsOnFloor.clear();
        Player.getCurrentWeapon().projectiles.clear();

    }


    public static void changeRooms(String dir) {
        switch (dir.toLowerCase()) {
            case "up":
                currentY -= 1;
                break;
            case "down":
                currentY += 1;
                break;
            case "right":
                currentX += 1;
                break;
            case "left":
                currentX -= 1;
                break;
        }

        int newRoomThreat = roomArray[currentY][currentX];

        switch (newRoomThreat) {
            case 0:
                setRoom(new StartingRoom());
                break;

            // vvvv PLACEHOLDER vvvv
            case 1:
                setRoom(new DarkRoomT1());
                break;
            case 2:
                setRoom(new DesertRoomT2());
                break;
            case 3:
                setRoom(new SturdyRoomT3());
                break;
            case 4:
                setRoom(new MossyRoomT4());
                break;
            case 5:
                setRoom(new BlueRoomT5());
                break;
            case 6:
                setRoom(new BloodRoomT6());
                break;
            case 7:
                setRoom(new MarbleRoomT7());
                break;
            case 8:
                setRoom(new MarbleRoomT7());
                break;
            case 9:
                setRoom(new MarbleRoomT7());
                break;
            case 10:
                setRoom(new MarbleRoomT7());
                break;
        }

        checkIfCompleted();
    }



    public static boolean checkCollisions(int x, int y, int width, int height) {
        for (int i = 0; i < obstacles.size(); i++) {
            RectObstacle rect = obstacles.get(i);

            if (x < rect.x+rect.width && y < rect.y+rect.height && x+width > rect.x && y+height > rect.y) {
                return true;
            }
        }
        return false;
    }

    public static void checkIfCompleted() {
        if (currentRoom.roomCompleted) {
                ArrayList<String> openWalls = wallsOpen();
                for (String i: openWalls) {
                    currentRoom.openWalls(i);
                    removeWallFromObstacles(i);
                }

        }
    }

    public static ArrayList<String> wallsOpen() {
        ArrayList<String> wallsToOpen = new ArrayList<String>();
        try { if (roomArray[currentY-1][currentX] < 11) { wallsToOpen.add("UP"); } } catch (ArrayIndexOutOfBoundsException exception) {}
        try { if (roomArray[currentY+1][currentX] < 11) { wallsToOpen.add("DOWN"); } } catch (ArrayIndexOutOfBoundsException exception) {}
        try { if (roomArray[currentY][currentX-1] < 11) { wallsToOpen.add("LEFT"); } } catch (ArrayIndexOutOfBoundsException exception) {}
        try { if (roomArray[currentY][currentX+1] < 11) { wallsToOpen.add("RIGHT"); } } catch (ArrayIndexOutOfBoundsException exception) {}
        return wallsToOpen;
    }

    public static void removeWallFromObstacles(String dir) {
        ArrayList<RectObstacle> toRemove = new ArrayList<RectObstacle>();

        switch (dir.toLowerCase()) {
            case "up":
                for (int j = 64; j < 956; j+=64) {
                    for (RectObstacle i: obstacles) {
                        if (i.x == j && i.y == 0) {
                            toRemove.add(i);
                            break;
                        }
                    }
                }
                break;
            case "down":
                for (int k = 64; k < 956; k+=64) {
                    for (RectObstacle i: obstacles) {
                        if (i.x == k && i.y == 704) {
                            toRemove.add(i);
                            break;
                        }
                    }
                }
                break;
            case "right":
                for (int m =64; m < 704; m+=64) {
                    for (RectObstacle i: obstacles) {
                        if (i.x == 960 && i.y == m) {
                            toRemove.add(i);
                            break;
                        }
                    }
                }
                break;
            case "left":
                for (int n = 64; n < 704; n+=64) {
                    for (RectObstacle i: obstacles) {
                        if (i.x == 0 && i.y == n) {
                            toRemove.add(i);
                            break;
                        }
                    }
                }
        }

        obstacles.removeAll(toRemove);
    }


}
































