package com.aquinoa.ziptechtask.commons;

import java.util.List;

/**
 * Convenience interface to transform a list of daos or a dao to an object model exposed through the
 * API.<br/>
 * This is a form of encapsulation to prevent exposing how DB schemas are defined.
 * 
 * @author allana
 *
 * @param <DAO>
 * @param <MODEL>
 */
public interface ResponseModel<DAO, MODEL> {

  public MODEL toModel(DAO dao);

  public List<MODEL> toModel(List<DAO> list);
}
