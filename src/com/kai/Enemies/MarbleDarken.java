package com.kai.Enemies;

import com.kai.Draw;
import com.kai.Items.Rings.CastIronRing;
import com.kai.Items.Rings.MarbleRing;
import com.kai.Items.Weapons.SturdySword;
import com.kai.RoomHandler;

public class MarbleDarken extends Enemy {

    public MarbleDarken(int x, int y) {
        super("Marble Darken",Draw.loadImage("Marble Darken"), Draw.loadImage("Marble Darken 2"),60,60,x,y,10,3,7,64,64,5);
    }

    public void dropLoot() {
        if (!dead) {
            dead = true;
            int dropChoice = RoomHandler.rand.nextInt(999) + 1;
            if (dropChoice > 970) {
                RoomHandler.addItemDrop(new MarbleRing(x, y));
            }
        }
    }


}
