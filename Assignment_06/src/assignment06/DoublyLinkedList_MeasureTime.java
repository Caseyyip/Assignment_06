package assignment06;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Timing class for DoublyLinkedList
 * @author Andy Dao
 */
public class DoublyLinkedList_MeasureTime {
	
	public static final int BILLION = 1000_000_000;
	public static final double MILLION = 1_048_576; // 2^20
	private static final int ITER_COUNT = 1000;

	
	 public static void main(String args[]) {
		 //addToOneMillion(); // This will add numbers 0 to 1048576
		 //countToABillionOnYourFingers();
		 indexOf(); 
	 }
	 
	 
	 public static void indexOf(){
		 long startTime = System.nanoTime();
		 while (System.nanoTime() - startTime < 1000000000);

		 try(FileWriter fw = new FileWriter(new File("indexOf.tsv"))) { //open up a file writer so we can write to file.
			 // Calculate size of set
			 for(int exponent = 10; exponent <= 20; exponent++){ // Used as exponent. We're going to be starting from 2^10, working up to 2^20.
				 int size = (int) Math.pow(2, exponent);  

				 // Now do the experiment multiple times then average out the results
				 long totalTime = 0; // Start total time at 0.

				 for(int iterate = 0; iterate < ITER_COUNT; iterate++){
					 /* SETUP */
					 DoublyLinkedList<Object> list = new DoublyLinkedList<>();

					 for(int i = 0; i < size; i++){
						 list.add(i, i);
					 }

					 int findThisElement = new Random().nextInt(size); // Will assign a random int between 0 and size.

					 /* TIME THE CODE */
					 long start = System.nanoTime();
					 list.indexOf(findThisElement); 
					 long stop = System.nanoTime();
					 totalTime += stop - start;
				 }
				 double averageTime = totalTime / (double)ITER_COUNT;
				 System.out.println(size + "\t" + averageTime); // Visual representation o
				 fw.write(size + "\t" + averageTime + "\n"); // Write data to file
			 }

		 } catch (IOException e) {
			 e.printStackTrace();
		 }
	 }

	 public static void addToOneMillion(){
		 double size = MILLION;
		 System.out.println("Size: " + size);
		 DoublyLinkedList<Object> list = new DoublyLinkedList<>();
		 
		 long start = System.nanoTime();
		 
		 for(int numOfRound = 0; numOfRound < 5; numOfRound++){
			 
			 
			 
			 
		 }
		 
		 
		 for(int count = 0; count < size; count++){
			 list.add(count, count);
		 }
		 long end = System.nanoTime() - start;
		 double time = end / BILLION;
		 System.out.println("Method add(int index, E element)" + time);
	 }
	 
	 public static void countToABillionOnYourFingers() {
			int count =0;
			long start;
			int size = BILLION;
			System.out.println("Size: " + size);
			boolean[] fingers = new boolean[size];
			start = System.nanoTime();
			for(; count < size; count++) {
				fingers[count] = true;
			}
			long end = System.nanoTime() - start;
			double time = end / (double)BILLION;
			for(boolean finger : fingers) {
				if(!finger) {
					System.out.println("Woops! Missed a finger");
				}
			}
			System.out.println("I counted to " + count + " in " + time + " seconds... on my fingers!");
		}
}
