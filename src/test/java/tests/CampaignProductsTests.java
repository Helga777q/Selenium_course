package tests;

import models.CampaignProduct;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;

public class CampaignProductsTests extends TestBase {

  @Test // check name, regular, price are correct on main page and product page
  public void checkProductTest() {
    List<CampaignProduct> mainPageList = campaignProductsFromMainPage();
    CampaignProduct mainPageProduct = mainPageList.get(0);
    openProductCampaignPage(0);
    CampaignProduct product = infoFromProductPage();
    assertThat(mainPageProduct, equalTo(product));
  }


  @Test //main Page - check colour is grey and stikethrough
  public void regularPriceMainPageTest() {
    mainPage();
    WebElement campaign = wd.findElement(By.id("box-campaigns"));
    List<WebElement> products = campaign.findElements(By.tagName("li"));
    for (WebElement product : products) {
      WebElement regularPrice = product.findElement(By.className("regular-price"));
      String style = regularPrice.getCssValue("text-decoration").split(" ", 2)[0];
      String color = regularPrice.getCssValue("color");
      assertThat(style, equalTo("line-through"));
      assertTrue(isColourGrey(color));
    }

  }


  @Test //product Page - check colour is grey and stikethrough
  public void regularPriceProductPageTest() {
    mainPage();
    openProductCampaignPage(0);
    WebElement regularPrice = wd.findElement(By.cssSelector("div.information  .regular-price"));
    String style = regularPrice.getCssValue("text-decoration").split(" ", 2)[0];
    String color = regularPrice.getCssValue("color");
    assertThat(style, equalTo("line-through"));
    assertTrue(isColourGrey(color));

  }

  @Test //main Page - check colour is red
  public void campaignPriceColourMainPageTest() {
    mainPage();
    WebElement campaign = wd.findElement(By.id("box-campaigns"));
    List<WebElement> products = campaign.findElements(By.cssSelector(".column.product.shadow"));
    for (WebElement product : products) {
      WebElement discountPrice = product.findElement(By.className("campaign-price"));
      String color = discountPrice.getCssValue("color");
      assertTrue(isColourRed(color));
    }
  }

  @Test
  public void campaignPriceColourProductPageTest() {
    mainPage();
    openProductCampaignPage(0);
    WebElement discountPrice = wd.findElement(By.cssSelector("div.information  .campaign-price"));
    String color = discountPrice.getCssValue("color");
    assertTrue(isColourRed(color));

  }


  private boolean isColourRed(String color) {
    String s = color.replaceAll("[^\\d-\\s+]", "").trim();
    List<String> str = Arrays.asList(s.split(" "));
    List<String> newStr = new ArrayList<>();
    newStr.add(str.get(1));
    newStr.add(str.get(2));
    return newStr.stream().allMatch((c) -> c.equals("0"));

  }


  private boolean isColourGrey(String color) {
    String s = color.replaceAll("[^\\d-\\s+]", "").trim();
    List<String> str = Arrays.asList(s.split(" "));
    List<String> newStr = new ArrayList<>();
    if (str.size() > 3) {
      for (int i = 0; i < str.size() - 1; i++) {
        newStr.add(str.get(i));
      }
    } else {
      newStr = new ArrayList<>(str);
    }
    return newStr.stream().allMatch(c -> c.equals(str.get(1)));
  }


  @Test //main page
  public void priceSizeMainPageTest() {
    mainPage();
    WebElement campaign = wd.findElement(By.id("box-campaigns"));
    List<WebElement> products = campaign.findElements(By.tagName("li"));
    for (WebElement product : products) {
      WebElement regularPrice = product.findElement(By.className("regular-price"));
      WebElement discountPrice = product.findElement(By.className("campaign-price"));
      double discountSize = trimPx(discountPrice.getCssValue("font-size"));
      double regularSize = trimPx(regularPrice.getCssValue("font-size"));
      assertTrue(isSize(discountSize, regularSize));
    }
  }

  @Test //product page
  public void priceSizeProductPageTest() {
    mainPage();
    openProductCampaignPage(0);
    WebElement discountPrice = wd.findElement(By.cssSelector("div.information  .campaign-price"));
    WebElement regularPrice = wd.findElement(By.cssSelector("div.information .regular-price"));
    double discountSize = trimPx(discountPrice.getCssValue("font-size"));
    double regularSize = trimPx(regularPrice.getCssValue("font-size"));
    assertTrue(isSize(discountSize, regularSize));

  }


  private CampaignProduct infoFromProductPage() {
    String name = wd.findElement(By.tagName("h1")).getAttribute("textContent");
    String regularPrice = wd.findElement(By.cssSelector("div.information   .regular-price")).getAttribute("textContent");
    String discountPrice = wd.findElement(By.cssSelector("div.information  .campaign-price")).getAttribute("textContent");
    return new CampaignProduct().withName(name).withPrice(regularPrice).withDiscountPrice(discountPrice);
  }


  private List<CampaignProduct> campaignProductsFromMainPage() {
    mainPage();
    List<CampaignProduct> campaignProducts = new ArrayList<>();
    WebElement campaign = wd.findElement(By.id("box-campaigns"));
    List<WebElement> products = campaign.findElements(By.cssSelector(".column.product.shadow"));
    for (WebElement product : products) {
      String name = product.findElement(By.className("name")).getAttribute("textContent");
      String regularPrice = product.findElement(By.className("regular-price")).getAttribute("textContent");
      String discountPrice = product.findElement(By.className("campaign-price")).getAttribute("textContent");
      campaignProducts.add(new CampaignProduct().withName(name).withPrice(regularPrice).withDiscountPrice(discountPrice));
    }
    return campaignProducts;
  }


  private void openProductCampaignPage(int id) {
    WebElement campaign = wd.findElement(By.id("box-campaigns"));
    campaign.findElements(By.cssSelector("li > a")).get(id).click();

  }

  private double trimPx(String font) {
    return Double.parseDouble(font.replaceAll("[^0-9\\.,]", "").trim());
  }

  private boolean isSize(double discountP, double regularP) {
    if (discountP > regularP) {
      return true;
    } else {
      return false;
    }
  }

}


