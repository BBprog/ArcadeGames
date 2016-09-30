/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gui.generic;

import app.engine.generic.IBestScore;
import app.engine.generic.IEngine;
import app.engine.generic.IGui;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Bprog
 */
public class Gui implements IGui {
    private IControlPanel p_control;
    private IScorePanel p_score;
    private IGamePanel p_game;
    private IBestScore best_score;
    private IEngine engine;
    private JFrame window;
    
    public Gui(String name) { window = new JFrame(name); }
    public void setGamePanel(IGamePanel pg) { p_game = pg; }
    
    @Override
    public void terminateGui() {
        Container cp = window.getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(p_game.getPanel(), BorderLayout.CENTER);
        Dimension d = new Dimension(p_game.getWidth(), p_game.getHeight());
        p_game.getPanel().setPreferredSize(d);
        JPanel bottom = new JPanel();
        bottom.setLayout(new BorderLayout());
        p_score = new ScorePanel();
        bottom.add(p_score.getPanel(), BorderLayout.NORTH);
        p_control = new ControlPanel(this);
        bottom.add(p_control.getPanel(), BorderLayout.CENTER);
        cp.add(bottom, BorderLayout.SOUTH);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setLocationRelativeTo(window.getParent());
        window.setVisible(true);
        ((Observable) engine).addObserver(p_score);
    }
    
    @Override
    public void setBestScore(IBestScore bs) { best_score = bs; }
    @Override
    public void setEngine(IEngine e) { engine = e; }
    
    @Override
    public void resetControlPanel() { 
        p_control.reset();
    }
    
    @Override
    public void repaintGamePanel() {
        p_game.getPanel().repaint();
    }
    @Override
    public void focusGamePanel() {
        p_game.getPanel().requestFocus();
    }

    @Override
    public void drawGameover(Graphics g) {
        p_game.drawGameover(g);
    }

    @Override
    public void setGamePanelBackgroundImage(String s, Graphics g) {
        p_game.fillBackgroundImage(s, g);
    }

    @Override
    public boolean containsGamePanel(int x, int y) { 
        return p_game.contains(x, y); 
    }
    
    public IControlPanel getP_control() { return p_control; }
    public IScorePanel getP_score() { return p_score; }
    public IGamePanel getP_game() { return p_game; }
    @Override
    public IBestScore getBestScore() { return best_score; }
    @Override
    public IEngine getEngine() { return engine; }    
}
