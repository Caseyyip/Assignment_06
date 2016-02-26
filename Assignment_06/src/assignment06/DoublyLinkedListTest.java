package assignment06;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Andy Dao and Ho Lam Yip
 */
public class DoublyLinkedListTest {
	
	private DoublyLinkedList<Object> doublyLinkedList;
	private DoublyLinkedList<Object> alreadyDefinedMixedDoublyLinkedList; // a mix doubly linked list of integers and strings
	private Integer integerObject1;
	private Integer integerObject2;
	private Integer integerObject3;
	private DoublyLinkedList<Object> integerList;
	private DoublyLinkedList<Object> stringList;
	
	/**
	 * Initiate objects in this setup method / setup test fixtures
	 */
	@Before
	public void setUp() throws Exception {
// Initiate doublyLinkedList
		doublyLinkedList =  new DoublyLinkedList<Object>();
		
// Some Integer objects
		integerObject1 = 5;
		integerObject2 = 20;
		integerObject3 = 55;
		
// An already declared DoublyLinkedList mixed with Integers and Strings
		alreadyDefinedMixedDoublyLinkedList = new DoublyLinkedList<Object>(); 
		alreadyDefinedMixedDoublyLinkedList.add(0, 5);
		alreadyDefinedMixedDoublyLinkedList.add(1, "this");
		alreadyDefinedMixedDoublyLinkedList.add(2, 100);
		alreadyDefinedMixedDoublyLinkedList.add(3, "is a mix");
		
// Integer DoublyLinkedList
		integerList = new DoublyLinkedList<Object>();
		
// String DoublyLinkedList
		stringList = new DoublyLinkedList<Object>();
	}


/**
 * Tests for the addFirst() method and will check with the size of the list
 */
	@Test
	// Add just 1 Integer object 
	public void addFirst_NoElementsAtStart_Integers() {
		integerList.addFirst(integerObject1);
		assertEquals(1, integerList.size());
	}
	
	@Test
	// Add just 1 String object
	public void addFirst_NoElementsAtStart_Strings(){
		stringList.addFirst("This is the first");
		assertEquals(1, stringList.size());
	}
	
	// Add three Integer objects
	@Test
	public void addFirst_ThreeElements_Integers() {
		integerList.addFirst(integerObject1);
		integerList.addFirst(integerObject2);
		integerList.addFirst(integerObject3);
		assertEquals(3, integerList.size());
	}
	
	// Add three String objects
	@Test
	public void addFirst_ThreeElements_Strings() {
		stringList.addFirst("One");
		stringList.addFirst("Two Two");
		stringList.addFirst("Three Three Three");
		assertEquals(3, stringList.size());
	}
	
	
/**
 * Tests for the addLast() method and will check with the size of the list
 */
	@Test
	// Add just 1 Integer object 
	public void addLast_NoElementsAtStart_Integers(){
		integerList.addLast(300); // Simply just add 300 to the end of the list with 
		assertEquals(1, integerList.size());
	}
	
	@Test
	// Add just 1 String object
	public void addLast_NoElementsAtStart_Strings(){
		stringList.addLast("This is the last");
		assertEquals(1, stringList.size());
	}
	
	@Test
	public void addLast_RegularAdd_ThenAddLast_Integers(){
		integerList.add(0, 1); // add 1 to index 0
		integerList.add(1, 4); // add 4 to index 1
		integerList.add(2, 2); // add 2 to index 2
		integerList.addLast(200); // Then add 200 with addLast()
		assertEquals(4, integerList.size()); 
	}
	
	@Test
	public void addLast_CombineAdd_AddLast_Integers(){
		integerList.add(0, 20);
		integerList.add(1, 15);
		integerList.add(2, 1);
		integerList.addLast(150);
		integerList.add(3, 50); // add 50 to index 3, which is where 150 was
		integerList.add(2, 1000); // Add 1000 to index 2, which is where 1 is. 1000 will be put before 1
		assertEquals(150, integerList.getLast()); // Shall still return 150 because nothing was added in an index
															 // past index 3, which is where 150 was
		
		// Uncomment below to see representation of the list after everything is ran.
		// integerList.printArray(integerList.toArray());
	}
	
