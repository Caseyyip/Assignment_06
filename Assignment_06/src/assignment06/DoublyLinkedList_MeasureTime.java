package assignment06;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Timing class for DoublyLinkedList
 * @author Andy Dao and Ho Lam Yip
 */
public class DoublyLinkedList_MeasureTime {

	public static final int BILLION = 1000_000_000;
	public static final double MILLION = 1_048_576; // 2^20
	private static final int ITER_COUNT = 1000;
	private static final int THOUSAND = 1_000;
	
	private static int randNum;
	private static int setSizeInt;

	private static boolean doDoublyFirst = true;
	private static long totalTimeForDoubly;
	private static long totalTimeForArray;
	

	public static void main(String args[]) {
		System.out.println("Set Size|Average Time");
		//addToOneMillion(); // This will add numbers 0 to 1048576
		//indexOf();
		//addWithJavaArrayList(); // add() method for Java's ArrayList
		//addFirstTiming(); // addFirst() with DoublyLinkedList
		//getTiming();
		//getTiming2();
		getTime3();
	}

	/* Timing code for add() for ArrayList*/
	public static void addWithJavaArrayList(){
		System.out.println("=== Testing add(int index, E element) ===");
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000);

		try(FileWriter fw = new FileWriter(new File("addWithArrayList.tsv"))) { //open up a file writer so we can write to file.
			// Calculate size of set
			for(int exponent = 10; exponent <= 16; exponent++){ // Used as exponent. We're going to be starting from 2^10, working up to 2^20.
				int size = (int) Math.pow(2, exponent);  

				// Now do the experiment multiple times then average out the results
				long totalTime = 0; // Start total time at 0.

				for(int iterate = 0; iterate < ITER_COUNT; iterate++){
					/* SETUP */
					ArrayList<Object> list = new ArrayList<>();

					/* TIME THE CODE */
					long start = System.nanoTime();
					for(int i = 0; i < size; i++){
						list.add(0, i);
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
		System.out.println("End of add() for ArrayList Test\n");

	}
	
	/* Timing addFirst() method */
	public static void addFirstTiming(){
		System.out.println("=== Testing addFirst() ===");

		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000);

		try(FileWriter fw = new FileWriter(new File("addFirstDoublyLinkedList.tsv"))) { //open up a file writer so we can write to file.
			// Calculate size of set
			for(int exponent = 10; exponent <= 16; exponent++){ // Used as exponent. We're going to be starting from 2^10, working up to 2^20.
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
		System.out.println("End of addFirst() for DoublyLinkedList Test\n");
	}
	
	/* Setup For get() method */
	public static void setupForGet(){
		
	}
	
	
	/* Timing code for get() for DoublyLinkedList */
	public static void getTime3(){
		System.out.println("=== Testing add(int index, E element) ===");
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000);

		try(FileWriter fw = new FileWriter(new File("addWithArrayList.tsv"))) { //open up a file writer so we can write to file.
			// Calculate size of set
			for(int exponent = 10; exponent <= 16; exponent++){ // Used as exponent. We're going to be starting from 2^10, working up to 2^20.
				int size = (int) Math.pow(2, exponent);  

				int findThisElement = new Random().nextInt(size); // Will assign a random int between 0 and size.
				
				long totalTime = getTimingDoublyLinkedList(size, findThisElement);
								//getTimingArrayList(size, findThisElement);
				double averageTime = totalTime / (double)ITER_COUNT;
				System.out.println(size + "\t" + averageTime); // Visual representation o
				fw.write(size + "\t" + averageTime + "\n"); // Write data to file
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("End of getDoubly Test\n");
	}
	
	
	
	
//	/* Timing get() method */
//	public static void getTiming(){
//		System.out.println("=== Testing get() ===");
//		long startTime = System.nanoTime();
//		while (System.nanoTime() - startTime < 1000000000); // Warm up
//
//		try(FileWriter fw = new FileWriter(new File("getTimingData.tsv"))) { //open up a file writer so we can write to file.
//			// Calculate size of set
//			for(int exponent = 10; exponent <= 16; exponent++){ // Used as exponent. We're going to be starting from 2^10, working up to 2^20.
//				int size = (int) Math.pow(2, exponent);  
//				
//				for(int iterate = 0; iterate < ITER_COUNT; iterate++){
//					int findThisElement = new Random().nextInt(size); // Will assign a random int between 0 and size.
//					
//					getTimingDoublyLinkedList(size, findThisElement);
//					getTimingArrayList(size, findThisElement);
//				}
//				double averageTimeForDoubly = getTotalTimeForDoubly() / (double)ITER_COUNT; // how long it took to find element
//				double averageTimeForArrayList = getTotalTimeForArray() / (double)ITER_COUNT;
//				System.out.println(getSize() + "\t" + averageTimeForDoubly + "\t" + averageTimeForArrayList); // Visual representation o
//				fw.write(getSize() + "\t" + averageTimeForDoubly + "\t" + averageTimeForArrayList + "\n"); // Write data to file
//			}
//			}	catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println("End of get() Test\n");
//	}

	
	
	
	/* Timing get() method with DoublyLinkedList */
	public static long getTimingDoublyLinkedList(int size, int randNum){
		// Now do the experiment multiple times then average out the results
		long totalTime = 0; // Start total time at 0.
		
		for(int iterate = 0; iterate < ITER_COUNT; iterate++){
			/* SETUP */
			DoublyLinkedList<Object> list = new DoublyLinkedList<>();

			/* Make the list to search through */
			for(int i = 0; i < size; i++){
				list.add(i);
			}
			/* TIME THE CODE */
			long start = System.nanoTime();
			list.get(randNum);
			long stop = System.nanoTime();
			totalTime += stop - start;
		}
		return totalTime;
	}
	
	
	
	
	
	
	/* Timing get() method with ArrayList*/
	public static long getTimingArrayList(int size, int randNum){
		// Now do the experiment multiple times then average out the results
				long totalTime = 0; // Start total time at 0.

				for(int iterate = 0; iterate < ITER_COUNT; iterate++){
					
					/* SETUP */
					ArrayList<Object> list = new ArrayList<>();
					for(int i = 0; i < size; i++){
						list.add(i);
					}
					
					/* TIME THE CODE */
					long start = System.nanoTime();
					list.get(randNum); // Find element
					long stop = System.nanoTime();
					totalTime += stop - start;
				}
				return totalTime;
	}
		
	public static long getTotalTimeForDoubly(){
		return totalTimeForDoubly;
	}
	
	public static void setTotalTimeForDoubly(long time){
		totalTimeForDoubly = time;
	}
	
	public static long getTotalTimeForArray(){
		return totalTimeForArray;
	}
	
	public static void setTimeForArray(long time){
		totalTimeForArray = time;
	}
	
	public static int getSize(){
		return setSizeInt;
	}
	
	public static void setSize(int size){
		setSizeInt = size;
	}
	
	public static int getRandomNum(){
		return randNum;
	}
	
	public static void setRandomNum(int rand){
		randNum = rand;
	}
	
	
	 /* Timing code for indexOf() */
	public static void indexOf(){
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000);

		try(FileWriter fw = new FileWriter(new File("indexOf.tsv"))) { //open up a file writer so we can write to file.
			// Calculate size of set
			for(int exponent = 10; exponent <= 16; exponent++){ // Used as exponent. We're going to be starting from 2^10, working up to 2^20.
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
	
	
	public static void getTiming2(){
		System.out.println("get(i) timing for DoublyLinkedList");
		for (int j = 1024; j < 1048577; j = j * 2)
		{
			DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<>();
			for (int i = 0; i < j; i++)
			{
				doublyLinkedList.add(new Random().nextInt(THOUSAND));
			}

			long startTime = System.nanoTime();
			doublyLinkedList.get(doublyLinkedList.size()/2);
			long endTime = System.nanoTime() - startTime;

			System.out.println(doublyLinkedList.size() + "\t" + endTime);
		}
		System.out.println("\nget(i) timing for ArrayList");
		for (int j = 1024; j < 1048577; j = j * 2)
		{
			ArrayList<Integer> arrayList = new ArrayList<>();
			for (int i = 0; i < j; i++)
			{
				arrayList.add(new Random().nextInt(THOUSAND));
			}

			long startTime = System.nanoTime();
			arrayList.get(arrayList.size()/2);
			long endTime = System.nanoTime() - startTime;

			System.out.println(arrayList.size() + "\t" + endTime);
		}
	}
}
			
			

//			System.out.println();
//			System.out.println("remove(i) timing for DoublyLinkedList");
//			for (int j = 1024; j < 1048577; j = j * 2)
//			{
//				DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
//				for (int i = 0; i < j; i++)
//				{
//					dll.add(new Random().nextInt(THOUSAND));
//				}
//				
//				long startTime = System.nanoTime();
//				dll.remove(dll.size()/2);
//				long endTime = System.nanoTime() - startTime;
//				
//				System.out.println(dll.size() + "\t" + endTime);
//			}
//			
//			
//			System.out.println("remove(i) timing for ArrayList");
//			for (int j = 1024; j < 1048577; j = j * 2)
//			{
//				ArrayList<Integer> dll = new ArrayList<>();
//				for (int i = 0; i < j; i++)
//				{
//					dll.add(new Random().nextInt(THOUSAND));
//				}
//				
//				long startTime = System.nanoTime();
//				dll.remove(dll.size()/2);
//				long endTime = System.nanoTime() - startTime;
//				
//				System.out.println(dll.size() + "\t" + endTime);
//			}
