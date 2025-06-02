/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package soGlumi;

import dbb.DBBroker;
import model.AbstractDomainObject;
import model.Glumi;
import systemOperations.OpstaSO;

/**
 *
 * @author Ana
 */
public class AddGlumi extends OpstaSO{
    int id;
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Glumi)) {
            throw new Exception("Prosledjeni objekat nije instanca klase");
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
