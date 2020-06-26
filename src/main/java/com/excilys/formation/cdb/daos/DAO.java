package com.excilys.formation.cdb.daos;

import java.sql.Connection;
import java.util.List;

public abstract class DAO<T> {
  protected Connection connect = null;
   
  public DAO(Connection conn){
    this.connect = conn;
  }
   
  /**
  * M�thode de cr�ation
  * @param obj
  * @return boolean 
  */
  public abstract boolean create(T obj);

  /**
  * M�thode pour effacer
  * @param obj
  * @return boolean 
  */
  public abstract boolean delete(int id);

  /**
  * M�thode de mise � jour
  * @param obj
  * @return boolean
  */
  public abstract boolean update(T obj);

  /**
  * M�thode de recherche des informations
  * @param id
  * @return T
  */
  public abstract T findById(int id);
  
  /**
   * M�thode de recherche des informations
   * @param id
   * @return T
   */
   public abstract T findByName(String name);
  
  /**
   * M�thode de recherche des informations
   * @return int
   */
  public abstract int getNumberRows();

  /**
  * M�thode de listing
  * @param id
  * @return T
  */
  public abstract List<T> list();
  
}