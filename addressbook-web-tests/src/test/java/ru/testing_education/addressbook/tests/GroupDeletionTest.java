package ru.testing_education.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.testing_education.addressbook.model.GroupData;
import ru.testing_education.addressbook.model.Groups;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupDeletionTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().page("groups");
    if (app.group().all().size()==0) {
      app.group().create(new GroupData().withGroupName("test_group"));
    }
  }

  @Test
  public void GroupDeletionTest() {

    Groups before = app.group().all();

    GroupData deletedGroup =  before.iterator().next();

    app.group().delete(deletedGroup);

    Groups after = app.group().all();

    assertThat(after.size(), equalTo(before.size()-1));

    assertThat(after, equalTo(before.without(deletedGroup)));

  }



}
