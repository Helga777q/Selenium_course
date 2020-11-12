package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestBase {

  public WebDriver wd;

  @BeforeMethod
  public void start(){
    wd= new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
  }

@AfterMethod
  public void stop(){
    wd.quit();
  }



}
