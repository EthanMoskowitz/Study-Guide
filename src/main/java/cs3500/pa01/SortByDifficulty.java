package cs3500.pa01;

import java.util.Comparator;

/**
 * Represents a comparator about question difficulty
 */
public class SortByDifficulty implements Comparator<Question> {

  /**
   * Compares two questions
   *
   * @param o1 the first question to be compared.
   * @param o2 the second question to be compared.
   * @return -1, 0, or 1
   */
  @Override
  public int compare(Question o1, Question o2) {
    if (o1.getDifficulty().equals(Difficulty.Hard)) {
      return -1;
    }
    if (o2.getDifficulty().equals(Difficulty.Hard)) {
      return 1;
    } else {
      return 0;
    }
  }
}
