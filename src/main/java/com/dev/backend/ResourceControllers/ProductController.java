package com.dev.backend.ResourceControllers;

import java.util.List;

import com.dev.backend.daos.ProductDao;
import com.dev.backend.entities.Product;
import com.dev.backend.entities.ProductList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Shittu on 30/05/2016.
 */
@Controller
@RequestMapping(value="/product")
public class ProductController {
  // CREATE

  @RequestMapping(value="/create" , method = RequestMethod.POST)
  @ResponseBody
  public boolean create(
      @RequestBody final Product product
  ) {
    System.out.println("Got here...");
    try {
      mProductDao.create(product);
    }
    catch(final Exception ex) {
      ex.printStackTrace();
      return false;
    }
    return true;
  }

  // READ

  @RequestMapping(value="/all" , method = RequestMethod.GET)
  @ResponseBody
  public ProductList getAll() {
    System.out.println("Got here...");
    try {
      return new ProductList(mProductDao.getAll());
    }
    catch(final Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }

  @RequestMapping(value="/", method = RequestMethod.GET)
  @ResponseBody
  public Product getProduct(@RequestParam(name="code") final long code) {
    try {
      return mProductDao.getById(code);
    }
    catch(Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }

  //UPDATE

  @RequestMapping(value="/update" , method = RequestMethod.PUT)
  @ResponseBody
  public boolean update(
      final Product product
  ) {
    System.out.println("Got here...");
    try {
      mProductDao.update(product);
    }
    catch(final Exception ex) {
      ex.printStackTrace();
      return false;
    }
    return true;
  }

  // DELETE

  @RequestMapping(value="/delete", method = RequestMethod.DELETE)
  @ResponseBody
  public boolean delete(@RequestParam(name="code")final long code) {
    try {
      Product product = new Product(code);
      mProductDao.delete(product);
    }
    catch(Exception ex) {
      ex.printStackTrace();
      return false;
    }
    return true;
  }


  // Private fields
  @Autowired
  private ProductDao mProductDao;
}
