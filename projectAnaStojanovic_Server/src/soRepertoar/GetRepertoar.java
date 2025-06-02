/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package soRepertoar;

import dbb.DBBroker;
import java.util.ArrayList;
import model.AbstractDomainObject;
import model.Repertoar;
import systemOperations.OpstaSO;

/**
 *
 * @author Ana
 */
public class GetRepertoar extends OpstaSO{

   private ArrayList<Repertoar> list;
    
    @Override
    protected void validate(AbstractDomainObject odo) throws Exception {
        if (!(odo instanceof Repertoar)) {
            throw new Exception("Prosledjeni objekat nije instanca klase");
        }
    }
    @Override
    protected void execute(AbstractDomainObject odo, Object o) throws Exception {
        ArrayList<AbstractDomainObject> listGlumac = DBBroker.getInstance().select(odo,o);
        list = (ArrayList<Repertoar>) (ArrayList<?>) listGlumac;
    }
    
    public ArrayList<Repertoar> getList(){
        return list;
    }
    
}
