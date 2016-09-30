/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.game.specific.tetris;

import app.engine.specific.tetris.TetrisEngine;
import app.game.generic.FitterGeneric;
import view.gui.specific.tetris.TetrisGameArea;

/**
 *
 * @author Bprog
 */
public class Tetris {
    public static void main(String[] args) {
        new FitterGeneric("Tetris", new TetrisGameArea(), new TetrisEngine(), "TEXT_FILE").construct();
    }
}
