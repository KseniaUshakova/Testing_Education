package ru.testing_education.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModifictaionTest extends TestBase {

  @BeforeMethod

  public void ensurePreconditions() {
    app.goTo().page("groups");
    if (app.group().list().size()==0) {
      app.group().create(new GroupData("test_group", null, null));
    }
  }

  @Test

  public void testGroupModification() {

    List<GroupData> before = app.group().list();

    int index = before.size()-1;
    GroupData group = new GroupData(before.get(index).getGroupId(),
            "test_group", "try3", "test2");

    app.group().modify(index, group);

    List<GroupData> after = app.group().list();

    Assert.assertEquals(after.size(), before.size());
    before.remove(index);
    before.add(group);

    Comparator<? super GroupData> byId = ((g1, g2) -> Integer.compare(g1.getGroupId(),g2.getGroupId()));
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before,after);


  }


}
