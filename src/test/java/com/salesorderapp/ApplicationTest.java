package com.salesorderapp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;

import com.salesorderapp.backend.daos.ProductDao;

import com.salesorderapp.backend.entities.Product;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Created by Shittu on 01/06/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Transactional
public class ApplicationTest {
  @Autowired
  private ProductDao productDao;

  private List<Product> expectedProducts = new ArrayList<>();
  private Product firstProduct;
  private Product secondProduct;

  @Before
  public void setup(){
//    productDao.executeNativeQuery("TRUNCATE SCHEMA PUBLIC AND COMMIT");
    firstProduct = new Product(new Date().getTime(), "Laptops", 23D, 12D);
    secondProduct = new Product(new Date().getTime() + 10L, "Shoes", 23D, 12D);

    productDao.create(firstProduct);
    productDao.create(secondProduct);

    expectedProducts.add(firstProduct);
    expectedProducts.add(secondProduct);

  }

  @After
  public void tearDown(){

  }

//  @Test
//  public void testCreate() {
//
//  }

  @Test
  public void testGetAll() {
    List<Product> products = productDao.getAll();
    Assert.assertNotNull("Failure: get all products is null.", products);
    Assert.assertEquals("Failure: expected products not equal actual.",expectedProducts, products);
  }
}
