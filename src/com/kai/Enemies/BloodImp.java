package com.kai.Enemies;

import com.kai.Draw;
import com.kai.Items.Weapons.BloodSoul;
import com.kai.Items.Weapons.SnowBlaze;
import com.kai.RoomHandler;

public class BloodImp extends Enemy {
    int moveTick;

    public BloodImp(int x, int y) {
        super("Blood Imp", Draw.loadImage("BloodImp1"), Draw.loadImage("BloodImp2"), 85,85,x,y,18,1,120,64,64,7);
        moveTick = 1;
    }

    public void dropLoot() {
        if (!dead) {
            dead = true;
            int dropChoice = RoomHandler.rand.nextInt(999)+1;
            if (dropChoice > 900) { //900
                RoomHandler.addItemDrop(new BloodSoul(x, y));
            }
        }
    }

    @Override
    public void chase(int px, int py) {
        moveTick++;
        if (moveTick % 90 == 0) {
            moveTick = 1;
            super.chase(px, py);
        }
    }
}
