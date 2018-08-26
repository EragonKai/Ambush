package com.kai.Rooms;

import com.kai.Draw;
import com.kai.Enemies.Enemy;

import java.awt.*;
import java.util.ArrayList;

public class StartingRoom extends Room {

    public StartingRoom() {
        super(Draw.loadImage("StartingRoom1Floor"), Draw.loadImage("StartingRoom1Wall"), 0);
        roomCompleted = true;
    }

    @Override
    public void drawSelf(Graphics g) {
        super.drawSelf(g);
        // CONTROLS:
        // W/A/S/D TO MOVE
        // CLICK TO PICK UP AN ITEM. WHILE DRAGGING IT AROUND, PRESS E TO OPEN ITS STATS AND CLICK ON YOUR CURRENT EQUIPPED WEAPON TO REPLACE IT
        // LEFT CLICK TO SHOOT
        // LEAVE A ROOM AND GO OFF SCREEN TO ENTER THE NEXT ONE
        // ENEMIES RESPAWN IN ROOMS AND WEAPONS ON THE FLOOR DISAPPEAR WHEN YOU LEAVE
    }

    public ArrayList<RectObstacle> obstacleList() {
        ArrayList<RectObstacle> obstacles = new ArrayList<RectObstacle>();
        obstacles = this.addWallsToObstacles(obstacles);

        return obstacles;
    }

    public ArrayList<Enemy> enemyList() {
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();

        return enemies;
    }

}
