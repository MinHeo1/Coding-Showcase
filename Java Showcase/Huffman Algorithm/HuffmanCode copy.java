// Min Heo
// CSE 143B
// 5/31/2022
// This program uses the Huffman coding algorithm to compress data to make it consume less
// space. The program does this, in broader terms, by looking at how much the character appears
// (the frequency of the character in the file) and assigns the characters bits based on this. So,
// the characters with higher frequency gets less bits (using ASCII coding).

import java.util.*;
import java.io.*;

public class HuffmanCode {

    private HuffmanNode root;
    
    // This stores and creates the knowledge base for the Huffman coding algorithm. It stores the
    // the frequency (how often the character appears) as well as the character itself and
    // has the function to allow the knowledge base to compare itself to other knowledge bases.
    private static class HuffmanNode implements Comparable<HuffmanNode> {
        public HuffmanNode left;
        public HuffmanNode right;
        public int freq;
        public char letter;

        // post: Constructs the knowledge base containing the frequency of the character, as well
        //       as the character itself. 
        // parameters:
        //  left: The direction that the knowledge base stores the data. If the data is stored in
        //        the left, it uses the bit 0.
        //  right: The direction that the knowledge base stores the data. If the data is stored in
        //         the right, it uses the bit 1.
        //  freq: The frequency of the character. Keeps track of how many of the character there
        //        are.
        //  letter: The character itself that is stored in the knowledge base.
        public HuffmanNode(HuffmanNode left, HuffmanNode right, int freq, char letter) {
            this.left = left;
            this.right = right;
            this.freq = freq;
            this.letter = letter;
        }

        // post: Creates an empty knowledge base.
        public HuffmanNode() {
            this(null, null, 0, (char) 0);
        }

        // post: Allows the knowledge base to compare themselves based off the frequency. It does
        //       so by showing the user the difference between the frequencies.
        // return: The program returns an integer that displays the difference in frequencies. If
        //         the knowledge base has a higher frequency, it returns a negative integer. If it
        //         has a lower frequency, it returns a positive frequency. And if it is equal, it
        //         returns 0.
        public int compareTo(HuffmanNode other) {
            return freq - other.freq;
        }
    }

    // post: Creates and initializes a new huffman coding algorithm. It does this by looking at
    //       the frequencies that are provided by the user. The frequencies provided by the user
    //       also include the ascii value to relate the frequency with the character. Then, the
    //       program creates knowledge bases with this data. This then creates the huffman coding
    //       algorithm by combining knowledge bases until there is only one, overarching knowledge
    //       base. It knows how to combine by combining the smallest knowledge bases. The resulting 
    //       knowledge base is then assigned the sum of the frequencies of the combined knowledge 
    //       bases.
    // parameters:
    //  frequencies: The frequencies of each character. The character is assigned using ascii
    //               values that are provided by the order in which the frequencies are assigned.
    public HuffmanCode(int[] frequencies) {
        Queue<HuffmanNode> priority = new PriorityQueue<HuffmanNode>();
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > 0) {
                HuffmanNode node = new HuffmanNode(null, null, frequencies[i], (char) i);
                priority.add(node);
            }
        }
        while (priority.size() > 1) {
            HuffmanNode node1 = priority.remove();
            HuffmanNode node2 = priority.remove();
            HuffmanNode dad = new HuffmanNode(node1, node2, node1.freq + node2.freq, (char) 0);
            priority.add(dad);
        }
        root = priority.remove();
    }

    // post: Creates a new knowledge base based off the ascii values and path that was
    //       provided in the file. Assumes that there is always a legilble file.
    // parameters:
    //  input: This reads the file that is encoded. The file is in standard format where
    //         the first line is the ascii value and the next is the path in the knowledge
    //         base to reach that ascii value (character). 
    public HuffmanCode(Scanner input) {
        while (input.hasNextLine()) {
            int asciiValue = Integer.parseInt(input.nextLine());
            String code = input.nextLine();
            root = add(asciiValue, code, root);
        }
    }

    // post: Creates a new knowledge base by following the path that was provided in the
    //       file. 
    // parameters:
    //  asciiValue: The ascii value for each character (follows conventional ascii rules).
    //  code: The path to travel in the knowledge base to reach the ascii value.
    //  node: The knowledge base that we want to traverse and update.
    // return: Returns a new knowledge base that was created with the given ascii values
    //         and path to get to each.
    private HuffmanNode add(int asciiValue, String code, HuffmanNode node) {
        if (node == null) {
            node = new HuffmanNode();
        }
        if (code.equals("")) {
            node.letter = (char) asciiValue;
        } else if (code.startsWith("0")) {
            node.left = add(asciiValue, code.substring(1), node.left);
        } else {
            node.right = add(asciiValue, code.substring(1), node.right);
        } 
        return node;
    }

    // post: Prints out the ascii value and the path the knowledge base should travel. This
    //       follows the format of: ascii value on one line, the path on the next. The ascii
    //       value is the conventional ascii value, but the path the knowledge base should
    //       travel is determined by the way the knowledge base is set with the characters.
    //       If the path it travels in the knowledge base is left, it is assigned a 0, if right
    //       assigned a 1. It continues to do so until it reaches the letter (combining all
    //       0's and 1's to output the total path). Does so for each character.
    // parameters:
    //   output: Prints the resulting ascii values and paths on to a file.
    public void save(PrintStream output) {
        save(output, root, "");
    }

    // post: Prints out the ascii value and the path the knowledge base should travel to get
    //       all of the characters. Taking in the overarching knowledge base provided by the user,
    //       this traveres the knowledge base to get the ascii value of each character and the
    //       path that it travels to get to it. 
    // parameters:
    //  output: Prints out the resulting ascii values and paths on to the file.
    //  node: The knowledge base that we want to traverse to get the path.
    //  path: The path the knowledge base would travel. Everytime it travels to the left, it adds
    //        a 0 to the path. If it travels right, it adds a 1. Continues to do so for every
    //        character until the final path is found.
    private void save(PrintStream output, HuffmanNode node, String path) {
        if (node.left == null && node.right == null) {
            output.println((int) node.letter);
            output.println(path);
        } else {
            save(output, node.left, path + "0");
            save(output, node.right, path + "1");
        }
    }

    // post: Prints out the uncompressed message. Reads the bits provided in the file to print it
    //       out. The bits are in binary and it reads each bit one by one. When it sees a 0 it
    //       travels to the left in the current knowledge base and to the right if it is a 1. It
    //       does so until there are no lines of bits left. It uses the path to determine the
    //       character that is stored in the knowledge base (with ascii value) to print the 
    //       original message. Continues to do so until it reaches the end of the file.
    // parameters:
    //  input: This reads the file given. Following the standard format of ascii value followed
    //         by the path (given in binary). 
    //  output: Prints out the resulting message after it is done traversing.
    public void translate(Scanner input, PrintStream output) {
        while (input.hasNext()) {
            translate(input, output, root);
        }
    }

    // post: This prints out the decoded message. It follows the path provided (going left if
    //       the bit is a 0, right if it is 1) and prints out the character stored in that
    //       knowledge base. It continues to do so until it reaches the end of the file.
    // parameters:
    //  input: This reads the file that has the ascii value followed by the path (in binary).
    //  output: Prints out the resulting message after decoding.
    //  node: The knowledge base the user wants to traverse in order to decode the message.
    private void translate(Scanner input, PrintStream output, HuffmanNode node) {
        if (node.left == null && node.right == null) {
            output.write(node.letter);
        } else if (input.next().charAt(0) == '0') {
            translate(input, output, node.left);
        } else {
            translate(input, output, node.right);
        }
    }
}