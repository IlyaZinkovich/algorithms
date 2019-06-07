package algorithms._1_arrays_strings;

public class _8_ZeroMatrix {

  public static byte[][] zeroMatrix(byte[][] matrix) {
    int n = matrix.length;
    int m = matrix[0].length;
    boolean[] zeroRows = new boolean[n];
    boolean[] zeroColumns = new boolean[m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (matrix[i][j] == 0) {
          zeroRows[i] = true;
          zeroColumns[j] = true;
        }
      }
    }
    nullifyRows(matrix, n, m, zeroRows);
    nullifyColumns(matrix, n, m, zeroColumns);
    return matrix;
  }

  private static void nullifyColumns(byte[][] matrix, int n, int m, boolean[] zeroColumns) {
    for (int j = 0; j < m; j++) {
      if (zeroColumns[j]) {
        for (int i = 0; i < n; i++) {
          matrix[i][j] = 0;
        }
      }
    }
  }

  private static void nullifyRows(byte[][] matrix, int n, int m, boolean[] zeroRows) {
    for (int i = 0; i < n; i++) {
      if (zeroRows[i]) {
        for (int j = 0; j < m; j++) {
          matrix[i][j] = 0;
        }
      }
    }
  }
}
