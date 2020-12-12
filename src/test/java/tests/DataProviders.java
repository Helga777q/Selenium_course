package tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import models.Customer;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class DataProviders {
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

}
