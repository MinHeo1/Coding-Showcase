// Min Heo
// 4/8/22
// CSE 143B
// This program creates an inventory that keeps tracks of the count of letters for
// given inputs (which are usually words) and then is able to print the corresponding
// letters. With it, the client can also add to and take away from it.

public class LetterInventory {
   
   private int[] countOfLetters;
   private int size;
   
   // This is the total number of letters in the alphabet (english the default is 26).
   public static final int DEFAULT_ALPHABET = 26;
   
   // post: Constructs an empty count for each letter.
   public LetterInventory() {
      countOfLetters = new int[DEFAULT_ALPHABET];
   }
   
   // post: Constructs a count of how many of each letter for the given words.
   // parameters:
   //    data: Takes in a word wherein the program will count how many of each letter.
   public LetterInventory(String data) {
      for (int i = 0; i < data.length(); i++) {
         char letter = data.charAt(i);
         char lowerCaseLetter = Character.toLowerCase(letter);
         if (Character.isLetter(lowerCaseLetter)) {
            size++;
            countOfLetters[lowerCaseLetter - 'a']++;
         }
      }
   }
   
   // pre: The input is a given letter (not a number, symbol, etc.). The program
   //      throws IllegalArgumentException if input is not a letter.
   // post: Gives the client the count associated with the letter given.
   // parameters:
   //    letter: The letter that the client would like to get the value of.
   public int get(char letter) {
      if (!Character.isLetter(letter)) {
         throw new IllegalArgumentException();
      }
      char letterProvided = Character.toLowerCase(letter);
      return countOfLetters[letterProvided - 'a'];
   }
   
   // pre: The given letter is a character and the value given is greater than zero.
   //      If these are not met, throws IllegalArgumentException.
   // post: Sets the count of the given letter to a value that the client would like.
   // parameters:
   //    letter: The letter that the client would like to change the value of.
   //    value: The value that the client would like to change the letter to.
   public void set(char letter, int value) {
      if (!Character.isLetter(letter) || value < 0) {
         throw new IllegalArgumentException();
      }
      char chosenLetter = Character.toLowerCase(letter);
      size += value - countOfLetters[chosenLetter - 'a'];
      countOfLetters[chosenLetter - 'a'] = value;
   }
   
   // post: Gives the total sum of the values in the array.
   // return: returns the sum of all the values.
   public int size() {
      return size;
   }
   
   // post: Checks and lets the client know if the inventory adds up to zero.
   // return: returns true/false depending on whether the inventory is zero or not.
   public boolean isEmpty() {
      return size == 0;
   }
   
   // post: returns all the letters (with their respective values) in string form and
   //       in lowercase between square brackets.
   // return: returns a string of the letters (in alphabetical order) with their corresponding
   //         values, between square brackets.
   public String toString() {
      String string = "[";
      for (int i = 0; i < countOfLetters.length; i++) {
         for (int j = 0; j < countOfLetters[i]; j++) {
            char letters = (char)(i + 'a');
            string += letters;
         }
      }
      return string += "]";
   }
   
   // post: Creates a LetterInventory that adds the original LetterInventory (and
   //       the values stored within it) to the LetterInventory given by the client.
   // parameters:
   //    other: The LetterInventory (and it's values) that the client wants to add to the
   //           original.
   // return: returns a LetterInventory that is the sum of the original and the provided.
   public LetterInventory add(LetterInventory other) {
      LetterInventory sum = new LetterInventory("");
      for (int i = 0; i < other.countOfLetters.length; i++) {
         int addition = countOfLetters[i] + other.countOfLetters[i];
         sum.countOfLetters[i] = addition;
         sum.size += addition;
      }
      return sum;
   }
   
   // post: Creates a LetterInventory that takes the difference of the original LetterInventory
   //       (and its values) and the provided LetterInventory.
   // parameters:
   //    other: The LetterInventory that the client wants to subtract the original to.
   // return: returns null if the subtracted value at any index is less than 0.
   // return: returns a LetterInventory of the difference of the original LetterInventory
   //         and the one the client provides.
   public LetterInventory subtract(LetterInventory other) {
      LetterInventory result = new LetterInventory("");
      for (int i = 0; i < other.countOfLetters.length; i++) {
         int subtraction = countOfLetters[i] - other.countOfLetters[i];
         if (subtraction < 0) {
            return null;
         }
         result.countOfLetters[i] = subtraction;
         result.size += subtraction;
      }
      return result;
   }
}