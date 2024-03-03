package org.example.schermate;

import org.example.comboboxs.TesseratiComboBox;
import org.example.entita.Alimento;
import org.example.entita.Tesserati;
import org.example.tables.TableAlimento;
import org.example.utils.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SomministrazionePage extends JFrame{


    private JPanel somministrazionePanel;
    private JButton tornaAllaHomeButton;
    private JComboBox comboBoxTesserati;
    private JTable tableAlimenti;
    private JButton button1;
    private JButton button2;
    private JTable tableAlimentiSomministrati;

    public SomministrazionePage(Integer xCord, Integer ycord ){
        Utils.setPositionAndDimensions(xCord,ycord, SomministrazionePage.this,somministrazionePanel);
        initializeComboBox();
        initializeAlimenti();
        initializeAlimentiSomministrati();
        tornaAllaHomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Utils.navigate(SomministrazionePage.this,somministrazionePanel, Home.class);
            }
        });
    }

    private void initializeComboBox(){
        comboBoxTesserati.setModel(new TesseratiComboBox(Utils.readJsonToListAdvanced("doNotDelete/tesserati.json", Tesserati.class)));
    }

    private void initializeAlimenti(){
        tableAlimenti.setModel(new TableAlimento(Utils.readJsonToListAdvanced("doNotDelete/alimenti.json", Alimento.class)));
        Utils.setTableAlignment(tableAlimenti);
    }

    private void initializeAlimentiSomministrati(){
        tableAlimentiSomministrati.setModel(new TableAlimento(new ArrayList<>()));
    }
}
