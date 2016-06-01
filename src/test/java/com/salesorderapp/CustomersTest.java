package com.salesorderapp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;

import com.salesorderapp.backend.daos.CustomerDao;
import com.salesorderapp.backend.models.Customer;
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
public class CustomersTest {
  @Autowired
  private CustomerDao customerDao;

  private List<Customer> expectedCustomers = new ArrayList<>();
  private Customer firstCustomer;
  private Customer secondCustomer;

  @Before
  public void setup(){
    firstCustomer = new Customer(new Date().getTime(), "Kehinde", "Lagos", "2443", "2234", 2000D, 0D);
    secondCustomer = new Customer(new Date().getTime() + 10L, "Shittu", "Nigeria", "2443", "2234", 2000D, 0D);

    customerDao.truncate("Customer");
    customerDao.create(firstCustomer);
    customerDao.create(secondCustomer);

    expectedCustomers.add(firstCustomer);
    expectedCustomers.add(secondCustomer);
  }

  @After
  public void tearDown(){
    System.out.println("Finished test.");
  }

  @Test
  public void testCreate() {
    Customer customer =  new Customer(new Date().getTime(), "Adedamola", "Ikeja", "2443", "2234", 2000D, 0D);
    customerDao.create(customer);

    Customer actualCustomer = customerDao.getById(customer.getCode());
    Assert.assertNotNull("Failure: get all customer is null.", actualCustomer);
    Assert.assertEquals("Failure: expected customer not equal actual.", customer, actualCustomer);
  }

  @Test
  public void testUpdate() {
    firstCustomer.setName("John");
    customerDao.update(firstCustomer);
    Customer actualCustomer = customerDao.getById(firstCustomer.getCode());

    Assert.assertNotNull("Failure: get all customer is null.", actualCustomer);
    Assert.assertEquals("Failure: update not persisted.", firstCustomer.getName(), actualCustomer.getName());
    Assert.assertEquals("Failure: expected customer not equal actual.", firstCustomer, actualCustomer);
  }

  @Test
  public void testGetAll() {
    List<Customer> customer = customerDao.getAll();
    Assert.assertNotNull("Failure: get all customer is null.", customer);
    Assert.assertEquals("Failure: expected customer not equal actual.", expectedCustomers, customer);
  }

  @Test
  public void testById() {
    Customer customer = customerDao.getById(firstCustomer.getCode());
    Assert.assertNotNull("Failure: get all customer is null.", customer);
    Assert.assertEquals("Failure: expected customer not equal actual.", firstCustomer, customer);
  }

  @Test
  public void testDelete() {
    customerDao.delete(firstCustomer);
    Customer customer = customerDao.getById(firstCustomer.getCode());
    Assert.assertNull("Failure: customer was not deleted", customer);
  }
}