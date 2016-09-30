/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gui.generic;

import app.engine.generic.EngineState;
import app.engine.generic.IEngine;
import app.engine.generic.IGui;
import app.engine.generic.ISound;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Bprog
 */
public class ControlPanel implements IControlPanel {
    private IGui gui;
    private IEngine engine;
    private JButton start_pause;
    private JButton stop;
    private JButton sound_on_off;
    private JPanel panel;
    static ImageIcon img_pause = new ImageIcon("Image/pause.png");
    static ImageIcon img_stop = new ImageIcon("Image/stop.png");
    static ImageIcon img_start = new ImageIcon("Image/start.png");
    static ImageIcon img_sound_on = new ImageIcon("Image/sound_on.png");
    static ImageIcon img_sound_off = new ImageIcon("Image/sound_off.png");
    
    private class ActivationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            EngineState state = engine.getState();
            switch (state) {
                case INITIALIZE:
                case GAMEOVER:
                    start_pause.setIcon(img_pause);
                    engine.startGame();
                    break;
                case PROCESSING:
                    engine.setState(EngineState.STOP);
                    start_pause.setIcon(img_start);
                    break;
                case STOP:
                    engine.setState(EngineState.PROCESSING);
                    start_pause.setIcon(img_pause);
                    break;
            }
            stop.setEnabled(true);
            gui.focusGamePanel();
        }       
    }
    private class StopListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            engine.setState(EngineState.GAMEOVER);
            start_pause.setIcon(img_start);
            start_pause.setEnabled(true);
            stop.setEnabled(false);
            gui.repaintGamePanel();
        }       
    }
    private class SoundListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ISound sound = engine.getSound();
            if (sound.isOn()) {
                sound.setOff();
                sound_on_off.setIcon(img_sound_off);
            }
            else {
                sound.setOn();
                sound_on_off.setIcon(img_sound_on);
            }
            gui.focusGamePanel();
        }       
    }
    
    public ControlPanel(final IGui gui) {
        this.gui = gui;
        engine = gui.getEngine();
        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        start_pause = new JButton(img_start);
        start_pause.setEnabled(true);
        panel.add(start_pause);
        stop = new JButton(img_stop);
        stop.setEnabled(false);
        panel.add(stop);
        sound_on_off = new JButton(img_sound_off);
        sound_on_off.setEnabled(true);
        panel.add(stop);
        start_pause.addActionListener(new ActivationListener());
        stop.addActionListener(new StopListener());
        sound_on_off.addActionListener(new SoundListener());    
    }
    
    @Override
    public void reset() {
        start_pause.setIcon(img_start);
        start_pause.setEnabled(true);
        stop.setEnabled(false);
    }

    @Override
    public JPanel getPanel() { return panel; }
    
}
