package algorithms._1_arrays_strings;

import static algorithms._1_arrays_strings._3_URLify.urlify;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.integers;
import static org.quicktheories.generators.SourceDSL.strings;

import java.util.regex.PatternSyntaxException;
import org.junit.jupiter.api.Test;
import org.quicktheories.core.Gen;

class _3_URLifyTest {

  private static final int MAX_LENGTH = 1000;

  @Test
  void testForAllStringsWithSpaces() {
    qt()
        .forAll(stringsWithSpaces())
        .check(string -> {
          int length = string.length() + numberOfSpaces(string) * 2;
          char[] chars = toCharArrayOfLength(string, length);
          return new String(urlify(chars, string.length())).equals(urlified(chars));
        });
  }

  @Test
  void testForAllStringsWithoutSpaces() {
    qt()
        .forAll(stringsWithoutSpaces())
        .check(string -> {
          int length = string.length() + numberOfSpaces(string) * 2;
          char[] chars = toCharArrayOfLength(string, length);
          return new String(urlify(chars, string.length())).equals(urlified(chars));
        });
  }

  private Gen<String> stringsWithSpaces() {
    return strings().allPossible().ofLengthBetween(0, MAX_LENGTH)
        .flatMap(string -> {
          if (string.isEmpty()) {
            return in -> string;
          } else {
            return integers().allPositive()
                .map(integer -> integer % string.length())
                .map(index -> replaceWithSpace(string, index));
          }
        });
  }

  private Gen<String> stringsWithoutSpaces() {
    return strings().allPossible().ofLengthBetween(0, MAX_LENGTH)
        .map(string -> string.replaceAll(" ", ""));
  }

  private int numberOfSpaces(String string) {
    return string.length() - string.replace(" ", "").length();
  }

  private char[] toCharArrayOfLength(final String string, final int length) {
    char[] chars = new char[length];
    System.arraycopy(string.toCharArray(), 0, chars, 0, string.length());
    return chars;
  }

  private String urlified(final char[] chars) {
    return new String(chars).replaceAll(" ", "%20");
  }

  private String replaceWithSpace(String string, Integer index) {
    try {
      return string.replace(string.charAt(index), ' ');
    } catch (PatternSyntaxException e) {
      return " ";
    }
  }
}