package cs3500.pa01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents an object that generates the list of questions
 */
public class GenerateList {
  File file;

  /**
   * Instantiates a generate list
   *
   * @param f sr file to get question from
   */
  GenerateList(File f) {
    file = f;
  }

  /**
   * Generates the list
   *
   * @return list of questions
   */
  public ArrayList<Question> generate() {
    ArrayList<Question> list = new ArrayList<>();
    Scanner scanner;
    try {
      scanner = new Scanner(new FileInputStream(file));
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    while (scanner.hasNextLine()) {
      String string = scanner.nextLine();
      String question = "";
      String answer = "";
      String difficulty = "";

      for (int i = 0; i < string.length(); i++) {
        Character c = string.charAt(i);
        Character next = getNext(i, string);
        Character next2 = getNext(i + 1, string);
        if (c.equals('{') && next.equals('Q') && next2.equals('}')) {
          question = getContent(i + 3, string);
        }
        if (c.equals('{') && next.equals('A') && next2.equals('}')) {
          answer = getContent(i + 3, string);
        }
        if (c.equals('{') && next.equals('D') && next2.equals('}')) {
          difficulty = getContent(i + 3, string);
        }
      }
      Question q = new Question(question, answer, difficulty);
      list.add(q);
    }
    return list;
  }

  /**
   * Gets the content of either a question, answer or difficulty
   *
   * @param index starting index of content
   * @param s string to get content from
   * @return content of string
   */
  private String getContent(int index, String s) {
    String content = "";
    for (int i = index; i < s.length(); i++) {
      Character c = s.charAt(i);
      Character next2 = getNext(i + 1, s);
      if (c.equals('{') && next2.equals('}') || c.equals('\n')) {
        break;
      }
      content = content + c;
    }
    return content;
  }

  /**
   * Gets next character
   *
   * @param i index of current character
   * @return next character
   */
  private char getNext(int i, String s) {
    if (!(i >= s.length() - 1)) {
      return s.charAt(i + 1);
    } else {
      return ' ';
    }
  }
}
