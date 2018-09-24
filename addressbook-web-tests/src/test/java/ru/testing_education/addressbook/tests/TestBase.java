package ru.testing_education.addressbook.tests;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import ru.testing_education.addressbook.appmanager.ApplicationManager;
import ru.testing_education.addressbook.model.ContactInfo;
import ru.testing_education.addressbook.model.Contacts;
import ru.testing_education.addressbook.model.GroupData;
import ru.testing_education.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

  org.slf4j.Logger logger = LoggerFactory.getLogger(TestBase.class);

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));


  public static boolean isAlertPresent(WebDriver wd) {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }


  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    app.stop();
  }

  @BeforeMethod
  public void logTestStart(Method method, Object[] p) {
    logger.info("Start test "+ method.getName()+ "with parameters" + Arrays.asList(p));
    }

  @AfterMethod (alwaysRun = true)
  public void logTestStop(Method method) {
    logger.info("Start test "+method.getName());
  }



  public void verifyGroupListInUI() {
    if (Boolean.getBoolean("verifyUI")) {
      Groups dbGroup = app.db().groups();
      Groups uiGroup = app.group().all();
      assertThat(uiGroup, equalTo(dbGroup.stream()
              .map((g) -> new GroupData().withGroupId(g.getGroupId()).withGroupName(g.getGroupName()))
              .collect(Collectors.toSet())));
    }
  }


  public void verifyContactListUI() {
    if (Boolean.getBoolean("verifyUI")) {
      Contacts dbContact = app.db().contacts();
      Contacts uiContact = app.contact().all();
      assertThat(
              uiContact.stream().map((c)-> new ContactInfo().withId(c.getId())
                      .withFirstName(c.getFirstName()).withSecondName(c.getSecondName())
                      .withAddress(c.getAddress())).collect(Collectors.toSet()),
              equalTo(dbContact.stream().map((c)-> new ContactInfo().withId(c.getId())
                      .withFirstName(c.getFirstName()).withSecondName(c.getSecondName())
                      .withAddress(c.getAddress())).collect(Collectors.toSet())));
    }
  }

}
