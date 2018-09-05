package ru.testing_education.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.testing_education.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

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

  public void selectGroup(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
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

  public void modify(int index, GroupData group) {
    selectGroup(index);
    initGroupModifictation();
    fillGroupForm(group);
    submitModificaction();
    returntoGroupPage("group page");
  }

  public void delete(int index) {
    selectGroup(index);
    deleteSelectedGroup();
    returntoGroupPage("group page");
  }

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getGroupCount() { return wd.findElements(By.name("selected[]")).size(); }

  public List<GroupData> list() {

    List<GroupData> groups = new ArrayList<>();

    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));

    for (WebElement element: elements){
      String name = element.getText();
      int groupId = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      GroupData group = new GroupData(groupId, name, null, null);
      groups.add(group);
    }
    return groups;

  }
}
