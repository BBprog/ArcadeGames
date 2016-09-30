/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.engine.specific.tetris;

import app.engine.generic.GameObjectAbstract;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Bprog
 */
public class Tetromino extends GameObjectAbstract {
    public static enum Tetrominoes {Vide, Z, S, Line, T, Square, L, ReverseL}
    private Tetrominoes shape;
    private int coords[][];
    
    public Tetromino() {
        coords = new int[4][2];
        setRandomShape();
        x = (TetrisParam.COLUMNS / 2) - 1;
        y = 0;
    }
    
    public void setRandomShape() {
        int x = Math.abs(rand.nextInt()) % 7 + 1;
        Tetrominoes[] values = Tetrominoes.values();
        setShape(values[x]);
    }
    public void setShape(Tetrominoes s) {
        int[][][] coordsTable = new int [][][] {
            { { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 } },
            { { 0, -1 }, { 0, 0 }, { -1, 0 }, { -1, 1 } },
            { { 0, -1 }, { 0, 0 }, { 1, 0 }, { 1, 1 } },
            { { 0, -1 }, { 0, 0 }, { 0, 1 }, { 0, 2 } },
            { { -1, 0 }, { 0, 0 }, { 1, 0 }, { 0, 1 } },
            { { 0, 0 }, { 1, 0 }, { 0, 1 }, { 1, 1 } },
            { { -1, -1 }, { 0, -1 }, { 0, 0 }, { 0, 1 } },
            { { 1, -1 }, { 0, -1 }, { 0, 0 }, { 0, 1 } }
        };
        for (int i = 0; i < 4; ++i)
            for (int j = 0; j < 2; ++j)
                coords[i][j] = coordsTable[s.ordinal()][i][j];
        shape = s;
    }
    
    public int[][] getCells(int[][] c) {
        int[][] cells = new int[4][2];
        for (int i = 0; i < 4; ++i) {
            cells[i][0] = x + c[i][0];
            cells[i][1] = y + c[i][1];
        }
        return cells;
    }
    public void moveLeft() {
        if (((TetrisEngine) engine).canMoveLeft(getCells(coords))) --x;
    }
    public void moveRight() {
        if (((TetrisEngine) engine).canMoveRight(getCells(coords))) ++x;
    }
    public void turnLeft() {
        if (shape == Tetrominoes.Square) return;
        int old;
        int[][] ncoords = new int[4][2];
        for (int i = 0; i < 4; ++i) {
            old = coords[i][0];
            ncoords[i][0] = coords[i][1];
            ncoords[i][1] = -old;
        }
        if (((TetrisEngine) engine).canTurn(getCells(ncoords))) {
            for (int i = 0; i < 4; ++i)
                for (int j = 0; j < 2; ++j)
                    coords[i][j] = ncoords[i][j];
        }
    }
    public void turnRight() {
        if (shape == Tetrominoes.Square) return;
        int old;
        int[][] ncoords = new int[4][2];
        for (int i = 0; i < 4; ++i) {
            old = coords[i][0];
            ncoords[i][0] = -coords[i][1];
            ncoords[i][1] = old;
        }
        if (((TetrisEngine) engine).canTurn(getCells(ncoords))) {
            for (int i = 0; i < 4; ++i)
                for (int j = 0; j < 2; ++j)
                    coords[i][j] = ncoords[i][j];
        }
    }
    
    @Override
    public void generate() {}

    @Override
    public void update() { ++y; }

    @Override
    public void draw(Graphics g) {
        int t = TetrisParam.CELL_SIZE;
        if (shape != Tetrominoes.Vide)
            for (int i = 0; i < 4; ++i)
                drawSquare(g, (x + coords[i][0]) * t, (y + coords[i][1]) * t);
    }
    public void drawSquare(Graphics g, int i, int j) {
        int t = TetrisParam.CELL_SIZE;
        g.setColor(Tetromino.getColor(shape));
        g.fillRect(i, j, t, t);
    }
    
    public static Color getColor (Tetrominoes shape) {
        Color colors[] = {new Color(0, 0, 0), new Color(204, 102, 102),
                          new Color(102, 204, 102), new Color(102, 102, 204),
                          new Color(204, 204, 102), new Color(204, 102, 204),
                          new Color(102, 204, 204), new Color(218, 170, 0)};
        return colors[shape.ordinal()];    
    }
    public Tetrominoes getShape() { return shape; }
    public int[][] getCoords() { return coords; }
}
