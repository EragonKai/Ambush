package com.kai.Rooms;

import com.kai.Draw;
import com.kai.Enemies.Enemy;
import com.kai.Enemies.Goblin;
import com.kai.Enemies.LivingConstruct;

import java.util.ArrayList;

public class DesertRoomT2 extends Room {

    public DesertRoomT2() {
        super(Draw.loadImage("DesertRoomT2Floor"), Draw.loadImage("DesertRoomT2Wall"), 2);
    }

    public ArrayList<RectObstacle> obstacleList() {
        ArrayList<RectObstacle> obstacles = new ArrayList<RectObstacle>();
        obstacles = this.addWallsToObstacles(obstacles);

        return obstacles;
    }

    public ArrayList<Enemy> enemyList() {
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();

        enemies.add(new LivingConstruct(80,80));
        enemies.add(new LivingConstruct(875, 618));

        return enemies;
    }

}
