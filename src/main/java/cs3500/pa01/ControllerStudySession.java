package cs3500.pa01;

import static java.lang.Integer.parseInt;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Represents an implementation of a Controller
 */
public class ControllerStudySession implements Controller {
  private StudySessionModel session;
  private Viewer viewer;
  private int total;
  private int current = 0;
  private SessionState state;
  private File fileOrigin;
  private final Readable input;
  private final Appendable output;
  private Scanner scanner;

  ControllerStudySession(SessionState s, Readable i, Appendable o) {
    state = s;
    input = i;
    output = o;
  }

  /**
   * Runs the controller with states
   */
  @Override
  public void run() {
    scanner = new Scanner(input);
    viewer = new ViewerStudySession(output);
    if (state.equals(SessionState.PATHSTART)) {
      viewer.promptPath();
      handleInputs();
      state = SessionState.NUMSTART;
    }
    if (state.equals(SessionState.NUMSTART)) {
      viewer.promptNum();
      handleInputs();
      state = SessionState.QUESTION;
    }
    if (state.equals(SessionState.QUESTION)) {
      questionPhase();
    }
  }

  /**
   * Handles the user inputs depending on current state
   */
  private void handleInputs() {
    while (state.equals(SessionState.PATHSTART)) {
      String path = scanner.nextLine();
      pathStart(path);
    }
    while (state.equals(SessionState.NUMSTART)) {
      int i = 0;
      try {
        i = parseInt(scanner.nextLine());
      } catch (NumberFormatException e) {
        System.out.print("Invalid Input. Provide a Number: ");
      }
      total = i;
      if (total > 0) {
        if (total > session.getList().size()) {
          total = session.getList().size();
        }
        state = SessionState.STANDBY;
      }
    }
    while (state.equals(SessionState.QUESTION)) {
      int i = 0;
      try {
        i = parseInt(scanner.nextLine());
      } catch (NumberFormatException e) {
        System.out.print("Invalid Input. Provide a Number: ");
      }
      menuPhase(i);
    }
  }

  /**
   * Initializes the list of questions from the sr file
   *
   * @param path path to sr file
   */
  private void pathStart(String path) {
    fileOrigin = Path.of(path).toFile();
    if (fileOrigin.exists() && path.endsWith(".sr")) {
      GenerateList generateList = new GenerateList(fileOrigin);
      ArrayList<Question> questionList = generateList.generate();

      Collections.shuffle(questionList);
      SortByDifficulty dif = new SortByDifficulty();
      questionList.sort(dif);

      session = new StudySessionModel(questionList);
      state = SessionState.STANDBY;
    } else {
      System.out.print("Invalid Path. Need a Valid Sr File: ");
    }
  }

  /**
   * Handles the questions
   */
  private void questionPhase() {
    if (current == total) {
      endSession();
    } else {
      Question q = session.getList().get(current);
      String question = q.getQuestion();
      viewer.displayQuestion(current + 1, question);
      viewer.displayMenu();
      handleInputs();
    }
  }

  /**
   * Handles running the menu
   *
   * @param input input from user
   */
  private void menuPhase(int input) {
    if (input == 1) {
      session.updateDifficulty(current, Difficulty.Easy);
      current++;
      questionPhase();
    }
    if (input == 2) {
      session.updateDifficulty(current, Difficulty.Hard);
      current++;
      questionPhase();
    }
    if (input == 3) {
      Question q = session.getList().get(current);
      viewer.displayAnswer(q.getAnswer());
    }
    if (input == 4) {
      endSession();
    }
  }

  /**
   * Ends the session and updates metadata
   */
  private void endSession() {
    String fullString = session.getString();
    FileOutput output = new FileOutput();
    try {
      output.writeFile(fileOrigin, fullString);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    String stats = session.packageStats();
    viewer.displayFinalStats(stats);
    state = SessionState.STANDBY;
  }
}
