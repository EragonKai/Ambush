package com.kai.Rooms;

import com.kai.Draw;
import com.kai.Enemies.Enemy;
import com.kai.Enemies.Goblin;

import java.util.ArrayList;

public class DarkRoomT1 extends Room{

    public DarkRoomT1() {
        super(Draw.loadImage("DarkRoomT1Floor"), Draw.loadImage("DarkRoomT1Wall"), 1);
    }

    public ArrayList<RectObstacle> obstacleList() {
        ArrayList<RectObstacle> obstacles = new ArrayList<RectObstacle>();
        obstacles = this.addWallsToObstacles(obstacles);

        return obstacles;
    }

    public ArrayList<Enemy> enemyList() {
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();

        enemies.add(new Goblin(512,384));
        enemies.add(new Goblin(80,80));

        return enemies;
    }
}