	@Test
	public void addLast_OnlyUseAddLast_Integers_Size(){
		integerList.addLast(1);
		integerList.addLast(2);
		integerList.addLast(3);
		integerList.addLast(4);
		integerList.addLast(5);
		assertEquals(5, integerList.size());
	}
	
	@Test
	public void addLast_OnlyUseAddLast_Strings_Size(){
		stringList.addLast("One");
		stringList.addLast("Two Two");
		stringList.addLast("Three Three Three");
		stringList.addLast("Four Four Four Four");
		stringList.addLast("Five Five Five Five Five");
		assertEquals(5, stringList.size());
	}
	
	
/**
 * Tests for the getFirst() method
 */
	@Test
	public void getFirst_Integers(){
		integerList.add(0, 20);
		integerList.add(1, 15);
		integerList.add(2, 100);
		assertEquals(20, integerList.getFirst());
	}
	
	@Test
	public void getFirst_Strings(){
		stringList.add(0, "Hello");
		stringList.add(1, "Its");
		stringList.add(2, "Me");
		assertEquals("Hello", stringList.getFirst());
	}
	
	@Test
	public void getFirst_WithAddFirst_Integers(){
		integerList.add(0, 20);
		integerList.add(1, 15);
		integerList.add(2, 100);
		integerList.addFirst(90); // Add 90 to very beginning
		assertEquals(90, integerList.getFirst());
	}
	
	@Test
	public void getFirst_WithAddFirst_Integers_mix(){
		integerList.add(0, 20);
		integerList.add(1, 15);
		integerList.add(2, 100);
		integerList.addFirst(90); // Add 90 to very beginning
		integerList.add(0, 1555); // This will move 90 that was at the beginning
		integerList.add(2, 644);
		assertEquals(1555, integerList.getFirst());

		// Uncomment below to see representation of the list after everything is ran.
		// integerList.printArray(integerList.toArray());
	}
	
	@Test
	public void getFirst_FromMixedList(){
		assertEquals(5, alreadyDefinedMixedDoublyLinkedList.getFirst()); // Check how this mixed list is already declared above
	}
	
	
/**
 * Tests for the getLast method
 */
	@Test
	public void getLast_Integers(){
		doublyLinkedList.add(0, 10);
		doublyLinkedList.add(1, 15);
		doublyLinkedList.add(2, 25);
		assertEquals(25, doublyLinkedList.getLast());
	}
	
	@Test
	public void getLast_Strings(){
		doublyLinkedList.add(0, "One");
		doublyLinkedList.add(1, "Two Two");
		doublyLinkedList.add(2, "Three Three Three");
		assertEquals("Three Three Three", doublyLinkedList.getLast());
	}	
	
	@Test
	public void getLast_OnlyUseAddLast_Integers(){
		integerList.addLast(1);
		integerList.addLast(2);
		integerList.addLast(3);
		integerList.addLast(4);
		integerList.addLast(5);
		assertEquals(5, integerList.getLast());
	}
	
	@Test
	public void getLast_OnlyUseAddLast_Strings(){
		stringList.addLast("One");
		stringList.addLast("Two Two");
		stringList.addLast("Three Three Three");
		stringList.addLast("Four Four Four Four");
		stringList.addLast("Five Five Five Five Five");
		assertEquals("Five Five Five Five Five", stringList.getLast());
	}
	
	
	@Test
	public void getLast_RegularAdd_ThenAddLast_Integers(){
		integerList.add(0, 1); // add 1 to index 0
		integerList.add(1, 4); // add 4 to index 1
		integerList.add(2, 2); // add 2 to index 2
		integerList.addLast(200); // Then add 200 with addLast()
		assertEquals(200, integerList.getLast()); 
	}	
	
