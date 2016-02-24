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
	
	// Iterator<Object> doublyLinkedIterator = list.iterator();

	
	public static void main(String[] args){
		DoublyLinkedList<Object> list = new DoublyLinkedList<>();
		list.addFirst(2);
		list.addFirst(10); 
//		printArray(list.toArray());
		
		list.add(4);
		list.add(15);
		printArray(list.toArray());
	}
	
	/**
	 * Default Constructor for DoublyLinkedList
	 */
	public DoublyLinkedList()
	{
		head = null;
		tail = null;
		numberOfElementsInList = 0;
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
									  // This will be reset when remove() is used.
		private int index = 0; // Keeps track of index for use in next() and remove()
		
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

//		public boolean hasPrevious() {
//			return index > 0; // if the index is greater than 0, then there is at least 1 element in the DoublyLinkedList. Return true if there is.
//		}
//
//		public E previous() {
//			if(hasPrevious() == false){ 
//				throw new NoSuchElementException(); // if hasPrevious() returns false (meaning if there is no previous node), will throw exception.
//			}
//			current = current.prev; // Takes the previous Node 
//			index--;
//			lastUsed = current; // Will set this Node to lastUsed, so that way if remove() or add() is used, it won't throw an exception.
//			return current.data;
//		}
//
//		public int nextIndex() {
//			return index;
//		}
//
//		public int previousIndex() {
//			return index-1;
//		}
//
//		public void set(E element) {
//			if(lastUsed == null){
//				throw new IllegalStateException();
//			}
//			lastUsed.data = element;
//		}
//
//		// Add element to the DoublyLinkedList
//		public void add(E element) {
//			Node x = current.prev;
//			Node y = new Node(null, null, null);
//			Node z = current;
//			
//			y.data = element;
//			
//			x.next = y;
//			y.next = z;
//			
//			z.prev = y;
//			y.prev = x;
//			numberOfElementsInList++;
//			index++;
//			lastUsed = null; // Resets lastUsed to null
//		}
	}

	
	
	//==============================================================================================================================//
	
	/**
	 * Add element to the front of the doubly linked list
	 * @param element
	 */
	@Override
	public void addFirst(E element) 
	{
			add(0, element); // Add element to index 0
	}
	
	/**
	 * Add element to the end of the doubly linked list
	 * @param element
	 */
	@Override
	public void addLast(E element)
	{
		add(element); // Uses add() because add() puts at the end anyways
	}
	/**
	 * Adds an object to the end of the list.
	 * @param obj the object to add to the list
	 * @return always succeeds, so always returns true
	 */
	public boolean add(E element) {
		Node newNode = new Node(element);
		
		if (isEmpty()) {
			head = newNode;
		}
		else {
			newNode.prev = tail;
			tail.next = newNode;
		}
		
		tail = newNode;
		numberOfElementsInList++;
		return true;
	}

	/**
	 * Add item to list of specified index
	 * 
	 * @throws IndexOutOfBoundsException
	 */
	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException
	{
		if(index < 0 || index > size()){
			throw new IndexOutOfBoundsException();
		}
		
		Node newNode = new Node(element);
		
		if(isEmpty() == true){
			head = newNode;
			tail = newNode;
		}
		else if(index == 0){
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
		}
		else if(index == size()){
			newNode.prev = tail;
			tail.next = newNode;
			tail = newNode;
		}
		else{
			assert index >= 1;
			Node previousNode = getNodeAtIndex(index-1);
			
			newNode.next = previousNode.next;
			newNode.next.prev = newNode;
			
			newNode.prev = previousNode;
			previousNode.next = newNode;
		}
		numberOfElementsInList++;
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
		return getNodeAtIndex(size()+1).data;
	}

	/**
	 * Gets the element at the specified index given.
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index > size()){
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
		assert index >= 0 && index < size();
		
		int mid = size() / 2;
		Node node = null;

		if(index <= mid){
			node = head;
			for(int i = 0; i < index; i++){
				node = node.next; 
			}
		}
		else{
			node = tail;
			for(int i = size()-1; i > index; i--){
				node = node.prev;
			}
		}
		return node;
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

	/**
	 * Removes the element of the specified index
	 * @throws IndexOutOfBoundsException - if the specified index is less than 0 or more than the size
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= size()){
			throw new IndexOutOfBoundsException();
		}
		E result = null;
		
		if(index == 0){
			result = head.data;
			head = head.next;
			if(head == null){
				tail = null;
			}
		}
		else{
			Node nodePrevious = getNodeAtIndex(index-1);
			Node nodeToRemove = nodePrevious.next;
			Node nodeAfter = nodeToRemove;
			
			nodePrevious.next = nodeAfter;
			if(nodeAfter != null){
				nodeAfter.prev = nodePrevious;
			}
			else{
				tail = nodeAfter;
			}
			result = nodeToRemove.data;
			
			if(index == size()-1){
				tail = nodePrevious;
			}
		}
		numberOfElementsInList--;
		return result;
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
		// DoublyLinkedListIterator lastIndexIterator = new DoublyLinkedListIterator();
		int index = size();
		Node current = tail;

		while(current != null){
			current = current.prev;
			//System.out.println(current.);
		}
		return -1;
	}

	/**
	 * Checks to see how many elements are in the DoublyLinkedList
	 * @return size
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
		return head == null && size() == 0;
	}

	/**
	 * Makes the head null which will let Garbage Collection take over to clear the list.
	 * Resets the number of elements in the list to 0 since all is removed.
	 */
	@Override
	public void clear() {
		head = null;
		numberOfElementsInList = 0;
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
			System.out.printf("[%s ", elements + "]");
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

	public Node(E data){
		this.data = data;
		this.prev = null;
		this.next = null;
	}
	
	public Node(E data, Node next, Node prev){
		this.data = data;
		this.next = next;
		this.prev = prev;
	}
	
//	
//	/**
//	 * Gets the data
//	 */
//	public E getData(){
//		return data;
//	}
//	
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
