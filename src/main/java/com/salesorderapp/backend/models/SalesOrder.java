package com.salesorderapp.backend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Shittu on 29/05/2016.
 */
@Entity
@Table(name = "sales_orders")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SalesOrder {

  @Id
  @Column(name = "code")
  private long code;

  @Column(name = "customer")
  private String customer;


  @Column(name = "quantity")
  private Double quantity;

  @Column(name = "total_price")
  private Double totalPrice;


  public SalesOrder() {
  }

  public SalesOrder(final long code) {
    this.code = code;
  }

  public SalesOrder(
      final long code,
      final String customer,
      final Double totalPrice
  ) {
    this.code = code;
    this.customer = customer;
    this.totalPrice = totalPrice;
  }

  public long getCode() {
    return code;
  }

  public void setCode(final long code) {
    this.code = code;
  }

  public String getCustomer() {
    return customer;
  }

  public void setCustomer(final String customer) {
    this.customer = customer;
  }

  public Double getTotalPrice() {
    return totalPrice;
  }

  public void setTotal(final Double totalPrice) {
    this.totalPrice = totalPrice;
  }
}
