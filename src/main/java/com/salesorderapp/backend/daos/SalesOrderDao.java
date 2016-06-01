package com.salesorderapp.backend.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.salesorderapp.backend.models.Customer;
import com.salesorderapp.backend.models.OrderLine;
import com.salesorderapp.backend.models.SalesOrder;
import com.salesorderapp.backend.entities.SalesOrderEntity;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by Shittu on 29/05/2016.
 */

@Repository
@Transactional
public class SalesOrderDao extends BaseDao<SalesOrder>{

  private static final String ENTITY = "SalesOrder";

  public List<SalesOrder> getAll(){
    return super.getAll(ENTITY);
  }

  /**
   * Return the Object having the passed id.
   */
  public SalesOrderEntity getById(final long salesCode) {

    SalesOrder salesOrder = entityManager.find(SalesOrder.class, salesCode);
    List<OrderLine> orderLines = mOrderLineDao.getBySalesCode(salesCode);
    List<Map<String, Object>> products = new ArrayList<>();
    Map<String, Object> customerObject = Maps.newHashMap();

    if(salesOrder != null && salesOrder.getCustomer() != null) {
      Customer customer = mCustomerDao.getById(Long.parseLong(salesOrder.getCustomer().substring(
          1,
          salesOrder.getCustomer().indexOf(")")
      )));

      if(customer != null) {
        customerObject.put("id", customer.getCode());
        customerObject.put("name", customer.getName());
      }
    } else {
      // this order has been deleted
      return null;
    }

    for(OrderLine orderLine : orderLines){
      Map<String, Object> product = Maps.newHashMap();
      product.put("id", orderLine.getProductCode());
      product.put("price", orderLine.getProductPrice());
      product.put("quantity", orderLine.getQuantity());

      products.add(product);
    }

    return new SalesOrderEntity(salesOrder.getCode(), customerObject, products, salesOrder.getTotalPrice());
  }

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