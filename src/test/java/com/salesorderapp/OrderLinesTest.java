package com.salesorderapp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;

import com.salesorderapp.backend.daos.OrderLineDao;
import com.salesorderapp.backend.models.OrderLine;
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
public class OrderLinesTest {
  @Autowired
  private OrderLineDao orderLineDao;

  private List<OrderLine> expectedOrderLines = new ArrayList<>();
  private OrderLine firstOrderLine;
  private OrderLine secondOrderLine;

  @Before
  public void setup(){
    firstOrderLine = new OrderLine(new Date().getTime(), new Date().getTime() + 2L, 23D, 12D);
    secondOrderLine = new OrderLine(new Date().getTime() + 10L, new Date().getTime() + 20L, 23D, 12D);

    orderLineDao.truncate("OrderLine");
    orderLineDao.create(firstOrderLine);
    orderLineDao.create(secondOrderLine);

    expectedOrderLines.add(firstOrderLine);
    expectedOrderLines.add(secondOrderLine);
  }

  @Test
  public void testCreate() {
    OrderLine orderLine = new OrderLine(new Date().getTime(), new Date().getTime() + 2L, 23D, 12D);
    orderLineDao.create(orderLine);

    OrderLine actualOrderLine = orderLineDao.getById(orderLine.getCode());
    Assert.assertNotNull("Failure: get all orderLine is null.", actualOrderLine);
    Assert.assertEquals("Failure: expected orderLine not equal actual.", orderLine, actualOrderLine);
  }

  @Test
  public void testUpdate() {
    firstOrderLine.setQuantity(25D);
    orderLineDao.update(firstOrderLine);
    OrderLine actualOrderLine = orderLineDao.getById(firstOrderLine.getCode());

    Assert.assertNotNull("Failure: get all orderLine is null.", actualOrderLine);
    Assert.assertEquals("Failure: update not persisted.", firstOrderLine.getQuantity(), actualOrderLine.getQuantity());
    Assert.assertEquals("Failure: expected orderLine not equal actual.", firstOrderLine, actualOrderLine);
  }

  @Test
  public void testGetAll() {
    List<OrderLine> orderLine = orderLineDao.getAll();
    Assert.assertNotNull("Failure: get all orderLine is null.", orderLine);
    Assert.assertEquals("Failure: expected orderLine not equal actual.", expectedOrderLines, orderLine);
  }

  @Test
  public void testById() {
    OrderLine orderLine = orderLineDao.getById(firstOrderLine.getCode());
    Assert.assertNotNull("Failure: get all orderLine is null.", orderLine);
    Assert.assertEquals("Failure: expected  orderLine not equal actual.", firstOrderLine, orderLine);
  }

  @Test
  public void testBySalesCode() {
    List<OrderLine> orderLines = orderLineDao.getBySalesCode(firstOrderLine.getSalesCode());
    Assert.assertNotNull("Failure: get all orderLine is null.", orderLines);
    Assert.assertTrue("Failure: unable to get orderLine by sales code", orderLines.contains(firstOrderLine));
  }

  @Test
  public void testDelete() {
    orderLineDao.delete(firstOrderLine);
    OrderLine orderLine = orderLineDao.getById(firstOrderLine.getCode());
    Assert.assertNull("Failure: orderLine was not deleted", orderLine);
  }
}
