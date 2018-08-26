package com.kai.Enemies;

import com.kai.Draw;
import com.kai.Items.Weapons.LightningBearer;
import com.kai.Items.Weapons.ShatteredSerpentScepter;
import com.kai.RoomHandler;

public class LivingConstruct extends ProjectileEnemy {

    public LivingConstruct(int x, int y) {
        super("Living Construct", Draw.loadImage("LivingConstruct1"), Draw.loadImage("LivingConstruct2"), 30, 30, x, y, 20,0.5,2, 64, 64, Draw.loadImage("LivingConstructProj"), 32, 32, 10, 1024, 200,4 );
    }

    public void dropLoot() {
        if (!dead) {
            dead = true;
            int dropChoice = RoomHandler.rand.nextInt(999) + 1;
            if (dropChoice > 500) { //750
                RoomHandler.addItemDrop(new ShatteredSerpentScepter(x, y));
            }
        }
    }
}
