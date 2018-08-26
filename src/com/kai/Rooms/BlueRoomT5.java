package com.kai.Rooms;

import com.kai.Draw;
import com.kai.Enemies.*;

import java.awt.*;
import java.util.ArrayList;

public class BlueRoomT5 extends Room {

    public BlueRoomT5() {
        super(Draw.loadImage("BlueRoomT5Floor"),Draw.loadImage("BlueRoomT5Wall"),5);
    }

    public ArrayList<RectObstacle> obstacleList() {
        ArrayList<RectObstacle> obstacles = new ArrayList<RectObstacle>();
        obstacles = this.addWallsToObstacles(obstacles);
        obstacles.add(new RectObstacle(480, 352,64,64));

        return obstacles;
    }


    public ArrayList<Enemy> enemyList() {
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();

        enemies.add(new BlueConstruct(100,100));
        enemies.add(new BlueConstruct(875, 618));
        enemies.add(new BlueConstruct(100,618));
        enemies.add(new BlueConstruct(875,100));
        //enemies.add(new VineJellyfish(280,352));
        enemies.add(new Goblin(280,352));


        return enemies;
    }

}
