package com.aquinoa.ziptechtask.commons;

import java.util.List;

public interface ResponseModel<DAO, MODEL> {

  public MODEL toModel(DAO dao);
  public List<MODEL> toModel(List<DAO> list);
}
