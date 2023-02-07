package me.vhoyd.GameEngine.util.file;

import java.io.File;

/**
  Simple class for lazily reading and writing to files without having to add try-catch blocks.  
*/
public class LazyFileHandler {
  private FileModifier writer;
  private FileParser reader;
  private boolean valid = true;

  public LazyFileHandler(String filepath) {
    try {
      writer = new FileModifier(filepath);
      writer.createFile();
      reader = new FileParser(filepath);
    } catch (Exception e) {
      valid = false;
    } 
  }

  /**
  Checks if this LazyFileHandler was constructed successfully. This means that both the reading and writing aspects were prepared and the file is ready to modify and observe.
  @return - whether or not this LazyFileHandler was constructed successfully.
  */
  public boolean isValid() {
    return valid;
  }

  /**
  Reads the next line in the target file.
  @return - the next line, or null if there is no next line or the reading failed.
  */
  public String readNextLine() {
    try {
      return reader.nextLine();
    } catch (Exception e) {
      return null;
    }
  }

  /**
  Resets the reading point to the beginning of the file.
  @return - whether the reader was successfully reset or not.
  */
  public boolean resetReader() {
    try {
      reader.reset();
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
  Searches the file for the inputted String.
  @param query - the String to filter by.
  @return - the first String found containing the query, or null if not found or the reading was interrupted.
  */
  public String findLine(String query) {
    try {
      return reader.findLine(query);
    } catch (Exception e) {
      return null;
    }
  }

  /**
  @return - the File object associated with this LazyFileHandler.
  */
  public File getFile() {
    return reader.getFile();
  }

  /**
  Writes the inputted text to the file. Also see {@link LazyFileHandler.writeLine(String text)}
  @param text - the text to write.
  @return - true if the writing was successful, false if the writing was interrupted.
  */
  public boolean write(String text) {
    try {
      writer.write(text);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
  Writes the inputted text to the file and starts a new line in the file. Also see {@link LazyFileHandler}.write(String text)}
  @param text - the text to write.
  @return - true if the writing was successful, fase if the writing was interrupted.
  */
  public boolean writeLine(String text) {
    try {
      writer.writeLine(text);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}