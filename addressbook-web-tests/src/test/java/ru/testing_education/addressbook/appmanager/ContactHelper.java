package ru.testing_education.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.testing_education.addressbook.model.ContactInfo;

public class ContactHelper extends HelperBase{

  public ContactHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.name("theform"));
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactDetails(ContactInfo contactInfo) {
    type(By.name("firstname"), contactInfo.getFirstName());
    type(By.name("middlename"), contactInfo.getMiddleName());
    type(By.name("lastname"), contactInfo.getSecondName());
    type(By.name("address"), contactInfo.getAddress());
    type(By.name("home"), contactInfo.getHomePhone());
    type(By.name("email"),contactInfo.getEmail1());
    type(By.name("email2"),contactInfo.getEmail2());
  }

  public void initContactCreation(String s) {
    click(By.linkText(s));
  }
}
