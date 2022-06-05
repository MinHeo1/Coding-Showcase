// Min Heo
// TA: Omar Ibrahim
// 2/1/22
// CSE 143E
//

public class HangmanManger {
   
   private int guesses;
   private Set<Character> lettersGuessed;
   private Set<String> possibleWords;
   private String pattern;
   
   public HangmanManager(Collection<String> dictionary, int length, int max) {
   
   }
   
   public Set<String> words() {
      return possibleWords;
   }
   
   public int guessesLeft() {
      return guesses;
   } 
   
   public Set<Character> guesses() {
      return lettersGuessed;
   }
   
   public String pattern() {
   
   }
   
   public int record(char guess) {
   
   }
}