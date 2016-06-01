package com.salesorderapp.backend.entities;

import java.util.List;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.salesorderapp.backend.models.SalesOrder;

/**
 * Created by Shittu on 30/05/2016.
 */
public class SalesOrderList {
  private List<SalesOrder> salesOrders;

  public SalesOrderList() {
  }

  public SalesOrderList(final List<SalesOrder> salesOrders) {
    this.salesOrders = salesOrders;
  }

  public List<SalesOrder> getSalesOrders() {
    return salesOrders;
  }

  public void setSalesOrders(final List<SalesOrder> salesOrders) {
    this.salesOrders = salesOrders;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final SalesOrderList that = (SalesOrderList) o;
    return Objects.equal(salesOrders, that.salesOrders);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(salesOrders);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("salesOrders", salesOrders)
        .toString();
  }
}
