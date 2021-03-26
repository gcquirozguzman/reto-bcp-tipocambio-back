package com.bcp.tipoCambio.entity;

import org.hibernate.envers.RevisionListener;

public class CustomRevisionEntityListener implements RevisionListener {

  @Override
  public void newRevision(Object revisionEntity) {
	  CustomRevisionEntity customRevisionEntity = (CustomRevisionEntity) revisionEntity;
	  String username = System.getProperty("user.name");
	  customRevisionEntity.setUsername(username);
  }
}