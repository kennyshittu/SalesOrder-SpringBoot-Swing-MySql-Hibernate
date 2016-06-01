package com.salesorderapp.backend.entities;

import java.util.List;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.salesorderapp.backend.models.Customer;

/**
 * Created by Shittu on 30/05/2016.
 */
public class CustomerList {
  private List<Customer> customers;

  public CustomerList() {
  }

  public CustomerList(final List<Customer> customers) {
    this.customers = customers;
  }

  public List<Customer> getCustomers() {
    return customers;
  }

  public void setCustomers(final List<Customer> customers) {
    this.customers = customers;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final CustomerList that = (CustomerList) o;
    return Objects.equal(customers, that.customers);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(customers);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customers", customers)
        .toString();
  }
}
