package tests;

import models.Customer;
import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.By;
import org.testng.annotations.Test;



public class CreateNewCustomerTests extends TestBase{


  @Test
  public void newCustomer() throws InterruptedException {
    mainPage();
    initNewAccountCreation();
    String email = generateEmail();
    Customer customer = new Customer().withFirstName("Olga").withLastName("Test2").withAddress("Some street").withPostcode("12345")
            .withCity("Odessa").withCountry("United States").withZone("Delaware")
            .withEmail(email).withPhone("+1667777").withPassword("12345678");
    fillNewAccountForm(customer);
    submitForm();
    logout();
    login(customer);
    logout();
  }

  private void fillNewAccountForm(Customer customer) throws InterruptedException {
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

  private void submitForm() throws InterruptedException {
    click(By.cssSelector("button[name='create_account']"));
  }

  private void login(Customer customer) throws InterruptedException {
    type(By.cssSelector("input[name='email']"), customer.getEmail());
    type(By.cssSelector("input[name='password']"), customer.getPassword());
    click(By.cssSelector("button[name='login']"));
  }





  private void logout() throws InterruptedException {
    click(By.linkText("Logout"));
  }

  private void initNewAccountCreation() throws InterruptedException {
    click(By.cssSelector("form[name='login_form'] > table a"));
  }


  private String generateEmail(){
    return new RandomStringGenerator.Builder()
            .withinRange('a', 'z').build().generate(7)+"@example.com";
  }


}
