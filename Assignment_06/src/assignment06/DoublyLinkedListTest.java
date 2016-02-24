package assignment06;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Andy Dao
 */
public class DoublyLinkedListTest {
	
	private Random randomInt;
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
// Initiate iterator
		
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
		
		// Uncomment printArray method below to see representation
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

		// Uncomment printArray method below to see representation
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
 * Tests for the removeFirst method
 */
	@Test
	public void removeFirst_Integers(){
		integerList.add(0, 20); // We will be removing 20
		integerList.add(1, 15); // 15 will be the new head
		integerList.add(2, 100);
		integerList.removeFirst();
		assertEquals(15, integerList.getFirst());
		
		// Uncomment to see representation
		// integerList.printArray(integerList.toArray());
	}
	
	@Test
	public void removeFirst_Strings(){
		stringList.add(0, "One"); // We will be removing "One"
		stringList.add(1, "Two Two"); // "Two Two" will be the new head
		stringList.add(2, "Three Three Three"); 
		stringList.removeFirst();
		assertEquals("Two Two", stringList.getFirst());
		
		// Uncomment to see representation
		// integerList.printArray(integerList.toArray());
	}
	
/**
 * Tests for the removeLast method
 */
	@Test
	public void removeLast_Test1(){
		
	}
	
/**
 * Tests for the remove method
 */
	@Test
	public void remove_Test1(){
		
	}
	
/**
 * Tests for the isEmpty method
 */
	@Test
	public void isEmpty_Test1(){
		
	}
	
/**
 * Tests for the clear method
 */
	@Test
	public void clear_Test1(){
		
	}
	
/**
 * Tests for the toArray method
 */
	@Test
	public void toArray_Test1(){
		
	}
	
/**
 * Releases external resources that was done in the @before class
 */
	@After
	public void tearDown() throws Exception {
	}
}
