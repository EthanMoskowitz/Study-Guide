package cs3500.pa01;

import java.util.ArrayList;

/**
 * Creates the questions as a string
 */
public class CreateQuestionString {

  /**
   * Creates the string of all the questions
   *
   * @param list list of questions
   * @return formatted string of questions
   */
  public String createString(ArrayList<Question> list) {
    StringBuilder builder = new StringBuilder();
    for (Question q : list) {
      StringBuilder line = new StringBuilder();
      line.append("{Q}");
      line.append(q.getQuestion());
      line.append("{A}");
      line.append(q.getAnswer());
      line.append("{D}");
      line.append(q.getDifficulty().toString());
      line.append('\n');
      builder.append(line);
    }
    return builder.toString();
  }
}
