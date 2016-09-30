/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gui.generic;

import app.engine.generic.IEngine;
import app.engine.generic.IGui;
import java.awt.Graphics;

/**
 *
 * @author Bprog
 */
public abstract class GameAreaAbstract implements IGameArea {
    protected IGui gui;
    protected IEngine engine;

    @Override
    public void paint(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pressKey(int code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setGui(IGui gui) { this.gui = gui; }
    @Override
    public void setEngine(IEngine engine) { this.engine = engine; }
    
    @Override
    public int getNbLines() { return engine.getParamInt(1); }
    @Override
    public int getNbColumns() { return engine.getParamInt(2); }
    @Override
    public int getCellSize() { return engine.getParamInt(3); }
    public String getBackgroundImage() { return engine.getParamString(1); };
}
