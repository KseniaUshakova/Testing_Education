package ru.testing_education.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.testing_education.addressbook.model.ContactInfo;
import ru.testing_education.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {

    click(By.name("theform"));
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactDetails(ContactInfo contactInfo, boolean creation) {

    type(By.name("firstname"), contactInfo.getFirstName());
    type(By.name("middlename"), contactInfo.getMiddleName());
    type(By.name("lastname"), contactInfo.getSecondName());
    type(By.name("address"), contactInfo.getAddress());
    type(By.name("home"), contactInfo.getHomePhone());
    type(By.name("email"), contactInfo.getEmail1());
    type(By.name("email2"), contactInfo.getEmail2());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactInfo.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }


  public void initContactCreation(String s) {

    click(By.linkText(s));
  }



  private void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[id='"+ id +"']")).click();

  }


  public void deleteContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    acceptYes();
  }


  //public void initEditContact(int index) {
  //
  //click(By.xpath("//table[@id='maintable']/tbody/tr["+index+"]/td[8]/a/img"));
  //}

  public void initEditContact(int id) {

  click(By.cssSelector("a[href='edit.php?id=" + id+ "']"));

  }


  public void acceptUpdateContact() {

    click(By.name("update"));
  }


  public void create(ContactInfo contact, boolean creation) {
    initContactCreation("add new");
    fillContactDetails(contact, true);
    submitContactCreation();
    contactCache = null;
  }

  public void modify(ContactInfo newcontact) {
    selectContactById(newcontact.getId());
    initEditContact(newcontact.getId());
    fillContactDetails(newcontact, false);
    acceptUpdateContact();
    contactCache = null;

  }

  public void delete(ContactInfo contact) {

    selectContactById(contact.getId());
    deleteContact();
    contactCache = null;
  }


  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {

    return wd.findElements(By.name("selected[]")).size();
  }


  private Contacts contactCache = null;


  public Contacts all() {

  if (contactCache!=null) {
    return new Contacts(contactCache);
  }

    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));

    for (WebElement element : elements) {

      String secondName = element.findElements(By.tagName("td")).get(1).getText();
      String firstName = element.findElements(By.tagName("td")).get(2).getText();

      int id = Integer.parseInt(element.findElements(By.tagName("td")).get(0).findElement(By.tagName("input")).getAttribute("value"));

      contactCache.add( new ContactInfo().withId(id).withFirstName(firstName).withSecondName(secondName));
    }
    return new Contacts(contactCache);
  }

}
