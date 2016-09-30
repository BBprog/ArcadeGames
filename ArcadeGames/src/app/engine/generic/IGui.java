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
public interface IGui {
    void terminateGui();
    void setBestScore(IBestScore b);
    void setEngine(IEngine e);
    void resetControlPanel();
    void repaintGamePanel();
    void focusGamePanel();
    void drawGameover(Graphics g);
    public void setGamePanelBackgroundImage(String s, Graphics g);
    boolean containsGamePanel(int x, int y);
    IBestScore getBestScore();
    IEngine getEngine();
}
