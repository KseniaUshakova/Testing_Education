package ru.testing_education.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "addressbook")
public class ContactInfo {

  @Expose(serialize = false, deserialize = false)
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;

  @Column(name = "firstname")
  private String firstName;
  @Column(name = "middlename")
  private String middleName;
  @Column(name = "lastname")
  private String secondName;

  @Column(name = "address")
  @Type(type = "text")
  private String address;

  @Column(name = "home")
  @Type(type = "text")
  private String homePhone;

  @Column(name = "email")
  @Type(type = "text")
  private String email1;

  @Column(name = "email2")
  @Type(type = "text")
  private String email2;

  @Column(name = "email3")
  @Type(type = "text")
  private String email3;

  @Column(name = "mobile")
  @Type(type = "text")
  private String mobilePhone;

  @Column(name = "work")
  @Type(type = "text")
  private String workPhone;

  @Transient
  private String allPhones;
  @Transient
  private String allEmails;

  @Column(name = "photo")
  @Type(type = "text")
  private String photo;

  @ManyToMany (fetch = FetchType.EAGER)
  @JoinTable(name="address_in_groups",
          joinColumns = @JoinColumn(name="id"),
          inverseJoinColumns = @JoinColumn(name="group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();

  @PostLoad
  private void postLoad() {
    address = address.replaceAll("\r", "");
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

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getEmail1() {
    return email1;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
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

  public ContactInfo withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  @Override
  public String toString() {
    return "ContactInfo{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", middleName='" + middleName + '\'' +
            ", secondName='" + secondName + '\'' +
            ", address='" + address + '\'' +
            ", homePhone='" + homePhone + '\'' +
            ", email1='" + email1 + '\'' +
            ", email2='" + email2 + '\'' +
            ", email3='" + email3 + '\'' +
            ", mobilePhone='" + mobilePhone + '\'' +
            ", workPhone='" + workPhone + '\'' +
            '}';
  }

  public boolean equalsString(String s1, String s2) {

    if (s1 == null && s2 == null) {
      return true;
    } else if (s1 == null && s2.equals("") || s1.equals("") && s2 == null) {
      return true;
    } else {
      return Objects.equals(s1, s2);
    }
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactInfo that = (ContactInfo) o;

    return id == that.id &&
            equalsString(firstName, that.firstName) &&
            equalsString(middleName, that.middleName) &&
            equalsString(secondName, that.secondName) &&
            equalsString(address, that.address) &&
            equalsString(homePhone, that.homePhone) &&
            equalsString(email1, that.email1) &&
            equalsString(email2, that.email2) &&
            equalsString(email3, that.email3) &&
            equalsString(mobilePhone, that.mobilePhone) &&
            equalsString(workPhone, that.workPhone);

  }

  public Groups getGroups() {
    return new Groups(groups);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, firstName, middleName, secondName,
            homePhone, email1, email2, email3, mobilePhone, workPhone);
  }


  public ContactInfo withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactInfo withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public ContactInfo withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public ContactInfo withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public File getPhoto() {
    if (photo == null) {
      return null;
    }
    return new File(photo);
  }

  public ContactInfo withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public ContactInfo inGroup(GroupData group) {
    groups.add(group);
    return this;
  }
}
