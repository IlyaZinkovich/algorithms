package algorithms._1_arrays_strings;

public class _3_URLify {

  public static char[] urlify(char[] string, int length) {
    int spacesCount = 0;
    for (char character : string) {
      if (character == ' ') {
        spacesCount++;
      }
    }
    int positionInResult = length + spacesCount * 2 - 1;
    int positionInString = length - 1;
    while (positionInResult > 0) {
      char character = string[positionInString];
      if (character != ' ') {
        string[positionInResult] = string[positionInString];
        positionInResult--;
      } else {
        string[positionInResult] = '0';
        positionInResult--;
        string[positionInResult] = '2';
        positionInResult--;
        string[positionInResult] = '%';
        positionInResult--;
      }
      positionInString--;
    }
    return string;

  }
}
