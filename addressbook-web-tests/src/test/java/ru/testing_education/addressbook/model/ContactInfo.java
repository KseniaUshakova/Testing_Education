package ru.testing_education.addressbook.model;

import java.util.Objects;

public class ContactInfo {
  private int id = Integer.MAX_VALUE;
  private  String firstName;
  private  String middleName;
  private  String secondName;
  private  String address;
  private  String homePhone;
  private  String email1;
  private  String email2;
  private  String group;

  @Override
  public String toString() {
    return "ContactInfo{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", secondName='" + secondName + '\'' +
            '}';
  }


  public int getId() {
    return id;
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

  public String getGroup() {
    return group;
  }

  public ContactInfo withId(int id) {
    this.id = id;
    return this;
  }

  public ContactInfo withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ContactInfo withMiddleName(String middleName) {
    this.middleName = middleName;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactInfo that = (ContactInfo) o;
    return id == that.id &&
            Objects.equals(firstName, that.firstName) &&
            Objects.equals(secondName, that.secondName);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, firstName, secondName);
  }

  public ContactInfo withSecondName(String secondName) {
    this.secondName = secondName;
    return this;
  }

  public ContactInfo withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactInfo withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public ContactInfo withEmail1(String email1) {
    this.email1 = email1;
    return this;
  }

  public ContactInfo withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactInfo withGroup(String group) {
    this.group = group;
    return this;

  }
}
