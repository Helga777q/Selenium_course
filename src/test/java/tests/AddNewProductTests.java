package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.File;

import static org.testng.Assert.assertTrue;


public class AddNewProductTests extends TestBase {


  @Test
  public void addNewProduct() throws InterruptedException {
    loginAsAdmin();
    goToCatalog();
    int before = goodsQuantity();
    initAddNewProduct();
    fillAddNewProductForm();
    submitForm();
    int after = goodsQuantity();
    assertTrue(after == before + 1);
  }


  private void fillAddNewProductForm() throws InterruptedException {

    //tab General
    clickCheckBox(By.name("status"));
    type(By.cssSelector("input[name='name[en]']"), "TestProduct");
    type(By.cssSelector("input[name='code']"), "123345");
    clickCheckBox(By.name("categories[]"));
    clickCheckBox(By.name("product_groups[]"));
    clearAndType(By.cssSelector("input[name='quantity']"), "7");
    selectFromDropdown(By.xpath("//select[@name='sold_out_status_id']"), 0);
    File photo = new File("src/test/resources/duck_1.jpg");
    attachFile(By.cssSelector("input[name='new_images[]']"), photo);
    type(By.cssSelector("input[name='date_valid_from']"), "12-02-2020");
    type(By.cssSelector("input[name='date_valid_to']"), "12-30-2021");
//tab Information
    click(By.linkText("Information"));
    selectFromDropdown(By.cssSelector("select[name='manufacturer_id']"), 1);
    typeTab(By.cssSelector("input[name='keywords']"), "some keywords");
    typeTab(By.cssSelector("input[name='short_description[en]']"), "some product");
    typeTab(By.cssSelector(".trumbowyg-editor"), "many textt ttttt ttt");
    typeTab(By.name("head_title[en]"), "title of the product");
    typeTab(By.name("meta_description[en]"), "_yyy");
//tab Prices
    click(By.linkText("Prices"));
    clearAndType(By.name("purchase_price"), "20");
    selectFromDropdown(By.name("purchase_price_currency_code"), 1);
    clearAndType(By.cssSelector("input[name='gross_prices[USD]']"), "35.5");
    clearAndType(By.cssSelector("input[name='gross_prices[EUR]']"), "28.5");
  }

  private void initAddNewProduct() throws InterruptedException {
    click(By.linkText("Add New Product"));
  }

  private void goToCatalog() throws InterruptedException {
    click(By.linkText("Catalog"));

  }

  private void submitForm() throws InterruptedException {
    click(By.cssSelector("button[name='save']"));
  }

  private int goodsQuantity() throws InterruptedException {
    String textContent = wd.findElement(By.cssSelector(".footer >td")).getAttribute("textContent");
    String products = textContent.substring(textContent.indexOf("|") + 1).trim();
    return Integer.parseInt((products.substring(9).trim()));
  }


}
