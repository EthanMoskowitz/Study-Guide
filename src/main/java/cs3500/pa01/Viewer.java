package cs3500.pa01;

/**
 * Represents an interface for a viewer
 */
public interface Viewer {

  /**
   * Welcomes the user and prompts them for a path
   */
  void promptPath();

  /**
   * Asks the user for a number of questions
   */
  void promptNum();

  /**
   * Displays the question
   *
   * @param num question number
   * @param question question to display
   */
  void displayQuestion(int num, String question);

  /**
   * Displays the menu
   */
  void displayMenu();

  /**
   * Displays the answer
   *
   * @param answer string of answer of question
   */
  void displayAnswer(String answer);

  /**
   * Displays the final stats
   *
   * @param stats total stats for the session
   */
  void displayFinalStats(String stats);
}
