package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class GeoZonesTest extends TestBase {



  @Test
  public  void geoZonesAdminTest(){
    openGeoZones();
    WebElement tbody = wd.findElement(By.tagName("tbody"));
    List<WebElement> rows = tbody.findElements(By.tagName("tr"));
    List<Integer> assertCount= new ArrayList<>();
    for (int i=1;i<=rows.size();i++){
      openGeoZonePage(i);
      try{
        assertTrue(isAlpabeticOrder());
      }
      catch(AssertionError e)
      {
        System.out.println("Assertion error: not alpabetic order for the GeoZones row: "+ i);
        assertCount.add(i);
      }
      wd.navigate().back();
    }
    assertTrue(assertCount.size()==0); //check that IsAlphabeticOrder() returns true for all the Geo  zones, if not test should fail

  }

  private void openGeoZones() {
    wd.get("http://localhost/litecart/admin/login.php");
    wd.findElement(By.name("username")).sendKeys("admin");
    wd.findElement(By.name("password")).sendKeys("admin");
    wd.findElement(By.tagName("button")).click();
    wd.findElement(By.cssSelector("li[data-code='geo_zones']")).click();

  }

  private void openGeoZonePage(int row){
    WebElement tbody = wd.findElement(By.tagName("tbody"));
    tbody.findElement(By.xpath(String.format(("tr[%s]/td[3]/a"), row))).click();
  }

  private boolean isAlpabeticOrder(){
    WebElement tbody = wd.findElement(By.tagName("tbody"));
    List<WebElement> rows = tbody.findElements(By.tagName("tr"));
    List<String> countryNames = new ArrayList<>(); // empty list for countries of geoZones
    for (WebElement row: rows){
      String countryName = row.findElement((By.xpath("td[2]"))).getAttribute("innerText");
      countryNames.add(countryName);
    }
    List<String> notSorted = new ArrayList<>(countryNames);
    Collections.sort(countryNames);
    return countryNames.equals(notSorted);
  }




}
