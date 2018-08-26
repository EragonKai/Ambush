package com.kai.Items.Weapons;

import com.kai.Draw;

public class MossCoveredWand extends Weapon{

    public MossCoveredWand(int x, int y) {
        super("Mossy Spiral Wand", "Rare", 1,1,1,10,0,1,x,y,false,Draw.loadImage("MossCoveredWand"),Draw.loadImage("MossCoveredWandProj"),6,7,16,16,384,6,15,1.2);
    }

}
