package algorithms._1_arrays_strings;

import static algorithms._1_arrays_strings._6_StringCompression.compress;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.strings;

import org.junit.jupiter.api.Test;

class _6_StringCompressionTest {

  private static final int MAX_LENGTH = 1000;

  @Test
  void forAllStrings() {
    qt()
        .forAll(strings().basicLatinAlphabet().ofLengthBetween(0, MAX_LENGTH)
            .map(string -> string.replaceAll("[0-9]", "")))
        .check(string -> {
          String compressed = compress(string);
          if (compressed.length() == string.length()) {
            return compressed.equals(string);
          } else {
            return decompress(compressed).equals(string);
          }
        });
  }

  private String decompress(String compressed) {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < compressed.length(); ) {
      char character = compressed.charAt(i);
      StringBuilder numberBuilder = new StringBuilder();
      while (i + 1 < compressed.length() && Character.isDigit(compressed.charAt(i + 1))) {
        numberBuilder.append(compressed.charAt(i + 1));
        i++;
      }
      int number = Integer.parseInt(numberBuilder.toString());
      while (number > 0) {
        builder.append(character);
        number--;
      }
      i++;
    }
    return builder.toString();
  }
}