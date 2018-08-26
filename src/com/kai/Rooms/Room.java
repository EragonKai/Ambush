package com.kai.Rooms;

import com.kai.Enemies.Enemy;
import com.kai.RoomHandler;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

//might remove abstract tag later, not needed atm

//each room will have a 64x64 wall tile and a 64x64 floor tile
//the wall tiles will be layered around on the sides of the area (excluding the inventory)
//floor tiles will just be on the floor
public abstract class Room {
    Image myFloor, myWall;
    public static int threatLevel;

    //width/height
    public static int wallSide, floorSide;

    //these will determine wether or not to load a certain side of the walls
    public boolean openLeft, openRight, openUp, openDown;
    public boolean roomCompleted;

    public ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    public Room(Image myFloor, Image myWall, int threatLevel) {
        this.myFloor = myFloor;
        this.myWall = myWall;
        this.threatLevel = threatLevel;

        wallSide = 64;
        floorSide = 64;
        openLeft = false;
        openRight = false;
        openUp = false;
        openDown = false;
    }

    public void drawSelf(Graphics g) {
        drawFloor(g);
        drawWalls(g);
    }

    public void drawWalls(Graphics g) {
        for (RectObstacle i: RoomHandler.obstacles) {
            g.drawImage(myWall, i.x, i.y, null);
        }
        /*
        if (!openLeft) {
            for (int i = 0; i < 768; i+=64) {
                g.drawImage(myWall, 0, i, null);
            }
        }
        if (!openRight) {
            for (int m = 0; m < 768; m+=64) {
                g.drawImage(myWall, 960, m, null);
            }
        }
        if (!openUp) {
            for (int j = 0; j < 1024; j+=64) {
                g.drawImage(myWall, j, 0, null);
            }
        }
        if (!openDown) {
            for (int k = 0; k < 1024; k+=64) {
                g.drawImage(myWall, k, 704, null);
            }
        }*/
    }

    public void drawFloor(Graphics g) {
        for (int i = 0; i < 1024; i+=64) {
            for (int j = 0; j < 768; j+=64) {
                g.drawImage(myFloor, i, j, null);
            }
        }
    }

    public ArrayList<RectObstacle> addWallsToObstacles(ArrayList<RectObstacle> obstacles) {
        for (int i = 0; i < 768; i+=64) {
            obstacles.add(new RectObstacle(0, i, this.wallSide, this.wallSide));
        }
        for (int m = 0; m < 768; m+=64) {
            obstacles.add(new RectObstacle(960, m, this.wallSide, this.wallSide));
        }
        for (int j = 0; j < 1024; j+=64) {
            obstacles.add(new RectObstacle(j, 0, this.wallSide, this.wallSide));
        }
        for (int k = 0; k < 1024; k+=64) {
            obstacles.add(new RectObstacle(k, 704, this.wallSide, this.wallSide));
        }


        return obstacles;
    }

    public void openWalls(String wall) {
        if (wall.toLowerCase().equals("up")) {
            openUp = true;
        }
        if (wall.toLowerCase().equals("down")) {
            openDown = true;
        }
        if (wall.toLowerCase().equals("right")) {
            openRight = true;
        }
        if (wall.toLowerCase().equals("left")) {
            openLeft = true;
        }
    }



    public abstract ArrayList<RectObstacle> obstacleList();
    public abstract ArrayList<Enemy> enemyList();
}























