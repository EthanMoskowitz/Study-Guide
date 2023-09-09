package cs3500.pa01;

/**
 * Represents an object that formats a string to be like a MarkDown file
 */
public class FormatMd extends FormatFile {
  /**
   * Instantiates a FormatFile
   *
   * @param s Given string to format
   */
  FormatMd(String s) {
    super(s);
  }

  /**
   * Formats the string into Markdown format
   *
   * @return formatted text
   */
  @Override
  public String formatString() {
    StringBuilder builder = new StringBuilder();
    boolean header = false;

    for (int i = 0; i < text.length(); i++) {
      Character c = text.charAt(i);
      Character next;
      next = getNext(i);
      Character prev;
      prev = getPrev(i);
      if (c.equals('#')) {
        header = true;
        if (!prev.equals(' ') && !prev.equals('#')) {
          builder.append('\n');
        }
      }
      if (header && c.equals('\n')) {
        builder.append(c);
        header = false;
      }
      if (header) {
        builder.append(c);
      }
      if (c.equals('[') && next.equals('[')) {
        StringBuilder phrase = new StringBuilder();
        phrase.append("- ");
        phrase.append(createImportant(i));
        if (!phrase.toString().contains(":::")) {
          builder.append(phrase);
        }
      }
    }
    return builder.toString();
  }
}

