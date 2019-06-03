package algorithms._1_arrays_strings;

import java.util.Arrays;

public class _1_IsUnique {

  private static final int EXTENDED_ASCII_CHARSET_LENGTH = 256;

  static boolean hasAllUniqueCharacters(String string) {
    int length = string.length();
    if (length > EXTENDED_ASCII_CHARSET_LENGTH) {
      return false;
    }
    boolean[] usedChars = new boolean[EXTENDED_ASCII_CHARSET_LENGTH];
    for (int i = 0; i < length; i++) {
      final char character = string.charAt(i);
      if (usedChars[character]) {
        return false;
      } else {
        usedChars[character] = true;
      }
    }
    return true;
  }

  static boolean hasAllUniqueCharactersWithoutAdditionalDataStructures(String string) {
    if (string.length() < 1) {
      return true;
    }
    if (string.length() > EXTENDED_ASCII_CHARSET_LENGTH) {
      return false;
    }
    char[] chars = string.toCharArray();
    Arrays.sort(chars);
    for (int i = 0; i < chars.length - 1; i++) {
      if (chars[i] == chars[i + 1]) {
        return false;
      }
    }
    return true;
  }
}
