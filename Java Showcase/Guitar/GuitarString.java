// Min Heo
// 1/20/22
// TA: Omar Ibrahim
// CSE 143E
// This program allows the client to create a ring buffer that allows them to
// produce some sound for any number of strings (for an instrument like a guitar).

import java.util.*;

public class GuitarString {
   
   private Queue<Double> ringBuffer;
   
   // This variable contains the energy decay factor for the Karplus-Strong algorithm.
   public static final double DECAY_FACTOR = 0.996;
   
   // pre: The frequency is less than or equal to zero or the ring buffer has a size that is
   //      less than 2, the program throws IllegalArgumentException.
   // post: Creates a ring buffer with a certain number of zeros determined by the sample rate
   //       divided by the frequency.
   // parameters:
   //    frequency: A frequency simulating a given guitar vibration, given by the client.
   public GuitarString(double frequency) {
      if (frequency <= 0) {
         throw new IllegalArgumentException();
      }
      int numberOfZeros = (int)(Math.round(StdAudio.SAMPLE_RATE / frequency));
      if (numberOfZeros < 2) {
         throw new IllegalArgumentException();
      }
      ringBuffer = new LinkedList<Double>();
      for (int i = 0; i < numberOfZeros; i++) {
         ringBuffer.add(0.0);
      }
   }
   
   // pre: If the size of the ring buffer is less than two, throws IllegalArgumentException.
   // post: This contsructs a new ring buffer that puts in values provided by the client
   //       inside of it.
   // parameters:
   //    init: Values that are provided by the client.
   public GuitarString(double[] init) {
      if (init.length < 2) {
         throw new IllegalArgumentException();
      }
      ringBuffer = new LinkedList<Double>();
      for (int i = 0; i < init.length; i++) {
         ringBuffer.add(init[i]);
      }
   }
   
   // post: Removes from the front of the ring buffer and then puts in a random
   //       value between -0.5 and 0.5 into the end of the ring buffer.
   public void pluck() {
      for (int i = 0; i <= ringBuffer.size(); i++) {
         double plucked = Math.random() - 0.5;
         ringBuffer.remove();
         ringBuffer.add(plucked);
      }
   }
   
   // post: Removes the first value in the ring buffer and then does a Karplus-Strong update
   //       with the first two values, and adds that value at the end of the ring buffer.
   public void tic() {
      double firstValue = ringBuffer.remove();
      double secondValue = ringBuffer.peek();
      double average = ((firstValue + secondValue) / 2);
      double karplusStrong = DECAY_FACTOR * average;
      ringBuffer.add(karplusStrong);
   }
   
   // post: Gives the client the first value in the ring buffer.
   // return: Returns the first value stored in the ring buffer.
   public double sample() {
      return ringBuffer.peek();
   }
}
