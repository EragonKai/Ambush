package com.kai.Enemies;

import com.kai.Draw;
import com.kai.Items.Rings.RingOfBlue;
import com.kai.Items.Rings.StarSandRing;
import com.kai.Items.Weapons.BloodSoul;
import com.kai.Items.Weapons.LightningBearer;
import com.kai.RoomHandler;

public class Goblin extends Enemy {

    public Goblin(int x, int y) {
        super("Goblin", Draw.loadImage("Goblin1"), Draw.loadImage("Goblin2"), 20, 20, x, y, 6, 3, 4, 64, 64,2);
    }

    @Override
    public void dropLoot() {
        if (!dead) {
            dead = true;
            int dropChoice = RoomHandler.rand.nextInt(999) + 1;
            if (dropChoice > 970) { // 970
                RoomHandler.addItemDrop(new LightningBearer(x, y));
            } else if (dropChoice>650) {
                RoomHandler.addItemDrop(new RingOfBlue(x, y));
            }
        }
    }
}
