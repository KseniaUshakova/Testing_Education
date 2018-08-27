package ru.testing_education.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.GroupData;

public class GroupModifictaionTest extends TestBase {

  @Test

  public void testGroupModification() {

    app.getNavigationHelper().goToGroupPage("groups");
    int before = app.getGroupHelper().getGroupCount();

    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test_group", null, null));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModifictation();
    app.getGroupHelper().fillGroupForm(new GroupData("Chrome_gr1", "try3", "test2"));
    app.getGroupHelper().submitModificaction();
    app.getGroupHelper().returntoGroupPage("group page");
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before);
  }
}
