/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gui.generic;

import app.engine.generic.IEngine;
import java.util.Observable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Bprog
 */
public class ScorePanel implements IScorePanel {
    private JPanel panel;
    private JTextField score;
    private JTextField level;
    
    public ScorePanel () {
        panel = new JPanel();
        JLabel l_score = new JLabel("Score");
        panel.add(l_score);
        score = new JTextField("0", 5);
        score.setEditable(false);
        panel.add(score);
        JLabel l_level = new JLabel ("Niveau");
        panel.add(l_level);
        level = new JTextField("1", 5);
        level.setEditable(false);
        panel.add(level);
    }

    @Override
    public void update(Observable o, Object arg) {
        IEngine engine = (IEngine) o;
        score.setText(""+engine.getScore());
        level.setText(""+engine.getLevel());
    }
    
    @Override
    public JPanel getPanel() { return panel; }
}
