package com.dev.backend.ResourceControllers;

import java.util.List;

import com.dev.backend.daos.SalesOrderDao;
import com.dev.backend.entities.SalesOrder;
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
@RequestMapping(value="/salesorder")
public class SalesOrderController {
  // CREATE

  @RequestMapping(value="/create" , method = RequestMethod.POST)
  @ResponseBody
  public String create(
      @RequestBody final SalesOrder salesOrder
  ) {
    System.out.println("Got here...");
    try {
      mSalesOrderDao.create(salesOrder);
    }
    catch(final Exception ex) {
      return ex.getMessage();
    }
    return "User succesfully saved!";
  }

  // READ

  @RequestMapping(value="/all" , method = RequestMethod.GET)
  @ResponseBody
  public List<SalesOrder> getAll() {
    System.out.println("Got here...");
    try {
      return mSalesOrderDao.getAll();
    }
    catch(final Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }

  @RequestMapping(value="/", method = RequestMethod.GET)
  @ResponseBody
  public SalesOrder getSalesOrder(@RequestParam(name="salescode") final long salesCode) {
    try {
      return mSalesOrderDao.getById(salesCode);
    }
    catch(Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }

  //UPDATE

  @RequestMapping(value="/update" , method = RequestMethod.PUT)
  @ResponseBody
  public String update(
      final SalesOrder salesOrder
  ) {
    System.out.println("Got here...");
    try {
      mSalesOrderDao.update(salesOrder);
    }
    catch(final Exception ex) {
      return ex.getMessage();
    }
    return "User succesfully saved!";
  }

  // DELETE

  @RequestMapping(value="/delete", method = RequestMethod.DELETE)
  @ResponseBody
  public String delete(@RequestParam(name="ordernumber")final long orderNumber) {
    try {
      SalesOrder salesOrder = new SalesOrder(orderNumber);
      mSalesOrderDao.delete(salesOrder);
    }
    catch(Exception ex) {
      return ex.getMessage();
    }
    return "User succesfully deleted!";
  }


  // Private fields
  @Autowired
  private SalesOrderDao mSalesOrderDao;
}
