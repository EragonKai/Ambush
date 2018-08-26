package com.kai.Rooms;

import com.kai.Draw;
import com.kai.Enemies.*;
import com.kai.RoomHandler;

import java.awt.*;
import java.util.ArrayList;

public class BloodRoomT6 extends Room {

    public BloodRoomT6() {
        super(Draw.loadImage("BloodRoomT6Floor"), Draw.loadImage("BloodRoomT6Wall"), 6);
    }


    public ArrayList<RectObstacle> obstacleList() {
        ArrayList<RectObstacle> obstacles = new ArrayList<RectObstacle>();
        obstacles = this.addWallsToObstacles(obstacles);
        for (int i = 192; i<832; i+=64) {
            obstacles.add(new RectObstacle(i, 352, 64, 64));
        }


        return obstacles;
    }


    public ArrayList<Enemy> enemyList() {
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();

        enemies.add(new BloodImp(480, 288));
        enemies.add(new BloodImp(480, 416));

        int safe = RoomHandler.rand.nextInt(14)+1;

        for (int i = 64; i<960;i+=64) {
            if (i != (safe * 64)) {
                enemies.add(new BloodTurret(i, 640, "up"));
            }
        }

        safe = RoomHandler.rand.nextInt(14)+1;

        for (int i = 64; i<960;i+=64) {
            if (i != (safe * 64)) {
                enemies.add(new BloodTurret(i, 64, "down"));
            }
        }


        return enemies;
    }

}
