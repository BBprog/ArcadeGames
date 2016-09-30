/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.engine.specific.tetris;

import app.engine.generic.GameObjectAbstract;
import app.engine.generic.IEngine;
import app.engine.generic.IObjectFactory;

/**
 *
 * @author Bprog
 */
public class TetrisFactory implements IObjectFactory {
    private IEngine engine;

    @Override
    public GameObjectAbstract createObject(int type) {
        GameObjectAbstract result = null;
        if (type == TetrisParam.TETROMINO) result = new Tetromino();
        result.setSpecificEngine(engine.getSpecificEngine());
        return result;
    }

    @Override
    public void setEngine(IEngine e) { engine = e; }
    
}
