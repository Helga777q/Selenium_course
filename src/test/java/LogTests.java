import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.TestBase;

import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class LogTests extends TestBase {


  @Test //check logs are collected
  public void logsFilterTests() {
    wd.get("https://software-testing.ru/edu/");
    //filter logs by severe status
    List<LogEntry> logs = wd.manage().logs().get("browser").getAll()
            .stream()
            .filter(l -> l.getLevel().equals(Level.WARNING))
            .collect(Collectors.toList());
    for (LogEntry logEntry: logs){
      System.out.println(logEntry);
    }
  }


  @Test
  public void log(){
    wd.get("https://software-testing.ru/edu/");
    logsAssertion();
  }

  private void logsAssertion() {
    List<LogEntry> logs = wd.manage().logs().get("browser").getAll();
    try{
      Assert.assertTrue(areLogsPresent(logs));
      System.out.println("no browser logs");
    }
    catch(AssertionError e)
    {
      for (LogEntry logEntry: logs){
        System.out.println(e);
        System.out.println(logEntry);
      }
    }
  }


  private boolean areLogsPresent(List <LogEntry> logs){
    if (logs.size()==0){
      return true;
    } else{
      return false;
    }
  }

  @Test
  public void logsAllTests() {
    wd.get("https://software-testing.ru/edu/");
    LogEntries logs = wd.manage().logs().get("browser");
    for (LogEntry logEntry: logs){
      System.out.println(logEntry);
    }
  }






}
