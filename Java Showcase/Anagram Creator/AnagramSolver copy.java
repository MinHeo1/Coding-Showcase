// Min Heo
// CSE 143B
// 5/13/22
// This program looks at a dictionary of words and creates (and prints) relevant anagrams based
// off words given by the user. The program ignores capitilization and spaces to create as many
// anagrams as possible with the given word. 

import java.util.*;

public class AnagramSolver {
   
   private List<String> userWords;
   private Map<String, LetterInventory> possibleInDictionary;
   
   // post: Constructs the "anagram solver". The program takes the dictionary provided and
   //       associates the words in the dictionary with their respective counts of letters.
   //       However, and importantly, this does not change the dictionary. The dictionary is
   //       assumed to be filled and non-empty.
   // parameters:
   //    dictionary: The list of possible words that could be made into anagrams based off the
   //                user's word.
   public AnagramSolver(List<String> dictionary) {
      userWords = dictionary;
      possibleInDictionary = new HashMap<String, LetterInventory>();
      for (String word : dictionary) {
         possibleInDictionary.put(word, new LetterInventory(word));
      }
   }
   
   // pre: If the given max by the user is less than zero, the program throws an
   //      IllegalArgumentException.
   // post: Prints all the anagrams disregarding spaces or capitalization. It checks whether it is
   //       an anagram by looking at the count of the respective letters of the word given by the
   //       user as well as in the dictionary. It does this by going through each possibility one
   //       by one.
   // parameters:
   //    text: The word given by the user to find anagrams of.
   //    max: The max number of words that can be included in each anagram. An exception is if
   //         the user passes 0. Then, instead of including 0 anagrams, it includes an unlimited
   //         number of words.
   public void print(String text, int max) {
      if (max < 0) {
         throw new IllegalArgumentException();
      }
      LetterInventory sorted = new LetterInventory(text);
      List<String> possibilities = new ArrayList<String>();
      for (String words : userWords) {
         if (sorted.subtract(possibleInDictionary.get(words)) != null) {
            possibilities.add(words);
         }
      }
      if (max == 0) {
         max = -1;
      }
      Stack<String> wordsToPrint = new Stack<String>();
      print(max, possibilities, sorted, wordsToPrint);
   }
   
   // post: Keeps tracks of the anagrams of the word given by the user as well as printing them.
   //       This continues to do so until there is no longer any possibilites (can be called
   //       exhaustive search, searching until it cannot anymore). 
   // parameters:
   //    max: The max number of words that can be included in each anagram. 
   //    listOfWords: The words that can be considered anagrams of the word given by the user.
   //    sorted: The count of the letters found by subtracting respective letters from the given
   //            word and the words in the dictionary.
   //    wordsToPrint: Keeps tracks of all the anagrams that work with the given word.
   private void print(int max, List<String> listOfWords, LetterInventory sorted, 
                      Stack<String> wordsToPrint) {
      if (sorted.isEmpty()) {
         System.out.println(wordsToPrint);
      } else if (max != 0) {
         for (String subtractWord : listOfWords) {
            LetterInventory newSorted = sorted.subtract(possibleInDictionary.get(subtractWord));
            if (newSorted != null) {
               wordsToPrint.push(subtractWord);
               print(max - 1, listOfWords, newSorted, wordsToPrint);
               wordsToPrint.pop();
            }
         }
      }
   }
}