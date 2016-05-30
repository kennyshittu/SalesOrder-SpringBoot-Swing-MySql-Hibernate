package com.dev.backend.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Created by Shittu on 29/05/2016.
 */
@Entity
@Table(name = "customers")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {

  @Id
  private long code;

  @NotNull
  @Size(min = 2, max = 80)
  private String name;

  private String address;

  private String primary_phone_line;

  private String secondary_phone_line;

  private Double credit_limit;

  private Double current_credit;


  public Customer() { }

  public Customer(final long code) {
    this.code = code;
  }

  public Customer(
      final long code,
      final String name,
      final String address,
      final String primary_phone_line,
      final String secondary_phone_line,
      final Double credit_limit,
      final Double current_credit
  ) {
    this.code = code;
    this.name = name;
    this.address = address;
    this.primary_phone_line = primary_phone_line;
    this.secondary_phone_line = secondary_phone_line;
    this.credit_limit = credit_limit;
    this.current_credit = current_credit;
  }

  public long getCode() {
    return code;
  }

  public void setCode(final long code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(final String address) {
    this.address = address;
  }

  public String getPrimary_phone_line() {
    return primary_phone_line;
  }

  public void setPrimary_phone_line(final String primary_phone_line) {
    this.primary_phone_line = primary_phone_line;
  }

  public String getSecondary_phone_line() {
    return secondary_phone_line;
  }

  public void setSecondary_phone_line(final String secondary_phone_line) {
    this.secondary_phone_line = secondary_phone_line;
  }

  public Double getCredit_limit() {
    return credit_limit;
  }

  public void setCredit_limit(final Double credit_limit) {
    this.credit_limit = credit_limit;
  }

  public Double getCurrent_credit() {
    return current_credit;
  }

  public void setCurrent_credit(final Double current_credit) {
    this.current_credit = current_credit;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final Customer customer = (Customer) o;
    return Objects.equal(code, customer.code) &&
        Objects.equal(name, customer.name) &&
        Objects.equal(address, customer.address) &&
        Objects.equal(primary_phone_line, customer.primary_phone_line) &&
        Objects.equal(secondary_phone_line, customer.secondary_phone_line) &&
        Objects.equal(credit_limit, customer.credit_limit) &&
        Objects.equal(current_credit, customer.current_credit);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(
        code,
        name,
        address,
        primary_phone_line,
        secondary_phone_line,
        credit_limit,
        current_credit
    );
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("code", code)
        .add("name", name)
        .add("address", address)
        .add("primary_phone_line", primary_phone_line)
        .add("secondary_phone_line", secondary_phone_line)
        .add("credit_limit", credit_limit)
        .add("current_credit", current_credit)
        .toString();
  }
}
