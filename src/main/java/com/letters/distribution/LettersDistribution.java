package com.letters.distribution;

import java.nio.file.NoSuchFileException;

public interface LettersDistribution {

  /**
   * Performs letters distribution.
   *
   * @param directoryToSearch - a directory to search
   * @throws java.nio.file.NoSuchFileException in case the {@param directoryToSearch} does not exist.
   */
  void doLettersDistribution(String directoryToSearch) throws NoSuchFileException;
}
