/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package soUloga;

import dbb.DBBroker;
import model.AbstractDomainObject;
import model.Uloga;
import systemOperations.OpstaSO;

/**
 *
 * @author Ana
 */
public class AddUloga extends OpstaSO{
    int id;
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Uloga)) {
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
