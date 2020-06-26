package com.excilys.formation.cdb.daos;

import java.sql.Connection;
import java.util.List;

public abstract class DAO<T> {
  protected Connection connect = null;
   
  public DAO(Connection conn){
    this.connect = conn;
  }
   
  /**
  * Méthode de création
  * @param obj
  * @return boolean 
  */
  public abstract boolean create(T obj);

  /**
  * Méthode pour effacer
  * @param obj
  * @return boolean 
  */
  public abstract boolean delete(int id);

  /**
  * Méthode de mise à jour
  * @param obj
  * @return boolean
  */
  public abstract boolean update(T obj);

  /**
  * Méthode de recherche des informations
  * @param id
  * @return T
  */
  public abstract T findById(int id);
  
  /**
   * Méthode de recherche des informations
   * @param id
   * @return T
   */
   public abstract T findByName(String name);
  
  /**
   * Méthode de recherche des informations
   * @return int
   */
  public abstract int getNumberRows();

  /**
  * Méthode de listing
  * @param id
  * @return T
  */
  public abstract List<T> list();
  
}