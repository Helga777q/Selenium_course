package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import java.util.List;
import java.util.regex.Pattern;

import static org.testng.Assert.assertEquals;

public class CartProductsTests extends TestBase {


  @Test
  public void CartCheckoutTest() {
    mainPage();
    int products = 3;
    for (int i = 1; i <= products; i++) {
      openProductPage(0);
      addToCart();
      wd.navigate().back();
    }
    checkout();
    clearCart();
    wd.navigate().back();
    assertEquals(getCartItemsQuantity(), 0);


  }

  private int getCartItemsQuantity() {
    return Integer.parseInt(wd.findElement(By.cssSelector("#cart .quantity")).getAttribute("textContent"));
  }


  private void clearCart() {
    int items = wd.findElements(By.cssSelector(".items li")).size();
    //clear cart not including the last item in the cart
    for (int i =1; i<items; i++){
      int tableRows = wd.findElements(By.cssSelector(".dataTable.rounded-corners tr")).size();
      click(By.cssSelector("li.shortcut"));
      click(By.cssSelector("button[name='remove_cart_item']"));
      int afterRows = tableRows-1;
      wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".dataTable.rounded-corners tr"), afterRows)); //check that the row in Order table minus 1
      tableRows = wd.findElements(By.cssSelector(".dataTable.rounded-corners tr")).size();
      assertEquals(tableRows, afterRows);
    }
    //clear the last item (or the only one)
    click(By.cssSelector("button[name='remove_cart_item']"));
    WebElement table = wd.findElement(By.cssSelector(".dataTable.rounded-corners"));
    wait.until(ExpectedConditions.stalenessOf(table));
    wait.until(ExpectedConditions.textMatches(By.cssSelector("div#checkout-cart-wrapper"), Pattern.compile("^There are no items in your cart.")));
    //System.out.println(wd.findElement(By.cssSelector("div#checkout-cart-wrapper")).getAttribute("textContent"));
  }

  private void checkout() {
    click(By.cssSelector("#cart .link"));
  }

  private void addToCart() {
    By locator = By.cssSelector("#cart .quantity");
    int quantity = Integer.parseInt(wd.findElement(locator).getAttribute("textContent"));
    int quantityNew = quantity + 1;
    if (areElementsPresent(By.cssSelector("select[name='options[Size]']"))) {
      selectFromDropdown(By.cssSelector("select[name='options[Size]']"), 1);
      addProduct();
    } else {
      addProduct();
    }
    wait.until(ExpectedConditions.textToBe(locator, String.valueOf(quantityNew)));
    quantity = Integer.parseInt(wd.findElement(locator).getAttribute("textContent"));
    assertEquals(quantityNew, quantity); // check that cart has new value of items
  }

  private void addProduct() {
    click(By.cssSelector("button[name='add_cart_product']"));
  }

  private void openProductPage(int id) {
    List<WebElement> allProducts = wd.findElements(By.cssSelector(".column.product.shadow"));
    allProducts.get(id).click();
  }


}

