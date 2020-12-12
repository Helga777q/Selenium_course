package tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import models.Customer;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


public class CreateNewCustomerTests extends TestBase{

  @DataProvider
  public Iterator<Object[]> customers() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/customers.json")));
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<Customer> customers = gson.fromJson(json, new TypeToken<List<Customer>>() {
      }.getType());
      return customers.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
  }




  @Test (dataProvider = "customers")
  public void registerNewCustomer(Customer customer)  {
    mainPage();
    initNewAccountCreation();
    fillNewAccountForm(customer);
    submitForm();
    logout();
    login(customer);
    logout();
  }

  private void fillNewAccountForm(Customer customer)  {
    type(By.name("firstname"), customer.getFirstName());
    type(By.name("lastname"), customer.getLastName());
    type(By.name("address1"), customer.getAddress());
    type(By.name("postcode"), customer.getPostcode());
    type(By.name("city"), customer.getCity());
    searchSelectDropdown(By.cssSelector("[role] [title]"), By.xpath("//input[@type='search']"),customer.getCountry());
    selectFromDropdown((By.cssSelector("select[name='zone_code']")), customer.getZone());
    type(By.name("email"), customer.getEmail());
    type(By.name("phone"), customer.getPhone());
    type(By.name("password"), customer.getPassword());
    type(By.name("confirmed_password"), customer.getPassword());

  }

  private void submitForm()  {
    click(By.cssSelector("button[name='create_account']"));
  }

  private void login(Customer customer)  {
    type(By.cssSelector("input[name='email']"), customer.getEmail());
    type(By.cssSelector("input[name='password']"), customer.getPassword());
    click(By.cssSelector("button[name='login']"));
  }





  private void logout()  {
    click(By.linkText("Logout"));
  }

  private void initNewAccountCreation()  {
    click(By.cssSelector("form[name='login_form'] > table a"));
  }



}
