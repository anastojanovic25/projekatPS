/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package soReziser;

import dbb.DBBroker;
import java.util.ArrayList;
import model.AbstractDomainObject;
import model.Reziser;
import systemOperations.OpstaSO;

/**
 *
 * @author Ana
 */
public class GetReziser extends OpstaSO{

   private ArrayList<Reziser> list;
    
    @Override
    protected void validate(AbstractDomainObject odo) throws Exception {
        if (!(odo instanceof Reziser)) {
            throw new Exception("Prosledjeni objekat nije instanca klase");
        }
    }
    @Override
    protected void execute(AbstractDomainObject odo, Object o) throws Exception {
        ArrayList<AbstractDomainObject> listGlumac = DBBroker.getInstance().select(odo,o);
        list = (ArrayList<Reziser>) (ArrayList<?>) listGlumac;
    }
    
    public ArrayList<Reziser> getList(){
        return list;
    }
    
    
}
