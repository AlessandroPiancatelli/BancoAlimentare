package org.example.schermate;

import org.example.utils.Utils;

import javax.swing.*;

public class Home  extends JFrame{
    private JButton inventarioButton;
    private JButton tesseratiButton;
    private JButton nuovaSomministrazioneButton;
    private JPanel home;

    public Home(Integer xCord, Integer ycord){
        Utils.setPositionAndDimensions(xCord,ycord,Home.this,home);
        inventarioButton.addActionListener(e -> Utils.navigate(Home.this,home, InventarioPage.class));
        tesseratiButton.addActionListener(e -> Utils.navigate(Home.this,home, TesseratiPage.class));
        nuovaSomministrazioneButton.addActionListener(e -> Utils.navigate(Home.this,home, SomministrazionePage.class));
    }

    private void initialize(){

    }

}
