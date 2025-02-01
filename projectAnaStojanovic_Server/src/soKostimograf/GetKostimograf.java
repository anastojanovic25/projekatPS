/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package soKostimograf;

import connection.DBBroker;
import java.util.ArrayList;
import model.AbstractDomainObject;
import model.Kostimograf;
import systemOperations.OpstaSO;

/**
 *
 * @author Ana
 */
public class GetKostimograf extends OpstaSO{

   private ArrayList<Kostimograf> list;
    
    @Override
    protected void validate(AbstractDomainObject odo) throws Exception {
        if (!(odo instanceof Kostimograf)) {
            throw new Exception("Prosledjeni objekat nije instanca klase");
        }
    }
    @Override
    protected void execute(AbstractDomainObject odo, Object o) throws Exception {
        ArrayList<AbstractDomainObject> listGlumac = DBBroker.getInstance().select(odo,o);
        list = (ArrayList<Kostimograf>) (ArrayList<?>) listGlumac;
    }
    
    public ArrayList<Kostimograf> getList(){
        return list;
    }
    
}
