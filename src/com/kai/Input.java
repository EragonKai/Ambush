package com.kai;

import com.kai.Items.Item;
import com.kai.Items.Rings.Ring;
import com.kai.Items.Weapons.Weapon;
import com.kai.UI.HUD;

import java.awt.*;

public class Input {
    public static Item itemBeingDragged, weaponHUDDisplay;
    public static int beingDraggedOGx, beingDraggedOGy;
    public static boolean autoFire, mouseHeldDownAutofire;

    public static void keyPressed(int keyCode) {
        switch (Screen.GameState) {
            case "STARTMENU":

                break;
            case "RUNNING": case"DRAGGING": case "MAP":
                if (keyCode == 87) {
                    Player.moveUp(true);
                }
                if (keyCode == 65) {
                    Player.moveLeft(true);
                }
                if (keyCode == 68) {
                    Player.moveRight(true);
                }
                if (keyCode == 83) {
                    Player.moveDown(true);
                }
                if (keyCode == 84) {
                    if (autoFire) {
                        autoFire = false;
                    } else {
                        autoFire = true;
                    }
                }
                if (Screen.GameState.equals("RUNNING") && keyCode == 77) {
                    Screen.GameState = "MAP";
                } else if (Screen.GameState.equals("MAP") && keyCode == 77) {
                    Screen.GameState = "RUNNING";
                }
                break;
        }
    }

    public static void keyReleased(int keyCode) {
        switch (Screen.GameState) {
            case "STARTMENU":
                break;
            case "RUNNING": case"DRAGGING": case "MAP":
                if (keyCode == 87) {
                    Player.moveUp(false);
                }
                if (keyCode == 65) {
                    Player.moveLeft(false);
                }
                if (keyCode == 68) {
                    Player.moveRight(false);
                }
                if (keyCode == 83) {
                    Player.moveDown(false);
                }
                break;
        }
    }

    public static void mousePressed(int mouseX, int mouseY, int keyCode) {
        switch (Screen.GameState) {
            case "RUNNING": case "DRAGGING": case "MAP":
            if (mouseX < 1024 && keyCode == 1) {
                mouseHeldDownAutofire = true;
            }
        }
    }

    public static void mouseReleased(int mouseX, int mouseY, int keyCode) {
        switch (Screen.GameState) {
            case "STARTMENU":
                if (mouseX > 408 && mouseX < 816 && mouseY > 512 && mouseY < 640) {
                    Screen.GameState = "RUNNING";
                    mouseHeldDownAutofire = false;
                }
                break;
            case "RUNNING": case "MAP":
                if (Screen.GameState.equals("RUNNING") && mouseX > 1044 && mouseX < 1203 && mouseY>680 && mouseY < 739) {
                    Screen.GameState = "MAP";
                } else {
                    Screen.GameState = "RUNNING";
                }


                mouseHeldDownAutofire = false;
                if (RoomHandler.itemClose(mouseX, mouseY) != null) {
                    Screen.GameState = "DRAGGING";
                    itemBeingDragged = RoomHandler.itemClose(mouseX, mouseY);
                    beingDraggedOGx = itemBeingDragged.x;
                    beingDraggedOGy = itemBeingDragged.y;
                }
                break;
            case "DRAGGING":
                Screen.GameState = "RUNNING";
                if (mouseX > (HUD.INVENTORY1XPOS-20) && mouseX < HUD.INVENTORY1XPOS+45 && mouseY > (HUD.INVENTORY1YPOS-20) && mouseY < HUD.INVENTORY1YPOS + 45 && itemBeingDragged instanceof Weapon && itemBeingDragged != Player.getCurrentWeapon()) {
                    Player.equipItem(0, null, (Weapon) itemBeingDragged, beingDraggedOGx, beingDraggedOGy);
                } else if (mouseX > (HUD.inventoryPos[1][0]-20) && mouseX < HUD.inventoryPos[1][0]+45 && mouseY > (HUD.inventoryPos[1][1]-20) && mouseY < HUD.inventoryPos[1][1] + 45 && itemBeingDragged instanceof Ring && itemBeingDragged != Player.inventory[1] && itemBeingDragged != Player.inventory[2]) {
                    Player.equipItem(1, itemBeingDragged, null, beingDraggedOGx, beingDraggedOGy);
                } else if (mouseX > (HUD.inventoryPos[2][0]-20) && mouseX < HUD.inventoryPos[2][0]+45 && mouseY > (HUD.inventoryPos[2][1]-20) && mouseY < HUD.inventoryPos[2][1] + 45 && itemBeingDragged instanceof Ring && itemBeingDragged != Player.inventory[1] && itemBeingDragged != Player.inventory[2]) {
                    Player.equipItem(2,itemBeingDragged,null,beingDraggedOGx,beingDraggedOGy);
                } else {
                    itemBeingDragged.setX(beingDraggedOGx);
                    itemBeingDragged.setY(beingDraggedOGy);
                }
        }
    }


    public static void mouseMoved(int mouseX, int mouseY) {
        switch (Screen.GameState) {
            case "DRAGGING":
                itemBeingDragged.setX(mouseX-16);
                itemBeingDragged.setY(mouseY-16);
                break;
        }
    }

    public static void mouseExists(int xOnScreen, int yOnScreen) {
        switch (Screen.GameState) {
            case "RUNNING": case "DEATHSCREEN":
                int mouseX = (int) (MouseInfo.getPointerInfo().getLocation().getX() - xOnScreen);
                int mouseY = (int) (MouseInfo.getPointerInfo().getLocation().getY() - yOnScreen);
                if (Screen.GameState.equals("RUNNING")) {
                    if (autoFire || mouseHeldDownAutofire) {
                        Player.shoot(mouseX, mouseY);
                    }
                }
                if (RoomHandler.itemClose(mouseX, mouseY) != null) {
                    weaponHUDDisplay = RoomHandler.itemClose(mouseX, mouseY);
                    HUD.displayWeapon = true;
                } else {
                    HUD.displayWeapon = false;
                }
                break;
            default:
                mouseHeldDownAutofire = false;
        }

    }

}
