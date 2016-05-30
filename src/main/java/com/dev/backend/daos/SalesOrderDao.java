package com.dev.backend.daos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.dev.backend.entities.SalesOrder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by Shittu on 29/05/2016.
 */

@Repository
@Transactional
public class SalesOrderDao {
  // ------------------------
  // PUBLIC METHODS
  // ------------------------

  /**
   * Save the user in the database.
   */
  public void create(final SalesOrder salesOrder) {
    entityManager.persist(salesOrder);
    return;
  }

  /**
   * Delete the user from the database.
   */
  public void delete(final SalesOrder salesOrder) {
    if (entityManager.contains(salesOrder))
      entityManager.remove(salesOrder);
    else
      entityManager.remove(entityManager.merge(salesOrder));
    return;
  }

  /**
   * Return all the users stored in the database.
   */
  @SuppressWarnings("unchecked")
  public List<SalesOrder> getAll() {

    return entityManager.createQuery(String.format("from SalesOrder")).getResultList();
  }

  /**
   * Return the user having the passed id.
   */
  public SalesOrder getById(final long salesCode) {
    return entityManager.find(SalesOrder.class, salesCode);
  }

  /**
   * Update the passed user in the database.
   */
  public void update(final SalesOrder salesOrder) {
    entityManager.merge(salesOrder);
    return;
  }

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------

  // An EntityManager will be automatically injected from entityManagerFactory
  // setup on DatabaseConfig class.
  @PersistenceContext
  private EntityManager entityManager;
}