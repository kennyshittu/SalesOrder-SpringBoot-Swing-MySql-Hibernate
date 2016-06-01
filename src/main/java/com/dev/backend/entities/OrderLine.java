package com.dev.backend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by Shittu on 30/05/2016.
 */
@Entity
@Table(name = "order_lines")
public class OrderLine {

  @Id
  @GeneratedValue
  private long code;

  @NotNull
  @Column(name = "sales_code")
  private long salesCode;

  @NotNull
  @Column(name = "product_code")
  private long productCode;

  @NotNull
  @Column(name = "product_price")
  private Double productPrice;

  @NotNull
  @Column(name = "quantity")
  private Double quantity;

  public OrderLine() {
  }

  public OrderLine( final long code) {
    this.code = code;
  }

  public OrderLine(
      final long salesCode,
      final long productCode,
      final Double productPrice,
      final Double quantity
  ) {
    this.salesCode = salesCode;
    this.productCode = productCode;
    this.productPrice = productPrice;
    this.quantity = quantity;
  }

  public long getCode() {
    return code;
  }

  public void setCode(final long code) {
    this.code = code;
  }

  public long getSalesCode() {
    return salesCode;
  }

  public void setSalesCode(final long salesCode) {
    this.salesCode = salesCode;
  }

  public long getProductCode() {
    return productCode;
  }

  public void setProductCode(final long productCode) {
    this.productCode = productCode;
  }

  public Double getProductPrice() {
    return productPrice;
  }

  public void setProductPrice(final Double productPrice) {
    this.productPrice = productPrice;
  }

  public Double getQuantity() {
    return quantity;
  }

  public void setQuantity(final Double quantity) {
    this.quantity = quantity;
  }
}
