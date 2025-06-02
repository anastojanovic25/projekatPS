/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package soKorisnik;

import dbb.DBBroker;
import model.AbstractDomainObject;
import model.Korisnik;
import systemOperations.OpstaSO;

/**
 *
 * @author Ana
 */
public class AddKorisnik extends OpstaSO{
    int id;
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Korisnik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase");
        }
    }
    @Override
    public void execute(AbstractDomainObject ado, Object o) throws Exception {
       id = DBBroker.getInstance().insert(ado);
    }

    public int getId() {
        return id;
    }
    
}
