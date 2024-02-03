package org.example.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.tuple.Pair;
import org.example.entita.Alimento;
import org.example.entita.Tesserati;
import org.example.tables.TableAlimento;
import org.example.tables.TableTesserati;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.List;

public class Utils  {

    public static void setTableAlignment( JTable table1){
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment( JLabel.LEFT );
        table1.getColumnModel().getColumn(0).setCellRenderer(leftRenderer );

        JTableHeader header = table1.getTableHeader();

        if (header != null) {
            header.setResizingAllowed(true); // Opzionale: abilita il ridimensionamento delle colonne
            header.setReorderingAllowed(true); // Opzionale: abilita il riordinamento delle colonne
            header.setVisible(true);
        }
    }

    public static void writeListToJson(Object list, String folderPath, String fileName) {
        // Creazione dell'oggetto ObjectMapper per la serializzazione JSON
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Creazione della cartella se non esiste
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Creazione del percorso completo del file
        String filePath = folderPath + File.separator + fileName;

        try {
            // Serializzazione della lista e scrittura su file JSON
            objectMapper.writeValue(new File(filePath), list);
            System.out.println("Lista scritta con successo nel file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Errore durante la scrittura del file JSON: " + e.getMessage());
        }
    }

    public static <T> List<T> readJsonToListAdvanced(String filePath, Class<T> generic) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(filePath), objectMapper.getTypeFactory().constructCollectionType(List.class,generic));
        } catch (IOException e) {
            System.err.println("Errore durante la lettura del file JSON: " + e.getMessage());
            return null;
        }
    }
    public static List<Alimento> removeFromTableIfPresent(JTable table){
        TableAlimento model = (TableAlimento) table.getModel();
        List<Alimento> list = model.getAlimentoList();
        int index = table.getSelectedRow();
        if(checkIfSelectedIsPresent(table)){
            list.remove(index);
            return list;
        }else{
            return list;
        }

    }

    public static List<Tesserati> removeFromTableIfPresentTesserati(JTable table){
        TableTesserati model = (TableTesserati) table.getModel();
        List<Tesserati> list = model.getTesseratiList();
        int index = table.getSelectedRow();
        if(checkIfSelectedIsPresentTesserati(table)){
            list.remove(index);
            return list;
        }else{
            return list;
        }

    }

    public static boolean checkIfSelectedIsPresent(JTable table){
        TableAlimento model = (TableAlimento) table.getModel();
        List<Alimento> list = model.getAlimentoList();
        int index = table.getSelectedRow();
        if(index == -1 || index > list.size() ){
            JOptionPane.showMessageDialog(null,"Nessun alimento selezionato","Errore",JOptionPane.ERROR_MESSAGE);
            return false;
        }else{
            return true;
        }
    }

    public static boolean checkIfSelectedIsPresentTesserati(JTable table){
        TableTesserati model = (TableTesserati) table.getModel();
        List<Tesserati> list = model.getTesseratiList();
        int index = table.getSelectedRow();
        if(index == -1 || index > list.size() ){
            JOptionPane.showMessageDialog(null,"Nessun tessarato selezionato","Errore",JOptionPane.ERROR_MESSAGE);
            return false;
        }else{
            return true;
        }
    }


    public static void navigate(JFrame frame, JPanel actualPanel, Class<?> destinationClass){
        Pair<Integer,Integer> cord = Pair.of(frame.getX(),frame.getY());
        actualPanel.setVisible(false);
        frame.dispose();
        tryToCreateFrame(destinationClass,cord).setVisible(true);
    }

    private static JFrame tryToCreateFrame(Class<?> destinationClass, Pair<Integer,Integer> cord){
        try {
            // Usa la riflessione per ottenere il costruttore della classe di destinazione
            Constructor<?> constructor = destinationClass.getConstructor(Integer.class, Integer.class);

            // Crea un'istanza della classe di destinazione
            Object destinationInstance = constructor.newInstance(cord.getLeft(), cord.getRight());

            // Visualizza l'istanza
            if (destinationInstance instanceof JFrame) {
                JFrame destinationFrame = (JFrame) destinationInstance;
                destinationFrame.setVisible(true);
                return destinationFrame;
            } else {
                // Logica gestione errore: La classe di destinazione non è un JFrame
                throw new RuntimeException("C'è stato un tentativo di creazione di una classe che non rappresenta un Jframe");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setPositionAndDimensions(Integer xCord, Integer ycord, JFrame frame, JPanel mainFramePanel){
        if(xCord != null && ycord != null){
            frame.setBounds(xCord,ycord,600,600);
        }else{
            frame.setBounds(300,300,600,600);
        }
        frame.setContentPane(mainFramePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void uploadTableAlimento(String fileName , JTable table){
        TableAlimento tableAlimento = new TableAlimento(Utils.readJsonToListAdvanced("doNotDelete/"+fileName+".json", Alimento.class));
        table.setModel(tableAlimento);
        Utils.setTableAlignment(table);
    }

    public static void uploadTableTesserati(String fileName , JTable table){
        TableTesserati   tableTesserati = new TableTesserati(Utils.readJsonToListAdvanced("doNotDelete/"+fileName+".json",Tesserati.class));
        table.setModel(tableTesserati);
        Utils.setTableAlignment(table);
    }


}
