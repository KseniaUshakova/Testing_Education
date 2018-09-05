package ru.testing_education.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().page("groups");
    if (app.group().list().size()==0) {
      app.group().create(new GroupData("test_group", null, null));
    }
  }

  @Test
  public void GroupDeletionTest() {

    List<GroupData> before = app.group().list();

    int index = before.size()-1;

    app.group().delete(index);

    List<GroupData> after = app.group().list();

    Assert.assertEquals(after.size(), before.size()-1);

    before.remove(index);

    Assert.assertEquals(before, after);

  }



}
