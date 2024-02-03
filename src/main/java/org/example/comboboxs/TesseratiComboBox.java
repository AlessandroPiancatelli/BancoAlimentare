package org.example.comboboxs;

import org.example.entita.Tesserati;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.List;

public class TesseratiComboBox implements ComboBoxModel<Tesserati> {
    private List<Tesserati> tesserati;
    private Tesserati selectedItem;

    public TesseratiComboBox(List<Tesserati> tesserati) {
        this.tesserati = tesserati;
    }

    public List<Tesserati> getTesserati() {
        return tesserati;
    }

    public void setTesserati(List<Tesserati> tesserati) {
        this.tesserati = tesserati;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        if (anItem instanceof Tesserati) {
            selectedItem = (Tesserati) anItem;
        }
    }

    @Override
    public Tesserati getSelectedItem() {
        return selectedItem;
    }

    @Override
    public int getSize() {
        return tesserati.size();
    }

    @Override
    public Tesserati getElementAt(int index) {
        if (index >= 0 && index < tesserati.size()) {
            return tesserati.get(index);
        }
        return null;
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        // Implementazione personalizzata se necessario
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        // Implementazione personalizzata se necessario
    }
}
