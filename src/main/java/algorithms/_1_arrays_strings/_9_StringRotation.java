package algorithms._1_arrays_strings;

public class _9_StringRotation {

  public static boolean isRotation(String one, String another) {
    if (one.length() != another.length()) {
      return false;
    } else if (one.isEmpty()) {
      return true;
    }
    return isSubstring(one, another + another);
  }

  private static boolean isSubstring(String one, String another) {
    return another.replace(one, "").length() != another.length();
  }
}