	@Test
	public void getLast_RegularAdd_ThenAddLast_Strings(){
		stringList.add(0, "One"); // add 1 to index 0
		stringList.add(1, "Two Two"); // add 4 to index 1
		stringList.add(2, "Three Three Three"); // add 2 to index 2
		stringList.addLast("Last"); // Then add 200 with addLast()
		assertEquals("Last", stringList.getLast());
	}	
	
	@Test
	public void getLast_FromMixedList(){
		assertEquals("is a mix", alreadyDefinedMixedDoublyLinkedList.getLast()); // Check above how this mixed list is already declared above
	}
	
	
/**
 * Tests for the removeFirst()
 */
	@Test
	public void removeFirst_Integers(){
		integerList.add(0, 20); // We will be removing 20
		integerList.add(1, 15); // 15 will be the new head
		integerList.add(2, 100);
		integerList.removeFirst();
		assertEquals(15, integerList.getFirst());
		
		// Uncomment below to see representation of the list after everything is ran.
		// integerList.printArray(integerList.toArray());
	}
	
	@Test
	public void removeFirst_Strings(){
		stringList.add(0, "One"); // We will be removing "One"
		stringList.add(1, "Two Two"); // "Two Two" will be the new head
		stringList.add(2, "Three Three Three"); 
		stringList.removeFirst();
		assertEquals("Two Two", stringList.getFirst());
		
		// Uncomment below to see representation of the list after everything is ran.
		// integerList.printArray(integerList.toArray());
	}
	
	@Test
	public void removeFirst_MixedList(){
		alreadyDefinedMixedDoublyLinkedList.removeFirst(); // Removes integer 5 from the list
		assertEquals("this", alreadyDefinedMixedDoublyLinkedList.getFirst());
		
		// Uncomment below to see representation of the list after everything is ran.
		// alreadyDefinedMixedDoublyLinkedList.printArray(alreadyDefinedMixedDoublyLinkedList.toArray());
		}
	
	@Test
	public void removeFirst_Integers_Size(){
		integerList.add(0, 20); // We will be removing 20
		integerList.add(1, 15); // 15 will be the new head
		integerList.add(2, 100);
		integerList.removeFirst();
		assertEquals(2, integerList.size());
		integerList.removeFirst(); // Remove the first again
		assertEquals(1, integerList.size());
		integerList.removeFirst(); // Remove the first once more
		assertEquals(0, integerList.size());
	}
	
	@Test
	public void removeFirst_MixedList_Size(){
		alreadyDefinedMixedDoublyLinkedList.removeFirst(); // Removes integer 5 from the list
		assertEquals(3, alreadyDefinedMixedDoublyLinkedList.size());
		alreadyDefinedMixedDoublyLinkedList.removeFirst(); // Removes integer "this" from the list
		assertEquals(2, alreadyDefinedMixedDoublyLinkedList.size());
		alreadyDefinedMixedDoublyLinkedList.removeFirst(); // Removes integer 100 from the list
		assertEquals(1, alreadyDefinedMixedDoublyLinkedList.size());
		alreadyDefinedMixedDoublyLinkedList.removeFirst(); // Removes integer "is a mix" from the list
		assertEquals(0, alreadyDefinedMixedDoublyLinkedList.size());
		
		// Uncomment below to see representation of the list after everything is ran.
		// alreadyDefinedMixedDoublyLinkedList.printArray(alreadyDefinedMixedDoublyLinkedList.toArray());
		}
	
/**
 * Tests for the removeLast method
 */
	@Test
	public void removeLast_Integers(){
		integerList.add(0, 20); 
		integerList.add(1, 15); // 15 will be the new head
		integerList.add(2, 100); // We will be removing 100
		integerList.removeLast();
		assertEquals(15, integerList.getLast());
		
		// Uncomment below to see representation of the list after everything is ran.
		// integerList.printArray(integerList.toArray());
	}
	
	@Test
	public void removeLast_Strings(){
		stringList.add(0, "One"); // We will be removing "One"
		stringList.add(1, "Two Two"); // "Two Two" will be the new head
		stringList.add(2, "Three Three Three"); 
		stringList.removeLast();
		assertEquals("Two Two", stringList.getLast());
		
		// Uncomment below to see representation of the list after everything is ran.
		// integerList.printArray(integerList.toArray());
	}
	
