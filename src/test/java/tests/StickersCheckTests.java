package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;


public class StickersCheckTests extends TestBase {

  @Test
  public void stickersPopularProductsTest() {
    mainPage();
    WebElement popularProducts = wd.findElement(By.id("box-most-popular"));
    List<WebElement> products = popularProducts.findElements(By.cssSelector(".column.product.shadow"));
    assertTrue(assertStickersCount(products));
  }

  @Test
  public void stickersCampaignProductsTest() {
    mainPage();
    WebElement campaign = wd.findElement(By.id("box-campaigns"));
    List<WebElement> products = campaign.findElements(By.cssSelector(".column.product.shadow"));
    assertTrue(assertStickersCount(products));

  }

  @Test
  public void stickersLatestProductsTest() {
    mainPage();
    WebElement latestProducts = wd.findElement(By.id("box-latest-products"));
    List<WebElement> products = latestProducts.findElements(By.cssSelector(".column.product.shadow"));
    assertTrue(assertStickersCount(products));
  }


  @Test
  public void stickersAllProductsTest() {
    mainPage();
    List<WebElement> allProducts = wd.findElements(By.cssSelector(".column.product.shadow"));
    allProducts.size();
    assertTrue(assertStickersCount(allProducts));
  }


  private boolean assertStickersCount(List<WebElement> products) {
    ArrayList<Boolean> test = new ArrayList();
    ArrayList<Boolean> test2 = new ArrayList();
    for (WebElement product : products) {
      List<WebElement> labels = product.findElements(By.cssSelector("[class~=sticker]"));
      System.out.println(labels.size());
      if (labels.size() == 1) {
        test.add(true);
      } else {
        test2.add(false);
      }
    }
    return products.size() == test.size();
  }
}
