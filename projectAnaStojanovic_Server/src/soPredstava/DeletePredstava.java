/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package soPredstava;

import dbb.DBBroker;
import java.sql.SQLException;
import model.AbstractDomainObject;
import model.Predstava;
import systemOperations.OpstaSO;

/**
 *
 * @author Ana
 */
public class DeletePredstava extends OpstaSO{
    boolean result;
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Predstava)) {
            throw new Exception("Prosledjeni objekat nije instanca klase");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado, Object o) throws Exception {
        result = DBBroker.getInstance().delete(ado);
    }
    public boolean isDeleted() throws SQLException {
        return result;
    }
    
}
