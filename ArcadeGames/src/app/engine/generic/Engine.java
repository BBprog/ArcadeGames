/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.engine.generic;

import java.awt.Graphics;
import java.util.Observable;

/**
 *
 * @author Bprog
 */
public class Engine extends Observable implements IEngine {
    private ISpecificEngine engine;
    private IGui gui;
    private ISound sound;
    protected EngineState state;
    private int score;
    private int level;
    
    public Engine () { state = EngineState.INITIALIZE; }

    @Override
    public void startGame() {
        Thread gameThread = new Thread() {
            public void run() {
                engine.initGame();
                gameLoop();
            }
        };
        gameThread.start();
    }
    private void gameLoop() {
        if (state == EngineState.INITIALIZE || state == EngineState.GAMEOVER) {
            engine.generateGame();
            state = EngineState.PROCESSING;
        }
        long timeBeg, timeTaken, timeLeft;
        while (true) {
            timeBeg = System.nanoTime();
            if (state == EngineState.GAMEOVER) {
                gui.resetControlPanel();
                break;
            }
            if (state == EngineState.PROCESSING) engine.updateGame();
            gui.repaintGamePanel();
            timeTaken = System.nanoTime() - timeBeg;
            timeLeft = (engine.getUpdatePeriod() - timeTaken) / 1000000;
            if (timeLeft < 10) timeLeft = 10;
            try {
                Thread.sleep(timeLeft);
            } catch (InterruptedException e) {}
        }
    }

    @Override
    public void endGame() {
        //sound.playEnd();
        setState(EngineState.GAMEOVER);
        gui.getBestScore().addTop5(score);
    }

    @Override
    public void notification() {
        setChanged();
        notifyObservers();
    }
    
    @Override
    public void draw(Graphics g) { engine.draw(g); }

    @Override
    public void keyUp() { engine.keyUp(); }
    @Override
    public void keyDown() { engine.keyDown(); }
    @Override
    public void keyLeft() { engine.keyLeft(); }
    @Override
    public void keyRight() { engine.keyRight(); }
    
    @Override
    public void setSpecificEngine(ISpecificEngine spe) { engine = spe; }
    @Override
    public void setGui(IGui gui) { this.gui = gui; }
    @Override
    public void setScore(int s) { score = s; }
    @Override
    public void setLevel(int l) { level = l; }
    @Override
    public void setSound(ISound s) { sound = s; }   
    @Override
    public void setState(EngineState state) { this.state = state; }
    
    @Override
    public int getParamInt(int p) { return engine.getParamInt(p); }
    @Override
    public String getParamString(int p) { return engine.getParamString(p); }
    @Override
    public EngineState getState() { return state; }
    @Override
    public ISpecificEngine getSpecificEngine() { return engine; }
    @Override
    public IGui getGui() { return gui; }
    @Override
    public int getScore() { return score; }
    @Override
    public int getLevel() { return level; }
    @Override
    public ISound getSound() { return sound; }    
}
