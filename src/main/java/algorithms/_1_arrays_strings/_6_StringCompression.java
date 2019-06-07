package algorithms._1_arrays_strings;

public class _6_StringCompression {

  public static String compress(String string) {
    if (string.isEmpty()) {
      return string;
    }
    char lastChar = string.charAt(0);
    int number = 0;
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < string.length() - 1; i++) {
      if (lastChar == string.charAt(i + 1)) {
        number++;
      } else {
        builder.append(lastChar);
        builder.append(number + 1);
        lastChar = string.charAt(i + 1);
        number = 0;
      }
    }
    builder.append(lastChar);
    builder.append(number + 1);
    String compressed = builder.toString();
    return compressed.length() >= string.length() ? string : compressed;
  }
}
