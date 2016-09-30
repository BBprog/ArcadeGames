/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gui.generic;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Bprog
 */
public interface IGamePanel {
    boolean contains(int x, int y);
    void drawGameover(Graphics g);
    void fillBackgroundImage(String imageFile, Graphics g);
    IGameArea getGameArea();
    int getWidth();
    int getHeight();
    void setGameArea(IGameArea spe);
    JPanel getPanel();
}
