package com.salesorderapp.backend.ResourceControllers;

import com.salesorderapp.backend.daos.ProductDao;
import com.salesorderapp.backend.models.Product;
import com.salesorderapp.backend.entities.ProductList;
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
  public Product create(
      @RequestBody final Product product
  ) {
    System.out.println("Got here...");
    try {
      mProductDao.create(product);
    }
    catch(final Exception ex) {
      ex.printStackTrace();
      return null;
    }
    return mProductDao.getById(product.getCode());
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
  public Boolean update(
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

  @RequestMapping(value="/delete", method = RequestMethod.GET)
  @ResponseBody
  public Boolean delete(@RequestParam(name="code")final long code) {
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
