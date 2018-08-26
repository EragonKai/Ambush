package com.kai.Items.Weapons;

import com.kai.Draw;
import com.kai.Player;

import java.util.Random;

public class LightningBearer extends Weapon {

    public LightningBearer(int x, int y) {
        super("Lightning Bearer", "Legendary",0,2,1,20,0,0, x, y,false, Draw.loadImage("LightningBearer"), Draw.loadImage("LightningBearerProjectile"), 7, 9, 16, 16, 768, 2, 14, 6);
    }

}
