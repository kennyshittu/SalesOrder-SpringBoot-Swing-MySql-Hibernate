package com.salesorderapp.backend.ResourceControllers;

import java.util.List;

import com.salesorderapp.backend.daos.OrderLineDao;
import com.salesorderapp.backend.entities.OrderLine;
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
@RequestMapping(value="/orderline")
public class OrderLineController {
  // CREATE

  @RequestMapping(value="/create" , method = RequestMethod.POST)
  @ResponseBody
  public OrderLine create(
      @RequestBody final OrderLine orderLine
  ) {
    System.out.println("Got here...");
    try {
      mOrderLineDao.create(orderLine);
    }
    catch(final Exception ex) {
      ex.printStackTrace();
      return null;
    }
    return mOrderLineDao.getById(orderLine.getCode());
  }

  // READ

  @RequestMapping(value="/all" , method = RequestMethod.GET)
  @ResponseBody
  public List<OrderLine> getAll() {
    System.out.println("Got here...");
    try {
      return mOrderLineDao.getAll();
    }
    catch(final Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }

  @RequestMapping(value="/", method = RequestMethod.GET)
  @ResponseBody
  public OrderLine getOrder(@RequestParam(name="code") final long code) {
    try {
      return mOrderLineDao.getById(code);
    }
    catch(Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }

  @RequestMapping(value="/salesorder", method = RequestMethod.GET)
  @ResponseBody
  public List<OrderLine> getOrdersBySalesOrder(@RequestParam(name="salescode") final long salescode) {
    try {
      return mOrderLineDao.getBySalesCode(salescode);
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
      final OrderLine orderLine
  ) {
    System.out.println("Got here...");
    try {
      mOrderLineDao.update(orderLine);
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
      OrderLine orderLine = new OrderLine(code);
      mOrderLineDao.delete(orderLine);
    }
    catch(Exception ex) {
      ex.printStackTrace();
      return false;
    }
    return true;
  }


  // Private fields
  @Autowired
  private OrderLineDao mOrderLineDao;
}
