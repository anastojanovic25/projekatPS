/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package soScenograf;

import dbb.DBBroker;
import java.util.ArrayList;
import model.AbstractDomainObject;
import model.Scenograf;
import systemOperations.OpstaSO;

/**
 *
 * @author Ana
 */
public class GetScenograf extends OpstaSO{

   private ArrayList<Scenograf> list;
    
    @Override
    protected void validate(AbstractDomainObject odo) throws Exception {
        if (!(odo instanceof Scenograf)) {
            throw new Exception("Prosledjeni objekat nije instanca klase");
        }
    }
    @Override
    protected void execute(AbstractDomainObject odo, Object o) throws Exception {
        ArrayList<AbstractDomainObject> listGlumac = DBBroker.getInstance().select(odo,o);
        list = (ArrayList<Scenograf>) (ArrayList<?>) listGlumac;
    }
    
    public ArrayList<Scenograf> getList(){
        return list;
    }
    
}
