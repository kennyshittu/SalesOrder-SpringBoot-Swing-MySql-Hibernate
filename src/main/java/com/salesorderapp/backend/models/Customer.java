package com.salesorderapp.backend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
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
  @Column(name = "name")
  private String name;

  @Column(name = "address")
  private String address;

  @Column(name = "primary_phone_line")
  private String primaryPhoneLine;

  @Column(name = "secondary_phone_line")
  private String secondaryPhoneLine;

  @Column(name = "credit_limit")
  private Double creditLimit;

  @Column(name = "current_credit")
  private Double currentCredit;


  public Customer() { }

  public Customer(final long code) {
    this.code = code;
  }

  public Customer(
      final long code,
      final String name,
      final String address,
      final String primaryPhoneLine,
      final String secondaryPhoneLine,
      final Double creditLimit,
      final Double currentCredit
  ) {
    this.code = code;
    this.name = name;
    this.address = address;
    this.primaryPhoneLine = primaryPhoneLine;
    this.secondaryPhoneLine = secondaryPhoneLine;
    this.creditLimit = creditLimit;
    this.currentCredit = currentCredit;
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

  public String getPrimaryPhoneLine() {
    return primaryPhoneLine;
  }

  public void setPrimaryPhoneLine(final String primaryPhoneLine) {
    this.primaryPhoneLine = primaryPhoneLine;
  }

  public String getSecondaryPhoneLine() {
    return secondaryPhoneLine;
  }

  public void setSecondaryPhoneLine(final String secondaryPhoneLine) {
    this.secondaryPhoneLine = secondaryPhoneLine;
  }

  public Double getCreditLimit() {
    return creditLimit;
  }

  public void setCreditLimit(final Double creditLimit) {
    this.creditLimit = creditLimit;
  }

  public Double getCurrentCredit() {
    return currentCredit;
  }

  public void setCurrentCredit(final Double currentCredit) {
    this.currentCredit = currentCredit;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final Customer customer = (Customer) o;
    return Objects.equal(code, customer.code) &&
        Objects.equal(name, customer.name) &&
        Objects.equal(address, customer.address) &&
        Objects.equal(primaryPhoneLine, customer.primaryPhoneLine) &&
        Objects.equal(secondaryPhoneLine, customer.secondaryPhoneLine) &&
        Objects.equal(creditLimit, customer.creditLimit) &&
        Objects.equal(currentCredit, customer.currentCredit);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(
        code,
        name,
        address,
        primaryPhoneLine,
        secondaryPhoneLine,
        creditLimit,
        currentCredit
    );
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("code", code)
        .add("name", name)
        .add("address", address)
        .add("primaryPhoneLine", primaryPhoneLine)
        .add("secondaryPhoneLine", secondaryPhoneLine)
        .add("creditLimit", creditLimit)
        .add("currentCredit", currentCredit)
        .toString();
  }
}
