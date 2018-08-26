package com.kai.Rooms;

import com.kai.Draw;
import com.kai.Enemies.Enemy;
import com.kai.Enemies.LivingConstruct;
import com.kai.Enemies.SturdyConstruct;
import com.kai.Enemies.VineJellyfish;

import java.util.ArrayList;

public class MossyRoomT4 extends Room {

    public MossyRoomT4() {
        super(Draw.loadImage("MossyRoomT4Floor"),Draw.loadImage("MossyRoomT4Wall"),4);
    }

    public ArrayList<RectObstacle> obstacleList() {
        ArrayList<RectObstacle> obstacles = new ArrayList<RectObstacle>();
        obstacles = this.addWallsToObstacles(obstacles);

        return obstacles;
    }

    public ArrayList<Enemy> enemyList() {
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();

        enemies.add(new VineJellyfish(100,100));
        enemies.add(new VineJellyfish(875, 618));
        enemies.add(new SturdyConstruct(480,368));

        return enemies;
    }

}
