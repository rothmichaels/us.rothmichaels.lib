/*
 * ShortArrayListTests.java
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
 * Dec 13L, 2011L 
 */
package us.rothmichaels.lists;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collections;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit Tests for {@link ShortArrayList}
 * 
 * @author Roth Michaels (<i><a
 *         href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 */
public class ShortArrayListTests {
	static final int INITIAL_SIZE = 10;
	static final int TEST_ADD_POINTER_LOC = 11;
	static final int EXPANDED_INTERNAL_SIZE = 20;
	static final short[] TEST_ARRAY = { (short) 0, (short) 1, (short) 2,
			(short) 3, (short) 4, (short) 5, (short) 6, (short) 7, (short) 8,
			(short) 9, (short) 10 };
	static final short[] INTERNAL_TEST_ARRAY = new short[EXPANDED_INTERNAL_SIZE];
	static {
		System.arraycopy(TEST_ARRAY, 0, INTERNAL_TEST_ARRAY, 0,
				TEST_ARRAY.length);
	}

	ShortArrayList testList;

	ShortArrayList exceptionList;

	@Before
	public void setUp() {
		testList = new ShortArrayList();
		exceptionList = new ShortArrayList();
		exceptionList.add((short) 1);
		exceptionList.add((short) 2);
		exceptionList.add((short) 3);
		exceptionList.add((short) 4);
		exceptionList.add((short) 5);
	}

	@After
	public void tearDown() {
	}

	@Test
	public void defaultConstructor() {
		// test initial internal array
		assertEquals("internal addPointer", 0, testList.addPointer);
		org.junit.Assert.assertArrayEquals("internal array",
				new short[INITIAL_SIZE], testList.data);

		// test initial output
		// TODO move to other tests
		assertEquals("output size", 0, testList.size());
		assertArrayEquals("output array", new short[0], testList.toArray());
	}

	@Test
	public void initialSizeConstructor() {
		final int initialSize = 13;
		@SuppressWarnings("hiding")
		final ShortArrayList testList = new ShortArrayList(initialSize);

		// test initial internal array
		assertEquals("internal addPointer", 0, testList.addPointer);
		assertArrayEquals("internal array", new short[initialSize],
				testList.data);

		// test initial output
		// TODO move to other tests
		assertEquals("output size", 0, testList.size());
		assertArrayEquals("output array", new short[0], testList.toArray());
	}

	@Test
	public void add() {
		final short testValue = (short) 6;
		final short[] expectedInternal = new short[INITIAL_SIZE];
		expectedInternal[0] = testValue;

		testList.add(testValue);

		// test internal
		assertEquals("internal addPointer", (short) 1, testList.addPointer);
		assertArrayEquals("internal array", expectedInternal, testList.data);

		// test output
		assertEquals("output size", (short) 1, testList.size());
		assertArrayEquals("output array", new short[] { testValue },
				testList.toArray());
	}

	@Test
	public void addExpand() {
		testFill(testList);

		// test internal
		assertEquals("internal addPointer", TEST_ADD_POINTER_LOC,
				testList.addPointer);
		assertArrayEquals("internal array", INTERNAL_TEST_ARRAY, testList.data);

		// test output
		assertEquals("output size", TEST_ADD_POINTER_LOC, testList.size());
		assertArrayEquals("output array", TEST_ARRAY, testList.toArray());
	}

	@Test
	public void insert() {
		final short[] testArray = { (short) 0, (short) 1, (short) 23,
				(short) 2, (short) 3, (short) 4, (short) 5, (short) 6,
				(short) 7, (short) 8, (short) 9, (short) 10 };
		final short[] internalTestArray = new short[EXPANDED_INTERNAL_SIZE];
		System.arraycopy(testArray, 0, internalTestArray, 0, testArray.length);

		testFill(testList);
		testList.add(2, (short) 23);

		// test internal
		assertEquals("internal addPointer", TEST_ADD_POINTER_LOC + (short) 1,
				testList.addPointer);
		assertArrayEquals("internal array", internalTestArray, testList.data);

		// test output
		assertEquals("output size", TEST_ADD_POINTER_LOC + (short) 1,
				testList.size());
		assertArrayEquals("output array", testArray, testList.toArray());
	}

	@Test
	public void clear() {
		testFill(testList);

		final short[] prevInternalArray = testList.data;
		testList.clear();

		// test internal
		assertEquals("internal addPointer", 0, testList.addPointer);
		assertArrayEquals("internal array", new short[EXPANDED_INTERNAL_SIZE],
				testList.data);
		assertSame(prevInternalArray, testList.data);

		// test external
		assertEquals("output size", 0, testList.size());
		assertArrayEquals("output array", new short[0], testList.toArray());
	}

	@Test
	public void size() {
		// new list size
		assertEquals(0, testList.size());

		testList.add((short) 2);
		testList.add((short) 3);

		// test size
		assertEquals((short) 2, testList.size());
	}

	@Test
	public void toArray() {
		// test initial array
		assertArrayEquals("output array", new short[0], testList.toArray());

		testList.add((short) 2);
		testList.add((short) 3);

		assertArrayEquals(new short[] { (short) 2, (short) 3 },
				testList.toArray());
	}

	@Test
	public void addAllCollection() {
		testList.add((short) 1);

		assertTrue(testList.addAll(Collections.unmodifiableList(Arrays
				.asList(new Short[] { (short) 2, (short) 3, (short) 4 }))));

		assertEquals((short) 1, testList.data[0]);
		assertEquals((short) 2, testList.data[1]);
		assertEquals((short) 3, testList.data[2]);
		assertEquals((short) 4, testList.data[3]);
	}

	@Test
	public void addAllCollectionAtIndex() {
		testList.add((short) 10);
		testList.add((short) 10);
		testList.add((short) 10);

		assertTrue(testList.addAll(
				1,
				Collections.unmodifiableList(Arrays.asList(new Short[] {
						(short) 2, (short) 3, (short) 4 }))));

		assertEquals((short) 10, testList.data[0]);
		assertEquals((short) 2, testList.data[1]);
		assertEquals((short) 3, testList.data[2]);
		assertEquals((short) 4, testList.data[3]);
		assertEquals((short) 10, testList.data[4]);
		assertEquals((short) 10, testList.data[5]);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void addAllCollectionAtIndexBelow() {
		try {
			exceptionList.addAll(
					-1,
					Collections.unmodifiableList(Arrays.asList(new Short[] {
							(short) 2, (short) 3, (short) 4 })));
		} catch (final ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void addAllCollectionAtIndexAbove() {
		try {
			exceptionList.addAll(
					6,
					Collections.unmodifiableList(Arrays.asList(new Short[] {
							(short) 2, (short) 3, (short) 4 })));
		} catch (final ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test
	public void addAll() {
		final IPrimativeShortList shortList = new ShortArrayList();
		shortList.addAll(Collections.unmodifiableList(Arrays
				.asList(new Short[] { (short) 2, (short) 3, (short) 4 })));

		testList.add((short) 1);
		assertTrue(testList.addAll(shortList));

		assertEquals((short) 1, testList.data[0]);
		assertEquals((short) 2, testList.data[1]);
		assertEquals((short) 3, testList.data[2]);
		assertEquals((short) 4, testList.data[3]);
	}

	@Test
	public void addAllAtIndex() {
		final IPrimativeShortList shortList = new ShortArrayList();
		shortList.addAll(Collections.unmodifiableList(Arrays
				.asList(new Short[] { (short) 2, (short) 3, (short) 4 })));

		testList.add((short) 10);
		testList.add((short) 10);
		testList.add((short) 10);
		assertTrue(testList.addAll(1, shortList));

		assertEquals((short) 10, testList.data[0]);
		assertEquals((short) 2, testList.data[1]);
		assertEquals((short) 3, testList.data[2]);
		assertEquals((short) 4, testList.data[3]);
		assertEquals((short) 10, testList.data[4]);
		assertEquals((short) 10, testList.data[5]);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void addAllAtIndexBelow() {
		final IPrimativeShortList shortList = new ShortArrayList();
		shortList.addAll(Collections.unmodifiableList(Arrays
				.asList(new Short[] { (short) 2, (short) 3, (short) 4 })));

		try {
			exceptionList.addAll(-1, shortList);
		} catch (final ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void addAllAtIndexAbove() {
		final IPrimativeShortList shortList = new ShortArrayList();
		shortList.addAll(Collections.unmodifiableList(Arrays
				.asList(new Short[] { (short) 2, (short) 3, (short) 4 })));

		try {
			exceptionList.addAll(6, shortList);
		} catch (final ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test
	public void contains() {
		testList.add((short) 1);
		testList.add((short) 2);
		testList.add((short) 3);
		testList.add((short) 4);
		testList.add((short) 5);

		assertTrue("contains true", testList.contains((short) 2));
		assertFalse("contains false", testList.contains((short) -14));
	}

	@Test
	public void containsAllColleciton() {
		testList.add((short) 1);
		testList.add((short) 2);
		testList.add((short) 3);
		testList.add((short) 4);
		testList.add((short) 5);

		assertTrue("contains true", testList.containsAll(Collections
				.unmodifiableList(Arrays.asList(new Short[] { (short) 3,
						(short) 2, (short) 4 }))));

		assertFalse("contains false", testList.containsAll(Collections
				.unmodifiableList(Arrays.asList(new Short[] { (short) 7,
						(short) 3, (short) 4 }))));
	}

	@Test
	public void containsAll() {
		testList.add((short) 1);
		testList.add((short) 2);
		testList.add((short) 3);
		testList.add((short) 4);
		testList.add((short) 5);

		final IPrimativeShortList expectTrue = new ShortArrayList();
		expectTrue.addAll(Collections.unmodifiableList(Arrays
				.asList(new Short[] { (short) 3, (short) 2, (short) 4 })));
		assertTrue("contains true", testList.containsAll(expectTrue));

		final IPrimativeShortList expectFalse = new ShortArrayList();
		expectFalse.addAll(Collections.unmodifiableList(Arrays
				.asList(new Short[] { (short) 7, (short) 2, (short) 4 })));
		assertFalse("contains false", testList.containsAll(expectFalse));
	}

	@Test
	public void get() {
		testList.add((short) 1);
		testList.add((short) 2);
		testList.add((short) 3);
		testList.add((short) 4);
		testList.add((short) 5);

		assertEquals((short) 3, testList.get(2));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetBelow() {
		try {
			exceptionList.get(-1);
		} catch (final ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetAbove() {
		try {
			exceptionList.get(6);
		} catch (final ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test
	public void indexOf() {
		testList.add((short) 1);
		testList.add((short) 2);
		testList.add((short) 3);
		testList.add((short) 3);
		testList.add((short) 5);

		assertEquals((short) 2, testList.indexOf((short) 3));
	}

	@Test(expected = IllegalArgumentException.class)
	public void indexOfNotFound() {
		testList.add((short) 1);
		testList.add((short) 2);
		testList.add((short) 3);
		testList.add((short) 3);
		testList.add((short) 5);

		testList.indexOf((short) 7);
	}

	@Test
	public void isEmpty() {
		assertTrue(testList.isEmpty());

		testList.add((short) 1);
		assertFalse(testList.isEmpty());

		testList.clear();
		assertTrue(testList.isEmpty());
	}

	@Test
	public void lastIndexOf() {
		testList.add((short) 1);
		testList.add((short) 2);
		testList.add((short) 3);
		testList.add((short) 3);
		testList.add((short) 5);

		assertEquals((short) 3, testList.lastIndexOf((short) 3));
	}

	@Test
	public void removeValue() {
		testList.add((short) 1);
		testList.add((short) 3);
		testList.add((short) 2);
		testList.add((short) 3);
		testList.add((short) 5);

		assertFalse(testList.removeValue((short) -4));
		assertTrue(testList.removeValue((short) 3));

		assertArrayEquals(new short[] { (short) 1, (short) 2, (short) 3,
				(short) 5 }, testList.toArray());
	}

	@Test
	public void remove() {
		testList.add((short) 1);
		testList.add((short) 2);
		testList.add((short) 3);
		testList.add((short) 3);
		testList.add((short) 5);

		assertTrue(testList.remove(2));

		assertEquals((short) 4, testList.addPointer);
		assertArrayEquals(new short[] { (short) 1, (short) 2, (short) 3,
				(short) 5 }, testList.toArray());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveBelow() {
		try {
			exceptionList.remove(-1);
		} catch (final ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveAbove() {
		try {
			exceptionList.remove(6);
		} catch (final ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}
	}

	@Test
	public void removeAllCollection() {
		testList.add((short) 1);
		testList.add((short) 2);
		testList.add((short) 3);
		testList.add((short) 4);
		testList.add((short) 5);

		assertFalse(testList.removeAll(Collections.unmodifiableList(Arrays
				.asList(new Short[] { (short) 20, (short) 30, (short) 40 }))));

		assertTrue(testList.removeAll(Collections.unmodifiableList(Arrays
				.asList(new Short[] { (short) 2, (short) 3, (short) 4 }))));

		assertArrayEquals(new short[] { (short) 1, (short) 5 },
				testList.toArray());
	}

	@Test
	public void retainAllCollection() {
		testList.add((short) 1);
		testList.add((short) 2);
		testList.add((short) 3);
		testList.add((short) 4);
		testList.add((short) 5);

		assertTrue(testList.retainAll(Collections.unmodifiableList(Arrays
				.asList(new Short[] { (short) 2, (short) 3, (short) 4 }))));

		assertArrayEquals(new short[] { (short) 2, (short) 3, (short) 4 },
				testList.toArray());
	}

	@Test
	public void set() {
		testList.add((short) 1);
		testList.add((short) 2);
		testList.add((short) 3);
		testList.add((short) 4);
		testList.add((short) 5);

		assertEquals((short) 3, testList.set(2, (short) 10));

		assertArrayEquals(new short[] { (short) 1, (short) 2, (short) 10,
				(short) 4, (short) 5 }, testList.toArray());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testSetBelow() {
		try {
			exceptionList.set(-1, (short) 6);
		} catch (final ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testSetAbove() {
		try {
			exceptionList.set(6, (short) 100);
		} catch (final ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test
	public void subList() {
		testList.add((short) 1);
		testList.add((short) 2);
		testList.add((short) 3);
		testList.add((short) 4);
		testList.add((short) 5);

		assertArrayEquals(new short[] { (short) 2, (short) 3, (short) 4 },
				testList.subList(1, 4).toArray());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void subListBelow() {
		try {
			exceptionList.subList(-1, 3);
		} catch (final ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void subListOutOfRange() {
		try {
			exceptionList.subList(0, 6);
		} catch (final ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void subListToLessThanFrom() {
		try {
			exceptionList.subList(4, 2);
		} catch (final ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	private void testFill(ShortArrayList al) {
		for (short i = 0; i < 11; ++i) {
			al.add(i);
		}
	}
}
