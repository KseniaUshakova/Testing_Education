package ru.testing_education.addressbook;

public class ContactInfo {
  private final String first_name;
  private final String middle_name;
  private final String second_name;
  private final String address;
  private final String home_phone;
  private final String email_1;
  private final String email_2;

  public ContactInfo(String first_name, String middle_name, String second_name, String address, String home_phone, String email_1, String email_2) {
    this.first_name = first_name;
    this.middle_name = middle_name;
    this.second_name = second_name;
    this.address = address;
    this.home_phone = home_phone;
    this.email_1 = email_1;
    this.email_2 = email_2;
  }

  public String getFirst_name() {
    return first_name;
  }

  public String getMiddle_name() {
    return middle_name;
  }

  public String getSecond_name() {
    return second_name;
  }

  public String getAddress() {
    return address;
  }

  public String getHome_phone() {
    return home_phone;
  }

  public String getEmail_1() {
    return email_1;
  }

  public String getEmail_2() {
    return email_2;
  }
}
