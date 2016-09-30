/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mods.persist;

import app.engine.generic.IBestScore;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;

/**
 *
 * @author Bprog
 */
public class BestScore implements IBestScore {
    private IPersist persist;
    private String gameName;
    private ArrayList<Score> top5;
    
    public BestScore(String name, String pers) {
        gameName = name;
        top5 = null;
        if (pers.equals("TEXT_FILE")) persist = new TextFile();
        else if (pers.equals("SERIALIZATION")) persist = new Serialization();
        top5 = persist.readList(gameName);
        if (top5 == null) top5 = new ArrayList<Score>();
    }
    
    public void addTop5 (int score) {
        if (top5.size() < 5) keepScore(score);
        else {
            Score min = top5.get(0);
            if (score > min.getScore()) {
                top5.remove(0);
                keepScore(score);
            }
        }
        persist.writeList(gameName, top5);
    }
    private void keepScore(int score) {
        String playerName = JOptionPane.showInputDialog(null, 
                "Nouveau meilleur score! \nTapez votre nom", "Meilleur score", 
                JOptionPane.PLAIN_MESSAGE);
        top5.add(new Score(playerName, score));
        Collections.sort(top5);
    }
    public String displayTop5() {
        String s = "";
        Score sc;
        for (int i = 0; i < top5.size(); ++i) {
            sc = top5.get(i);
            s = s + sc.getPlayerName() + " " + sc.getScore() + "\n";
        }
        return s;
    }
}
