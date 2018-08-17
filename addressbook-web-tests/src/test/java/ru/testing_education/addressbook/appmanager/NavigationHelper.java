package ru.testing_education.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase {


  public NavigationHelper(FirefoxDriver wd) { super(wd); }

  public void goToGroupPage(String groups) { click(By.linkText(groups)); }

  public void goToHomePage(String home) {
    click(By.linkText(home));
  }

}


