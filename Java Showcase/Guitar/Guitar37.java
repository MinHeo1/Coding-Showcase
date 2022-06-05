// Min Heo
// 1/20/22
// TA: Omar Ibrahim
// CSE 143E
// Allows the client to produce sound similar to a guitar (with any number of guitar strings)
// by using their keyboard (similar to a piano setup).

public class Guitar37 implements Guitar {
   
   private GuitarString[] strings;
   private int time;
   
   // The pitch value that allows the client to get the pitch for their keyboard inputs.
   public static final int PITCH_VALUE = 24;
   
   // The corresponding keys that allows the client to play on their keyboard.
   public static final String KEYBOARD =
   "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
   
   // post: Keeps tracks of the corresponding frequency values of the keys (in the range of
   //       110 Hz and 880 Hz, in the chromatic scale). This frequency is calculated using
   //       a specific formula.
   public Guitar37() {
      time = 0;
      strings = new GuitarString[KEYBOARD.length()];
      for (int i = 0; i < KEYBOARD.length(); i++) {
         strings[i] = new GuitarString(440 * (Math.pow(2, ((double)i - PITCH_VALUE) / 12)));
      }
   }
   
   // pre: The pitch has the be less than the difference of the length of the keyboard (37) and
   //      the pitch value (24) and greater than the negative of the pitch value. This is because
   //      the bounds for pitch on the simulated piano keyboard ranges from -24 to 12.
   // post: Plays the simulated note at the pitch given by the client. If it can't be played,
   //       it is ignored by the program/
   // parameter:
   //    pitch: The pitch the client wants to play.
   public void playNote(int pitch) {
      if (KEYBOARD.length() - PITCH_VALUE > pitch && pitch >= -1 * PITCH_VALUE) {
         strings[PITCH_VALUE + pitch].pluck();
      }
   }
   
   // post: Shows the client whether the inputted character is part of the "piano keyboard" for
   //       the program. Shows them whether it can be played or not.
   // parameter:
   //    key: The key the client wants to check is on the piano keyboard.
   // return: Returns whether the given key on the keyboard is part of the setup.
   public boolean hasString(char key) {
      return KEYBOARD.contains(key + "");
   }
   
   // pre: The program has to contain the corresponding key in the "piano keyboard", if not
   //      throws IllegalArgumentException.
   // post: Allows the user to "pluck" or play a sound at the given key in the keyboard.
   // parameters:
   //   key: The key on the keyboard the user wants to play.
   public void pluck(char key) {
      if (!KEYBOARD.contains(key + "")) {
         throw new IllegalArgumentException();
      }
      int index = KEYBOARD.indexOf(key);
      strings[index].pluck();
   }
   
   // post: Sums up all of the values that allows sound to be produced.
   // return: Returns the sum of all the values that were inside of the program.
   public double sample() {
      double sum = 0.0;
      for (int i = 0; i < strings.length; i++) {
         sum += strings[i].sample();
      }
      return sum;
   }
   
   // post: Counts the number of times the keyboard was played, keeps track of "tics".
   public void tic() {
      for (int i = 0; i < strings.length; i++) {
         strings[i].tic();
      }
      time++;
   }
   
   // post: Gives the user how many times the keys have been played.
   // return: Returns the value of the number of times the keys were played.
   public int time() {
      return time;
   }
}
