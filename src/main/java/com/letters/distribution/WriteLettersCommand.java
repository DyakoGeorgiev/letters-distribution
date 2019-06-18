package com.letters.distribution;

import org.apache.commons.io.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import static com.axway.java.academy.HW2.Task1.LettersOccurrenceDistribution.MAX_NUMBER_LETTER_DISTRIBUTION_MESSAGE;

public class WriteLettersCommand implements LettersCommand {
  private LettersOccurrenceDistribution lettersDistribution;

  WriteLettersCommand (LettersOccurrenceDistribution lettersDistribution){
    this.lettersDistribution = lettersDistribution;
  }


  /**
   * Writes the fileHashMap (which stores the letters and their filename occurrences).
   * Writes the charOccurrence hashmap (which stores the letters and their number of occurrences.
   */

  @Override
  public void execute () {
    String path = LettersOccurrenceDistribution.PATH_TO_USER_HOME_DIRECTORY + File.separator + LettersOccurrenceDistribution.LETTERS_DISTRIBUTION_DIRECTORY_NAME;
    File directory = new File(path);

    //Check to see if there exists already a directory with the same name, if exists - delete it.
    if(directory.isDirectory()){
      try {
        FileUtils.deleteDirectory(directory);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    //Creating new directory ../distribution
    directory.mkdirs();

    String pathFile = path + File.separator + LettersOccurrenceDistribution.LETTERS_DISTRIBUTION_FILE_NAME;

    try(PrintWriter out = new PrintWriter(
      new BufferedWriter(
        new OutputStreamWriter(
          new FileOutputStream(pathFile), StandardCharsets.UTF_8)))) {
      out.println(MAX_NUMBER_LETTER_DISTRIBUTION_MESSAGE);
      for (Character key : lettersDistribution.charOccurrences.keySet()) {
        out.println(key + " -> " + lettersDistribution.charOccurrences.get(key));
      }

      out.println(LettersOccurrenceDistribution.MAX_NUMBER_LETTER_OCCURRENCES_FILE_MESSAGE);

      lettersDistribution.fileHashMap.forEach((character, fileNames) ->
        out.println(character + " -> " + fileNames.toString().replace("[", "").replace("]", "")));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
