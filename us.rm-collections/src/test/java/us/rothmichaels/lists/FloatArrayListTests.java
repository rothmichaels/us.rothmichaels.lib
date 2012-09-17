/*
 * FloatArrayListTests.java
 *
 * Dec 13, 2011 
 */
package us.rothmichaels.lists;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import us.rothmichaels.lists.FloatArrayList;

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
	
	FloatArrayList testList;
	
	@Before
	public void setUp() {
		testList = new FloatArrayList();
	}
	
	@After
	public void tearDown() { }
	
	@Test
	public void defaultConstructor() {
		// test initial internal array
		assertEquals("internal addPointer", 0, testList.addPointer);
		org.junit.Assert.assertArrayEquals("internal array", new float[INITIAL_SIZE], testList.data, 0f);
		
		// test initial output
		// TODO move to other tests
		assertEquals("output size", 0, testList.size());
		assertArrayEquals("output array", new float[0], testList.toArray(), 0f);
	}
	
	@Test
	public void initialSizeConstructor() {
		final int initialSize = 13;
		@SuppressWarnings("hiding")
		final FloatArrayList testList = new FloatArrayList(initialSize);
		
		// test initial internal array
		assertEquals("internal addPointer", 0, testList.addPointer);
		assertArrayEquals("internal array", new float[initialSize], testList.data, 0f);
		
		// test initial output
		// TODO move to other tests
		assertEquals("output size", 0, testList.size());
		assertArrayEquals("output array", new float[0], testList.toArray(), 0f);
	}
	
	@Test
	public void add() {
		final float testValue = 6.3f;
		final float[] expectedInternal = new float[INITIAL_SIZE];
		expectedInternal[0] = testValue;
		
		testList.add(testValue);
		
		// test internal
		assertEquals("internal addPointer", 1, testList.addPointer);
		assertArrayEquals("internal array", expectedInternal, testList.data, 0f);
		
		// test output
		assertEquals("output size", 1, testList.size());
		assertArrayEquals("output array", new float[] { testValue }, testList.toArray(), 0f);
	}
	
	@Test
	public void addExpand() {
		testFill(testList);
		
		// test internal
		assertEquals("internal addPointer", TEST_ADD_POINTER_LOC, testList.addPointer);
		assertArrayEquals("internal array", INTERNAL_TEST_ARRAY, testList.data, 0f);
		
		// test output
		assertEquals("output size", TEST_ADD_POINTER_LOC, testList.size());
		assertArrayEquals("output array", TEST_ARRAY, testList.toArray(), 0f);
	}
	
	@Test
	public void insert() {
		final float[] testArray = { 0, 1, 23.4f, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		final float[] internalTestArray = new float[EXPANDED_INTERNAL_SIZE];
		System.arraycopy(testArray, 0, internalTestArray, 0, testArray.length);
		
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
	public void clear() {
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
	
	@Test
	public void size() {
		// new list size
		assertEquals(0, testList.size());
		
		testList.add(2f);
		testList.add(3f);
		
		// test size
		assertEquals(0, testList.size());
	}
	
	@Test
	public void toArray() {
		// test initial array
		assertArrayEquals("output array", new float[0], testList.toArray(), 0f);
		
		testList.add(2f);
		testList.add(3f);
		
		assertArrayEquals(new float[] { 2f, 3f }, testList.toArray(), 0f);
	}
	
	@Test
	public void addAllCollection() {
		testList.add(1f);
		
		testList.addAll(Collections.unmodifiableList(Arrays.asList(new Float[] {
				2f, 3f, 4f })));
		
		assertEquals(1f, testList.data[0], 0f);
		assertEquals(2f, testList.data[1], 0f);
		assertEquals(3f, testList.data[2], 0f);
		assertEquals(4f, testList.data[3], 0f);
	}
	
	@Test
	public void addAllCollectionAtIndex() {
		
		fail("write a test");
	}
	
	@Test
	public void addAll() {

		fail("write a test");
	}
	
	@Test
	public void addAllAtIndex() {

		fail("write a test");
	}
	
	@Test
	public void contains() {
		fail("write a test");
	}
	
	@Test
	public void containsAllColleciton() {
		fail("write a test");
	}
	
	@Test
	public void containsAll() {
		fail("write a test");
	}
	
	@Test
	public void get() {
		fail("write a test");
	}
	
	@Test
	public void indexOf() {
		fail("write a test");
	}
	
	@Test
	public void isEmpty() {
		fail("write a test");
	}
	
	@Test
	public void lastIndexOf() {
		fail("write a test");	
	}
	
	@Test
	public void removeValue() {
		fail("write a test");
	}
	
	@Test
	public void remove() { 
		fail("write a test");
	}
	
	@Test
	public void removeAllCollection() {
		fail("write a test");
	}
	
	@Test
	public void retainAllCollection() {
		fail("write a test");
	}
	
	@Test
	public void set() {
		fail("write a test");
	}
	
	@Test
	public void subList() {
		fail("write a test");
	}
	
	private void testFill(FloatArrayList al) {
		for (int i = 0; i < 11; ++i) {
			al.add(i);
		}
	}
}
