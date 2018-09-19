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

    if (app.db().groups().size() == 0) {
      app.goTo().page("groups");
      app.group().create(new GroupData().withGroupName("test_group"));
    }
  }

  @Test

  public void testGroupModification() {

    Groups before = app.db().groups();

    GroupData modifiedGroup = before.iterator().next();

    GroupData group = new GroupData().withGroupId(modifiedGroup.getGroupId())
            .withGroupName("test_group").withGroupHeader("try3").withGroupFooter("test2");
    app.goTo().page("groups");

    app.group().modify(group);
    assertThat(app.group().count(), equalTo(before.size()));

    Groups after = app.db().groups();
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));


  }


}
