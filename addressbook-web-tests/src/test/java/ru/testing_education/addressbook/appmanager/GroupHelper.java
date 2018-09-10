package ru.testing_education.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.testing_education.addressbook.model.GroupData;
import ru.testing_education.addressbook.model.Groups;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver wd) {
    super(wd);
  }


  public void submitGroupCreation(String submit) {
    click(By.name(submit));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getGroupName());
    type(By.name("group_header"), groupData.getGroupHeader());
    type(By.name("group_footer"), groupData.getGroupFooter());
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void deleteSelectedGroup() {
    click(By.name("delete"));
  }

 // public void selectGroup(int index) {
 //   wd.findElements(By.name("selected[]")).get(index).click();
 // }

  private void selectGroupById(int groupId) {
    wd.findElement(By.cssSelector("input[value='" + groupId + "']" )).click();

  }


  public void initGroupModifictation() { click(By.name("edit")); }

  public void submitModificaction() { click (By.name("update")); }

  public void returntoGroupPage(String groups) { click(By.linkText (groups)); }


  public void create(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation("submit");
    returntoGroupPage("group page");

  }

  public void modify(GroupData group) {
    selectGroupById(group.getGroupId());
    initGroupModifictation();
    fillGroupForm(group);
    submitModificaction();
    returntoGroupPage("group page");
  }


  public void delete(GroupData group) {
    selectGroupById(group.getGroupId());
    deleteSelectedGroup();
    returntoGroupPage("group page");

  }


  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getGroupCount() { return wd.findElements(By.name("selected[]")).size(); }


  public Groups all() {

    Groups groups = new Groups();

    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));

    for (WebElement element: elements){
      String name = element.getText();
      int groupId = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      groups.add(new GroupData().withGroupId(groupId).withGroupName(name));
    }
    return groups;

  }


}
