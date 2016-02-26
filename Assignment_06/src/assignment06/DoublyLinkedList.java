package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * [The Problem] 
 * We have been asked to construct a list representation that can accommodate any type of item. 
 * We decide to implement a doubly linked list and compare its performance to Java's ArrayList to determine which is more efficient.
 * 
 * Also, notice the required Big-O behavior for each method when implemented for a doubly-linked list. Adhere to the following rules:
 * @author Andy Dao and Ho Lam Yip
 */
public class DoublyLinkedList<E> implements List<E>, Iterable<E>
{
	int numberOfElementsInList; // Number of elements in List
	private Node node; // an Node object
	private Node head; // The head of the linkedList
	private Node tail; // The tail of the linkedList
	
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
		
		/**
		 * Returns true if the iteration has more elements. (In other words, returns true if next would return an element rather than throwing an exception.)
		 * @return True if the iteration has more elements.
		 */
		public boolean hasNext() {	
			return index < numberOfElementsInList; // if index is less than the number of elements in the DoublyLinkedList, return true
		}

		/**
		 * Returns the next element in the iteration.
		 * @return The next element in the iteration.
		 */
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
		
		/**
		 * Removes from the underlying collection the last element returned by this iterator (optional operation). 
		 * This method can be called only once per call to next. The behavior of an iterator is unspecified if the underlying collection is modified while 
		 * the iteration is in progress in any way other than by calling this method.
		 * 
		 * @throws IllegalStateException if next() has not been called.
		 */
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
	}

	
	//==============================================================================================================================//
	
	/**
	 * Inserts the specified element at the beginning of this list.
	 * @param element - element to add
	 */
	@Override
	public void addFirst(E element) 
	{
		add(0, element); // Add element to index 0
	}
	
	/**
	 * Inserts the specified element at the end of the list. O(1) for a doubly-linked list.
	 * @param element - the element to add
	 */
	@Override
	public void addLast(E element)
	{
		add(element); // Uses add() because add() puts at the end anyways
	}

	/**
	 * Inserts the specified element at the specified position in this list. Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices).
	 * @param index - index at which the specified element is to be inserted
	 * @param element - element to be inserted
	 * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size())
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
			Node previousNode = getNodeAtIndex(index-1);
			
			newNode.next = previousNode.next;
			newNode.next.prev = newNode;
			
			newNode.prev = previousNode;
			previousNode.next = newNode;
		}
		numberOfElementsInList++;
	}
	
	/**
	 * Appends the specified element to the end of this list.
	 * @param element - the object to add to the list
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
	 * Returns the first element in this list.
	 * @return The first element in this list
	 * @throws NoSuchElementException - if this list is empty
	 */
	@Override
	public E getFirst() throws NoSuchElementException {
		if(size() == 0){
			throw new NoSuchElementException();
		}
		return getNodeAtIndex(0).data;
	}

	/**
	 * Returns the last element in this list.
	 * @return The last element in this list
	 * @throws NoSuchElementException - if this list is empty
	 */
	@Override
	public E getLast() throws NoSuchElementException {
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		return getNodeAtIndex(size()).data;
	}

	/**
	 * Returns the element at the specified position in this list as long as the index given is in the range of index < 0 || index > size(). If it is
	 * not in the range, will throw IndexOutOfBoundsException()
	 * @param index - Index of the element to return
	 * @throws IndexOutOfBoundsException If index is not in the range of 0 to size of list.
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
	 * Private helper method to get the Node of the specified index.
	 * @return The Node at the specified index.
	 * @throws IndexOutOfBoundsException If index is not in the range of 0 to size of list.
	 */
	private Node getNodeAtIndex(int index){
		if(index < 0 || index > size()){
			throw new IndexOutOfBoundsException();
		}
		int mid = size() / 2; 
		Node node = null;
		// If the index is less than mid, start from the beginning and work on the way up to the index 
		if(index <= mid){
			node = head;
			for(int i = 0; i < index; i++){
				node = node.next; // Grab the Node and return it at end
			}
		}
		else{
			// If the index is greater than mid, start from the very end at the last node index, and work on way down
			node = tail;
			for(int i = size()-1; i > index; i--){
				node = node.prev; // Grab the Node and return it at the end
			}
		}
		return node;
	}

	/**
	 * Removes and returns the first element from this list.
	 * @return The first element from this list
	 * @throws NoSuchElementException - if this list is empty
	 */
	@Override
	public E removeFirst() throws NoSuchElementException {
		if(size() == 0){
			throw new NoSuchElementException();
		}
		return remove(0);
	}

	/**
	 * Removes and returns the last element from this list.
	 * @return The last element from this list
	 * @throws NoSuchElementException - if this list is empty
	 */
	@Override
	public E removeLast() throws NoSuchElementException {
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		return remove(size()-1);
	}

	/**
	 * Removes and returns the element at the specified position in the list. 
	 * @param index - At which the specified element is to be removed.
	 * @throws IndexOutOfBoundsException If index is out of range (index < 0 || index > size()) O(N) for a doubly-linked list.
	 * @throws NullPointerException If the list is empty.
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >size()){
			throw new IndexOutOfBoundsException();
		}
		
		E element = getNodeAtIndex(index).data;
		remove(element);
		return element;
	}
	
	/**
	 * Helper method to remove the element at the specified index
	 * @param data - The element to remove
	 */
	private void remove(E data){
		if (size() == 1 && head.data.equals(data)) {
			head = tail = null; // removing the only item from list 
			numberOfElementsInList--;
			return;
	    }
		if (head.data.equals(data)) { // removing head
	        head = head.next;
	        head.prev = null;
	        numberOfElementsInList--;
	        return;
	    }
		if (tail.data.equals(data)) { // removing tail
	        tail = tail.prev;
	        tail.next = null;
	        numberOfElementsInList--;
	        return;
	    }
		for (Node current = head; current != null; current = current.next) {
			if (current.data.equals(data)) {
				Node prev = current.prev;
				Node next = current.next;
				prev.next = next;
				next.prev = prev;
				numberOfElementsInList--;
				return;
			}
		}
	}
	
	/**
	 * Returns the index of the first occurrence of the specified element in the list, or -1 if this list does not contain the element. 
	 * O(N) for a doubly-linked list.
	 * 
	 * @param element - Element to search for.
	 * @return The index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element

	 */
	@Override
	public int indexOf(E element) {
		Node current = head;
		int index = 0;
		
		// Make sure that the head is not null
		while(current != null){
			if(current.data.equals(element)){
				return index;
			}
			current = current.next;
			index++;
		}
		return -1;
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element. 
	 * More formally, returns the highest index i such that (element==null ? get(i)==null : element.equals(get(i))), or -1 if there is no such index.
	 * 
	 * @param element - element to search for
	 * @return The index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element
	 */
	@Override
	public int lastIndexOf(E element) {
		Node current = tail;
		int index = size()-1; // Start index count at the end of list and increment down until no element found
							  // or element has been found.
		
		// Make sure that the tail is not null
		while(current != null){
			if(current.data.equals(element)){
				return index;
			}
			current = current.prev;
			index--;
		}
		return -1;
	}

	/**
	 * Returns the number of elements in this list.
	 * @return The number of elements in this list.
	 */
	@Override
	public int size() {
		return numberOfElementsInList;
	}

	/**
	 * Returns true if this list contains no elements.
	 * @return true if this list contains no elements.
	 */
	@Override
	public boolean isEmpty() {
		return head == null && size() == 0;
	}

	/**
	 * Makes the head null which will let Garbage Collection take over to clear the list.
	 * Resets the number of elements in the list to 0 since all is removed.
	 * 
	 * Removes all of the elements from this list (optional operation). The list will be empty after this call returns.
	 */
	@Override
	public void clear() {
		head = null;
		numberOfElementsInList = 0;
	}

	/**
	 * Returns an array containing all of the elements in this list in proper sequence (from first to last element). O(N) for a doubly-linked list.
	 * @return an array containing all of the elements in this list in proper sequence
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
	 * Helps to print the list. Mostly used for visual representation when implementing the code.
	 * @param inputArray - The Array to print out.
	 * @return System.out.println() of the Array 
	 */
	public static <E> void printArray(E[] inputArray){
		for(E elements : inputArray){
			System.out.printf("[%s ", elements + "]");
		}
		System.out.println();
	}

/**
 * Inner Class which defines the Node class.
 * A representation of a node (element) in an XML document. This interface extends the standard DOM Node interface with methods for getting and 
 * setting the value of a node, for getting and setting the parent of a node, and for removing a node.
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
}
}
