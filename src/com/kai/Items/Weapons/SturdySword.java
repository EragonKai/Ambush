package com.kai.Items.Weapons;

import com.kai.Draw;

public class SturdySword extends Weapon {
    public SturdySword(int x, int y) {
        super("Sturdy Sword", "Rare",4,0,0,20,2,0,x,y,true,Draw.loadImage("SturdySword"),Draw.loadImage("SturdySwordProj"),15,25,64,64,96,1,1.5,3);
    }
}
