package com.kai.Enemies;

import com.kai.Draw;
import com.kai.Items.Rings.CastIronRing;
import com.kai.Items.Weapons.ShatteredSerpentScepter;
import com.kai.Items.Weapons.SturdySword;
import com.kai.RoomHandler;

public class SturdyConstruct extends Enemy {

    public SturdyConstruct(int x, int y) {
        super("Sturdy Construct", Draw.loadImage("SturdyConstruct1"), Draw.loadImage("SturdyConstruct2"), 60,60,x,y,8,3,2.5,64,64,4);
    }

    public void dropLoot() {
        if (!dead) {
            dead = true;
            int dropChoice = RoomHandler.rand.nextInt(999) + 1;
            if (dropChoice > 920) { //920
                RoomHandler.addItemDrop(new SturdySword(x, y));
            } else if (dropChoice > 760) {
                RoomHandler.addItemDrop(new CastIronRing(x,y));
            }
        }
    }
}
