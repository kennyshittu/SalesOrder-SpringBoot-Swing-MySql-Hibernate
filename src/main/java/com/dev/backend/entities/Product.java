package com.dev.backend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Created by Shittu on 29/05/2016.
 */
@Entity
@Table(name = "products")
public class Product {
  @Id
  private long code;

  @Column(name="description")
  private String description;

  @Column(name="price")
  private Double price;

  @Column(name="quantity")
  private Double quantity;

  public Product() {
  }

  public Product(final long code) {
    this.code = code;
  }

  public Product(final long code, final String description, final Double price, final Double quantity) {
    this.code = code;
    this.description = description;
    this.price = price;
    this.quantity = quantity;
  }

  public long getCode() {
    return code;
  }

  public void setCode(final long code) {
    this.code = code;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(final Double price) {
    this.price = price;
  }

  public Double getQuantity() {
    return quantity;
  }

  public void setQuantity(final Double quantity) {
    this.quantity = quantity;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final Product product = (Product) o;
    return Objects.equal(code, product.code) &&
        Objects.equal(description, product.description) &&
        Objects.equal(price, product.price) &&
        Objects.equal(quantity, product.quantity);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(code, description, price, quantity);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("code", code)
        .add("description", description)
        .add("price", price)
        .add("quantity", quantity)
        .toString();
  }
}
