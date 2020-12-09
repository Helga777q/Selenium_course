package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;


import java.io.File;
import java.util.concurrent.TimeUnit;


public class TestBase {

  public WebDriver wd;
  public WebDriverWait wait;

  @BeforeMethod
  public void start(){
    wd= new InternetExplorerDriver();
    wd.manage().timeouts().implicitlyWait(2 , TimeUnit.SECONDS);
    wait= new WebDriverWait(wd, 2);
  }

  @AfterMethod
  public void stop(){
    wd.quit();
  }

//check if element is present
public boolean isElementPresent(By locator) {
  try {
    wait.until((WebDriver d)-> d.findElement(locator));
    //wd.findElement(locator);
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

  public void type(By locator, String text)  {
    click(locator);
    wd.findElement(locator).sendKeys(text);
  }

  public void typeTab(By locator, String text)  {
    wd.findElement(locator).sendKeys(Keys.TAB);
    wd.findElement(locator).sendKeys(text);
  }

  public void click(By locator) {
    wd.findElement(locator).click();
  }

  public void clearAndType(By locator, String text)  {
    click(locator);
    wd.findElement(locator).clear();
    wd.findElement(locator).sendKeys(text);
  }

  public void goToAdminPage(){
    wd.get("http://localhost/litecart/admin/login.php");
  }

  public void selectFromDropdown(By locator, String option){
     new Select (wd.findElement(locator)).selectByVisibleText(option);
  }
  public void selectFromDropdown(By locator, int index){
    new Select (wd.findElement(locator)).selectByIndex(index);
  }


  public void searchSelectDropdown(By dropdown, By search, String text)  {
    click(dropdown);
    type(search, text);
    wd.findElement(search).sendKeys(Keys.ENTER);
  }

  public void attachFile(By locator, File file){
    wd.findElement(locator).sendKeys(file.getAbsolutePath());
  }


  public void clickCheckBox(By locator){
    boolean checked = Boolean.parseBoolean(wd.findElement(locator).getAttribute("checked"));
    if (!checked){
      wd.findElement(locator).click();
    }

  }

  public  void loginAsAdmin()  {
    goToAdminPage();
    type(By.name("username"), "admin");
    type(By.name("password"), "admin");
    click(By.tagName("button"));
  }




}
