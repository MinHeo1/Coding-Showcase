// Min Heo
// CSE 143B
// 5/24/2022
// This program allows the user to play a game of 20 questions. The computer
// will ask the user yes or no questions to which the player responds. The 
// computer tries to guess the secret object the user has in mind. When the
// program thinks it knows the answer it will ask. If it is, the computer wins.
// If it isn't, the computer modifies its knowledge and allows the user to input
// a question (and an answer) so that the computer can make better guesses.

import java.util.*;
import java.io.*;

public class QuestionsGame {
    
    private QuestionNode root;
    private Scanner console;

    // This stores and creates the knowledge base the computer has. It stores
    // the questions and answers that the user provides so that the computer
    // can perform better at the game. 
    private static class QuestionNode {
        public String data;
        public QuestionNode left;
        public QuestionNode right;
        
        // post: Creates the knowledge base for the computer. Storing the 
        //       questions and answers .
        // parameters:
        //  text: Either the question or the answer for the game (which can be
        //        provided by the user).
        //  yes: The direction the knowledge is stored. If the answer to a question
        //       is yes, it is stored in the left.
        //  no:  If the answer to a question is no, it is stored to the right.
        public QuestionNode(String text, QuestionNode yes, QuestionNode no) {
            data = text;
            left = yes;
            right = no;
        }

        // post: Creates the knowledge base for the computer to play the game.
        // parameters: 
        //  text: Either the question or the answer for the game.
        public QuestionNode(String text) {
            this(text, null, null);
        }
    }

    // post: Creates and initializes a new game of 20 questions. This new game of 20 questions
    //       starts with the default beginning answer being "computer".
    public QuestionsGame() {
        root = new QuestionNode("computer");
        console = new Scanner(System.in);
    }

    // post: Replaces the old game of 20 questions with a new one
    //       with questions (and answers) from a different game (provided
    //       in a file). This new game has its own set of questions
    //       and answers that the computer can play with the user. Replaces
    //       the old knowledge base with a new one.
    // parameters:
    //  input: This reads the questions and answers within the new game ( which is 
    //          provided in a file). The file needs to be in standard format which starts
    //          with one line being a "Q:" or "A:" (depending on whether it is a question or 
    //          answer) and the next line being the response to it.
    public void read(Scanner input) {
        root = readHelper(input);
    }
    
    // post: Creates a new game of 20 questions with the new set of questions
    //       and answers the computer can choose from. It replaces the old
    //       knowledge base with a new one.
    // parameters:
    //  input: This reads the questions and answers for the new game (with the
    //         given file).
    // return: Returns the new knowledge base with the given questions and answers
    //         from the given file.
    private QuestionNode readHelper(Scanner input) {
        String qOrA = input.nextLine();
        String text = input.nextLine();
        if (qOrA.equals("Q:")) {
            QuestionNode left = readHelper(input);
            QuestionNode right = readHelper(input);
            QuestionNode question = new QuestionNode(text, left, right);
            return question;
        }  else {
            QuestionNode answer = new QuestionNode(text);
            return answer;
        }
    }
    
    // post: This stores the current knowledge base into a new file so the 
    //       user can play at another time. The file should also follow the 
    //       standard format.
    // parameters:
    //  output: This writes the current knowledge base into a new file.
    public void write(PrintStream output) {
        write(output, root);
    }

    // post: Stores the current knowledge base into a new file allowing the user
    //       to play with this knowledge base at a different time. Follows the
    //       standard format. Standard format is where the file is stored with line
    //       pairs. The first line is either Q: or A: (exactly like that), while the
    //       second line contains the text for either the question or answer.
    // parameters:
    //  output: Writes the current knowledge base into the file.
    //  words: The knowledge base that the user wants to have saved in a file.
    private void write(PrintStream output, QuestionNode words) {
        if (words.left == null && words.right == null) {
            output.println("A:");
            output.println(words.data);
        } else {
            output.println("Q:");
            output.println(words.data);
            write(output, words.left);
            write(output, words.right);
        }
    }

    // post: Allows the user to play one complete game with the current knowledge
    //       base. If the computer wins a game, it prints a message, if it loses
    //       it updates its current knowledge base by asking the user what object
    //       they were thinking of, a questions that leads to that object, and 
    //       the answer to that question (in yes/no format).  
    public void askQuestions() {
        root = askQuestions(root);
    }

    // post: Allows the user to play a complete game of 20 questions. It updates the
    //       current knowledge base if the user wins the game with the question (and 
    //       answer) given by the user.
    // parameters:
    //  node: The knowledge that is stored for each question or answer. 
    private QuestionNode askQuestions(QuestionNode node) {
        QuestionNode newNode = node;
        if (newNode.left == null && newNode.right == null) {
            boolean prompt = yesTo("Would your object happen to be " + node.data + "?");
            if (prompt) {
                 System.out.println("Great, I got it right!");
            } else {
                System.out.print("What is the name of your object? ");
                String response = console.nextLine();
                System.out.println("Please give me a yes/no question that");
                System.out.println("distinguishes between your object");
                System.out.print("and mine--> ");
                String question = console.nextLine();
                boolean newQuestion = yesTo("And what is the answer for your object?");
                if (newQuestion) {
                    newNode.left = new QuestionNode(response);
                    newNode.right = new QuestionNode(newNode.data);
                } else {
                    newNode.left = new QuestionNode(newNode.data);
                    newNode.right = new QuestionNode(response);
                }
                newNode.data = question;
            }
        } else {
            String question = newNode.data;
            boolean answer = yesTo(question);
            if (answer) {
                askQuestions(newNode.left);
            } else {
                askQuestions(newNode.right);
            }
        }
        return newNode;
    }

    // Do not modify this method in any way
    // post: asks the user a question, forcing an answer of "y" or "n";
    //       returns true if the answer was yes, returns false otherwise
    private boolean yesTo(String prompt) {
        System.out.print(prompt + " (y/n)? ");
        String response = console.nextLine().trim().toLowerCase();
        while (!response.equals("y") && !response.equals("n")) {
            System.out.println("Please answer y or n.");
            System.out.print(prompt + " (y/n)? ");
            response = console.nextLine().trim().toLowerCase();
        }
        return response.equals("y");
    }
}