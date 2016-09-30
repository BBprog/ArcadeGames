/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.engine.generic;

import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author Bprog
 */
public abstract class GameObjectAbstract {
    protected ISpecificEngine engine;
    protected Random rand;
    protected int x;
    protected int y;
    
    public GameObjectAbstract() { rand = new Random(); }
    
    public void setSpecificEngine(ISpecificEngine spe) { this.engine = spe; }
    public void setX(int a) { this.x = a; }
    public void setY(int b) { this.y = b; }
    
    public int getX() { return x; }
    public int getY() { return y; }
    
    public abstract void generate();
    public abstract void update();
    public abstract void draw(Graphics g);
}
