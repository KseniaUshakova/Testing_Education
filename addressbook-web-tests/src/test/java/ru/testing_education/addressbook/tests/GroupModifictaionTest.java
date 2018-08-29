package ru.testing_education.addressbook.tests;

import org.omg.CORBA.Object;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupModifictaionTest extends TestBase {

  @Test

  public void testGroupModification() {

    app.getNavigationHelper().goToGroupPage("groups");
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test_group", null, null));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size()-1);
    app.getGroupHelper().initGroupModifictation();
    GroupData group = new GroupData(before.get(before.size()-1).getGroupId(),"Chrome_gr1", "try3", "test2");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitModificaction();
    app.getGroupHelper().returntoGroupPage("group page");
    List<GroupData> after = app.getGroupHelper().getGroupList();

    Assert.assertEquals(after.size(), before.size());
    before.remove(before.size()-1);
    before.add(group);

    Assert.assertEquals(new HashSet<java.lang.Object>(before),new HashSet<java.lang.Object>(after));


  }
}
