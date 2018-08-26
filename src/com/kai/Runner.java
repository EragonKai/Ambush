package com.kai;

import javax.swing.JFrame;

// CONTROLS:
// W/A/S/D TO MOVE
// CLICK TO PICK UP AN ITEM. WHILE DRAGGING IT AROUND, PRESS E TO OPEN ITS STATS AND CLICK ON YOUR CURRENT EQUIPPED WEAPON TO REPLACE IT
// LEFT CLICK TO SHOOT
// LEAVE A ROOM AND GO OFF SCREEN TO ENTER THE NEXT ONE
// ENEMIES RESPAWN IN ROOMS AND WEAPONS ON THE FLOOR DISAPPEAR

public class Runner {
    public static void main(String args[]) {
        JFrame frame = new JFrame("Ambush");
        Screen s1 = new Screen();
        frame.add(s1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        s1.animate();
    }
}
