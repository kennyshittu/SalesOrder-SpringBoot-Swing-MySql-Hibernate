package com.salesorderapp.backend.entities;

import java.util.List;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.salesorderapp.backend.models.Product;

/**
 * Created by Shittu on 30/05/2016.
 */
public class ProductList {
  private List<Product> products;

  public ProductList(final List<Product> products) {
    this.products = products;
  }

  public ProductList() {
  }

  public List<Product> getProducts() {
    return products;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final ProductList that = (ProductList) o;
    return Objects.equal(products, that.products);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(products);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("products", products)
        .toString();
  }
}
