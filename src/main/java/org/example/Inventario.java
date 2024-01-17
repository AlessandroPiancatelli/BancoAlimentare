package org.example;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;

import static org.example.Utils.readJsonToList;

public class Inventario extends JFrame{
    private JTable table1;
    private JPanel inventario;
    private JButton aggiungiButton;
    private JTextField textField1;
    private JTextField textField2;
    private JButton cancellaButton;
    private JButton indietroButton;

    public Inventario(Integer xCord, Integer ycord) {
        Utils.setPositionAndDimensions(xCord,ycord,Inventario.this,inventario);
        Utils.uploadTableAlimento("alimenti",table1);
        aggiungiButton.addActionListener(e -> addItem());
        cancellaButton.addActionListener(e -> delete());
        indietroButton.addActionListener(e -> Utils.navigate(Inventario.this,inventario, Home.class));
    }

    public void addItem(){
       TableAlimento tableAlimento = (TableAlimento) this.table1.getModel();
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

       Alimento alimento = new Alimento(id,name);
       List<Alimento> alimentoList = tableAlimento.getAlimentoList();
       alimentoList.add(alimento);
       TableAlimento newTable = new TableAlimento(alimentoList);
       table1.setModel(newTable);
       Utils.setTableAlignment(table1);
       Utils.writeListToJson(alimentoList, "doNotDelete","alimenti.json");
    }

    private void uploadTable(){
        TypeReference<List<Alimento>> alimentoType = new TypeReference<>() {};
        List<Alimento> alimenti = new ArrayList<>();
        try {
            alimenti = readJsonToList("alimenti.json", alimentoType);
            System.out.println(alimenti);
        } catch (IOException e) {
            // Gestisci l'eccezione
            e.printStackTrace();
        }
        TableAlimento tableAlimento = new TableAlimento(alimenti);
        table1.setModel(tableAlimento);
        Utils.setTableAlignment(table1);
    }

    private void delete(){
        if(!Utils.checkIfSelectedIsPresent(table1)){
            return;
        }
        Object[] options = {"Si","No"};
        int response = JOptionPane.showOptionDialog(null,"Sei sicuro di vole cancellare l'alimento selezionato?","Conferma cancellazzione",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
        switch (response){
            case 0:
                break;
            case 1:
            case 2:
                return;
        }
        List<Alimento> list = Utils.removeFromTableIfPresent(table1);
        Utils.writeListToJson(list, "doNotDelete","alimenti.json");
        uploadTable();
    }
}
