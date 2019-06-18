package com.letters.distribution;

import java.io.File;
import java.util.ArrayList;

public class UpdateLettersCommand implements LettersCommand {

  private LettersOccurrenceDistribution lettersDistribution;

  public UpdateLettersCommand (LettersOccurrenceDistribution lettersDistribution){
    this.lettersDistribution = lettersDistribution;
  }

  /**
   * Updates the charOccurrences hashmap with the given temporary hashmap to store only the letters with the most
   * occurrences.
   *
   */

  @Override
  public void execute () {
    File file = new File(lettersDistribution.absolutePath);
    ArrayList<String> list;
    for (Character key: lettersDistribution.temporaryCharacterMap.keySet()) {

      if (lettersDistribution.temporaryCharacterMap.get(key) > lettersDistribution.charOccurrences.get(key)) {

        ArrayList<String> valuesList = new ArrayList<>();
        valuesList.add(file.getName());
        lettersDistribution.fileHashMap.put(key,valuesList);
        lettersDistribution.charOccurrences.put(key, lettersDistribution.temporaryCharacterMap.get(key));

      } else if (lettersDistribution.temporaryCharacterMap.get(key).equals(lettersDistribution.charOccurrences.get(key)) && lettersDistribution.temporaryCharacterMap.get(key) != 0) {
        list = lettersDistribution.fileHashMap.get(key);
        list.add(file.getName());
        lettersDistribution.fileHashMap.put(key,list);
        lettersDistribution.charOccurrences.put(key, lettersDistribution.temporaryCharacterMap.get(key));

      } else if(lettersDistribution.temporaryCharacterMap.get(key) == 0 && lettersDistribution.charOccurrences.get(key) == 0) {
        list = new ArrayList<>();
        list.add(LettersOccurrenceDistribution.NO_CHARACTER_FOUND + key);
        lettersDistribution.fileHashMap.put(key,list);
      }
    }
  }
}
