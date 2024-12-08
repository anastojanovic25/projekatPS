/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableModel;

import forms.*;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Glumac;

/**
 *
 * @author Ana
 */
public class TableModelGlumci extends AbstractTableModel{
    List<Glumac> lista;
    String[] kolone={"Ime","Prezime"};
    
    public TableModelGlumci(List<Glumac> lista){
        this.lista=lista;
    } 

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Glumac g=lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
               return g.getIme();
            case 1:
                return g.getPrezime();
            default:
                return "N/A";
        }
    }
    
    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Glumac> getLista() {
        return lista;
    }
    
    
}
