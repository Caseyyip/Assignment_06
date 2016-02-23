package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * [The Problem] 
 * We have been asked to construct a list representation that can accommodate any type of item. 
 * We decide to implement a doubly linked list and compare its performance to Java's ArrayList to determine which is more efficient.
 * 
 * [Requirements]
 * Please look at your feedback on Assignment 03 to make sure you understand the functionality of Iterators.  
 * As always, do not modify the file name, package name, or signatures of any methods. 
 * Implement every method in both interfaces according to the functionality described in the comments, and Java documentation. 
 * Your list should use 0-based indexing just like an array... 
 * 	- (the first item is considered at index 0). 
 * 
 * Also, notice the required Big-O behavior for each method when implemented for a doubly-linked list. Adhere to the following rules:
 * 		Add your DoublyLinkedList class to the assignment06 package. DO NOT MAKE A NEW PROJECT. Learn the difference between packages and projects. 
 * 		Include a zero-parameter constructor, public DoublyLinkedList(). This is known as the 'default constructor'.
 * 		Do not use Java's LinkedList class.
 * 
 * Create and submit tests for the DoublyLinkedList class. Consider carefully how to test for a broad range of inputs.
 * 
 * @author Andy Dao
 */
public class DoublyLinkedList<E> implements List<E>, Iterable<E>
{
	private Node something;
	private Node node; // an Node object
	private Node head; // The head of the linkedList
	private Node tail; // The tail of the linkedList
	
	
	/**
	 * Default Constructor for DoublyLinkedList
	 */
	public DoublyLinkedList()
	{
		head = null;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFirst(E element) {
		node = new Node(element, head, null);
	}

	/**
	 * Add the object o of type E, into the very end of the DoublyLinkedList
	 * If the head is null though, just add the object into the head since it'll be the first element.
	 */
	@Override
	public void addLast(E o) {
		if(head == null){ // If the head is null, just add the object into the head (first index)
			addFirst(o);
		}
		else{
			Node tempCheck = head; 
			while(tempCheck.next != null){ // As long as the Node doesn't have a null next...
				tempCheck = tempCheck.next; // make the tempCheck to the next Node. 
			}								// Keep doing this until one of the Nodes has a null next
			tempCheck.next = new Node(o, null);	// Add the 'E o' object to the very end and declare it's next, null.
		}
	}

	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException
	{
		
	}

	@Override
	public E getFirst() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E getLast() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E removeFirst() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E removeLast() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(E element) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lastIndexOf(E element) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Checks to see if the DoublyLinkedList is empty by checking if the head is null. 
	 * If the head is null, there are no elements.
	 */
	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

/**
 * Inner Class which defines the Node class.
 */
public class Node {
	private E data;
	private Node next;
	private Node previous;
	
	public Node(E data, Node next, Node previous){
		this.data = data;
		this.next = next;
		this.previous = previous;
	}
	
	/**
	 * Gets the data
	 */
	public E getData(){
		return data;
	}
	
	/**
	 * Gets the next Node
	 */
	public Node getNextNode(){
		return next;
	}
	
	/**
	 * Gets the previous Node
	 */
	public Node getPreviousNode()
	{
		return previous;
	}
	
	/**
	 * Sets the data
	 */
	public void setData(E mData){
		data = mData;
	}
	
	/**
	 * Sets the next Node
	 */
	public void setNextNode(Node mNode)
	{
		next = mNode;
		}
	
	/**
	 * Sets the previous Node
	 */
	public void setPreviousNode(Node pNode)
	{
		previous = pNode;
		}
	
	}

}
