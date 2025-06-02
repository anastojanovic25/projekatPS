/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableModel;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Glumi;

/**
 *
 * @author Ana
 */
public class TableModelGlumi extends AbstractTableModel {
    List<Glumi> lista;
    String[] kolone={"Ime i prezime","Uloga"};
    public TableModelGlumi(List<Glumi> lista){
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
        Glumi g=lista.get(rowIndex);
        switch(columnIndex){
            case 0:
                return g.getG().getIme()+" "+g.getG().getPrezime();
            case 1:
                return g.getUloga().getNaziv();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Glumi> getLista() {
        return lista;
    }
    
    
}
