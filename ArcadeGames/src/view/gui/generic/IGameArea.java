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
public interface IGameArea {
    int getNbLines();
    int getNbColumns();
    int getCellSize();
    void paint(Graphics g);
    void pressKey(int code);
    void setGui(IGui gui);
    void setEngine(IEngine engine);
}
