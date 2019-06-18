package com.letters.distribution;

import com.letters.distribution.LettersOccurrenceDistribution;

import java.nio.file.NoSuchFileException;
import java.util.Scanner;

public class Main {
  public static void main (String[] args) {

    Scanner in = new Scanner(System.in);
    String path = in.nextLine();

    LettersOccurrenceDistribution lettersOccurrenceDistribution = new LettersOccurrenceDistribution();
    try {
      lettersOccurrenceDistribution.doLettersDistribution(path);
    } catch (NoSuchFileException e) {
      e.printStackTrace();
    }
  }
}
