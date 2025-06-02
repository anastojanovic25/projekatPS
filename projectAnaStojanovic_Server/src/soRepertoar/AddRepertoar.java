/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package soRepertoar;

import dbb.DBBroker;
import java.util.Date;
import model.AbstractDomainObject;
import model.Repertoar;
import systemOperations.OpstaSO;

/**
 *
 * @author Ana
 */
public class AddRepertoar extends OpstaSO{
    int id;
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Repertoar)) {
            throw new Exception("Prosledjeni objekat nije instanca klase");
        }
        
        Repertoar repertoar = (Repertoar) ado;

    
        if (repertoar.getDatum().before(new Date())) {
            throw new Exception("Datum repertoara ne moze biti u proslosti.");
        }
    }
    @Override
    protected void execute(AbstractDomainObject ado, Object o) throws Exception {
       id = DBBroker.getInstance().insert(ado);
    }

    public int getId() {
        return id;
    }
    
}
