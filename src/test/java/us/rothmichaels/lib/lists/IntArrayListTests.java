/*
 * IntArrayListTests.java
 *
 * Dec 19, 2011 
 */
package us.rothmichaels.lib.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 *
 * @author Roth Michaels (<i><a href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 *
 */
public class IntArrayListTests {
	static final int INITIAL_SIZE = 10;
	static final int TEST_ADD_POINTER_LOC = 11;
	static final int EXPANDED_INTERNAL_SIZE = 20;
	static final int[] TEST_ARRAY = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	static final int[] INTERNAL_TEST_ARRAY = new int[EXPANDED_INTERNAL_SIZE];
	static {
		System.arraycopy(TEST_ARRAY, 0, INTERNAL_TEST_ARRAY, 0, TEST_ARRAY.length);
	}
	
	IntArrayList testList;
	
	@Before
	public void setUp() {
		testList = new IntArrayList();
		testList.add(0);
		testList.add(1);
		testList.add(2);
		testList.add(3);
		testList.add(4);
		testList.add(5);
		testList.add(6);
		testList.add(7);
	}
	
	@Test
	public void testDefaultConstructor() {
		final IntArrayList testList = new IntArrayList();
		
		// test initial internal array
		assertEquals("internal addPointer", 0, testList.addPointer);
		assertArrayEquals("internal array", new int[INITIAL_SIZE], testList.data);
		
		// test initial output
		assertEquals("output size", 0, testList.size());
		assertArrayEquals("output array", new int[0], testList.toArray());
	}
	
	@Test
	public void testInitialSizeConstructor() {
		final int initialSize = 13;
		final IntArrayList testList = new IntArrayList(initialSize);
		
		// test initial internal array
		assertEquals("internal addPointer", 0, testList.addPointer);
		assertArrayEquals("internal array", new int[initialSize], testList.data);
		
		// test initial output
		assertEquals("output size", 0, testList.size());
		assertArrayEquals("output array", new int[0], testList.toArray());
	}
	
	@Test
	public void testAdd() {
		final int testValue = 6;
		final int[] expectedInternal = new int[INITIAL_SIZE];
		expectedInternal[0] = testValue;
		final IntArrayList testList = new IntArrayList();
		
		testList.add(testValue);
		
		// test internal
		assertEquals("internal addPointer", 1, testList.addPointer);
		assertArrayEquals("internal array", expectedInternal, testList.data);
		
		// test output
		assertEquals("output size", 1, testList.size());
		assertArrayEquals("output array", new int[] { testValue }, testList.toArray());
	}
	
	@Test
	public void testAddExpand() {
		final IntArrayList testList = new IntArrayList();
		testFill(testList);
		
		// test internal
		assertEquals("internal addPointer", TEST_ADD_POINTER_LOC, testList.addPointer);
		assertArrayEquals("internal array", INTERNAL_TEST_ARRAY, testList.data);
		
		// test output
		assertEquals("output size", TEST_ADD_POINTER_LOC, testList.size());
		assertArrayEquals("output array", TEST_ARRAY, testList.toArray());
	}
	
	@Test
	public void testInsert() {
		final int[] testArray = { 0, 1, 23, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		final int[] internalTestArray = new int[EXPANDED_INTERNAL_SIZE];
		System.arraycopy(testArray, 0, internalTestArray, 0, testArray.length);
		
		final IntArrayList testList = new IntArrayList();
		testFill(testList);
		testList.add(2, 23);
		
		// test internal
		assertEquals("internal addPointer", TEST_ADD_POINTER_LOC+1, testList.addPointer);
		assertArrayEquals("internal array", internalTestArray, testList.data);
		
		// test output
		assertEquals("output size", TEST_ADD_POINTER_LOC+1, testList.size());
		assertArrayEquals("output array", testArray, testList.toArray());
	}
	
	
	
	@Test
	public void testGet() {
		assertEquals(4, testList.get(4));
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testGetBelow() {
		testList.get(-1);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testGetAbove() {
		testList.get(100);
	}
	
	@Test
	public void testRemove() {
		testList.remove(3);
		testList.add(100);
		assertArrayEquals(new int[] { 0, 1, 2, 4, 5, 6, 7, 100 }, testList.toArray());
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testRemoveBelow() {
		testList.remove(-1);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testRemoveAbove() {
		testList.remove(100);
	}
	
	@Test
	public void testSet() {
		testList.set(5, 100);
		assertArrayEquals(new int[] { 0, 1, 2, 3, 4, 100, 6, 7 }, testList.toArray());
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testSetBelow() {
		testList.set(-1,100);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testSetAbove() {
		testList.set(100,100);
	}
	
	@Test
	public void testClear() {
		final IntArrayList testList = new IntArrayList();
		testFill(testList);
		
		final int[] prevInternalArray = testList.data;
		testList.clear();
		
		// test internal
		assertEquals("internal addPointer", 0, testList.addPointer);
		assertArrayEquals("internal array", new int[EXPANDED_INTERNAL_SIZE], testList.data);
		assertSame(prevInternalArray, testList.data);
		
		// test external
		assertEquals("output size", 0, testList.size());
		assertArrayEquals("output array", new int[0], testList.toArray());
	}
	
	private void testFill(IntArrayList al) {
		for (int i = 0; i < 11; ++i) {
			al.add(i);
		}
	}
}
