/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gui.specific.pacman;

import app.engine.generic.EngineState;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import view.gui.generic.GameAreaAbstract;

/**
 *
 * @author Bprog
 */
public class PacmanGameArea extends GameAreaAbstract {
    
    public void paint(Graphics g) {
        if (engine.getState() != EngineState.GAMEOVER) engine.draw(g);
        else gui.drawGameover(g);
    }   
    
    public void pressKey(int code) {
        switch (code) {
            case KeyEvent.VK_UP:
                engine.keyUp(); break;
            case KeyEvent.VK_DOWN:
                engine.keyDown(); break;
            case KeyEvent.VK_LEFT:
                engine.keyLeft(); break;
            case KeyEvent.VK_RIGHT:
                engine.keyRight(); break;
                
        }
    }
}