	@Test
	public void removeLast_MixedList(){
		alreadyDefinedMixedDoublyLinkedList.removeLast(); // Removes integer 5 from the list
		assertEquals(100, alreadyDefinedMixedDoublyLinkedList.getLast());
		
		// Uncomment below to see representation of the list after everything is ran.
		// alreadyDefinedMixedDoublyLinkedList.printArray(alreadyDefinedMixedDoublyLinkedList.toArray());
		}
	
/**
 * Tests for the remove method at a specific index
 */
	@Test
	public void remove_Integers(){
		integerList.add(0, 20); 
		integerList.add(1, 15); 
		integerList.add(2, 100); 
		integerList.add(3, 44); 
		
		integerList.remove(1); // Remove 15 at index 1
		integerList.remove(2); // Remove 44 at index 2 (since it was moved down)
		
		assertEquals(20, integerList.getFirst());
		assertEquals(100, integerList.getLast());
		
		// Uncomment below to see representation of the list after everything is ran.
		// integerList.printArray(integerList.toArray());
	}
	
	@Test
	public void remove_Integers_Size(){
		for(int i = 0; i < 30; i++){ // Size will be 30 after for-loop
			integerList.add(i);
		}
		assertEquals(30, integerList.size()); 
		
		for(int j = 0; j < 15; j++){ // Remove 15 elements
			integerList.remove(j);
		}
		assertEquals(15, integerList.size()); // Size should be 15 after for-loop.
	}
	
	@Test
	public void remove_Strings(){
		stringList.add(0, "One"); 
		stringList.add(1, "Two Two"); 
		stringList.add(2, "Three Three Three"); 
		stringList.add(3, "Four Four Four Four"); 
		stringList.remove(1); // "Two Two" will get removed
		stringList.remove(2); // "Four Four Four Four" will get removed
		assertEquals("One", stringList.getFirst());
		assertEquals("Three Three Three", stringList.getLast());

		// Uncomment below to see representation of the list after everything is ran.
		// integerList.printArray(integerList.toArray());
	}
	
	@Test
	public void remove_Strings_Size(){
		stringList.add(0, "One"); 
		stringList.add(1, "Two Two"); 
		stringList.add(2, "Three Three Three"); 
		stringList.add(3, "Four Four Four Four"); 
		stringList.remove(1); // "Two Two" will get removed
		stringList.remove(2); // "Four Four Four Four" will get removed
		stringList.remove(0); // "One" will get removed.
		
		assertEquals(1, stringList.size()); // Size should be 1 now.
		stringList.remove(0);
		assertEquals(0, stringList.size()); // Size should be 0 now.
	}
	
	
/**
 * Tests for indexOf() method
 */
	@Test
	public void indexOf_Integers(){
		integerList.add(15); // First occurrence for 15
		integerList.add(30);
		integerList.add(13); // First occurrence for 13
		integerList.add(15);
		integerList.add(900);
		integerList.add(13);
		assertEquals(2, integerList.indexOf(13));
		assertEquals(0, integerList.indexOf(15));
	}
	
	@Test
	public void indexOf_Strings(){
		stringList.add(0, "One");
		stringList.add(1, "Two Two"); // First occurrence of "Two Two"
		stringList.add(2, "Three Three Three"); // First occurrence of "Three Three Three"
		stringList.add(3, "Four Four Four Four"); 
		stringList.add(4, "Two Two");
		stringList.add(5, "Five Five Five Five Five"); 
		stringList.add(6, "Two Two"); 
		stringList.add(5, "Three Three Three"); 
		stringList.add(5, "One"); 
		assertEquals(2, stringList.indexOf("Three Three Three"));
		assertEquals(1, stringList.indexOf("Two Two"));
	}
	
