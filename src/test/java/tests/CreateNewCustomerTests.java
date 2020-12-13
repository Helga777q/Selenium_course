package tests;


import models.Customer;
import org.openqa.selenium.By;
import org.testng.annotations.Test;



public class CreateNewCustomerTests extends TestBase{

  
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
