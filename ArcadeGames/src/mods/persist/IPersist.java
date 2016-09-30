/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mods.persist;

import java.util.ArrayList;

/**
 *
 * @author Bprog
 */
public interface IPersist {
    static final String PATH_SCORES = "Highscores/";
    ArrayList<Score> readList(String name);
    void writeList(String name, ArrayList<Score> list);
}
