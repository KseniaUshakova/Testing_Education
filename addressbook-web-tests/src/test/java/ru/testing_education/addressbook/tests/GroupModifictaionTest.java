package ru.testing_education.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.GroupData;
import ru.testing_education.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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

    Groups before = app.group().all();

    GroupData modifiedGroup =  before.iterator().next();

    GroupData group = new GroupData().withGroupId(modifiedGroup.getGroupId())
            .withGroupName("test_group").withGroupHeader("try3").withGroupFooter("test2");

    app.group().modify(group);

    Groups after = app.group().all();

    assertThat(after.size(), equalTo(before.size()));
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));


  }


}
