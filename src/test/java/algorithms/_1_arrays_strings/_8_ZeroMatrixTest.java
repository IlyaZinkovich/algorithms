package algorithms._1_arrays_strings;

import static algorithms._1_arrays_strings._8_ZeroMatrix.zeroMatrix;
import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.integers;

import org.junit.jupiter.api.Test;
import org.quicktheories.core.Gen;
import org.quicktheories.core.RandomnessSource;
import org.quicktheories.impl.Constraint;

class _8_ZeroMatrixTest {

  @Test
  void forAllMatrices() {
    qt()
        .forAll(matrices())
        .check(matrix -> {
          int expectedZeroRows = expectedZeroRows(matrix);
          int expectedZeroColumns = expectedZeroColumns(matrix);
          byte[][] zeroMatrix = zeroMatrix(matrix);
          int zeroRows = countZeroRows(zeroMatrix);
          int zeroColumns = countZeroColumns(zeroMatrix);
          return expectedZeroRows == zeroRows
              && expectedZeroColumns == zeroColumns;
        });
  }

  private int expectedZeroRows(final byte[][] matrix) {
    int expectedZeroRows = 0;
    for (byte[] row : matrix) {
      for (byte element : row) {
        if (element == 0) {
          expectedZeroRows++;
          break;
        }
      }
    }
    return expectedZeroRows;
  }

  private int countZeroRows(final byte[][] zeroMatrix) {
    int zeroRows = 0;
    for (byte[] row : zeroMatrix) {
      int zerosInRow = countZerosInRow(row);
      if (zerosInRow == row.length) {
        zeroRows++;
      }
    }
    return zeroRows;
  }

  private int expectedZeroColumns(final byte[][] matrix) {
    int expectedZeroColumns = 0;
    for (int j = 0; j < matrix[0].length; j++) {
      for (byte[] row : matrix) {
        if (row[j] == 0) {
          expectedZeroColumns++;
          break;
        }
      }
    }
    return expectedZeroColumns;
  }

  private int countZerosInRow(byte[] zeroMatrix) {
    int zerosInRow = 0;
    for (byte element : zeroMatrix) {
      if (element == 0) {
        zerosInRow++;
      }
    }
    return zerosInRow;
  }

  private int countZeroColumns(final byte[][] matrix) {
    int zeroColumns = 0;
    for (int j = 0; j < matrix[0].length; j++) {
      int zerosInRow = 0;
      for (byte[] row : matrix) {
        if (row[j] == 0) {
          zerosInRow++;
        }
      }
      if (zerosInRow == matrix.length) {
        zeroColumns++;
      }
    }
    return zeroColumns;
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
}
