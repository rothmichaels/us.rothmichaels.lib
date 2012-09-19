/*
 * FloatArrayListTests.java
 *
 * Copyright (c) 2011 Roth Michaels. All rights reserved.
 *
 * The use and distribution terms for this software are covered by the
 * Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php) 
 * which can be found in the file epl-v10.html at the root of this
 * distribution. By using this software in any fashion, you are agreeing
 * to be bound by the terms of this license.
 *
 * EXCEPT AS EXPRESSLY SET FORTH IN THIS AGREEMENT, THE PROGRAM IS
 * PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, EITHER EXPRESS OR IMPLIED INCLUDING, WITHOUT LIMITATION, ANY
 * WARRANTIES OR CONDITIONS OF TITLE, NON-INFRINGEMENT, MERCHANTABILITY
 * OR FITNESS FOR A PARTICULAR PURPOSE. Each Recipient is solely
 * responsible for determining the appropriateness of using and
 * distributing the Program and assumes all risks associated with its
 * exercise of rights under this Agreement , including but not limited
 * to the risks and costs of program errors, compliance with applicable
 * laws, damage to or loss of data, programs or equipment, and
 * unavailability or interruption of operations.
 *
 * EXCEPT AS EXPRESSLY SET FORTH IN THIS AGREEMENT, NEITHER RECIPIENT
 * NOR ANY CONTRIBUTORS SHALL HAVE ANY LIABILITY FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING WITHOUT LIMITATION LOST PROFITS), HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
 * TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF
 * THE USE OR DISTRIBUTION OF THE PROGRAM OR THE EXERCISE OF ANY RIGHTS
 * GRANTED HEREUNDER, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH
 * DAMAGES.
 *
 * You must not remove this notice, or any other, from this software.
 *
 * Dec 13, 2011 
 */
package us.rothmichaels.lists;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import us.rothmichaels.lists.FloatArrayList;

/**
 * Unit Tests for {@link FloatArrayList}
 * 
 * @author Roth Michaels (<i><a
 *         href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 */
public class FloatArrayListTests {
	static final int INITIAL_SIZE = 10;
	static final int TEST_ADD_POINTER_LOC = 11;
	static final int EXPANDED_INTERNAL_SIZE = 20;
	static final float[] TEST_ARRAY = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	static final float[] INTERNAL_TEST_ARRAY = new float[EXPANDED_INTERNAL_SIZE];
	static {
		System.arraycopy(TEST_ARRAY, 0, INTERNAL_TEST_ARRAY, 0,
				TEST_ARRAY.length);
	}

	FloatArrayList testList;

	FloatArrayList exceptionList;

	@Before
	public void setUp() {
		testList = new FloatArrayList();
		exceptionList = new FloatArrayList();
		exceptionList.add(1f);
		exceptionList.add(2f);
		exceptionList.add(3f);
		exceptionList.add(4f);
		exceptionList.add(5f);
	}

	@After
	public void tearDown() {
	}

	@Test
	public void defaultConstructor() {
		// test initial internal array
		assertEquals("internal addPointer", 0, testList.addPointer);
		org.junit.Assert.assertArrayEquals("internal array",
				new float[INITIAL_SIZE], testList.data, 0f);

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
		assertArrayEquals("internal array", new float[initialSize],
				testList.data, 0f);

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
		assertArrayEquals("output array", new float[] { testValue },
				testList.toArray(), 0f);
	}

	@Test
	public void addExpand() {
		testFill(testList);

		// test internal
		assertEquals("internal addPointer", TEST_ADD_POINTER_LOC,
				testList.addPointer);
		assertArrayEquals("internal array", INTERNAL_TEST_ARRAY, testList.data,
				0f);

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
		assertEquals("internal addPointer", TEST_ADD_POINTER_LOC + 1,
				testList.addPointer);
		assertArrayEquals("internal array", internalTestArray, testList.data,
				0f);

		// test output
		assertEquals("output size", TEST_ADD_POINTER_LOC + 1, testList.size());
		assertArrayEquals("output array", testArray, testList.toArray(), 0f);
	}

	@Test
	public void clear() {
		testFill(testList);

		final float[] prevInternalArray = testList.data;
		testList.clear();

		// test internal
		assertEquals("internal addPointer", 0, testList.addPointer);
		assertArrayEquals("internal array", new float[EXPANDED_INTERNAL_SIZE],
				testList.data, 0f);
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
		assertEquals(2, testList.size());
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

		assertTrue(testList.addAll(Collections.unmodifiableList(Arrays
				.asList(new Float[] { 2f, 3f, 4f }))));

		assertEquals(1f, testList.data[0], 0f);
		assertEquals(2f, testList.data[1], 0f);
		assertEquals(3f, testList.data[2], 0f);
		assertEquals(4f, testList.data[3], 0f);
	}

