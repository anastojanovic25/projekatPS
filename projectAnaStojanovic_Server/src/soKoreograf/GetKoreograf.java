/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package soKoreograf;

import dbb.DBBroker;
import java.util.ArrayList;
import model.AbstractDomainObject;
import model.Koreograf;
import systemOperations.OpstaSO;

/**
 *
 * @author Ana
 */
public class GetKoreograf extends OpstaSO{

   private ArrayList<Koreograf> list;
    
    @Override
    protected void validate(AbstractDomainObject odo) throws Exception {
        if (!(odo instanceof Koreograf)) {
            throw new Exception("Prosledjeni objekat nije instanca klase");
        }
    }
    @Override
    protected void execute(AbstractDomainObject odo, Object o) throws Exception {
        ArrayList<AbstractDomainObject> listGlumac = DBBroker.getInstance().select(odo,o);
        list = (ArrayList<Koreograf>) (ArrayList<?>) listGlumac;
    }
    
    public ArrayList<Koreograf> getList(){
        return list;
    }
    
}
