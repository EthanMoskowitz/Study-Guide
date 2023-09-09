package cs3500.pa01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Represents a controller for creating a study guide
 */
public class ControllerStudyGuide implements Controller {
  private static final String fileType = ".md";
  private static Path input;
  private static String flag;
  private static Path output;

  /**
   * Instantiates the study guide
   *
   * @param i path to input file
   * @param f flag to sort files by
   * @param o output file to create study guide to
   */
  ControllerStudyGuide(String i, String f, String o) {
    input = Path.of(i);
    flag = f;
    output = Path.of(o);
  }

  /**
   * Runs the controller
   */
  @Override
  public void run() {
    checkInputs();
    createStudyGuide();
  }

  /**
   * Checks if the given inputs are valid for the study guide
   */
  private static void checkInputs() {
    if (!flag.equals("filename") && !flag.equals("created")
        && !flag.equals("modified")) {
      throw new IllegalArgumentException("Invalid Flag");
    }

    if (!output.toString().endsWith(fileType)) {
      throw new IllegalArgumentException("Invalid File");
    }

  }

  /**
   * Creates the study guide and questions guide
   */
  private static void createStudyGuide() {
    // visits files and makes list of markdown files
    MarkDownFile mark = new MarkDownFile(fileType);
    try {
      Files.walkFileTree(input, mark);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    ArrayList<MdFile> fileList = mark.getList();

    // sorts files depending on given flag
    Sorter sort = new Sorter(flag, fileList);
    fileList = sort.sortList();

    // turns MdFiles back into list of normal files
    ArrayList<File> files = new ArrayList<>();
    for (MdFile f : fileList) {
      files.add(f.getFile());
    }

    // converts file content to string
    FilesToString fileString = new FilesToString(files);
    String guide = fileString.convert();

    // formats string into markdown file format
    FormatMd formatterMd = new FormatMd(guide);
    FormatSr formatterSr = new FormatSr(guide);
    String guideMd = formatterMd.formatString();
    String questionSr = formatterSr.formatString();

    // writes the text into the new file
    File outputMd = new File(output.toString());
    String toSr = output.toString();
    String srPath = toSr.replace(".md", ".sr");
    File outputSr = new File(srPath);
    FileOutput outFile = new FileOutput();
    try {
      outFile.writeFile(outputMd, guideMd);
      outFile.writeFile(outputSr, questionSr);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

}
