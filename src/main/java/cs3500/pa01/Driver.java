package cs3500.pa01;

import java.io.InputStreamReader;

/**
 * This is the main driver of this project.
 */
public class Driver {

  /**
   * Project entry point
   *
   * @param args should be three arguments: a valid path, a valid string,
   *             and another valid path or zero arguments
   */
  public static void main(String[] args) {
    switch (args.length) {
      case 3 -> {
        Controller c = new ControllerStudyGuide(args[0], args[1], args[2]);
        c.run();
      }
      case 0 -> {
        Controller c = new ControllerStudySession(SessionState.PATHSTART,
            new InputStreamReader(System.in), System.out);
        c.run();
      }
      default -> throw new IllegalArgumentException("Requires 3 or 0 inputs");
    }
  }
}