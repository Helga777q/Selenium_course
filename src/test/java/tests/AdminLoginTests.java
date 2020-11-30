package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class AdminLoginTests extends TestBase {


  @Test
  public  void loginAsAdminTest() throws InterruptedException {
    loginAsAdmin();
    String alertMessage = wd.findElement(By.cssSelector("div#notices > .notice.success")).getText().trim();
    assertTrue(isElementPresent(By.cssSelector("a[title='Logout']")));
    assertTrue(alertMessage.equals("You are now logged in as admin"));
  }


  @Test
  public void adminMenuTest() throws InterruptedException {
    loginAsAdmin();
    List<WebElement> menu = wd.findElements(By.cssSelector("#box-apps-menu a"));
    int items = menu.size();
    for (int i =0; i<items; i++){
      menu = wd.findElements(By.cssSelector("#box-apps-menu a"));
      menu.get(i).click();
      isElementPresent((By.tagName("h1")));
      }
  }



  public  void loginAsAdmin() throws InterruptedException {
   goToAdminPage();
   type(By.name("username"), "admin");
   type(By.name("password"), "admin");
   click(By.tagName("button"));
  }

}
