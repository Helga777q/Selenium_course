package models;

public class Customer {

  private String firstName;
  private String lastName;
  private String address;
  private String postcode;
  private String city;
  private String country;
  private String zone;
  private String email;
  private String phone;
  private String password;


  public Customer withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public Customer withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public Customer withAddress(String address) {
    this.address = address;
    return this;
  }

  public Customer withPostcode(String postcode) {
    this.postcode = postcode;
    return this;
  }

  public Customer withCity(String city) {
    this.city = city;
    return this;
  }

  public Customer withCountry(String country) {
    this.country = country;
    return this;
  }

  public Customer withZone(String zone) {
    this.zone = zone;
    return this;
  }

  public Customer withEmail(String email) {
    this.email = email;
    return this;
  }

  public Customer withPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public Customer withPassword(String password) {
    this.password = password;
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getAddress() {
    return address;
  }

  public String getPostcode() {
    return postcode;
  }

  public String getCity() {
    return city;
  }

  public String getCountry() {
    return country;
  }

  public String getZone() {
    return zone;
  }

  public String getEmail() {
    return email;
  }

  public String getPhone() {
    return phone;
  }

  public String getPassword() {
    return password;
  }

  @Override
  public String toString() {
    return "Customer{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }
}
