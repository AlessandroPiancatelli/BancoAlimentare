package org.example.schermate;

import org.example.entita.Alimento;
import org.example.tables.TableAlimento;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventarioPageTest {

    @Test
    void constructor() {
        InventarioPage yourClass = new InventarioPage(null,null);
        assertNotNull(yourClass);
    }

    @Test
    void addItem() {

        // Creare un'istanza della tua classe o il metodo addItem è presente
        InventarioPage yourClass = new InventarioPage(null,null);
        // Inizializzare la tua GUI o l'oggetto table1
        // In questo esempio, stiamo simulando l'input dell'utente

        // Imposta l'id, il nome e la quantità
        yourClass.textField1.setText("1"); // id
        yourClass.textField2.setText("Apple"); // nome
        yourClass.textField3.setText("10"); // quantità

        // Esegui il metodo addItem
        yourClass.addItem();

        // Ottenere la lista di Alimenti dalla tua tabella
        TableAlimento tableAlimento = (TableAlimento) yourClass.table1.getModel();
        List<Alimento> alimentoList = tableAlimento.getAlimentoList();

        // Assicurati che l'elemento aggiunto sia presente nella lista
        boolean found = false;
        for (Alimento alimento : alimentoList) {
            if (alimento.getId().equals("1") &&
                    alimento.getNome().equals("Apple") &&
                    alimento.getQuantita().equals(10L)) {
                found = true;
                break;
            }
        }

        assertTrue(found, "L'alimento aggiunto non è presente nella lista.");
    }

}