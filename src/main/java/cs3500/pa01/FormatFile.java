package cs3500.pa01;

/**
 * Abstract class for formatting md and sr files
 */
public abstract class FormatFile {
  String text;

  /**
   * Instantiates a FormatFile
   *
   * @param s Given String to format
   */
  FormatFile(String s) {
    text = s;
  }

  /**
   * Formats the String properly
   *
   * @return formatted string
   */
  public abstract String formatString();

  /**
   * Gets previous character from text
   *
   * @param i index of current character
   * @return previous character
   */
  public char getPrev(int i) {
    if (i != 0) {
      return text.charAt(i - 1);
    } else {
      return ' ';
    }
  }

  /**
   * Gets next character
   *
   * @param i index of current character
   * @return next character
   */
  public char getNext(int i) {
    if (i != text.length() - 1) {
      return text.charAt(i + 1);
    } else {
      return ' ';
    }
  }

  /**
   * Creates important phrases from md file
   *
   * @param index index of the start of the important phrase
   * @return StringBuilder of important phrase
   */
  public StringBuilder createImportant(int index) {
    StringBuilder phrase = new StringBuilder();
    for (int i = index; i < text.length(); i++) {
      Character c = text.charAt(i);
      Character next = getNext(i);
      Character prev = getPrev(i);
      if (c.equals(']') && next.equals(']')) {
        phrase.append('\n');
        break;
      }
      if (!c.equals('\n') && !(c.equals('[') && (prev.equals('[') || next.equals('[')))) {
        phrase.append(c);
      }
    }
    return phrase;
  }

}
