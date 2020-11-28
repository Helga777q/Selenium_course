package tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import static org.testng.Assert.assertTrue;

public class CountryTest extends TestBase {


  @Test //check the list of countries is in alphabetic order
  public void countrySortedTest() throws UnsupportedEncodingException {
    openCountries();
    WebElement table = wd.findElement(By.cssSelector(".dataTable"));
    List<WebElement> countries = table.findElements(By.cssSelector("td:nth-of-type(5)"));
    List<String> countriesName = new ArrayList<String>();
    for (WebElement country : countries) {
      countriesName.add(normalizedString(country.getText())); //add each country name to the list
    }
    List<String> notSorted = new ArrayList<String>(countriesName); //copy list of countries
    Collections.sort(countriesName); // sort list of countries
    boolean isEqual = countriesName.equals(notSorted);
    assertTrue(isEqual);
  }


  @Test
  public void countryZonesTest() {
    openCountries();
    WebElement table = wd.findElement(By.cssSelector(".dataTable"));
    List<WebElement> rowZones = table.findElements(By.cssSelector(".row"));
    rowZones.size();
    List<Integer> rowNumber = new ArrayList<>();
    int rowCount = 1;
    for (WebElement tr : rowZones) {
      int zonesCount = Integer.parseInt(tr.findElement(By.cssSelector("td:nth-of-type(6)")).getAttribute("textContent"));
      if (zonesCount > 0) {
        rowNumber.add(rowCount);
      }
      rowCount = rowCount + 1;
    }
    rowNumber.size();
    List<Integer> assertCount= new ArrayList<>();
    for (int i=0; i<rowNumber.size(); i++){
      openCountryPage(rowNumber.get(i));
      try{
       assertTrue(IsAlphabeticOrderZones());
      }
      catch(AssertionError e)
     {
        System.out.println("Assertion error: not alpabetic order for the Country row: "+i);
       assertCount.add(i);
    }
      wd.navigate().back();
    }

    assertTrue(assertCount.size()==0); //check that IsAlphabeticOrderZones() returns true for all the countries with zones, if not test should fail

  }


@Test
  private void openCountries() {
    wd.get("http://localhost/litecart/admin/login.php");
    wd.findElement(By.name("username")).sendKeys("admin");
    wd.findElement(By.name("password")).sendKeys("admin");
    wd.findElement(By.tagName("button")).click();
    wd.findElement(By.xpath("//span[contains(text(),'Countries')]")).click();
  }

  private void openCountryPage( int countryRow) {
    WebElement table = wd.findElement(By.cssSelector(".dataTable"));
    List<WebElement> rows = table.findElements(By.cssSelector(".row"));
    rows.get(countryRow-1).findElement(By.xpath(String.format(("//tr[%s]/td[7]/a/i"), countryRow+1))).click();


  }


  private boolean IsAlphabeticOrderZones() {
    WebElement tbody = wd.findElement(By.cssSelector("#table-zones tbody"));
    List<WebElement> rows = tbody.findElements(By.cssSelector("tr:not(.header)"));
    List<String> zonesNames = new ArrayList<String>();
    for (int i=0; i<rows.size()-1; i++){
      String id = rows.get(i).findElement(By.cssSelector("td:nth-of-type(1)")).getAttribute("innerText");
      String zoneName = rows.get(i).
              findElement((By.cssSelector(String.format("input[name='zones[%s][name]']", id))))
              .getAttribute("value");
      zonesNames.add(zoneName);
    }
    List<String> notSorted = new ArrayList<String>(zonesNames);
    Collections.sort(zonesNames);
     boolean isEqual = zonesNames.equals(notSorted);
     return isEqual;
  }





  private String normalizedString(String s) throws UnsupportedEncodingException {
    String s1 = Normalizer.normalize(s, Normalizer.Form.NFKD);
    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
    return pattern.matcher(s1).replaceAll("");
  }
}
