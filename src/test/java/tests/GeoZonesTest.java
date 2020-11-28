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
    WebElement tbody = wd.findElement(By.cssSelector(".dataTable tbody"));
    List<WebElement> rows = tbody.findElements(By.className("row"));
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
    wd.findElement(By.xpath("//span[contains(text(),'Geo Zones')]")).click();
  }


  private void openGeoZonePage(int row){
    WebElement table = wd.findElement(By.cssSelector(".dataTable .row"));
    table.findElement(By.xpath(String.format(("//tr[%s]/td[5]/a/i"), row+1))).click();
  }

  private boolean isAlpabeticOrder(){
    WebElement tbody = wd.findElement(By.id("table-zones"));
    List<WebElement> rows = tbody.findElements(By.cssSelector("tr:not(.header)"));
    List<String> zones = new ArrayList<>(); // empty list for countries of geoZones
    for (int i = 0; i<rows.size()-1; i++){
      String zone = rows.get(i).findElement((By.cssSelector("td:nth-of-type(3) >  select > option[selected = 'selected']"))).getAttribute("innerText");
      zones.add(zone);
    }
    List<String> notSorted = new ArrayList<>(zones);
    Collections.sort(zones);
    return zones.equals(notSorted);
  }




}
