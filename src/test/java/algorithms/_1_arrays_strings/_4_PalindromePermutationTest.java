package algorithms._1_arrays_strings;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.strings;

import java.util.function.Supplier;
import org.junit.jupiter.api.Test;
import org.quicktheories.core.Gen;
import org.quicktheories.core.RandomnessSource;
import org.quicktheories.impl.Constraint;

class _4_PalindromePermutationTest {

  private static final int MAX_LENGTH = 1000;

  @Test
  void testForAllPalindromePermutations() {
    qt()
        .forAll(palindromePermutations())
        .check(_4_PalindromePermutation::palindromePermutation);
  }

  @Test
  void testForAllNonPalindromePermutations() {
    qt()
        .forAll(nonPalindromePermutations())
        .check(string -> string.length() < 2
            || !_4_PalindromePermutation.palindromePermutation(string));
  }

  private Gen<String> nonPalindromePermutations() {
    return palindromePermutations().map(string -> {
      if (string.isEmpty()) {
        return string;
      } else if (string.length() % 2 == 0) {
        char stringCharacter = string.charAt(0);
        char differentCharacter = (char) (stringCharacter + 1);
        return stringCharacter + string + differentCharacter;
      } else {
        char stringCharacter = string.charAt(0);
        char differentCharacter = (char) (stringCharacter + 1);
        return stringCharacter + string + string + differentCharacter;
      }
    });
  }

  private Gen<String> palindromePermutations() {
    return strings().allPossible().ofLengthBetween(0, MAX_LENGTH)
        .map(string -> string + string)
        .flatMap(string -> in -> shuffle(string, indexRNG(string, in)));
  }

  private Supplier<Integer> indexRNG(final String string, final RandomnessSource in) {
    return () -> (int) in.next(Constraint.between(0, string.length() - 1));
  }

  private static String shuffle(String text, Supplier<Integer> rng) {
    char[] characters = text.toCharArray();
    for (int i = 0; i < characters.length; i++) {
      int randomIndex = rng.get();
      char temp = characters[i];
      characters[i] = characters[randomIndex];
      characters[randomIndex] = temp;
    }
    return new String(characters);
  }
}