package com.kai;

import com.kai.UI.DeathScreen;
import com.kai.UI.HUD;
import com.kai.UI.StartMenu;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.*;
import com.kai.UI.Map;


public class Screen extends JPanel implements KeyListener, MouseListener, MouseMotionListener {
    Draw imageLoader;
    Player p1;
    StartMenu menu;
    RoomHandler rh;
    HUD hud;
    Map map;
    DeathScreen ds;
    int xOnScreen;
    int yOnScreen;

    public static String GameState;

    public Screen() {
        GameState = "STARTMENU";
        imageLoader = new Draw();
        menu = new StartMenu();
        p1 = new Player();
        rh = new RoomHandler();
        hud = new HUD();
        map = new Map(152, 20);
        ds = new DeathScreen();

        setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public Dimension getPreferredSize() {
        return new Dimension(1224,768);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = (int)getLocationOnScreen().getX();
        int y = (int)getLocationOnScreen().getY();

        Input.mouseExists(x,y);

        switch (GameState) {
            case "STARTMENU":
                StartMenu.drawSelf(g);
                break;
            case "RUNNING": case "DRAGGING": case "MAP":
                RoomHandler.drawCurrentRoom(g);
                RoomHandler.checkIfCompleted();
                RoomHandler.enemies();

                p1.drawMe(g);
                p1.move();

                HUD.drawSelf(g);
                RoomHandler.drawItems(g);

                if(GameState.equals("MAP")) {
                    map.drawSelf(g);
                }
                break;
            case "DEATHSCREEN":
                Player.health = 0;
                RoomHandler.itemsOnFloor.clear();
                DeathScreen.drawMe(g);
                HUD.drawSelf(g);
                RoomHandler.drawItems(g);
                break;
        }
    }

    public void animate() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch(InterruptedException ex) { Thread.currentThread().interrupt(); }

            repaint();

        }

    }

    public void keyPressed(KeyEvent e) {
        Input.keyPressed(e.getKeyCode());
    }
    public void keyReleased(KeyEvent e) {
        Input.keyReleased(e.getKeyCode());
    }
    public void keyTyped(KeyEvent e) {}
    public void mousePressed(MouseEvent e) { Input.mousePressed(e.getX(), e.getY(), e.getButton());}
    public void mouseReleased(MouseEvent e) {
        Input.mouseReleased(e.getX(), e.getY(), e.getButton());
    }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e){}
    public void mouseMoved(MouseEvent e) { Input.mouseMoved(e.getX(), e.getY()); }
    public void mouseDragged(MouseEvent e) {}
}
