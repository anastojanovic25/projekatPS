/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package soGlumac;

import connection.DBBroker;
import model.AbstractDomainObject;
import model.Glumac;
import systemOperations.OpstaSO;

/**
 *
 * @author Ana
 */
public class AddGlumac extends OpstaSO{
    int id;
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Glumac)) {
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
