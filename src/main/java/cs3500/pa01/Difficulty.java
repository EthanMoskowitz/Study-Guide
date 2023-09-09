package cs3500.pa01;

/**
 * Represents difficulty of a question
 */
public enum Difficulty {
  /**
   * If question is hard
   */
  Hard("Hard"),
  /**
   * If question is easy
   */
  Easy("Easy");

  /**
   * Instantiates the difficulty depending on the string
   *
   * @param s either Hard or Easy
   */
  Difficulty(String s) {
  }
}
