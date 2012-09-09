/*
 * FloatArrayListTests.java
 *
 * Dec 13, 2011 
 */
package us.rothmichaels.lib.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit Tests for {@link FloatArrayList}
 *
 * @author Roth Michaels (<i><a href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 */
public class FloatArrayListTests {
	static final int INITIAL_SIZE = 10;
	static final int TEST_ADD_POINTER_LOC = 11;
	static final int EXPANDED_INTERNAL_SIZE = 20;
	static final float[] TEST_ARRAY = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	static final float[] INTERNAL_TEST_ARRAY = new float[EXPANDED_INTERNAL_SIZE];
	static {
		System.arraycopy(TEST_ARRAY, 0, INTERNAL_TEST_ARRAY, 0, TEST_ARRAY.length);
	}
	
	@Test
	public void testDefaultConstructor() {
		final FloatArrayList testList = new FloatArrayList();
		
		// test initial internal array
		assertEquals("internal addPointer", 0, testList.addPointer);
		org.junit.Assert.assertArrayEquals("internal array", new float[INITIAL_SIZE], testList.data, 0f);
		
		// test initial output
		assertEquals("output size", 0, testList.size());
		assertArrayEquals("output array", new float[0], testList.toArray(), 0f);
	}
	
	@Test
	public void testInitialSizeConstructor() {
		final int initialSize = 13;
		final FloatArrayList testList = new FloatArrayList(initialSize);
		
		// test initial internal array
		assertEquals("internal addPointer", 0, testList.addPointer);
		assertArrayEquals("internal array", new float[initialSize], testList.data, 0f);
		
		// test initial output
		assertEquals("output size", 0, testList.size());
		assertArrayEquals("output array", new float[0], testList.toArray(), 0f);
	}
	
	@Test
	public void testAdd() {
		final float testValue = 6.3f;
		final float[] expectedInternal = new float[INITIAL_SIZE];
		expectedInternal[0] = testValue;
		final FloatArrayList testList = new FloatArrayList();
		
		testList.add(testValue);
		
		// test internal
		assertEquals("internal addPointer", 1, testList.addPointer);
		assertArrayEquals("internal array", expectedInternal, testList.data, 0f);
		
		// test output
		assertEquals("output size", 1, testList.size());
		assertArrayEquals("output array", new float[] { testValue }, testList.toArray(), 0f);
	}
	
	@Test
	public void testAddExpand() {
		final FloatArrayList testList = new FloatArrayList();
		testFill(testList);
		
		// test internal
		assertEquals("internal addPointer", TEST_ADD_POINTER_LOC, testList.addPointer);
		assertArrayEquals("internal array", INTERNAL_TEST_ARRAY, testList.data, 0f);
		
		// test output
		assertEquals("output size", TEST_ADD_POINTER_LOC, testList.size());
		assertArrayEquals("output array", TEST_ARRAY, testList.toArray(), 0f);
	}
	
	@Test
	public void testInsert() {
		final float[] testArray = { 0, 1, 23.4f, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		final float[] internalTestArray = new float[EXPANDED_INTERNAL_SIZE];
		System.arraycopy(testArray, 0, internalTestArray, 0, testArray.length);
		
		final FloatArrayList testList = new FloatArrayList();
		testFill(testList);
		testList.add(2, 23.4f);
		
		// test internal
		assertEquals("internal addPointer", TEST_ADD_POINTER_LOC+1, testList.addPointer);
		assertArrayEquals("internal array", internalTestArray, testList.data, 0f);
		
		// test output
		assertEquals("output size", TEST_ADD_POINTER_LOC+1, testList.size());
		assertArrayEquals("output array", testArray, testList.toArray(), 0f);
	}
	
	@Test
	public void testClear() {
		final FloatArrayList testList = new FloatArrayList();
		testFill(testList);
		
		final float[] prevInternalArray = testList.data;
		testList.clear();
		
		// test internal
		assertEquals("internal addPointer", 0, testList.addPointer);
		assertArrayEquals("internal array", new float[EXPANDED_INTERNAL_SIZE], testList.data, 0f);
		assertSame(prevInternalArray, testList.data);
		
		// test external
		assertEquals("output size", 0, testList.size());
		assertArrayEquals("output array", new float[0], testList.toArray(), 0f);
	}
	
	private void testFill(FloatArrayList al) {
		for (int i = 0; i < 11; ++i) {
			al.add(i);
		}
	}
}
