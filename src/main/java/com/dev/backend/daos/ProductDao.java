package com.dev.backend.daos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.dev.backend.entities.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by Shittu on 29/05/2016.
 */

@Repository
@Transactional
public class ProductDao {
  // ------------------------
  // PUBLIC METHODS
  // ------------------------

  /**
   * Save the user in the database.
   */
  public void create(final Product product) {
    entityManager.persist(product);
    return;
  }

  /**
   * Delete the user from the database.
   */
  public void delete(final Product product) {
    if (entityManager.contains(product))
      entityManager.remove(product);
    else
      entityManager.remove(entityManager.merge(product));
    return;
  }

  /**
   * Return all the users stored in the database.
   */
  @SuppressWarnings("unchecked")
  public List<Product> getAll() {

    return entityManager.createQuery(String.format("from Product")).getResultList();
  }

  /**
   * Return the user having the passed id.
   */
  public Product getById(final long code) {
    return entityManager.find(Product.class, code);
  }

  /**
   * Update the passed user in the database.
   */
  public void update(final Product product) {
    entityManager.merge(product);
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