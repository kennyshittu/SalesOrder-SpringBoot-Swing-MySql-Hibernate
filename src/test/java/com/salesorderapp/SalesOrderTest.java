package com.salesorderapp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;

import com.salesorderapp.backend.daos.SalesOrderDao;
import com.salesorderapp.backend.entities.SalesOrderEntity;
import com.salesorderapp.backend.models.SalesOrder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Shittu on 01/06/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Transactional
public class SalesOrderTest {
  @Autowired
  private SalesOrderDao salesOrderDao;

  private List<SalesOrder> expectedSalesOrder = new ArrayList<>();
  private SalesOrder firstSalesOrder;
  private SalesOrder secondSalesOrder;

  @Before
  public void setup(){
    firstSalesOrder = new SalesOrder(new Date().getTime(), "(11) Kenny", 23D);
    secondSalesOrder = new SalesOrder(new Date().getTime() + 10L, "(15) Johnson", 23D);

    salesOrderDao.truncate("SalesOrder");
    salesOrderDao.create(firstSalesOrder);
    salesOrderDao.create(secondSalesOrder);

    expectedSalesOrder.add(firstSalesOrder);
    expectedSalesOrder.add(secondSalesOrder);
  }

  @After
  public void tearDown(){
    System.out.println("Finished test.");
  }

  @Test
  public void testCreate() {
    SalesOrder salesOrder = new SalesOrder(new Date().getTime() + 10L, "(17) Shittu", 23D);
    salesOrderDao.create(salesOrder);

    SalesOrderEntity actualSalesOrderEntity = salesOrderDao.getById(salesOrder.getCode());
    Assert.assertNotNull("Failure: get all salesOrder is null.", actualSalesOrderEntity);
    Assert.assertEquals("Failure: expected salesOrder not equal actual.", salesOrder.getCode(), actualSalesOrderEntity.getOrderNumber());
  }

  @Test
  public void testUpdate() {
    firstSalesOrder.setTotal(1000D);
    salesOrderDao.update(firstSalesOrder);
    SalesOrderEntity actualSalesOrderEntity = salesOrderDao.getById(firstSalesOrder.getCode());

    Assert.assertNotNull("Failure: get all salesOrder is null.", actualSalesOrderEntity);
    Assert.assertEquals("Failure: update not persisted.", firstSalesOrder.getTotalPrice(), actualSalesOrderEntity.getTotalPrice());
  }

  @Test
  public void testGetAll() {
    List<SalesOrder> salesOrders = salesOrderDao.getAll();
    Assert.assertNotNull("Failure: get all salesOrder is null.", salesOrders);
    Assert.assertEquals("Failure: expected salesOrder not equal actual.", expectedSalesOrder, salesOrders);
  }

  @Test
  public void testById() {
    SalesOrderEntity salesOrderEntity = salesOrderDao.getById(firstSalesOrder.getCode());
    Assert.assertNotNull("Failure: get all salesOrder is null.", salesOrderEntity);
    Assert.assertEquals("Failure: expected salesOrder not equal actual.", firstSalesOrder.getCode(), salesOrderEntity.getOrderNumber());
  }

  @Test
  public void testDelete() {
    salesOrderDao.delete(firstSalesOrder);
    SalesOrderEntity salesOrderEntity = salesOrderDao.getById(firstSalesOrder.getCode());
    Assert.assertNull("Failure: salesOrder was not deleted", salesOrderEntity);
  }
}
