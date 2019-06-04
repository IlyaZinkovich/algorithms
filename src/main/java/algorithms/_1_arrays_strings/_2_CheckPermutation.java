package algorithms._1_arrays_strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _2_CheckPermutation {

  public static boolean isPermutation(String one, String another) {
    int oneLength = one.length();
    int anotherLength = another.length();
    if (oneLength != anotherLength) {
      return false;
    }
    Map<Character, Integer> charsUsage = new HashMap<>();
    for (int i = 0; i < oneLength; i++) {
      char oneChar = one.charAt(i);
      Integer oneCharUsage = charsUsage.getOrDefault(oneChar, 0);
      charsUsage.put(oneChar, oneCharUsage + 1);
      char anotherChar = another.charAt(i);
      Integer anotherCharUsage = charsUsage.getOrDefault(anotherChar, 0);
      charsUsage.put(anotherChar, anotherCharUsage - 1);
    }
    for (int i : charsUsage.values()) {
      if (i != 0) {
        return false;
      }
    }
    return true;
  }

  public static boolean isPermutationSorting(String one, String another) {
    int oneLength = one.length();
    int anotherLength = another.length();
    if (oneLength != anotherLength) {
      return false;
    }
    char[] oneChars = one.toCharArray();
    char[] anotherChars = another.toCharArray();
    Arrays.sort(oneChars);
    Arrays.sort(anotherChars);
    return Arrays.equals(oneChars, anotherChars);
  }
}
