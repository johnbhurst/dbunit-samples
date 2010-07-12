// $Id$
// John Hurst (john.b.hurst@gmail.com)
// 2010-07-12

package org.dbunit.samples.birt.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Office {
  private String officeCode;
  private String city;
  private String phone;
  private String addressLine1;
  private String addressLine2;
  private String state;
  private String country;
  private String postalCode;
  private String territory;

  public Office() {
  }

  public String getOfficeCode() {
    return officeCode;
  }

  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddressLine1() {
    return addressLine1;
  }

  public void setAddressLine1(String addressLine1) {
    this.addressLine1 = addressLine1;
  }

  public String getAddressLine2() {
    return addressLine2;
  }

  public void setAddressLine2(String addressLine2) {
    this.addressLine2 = addressLine2;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getTerritory() {
    return territory;
  }

  public void setTerritory(String territory) {
    this.territory = territory;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj instanceof Office) {
      Office other = (Office) obj;
      return new EqualsBuilder()
        .append(getOfficeCode(), other.getOfficeCode())
        .isEquals();
    }
    return false;
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(7, 13)
      .append(getOfficeCode())
      .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
      .append("officeCode", getOfficeCode())
      .append("city", getCity())
      .append("phone", getPhone())
      .append("addressLine1", getAddressLine1())
      .append("addressLine2", getAddressLine2())
      .append("state", getState())
      .append("country", getCountry())
      .append("postalCode", getPostalCode())
      .append("territory", getTerritory())
      .toString();
  }
}
