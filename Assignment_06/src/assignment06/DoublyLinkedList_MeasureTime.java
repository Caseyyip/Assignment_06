package assignment06;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Timing class for DoublyLinkedList
 * @author Andy Dao and Ho Lam Yip
 */
public class DoublyLinkedList_MeasureTime {

	public static final int BILLION = 1000_000_000;
	public static final double MILLION = 1_048_576; // 2^20
	private static final int ITER_COUNT = 1000;

	public static void main(String args[]) {
		System.out.println("Set Size|Average Time");
		//addToOneMillion(); // This will add numbers 0 to 1048576
		//indexOf();
		addToIndexWithElement();
		addFirstTiming();
	}

	 /* Timing code for indexOf() */
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
		System.out.println("End of indexOf() Test\n");
	}
	
	/* Timing code for add() */
	public static void addToIndexWithElement(){
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000);

		try(FileWriter fw = new FileWriter(new File("add.tsv"))) { //open up a file writer so we can write to file.
			// Calculate size of set
			for(int exponent = 10; exponent <= 20; exponent++){ // Used as exponent. We're going to be starting from 2^10, working up to 2^20.
				int size = (int) Math.pow(2, exponent);  

				// Now do the experiment multiple times then average out the results
				long totalTime = 0; // Start total time at 0.

				for(int iterate = 0; iterate < ITER_COUNT; iterate++){
					/* SETUP */
					DoublyLinkedList<Object> list = new DoublyLinkedList<>();

					/* TIME THE CODE */
					long start = System.nanoTime();
					for(int i = 0; i < size; i++){
						list.add(0, i 	);
					}
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
		System.out.println("End of add() Test\n");

	}
	
	/* Timing addFirst() method */
	public static void addFirstTiming(){
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000);

		try(FileWriter fw = new FileWriter(new File("addFirst.tsv"))) { //open up a file writer so we can write to file.
			// Calculate size of set
			for(int exponent = 10; exponent <= 20; exponent++){ // Used as exponent. We're going to be starting from 2^10, working up to 2^20.
				int size = (int) Math.pow(2, exponent);  

				// Now do the experiment multiple times then average out the results
				long totalTime = 0; // Start total time at 0.

				for(int iterate = 0; iterate < ITER_COUNT; iterate++){
					/* SETUP */
					DoublyLinkedList<Object> list = new DoublyLinkedList<>();

					/* TIME THE CODE */
					long start = System.nanoTime();
					for(int i = 0; i < size; i++){
						list.addFirst(i);
					}
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
		System.out.println("End of addFirst() Test\n");
	}
}
