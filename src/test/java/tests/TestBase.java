package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;


import java.util.concurrent.TimeUnit;


public class TestBase {

  public WebDriver wd;
  public WebDriverWait wait;

  @BeforeMethod
  public void start(){
    wd= new ChromeDriver();
    wd.manage().timeouts().implicitlyWait(10 , TimeUnit.SECONDS);
    wait= new WebDriverWait(wd, 2);
  }

  @AfterMethod
  public void stop(){
    wd.quit();
  }

//check if element is present
public boolean isElementPresent(By locator) {
  try {
    wd.findElement(locator);
    return true;
  } catch (NoSuchElementException ex) {
    return false;
  }
}

  //check if elements are present
  boolean areElementsPresent(By locator) {
    return wd.findElements(locator).size() > 0;
  }

  public void mainPage(){
    wd.get("http://localhost/litecart/");
  }



}
