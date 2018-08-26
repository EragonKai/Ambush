package com.kai.Enemies;

import com.kai.Draw;
import com.kai.Items.Rings.RingOfFrozenLakes;
import com.kai.Items.Weapons.ShatteredSerpentScepter;
import com.kai.Items.Weapons.SnowBlaze;
import com.kai.RoomHandler;

public class BlueConstruct extends ProjectileEnemy {

    public BlueConstruct(int x, int y) {
        super("Blue Construct", Draw.loadImage("BlueConstruct1"),Draw.loadImage("BlueConstruct2"),40,40,x,y,18,1,0,64,64,Draw.loadImage("BlueConstructProj"),16,16,6,1024,1024,4);
        this.currentImage = this.mySprite;
        this.animationSpeed = 50;
    }

    public void dropLoot() {
        if (!dead) {
            dead = true;
            int dropChoice = RoomHandler.rand.nextInt(999) + 1;
            if (dropChoice > 985) { //985
                RoomHandler.addItemDrop(new RingOfFrozenLakes(x,y));
            } else if (dropChoice > 500) { //500
                RoomHandler.addItemDrop(new SnowBlaze(x, y));
            }
        }
    }

}
