// Min Heo
// 4/27/22
// CSE 143B
// This program creates a new game of absurdle. A play on the game wordle, the program
// chooses a word with a given length and the user has to guess it. However, the twist
// is that the program changes the word based off of how many options there are available.
// The program has a list of words it chooses from and groups them by pattern (the letters
// that the words have). It then continues to choose the group with the most words until
// either the user guesses the word or the user loses the game. During the game, the
// program continues to give the user feedback. It gives a green square for every letter
// that is in the right spot and the right letter, a yellow square for a right letter in
// a wrong spot, and a gray square for a wrong letter.

import java.util.*;

public class AbsurdleManager {
   
   private Set<String> possibleWords;
   private int wordLength;
   
   
   // pre: If the length of the word that is to be guessed is less than 1
   //      then the program throws an IllegalArgumentException().
   // post: The program constructs the absurdle game. It creates the list of words
   //       that match the given requirement (so, if the game wants words length 4
   //       the game makes a list with all words with that given length). The program
   //       also gets rid of all duplicates (if there are any).
   // parameters:
   //    dictionary: The list of words that the program can choose from.
   //    length: The length of the word chosen by the program.
   public AbsurdleManager(Collection<String> dictionary, int length) {
      if (length < 1) {
         throw new IllegalArgumentException();
      }
      possibleWords = new TreeSet<String>();
      wordLength = length;
      for (String words : dictionary) {
         if (words.length() == length) {
            possibleWords.add(words);
         }
      }
   }
   
   // post: Lets the user see the words the program is considering.
   // return: Returns the words that the program is looking at for the game.
   public Set<String> words() {
      return possibleWords;
   }
   
   // The comment for this method is provided. Do not modify this comment:
   // Params:
   //  String word -- the secret word trying to be guessed. Assumes word is made up of only
   //                 lower case letters and is the same length as guess.
   //  String guess -- the guess for the word. Assumes guess is made up of only
   //                  lower case letters and is the same length as word.
   // Exceptions:
   //   none
   // Returns:
   //   returns a string, made up of gray, yellow, or green squares, representing a
   //   standard wordle clue for the provided guess made against the provided secret word.
   public static String patternFor(String word, String guess) {
      Map<Character, Integer> counts = new TreeMap<Character, Integer>();
      for (int i = 0; i < word.length(); i++) {
         char letters = word.charAt(i);
         if (counts.containsKey(letters)) {
            counts.put(letters, counts.get(letters) + 1);
         } else {
            counts.put(letters, 1);
         }
      }
      String[] pattern = new String[word.length()];
      for (int i = 0; i < word.length(); i++) {
         if (word.charAt(i) == guess.charAt(i)) {
            pattern[i] = "🟩";
            counts.put(word.charAt(i), counts.get(word.charAt(i)) - 1);
         }
      }
      for (int i = 0; i < word.length(); i++) {
         if (pattern[i] != "🟩" && counts.containsKey(guess.charAt(i)) && 
             counts.get(guess.charAt(i)) > 0) {
            pattern[i] = "🟨";
            counts.put(guess.charAt(i), counts.get(guess.charAt(i)) - 1);
         }
      }
      for (int i = 0; i < word.length(); i++) {
         if (pattern[i] != "🟩" && pattern[i] != "🟨") {
            pattern[i] = "⬜";
         }
      }
      String finalPattern = "";
      for (int i = 0; i < pattern.length; i++) {
         finalPattern += pattern[i];
      }
      return finalPattern;
   }
   
   // pre: The program throws an IllegalStateException() if there is no words in the list
   //      of words the program is considering. The program also throws an
   //      IllegalArgumentException() if the guess by the user is not the right length.
   // post: This records the guess of the user. The program then takes the guess and narrows
   //       down the list of options (by choosing the group of words that has the largest
   //       amount of words). It then shows the user the patented wordle pattern (with the
   //       colored blocks).
   // parameters:
   //   guess: The guess of the user.
   // return: This returns the pattern of the word. Giving a green block for the right letter
   //         in the right place, yellow for right letter wrong spot, and gray for wrong letter.
   public String record(String guess) {
      if (possibleWords.isEmpty()) {
         throw new IllegalStateException();
      } else if (guess.length() != wordLength) {
         throw new IllegalArgumentException();
      }
      Map<String, Set<String>> groups = getGroups(guess);
      String biggestPattern = createBiggest(groups);
      return biggestPattern;
   }
   
   // post: This creates a mapping of the pattern to the words that match that pattern.
   // parameters:
   //    guess: The letter that the user guesses.
   // return: Returns a map of the possible words that the program can choose from to guess.
   private Map<String, Set<String>> getGroups(String guess) {
      Map<String, Set<String>> groups = new TreeMap<String, Set<String>>();
      for (String word : possibleWords) {
         String pattern = patternFor(word, guess);
         if (!groups.containsKey(pattern)) {
            groups.put(pattern, new TreeSet<String>());
         }
         groups.get(pattern).add(word);
      }
      return groups;
   }
   
   // post: This finds the largest word that matches based off the pattern It chooses the word
   //       with the most occurences of the pattern.
   // parameters:
   //    groups: This takes in the mapping of the words that the program is considering.
   // return: Returns the word that has the most occurences of the pattern that was created.
   private String createBiggest(Map<String, Set<String>> groups) {
      possibleWords = null;
      String biggestPattern = "";
      for (String pattern : groups.keySet()) {
         Set<String> set = groups.get(pattern);
         if (possibleWords == null || possibleWords.size() < set.size()) {
            biggestPattern = pattern;
            possibleWords = set;
         }
      }
      return biggestPattern;
   }
}