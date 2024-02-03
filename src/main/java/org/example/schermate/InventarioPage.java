package org.example.schermate;

import javax.swing.*;
import java.util.List;

import org.example.entita.Alimento;
import org.example.tables.TableAlimento;
import org.example.utils.Utils;

import static org.example.utils.Utils.readJsonToListAdvanced;

public class InventarioPage extends JFrame{
    private JTable table1;
    private JPanel inventario;
    private JButton aggiungiButton;
    private JTextField textField1;
    private JTextField textField2;
    private JButton cancellaButton;
    private JButton indietroButton;

    public InventarioPage(Integer xCord, Integer ycord) {
        Utils.setPositionAndDimensions(xCord,ycord, InventarioPage.this,inventario);
        Utils.uploadTableAlimento("alimenti",table1);
        aggiungiButton.addActionListener(e -> addItem());
        cancellaButton.addActionListener(e -> delete());
        indietroButton.addActionListener(e -> Utils.navigate(InventarioPage.this,inventario, Home.class));
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
        List<Alimento> alimenti = readJsonToListAdvanced("alimenti.json", Alimento.class);
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
