package com.salesorderapp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;

import com.salesorderapp.backend.daos.ProductDao;

import com.salesorderapp.backend.models.Product;
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
public class ProductsTest {
  @Autowired
  private ProductDao productDao;

  private List<Product> expectedProducts = new ArrayList<>();
  private Product firstProduct;
  private Product secondProduct;

  @Before
  public void setup(){
    firstProduct = new Product(new Date().getTime(), "Laptops", 23D, 12D);
    secondProduct = new Product(new Date().getTime() + 10L, "Shoes", 23D, 12D);

    productDao.truncate("Product");
    productDao.create(firstProduct);
    productDao.create(secondProduct);

    expectedProducts.add(firstProduct);
    expectedProducts.add(secondProduct);
  }

  @After
  public void tearDown(){
    System.out.println("Finished test.");
  }

  @Test
  public void testCreate() {
    Product product = new Product(new Date().getTime() + 10L, "Shoes", 23D, 12D);
    productDao.create(product);

    Product actualProduct = productDao.getById(product.getCode());
    Assert.assertNotNull("Failure: get all products is null.", actualProduct);
    Assert.assertEquals("Failure: expected products not equal actual.", product, actualProduct);
  }

  @Test
  public void testUpdate() {
    firstProduct.setDescription("Cars");
    productDao.update(firstProduct);
    Product actualProduct = productDao.getById(firstProduct.getCode());

    Assert.assertNotNull("Failure: get all products is null.", actualProduct);
    Assert.assertEquals("Failure: update not persisted.", firstProduct.getDescription(), actualProduct.getDescription());
    Assert.assertEquals("Failure: expected products not equal actual.", firstProduct, actualProduct);
  }

  @Test
  public void testGetAll() {
    List<Product> products = productDao.getAll();
    Assert.assertNotNull("Failure: get all products is null.", products);
    Assert.assertEquals("Failure: expected products not equal actual.",expectedProducts, products);
  }

  @Test
  public void testById() {
    Product product = productDao.getById(firstProduct.getCode());
    Assert.assertNotNull("Failure: get all product is null.", product);
    Assert.assertEquals("Failure: expected product not equal actual.",firstProduct, product);
  }

  @Test
  public void testDelete() {
    productDao.delete(firstProduct);
    Product product = productDao.getById(firstProduct.getCode());
    Assert.assertNull("Failure: Object was not deleted", product);
  }
}
