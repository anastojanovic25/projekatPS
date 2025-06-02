/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package soPredstava;

import dbb.DBBroker;
import java.util.ArrayList;
import model.AbstractDomainObject;
import model.Predstava;
import systemOperations.OpstaSO;

/**
 *
 * @author Ana
 */
public class GetPredstava extends OpstaSO{

   private ArrayList<Predstava> list;
    
    @Override
    protected void validate(AbstractDomainObject odo) throws Exception {
        if (!(odo instanceof Predstava)) {
            throw new Exception("Prosledjeni objekat nije instanca klase");
        }
    }
    @Override
    protected void execute(AbstractDomainObject odo, Object o) throws Exception {
        ArrayList<AbstractDomainObject> listGlumac = DBBroker.getInstance().select(odo,o);
        list = (ArrayList<Predstava>) (ArrayList<?>) listGlumac;
    }
    
    public ArrayList<Predstava> getList(){
        return list;
    }
    
}
