package algorithms._1_arrays_strings;

import static algorithms._1_arrays_strings._2_CheckPermutation.isPermutation;
import static algorithms._1_arrays_strings._2_CheckPermutation.isPermutationSorting;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.strings;

import java.util.Random;
import java.util.function.Supplier;
import org.junit.jupiter.api.Test;

class _2_CheckPermutationTest {

  private static final Random RNG = new Random();
  private static final int MAX_LENGTH = 1000;

  @Test
  void testForAllStringsAndTheirPermutations() {
    qt()
        .forAll(strings().allPossible().ofLengthBetween(0, MAX_LENGTH))
        .check(string -> isPermutation(string, shuffle(string)));
  }

  @Test
  void testForAllDifferentStrings() {
    qt()
        .forAll(
            strings().allPossible().ofLengthBetween(0, MAX_LENGTH),
            strings().allPossible().ofLengthBetween(0, MAX_LENGTH)
        )
        .check((one, another) -> one.equals(another) || !isPermutation(one, another));
  }

  @Test
  void testForAllStringsAndTheirPermutationsSorting() {
    qt()
        .forAll(strings().allPossible().ofLengthBetween(0, MAX_LENGTH))
        .check(string -> isPermutationSorting(string, shuffle(string)));
  }

  @Test
  void testForAllDifferentStringsSorting() {
    qt()
        .forAll(
            strings().allPossible().ofLengthBetween(0, MAX_LENGTH),
            strings().allPossible().ofLengthBetween(0, MAX_LENGTH)
        )
        .check((one, another) -> one.equals(another) || !isPermutationSorting(one, another));
  }

  private static String shuffle(String source) {
    final char[] chars = source.toCharArray();
    shuffleArray(chars, () -> RNG.nextInt(MAX_LENGTH));
    return new String(chars);
  }

  private static void shuffleArray(char[] array, Supplier<Integer> randomInteger) {
    for (int i = array.length - 1; i > 0; i--) {
      int index = randomInteger.get() % (i + 1);
      char a = array[index];
      array[index] = array[i];
      array[i] = a;
    }
  }
}