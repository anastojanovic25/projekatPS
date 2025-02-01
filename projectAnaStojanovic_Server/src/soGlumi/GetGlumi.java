/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package soGlumi;

import connection.DBBroker;
import java.util.ArrayList;
import model.AbstractDomainObject;
import model.Glumi;
import systemOperations.OpstaSO;

/**
 *
 * @author Ana
 */
public class GetGlumi extends OpstaSO{

   private ArrayList<Glumi> list;
    
    @Override
    protected void validate(AbstractDomainObject odo) throws Exception {
        if (!(odo instanceof Glumi)) {
            throw new Exception("Prosledjeni objekat nije instanca klase");
        }
    }
    @Override
    protected void execute(AbstractDomainObject odo, Object o) throws Exception {
        ArrayList<AbstractDomainObject> listGlumac = DBBroker.getInstance().select(odo,o);
        list = (ArrayList<Glumi>) (ArrayList<?>) listGlumac;
    }
    
    public ArrayList<Glumi> getList(){
        return list;
    }
    
}
