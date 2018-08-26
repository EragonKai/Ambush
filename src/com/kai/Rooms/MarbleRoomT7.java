package com.kai.Rooms;

import com.kai.Draw;
import com.kai.Enemies.*;
import com.kai.RoomHandler;

import java.util.ArrayList;

public class MarbleRoomT7 extends Room {

    public MarbleRoomT7() {
        super(Draw.loadImage("MarbleRoomT7Floor"),Draw.loadImage("MarbleRoomT7Wall"), 7);
    }


    public ArrayList<RectObstacle> obstacleList() {
        ArrayList<RectObstacle> obstacles = new ArrayList<RectObstacle>();
        obstacles = this.addWallsToObstacles(obstacles);


        return obstacles;
    }


    public ArrayList<Enemy> enemyList() {
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();

        //enemies.add(new BlueConstruct(480, 352));
        enemies.add(new MarbleEgg(384, 268));
        enemies.add(new MarbleEgg(384,436));
        enemies.add(new MarbleEgg(564,268));
        enemies.add(new MarbleEgg(564,436));
        //enemies.add(new MarbleEgg(100,100));
       // enemies.add(new MarbleEgg(875, 618));
        enemies.add(new MarbleEgg(100,618));
        enemies.add(new MarbleEgg(875,100));


        return enemies;
    }
}
