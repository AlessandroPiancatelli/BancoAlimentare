package org.example;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.List;
import java.util.Objects;

public class TableTesserati implements TableModel {
    private List<Tesserati> tesseratiList;

    public TableTesserati(List<Tesserati> tesseratiList) {
        this.tesseratiList = tesseratiList;
    }

    public List<Tesserati> getTesseratiList() {
        return tesseratiList;
    }

    public void setTesseratiList(List<Tesserati> tesseratiList) {
        this.tesseratiList = tesseratiList;
    }

    @Override
    public int getRowCount() {
        return this.tesseratiList.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex){
            case 0:
                return "codice";
            case 1:
                return "nome";
            default:
                return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:
                return Long.class;
            case 1:
                return String.class;
            default:
                return Objects.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Tesserati tesserato = this.tesseratiList.get(rowIndex);
        switch (columnIndex){
            case 0:
                return tesserato.getCodice();
            case 1:
                return tesserato.getNome();
            default:
                return "placeholder";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }

    @Override
    public String toString() {
        return "TableTesserati{" +
                "tesseratiList=" + tesseratiList +
                '}';
    }
}