	@Test
	public void addAllCollectionAtIndex() {
		testList.add(10f);
		testList.add(10f);
		testList.add(10f);

		assertTrue(testList.addAll(
				1,
				Collections.unmodifiableList(Arrays.asList(new Float[] { 2f,
						3f, 4f }))));

		assertEquals(10f, testList.data[0], 0f);
		assertEquals(2f, testList.data[1], 0f);
		assertEquals(3f, testList.data[2], 0f);
		assertEquals(4f, testList.data[3], 0f);
		assertEquals(10f, testList.data[4], 0f);
		assertEquals(10f, testList.data[5], 0f);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void addAllCollectionAtIndexBelow() {
		try {
			exceptionList.addAll(
					-1,
					Collections.unmodifiableList(Arrays.asList(new Float[] {
							2f, 3f, 4f })));
		} catch (ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void addAllCollectionAtIndexAbove() {
		try {
			exceptionList.addAll(
					6,
					Collections.unmodifiableList(Arrays.asList(new Float[] {
							2f, 3f, 4f })));
		} catch (ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test
	public void addAll() {
		IPrimativeFloatList floatList = new FloatArrayList();
		floatList.addAll(Collections.unmodifiableList(Arrays
				.asList(new Float[] { 2f, 3f, 4f })));

		testList.add(1f);
		assertTrue(testList.addAll(floatList));

		assertEquals(1f, testList.data[0], 0f);
		assertEquals(2f, testList.data[1], 0f);
		assertEquals(3f, testList.data[2], 0f);
		assertEquals(4f, testList.data[3], 0f);
	}

	@Test
	public void addAllAtIndex() {
		IPrimativeFloatList floatList = new FloatArrayList();
		floatList.addAll(Collections.unmodifiableList(Arrays
				.asList(new Float[] { 2f, 3f, 4f })));

		testList.add(10f);
		testList.add(10f);
		testList.add(10f);
		assertTrue(testList.addAll(1, floatList));

		assertEquals(10f, testList.data[0], 0f);
		assertEquals(2f, testList.data[1], 0f);
		assertEquals(3f, testList.data[2], 0f);
		assertEquals(4f, testList.data[3], 0f);
		assertEquals(10f, testList.data[4], 0f);
		assertEquals(10f, testList.data[5], 0f);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void addAllAtIndexBelow() {
		IPrimativeFloatList floatList = new FloatArrayList();
		floatList.addAll(Collections.unmodifiableList(Arrays
				.asList(new Float[] { 2f, 3f, 4f })));

		try {
			exceptionList.addAll(-1, floatList);
		} catch (ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void addAllAtIndexAbove() {
		IPrimativeFloatList floatList = new FloatArrayList();
		floatList.addAll(Collections.unmodifiableList(Arrays
				.asList(new Float[] { 2f, 3f, 4f })));

		try {
			exceptionList.addAll(6, floatList);
		} catch (ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test
	public void contains() {
		testList.add(1f);
		testList.add(2f);
		testList.add(3f);
		testList.add(4f);
		testList.add(5f);

		assertTrue("contains true", testList.contains(2f));
		assertFalse("contains false", testList.contains(-14f));
	}

	@Test
	public void containsAllColleciton() {
		testList.add(1f);
		testList.add(2f);
		testList.add(3f);
		testList.add(4f);
		testList.add(5f);

		assertTrue("contains true", testList.containsAll(Collections
				.unmodifiableList(Arrays.asList(new Float[] { 3f, 2f, 4f }))));

		assertFalse("contains false", testList.containsAll(Collections
				.unmodifiableList(Arrays.asList(new Float[] { 7f, 3f, 4f }))));
	}

	@Test
	public void containsAll() {
		testList.add(1f);
		testList.add(2f);
		testList.add(3f);
		testList.add(4f);
		testList.add(5f);

		IPrimativeFloatList expectTrue = new FloatArrayList();
		expectTrue.addAll(Collections.unmodifiableList(Arrays
				.asList(new Float[] { 3f, 2f, 4f })));
		assertTrue("contains true", testList.containsAll(expectTrue));

		IPrimativeFloatList expectFalse = new FloatArrayList();
		expectFalse.addAll(Collections.unmodifiableList(Arrays
				.asList(new Float[] { 7f, 2f, 4f })));
		assertFalse("contains false", testList.containsAll(expectFalse));
	}

	@Test
	public void get() {
		testList.add(1f);
		testList.add(2f);
		testList.add(3f);
		testList.add(4f);
		testList.add(5f);

		assertEquals(3f, testList.get(2), 0f);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetBelow() {
		try {
			exceptionList.get(-1);
		} catch (ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetAbove() {
		try {
			exceptionList.get(6);
		} catch (ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test
	public void indexOf() {
		testList.add(1f);
		testList.add(2f);
		testList.add(3f);
		testList.add(3f);
		testList.add(5f);

		assertEquals(2, testList.indexOf(3f));
	}

	@Test(expected = IllegalArgumentException.class)
	public void indexOfNotFound() {
		testList.add(1f);
		testList.add(2f);
		testList.add(3f);
		testList.add(3f);
		testList.add(5f);

		testList.indexOf(7f);
	}

	@Test
	public void isEmpty() {
		assertTrue(testList.isEmpty());

		testList.add(1f);
		assertFalse(testList.isEmpty());

		testList.clear();
		assertTrue(testList.isEmpty());
	}

	@Test
	public void lastIndexOf() {
		testList.add(1f);
		testList.add(2f);
		testList.add(3f);
		testList.add(3f);
		testList.add(5f);

		assertEquals(3, testList.lastIndexOf(3f));
	}

	@Test
	public void removeValue() {
		testList.add(1f);
		testList.add(3f);
		testList.add(2f);
		testList.add(3f);
		testList.add(5f);

		assertFalse(testList.removeValue(-4f));
		assertTrue(testList.removeValue(3f));

		assertArrayEquals(new float[] { 1f, 2f, 3f, 5f }, testList.toArray(),
				0f);
	}

	@Test
	public void remove() {
		testList.add(1f);
		testList.add(2f);
		testList.add(3f);
		testList.add(3f);
		testList.add(5f);

		assertTrue(testList.remove(2));

		assertEquals(4, testList.addPointer);
		assertArrayEquals(new float[] { 1f, 2f, 3f, 5f }, testList.toArray(),
				0f);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveBelow() {
		try {
			exceptionList.remove(-1);
		} catch (ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveAbove() {
		try {
			exceptionList.remove(6);
		} catch (ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}
	}

	@Test
	public void removeAllCollection() {
		testList.add(1f);
		testList.add(2f);
		testList.add(3f);
		testList.add(4f);
		testList.add(5f);

		assertFalse(testList.removeAll(Collections.unmodifiableList(Arrays
				.asList(new Float[] { 20f, 30f, 40f }))));

		assertTrue(testList.removeAll(Collections.unmodifiableList(Arrays
				.asList(new Float[] { 2f, 3f, 4f }))));

		assertArrayEquals(new float[] { 1f, 5f }, testList.toArray(), 0f);
	}

	@Test
	public void retainAllCollection() {
		testList.add(1f);
		testList.add(2f);
		testList.add(3f);
		testList.add(4f);
		testList.add(5f);

		assertTrue(testList.retainAll(Collections.unmodifiableList(Arrays
				.asList(new Float[] { 2f, 3f, 4f }))));

		assertArrayEquals(new float[] { 2f, 3f, 4f }, testList.toArray(), 0f);
	}

	@Test
	public void set() {
		testList.add(1f);
		testList.add(2f);
		testList.add(3f);
		testList.add(4f);
		testList.add(5f);

		assertEquals(3f, testList.set(2, 10f), 0f);

		assertArrayEquals(new float[] { 1f, 2f, 10f, 4f, 5f },
				testList.toArray(), 0f);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testSetBelow() {
		try {
			exceptionList.set(-1, 6);
		} catch (ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testSetAbove() {
		try {
			exceptionList.set(100, 6);
		} catch (ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test
	public void subList() {
		testList.add(1f);
		testList.add(2f);
		testList.add(3f);
		testList.add(4f);
		testList.add(5f);

		assertArrayEquals(new float[] { 2f, 3f, 4f }, testList.subList(1, 4)
				.toArray(), 0f);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void subListBelow() {
		try {
			exceptionList.subList(-1, 3);
		} catch (ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void subListOutOfRange() {
		try {
			exceptionList.subList(0, 6);
		} catch (ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void subListToLessThanFrom() {
		try {
			exceptionList.subList(4, 2);
		} catch (ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	private void testFill(FloatArrayList al) {
		for (int i = 0; i < 11; ++i) {
			al.add(i);
		}
	}
}
