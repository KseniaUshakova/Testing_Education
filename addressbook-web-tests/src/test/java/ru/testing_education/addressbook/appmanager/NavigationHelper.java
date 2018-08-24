package ru.testing_education.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {


  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void goToGroupPage(String groups) {

    if (isElementPresent(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).getText().equals("Group")
            && isElementPresent(By.name("new"))) {

      return;
    }

    click(By.linkText(groups));
  }

  public void goToHomePage(String home) {

    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText(home));
  }

}


