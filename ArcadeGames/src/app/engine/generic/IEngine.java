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
public interface IEngine {
    void startGame();
    void endGame();
    void notification();
    void setSpecificEngine(ISpecificEngine spe);
    ISpecificEngine getSpecificEngine();
    void setGui(IGui gui);
    IGui getGui();
    void draw(Graphics g);
    void keyUp();
    void keyDown();
    void keyLeft();
    void keyRight();
    int getParamInt(int p);
    String getParamString(int p);
    EngineState getState();
    void setState(EngineState state);
    int getScore();
    int getLevel();
    ISound getSound();
    void setScore(int s);
    void setLevel(int l);
    void setSound(ISound s);
}
