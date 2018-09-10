package ru.testing_education.addressbook.tests;

import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.GroupData;
import ru.testing_education.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationTest extends TestBase {

  @Test
  public void testGroupCreation() {

    app.goTo().page("groups");
    Groups before = (Groups) app.group().all();
    GroupData group = new GroupData().withGroupName("test_group");
    app.group().create(group);
    Groups after = (Groups) app.group().all();

    assertThat(after, equalTo(before.withAdded
            (group.withGroupId(after.stream().mapToInt((g)->(g.getGroupId())).max().getAsInt()))));


    assertThat(after.size(), equalTo(before.size()+1));


  }


}
