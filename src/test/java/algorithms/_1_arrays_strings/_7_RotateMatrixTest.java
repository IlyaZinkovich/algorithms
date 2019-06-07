package algorithms._1_arrays_strings;

import static algorithms._1_arrays_strings._7_RotateMatrix.rotate;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.integers;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.quicktheories.core.Gen;
import org.quicktheories.core.RandomnessSource;
import org.quicktheories.impl.Constraint;

class _7_RotateMatrixTest {

  @Test
  void testForAll() {
    qt()
        .forAll(matrices())
        .check(matrix -> {
          byte[][] original = copyMatrix(matrix);
          return Arrays.deepEquals(original, rotate(rotate(rotate(rotate(matrix)))));
        });
  }

  private Gen<byte[][]> matrices() {
    return integers().between(1, 100).flatMap(n -> in -> matrix(n, in));
  }

  private byte[][] matrix(final Integer n, final RandomnessSource in) {
    byte[][] matrix = new byte[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        matrix[i][j] = (byte) in.next(Constraint.between(Byte.MIN_VALUE, Byte.MAX_VALUE));
      }
    }
    return matrix;
  }

  private static byte[][] copyMatrix(byte[][] src) {
    int length = src.length;
    byte[][] target = new byte[length][src[0].length];
    for (byte i = 0; i < length; i++) {
      System.arraycopy(src[i], 0, target[i], 0, src[i].length);
    }
    return target;
  }
}