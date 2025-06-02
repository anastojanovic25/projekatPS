/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package soGlumac;

import dbb.DBBroker;
import java.util.ArrayList;
import model.AbstractDomainObject;
import model.Glumac;
import systemOperations.OpstaSO;

/**
 *
 * @author Ana
 */
public class GetGlumac extends OpstaSO{

   private ArrayList<Glumac> list;
    
    @Override
    protected void validate(AbstractDomainObject odo) throws Exception {
        if (!(odo instanceof Glumac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase");
        }
    }
    @Override
    public void execute(AbstractDomainObject odo, Object o) throws Exception {
        ArrayList<AbstractDomainObject> listGlumac = DBBroker.getInstance().select(odo,o);
        list = (ArrayList<Glumac>) (ArrayList<?>) listGlumac;
    }
    
    public ArrayList<Glumac> getList(){
        return list;
    }
}
