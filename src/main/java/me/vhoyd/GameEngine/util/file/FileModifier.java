package me.vhoyd.GameEngine.util.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileModifier {
  private File file;
  private FileWriter writer;

  public FileModifier(String filepath) throws IOException {
    file = new File(filepath);
    writer = new FileWriter(file);
  }
  /**
  Writes the inputted String into the file and starts a new line. Also see {@link FileModifier.write(String text)}
  @param text - the text to write to the file.
  */
  public void write(String text) throws IOException {
    writer.write(text);
    writer.flush();
  }

  /**
  Writes the inputted String into the file and starts a new line. Also see {@link FileModifier.write(String text)}
  @param text - the text to write to the file.
  */
  public void writeLine(String text) throws IOException {
    writer.write(text+"\n");
    writer.flush();
  }

  /**
  @return - the File object associated with this FileModifier
  */
  public File getFile() {
    return file;
  }

  /**
  @return - whether the file associated with this FileModifier already exists.
  */
  public boolean fileExists() {
    return file.exists();
  }

  /**
  Creates the file associated with the File object this FileModifier uses, which will allow for writing to and reading from the file.
  @return - true if a new file was created, or false if the file already exists.
  */
  public boolean createFile() throws IOException {
    return file.createNewFile();
  }
}