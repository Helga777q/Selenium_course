import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class MyFirstSeleniumTest {

 private WebDriver wd;

 @BeforeTest
 public void startChrome(){
  ChromeOptions options = new ChromeOptions();
  options.addArguments("-lang=en-GB");
  wd = new ChromeDriver(options);
 }


 @Test
 public void openGoogleAndSearch(){
  wd.navigate().to("https://google.com"); //equal wd.get("link to address")
  assertEquals(wd.getTitle(), "Google");
  WebElement element = wd.findElement(By.name("q"));
  element.sendKeys("webdriver");
  element.submit();
  //wd.findElement(By.name("btnK")).click();
  assertEquals(wd.getTitle(), "webdriver - Google Search");

 }

 @AfterTest
 public void browserQuit(){
  wd.quit();
}

}
