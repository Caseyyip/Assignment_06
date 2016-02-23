package assignment06;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import assignment06.DoublyLinkedList.Node;

/**
 * @author Andy Dao
 */
public class DoublyLinkedListTest {
	
	private Node node;
	private Node head;
	private Integer integerObject1;
	private DoublyLinkedList<Object> doublyLinked = new DoublyLinkedList<Object>();
	
	/**
	 * Initiate objects in this setup method / setup test fixtures
	 */
	@Before
	public void setUp() throws Exception {
		integerObject1 = 5;
	}

	
	/**
	 * Tests for the addFirst method
	 */
	@Test
	public void addFirst_Test1() {
		assertFalse(doublyLinked.isEmpty());
	}
	
	
	/**
	 * Tests for the addLast method
	 */
	@Test
	public void addLast_Test1(){
		
	}
	
	/**
	 * Test for the add method
	 */
	@Test
	public void add_Test1(){
		
	}
	
	/**
	 * Tests for the getFirst method
	 */
	@Test
	public void getFirst_Test1(){
		
	}
	
	/**
	 * Tests for the getLast method
	 */
	@Test
	public void getLast_Test1(){
		
	}
	
	/**
	 * Tests for the get method
	 */
	@Test
	public void get_Test1(){
		
	}
	
	/**
	 * Tests for the removeFirst method
	 */
	@Test
	public void removeFirst_Test1(){
		
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
