package ru.testing_education.addressbook.model;

import com.beust.jcommander.Parameter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.util.Objects;

@XStreamAlias("group")
public class GroupData {
  @XStreamOmitField
  private int groupId = Integer.MAX_VALUE;
  private String groupName;
  private String groupHeader;
  private String groupFooter;


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
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GroupData groupData = (GroupData) o;
    return groupId == groupData.groupId &&
            Objects.equals(groupName, groupData.groupName);
  }

  @Override
  public int hashCode() {

    return Objects.hash(groupId, groupName);
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
