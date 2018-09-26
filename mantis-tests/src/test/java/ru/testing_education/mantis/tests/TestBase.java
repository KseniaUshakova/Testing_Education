package ru.testing_education.mantis.tests;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.testing_education.mantis.appmanager.ApplicationManager;


public class TestBase {


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

 /* @BeforeMethod
  public void logTestStart(Method method, Object[] p) {
    logger.info("Start test "+ method.getName()+ "with parameters" + Arrays.asList(p));
    }

  @AfterMethod (alwaysRun = true)
  public void logTestStop(Method method) {
    logger.info("Start test "+method.getName());
  }
  */


}
