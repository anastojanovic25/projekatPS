/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Predstava;

/**
 *
 * @author Ana
 */
public class TableModelRepertoar extends AbstractTableModel {
    List<Predstava> lista;
    String[] kolone={"Naziv","Duzina trajanja(minuti)","Datum","Zanr","Korisnik"};
    public TableModelRepertoar(List<Predstava> lista){
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
        Predstava p=lista.get(rowIndex);
        switch(columnIndex){
            case 0:
                return p.getNaziv();
            case 1:
                return p.getTrajanje();
            case 2:
                return p.getDatumOdrzavanja();
            case 3:
                return p.getZanr();
            case 4:
                return p.getKorisnikUnos();
            default:
                return "N/A";
        }
    }
    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Predstava> getLista() {
        return lista;
    }
    
    
}
