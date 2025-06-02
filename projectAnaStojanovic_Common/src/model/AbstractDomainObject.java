/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ana
 */
public abstract class AbstractDomainObject implements Serializable{
    public abstract String tableName();
    public abstract String alijas();
    public abstract String join();
    public abstract ArrayList<AbstractDomainObject> getList(ResultSet rs, Object o) throws SQLException;
    public abstract String columnsForInsert();
    public abstract String uslov();
    public abstract String valuesForInsert();
    public abstract String valuesForUpdate(Object o);
    public abstract String requirementForSelect(Object o);
    public abstract String searchAttribute();
}
