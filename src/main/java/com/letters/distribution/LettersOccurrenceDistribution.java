package com.letters.distribution;

import java.io.File;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class LettersOccurrenceDistribution implements LettersDistribution{

  private final String DIRECTORY_PATH_ERROR_MESSAGE = "You have to provide existing directory absolutePath with .txt files in it.";
  private final String FILE_TXT_ENDING = ".txt";
  private boolean isTxtFound = false;
  static final String NO_CHARACTER_FOUND = "NO_FILES_CONTAINING_LETTER_";
  static final String PATH_TO_USER_HOME_DIRECTORY = System.getProperty("user.home");
  static final String LETTERS_DISTRIBUTION_DIRECTORY_NAME = "distribution";
  static final String LETTERS_DISTRIBUTION_FILE_NAME = "letters_distribution.txt";
  static final String MAX_NUMBER_LETTER_DISTRIBUTION_MESSAGE = "Maximum number of letter distribution";
  static final String MAX_NUMBER_LETTER_OCCURRENCES_FILE_MESSAGE = "Maximum number of letter occurrences:";


  /**
   * HashMap that contains all the alphabet characters as keys.
   * And List of Strings as values that will contain the file names.
   *
   **/
  HashMap<Character, ArrayList<String>> fileHashMap = new HashMap<>(){
    {
      ArrayList<String> valuesList = new ArrayList<>();
      valuesList.add("");
      for (char ch = 'a'; ch <= 'z'; ++ch) {
        put(ch, valuesList);
      }
    }
  };


  /**
   * HashMap that contains all the alphabet characters as keys and their occurrences as values.
   *
   **/

  HashMap<Character, Integer> charOccurrences = new HashMap<>(){
    {
      for (char ch = 'a'; ch <= 'z'; ++ch)
        put(ch, 0);
    }
  };

  /**
   * Temporary HashMap that contains all the alphabet characters as keys and their occurrences as values on the given file - locally
   *
   **/

  HashMap<Character, Integer> temporaryCharacterMap = new HashMap<>(){
    {
      for (char ch = 'a'; ch <= 'z'; ++ch)
        put(ch, 0);
    }
  };

  /**
   * Absolute file path.
   *
   **/

  String absolutePath;

  LettersOccurrenceDistribution (){

  }



  /**
   * Performs letters distribution.
   *
   * @param directoryToSearch - a directory to search
   * @throws NoSuchFileException in case the {@param directoryToSearch} does not exist.
   */


  @Override
  public void doLettersDistribution (String directoryToSearch) throws NoSuchFileException {

    LettersOccurrenceDistribution lettersOccurrenceDistribution = this;
    SearchLettersCommand searchLettersCommand = new SearchLettersCommand(lettersOccurrenceDistribution);
    UpdateLettersCommand updateLettersCommand = new UpdateLettersCommand(lettersOccurrenceDistribution);
    WriteLettersCommand writeLettersCommand = new WriteLettersCommand(lettersOccurrenceDistribution);

    File dir = new File(directoryToSearch);

    if(!dir.isDirectory()){
      throw new NoSuchFileException(DIRECTORY_PATH_ERROR_MESSAGE);
    }

    File[] listOfFilesInDirectory = dir.listFiles();

    for (File f : Objects.requireNonNull(listOfFilesInDirectory)) {
      if (f.isDirectory()) {
        doLettersDistribution(f.getAbsolutePath());
      } else if (f.getName().endsWith(FILE_TXT_ENDING)) {
        isTxtFound = true;
        absolutePath = f.getAbsolutePath();
        searchLettersCommand.execute();
        updateLettersCommand.execute();
        writeLettersCommand.execute();
      }
    }

    if(!isTxtFound) {
      throw new NoSuchFileException("No .txt files found.");
    }
  }

}
