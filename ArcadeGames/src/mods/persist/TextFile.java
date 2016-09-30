/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mods.persist;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 *
 * @author Bprog
 */
public class TextFile implements IPersist {

    @Override
    public ArrayList<Score> readList(String name) {
        File f = new File(PATH_SCORES + name + ".sco.txt");
        ArrayList<Score> res = new ArrayList<Score>();
        if (f.exists()) {
            try {
                String line = null;
                String[] words = new String[2];
                Score s = null;
                FileReader fr = new FileReader(PATH_SCORES + name + ".sco.txt");
                BufferedReader br = new BufferedReader (fr);
                while ((line = br.readLine()) != null) {
                   words = line.split("\t");
                   s = new Score(words[0], Integer.parseInt(words[1]));
                   res.add(s);
                }
                br.close();
            } 
            catch(Exception e) {
                System.out.println("read error: " + e.getMessage());
            }
        }
        return res;
    }

    @Override
    public void writeList(String name, ArrayList<Score> list) {
        try {
            FileWriter fw = new FileWriter(PATH_SCORES + name + ".sco.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (Score s : list) {
                bw.write(s.getPlayerName() + "\t" + s.getScore());
                bw.newLine();
            }
            bw.close();
        } 
        catch (Exception e) {
            System.out.println("write error: " + e.getMessage());
        }
    }
    
}
