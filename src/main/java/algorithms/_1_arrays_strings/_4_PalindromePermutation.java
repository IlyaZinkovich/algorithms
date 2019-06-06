package algorithms._1_arrays_strings;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class _4_PalindromePermutation {

  public static boolean palindromePermutation(String string) {
    Map<Character, Integer> frequencies = new HashMap<>();
    for (int i = 0; i < string.length(); i++) {
      char character = string.charAt(i);
      Integer frequency = frequencies.getOrDefault(character, 0);
      frequencies.put(character, frequency + 1);
    }
    Collection<Integer> frequenciesValues = frequencies.values();
    int numberOfOddFrequencies = 0;
    for (int value : frequenciesValues) {
      if (numberOfOddFrequencies > 1) {
        return false;
      } else if (value % 2 == 1) {
        numberOfOddFrequencies++;
      }
    }
    return (string.length() % 2 == 0 && numberOfOddFrequencies == 0)
        || (string.length() % 2 == 1 && numberOfOddFrequencies == 1);
  }
}
