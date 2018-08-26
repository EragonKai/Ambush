package com.kai;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.lang.ClassLoader;

public class Draw {
    private static BufferedImage playerIdle, player1, player2;
    private static BufferedImage StartMenu, HUD, Map, DeathScreen;
    private static BufferedImage ItemSpriteSheet;
    private static BufferedImage TileSpriteSheet;
    private static BufferedImage EnemySpriteSheet;

    public Draw() {

        try {
            playerIdle = ImageIO.read(getClass().getResourceAsStream("/com/kai/resources/playerIdle.png"));
            player1 = ImageIO.read(getClass().getResource("/com/kai/resources/player1.png"));
            player2 = ImageIO.read(getClass().getResource("/com/kai/resources/player2.png"));

            StartMenu = ImageIO.read(getClass().getResource("/com/kai/resources/StartMenu.png"));
            HUD = ImageIO.read(getClass().getResource("/com/kai/resources/HUD.png"));
            Map = ImageIO.read(getClass().getResource("/com/kai/resources/Map.png"));
            DeathScreen = ImageIO.read(getClass().getResource("/com/kai/resources/DeathScreen.png"));

            TileSpriteSheet = ImageIO.read(getClass().getResource("/com/kai/resources/TileSpriteSheet.png"));
            ItemSpriteSheet = ImageIO.read(getClass().getResource("/com/kai/resources/ItemSpriteSheet.png"));
            EnemySpriteSheet = ImageIO.read(getClass().getResource("/com/kai/resources/EnemySpriteSheet.png"));

        } catch (IOException ex) { System.out.println("image loading exception"); }
    }

