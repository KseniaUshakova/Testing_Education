package ru.testing_education.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupModifictaionTest extends TestBase {

  @BeforeMethod

  public void ensurePreconditions() {
    app.goTo().page("groups");
    if (app.group().all().size()==0) {
      app.group().create(new GroupData().withGroupName("test_group"));
    }
  }

  @Test

  public void testGroupModification() {

    Set<GroupData> before = app.group().all();

    GroupData modifiedGroup =  before.iterator().next();

    GroupData group = new GroupData().withGroupId(modifiedGroup.getGroupId())
            .withGroupName("test_group").withGroupHeader("try3").withGroupFooter("test2");

    app.group().modify(group);

    Set<GroupData> after = app.group().all();

    Assert.assertEquals(after.size(), before.size());
    before.remove(modifiedGroup);
    before.add(group);

    Assert.assertEquals(before,after);


  }


}
