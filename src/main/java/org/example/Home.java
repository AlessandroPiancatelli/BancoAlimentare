package org.example;

import org.apache.commons.lang3.tuple.Pair;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home  extends JFrame{
    private JButton inventarioButton;
    private JButton tesseratiButton;
    private JButton nuovaSomministrazioneButton;
    private JPanel home;

    public Home(Integer xCord, Integer ycord){

        Utils.setPositionAndDimensions(xCord,ycord,Home.this,home);

        inventarioButton.addActionListener(e -> Utils.navigate(Home.this,home, Inventario.class));
        tesseratiButton.addActionListener(e -> Utils.navigate(Home.this,home, TesseratiPage.class));
        nuovaSomministrazioneButton.addActionListener(e -> Utils.navigate(Home.this,home, Somministrazione.class));
    }

    private void initialize(){

    }

}
