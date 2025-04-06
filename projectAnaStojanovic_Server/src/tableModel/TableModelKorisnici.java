/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableModel;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Korisnik;

/**
 *
 * @author Ana
 */
public class TableModelKorisnici extends AbstractTableModel{
    String[] kolone={"Email","Broj telefona"};
    List<Korisnik> lista;
    public TableModelKorisnici(List<Korisnik> lista){
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
        Korisnik k=lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return k.getEmail();
            case 1:
                return k.getBroj();
            default:
                return "N/A";
        }
    }
    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void setKorisnici(List<Korisnik> lista) {
         this.lista = lista;
    fireTableDataChanged();
    }
}
