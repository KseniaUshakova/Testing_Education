package ru.testing_education.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.GroupData;
import ru.testing_education.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationTest extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups() throws IOException {

    List<Object[]> listGroups = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")));
    String line = reader.readLine();
    while (line != null) {
      String[] split = line.split(",");
      listGroups.add(new Object[]{new GroupData().withGroupName(split[0]).withGroupHeader(split[1]).withGroupFooter(split[2])});
      line = reader.readLine();
    }
    return listGroups.iterator();
  }

  @Test(dataProvider = "validGroups")
  public void testGroupCreation(GroupData group) {

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
