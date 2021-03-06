/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Hamadi McIntosh
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		try {
			a = emptyList.remove(0);
			fail("Check out of bounds.");
		}
		catch (IndexOutOfBoundsException e) {

		}

		String b;
		try {
			b = shortList.remove(-1);
			fail("Check out of bounds.");
		}
		catch (IndexOutOfBoundsException e) {

		}

		try {
			b = shortList.remove(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {

		}

		a = longerList.remove(3);
		assertEquals("Remove: check a is correct ", 3, a);
		assertEquals("Remove: check element 3 is correct ", (Integer)4, longerList.get(3));
		assertEquals("Remove: check size is correct ", (LONG_LIST_LENGTH-1),
				longerList.size());

		b = shortList.remove(1);
		assertEquals("Remove: check b is correct ", "B",b);
		assertEquals("Remove: check element 0 is correct ", "A", shortList.get(0));
		assertEquals("Remove: check size is correct ", 1, shortList.size());
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
		//test short list, add should throw an exception
		try {
			shortList.add(null);
			fail("Check null pointer");
		}
		catch (NullPointerException e) {

		}

		emptyList.add(0);
		emptyList.add(1);
		emptyList.add(2);
		// test empty list, first contents, then size
		assertEquals("Check first", (Integer)0, emptyList.get(0));
		assertEquals("Check second", (Integer)1, emptyList.get(1));
		assertEquals("Check third", (Integer)2, emptyList.get(2));

		assertEquals("Check size", 3, emptyList.size);

		shortList.add("C");
		shortList.add("D");
		shortList.add("E");
		// test short list, first contents, then size
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		assertEquals("Check third", "C", shortList.get(2));
		assertEquals("Check fourth", "D", shortList.get(3));
		assertEquals("Check fifth", "E", shortList.get(4));

		assertEquals("Check size", 5, shortList.size);

		for (int i = LONG_LIST_LENGTH; i < (LONG_LIST_LENGTH+3); i++)
		{
			longerList.add(i);
		}
		for(int i = 0; i<(LONG_LIST_LENGTH+3); i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}

		assertEquals("Check size", (LONG_LIST_LENGTH+3), longerList.size);
		
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		assertEquals("Check size", 0, emptyList.size);
		assertEquals("Check size", 2, shortList.size);
		assertEquals("Check size", LONG_LIST_LENGTH, longerList.size);
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		//test short list, add should throw an exception
		try {
			shortList.add(0,null);
			fail("Check null pointer");
		}
		catch (NullPointerException e) {

		}

		try {
			shortList.add(-1,"C");
			fail("Check out of bounds.");
		}
		catch (IndexOutOfBoundsException e) {

		}

		try {
			shortList.add(3,"C");
			fail("Check out of bounds.");
		}
		catch (IndexOutOfBoundsException e) {

		}

		shortList.add(2,"C");
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		assertEquals("Check third", "C", shortList.get(2));

		assertEquals("Check size", 3, shortList.size);

		shortList.add(0, "D");
		assertEquals("Check first", "D", shortList.get(0));
		assertEquals("Check first", "A", shortList.get(1));
		assertEquals("Check second", "B", shortList.get(2));
		assertEquals("Check third", "C", shortList.get(3));

		assertEquals("Check size", 4, shortList.size);

		shortList.add(2, "E");
		assertEquals("Check first", "D", shortList.get(0));
		assertEquals("Check first", "A", shortList.get(1));
		assertEquals("Check second", "E", shortList.get(2));
		assertEquals("Check second", "B", shortList.get(3));
		assertEquals("Check third", "C", shortList.get(4));

		assertEquals("Check size", 5, shortList.size);

		emptyList.add(0,0);
		emptyList.add(0,1);
		emptyList.add(2,2);
		emptyList.add(1,3);
		// test empty list, first contents, then size
		assertEquals("Check first", (Integer)1, emptyList.get(0));
		assertEquals("Check second", (Integer)3, emptyList.get(1));
		assertEquals("Check third", (Integer)0, emptyList.get(2));
		assertEquals("Check third", (Integer)2, emptyList.get(3));

		assertEquals("Check size", 4, emptyList.size);
		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		int a;
		String b;
	    try {
	    	b = shortList.set(0, null);
	    	fail("Check null pointer");
	    }
	    catch (NullPointerException e) {

	    }

	    try {
	    	b = shortList.set(-1, "C");
	    	fail("Check out of bounds.");
	    }
	    catch (IndexOutOfBoundsException e) {

	    }

	    try {
	    	b = shortList.set(2, "C");
	    	fail("Check out of bounds.");
	    }
	    catch (IndexOutOfBoundsException e) {

	    }

	    try {
	    	a = emptyList.set(0, 0);
	    	fail("Check out of bounds.");
	    }
	    catch (IndexOutOfBoundsException e) {

	    }

	    b = shortList.set(0, "C");
	    assertEquals("Set: check b is correct ", "A", b);
	    assertEquals("Set: check element 0 is correct", "C", shortList.get(0));

	    b = shortList.set(1, "D");
	    assertEquals("Set: check b is correct ", "B", b);
	    assertEquals("Set: check element 1 is correct", "D", shortList.get(1));

	    a = longerList.set(0, 10);
	    assertEquals("Set: check a is correct ", 0, a);
	    assertEquals("Set: check element 0 is correct", (Integer)10, longerList.get(0));

	    a = longerList.set((LONG_LIST_LENGTH-1), 11);
	    assertEquals("Set: check a is correct ", 9, a);
	    assertEquals("Set: check element 9 is correct", (Integer)11,
	    		longerList.get(LONG_LIST_LENGTH-1));

	    a = longerList.set(6, 12);
	    assertEquals("Set: check a is correct ", 6, a);
	    assertEquals("Set: check element 6 is correct", (Integer)12, longerList.get(6));
	    
	}
	
	
	
	
}
