package tests;

import com.beust.jcommander.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class AdminLoginTests extends TestBase {


  @Test
  public  void loginAsAdminTest() {
    loginAsAdmin();
    String alertMessage = wd.findElement(By.cssSelector(".alert-success")).getText().substring(2);
    assertTrue(isElementPresent(By.linkText("Logout")));
    assertTrue(alertMessage.equals("You are now logged in as admin"));
  }


  @Test
  public void adminMenuTest(){
    loginAsAdmin();
    List<WebElement> menu = wd.findElements(By.cssSelector("#box-apps-menu a"));
    int items = menu.size();
    for (int i =0; i<items; i++){
      menu = wd.findElements(By.cssSelector("#box-apps-menu a"));
      menu.get(i).click();
      isElementPresent((By.tagName("title")));
      }
  }



  public  void loginAsAdmin() {
    wd.get("http://localhost/litecart/admin/login.php");
    wd.findElement(By.name("username")).sendKeys("admin");
    wd.findElement(By.name("password")).sendKeys("admin");
    wd.findElement(By.tagName("button")).click();
  }

}
