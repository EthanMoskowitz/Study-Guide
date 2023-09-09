package cs3500.pa01;

/**
 * Represents a question, answer, and the metadata included
 */
public class Question {
  private final String question;
  private final String answer;
  private Difficulty difficulty;

  /**
   * Instantiates a question
   *
   * @param q given question
   * @param a given answer
   * @param d given difficulty
   */
  Question(String q, String a, String d) {
    question = q;
    answer = a;
    difficulty = Difficulty.valueOf(d);
  }

  /**
   * Gets the question
   *
   * @return the question
   */
  public String getQuestion() {
    return question;
  }

  /**
   * Gets the answer
   *
   * @return the answer
   */
  public String getAnswer() {
    return answer;
  }

  /**
   * Gets the difficulty
   *
   * @return the difficulty
   */
  public Difficulty getDifficulty() {
    return difficulty;
  }

  /**
   * Sets the difficulty to given difficulty
   *
   * @param d difficulty to change to
   */
  public void setDifficulty(Difficulty d) {
    difficulty = d;
  }

}
