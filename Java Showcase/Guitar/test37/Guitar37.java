
public class Guitar37 implements Guitar {

   private GuitarString[] strings;
   private int time;
   
   public static final int PITCH_VALUE = 24;
   
   public static final String KEYBOARD =
      "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  
    
    public Guitar37() {
      time = 0;
      strings = new GuitarString[KEYBOARD.length()];
      for (int i = 0; i < KEYBOARD.length(); i++) {
         strings[i] = new GuitarString(440 * (Math.pow(2, ((double)i - PITCH_VALUE) / 12)));
      }
    }
    
    public void playNote(int pitch) {
      if (KEYBOARD.length() - PITCH_VALUE > pitch && pitch >= -1 * PITCH_VALUE) {
         strings[PITCH_VALUE + pitch].pluck();
      }
    }
    
    public boolean hasString(char key) {
      return KEYBOARD.contains(key + "");
    }
    
    public void pluck(char key) {
      if (KEYBOARD.contains(key + "")) {
         int index = KEYBOARD.indexOf(key);
         strings[index].pluck();
      }
    }
    
    public double sample() {
      double sum = 0.0;
      for (int i = 0; i < strings.length; i++) {
         sum += strings[i].sample();
      }
      return sum;
    }
    
    public void tic() {
      for (int i = 0; i < strings.length; i++) {
         strings[i].tic();
      }
      time++;
    }
    
    public int time() {
      return time;
    }
}
