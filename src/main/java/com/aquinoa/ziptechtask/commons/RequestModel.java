package com.aquinoa.ziptechtask.commons;

public interface RequestModel<DAO> {

  public DAO toDb();
}
