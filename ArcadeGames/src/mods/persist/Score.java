/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mods.persist;

import java.io.Serializable;

/**
 *
 * @author Bprog
 */
public class Score implements Comparable<Object>, Serializable {
    private String playerName;
    private int score;
    
    public Score (String n, int s) {
        playerName = n;
        score = s;
    }
    
    @Override
    public int compareTo(Object o) {
        Score s = (Score) o;
        if (this.score < s.getScore()) return -1;
        else if (this.score == s.getScore()) return 0;
        else return 1;
    }

    public void setScore(int score) { this.score = score; }
    
    public String getPlayerName() { return playerName; }
    public int getScore() { return score; }

    @Override
    public String toString() { return playerName + " " + score; }
}
