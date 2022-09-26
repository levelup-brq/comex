package br.com.alura.comex.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFabrica {

  private static final EntityManagerFactory FACTORY = Persistence
    .createEntityManagerFactory("loja");


  public static EntityManager getEntityManager() {
    return FACTORY.createEntityManager();
  }
  
}
