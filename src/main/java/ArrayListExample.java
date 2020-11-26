import java.io.UnsupportedEncodingException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ArrayListExample
{
  public static void main(String[] args) throws UnsupportedEncodingException {




    ArrayList<String> listOne = new ArrayList<>(Arrays.asList("A", "b", "c", "d", "f"));

    ArrayList<String> listTwo = new ArrayList<>(Arrays.asList("Ä", "b", "f", "d", "c"));
    ArrayList<String> normalized = new ArrayList<>();

    //Collections.sort(listOne);
    for (String el: listTwo){
      normalized.add(normalizedString(el));
    }
    Collections.sort(normalized);

    for (String el: normalized){
      System.out.println(el);
    }

    //Compare unequal lists example

    boolean isEqual = listOne.equals(normalized);      //false
    System.out.println(isEqual);



  }


  private static String normalizedString(String s) throws UnsupportedEncodingException {
    String s1 = Normalizer.normalize(s, Normalizer.Form.NFKD);
    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
    return pattern.matcher(s1).replaceAll("");
  }
}