	@Test
	public void indexOf_Mix(){
		doublyLinkedList.add(3);
		doublyLinkedList.add(10);
		doublyLinkedList.add("hello");
		doublyLinkedList.add(900); // First occurrence of 900 - index 3
		doublyLinkedList.add("world"); // First occurrence of "world" - index 4
		doublyLinkedList.add(10);
		doublyLinkedList.add("world");
		doublyLinkedList.add("world"); 
		doublyLinkedList.add(900); 
		doublyLinkedList.add(100);
		assertEquals(4, doublyLinkedList.indexOf("world"));
		assertEquals(3, doublyLinkedList.indexOf(900));
	}
	
	
/**
 * Tests for indexOf() method
 */
	@Test
	public void lastIndexOf_Integers(){
		integerList.add(15); 
		integerList.add(30);
		integerList.add(13); 
		integerList.add(15); // last occurrence for 15 - index 3
		integerList.add(900);
		integerList.add(13); // last occurrence for 13 - index 5
		assertEquals(5, integerList.lastIndexOf(13));
		assertEquals(3, integerList.lastIndexOf(15));
	}
	
	@Test
	public void lastIndexOf_Strings(){
		stringList.add(0, "One");
		stringList.add(1, "Two Two"); 
		stringList.add(2, "Three Three Three"); 
		stringList.add(3, "Four Four Four Four"); 
		stringList.add(4, "Two Two");
		stringList.add(5, "Five Five Five Five Five"); 
		stringList.add(6, "Two Two"); // Last occurrence of "Two Two" - index 6
		stringList.add(7, "Three Three Three"); // Last occurrence of "Three Three Three" - index 7
		stringList.add(8, "One"); 
		assertEquals(7, stringList.lastIndexOf("Three Three Three"));
		assertEquals(6, stringList.lastIndexOf("Two Two"));
	}
	
	@Test
	public void lastIndexOf_Mix(){
		doublyLinkedList.add(3);
		doublyLinkedList.add(10);
		doublyLinkedList.add("hello");
		doublyLinkedList.add(900);
		doublyLinkedList.add("world");
		doublyLinkedList.add(10);
		doublyLinkedList.add("world");
		doublyLinkedList.add("world"); // Last occurrence of "world" - index 7
		doublyLinkedList.add(900); // Last occurrence of 900 - index 8
		doublyLinkedList.add(100);
		assertEquals(7, doublyLinkedList.lastIndexOf("world"));
		assertEquals(8, doublyLinkedList.lastIndexOf(900));
	}
	
	
/**
 * Tests for the isEmpty method
 */
	@Test
	public void isEmpty_Test(){
		for(int i = 0; i < 11; i++){ // Adds integers 0-10
			doublyLinkedList.add(i);
		}
		doublyLinkedList.clear(); // Clears the list
		assertTrue(doublyLinkedList.isEmpty());
	}
	
/**
 * Tests for the clear method
 */
	@Test
	public void clear_Integers_Size50(){
		for(int i = 0; i < 50; i++){ // Adds integers 0-49
			doublyLinkedList.add(i);
		}
		assertEquals(50, doublyLinkedList.size()); // Size will be 50 
		doublyLinkedList.clear(); // Clear list
		assertTrue(doublyLinkedList.isEmpty()); 
	}
	
///**
// * Test DoublyLinkedList Iterator
// */
//	@Test
//	public void iteratorTest(){
//		DoublyLinkedList<Object> list = new DoublyLinkedList<>();
//		for(int i = 0; i < 25; i++){
//			list.add(i, i); // add int from 0 to 24 for a list of size 25.
//		}
//		for(Object obj : list){
//			System.out.print(obj);
//		}
//		
//		if(list.iterator().hasNext()){
//			System.out.println(list.iterator().next());
//		}		
//	}
	
/**
 * Releases external resources that was done in the @before class after each Test.
 */
	@After
	public void tearDown() throws Exception {
		doublyLinkedList = null;
		alreadyDefinedMixedDoublyLinkedList = null; 
		integerObject1 = null;
		integerObject2 = null;
		integerObject3 = null;
		integerList = null;
		stringList = null;
	}
}
