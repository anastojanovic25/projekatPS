/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package systemOperations;

import connection.DBBroker;
import java.sql.SQLException;
import model.AbstractDomainObject;

/**
 *
 * @author Ana
 */
public abstract class OpstaSO {
   protected abstract void validate(AbstractDomainObject odo) throws Exception;
   protected abstract void execute(AbstractDomainObject odo, Object o) throws Exception;
    
    public void templateExecute(AbstractDomainObject odo, Object o) throws Exception{
        try{
            validate(odo);
            execute(odo, o);
            commit();
        }catch(Exception e){
            rollback();
            throw e;
        }
    }
    
    public void commit() throws Exception{
        
        DBBroker.getInstance().getConnection().commit();
    }
    
    public void rollback() throws Exception{
        DBBroker.getInstance().getConnection().rollback();
    }
}
