package algorithms._1_arrays_strings;

import static algorithms._1_arrays_strings._1_IsUnique.hasAllUniqueCharacters;
import static algorithms._1_arrays_strings._1_IsUnique.hasAllUniqueCharactersWithoutAdditionalDataStructures;
import static org.quicktheories.QuickTheory.qt;

import java.util.function.Supplier;
import org.junit.jupiter.api.Test;
import org.quicktheories.core.RandomnessSource;
import org.quicktheories.impl.Constraint;

class _1_IsUniqueTest {

  @Test
  void testForAllStringsWithUniqueCharacters() {
    qt()
        .forAll(_1_IsUniqueTest::randomStringsWithUniqueCharacters)
        .check(_1_IsUnique::hasAllUniqueCharacters);
  }

  @Test
  void testForAllStringsWithNonUniqueCharacters() {
    qt()
        .forAll(_1_IsUniqueTest::randomStringsWithNonUniqueCharacter)
        .check(string -> !hasAllUniqueCharacters(string));
  }

  @Test
  void testForAllStringsWithUniqueCharactersWithoutAdditionalDataStructures() {
    qt()
        .forAll(_1_IsUniqueTest::randomStringsWithUniqueCharacters)
        .check(_1_IsUnique::hasAllUniqueCharactersWithoutAdditionalDataStructures);
  }

  @Test
  void testForAllStringsWithNonUniqueCharactersWithoutAdditionalDataStructures() {
    qt()
        .forAll(_1_IsUniqueTest::randomStringsWithNonUniqueCharacter)
        .check(string -> !hasAllUniqueCharactersWithoutAdditionalDataStructures(string));
  }

  private static String randomStringsWithNonUniqueCharacter(final RandomnessSource in) {
    String string = randomStringsWithUniqueCharacters(in);
    char character = string.charAt(randomBoundedInt(in, string.length() - 1));
    return string.replace(character, string.charAt(string.length() - 1));
  }

  private static String randomStringsWithUniqueCharacters(final RandomnessSource in) {
    final char[] characters = characters();
    shuffleArray(characters, () -> randomBoundedInt(in, characters.length));
    return new String(characters);
  }

  private static int randomBoundedInt(RandomnessSource in, long bound) {
    return (int) in.next(Constraint.between(0, bound - 1L));
  }

  private static char[] characters() {
    char[] chars = new char[256];
    for (char i = 0; i < 256; i++) {
      chars[i] = i;
    }
    return chars;
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