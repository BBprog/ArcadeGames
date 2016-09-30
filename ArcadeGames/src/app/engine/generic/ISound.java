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
public interface ISound {
    void init();
    boolean isOn();
    void setOn();
    void setOff();
    void playCollision();
    void playEnd();
}
