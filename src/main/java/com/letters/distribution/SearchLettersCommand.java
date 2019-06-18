package com.letters.distribution;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SearchLettersCommand implements LettersCommand {

  private LettersOccurrenceDistribution lettersDistribution;

  public SearchLettersCommand (LettersOccurrenceDistribution lettersDistribution) {
    this.lettersDistribution = lettersDistribution;
  }

  /**
   * Performs searching in a file for letters distribution. Stores the result for the given file in a temporary hashmap.
   *
   */
  @Override
  public void execute () {
    File file = new File(lettersDistribution.absolutePath);

    try (Scanner in = new Scanner(file)) {

      while (in.hasNext()) {
        String inputString = in.nextLine().toLowerCase();
        inputString = inputString.replace(" ", "");
        char[] strArray = inputString.toCharArray();

        for (char c : strArray) {
          if (Character.isLetter(c)) {
            lettersDistribution.temporaryCharacterMap.put(c, lettersDistribution.temporaryCharacterMap.get(c) + 1);
          }
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
