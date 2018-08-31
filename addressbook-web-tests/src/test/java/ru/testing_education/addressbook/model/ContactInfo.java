package ru.testing_education.addressbook.model;

import java.util.Objects;

public class ContactInfo {
  private final String firstName;
  private final String middleName;
  private final String secondName;
  private final String address;
  private final String homePhone;
  private final String email1;
  private final String email2;
  private final String group;

  public ContactInfo(String firstName, String middleName, String secondName, String address, String homePhone, String email1, String email2, String group) {
    this.firstName = firstName;
    this.middleName = middleName;
    this.secondName = secondName;
    this.address = address;
    this.homePhone = homePhone;
    this.email1 = email1;
    this.email2 = email2;
    this.group = group;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactInfo that = (ContactInfo) o;
    return Objects.equals(firstName, that.firstName) &&
            Objects.equals(secondName, that.secondName);
  }

  @Override
  public int hashCode() {

    return Objects.hash(firstName, secondName);
  }

  @Override
  public String toString() {
    return "ContactInfo{" +
            "firstName='" + firstName + '\'' +
            ", secondName='" + secondName + '\'' +
            '}';
  }

  public String getFirstName() {
    return firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public String getSecondName() {
    return secondName;
  }

  public String getAddress() {
    return address;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getEmail1() {
    return email1;
  }

  public String getEmail2() {
    return email2;
  }

  public String getGroup() { return group; }
}
