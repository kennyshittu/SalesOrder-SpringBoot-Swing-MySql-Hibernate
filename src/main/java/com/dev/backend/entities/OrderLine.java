package com.dev.backend.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Shittu on 30/05/2016.
 */
@Entity
@Table(name = "order_lines")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderLine {

  @Id
  private long code;

  @NotNull
  private long sales_code;

  @NotNull
  private long product_code;

  @NotNull
  private Double quantity;

  public OrderLine() {
  }

  public OrderLine( final long code) {
    this.code = code;
  }

  public OrderLine(final long code, final long sales_code, final long product_code, final Double quantity) {
    this.code = code;
    this.sales_code = sales_code;
    this.product_code = product_code;
    this.quantity = quantity;
  }

  public long getCode() {
    return code;
  }

  public void setCode(final long code) {
    this.code = code;
  }

  public long getSales_code() {
    return sales_code;
  }

  public void setSales_code(final long sales_code) {
    this.sales_code = sales_code;
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
}
