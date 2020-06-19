package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * @author Hamadi McIntosh
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		this.head = new LLNode<E>();
		this.tail = new LLNode<E>();
		this.head.next = tail;
		this.tail.prev = head;
		this.size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) throws NullPointerException
	{
		if (element==null) {
			throw new NullPointerException("Linked list cannot store null pointers.");
		}
		add(this.size, element);
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) throws IndexOutOfBoundsException
	{
		if ((index>(this.size-1)) || (index<0)) {
			throw new IndexOutOfBoundsException("Linked list indexing begins at 0 " +
					"and linked list has size " + this.size + ".");
		}
		LLNode<E> curr = getNode(index);
		return curr.data;
	}
	
	private LLNode<E> getNode(int index) throws IndexOutOfBoundsException
	{
		if ((index>(this.size-1)) || (index<0)) {
			throw new IndexOutOfBoundsException("Linked list indexing begins at 0 " +
					"and linked list has size " + this.size + ".");
		}
		LLNode<E> curr;
		if (index < (this.size-index-1)) {
			curr = this.head.next;
			for (int i=0;i<index;i++) {
				curr = curr.next;
			}
		}
		else {
			curr = this.tail.prev;
			for (int i=(this.size-1);i>index;i--) {
				curr = curr.prev;
			}
		}
		return curr;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element )
			throws IndexOutOfBoundsException,NullPointerException
	{
		if (element==null) {
			throw new NullPointerException("Linked list cannot store null pointers.");
		}
		
		LLNode<E> addNode = new LLNode<E>(element);
		if ((index<0) || (index>this.size)) {
			throw new IndexOutOfBoundsException("Linked list indexing starts at 0 " +
					"and linked list has size " + this.size + ".");
		}
		else if (index==this.size) {
			addNode.next = this.tail;
			addNode.prev = this.tail.prev;
			addNode.next.prev = addNode;
			addNode.prev.next = addNode;
		}
		else {
			LLNode<E> curr = getNode(index);
			addNode.next = curr;
			addNode.prev = curr.prev;
			addNode.next.prev = addNode;
			addNode.prev.next = addNode;
		}
		this.size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		return this.size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) throws IndexOutOfBoundsException
	{
		if ((index<0) || (index>=this.size)) {
			throw new IndexOutOfBoundsException("Linked list indexing begins at 0 " +
					"and linked list has size " + this.size + ".");
		}
		LLNode<E> extractNode = getNode(index);
		extractNode.prev.next = extractNode.next;
		extractNode.next.prev = extractNode.prev;
		extractNode.next = null;
		extractNode.prev = null;
		this.size--;
		return extractNode.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element)
			throws IndexOutOfBoundsException, NullPointerException
	{
		if (element==null) {
			throw new NullPointerException("Linked list cannot store null pointers.");
		}
		if ((index<0) || (index>=this.size)) {
			throw new IndexOutOfBoundsException("Linked list indexing begins at 0 " +
					"and linked list has size " + this.size + ".");
		}
		LLNode<E> replaceNode = getNode(index);
		E removeElement = replaceNode.data;
		replaceNode.data = element;
		return removeElement;
	}   
	
	public String toString() {
		LLNode<E> curr = this.head.next;
		String listString = "Node 0: " + curr.data;
		for (int i=1;i<this.size;i++) {
			curr = curr.next;
			listString += "\nNode " + i + ": " + curr.data;
		}
		return listString;
	}
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	public LLNode() {
		this.data = null;
		this.prev = null;
		this.next = null;
	}

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
	
	public String toString() {
		return "" + this.data;
	}

}
