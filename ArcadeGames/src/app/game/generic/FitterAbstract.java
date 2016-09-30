/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.game.generic;

/**
 *
 * @author Bprog
 */
public abstract class FitterAbstract {
    public void construct() {
        constructGui();
        constructEngine();
        addSound();
        addPersistant();
        endConstruction();
    }
    public abstract void constructGui();
    public abstract void constructEngine();
    public abstract void addSound();
    public abstract void addPersistant();
    public abstract void endConstruction();
}
