package org.example;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.List;
import java.util.Objects;

public class TableAlimento implements TableModel {
    private List<Alimento> alimentoList;

    public TableAlimento(List<Alimento> alimentoList) {
        this.alimentoList = alimentoList;
    }

    public List<Alimento> getAlimentoList() {
        return alimentoList;
    }

    public void setAlimentoList(List<Alimento> alimentoList) {
        this.alimentoList = alimentoList;
    }

    @Override
    public int getRowCount() {
        return this.alimentoList.size();
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
       Alimento alimento = this.alimentoList.get(rowIndex);
       switch (columnIndex){
           case 0:
               return alimento.getId();
           case 1:
               return alimento.getNome();
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
        return "TableAlimento{" +
                "alimentoList=" + alimentoList +
                '}';
    }
}
