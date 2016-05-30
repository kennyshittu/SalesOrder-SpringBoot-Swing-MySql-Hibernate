package com.dev.backend.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Shittu on 29/05/2016.
 */
@Entity
@Table(name = "sales_orders")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SalesOrder {

  @Id
  private long code;

  @NotNull
  private long customer_code;

  private long product_code;

  private Double quantity;

  private Double total_price;


  public SalesOrder() {
  }

  public SalesOrder(final long code) {
    this.code = code;
  }

  public SalesOrder(
      final long code,
      final long customer_code,
      final long product_code,
      final Double quantity,
      final Double total_price
  ) {
    this.code = code;
    this.customer_code = customer_code;
    this.product_code = product_code;
    this.quantity = quantity;
    this.total_price = total_price;
  }

  public long getCode() {
    return code;
  }

  public void setCode(final long code) {
    this.code = code;
  }

  public long getCustomer_code() {
    return customer_code;
  }

  public void setCustomer_code(final long customer_code) {
    this.customer_code = customer_code;
  }

  public long getProduct_code() {
    return product_code;
  }

  public void setProduct_code(final long product_code) {
    this.product_code = product_code;
  }

  public Double getQuantity() {
    return quantity;
  }

  public void setQuantity(final Double quantity) {
    this.quantity = quantity;
  }

  public Double getTotal_price() {
    return total_price;
  }

  public void setTotal_price(final Double total_price) {
    this.total_price = total_price;
  }
}
