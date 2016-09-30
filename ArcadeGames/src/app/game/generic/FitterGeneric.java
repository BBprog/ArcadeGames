/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.game.generic;

import app.engine.generic.Engine;
import app.engine.generic.ISpecificEngine;
import mods.persist.BestScore;
import mods.utils.sound.Sound;
import view.gui.generic.GamePanel;
import view.gui.generic.Gui;
import view.gui.generic.IGameArea;

/**
 *
 * @author Bprog
 */
public class FitterGeneric extends FitterAbstract {
    private Gui gui;
    private Engine engine;
    private IGameArea area;
    private ISpecificEngine spe;
    private String name;
    private String pers;
    
    public FitterGeneric(String n, IGameArea a, ISpecificEngine e, String p) {
        name = n;
        area = a;
        spe = e;
        pers = p;
    }

    @Override
    public void constructGui() {
        gui = new Gui(name);
        GamePanel pGame = new GamePanel(gui);
        gui.setGamePanel(pGame);
        pGame.setGameArea(area);
        area.setGui(gui);
    }

    @Override
    public void constructEngine() {
        engine = new Engine();
        engine.setGui(gui);
        gui.setEngine(engine);
        engine.setSpecificEngine(spe);
        spe.setEngine(engine);
        area.setEngine(engine);
    }

    @Override
    public void addSound() {
        /*
        Sound sound = new Sound();
        sound.init();
        engine.setSound(sound);
        */
    }

    @Override
    public void addPersistant() {
        BestScore bs = new BestScore(name, pers);
        gui.setBestScore(bs);
    }

    @Override
    public void endConstruction() {
        gui.terminateGui();
    }
    
}
