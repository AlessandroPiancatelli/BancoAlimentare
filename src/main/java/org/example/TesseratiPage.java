package org.example;

import com.fasterxml.jackson.core.type.TypeReference;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.example.Utils.readJsonToList;

public class TesseratiPage extends JFrame{
    private JTable table1;
    private JButton indietroButton;
    private JTextField textField1;
    private JTextField textField2;
    private JButton aggiungiButton;
    private JButton cancellaButton;
    private JScrollPane Tesserati;
    private JPanel tesseratiHome;

    public TesseratiPage(Integer xCord, Integer ycord){
        Utils.setPositionAndDimensions(xCord,ycord,TesseratiPage.this,tesseratiHome);
        Utils.uploadTableTesserati("tesserati",table1);
        indietroButton.addActionListener(e -> Utils.navigate(TesseratiPage.this,tesseratiHome, Home.class));
        aggiungiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItem();
            }
        });
        cancellaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete();
            }
        });
    }

    public void addItem(){
        TableTesserati tableTesserati = (TableTesserati) this.table1.getModel();
        Long id;
        try{
            id = Long.valueOf(this.textField1.getText());
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Campo Id non valido");
            return;
        }
        String name = this.textField2.getText();
        if(name.isBlank()){
            JOptionPane.showMessageDialog(null,"Campo nome non valido");
            return;
        }

        Tesserati tesserato = new Tesserati(name,id);
        List<Tesserati> tesseratiList = tableTesserati.getTesseratiList();
        tesseratiList.add(tesserato);
        TableTesserati newTable = new TableTesserati(tesseratiList);
        table1.setModel(newTable);
        Utils.setTableAlignment(table1);
        Utils.writeListToJson(tesseratiList, "doNotDelete","tesserati.json");
    }

    private void uploadTable(){
        TypeReference<List<Tesserati>> alimentoType = new TypeReference<>() {};
        List<Tesserati> alimenti = new ArrayList<>();
        try {
            alimenti = readJsonToList("doNotDelete/tesserati.json", alimentoType);
            System.out.println(alimenti);
        } catch (IOException e) {
            // Gestisci l'eccezione
            e.printStackTrace();
        }
        TableTesserati tableAlimento = new TableTesserati(alimenti);
        table1.setModel(tableAlimento);
        Utils.setTableAlignment(table1);
    }

    private void delete(){
        if(!Utils.checkIfSelectedIsPresentTesserati(table1)){
            return;
        }
        Object[] options = {"Si","No"};
        int response = JOptionPane.showOptionDialog(null,"Sei sicuro di vole cancellare il tesserato selezionato?","Conferma cancellazzione",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
        switch (response){
            case 0:
                break;
            case 1:
            case 2:
                return;
        }
        List<Tesserati> list = Utils.removeFromTableIfPresentTesserati(table1);
        Utils.writeListToJson(list, "doNotDelete","tesserati.json");
        uploadTable();
    }

}
