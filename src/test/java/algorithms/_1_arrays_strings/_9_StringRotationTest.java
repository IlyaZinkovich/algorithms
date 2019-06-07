package algorithms._1_arrays_strings;

import static algorithms._1_arrays_strings._9_StringRotation.isRotation;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.integers;
import static org.quicktheories.generators.SourceDSL.strings;

import org.junit.jupiter.api.Test;

class _9_StringRotationTest {

  private static final int MAX_LENGTH = 1000;

  @Test
  void forAllRotations() {
    qt()
        .forAll(
            strings().ascii().ofLengthBetween(0, MAX_LENGTH),
            integers().between(0, MAX_LENGTH - 1)
        )
        .check((string, index) -> {
          int stringIndex = string.isEmpty() ? 0 : index % string.length();
          String rotation = string.substring(stringIndex) + string.substring(0, stringIndex);
          return isRotation(rotation, string);
        });
  }
}