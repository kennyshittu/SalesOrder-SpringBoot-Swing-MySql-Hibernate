package com.dev.backend.daos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.dev.backend.entities.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by Shittu on 29/05/2016.
 */

@Repository
@Transactional
public class CustomerDao {
  // ------------------------
  // PUBLIC METHODS
  // ------------------------

  /**
   * Save the user in the database.
   */
  public void create(final Customer customer) {
    entityManager.persist(customer);
    return;
  }

  /**
   * Delete the user from the database.
   */
  public void delete(final Customer customer) {
    if (entityManager.contains(customer))
      entityManager.remove(customer);
    else
      entityManager.remove(entityManager.merge(customer));
    return;
  }

  /**
   * Return all the users stored in the database.
   */
  @SuppressWarnings("unchecked")
  public List<Customer> getAll() {

    return entityManager.createQuery(String.format("from Customer")).getResultList();
  }

  /**
   * Return the user having the passed email.
   */
//  public Customer getByEmail(String email) {
//    return (Customer) entityManager.createQuery(
//        "from User where email = :email")
//        .setParameter("email", email)
//        .getSingleResult();
//  }

  /**
   * Return the user having the passed id.
   */
  public Customer getById(final long code) {
    return entityManager.find(Customer.class, code);
  }

  /**
   * Update the passed user in the database.
   */
  public void update(final Customer customer) {
    entityManager.merge(customer);
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
