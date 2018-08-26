package com.kai.Items.Weapons;

import com.kai.Draw;

import java.awt.*;

public class StartingGun extends Weapon {
    public StartingGun(int x, int y) {
        super("Starting Gun", "Common",0,0,0, 0,0,0,x, y,true, Draw.loadImage("StartingWeapon1"), Draw.loadImage("StartingWeapon1Projectile"), 6, 9, 16, 16, 512, 1, 8, 3);
    }


}
