package cs3500.pa01;

import java.io.IOException;

/**
 * Represents a Viewer
 */
public class ViewerStudySession implements Viewer {
  private Appendable appendable;

  ViewerStudySession(Appendable a) {
    appendable = a;
  }

  /**
   * Welcomes the user and prompts them for a path
   */
  public void promptPath() {
    try {
      appendable.append("Welcome!\n");
      appendable.append("To Start, Please Provide a Full Path to an Sr File: ");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Asks the user for a number of questions
   */
  public void promptNum() {
    try {
      appendable.append("\n");
      appendable.append("Please Provide How Many Questions You Would Like: ");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Displays the question
   *
   * @param num question number
   * @param question question to display
   */
  public void displayQuestion(int num, String question) {
    try {
      appendable.append("\n");
      appendable.append(String.valueOf(num)).append(". ").append(question).append("\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Displays the menu
   */
  public void displayMenu() {
    try {
      appendable.append("1. Set to Easy  ");
      appendable.append("2. Set to Hard  ");
      appendable.append("3. Show Answer  ");
      appendable.append("4. Exit  ");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Displays the answer
   *
   * @param answer string of answer of question
   */
  public void displayAnswer(String answer) {
    try {
      appendable.append("Answer: ").append(answer);
      appendable.append("\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    displayMenu();
  }

  /**
   * Displays the final stats
   *
   * @param stats total stats for the session
   */
  public void displayFinalStats(String stats) {
    try {
      appendable.append("\n");
      appendable.append(stats);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
