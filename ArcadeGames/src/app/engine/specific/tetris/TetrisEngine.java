/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.engine.specific.tetris;

import app.engine.generic.EngineState;
import app.engine.generic.IEngine;
import app.engine.generic.ISpecificEngine;
import java.awt.Graphics;

/**
 *
 * @author Bprog
 */
public class TetrisEngine implements ISpecificEngine {
    private Tetromino tetromino;
    private IEngine engine;
    private int updateFreq;
    private TetrisFactory factory;
    private Tetromino.Tetrominoes [][] tas;
    
    public TetrisEngine() { factory = new TetrisFactory(); }

    @Override
    public void initGame() {
        factory.setEngine(engine);
        tas = new Tetromino.Tetrominoes[TetrisParam.LINES][TetrisParam.COLUMNS];
        for (int i = 0; i < TetrisParam.LINES; ++i)
            for (int j = 0; j < TetrisParam.COLUMNS; ++j)
                tas[i][j] = Tetromino.Tetrominoes.Vide;
        tetromino = (Tetromino) factory.createObject(TetrisParam.TETROMINO);
    }

    @Override
    public void generateGame() {
        engine.setScore(0);
        engine.setLevel(1);
        updateFreq = TetrisParam.UPDATE_FREQ;
        engine.notification();
        engine.setState(EngineState.INITIALIZE);
    }

    @Override
    public void updateGame() {
        handleCollision();
        tetromino.update();
        searchFilledLines();
        if (isTasFull()) engine.endGame();
    }
    private void searchFilledLines() {
        boolean full = true;
        for (int i = 0; i < TetrisParam.LINES; ++i) {
            for (int j = 0; j < TetrisParam.COLUMNS; ++j) {
                if (tas[i][j] == Tetromino.Tetrominoes.Vide) {
                    full = false;
                    break;
                }
            }
            if (full) {
                engine.setScore(engine.getScore() + 100);
                updateTas(i);
            } 
            else full = true;
        }                        
    }
    private void updateTas(int i) {
        for (int k = i; k > 0; k--)
            for (int l = 0; l < TetrisParam.COLUMNS; ++l)
                tas[k][l] = tas[k - 1][l];
        changeLevel();
        engine.notification();
    }
    private boolean isTasFull() {
        boolean res = false;
        for (int j = 0; j < TetrisParam.COLUMNS; ++j) {
            if (tas[1][j] != Tetromino.Tetrominoes.Vide) {
                res = true;
                break;
            }
        }
        return res;
    }
    
    public boolean canMoveLeft(int[][] cels) {
        boolean res = true;
        for (int i = 0; i < 4; ++i) {
            if (cels[i][0] - 1 < 0 || cels[i][1] < 0) {
                res = false;
                break;
            }
            else if (tas[cels[i][1]][cels[i][0] - 1] != Tetromino.Tetrominoes.Vide) {
                res = false;
                break;
            }
        }
        return res;
    }
    public boolean canMoveRight(int[][] cels) {
        boolean res = true;
        for (int i = 0; i < 4; ++i) {
            if (cels[i][0] + 1 > TetrisParam.COLUMNS - 1) {
                res = false;
                break;
            }
            else if (tas[cels[i][1]][cels[i][0] + 1] != Tetromino.Tetrominoes.Vide) {
                res = false;
                break;
            }
        }
        return res;
    }
    public boolean canTurn(int[][] cels) {
        boolean res = true;
        for (int i = 0; i < 4; ++i) {
            if (cels[i][0] > TetrisParam.COLUMNS - 1 || 
                cels[i][0] < 0 ||
                cels[i][1] > TetrisParam.LINES - 1) {
                res = false;
                break;
            }
            else if (tas[cels[i][1]][cels[i][0]] != Tetromino.Tetrominoes.Vide) {
                res = false;
                break;
            }
        }
        return res;
    }

    @Override
    public void handleCollision() {
        int[][] cds = tetromino.getCoords();
        int y = tetromino.getY();
        int x = tetromino.getX();
        for (int i = 0; i < 4; ++i){
            if ((y + cds[i][1] == TetrisParam.LINES - 1) || 
                (tas [y + cds[i][1] + 1][x + cds[i][0]] != Tetromino.Tetrominoes.Vide)) {
                storeAndChange();
                break;                
            }
        }            
    }
    public void storeAndChange() {
        int[][] coords = tetromino.getCoords();
        int y = tetromino.getY();
        int x = tetromino.getX();
        for (int i = 0; i < 4; ++i) {
            tas [y + coords[i][1]][x + coords[i][0]] = tetromino.getShape();
        }
        tetromino = (Tetromino) factory.createObject(TetrisParam.TETROMINO);
        tetromino.setRandomShape();
        engine.setScore(engine.getScore() + 1);
        engine.notification();
    }
    public void changeLevel() {
        engine.setLevel(engine.getLevel() + 1);
        updateFreq += 1;
    } 
    
    public void drawTas(Graphics g) {
        int t = TetrisParam.CELL_SIZE;
        for (int i = 0; i < TetrisParam.COLUMNS; ++i) {
            for (int j = 0; j < TetrisParam.LINES; ++j) {
                if (tas[j][i] != Tetromino.Tetrominoes.Vide) {
                    g.setColor(Tetromino.getColor(tas[j][i]));
                    g.fillRect(i * t, j * t, t, t);
                }
            }
        }
    }
    
    @Override
    public void draw(Graphics g) {
        switch (engine.getState()) {
            case PROCESSING:
            case STOP:
                tetromino.draw(g);
                drawTas(g);
                break;
            case GAMEOVER:
            case INITIALIZE: break;
        }
    }

    @Override
    public void keyUp() { tetromino.turnLeft(); }
    @Override
    public void keyDown() { tetromino.turnRight(); }
    @Override
    public void keyLeft() { tetromino.moveLeft(); }
    @Override
    public void keyRight() { tetromino.moveRight(); }

    @Override
    public void setEngine(IEngine e) { engine = e; }  
    
    @Override
    public int getParamInt(int p) {
        int res;
        switch (p) {
            case 1:
                res = TetrisParam.LINES; break;
            case 2:
                res = TetrisParam.COLUMNS; break;
            case 3:
                res = TetrisParam.CELL_SIZE; break;
            default:
                res = 0;
        }
        return res;
    }
    @Override
    public String getParamString(int p) { return new String(); }
    @Override
    public long getUpdatePeriod() { return 1000000000L / updateFreq; }
    public Tetromino getTetromino() { return tetromino; }
}
