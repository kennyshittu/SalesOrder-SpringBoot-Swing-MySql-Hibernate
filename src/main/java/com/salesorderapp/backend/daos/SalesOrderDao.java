package com.salesorderapp.backend.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.salesorderapp.backend.entities.Customer;
import com.salesorderapp.backend.entities.OrderLine;
import com.salesorderapp.backend.entities.SalesOrder;
import com.salesorderapp.backend.models.SalesOrderEntity;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
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
  public SalesOrderEntity getById(final long salesCode) {

    SalesOrder salesOrder = entityManager.find(SalesOrder.class, salesCode);
    Customer customer = mCustomerDao.getById(Long.parseLong(salesOrder.getCustomer().substring(
        1,
        salesOrder.getCustomer().indexOf(")")
    )));

    List<OrderLine> orderLines = mOrderLineDao.getBySalesCode(salesCode);
    List<Map<String, Object>> products = new ArrayList<>();
    Map<String, Object> customerObject = Maps.newHashMap();

    customerObject.put("id", customer.getCode());
    customerObject.put("name", customer.getName());

    for(OrderLine orderLine : orderLines){
      Map<String, Object> product = Maps.newHashMap();
      product.put("id", orderLine.getProductCode());
      product.put("price", orderLine.getProductPrice());
      product.put("quantity", orderLine.getQuantity());

      products.add(product);
    }

    return new SalesOrderEntity(salesOrder.getCode(), customerObject, products, salesOrder.getTotalPrice());
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

  // Private fields
  @Autowired
  private OrderLineDao mOrderLineDao;

  @Autowired
  private CustomerDao mCustomerDao;
}