package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Somministrazione extends JFrame{


    private JPanel somministrazionePanel;
    private JButton tornaAllaHomeButton;
    private JComboBox comboBox1;

    public Somministrazione(Integer xCord, Integer ycord ){
        Utils.setPositionAndDimensions(xCord,ycord,Somministrazione.this,somministrazionePanel);
        tornaAllaHomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Utils.navigate(Somministrazione.this,somministrazionePanel, Home.class);
            }
        });
    }
}
