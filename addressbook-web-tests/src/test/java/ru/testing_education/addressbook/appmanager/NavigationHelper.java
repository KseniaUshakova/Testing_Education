package ru.testing_education.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {


  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void goToGroupPage(String groups) {
    click(By.linkText(groups));
  }

  public void goToHomePage(String home) {
    click(By.linkText(home));
  }

}


