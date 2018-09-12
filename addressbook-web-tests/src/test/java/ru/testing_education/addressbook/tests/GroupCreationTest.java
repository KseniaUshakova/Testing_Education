package ru.testing_education.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.GroupData;
import ru.testing_education.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationTest extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups() {

    List<Object[]> listGroups = new ArrayList<Object[]>();
    listGroups.add(new Object[]{new GroupData().withGroupName("test_group").withGroupHeader("header").withGroupFooter("footer")});
    listGroups.add(new Object[]{new GroupData().withGroupName("test_group2").withGroupHeader("header2").withGroupFooter("footer2")});
    listGroups.add(new Object[]{new GroupData().withGroupName("test_group3").withGroupHeader("header3").withGroupFooter("footer3")});

    return listGroups.iterator();
  }

  @Test(dataProvider = "validGroups")
  public void testGroupCreation(GroupData group){

    app.goTo().page("groups");
    Groups before = (Groups) app.group().all();
    app.group().create(group);

    assertThat(app.group().count(), equalTo(before.size() + 1));
    Groups after = (Groups) app.group().all();

    assertThat(after, equalTo(before.withAdded
            (group.withGroupId(after.stream().mapToInt((g) -> (g.getGroupId())).max().getAsInt()))));
  }


  @Test
  public void testGroupBadCreation() {
    app.goTo().page("groups");
    Groups before = (Groups) app.group().all();
    GroupData group = new GroupData().withGroupName("test_group'");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = (Groups) app.group().all();

    assertThat(after, equalTo(before));

  }


}
