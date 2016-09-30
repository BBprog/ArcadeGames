/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.engine.generic;

import java.awt.Graphics;

/**
 *
 * @author Bprog
 */
public interface ISpecificEngine {
    void initGame();
    void generateGame();
    void updateGame();
    void handleCollision();
    long getUpdatePeriod();
    void draw(Graphics g);
    void keyUp();
    void keyDown();
    void keyLeft();
    void keyRight();
    int getParamInt(int p);
    String getParamString(int p);
    void setEngine(IEngine e);
}
