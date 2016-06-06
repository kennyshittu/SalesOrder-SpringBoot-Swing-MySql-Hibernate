package com.salesorderapp.backend.ResourceControllers;

import com.salesorderapp.backend.daos.CustomerDao;
import com.salesorderapp.backend.models.Customer;
import com.salesorderapp.backend.entities.CustomerList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Shittu on 29/05/2016.
 */
@Controller
@RequestMapping(value="/customer")
public class CustomerController {
  // CREATE

  @RequestMapping(value="/create" , method = RequestMethod.POST)
  @ResponseBody
  public Customer create(
      @RequestBody final Customer customer
  ) {
    System.out.println("Got here...");
    try {
      Customer existingCustomer = getCustomer(customer.getCode());
      if(existingCustomer != null){
        mCustomerDao.update(customer);
      }else{
        mCustomerDao.create(customer);
      }
    }
    catch(final Exception ex) {
      ex.printStackTrace();
      return null;
    }
    return mCustomerDao.getById(customer.getCode());
  }

  // READ

  @RequestMapping(value="/all" , method = RequestMethod.GET)
  @ResponseBody
  public CustomerList getAll() {
    System.out.println("Got here...");
    try {
      return new CustomerList(mCustomerDao.getAll());
    }
    catch(final Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }

  @RequestMapping(value="/", method = RequestMethod.GET)
  @ResponseBody
  public Customer getCustomer(@RequestParam(name="code") final long code) {
    try {
      return mCustomerDao.getById(code);
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
      final Customer customer
  ) {
    System.out.println("Got here...");
    try {
      mCustomerDao.update(customer);
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
      Customer customer = new Customer(code);
      mCustomerDao.delete(customer);
    }
    catch(Exception ex) {
      ex.printStackTrace();
      return false;
    }
    return true;
  }


  // Private fields
  @Autowired
  private CustomerDao mCustomerDao;
}
