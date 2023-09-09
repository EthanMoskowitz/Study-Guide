package cs3500.pa01;

import java.util.ArrayList;

/**
 * Represents a study session
 */
public class StudySessionModel {
  private ArrayList<Question> list;
  private int answered = 0;
  private int toHard = 0;
  private int toEasy = 0;

  /**
   * Instantiates a study session
   *
   * @param q random sorted list of questions
   */
  StudySessionModel(ArrayList<Question> q) {
    list = q;
  }

  /**
   * Updates the difficulty of a question
   *
   * @param index index of the question to change
   * @param d difficulty to change to
   */
  public void updateDifficulty(int index, Difficulty d) {
    answered++;
    Question temp = list.get(index);
    if (temp.getDifficulty().equals(Difficulty.Hard) && d.equals(Difficulty.Easy)) {
      toEasy++;
    }
    if (temp.getDifficulty().equals(Difficulty.Easy) && d.equals(Difficulty.Hard)) {
      toHard++;
    }
    list.get(index).setDifficulty(d);
  }

  /**
   * Gets all the questions as a string
   *
   * @return question string
   */
  public String getString() {
    CreateQuestionString create = new CreateQuestionString();
    return create.createString(list);
  }

  /**
   * Gets total hard questions
   *
   * @return number of hard questions
   */
  private int setTotalHard() {
    int totalHard = 0;
    for (Question q : list) {
      if (q.getDifficulty().equals(Difficulty.Hard)) {
        totalHard++;
      }
    }
    return totalHard;
  }

  /**
   * Gets total easy questions
   *
   * @return number of easy questions
   */
  private int setTotalEasy() {
    int totalEasy = 0;
    for (Question q : list) {
      if (q.getDifficulty().equals(Difficulty.Easy)) {
        totalEasy++;
      }
    }
    return totalEasy;
  }

  /**
   * Packages all the session stats
   *
   * @return string of final session stats
   */
  public String packageStats() {
    return """
        Questions Answered:\s""" + answered + """
        \nQuestions Changed From Easy to Hard:\s""" + toHard + """
        \nQuestions Changed From Hard to Easy:\s""" + toEasy + """
        \nTotal Hard Questions:\s""" + setTotalHard() + """
        \nTotal Easy Questions:\s""" + setTotalEasy();
  }

  /**
   * Gets the list of questions
   *
   * @return list of questions
   */
  public ArrayList<Question> getList() {
    return list;
  }

}
