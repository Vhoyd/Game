package me.vhoyd.GameEngine.util.file;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileParser {
  private File file;
  private BufferedReader reader;
  private long line = 0L;
  private boolean wasReset;
  public FileParser(String filepath) throws IOException {
    file = new File(filepath);
    if (!file.exists()) {
      throw new FileNotFoundException("File not found: "+filepath);
    }
    if (!file.canRead()) {
      throw new FileNotFoundException("Cannot read file "+filepath);
    }
    reader = new BufferedReader(new FileReader(file));
  }

  /**
  @return - the next line in the file.
  */
  public String nextLine() throws IOException {
    line++;
    if (wasReset) {
      for (long i = 0; i < line; i++) {
        reader.readLine();
      }
    }
    return reader.readLine();
  }

  /**
  Resets the reading point back to the beginning of the file.
  */
  public void reset() throws IOException {
    line = 0L;
    reader = new BufferedReader(new FileReader(file));
  }

  /**
  Searches the file for the given String.
  @return - the String containing the first found query, or null if not found.
  @param query - the String to filter by.
  */
  public String findLine(String query) throws IOException {
    reader = new BufferedReader(new FileReader(file));
    wasReset = true;
    for (String line; (line = reader.readLine()) != null; ) {
      if (line.contains(query)) {
        reader = new BufferedReader(new FileReader(file));
        return line;
      }
    }
    reader = new BufferedReader(new FileReader(file));
    return null;
  }

  /**
  @return - the File this FileParser is reading from.
  */
  public File getFile() {
    return file;
  }
}