package com.dev.backend.daos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.dev.backend.entities.OrderLine;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Shittu on 30/05/2016.
 */

@Repository
@Transactional
public class OrderLineDao {
  // ------------------------
  // PUBLIC METHODS
  // ------------------------

  /**
   * Save the user in the database.
   */
  public void create(final OrderLine orderLine) {
    entityManager.persist(orderLine);
    return;
  }

  /**
   * Delete the user from the database.
   */
  public void delete(final OrderLine orderLine) {
    if (entityManager.contains(orderLine))
      entityManager.remove(orderLine);
    else
      entityManager.remove(entityManager.merge(orderLine));
    return;
  }

  /**
   * Return all the users stored in the database.
   */
  @SuppressWarnings("unchecked")
  public List<OrderLine> getAll() {

    return entityManager.createQuery(String.format("from OrderLine")).getResultList();
  }

  /**
   * Return the user having the passed id.
   */
  public OrderLine getById(final long code) {
    return entityManager.find(OrderLine.class, code);
  }


  /**
   * Return the user having the passed email.
   */
  public List<OrderLine> getBySalesCode(final long salesCode) {
    return (List<OrderLine>) entityManager
        .createQuery(String.format("from OrderLine where sales_code = :salesCode"))
        .setParameter("salesCode", salesCode)
        .getResultList();
  }


  /**
   * Update the passed user in the database.
   */
  public void update(final OrderLine orderLine) {
    entityManager.merge(orderLine);
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
