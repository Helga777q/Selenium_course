package tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

public class CountriesLinkTests extends TestBase {


  @Test
  public void countriesWindowsTests(){
    loginAsAdmin();
    openCountries();
    openCountryPage(5);
    openAllExternalLinks();
  }

  private void openAllExternalLinks() {
    List<WebElement> externalLinks = wd.findElements(By.cssSelector(".fa.fa-external-link"));
    String mainWindow = wd.getWindowHandle();
    for (WebElement link: externalLinks){
      Set<String> existingWindows = wd.getWindowHandles();
      link.click();
      String externalWindowLink = wait.until(newExternalWindow(existingWindows));
      //option with ExpectedConitions.numberoOfWindowsToBe
      //wait.until(ExpectedConditions.numberOfWindowsToBe(2));
      //Set<String> windowsNew = wd.getWindowHandles();
      //String externalWindowLink = windowsNew.stream().filter((w)->!w.equals(mainWindow)).collect(Collectors.toList()).get(0);
      wd.switchTo().window(externalWindowLink);
      wd.close();
      wd.switchTo().window(mainWindow);
    }
  }

  private void openCountries() {
    wd.findElement(By.xpath("//span[contains(text(),'Countries')]")).click();
  }


  private void openCountryPage( int countryRow) {
    WebElement table = wd.findElement(By.cssSelector(".dataTable"));
    List<WebElement> rows = table.findElements(By.cssSelector(".row"));
    rows.get(countryRow-1).findElement(By.xpath(String.format(("//tr[%s]/td[7]/a/i"), countryRow+1))).click();
  }


  public ExpectedCondition<String> newExternalWindow(Set<String> oldWindows){
    return new ExpectedCondition<String>() {
      public String apply(WebDriver driver) {
        Set<String> handles = driver.getWindowHandles();
        handles.removeAll(oldWindows);
        return handles.size() > 0 ? handles.iterator().next() : null;
      }
    };
  }




}
