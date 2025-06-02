/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableModel;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.GlumacUloga;

/**
 *
 * @author Ana
 */
public class TableModelGlumacUloga extends AbstractTableModel {
    String[] kolone={"Uloga", "Glumac"};
    List<GlumacUloga> lista;
    public TableModelGlumacUloga(List<GlumacUloga> lista){
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
        GlumacUloga gu=lista.get(rowIndex);
        switch(columnIndex){
            case 0:
                return gu.getUloga();
            case 1:
                return gu.getGlumac().getIme()+" "+gu.getGlumac().getPrezime();
            default:
                    return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<GlumacUloga> getLista() {
        return lista;
    }
    
    
}
