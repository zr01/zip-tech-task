package com.aquinoa.ziptechtask.commons;

/**
 * Convenience interface to transform from models to daos.<br/>
 * This is a form of encapsulation to prevent exposing how DB schemas are defined.
 * 
 * @author allana
 *
 * @param <DAO>
 */
public interface RequestModel<DAO> {

  public DAO toDb();
}
