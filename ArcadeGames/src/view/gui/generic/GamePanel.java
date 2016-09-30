/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gui.generic;

import app.engine.generic.EngineState;
import app.engine.generic.IGui;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Bprog
 */
public class GamePanel implements IGamePanel {
    private IGameArea area;
    private IGui gui;
    private JPanel panel;
    private class TouchListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            if (gui.getEngine().getState() == EngineState.STOP) return;
            area.pressKey(e.getKeyCode());
        }
    }
    
    public GamePanel(IGui g) {
        this.gui = g;
        panel = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                area.paint(g);
            }
        };
        panel.setFocusable(true);
        panel.requestFocus();
        panel.addKeyListener(new TouchListener());
    }

    @Override
    public boolean contains(int x, int y) {
        if ((x < 0) || (x >= area.getNbLines())) return false;
        if ((y < 0) || (y >= area.getNbColumns())) return false;
        return true;
    }

    @Override
    public void drawGameover(Graphics g) {
        int h = getHeight() / 5;
        g.setColor(Color.YELLOW);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setFont(new Font("Verdana", Font.BOLD, 20));
        g.setColor(Color.RED);
        g.drawString("GAME OVER!", position(g, "GAME OVER!"), h);
        h += h;
        String s = gui.getBestScore().displayTop5();
        g.setColor(Color.GRAY);
        g.drawString("MEILLEURS SCORES:", position(g, "MEILLEURS SCORES:"), h);
        for (String line : s.split("\n")) {
            h += g.getFontMetrics().getHeight();
            g.drawString(line, position(g, line), h);
        }
    }
    private int position(Graphics g, String s) {
        return getWidth() / 2 - (g.getFontMetrics().stringWidth(s)) / 2;
    }

    @Override
    public void fillBackgroundImage(String imagePath, Graphics g) {
        try {
            BufferedImage bimg = ImageIO.read(new File(imagePath));
            Graphics2D g2 = (Graphics2D) g;
            Rectangle r = new Rectangle(0, 0, 32, 32);
            g2.setPaint(new TexturePaint(bimg, r));
            Rectangle rect = new Rectangle(0, 0, getWidth(), getHeight());
            g2.fill(rect);
        } catch (Exception e) {}
    }

    @Override
    public void setGameArea(IGameArea spe) { area = spe; }

    @Override
    public IGameArea getGameArea() { return area; }
    @Override
    public int getWidth() { return area.getNbColumns() * area.getCellSize(); }
    @Override
    public int getHeight() { return area.getNbLines() * area.getCellSize(); }
    @Override
    public JPanel getPanel() { return panel; }
    
}
