// Min Heo
// 1/24/22
// TA: Omar Ibrahim
// CSE 143E
// This game simulates a game of "Assassin". People in the game are assigned someone they
// have to kill. However, they themselves do not know who is trying to kill them. This program
// keeps track of who is playing, who is killing who, who has been killed, and also who is
// to be killed next. The client gives the program the list of names who are participating,
// then the program simulates the game. The people first are placed into a kill ring, and once
// they are killed they are placed into the graveyard.

import java.util.*;

public class AssassinManager {
   
   private AssassinNode killRingFront;
   private AssassinNode graveyardFront;
   
   // pre: If the size of the list of names given by the client is equal to zero the program
   //      throws an IllegalArgumentException.
   // post: Constructs the game of assassin with the names given by the client. Creates the
   //       kill ring and prepares the graveyard.
   // parameters:
   //    names: The list of names that are going to be playing assassin in this program.
   public AssassinManager(List<String> names) {
      AssassinNode graveyardFront = null;
      if (names.size() == 0) {
         throw new IllegalArgumentException();
      }
      killRingFront = new AssassinNode(names.get(0));
      AssassinNode currAssassin = killRingFront;
      for (int i = 1; i < names.size(); i++) {
         String assassin = names.get(i);
         AssassinNode newAssassin = new AssassinNode(assassin);
         currAssassin.next = newAssassin;
         currAssassin = currAssassin.next;
      }
   }
   
   // post: Prints out and shows to the client the current kill ring. Also tells the client
   //       who is trying to assassinate who.
   public void printKillRing() {
      AssassinNode currAssassin = killRingFront;
      while (currAssassin != null) {
         AssassinNode nextAssassin = currAssassin.next;
         if (nextAssassin == null) {
            System.out.println("    " + currAssassin.name + " is stalking " + killRingFront.name);
         } else {
            System.out.println("    " + currAssassin.name + " is stalking " + nextAssassin.name);
         }
         currAssassin = nextAssassin;
      }
   }
   
   // post: Prints out and shows the client who is in the graveyard and who killed that person.
   public void printGraveyard() {
      AssassinNode currAssassin = graveyardFront;
      while (currAssassin != null) {
         System.out.println("    " + currAssassin.name + " was killed by " + currAssassin.killer);
         currAssassin = currAssassin.next;
      }
   }
   
   // post: Tells the user whether the person the client inputs is in the kill ring or not.
   //       Casing does not matter (meaning the name the client is looking for can be
   //       uppercase or lowercase and the program won't discriminate).
   // parameters:
   //    name: The name that the client wants to check whether or not they are in the kill ring.
   // return: Returns true if the name inputted by the client is in the kill ring, returns false
   //         otherwise.
   public boolean killRingContains(String name) {
      return checkContains(killRingFront, name);
   }
   
   // post: Tells the client whether the person inputted is in the graveyard or not. Casing does
   //       not matter for the name (i.e., both upper and lowercase are fine for the name).
   // parameters:
   //    name: The name that the client wants to check whether or not they are in the graveyard.
   // return: Returns true if the name inputted is in the graveyard, returns false otherwise.
   public boolean graveyardContains(String name) {
      return checkContains(graveyardFront, name);
   }
   
   // post: Tells the client whether the game is over. The game is over when there is only one
   //       person left in the kill ring.
   // return: Returns true if there is only one person in the graveyard (game over), returns
   //         false otherwise.
   public boolean gameOver() {
      return killRingFront.next == null;
   }
   
   // post: Gives the client the name of the winner of Assassin (i.e., last person in the kill
   //       ring).
   // return: Returns the name of the winner of Assassin.
   // return: Returns null if the game is still going (more than one person in the kill ring).
   public String winner() {
      if (gameOver()) {
         return killRingFront.name;
      }
      return null;
   }
   
   // pre: If the game is over (only one person left in the kill ring) then the program throws
   //      IllegalStateException. If the name given by the client they want to kill is not in
   //      the kill ring, the program throws IllegalArgumentException. If both cases are true,
   //      then the IllegalStateException takes precedence.
   // post: Kills the person that the user wants to have killed. Then, the person inputted is
   //       put into the front of the graveyard, the killer is recorded, and the kill ring is
   //       modified. Meaning, the person who killed the inputted person is now targetting the
   //       person the inputted person was trying to kill.
   // parameters:
   //    name: The name the client gives to be killed by the program.
   public void kill(String name) {
      if (gameOver()) {
         throw new IllegalStateException();
      }
      if (!killRingContains(name)) {
         throw new IllegalArgumentException();
      }
      AssassinNode currAssassin = killRingFront;
      AssassinNode killed;
      if (killRingFront.name.equalsIgnoreCase(name)) {
         killed = killRingFront;
         killRingFront = killed.next;
         while (currAssassin.next != null) {
            currAssassin = currAssassin.next;
         }
      } else {
         while (!currAssassin.next.name.equalsIgnoreCase(name)) {
            currAssassin = currAssassin.next;
         }
         killed = currAssassin.next;
         currAssassin.next = killed.next;
      }
      killed.killer = currAssassin.name;
      killed.next = graveyardFront;
      graveyardFront = killed;
   }
   
   // post: Checks whether the name given by the client is in the kill ring or the  graveyard.
   //       Name is not case-sensitive (upper or lowercase, or mixture of both, is fine).
   // parameters:
   //    graveOrRing: Either the list of people in the kill ring or the graveyard.
   //    name: The name the client wants to check whether or not they are in the kill ring or
   //          the graveyard.
   // return: Returns true if the name is in the kill ring or graveyard (not case-sensitive),
   //         returns false otherwise.
   private boolean checkContains(AssassinNode graveOrRing, String name) {
      AssassinNode currAssassin = graveOrRing;
      while (currAssassin != null) {
         if (currAssassin.name.equalsIgnoreCase(name)) {
            return true;
         }
         currAssassin = currAssassin.next;
      }
      return false;
   }
}