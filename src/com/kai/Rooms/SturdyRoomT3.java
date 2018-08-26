package com.kai.Rooms;

import com.kai.Draw;
import com.kai.Enemies.Enemy;
import com.kai.Enemies.Goblin;
import com.kai.Enemies.LivingConstruct;
import com.kai.Enemies.SturdyConstruct;

import java.util.ArrayList;

public class SturdyRoomT3 extends Room {

    public SturdyRoomT3() {
        super(Draw.loadImage("SturdyRoomT3Floor"),Draw.loadImage("SturdyRoomT3Wall"),3);
    }

    public ArrayList<RectObstacle> obstacleList() {
        ArrayList<RectObstacle> obstacles = new ArrayList<RectObstacle>();
        obstacles = this.addWallsToObstacles(obstacles);

        return obstacles;
    }

    public ArrayList<Enemy> enemyList() {
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();

        enemies.add(new SturdyConstruct(100,100));
        enemies.add(new SturdyConstruct(875, 618));
        enemies.add(new SturdyConstruct(100,618));
        enemies.add(new SturdyConstruct(875,100));

        enemies.add(new LivingConstruct(512,384));

        return enemies;
    }
}
