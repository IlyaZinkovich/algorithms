package algorithms._1_arrays_strings;

public class _7_RotateMatrix {

  public static byte[][] rotate(byte[][] matrix) {
    int n = matrix.length;
    for (int i = 0; i < n; i++) {
      for (int j = i; j < n - i; j++) {
        rotate(matrix, i, j, n);
      }
    }
    return matrix;
  }

  private static void rotate(byte[][] matrix, int i, int j, int n) {
    byte temp1 = matrix[j][n - i - 1];
    matrix[j][n - i - 1] = matrix[i][j];
    byte temp2 = matrix[n - i - 1][n - j - 1];
    matrix[n - i - 1][n - j - 1] = temp1;
    temp1 = matrix[n - j - 1][i];
    matrix[n - j - 1][i] = temp2;
    matrix[i][j] = temp1;
  }
}
