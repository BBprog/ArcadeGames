/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mods.persist;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Bprog
 */
public class Serialization implements IPersist {

    @Override
    public ArrayList<Score> readList(String name) {
        File f = new File(PATH_SCORES + name + ".sco");
        ArrayList<Score> res = new ArrayList<Score>();
        if (f.exists()) {
            try {
                FileInputStream fis = new FileInputStream(PATH_SCORES + name + ".sco");
                ObjectInputStream ois = new ObjectInputStream(fis);
                res = (ArrayList<Score>) ois.readObject();
                ois.close();
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
            FileOutputStream fos = new FileOutputStream(PATH_SCORES + name + ".sco");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
        } 
        catch(Exception e) {
            System.out.println("write error: " + e.getMessage());
        }
    }
    
}
