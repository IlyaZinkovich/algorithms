package algorithms._1_arrays_strings;

public class _5_OneAway {

  public static boolean oneAway(String one, String another) {
    int oneLength = one.length();
    int anotherLength = another.length();
    int lengthDifference = Math.abs(oneLength - anotherLength);
    if (lengthDifference == 1 && (oneLength == 0 || another.length() == 0)) {
      return true;
    } else if (lengthDifference == 0) {
      int replacedCharacters = 0;
      for (int i = 0; i < oneLength; i++) {
        if (replacedCharacters > 1) {
          return false;
        } else if (one.charAt(i) != another.charAt(i)) {
          replacedCharacters++;
        }
      }
      return replacedCharacters <= 1;
    } else if (lengthDifference == 1) {
      int i = 0;
      while (one.charAt(i) == another.charAt(i)) {
        i++;
      }
      if (oneLength > anotherLength) {
        for (int j = i; j < anotherLength; j++) {
          if (one.charAt(j + 1) != another.charAt(j)) {
            return false;
          }
        }
      } else {
        for (int j = i; j < oneLength; j++) {
          if (another.charAt(j + 1) != one.charAt(j)) {
            return false;
          }
        }
      }
      return true;
    } else {
      return false;
    }
  }
}
