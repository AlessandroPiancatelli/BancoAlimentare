package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.tuple.Pair;

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

    public static <T> List<T> readJsonToList(String filePath, TypeReference<List<T>> typeReference) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Deserializza il file JSON nella lista di oggetti
            List<T> myList = objectMapper.readValue(new File(filePath), typeReference);

            return myList;
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Errore durante la lettura del file JSON: " + e.getMessage());
            throw e; // Rilancia l'eccezione per gestirla in un livello superiore, se necessario
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
        TypeReference<List<Alimento>> alimentoType = new TypeReference<>() {};

        TableAlimento tableAlimento;
        try {
            tableAlimento = new TableAlimento(Utils.readJsonToList("doNotDelete/"+fileName+".json",alimentoType));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        table.setModel(tableAlimento);
        Utils.setTableAlignment(table);
    }

    public static void uploadTableTesserati(String fileName , JTable table){
        TypeReference<List<Tesserati>> alimentoType = new TypeReference<>() {};

        TableTesserati tableTesserati;
        try {
            tableTesserati = new TableTesserati(Utils.readJsonToList("doNotDelete/"+fileName+".json",alimentoType));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        table.setModel(tableTesserati);
        Utils.setTableAlignment(table);
    }


}
