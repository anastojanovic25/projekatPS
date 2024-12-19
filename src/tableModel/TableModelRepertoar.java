/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableModel;

import forms.*;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Repertoar;
import static model.Repertoar.formatDate;

/**
 *
 * @author Ana
 */
public class TableModelRepertoar extends AbstractTableModel {
    List<Repertoar> lista;
    String[] kolone={"datum","vreme","naziv predstave","sala"};
    public TableModelRepertoar(List<Repertoar> lista){
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
        Repertoar r=lista.get(rowIndex);
        switch(columnIndex){
            case 0:
                return formatDate(r.getDatum());
            case 1:
                return r.getVreme();
            case 2:
                return r.getPredstava().getNaziv();
            case 3:
                return r.getSala();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Repertoar> getLista() {
        return lista;
    }
    
    
    
}
