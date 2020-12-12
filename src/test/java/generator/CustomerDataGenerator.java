package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Customer;
import org.apache.commons.text.RandomStringGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class CustomerDataGenerator {

  @Parameter(names = "-c", description = "Customer count")
  public int count;

  @Parameter(names="-f", description = "File")
  public String file;



public static void main(String[] args ) throws IOException {
  CustomerDataGenerator generator = new CustomerDataGenerator();
  JCommander jCommander = new JCommander(generator);
  try {
    jCommander.parse(args);
  } catch (ParameterException ex){
    jCommander.usage();
    return;
  }
  generator.run();


}

  private void run() throws IOException {
    List<Customer> customers = generateCustomers(count); //generate Test Data
    save(customers, new File(file));
  }

  private  void save(List<Customer> customers, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json = gson.toJson(customers);
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();
  }

  private static List<Customer> generateCustomers(int count) {
  List<Customer> customers = new ArrayList<Customer>();
  String[] zones = new String[]{"Delaware", "Alabama", "Alaska", "California", "Florida"};
  for (int i =0; i<count; i++ ){
    customers.add(new Customer()
            .withFirstName(String.format("First Name %s", i))
            .withLastName(String.format("Last Name %s", i))
            .withAddress(String.format("Some address %s", i))
            .withPostcode("12345")
            .withCity("Odessa")
            .withCountry("United States")
            .withZone(zones[(int)(Math.random()* zones.length)])
            .withEmail(new RandomStringGenerator.Builder()
                    .withinRange('a', 'z').build().generate(7)+"@example.com") //generate random email
            .withPhone(String.format("+166777%s", i))
            .withPassword(String.format("1234567%s", i))

    );
  }
  return customers;

  }


}
