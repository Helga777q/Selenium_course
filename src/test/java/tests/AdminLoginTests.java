package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class AdminLoginTests extends TestBase {

  @Test
  public void loginAsAdminTest() {
    loginAsAdmin();
    String alertMessage = wd.findElement(By.cssSelector(".alert-success")).getText().substring(2);
    assertTrue(isElementPresent(By.linkText("Logout")));
    assertTrue(alertMessage.equals("You are now logged in as admin"));
  }


  @Test
  public void loginAdminMenuTest() {
    loginAsAdmin();
    //Appearance
    wd.findElement(By.cssSelector("[data-code='appearance']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading"))
            .getText().trim().equals("Template"));
    wd.findElement(By.cssSelector("[data-code='logotype']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Logotype"));

//Catalog
    wd.findElement(By.cssSelector("[data-code='catalog']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Catalog"));
    wd.findElement(By.cssSelector("[data-code='attribute_groups']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Attribute Groups"));
    wd.findElement(By.cssSelector("[data-code='manufacturers']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Manufacturers"));
    wd.findElement(By.cssSelector("[data-code='suppliers']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Suppliers"));
    wd.findElement(By.cssSelector("[data-code='delivery_statuses']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Delivery Statuses"));
    wd.findElement(By.cssSelector("[data-code='sold_out_statuses']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Sold Out Statuses"));
    wd.findElement(By.cssSelector("[data-code='quantity_units']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Quantity Units"));
    wd.findElement(By.cssSelector("[data-code='csv']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("CSV Import/Export"));

//Countries
    wd.findElement(By.cssSelector("[data-code='countries']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Countries"));

//Currencies
    wd.findElement(By.cssSelector("[data-code='currencies']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Currencies"));


//Customers
    wd.findElement(By.cssSelector("[data-code='customers']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Customers"));
    wd.findElement(By.cssSelector("[data-code='csv']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("CSV Import/Export"));
    wd.findElement(By.cssSelector("[data-code='newsletter']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Newsletter"));


    //Geo Zones
    wd.findElement(By.cssSelector("[data-code='geo_zones']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Geo Zones"));


//Languages
    wd.findElement(By.cssSelector("[data-code='languages']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Languages"));

    //Modules
    wd.findElement(By.cssSelector("[data-code='modules']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Customer Modules"));
    wd.findElement(By.cssSelector("[data-code='shipping']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Shipping Modules"));
    wd.findElement(By.cssSelector("[data-code='payment']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Payment Modules"));
    wd.findElement(By.cssSelector("[data-code='order']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Order Modules"));
    wd.findElement(By.cssSelector("[data-code='order_total']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Order Total Modules"));
    wd.findElement(By.cssSelector("[data-code='jobs']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Job Modules"));

    //Orders
    wd.findElement(By.cssSelector("[data-code='orders']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Orders"));
    wd.findElement(By.cssSelector("[data-code='order_statuses']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Order Statuses"));

    //Pages
    wd.findElement(By.cssSelector("[data-code='pages']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Pages"));
    wd.findElement(By.cssSelector("[data-code='csv']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("CSV Import/Export"));

    //Reports
    wd.findElement(By.cssSelector("[data-code='reports']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Monthly Sales"));
    wd.findElement(By.cssSelector("[data-code='most_sold_products']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Most Sold Products"));
    wd.findElement(By.cssSelector("[data-code='most_shopping_customers']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Most Shopping Customers"));


//Settings
    wd.findElement(By.cssSelector("[data-code='settings']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Settings"));
    wd.findElement(By.cssSelector("[data-code='store_info']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Settings"));
    wd.findElement(By.cssSelector("[data-code='defaults']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Settings"));
    wd.findElement(By.cssSelector("[data-code='email']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Settings"));
    wd.findElement(By.cssSelector("[data-code='listings']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Settings"));
    wd.findElement(By.cssSelector("[data-code='customer_details']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Settings"));
    wd.findElement(By.cssSelector("[data-code='legal']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Settings"));
    wd.findElement(By.cssSelector("[data-code='images']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Settings"));
    wd.findElement(By.cssSelector("[data-code='checkout']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Settings"));
    wd.findElement(By.cssSelector("[data-code='advanced']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Settings"));
    wd.findElement(By.cssSelector("[data-code='security']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Settings"));

    //Slides
    wd.findElement(By.cssSelector("[data-code='slides']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Slides"));

    //Tax
    wd.findElement(By.cssSelector("[data-code='tax']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Tax Rates"));
    wd.findElement(By.cssSelector("[data-code='tax_classes']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Tax Classes"));


    //Translations
    wd.findElement(By.cssSelector("[data-code='translations']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Search Translations"));
    wd.findElement(By.cssSelector("[data-code='scan']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Scan Files For Translations"));
    wd.findElement(By.cssSelector("[data-code='csv']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("CSV Import/Export"));

//Users
    wd.findElement(By.cssSelector("[data-code='users']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("Users"));

    //vQmods
    wd.findElement(By.cssSelector("[data-code='vqmods']")).click();
    assertTrue(wd.findElement(By.cssSelector(".panel-heading")).getText().trim().equals("vQmods"));
  }


  private void loginAsAdmin() {
    wd.get("http://localhost/litecart/admin/login.php");
    wd.findElement(By.name("username")).sendKeys("admin");
    wd.findElement(By.name("password")).sendKeys("admin");
    wd.findElement(By.tagName("button")).click();
  }

}
