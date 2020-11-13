package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class AdminLoginTests extends TestBase{

  @Test
  public void loginAsAdminTest(){
    wd.get("http://localhost/litecart/admin/login.php");
    wd.findElement(By.name("username")).sendKeys("admin");
    wd.findElement(By.name("password")).sendKeys("admin");
    wd.findElement(By.tagName("button")).click();
    String alertMessage = wd.findElement(By.cssSelector(".alert-success")).getText().substring(2);
    assertTrue(isElementPresent(By.linkText("Logout")));
    assertTrue(alertMessage.equals("You are now logged in as admin"));
  }

}
