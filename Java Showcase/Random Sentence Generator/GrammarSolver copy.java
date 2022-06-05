// Min Heo
// CSE 143B
// 4/30/22
// This program radomly generates sentences based off of certain sets of rules written in
// Bakus-Naur Form. The form follows a peculiar syntax with certain sets of rules and characters.
// The program follows correct grammar structure (in this case, following
// the syntax and symbols for the language) and randomly creates an output based off of
// those things.

import java.util.*;

public class GrammarSolver {
   
   private SortedMap<String, List<List<String>>> grammarLookUp;
   
   // pre: If the given rules are empty (no rules were given) then the program throws an
   //      IllegalArgumentException.
   // post: This constructs the list of rules that the program will follow when constructing
   //       the random outputs. Each rule leads to one line of text.
   // paramters:
   //    rules: The rules that the program must follow (syntax, convention, etc.).
   public GrammarSolver(List<String> rules) {
      if (rules.isEmpty()) {
         throw new IllegalArgumentException();
      }
      grammarLookUp = new TreeMap<String, List<List<String>>>();
      for (int i = 0; i < rules.size(); i++) {
         String[] split = rules.get(i).split("::=");
         String nonTerminal = split[0];
         String[] separateRules = split[1].split("\\|");
         List<List<String>> listOfRules = new ArrayList<List<String>>();
         for (int j = 0; j < separateRules.length; j++) {
            String setOfRules = separateRules[j].trim();
            String[] piecesOfRules = setOfRules.split("\\s+");
            List<String> partsOfRules = new ArrayList<String>();
            for (int k = 0; k < piecesOfRules.length; k++) {
               partsOfRules.add(piecesOfRules[k]);
            }
            listOfRules.add(partsOfRules);
         }
         grammarLookUp.put(nonTerminal, listOfRules);
      }
   }
   
   // post: Lets the user know if the given symbol (such as a word) is a non-terminal. A
   //       non-terminal being a definition for a group of specific words/symbols.
   // parameters:
   //    symbol: The given symbol of the user to be checked whether non-terminal or not.
   // return: Returns true if the symbol is non-terminal, false otherwise.
   public boolean grammarContains(String symbol) {
      return grammarLookUp.containsKey(symbol);
   }
   
   // post: This method shows the user the non-terminal of the previous grammars.
   // return: Prints the non-terminals that were used for that specific grammar. This will be
   //         in closed brackets, as well as comma-separated.
   public String getSymbols() {
      String getSymbol = "[";
      for (String nonTerminal : grammarLookUp.keySet()) {
         getSymbol += nonTerminal + ", ";
      }
      getSymbol = getSymbol.substring(0, getSymbol.length() - 2);
      getSymbol += "]";
      return getSymbol;
   }
   
   // pre: If times is less than zero or the given symbol is not a non-terminal, than the program
   //      throws an IllegalArgumentException.
   // post: The program continues to generates random terminals based off of the given symbol
   //       well for the amount that is specified by the user. Then this is shown to the user in
   //       a specific format. It will be in brackets, and separated by commas with one space
   //       between each terminal (no extra spaces).
   // paramters:
   //    symbol: The non-terminal that the user inputs to get the terminals from.
   //    times: The number of terminals that the user wants to randomly generate.
   // return: Returns the terminals that were randomly picked by the program in the specific
   //         format specified above.
   public String[] generate(String symbol, int times) {
      if (times < 0 || !grammarContains(symbol)) {
         throw new IllegalArgumentException();
      }
      String[] sentence = new String[times];
      for (int i = 0; i < times; i++) {
         sentence[i] = generateHelper(symbol);
      }
      return sentence;
   }
   
   // post: This program randomly generates the terminals that are within the non-terminals.
   // paramters:
   //    symbol: The non-terminal that the user wants to get the terminals from.
   // return: Returns each terminal, without extra spaces.
   private String generateHelper(String symbol) {
      String finalWords = "";
      if (!grammarContains(symbol)) {
         return symbol;
      } else {
         List<List<String>> potentialRules = grammarLookUp.get(symbol);
         Random rand = new Random();
         int randomIndex = rand.nextInt(potentialRules.size());
         List<String> randomWords = potentialRules.get(randomIndex);
         for (int i = 0; i < randomWords.size(); i++) {
            finalWords += generateHelper(randomWords.get(i)) + " ";
         }
         return finalWords.trim();
      }
   }
}