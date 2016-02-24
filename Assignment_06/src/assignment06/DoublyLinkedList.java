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
	private int numberOfElementsInList; // Number of elements in List
	private Node node; // an Node object
	private Node head; // The head of the linkedList
	private Node tail; // The tail of the linkedList
	
	private Node pre; // The element before the first item
	private Node post; // The element after the last item
	
	// Iterator<Object> doublyLinkedIterator = list.iterator();

	
	public static void main(String[] args){
		DoublyLinkedList<Object> list = new DoublyLinkedList<>();
		for(int i = 0; i < 10; i++){
			list.add(i, i+1);
		}
		list.add(8, 3);
		printArray(list.toArray());
		System.out.println("The number 2 shows up first first at index: " + list.indexOf(3));
		System.out.println("The number 2 shows up very last at index: " + list.lastIndexOf(3));
	}
	
	/**
	 * Default Constructor for DoublyLinkedList
	 */
	public DoublyLinkedList()
	{
		numberOfElementsInList = 0;
		
		node = new Node(null, null, null);
		node.prev = node;
		node.next = node;
	}

	/**
	 * Returns an element of type E
	 */
	@Override
	public Iterator<E> iterator()  {
		return new DoublyLinkedListIterator();
	}
	
	/**
	 * An Iterator Class for DoublyLinkedLists
	 */
	private class DoublyLinkedListIterator implements Iterator<E>{
		private Node current = node.next; // The node to be returned by next();
		private Node lastUsed = null; // The last node to be returned by either previous() or next()
									  // This will be resetted when remove() is used or if add() is used.
		private int index = 0; // Keeps track of index for use in next() and previous(), add() and remove()
		
		public boolean hasNext() {
			return index < numberOfElementsInList; // if index is less than the number of elements in the DoublyLinkedList, return true
		}

		public E next() {
			if(hasNext() == false){
				throw new NoSuchElementException();
			}
			lastUsed = current; // Makes the current node and remembers that it was last used 
								// Remember, current is already 
			E data = current.data; // Adds the element in current node to E data
			current = current.next; // makes the next node after the current node, THE current node.
			index++;
			return data; // return that data
		}

		public boolean hasPrevious() {
			return index > 0; // if the index is greater than 0, then there is at least 1 element in the DoublyLinkedList. Return true if there is.
		}

		public E previous() {
			if(hasPrevious() == false){ 
				throw new NoSuchElementException(); // if hasPrevious() returns false (meaning if there is no previous node), will throw exception.
			}
			current = current.prev; // Takes the previous Node 
			index--;
			lastUsed = current; // Will set this Node to lastUsed, so that way if remove() or add() is used, it won't throw an exception.
			return current.data;
		}

		public int nextIndex() {
			return index;
		}

		public int previousIndex() {
			return index-1;
		}
		
		public void remove() {
			if(lastUsed == null){
				throw new IllegalStateException();
			}
			Node x = lastUsed.prev;
			Node y = lastUsed.next;
			x.next = y;
			y.prev = x;
			numberOfElementsInList--;
			if(current == lastUsed){
				current = y;
			}
			else{
				index--;
			}
			lastUsed = null; // Resets lastUsed to null
		}

		public void set(E element) {
			if(lastUsed == null){
				throw new IllegalStateException();
			}
			lastUsed.data = element;
		}

		// Add element to the DoublyLinkedList
		public void add(E element) {
			Node x = current.prev;
			Node y = new Node(null, null, null);
			Node z = current;
			
			y.data = element;
			
			x.next = y;
			y.next = z;
			
			z.prev = y;
			y.prev = x;
			numberOfElementsInList++;
			index++;
			lastUsed = null; // Resets lastUsed to null
		}
	}

	
	
	//==============================================================================================================================//
	
	@Override
	public void addFirst(E element) 
	{
		Node tmp = new Node(element, head, null);
        if(head != null ) {
        	head.prev = tmp;
        	}
        head = tmp;
        if(tail == null) { 
        	tail = tmp;
        	}
		
		//add(0, element);
	}
	
	@Override
	public void addLast(E element)
	{
		add(size(), element);
	}

	/**
	 * Add item to list of specified index
	 * 
	 * @throws IndexOutOfBoundsException
	 */
	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException
	{
		if(index < 0 || index > size()){ // If the given index is less than 0 or is bigger than the list size
			throw new IndexOutOfBoundsException();
		}
		if(size() == 0){
			addFirst(element);
		}

		addBefore(getNodeAtIndex(index), element); // Add the node at the given index. 
												   // If there is already an element there, move that element 1 index to the right
	}
	
	/**
	 * This will add the element right before the given index in add().
	 * Helper method that creates a node for the element and will initiate it's prev and next values
	 */
	public Node addBefore(Node mNode, E element){
		Node addThisNode = new Node(null, null, null); // Create a whole new node
		addThisNode.data = element; // Add the element to the data part of the node
		addThisNode.prev = mNode.prev; // Take the Node that was at this index, takes its prev and declare the new added Node that prev.
		addThisNode.next = mNode; // Now make the Node that was at this index, and make it the value of the new added Node's next.
		addThisNode.next.prev = addThisNode; 
		addThisNode.prev.next = addThisNode;
		
		numberOfElementsInList++;
		return addThisNode;
	}

	/**
	 * Gets the first element in the list
	 * @throws NoSuchElementException if the head itself is null.
	 */
	@Override
	public E getFirst() throws NoSuchElementException {
		if(size() == 0){
			throw new NoSuchElementException();
		}
		return getNodeAtIndex(0).data;
	}

	/**
	 * Gets the last element in the list
	 * @throws NoSuchElementException if the head is null
	 */
	@Override
	public E getLast() throws NoSuchElementException {
		if(size() == 0){
			throw new NoSuchElementException();
		}
		return getNodeAtIndex(size()-1).data;
	}

	/**
	 * Gets the element at the specified index given.
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index > size() -1){
			throw new IndexOutOfBoundsException();
		}
		else{
			return getNodeAtIndex(index).data;
		}
	}
	
	/**
	 * Private helper method to get the node of the specified index
	 * @returns the node at the index
	 */
	private Node getNodeAtIndex(int index){
		Node getNode = new Node(null, null, null);
		if(index < size() / 2){
			getNode = node.next;
				for(int j = 0; j < index; j++){
					getNode = getNode.next;
				}
			}
		else{
			getNode = node;
			for(int j = size(); j > index; j--){ // Start at the end of the list, and increment down until it reaches 1 more than the index given
				getNode = getNode.prev;
			}
		}
		return getNode;
	}

	@Override
	public E removeFirst() throws NoSuchElementException {
		if(size() == 0){
			throw new NoSuchElementException();
		}
		return remove(0);
	}

	@Override
	public E removeLast() throws NoSuchElementException {
		if(size() == 0){
			throw new NoSuchElementException();
		}
		return remove(size()-1);
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index > size()-1){
			throw new IndexOutOfBoundsException();
		}
		else{
			Node rem = getNodeAtIndex(index);
			remove(rem);
			return rem.data; 
		}
	}
	
	/**
	 * Helper method to remove an element
	 */
	public void remove(Node rNode){
		rNode.prev.next = rNode.next;
		rNode.next.prev = rNode.prev;
		numberOfElementsInList--;
	}

	@Override
	public int indexOf(E element) {
		DoublyLinkedListIterator indexOfIterator = new DoublyLinkedListIterator();
		int index = 0;
		
		while(indexOfIterator.hasNext()){
			if(indexOfIterator.next().equals(element)){
				return index;
			}
			index++;
		}
		return -1;
	}

	/**
	 * Returns the index with this element of it's last occurrence.
	 * Returns -1 if the element is not found.
	 */
	@Override
	public int lastIndexOf(E element) {
		DoublyLinkedListIterator lastIndexIterator = new DoublyLinkedListIterator();
		int index = numberOfElementsInList;
		
		while(lastIndexIterator.hasPrevious()){
			if(lastIndexIterator.previous().equals(element)){
				return index;
			}
			index--;
		}
		return -1;
	}

	/**
	 * Checks to see how many elements are in the DoublyLinkedList
	 * @return
	 */
	@Override
	public int size() {
		return numberOfElementsInList;
	}

	/**
	 * Checks to see if the DoublyLinkedList is empty by checking if the head is null or if numberOfElementsInList is 0.
	 * If the head is null, there are no elements.
	 */
	@Override
	public boolean isEmpty() {
		return numberOfElementsInList == 0 || head == null;
	}

	@Override
	public void clear() {
		head = null;
	}

	/**
	 * Convert to an Array
	 */
	@Override
	public Object[] toArray() {
		Object[] convertToArray = new Object[size()];
		
		for(int i = 0; i < size(); i++){
			convertToArray[i] = getNodeAtIndex(i).data;
		}
		return convertToArray;
	}
	
	/**
	 * Helps to print the DoublyLinkedList 
	 */
	public static <E> void printArray(E[] inputArray){
		for(E elements : inputArray){
			System.out.printf("%s ", elements);
		}
		System.out.println();
	}

/**
 * Inner Class which defines the Node class.
 */
private class Node {
	private E data;
	private Node prev;
	private Node next;
	
	
	public Node(E data, Node next, Node prev){
		this.data = data;
		this.next = next;
		this.prev = prev;
	}
	
//	/**
//	 * Gets the data
//	 */
//	public E getData(){
//		return data;
//	}
//	
//	/**
//	 * Gets the next Node
//	 */
//	public Node getNextNode(){
//		return next;
//	}
//	
//	/**
//	 * Gets the previous Node
//	 */
//	public Node getPreviousNode()
//	{
//		return prev;
//	}
//	
//	/**
//	 * Sets the data
//	 */
//	public void setData(E mData){
//		data = mData;
//	}
//	
//	/**
//	 * Sets the next Node
//	 */
//	
//	public void setLinkNext(Node mNode)
//	{
//		next = mNode;
//	}
//	
//	/**
//	 * Sets the previous Node
//	 */
//	public void setLinkPrevious(Node pNode)
//	{
//		prev = pNode;
//	}
}
}