    public static Image loadImage(String image) {
        switch (image) {
            case "playerIdle":
                return playerIdle;
            case "player1":
                return player1;
            case "player2":
                return player2;
            case "StartMenu":
                return StartMenu;
            case "StartingRoom1Floor":
                return TileSpriteSheet.getSubimage(0,0,8,8).getScaledInstance(64,64,Image.SCALE_SMOOTH);
            case "StartingRoom1Wall":
                return TileSpriteSheet.getSubimage(8,0,8,8).getScaledInstance(64,64,Image.SCALE_SMOOTH);
            case "HUD":
                return HUD;
            case "StartingWeapon1":
                return ItemSpriteSheet.getSubimage(0,24,8,8).getScaledInstance(32,32,Image.SCALE_SMOOTH);
            case "StartingWeapon1Projectile":
                return ItemSpriteSheet.getSubimage(0,32,8,8).getScaledInstance(16,16,Image.SCALE_SMOOTH);
            case "LightningBearer":
                return ItemSpriteSheet.getSubimage(8,24,8,8).getScaledInstance(32,32,Image.SCALE_SMOOTH);
            case "LightningBearerProjectile":
                return ItemSpriteSheet.getSubimage(8,32,8,8).getScaledInstance(16,16,Image.SCALE_SMOOTH);
            case "Goblin1":
                return EnemySpriteSheet.getSubimage(0,0,8,8).getScaledInstance(64,64,Image.SCALE_SMOOTH);
            case "Goblin2":
                return EnemySpriteSheet.getSubimage(8,0,8,8).getScaledInstance(64,64,Image.SCALE_SMOOTH);
            case "DarkRoomT1Wall":
                return TileSpriteSheet.getSubimage(16,0,8,8).getScaledInstance(64,64,Image.SCALE_SMOOTH);
            case "DarkRoomT1Floor":
                return TileSpriteSheet.getSubimage(24,0,8,8).getScaledInstance(64,64,Image.SCALE_SMOOTH);
            case "LivingConstruct1":
                return EnemySpriteSheet.getSubimage(0,8,8,8).getScaledInstance(64,64,Image.SCALE_SMOOTH);
            case "LivingConstruct2":
                return EnemySpriteSheet.getSubimage(8,8,8,8).getScaledInstance(64,64,Image.SCALE_SMOOTH);
            case "LivingConstructProj":
                return EnemySpriteSheet.getSubimage(16,8,8,8).getScaledInstance(32,32,Image.SCALE_SMOOTH);
            case "DesertRoomT2Floor":
                return TileSpriteSheet.getSubimage(32,0,8,8).getScaledInstance(64,64,Image.SCALE_SMOOTH);
            case "DesertRoomT2Wall":
                return TileSpriteSheet.getSubimage(40,0,8,8).getScaledInstance(64,64,Image.SCALE_SMOOTH);
            case "ShatteredSerpentScepter":
                return ItemSpriteSheet.getSubimage(16,24,8,8).getScaledInstance(32,32,Image.SCALE_SMOOTH);
            case "ShatteredSerpentScepterProj":
                return ItemSpriteSheet.getSubimage(16,32,8,8).getScaledInstance(32,32,Image.SCALE_SMOOTH);
            case "SturdyConstruct1":
                return EnemySpriteSheet.getSubimage(0,16,8,8).getScaledInstance(64,64,Image.SCALE_SMOOTH);
            case "SturdyConstruct2":
                return EnemySpriteSheet.getSubimage(8,16,8,8).getScaledInstance(64,64,Image.SCALE_SMOOTH);
            case "SturdyRoomT3Floor":
                return TileSpriteSheet.getSubimage(48,0,8,8).getScaledInstance(64,64,Image.SCALE_SMOOTH);
            case "SturdyRoomT3Wall":
                return TileSpriteSheet.getSubimage(56,0,8,8).getScaledInstance(64,64,Image.SCALE_SMOOTH);
            case "SturdySword":
                return ItemSpriteSheet.getSubimage(24,24,8,8).getScaledInstance(32,32,Image.SCALE_SMOOTH);
            case "SturdySwordProj":
                return ItemSpriteSheet.getSubimage(24,32,8,8).getScaledInstance(64,64,Image.SCALE_SMOOTH);
            case "VineJellyfish1":
                return EnemySpriteSheet.getSubimage(0,24,8,8).getScaledInstance(64,64,Image.SCALE_SMOOTH);
            case "VineJellyfish2":
                return EnemySpriteSheet.getSubimage(8,24,8,8).getScaledInstance(64,64,Image.SCALE_SMOOTH);
            case "VineJellyfishProj":
                return EnemySpriteSheet.getSubimage(16,24,8,8).getScaledInstance(32,32,Image.SCALE_SMOOTH);
            case "MossyRoomT4Wall":
                return TileSpriteSheet.getSubimage(72,0,8,8).getScaledInstance(64,64,Image.SCALE_SMOOTH);
            case "MossyRoomT4Floor":
                return TileSpriteSheet.getSubimage(64,0,8,8).getScaledInstance(64,64,Image.SCALE_SMOOTH);
            case "MossCoveredWand":
                return ItemSpriteSheet.getSubimage(32,24,8,8).getScaledInstance(32,32,Image.SCALE_SMOOTH);
            case "MossCoveredWandProj":
                return ItemSpriteSheet.getSubimage(32,32,8,8).getScaledInstance(16,16,Image.SCALE_SMOOTH);
            case "BlueRoomT5Wall":
                return TileSpriteSheet.getSubimage(88,0,8,8).getScaledInstance(64,64,Image.SCALE_SMOOTH);
            case "BlueRoomT5Floor":
                return TileSpriteSheet.getSubimage(80,0,8,8).getScaledInstance(64,64,Image.SCALE_SMOOTH);
            case "BlueConstruct1":
                return EnemySpriteSheet.getSubimage(0,32,8,8).getScaledInstance(64,64,Image.SCALE_SMOOTH);
            case "BlueConstruct2":
                return EnemySpriteSheet.getSubimage(8,32,8,8).getScaledInstance(64,64,Image.SCALE_SMOOTH);
            case "BlueConstructProj":
                return EnemySpriteSheet.getSubimage(16,32,8,8).getScaledInstance(16,16,Image.SCALE_SMOOTH);
            case "SnowBlaze":
                return ItemSpriteSheet.getSubimage(40,24,8,8).getScaledInstance(32,32,Image.SCALE_SMOOTH);
            case "SnowBlazeProj":
                return ItemSpriteSheet.getSubimage(40,32,8,8).getScaledInstance(16,16,Image.SCALE_SMOOTH);
            case "Map":
                return Map;
            case "BloodRoomT6Floor":
                return TileSpriteSheet.getSubimage(96,0,8,8).getScaledInstance(64,64,Image.SCALE_SMOOTH);
            case "BloodRoomT6Wall":
                return TileSpriteSheet.getSubimage(104,0,8,8).getScaledInstance(64,64,Image.SCALE_SMOOTH);
            case "BloodTurret1":
                return EnemySpriteSheet.getSubimage(0,40,8,8).getScaledInstance(64,64,Image.SCALE_SMOOTH);
            case "BloodTurret2":
                return EnemySpriteSheet.getSubimage(8,40,8,8).getScaledInstance(64,64,Image.SCALE_SMOOTH);
            case "BloodTurretProj":
                return EnemySpriteSheet.getSubimage(32,40,8,8).getScaledInstance(16,16,Image.SCALE_SMOOTH);
            case "BloodTurret2-1":
                return EnemySpriteSheet.getSubimage(16,40,8,8).getScaledInstance(64,64,Image.SCALE_SMOOTH);
            case "BloodTurret2-2":
                return EnemySpriteSheet.getSubimage(24,40,8,8).getScaledInstance(64,64,Image.SCALE_SMOOTH);
            case "BloodImp1":
                return EnemySpriteSheet.getSubimage(0,48,8,8).getScaledInstance(64,64,Image.SCALE_SMOOTH);
            case "BloodImp2":
                return EnemySpriteSheet.getSubimage(8,48,8,8).getScaledInstance(64,64,Image.SCALE_SMOOTH);
            case "BloodSoul":
                return ItemSpriteSheet.getSubimage(48,24,8,8).getScaledInstance(32,32,Image.SCALE_SMOOTH);
            case "BloodSoulProj":
                return ItemSpriteSheet.getSubimage(48,32,8,8).getScaledInstance(16,16,Image.SCALE_SMOOTH);
            case "BloodSoulProj2":
                return ItemSpriteSheet.getSubimage(48,32,8,8).getScaledInstance(32,32,Image.SCALE_SMOOTH);
            case "StartingRing":
                return ItemSpriteSheet.getSubimage(0,0,8,8).getScaledInstance(32,32,Image.SCALE_SMOOTH);
            case "StarsandRing":
                return ItemSpriteSheet.getSubimage(8,0,8,8).getScaledInstance(32,32,Image.SCALE_SMOOTH);
            case "Ring of Blue":
                return ItemSpriteSheet.getSubimage(16,0,8,8).getScaledInstance(32,32,Image.SCALE_SMOOTH);
            case "Cast Iron Ring":
                return ItemSpriteSheet.getSubimage(24,0,8,8).getScaledInstance(32,32,Image.SCALE_SMOOTH);
            case "Daredevil Ring":
                return ItemSpriteSheet.getSubimage(32,0,8,8).getScaledInstance(32,32,Image.SCALE_SMOOTH);
            case "Ring of Frozen Lakes":
                return ItemSpriteSheet.getSubimage(40,0,8,8).getScaledInstance(32,32,Image.SCALE_SMOOTH);
            case "DeathScreen":
                return DeathScreen;
            case "MarbleRoomT7Floor":
                return TileSpriteSheet.getSubimage(112,0,8,8).getScaledInstance(64,64,Image.SCALE_SMOOTH);
            case "MarbleRoomT7Wall":
                return TileSpriteSheet.getSubimage(120,0,8,8).getScaledInstance(64,64,Image.SCALE_SMOOTH);
            case "Marble Darken":
                return EnemySpriteSheet.getSubimage(0,56,8,8).getScaledInstance(64,64,Image.SCALE_SMOOTH);
            case "Marble Darken 2":
                return EnemySpriteSheet.getSubimage(8,56,8,8).getScaledInstance(64,64,Image.SCALE_SMOOTH);
            case "Marble Egg":
                return EnemySpriteSheet.getSubimage(0,64,8,8).getScaledInstance(64,64,Image.SCALE_SMOOTH);
            case "Marble Egg 2":
                return EnemySpriteSheet.getSubimage(8,64,8,8).getScaledInstance(64,64,Image.SCALE_SMOOTH);
            case "Marble Egg Proj":
                return EnemySpriteSheet.getSubimage(16,64,8,8).getScaledInstance(16,16,Image.SCALE_SMOOTH);
            case "Marble Ring":
                return ItemSpriteSheet.getSubimage(48, 0,8,8).getScaledInstance(32,32,Image.SCALE_SMOOTH);

        }


        return null;
    }

}
