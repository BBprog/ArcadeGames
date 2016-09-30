/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.game.specific.snake;

import app.engine.specific.snake.SnakeEngine;
import app.game.generic.FitterGeneric;
import view.gui.specific.snake.SnakeGameArea;

/**
 *
 * @author Bprog
 */
public class Snake {
    public static void main(String[] args) {
        new FitterGeneric("Tetris", new SnakeGameArea(), new SnakeEngine(), "SERIALIZATION").construct();
    }
}
