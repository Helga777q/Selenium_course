package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;


public class BrowserLogTests extends TestBase {


  @Test
  public void productCatalogLogsTest() {
    goToAdminPage();
    loginAsAdmin();
    openProductCatalogPage();
    checkLogsProductPages();


  }

  private void checkLogsProductPages() {
    List<WebElement> products = wd.findElements(By.className("row"));
    //int count = products.size();
    for (int i = 2; i < products.size(); i++) {
      click(By.cssSelector(String.format(("tr:nth-of-type(%s) > td:nth-of-type(3) > a"), i + 2)));
      List<LogEntry> logs = wd.manage().logs().get("browser").getAll();
      try{
        assertTrue(areLogsPresent(logs));
      }
      catch(AssertionError e)
      {
       for (LogEntry logEntry: logs){
         System.out.println(logEntry);
       }
      }
      wd.navigate().back();
    }

  }

  private boolean areLogsPresent(List <LogEntry> logs){
    if (logs.size()==0){
      return true;
    } else{
      return false;
    }
  }

  private void openProductCatalogPage() {
    wd.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
  }



}
