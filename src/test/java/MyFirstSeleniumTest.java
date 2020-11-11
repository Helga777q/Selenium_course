import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class MyFirstSeleniumTest {
 private WebDriver wd;

 @Test
  public void openSoftTestingSiteChrome(){
  wd = new ChromeDriver();
  wd.get("https://software-testing.ru/");
  assertEquals(wd.getTitle(), "Software-Testing.Ru");
 }

 @Test
 public void openGoogleFirefox(){
  wd=new FirefoxDriver();
  wd.get("https://google.com");
  assertEquals(wd.getTitle(), "Google");
 }

 @AfterMethod
 public void browserQuit(){
  wd.quit();
 }

}
