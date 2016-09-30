/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.engine.generic;

/**
 *
 * @author Bprog
 */
public interface IBestScore {
    void addTop5 (int score);
    String displayTop5();
}
