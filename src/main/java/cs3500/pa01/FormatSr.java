package cs3500.pa01;

/**
 * Represents an object that formats a string to be like a SR file
 */
public class FormatSr extends FormatFile {

  /**
   * Instantiates a FormatFile
   *
   * @param s Given String to format
   */
  FormatSr(String s) {
    super(s);
  }

  /**
   * Formats the string into Sr format
   *
   * @return formatted text
   */
  @Override
  public String formatString() {
    StringBuilder builder = new StringBuilder();

    for (int i = 0; i < text.length(); i++) {
      Character c = text.charAt(i);
      Character next = getNext(i);

      if (c.equals('[') && next.equals('[')) {
        StringBuilder phrase = new StringBuilder();
        phrase.append("{Q}");
        phrase.append(createImportant(i));
        if (phrase.toString().contains(":::")) {
          int index = phrase.indexOf(":::");
          if (phrase.charAt(index - 1) == ' ') {
            phrase.replace(index - 1, index, "");
          }
          if (phrase.charAt(index + 2) == ' ') {
            phrase.replace(index + 2, index + 3, "");
          }
          String s = phrase.toString().replace(":::", "{A}");
          phrase.replace(0, phrase.length(), s);
          phrase.replace(phrase.length() - 1, phrase.length(), "");
          phrase.append("{D}Hard\n");
          builder.append(phrase);
        }
      }
    }
    return builder.toString();
  }
}
