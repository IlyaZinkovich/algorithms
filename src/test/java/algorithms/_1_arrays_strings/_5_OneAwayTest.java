package algorithms._1_arrays_strings;

import static algorithms._1_arrays_strings._5_OneAway.oneAway;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.characters;
import static org.quicktheories.generators.SourceDSL.integers;
import static org.quicktheories.generators.SourceDSL.strings;

import org.junit.jupiter.api.Test;

class _5_OneAwayTest {

  private static final int MAX_LENGTH = 1000;

  @Test
  void oneAwayWithInsertedCharacter() {
    qt()
        .forAll(
            strings().allPossible().ofLengthBetween(0, MAX_LENGTH),
            integers().between(0, MAX_LENGTH - 1),
            characters().ascii())
        .check((string, index, character) -> oneAway(string, insert(string, index, character)));
  }

  @Test
  void oneAwayWithRemovedCharacter() {
    qt()
        .forAll(
            strings().allPossible().ofLengthBetween(1, MAX_LENGTH),
            integers().between(0, MAX_LENGTH - 1))
        .check((string, index) -> oneAway(string, remove(string, index)));
  }

  @Test
  void oneAwayWithReplacedCharacter() {
    qt()
        .forAll(
            strings().allPossible().ofLengthBetween(1, MAX_LENGTH),
            integers().between(0, MAX_LENGTH - 1),
            characters().ascii())
        .check((string, index, character) -> oneAway(string, replace(string, index, character)));
  }

  private String replace(final String string, final Integer index, final Character character) {
    int indexInString = index % string.length();
    return string.substring(0, indexInString) + character + string.substring(indexInString + 1);
  }

  private String insert(final String string, final Integer index, final Character character) {
    if (string.isEmpty()) {
      return character.toString();
    }
    int indexInString = index % string.length();
    return string.substring(0, indexInString) + character + string.substring(indexInString);
  }

  private String remove(final String string, final Integer index) {
    int indexInString = index % string.length();
    return string.substring(0, indexInString) + string.substring(indexInString);
  }
}