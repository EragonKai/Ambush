package com.kai.UI;

import com.kai.Draw;
import com.kai.RoomHandler;

import java.awt.*;

public class Map {
    Image self;
    public static int x, y;

    public Map(int x, int y) {
        self = Draw.loadImage("Map");
        this.x = x;
        this.y = y;
    }

    public void drawSelf(Graphics g){
        g.drawImage(self, x, y, null);
        RoomHandler.drawMapNumbers(g, x, y);
    }

}
