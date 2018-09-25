package ru.testing_education.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("group")
@Entity
@Table(name = "group_list")
public class GroupData {
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GroupData groupData = (GroupData) o;
    return groupId == groupData.groupId &&
            Objects.equals(groupName, groupData.groupName) &&
            Objects.equals(groupHeader, groupData.groupHeader) &&
            Objects.equals(groupFooter, groupData.groupFooter);
  }

  @Override
  public int hashCode() {

    return Objects.hash(groupId, groupName, groupHeader, groupFooter);
  }

  @XStreamOmitField
  @Id
  @Column(name = "group_id")
  private int groupId = Integer.MAX_VALUE;

  @Column(name = "group_name")
  private String groupName;
  @Column(name = "group_header")
  @Type(type = "text")

  private String groupHeader;
  @Column(name = "group_footer")
  @Type(type = "text")
  private String groupFooter;


  @ManyToMany (mappedBy = "groups",fetch = FetchType.EAGER)
  @Where(clause = "deprecated='0000-00-00'")
  private Set<ContactInfo> contacts = new HashSet<ContactInfo>();


  public Contacts getContacts() {
    return new Contacts(contacts);
  }

  public GroupData withGroupId(int groupId) {
    this.groupId = groupId;
    return this;
  }

  public GroupData withGroupName(String groupName) {
    this.groupName = groupName;
    return this;
  }

  public GroupData withGroupHeader(String groupHeader) {
    this.groupHeader = groupHeader;
    return this;
  }

  public GroupData withGroupFooter(String groupFooter) {
    this.groupFooter = groupFooter;
    return this;
  }


  public int getGroupId() {
    return groupId;
  }

  public String getGroupName() {
    return groupName;
  }

  public String getGroupHeader() { return groupHeader; }

  public String getGroupFooter() {
    return groupFooter;
  }

  @Override
  public String toString() {
    return "GroupData{" +
            "groupId='" + groupId + '\'' +
            ", groupName='" + groupName + '\'' +
            '}';
  }

  public void setGroupId(int groupID) {
    this.groupId = groupId;

  }
}
