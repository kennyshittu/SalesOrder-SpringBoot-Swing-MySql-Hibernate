package com.dev.backend.models;

import java.util.List;
import java.util.Map;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Created by Shittu on 31/05/2016.
 */
public class SalesOrderEntity {
  private long orderNumber;
  private Map<String, Object> customer;
  private List<Map<String, Object>> products;
  private Double totalPrice;

  public SalesOrderEntity() {
  }

  public SalesOrderEntity(
      final long orderNumber,
      final Map<String, Object> customer,
      final List<Map<String, Object>> products,
      final Double totalPrice
  ) {
    this.orderNumber = orderNumber;
    this.customer = customer;
    this.products = products;
    this.totalPrice = totalPrice;
  }

  public long getOrderNumber() {
    return orderNumber;
  }

  public void setOrderNumber(final long orderNumber) {
    this.orderNumber = orderNumber;
  }

  public Map<String, Object> getCustomer() {
    return customer;
  }

  public void setCustomer(final Map<String, Object> customer) {
    this.customer = customer;
  }

  public List<Map<String, Object>> getProducts() {
    return products;
  }

  public void setProducts(final List<Map<String, Object>> products) {
    this.products = products;
  }

  public Double getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(final Double totalPrice) {
    this.totalPrice = totalPrice;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final SalesOrderEntity that = (SalesOrderEntity) o;
    return Objects.equal(orderNumber, that.orderNumber) &&
        Objects.equal(customer, that.customer) &&
        Objects.equal(products, that.products) &&
        Objects.equal(totalPrice, that.totalPrice);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(orderNumber, customer, products, totalPrice);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("orderNumber", orderNumber)
        .add("customer", customer)
        .add("products", products)
        .add("totalPrice", totalPrice)
        .toString();
  }
}

