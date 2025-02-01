/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package soKorisnik;

import connection.DBBroker;
import java.util.ArrayList;
import model.AbstractDomainObject;
import model.Korisnik;
import systemOperations.OpstaSO;

/**
 *
 * @author Ana
 */
public class GetKorisnik extends OpstaSO{

   private ArrayList<Korisnik> list;
    
    @Override
    protected void validate(AbstractDomainObject odo) throws Exception {
        if (!(odo instanceof Korisnik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase");
        }
    }
    @Override
    protected void execute(AbstractDomainObject odo, Object o) throws Exception {
        ArrayList<AbstractDomainObject> listGlumac = DBBroker.getInstance().select(odo,o);
        list = (ArrayList<Korisnik>) (ArrayList<?>) listGlumac;
    }
    
    public ArrayList<Korisnik> getList(){
        return list;
    }
    
}
