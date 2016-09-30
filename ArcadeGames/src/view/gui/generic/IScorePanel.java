/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gui.generic;

import java.util.Observer;
import javax.swing.JPanel;

/**
 *
 * @author Bprog
 */
public interface IScorePanel extends Observer {
    JPanel getPanel();
}
