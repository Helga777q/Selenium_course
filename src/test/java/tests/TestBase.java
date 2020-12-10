package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;


public class TestBase {

  public  EventFiringWebDriver wd;
  public WebDriverWait wait;



  public  static class MyListener extends AbstractWebDriverEventListener {

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
      System.out.println(by);
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
      System.out.println(throwable);
      File tmp = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
      File screen = new File("screen-"+System.currentTimeMillis()+ ".png");
      try {
        FileHandler.copy(tmp,screen);
      } catch (IOException e) {
        e.printStackTrace();
      }
      System.out.println(screen);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
      System.out.println(by+ " found");

    }
  }



  @BeforeMethod
  public void start(){

    ChromeOptions opt = new ChromeOptions();
    LoggingPreferences logPrefs = new LoggingPreferences();
    logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
    opt.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
    opt.setExperimentalOption("w3c", false);
    wd= new EventFiringWebDriver(new ChromeDriver(opt));
    wd.register(new MyListener());
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
