/*
 * LongArrayListTests.java
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
 * Unit Tests for {@link LongArrayList}
 * 
 * @author Roth Michaels (<i><a
 *         href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 */
public class LongArrayListTests {
	static final int INITIAL_SIZE = 10;
	static final int TEST_ADD_POINTER_LOC = 11;
	static final int EXPANDED_INTERNAL_SIZE = 20;
	static final long[] TEST_ARRAY = { 0L, 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L,
			10L };
	static final long[] INTERNAL_TEST_ARRAY = new long[EXPANDED_INTERNAL_SIZE];
	static {
		System.arraycopy(TEST_ARRAY, 0, INTERNAL_TEST_ARRAY, 0,
				TEST_ARRAY.length);
	}

	LongArrayList testList;

	LongArrayList exceptionList;

	@Before
	public void setUp() {
		testList = new LongArrayList();
		exceptionList = new LongArrayList();
		exceptionList.add(1L);
		exceptionList.add(2L);
		exceptionList.add(3L);
		exceptionList.add(4L);
		exceptionList.add(5L);
	}

	@After
	public void tearDown() {
	}

	@Test
	public void defaultConstructor() {
		// test initial internal array
		assertEquals("internal addPointer", 0, testList.addPointer);
		org.junit.Assert.assertArrayEquals("internal array",
				new long[INITIAL_SIZE], testList.data);

		// test initial output
		// TODO move to other tests
		assertEquals("output size", 0, testList.size());
		assertArrayEquals("output array", new long[0], testList.toArray());
	}

	@Test
	public void initialSizeConstructor() {
		final int initialSize = 13;
		@SuppressWarnings("hiding")
		final LongArrayList testList = new LongArrayList(initialSize);

		// test initial internal array
		assertEquals("internal addPointer", 0, testList.addPointer);
		assertArrayEquals("internal array", new long[initialSize],
				testList.data);

		// test initial output
		// TODO move to other tests
		assertEquals("output size", 0, testList.size());
		assertArrayEquals("output array", new long[0], testList.toArray());
	}

	@Test
	public void add() {
		final long testValue = 6L;
		final long[] expectedInternal = new long[INITIAL_SIZE];
		expectedInternal[0] = testValue;

		testList.add(testValue);

		// test internal
		assertEquals("internal addPointer", 1L, testList.addPointer);
		assertArrayEquals("internal array", expectedInternal, testList.data);

		// test output
		assertEquals("output size", 1L, testList.size());
		assertArrayEquals("output array", new long[] { testValue },
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
		final long[] testArray = { 0L, 1L, 23L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L,
				10L };
		final long[] internalTestArray = new long[EXPANDED_INTERNAL_SIZE];
		System.arraycopy(testArray, 0, internalTestArray, 0, testArray.length);

		testFill(testList);
		testList.add(2, 23L);

		// test internal
		assertEquals("internal addPointer", TEST_ADD_POINTER_LOC + 1L,
				testList.addPointer);
		assertArrayEquals("internal array", internalTestArray, testList.data);

		// test output
		assertEquals("output size", TEST_ADD_POINTER_LOC + 1L, testList.size());
		assertArrayEquals("output array", testArray, testList.toArray());
	}

	@Test
	public void clear() {
		testFill(testList);

		final long[] prevInternalArray = testList.data;
		testList.clear();

		// test internal
		assertEquals("internal addPointer", 0, testList.addPointer);
		assertArrayEquals("internal array", new long[EXPANDED_INTERNAL_SIZE],
				testList.data);
		assertSame(prevInternalArray, testList.data);

		// test external
		assertEquals("output size", 0, testList.size());
		assertArrayEquals("output array", new long[0], testList.toArray());
	}

	@Test
	public void size() {
		// new list size
		assertEquals(0, testList.size());

		testList.add(2L);
		testList.add(3L);

		// test size
		assertEquals(2L, testList.size());
	}

	@Test
	public void toArray() {
		// test initial array
		assertArrayEquals("output array", new long[0], testList.toArray());

		testList.add(2L);
		testList.add(3L);

		assertArrayEquals(new long[] { 2L, 3L }, testList.toArray());
	}

	@Test
	public void addAllCollection() {
		testList.add(1L);

		assertTrue(testList.addAll(Collections.unmodifiableList(Arrays
				.asList(new Long[] { 2L, 3L, 4L }))));

		assertEquals(1L, testList.data[0]);
		assertEquals(2L, testList.data[1]);
		assertEquals(3L, testList.data[2]);
		assertEquals(4L, testList.data[3]);
	}

	@Test
	public void addAllCollectionAtIndex() {
		testList.add(10L);
		testList.add(10L);
		testList.add(10L);

		assertTrue(testList.addAll(
				1,
				Collections.unmodifiableList(Arrays.asList(new Long[] { 2L, 3L,
						4L }))));

		assertEquals(10L, testList.data[0]);
		assertEquals(2L, testList.data[1]);
		assertEquals(3L, testList.data[2]);
		assertEquals(4L, testList.data[3]);
		assertEquals(10L, testList.data[4]);
		assertEquals(10L, testList.data[5]);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void addAllCollectionAtIndexBelow() {
		try {
			exceptionList.addAll(
					-1,
					Collections.unmodifiableList(Arrays.asList(new Long[] { 2L,
							3L, 4L })));
		} catch (final ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void addAllCollectionAtIndexAbove() {
		try {
			exceptionList.addAll(
					6,
					Collections.unmodifiableList(Arrays.asList(new Long[] { 2L,
							3L, 4L })));
		} catch (final ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test
	public void addAll() {
		final IPrimativeLongList longList = new LongArrayList();
		longList.addAll(Collections.unmodifiableList(Arrays.asList(new Long[] {
				2L, 3L, 4L })));

		testList.add(1L);
		assertTrue(testList.addAll(longList));

		assertEquals(1L, testList.data[0]);
		assertEquals(2L, testList.data[1]);
		assertEquals(3L, testList.data[2]);
		assertEquals(4L, testList.data[3]);
	}

	@Test
	public void addAllAtIndex() {
		final IPrimativeLongList longList = new LongArrayList();
		longList.addAll(Collections.unmodifiableList(Arrays.asList(new Long[] {
				2L, 3L, 4L })));

		testList.add(10L);
		testList.add(10L);
		testList.add(10L);
		assertTrue(testList.addAll(1, longList));

		assertEquals(10L, testList.data[0]);
		assertEquals(2L, testList.data[1]);
		assertEquals(3L, testList.data[2]);
		assertEquals(4L, testList.data[3]);
		assertEquals(10L, testList.data[4]);
		assertEquals(10L, testList.data[5]);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void addAllAtIndexBelow() {
		final IPrimativeLongList longList = new LongArrayList();
		longList.addAll(Collections.unmodifiableList(Arrays.asList(new Long[] {
				2L, 3L, 4L })));

		try {
			exceptionList.addAll(-1, longList);
		} catch (final ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void addAllAtIndexAbove() {
		final IPrimativeLongList longList = new LongArrayList();
		longList.addAll(Collections.unmodifiableList(Arrays.asList(new Long[] {
				2L, 3L, 4L })));

		try {
			exceptionList.addAll(6, longList);
		} catch (final ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test
	public void contains() {
		testList.add(1L);
		testList.add(2L);
		testList.add(3L);
		testList.add(4L);
		testList.add(5L);

		assertTrue("contains true", testList.contains(2L));
		assertFalse("contains false", testList.contains(-14L));
	}

	@Test
	public void containsAllColleciton() {
		testList.add(1L);
		testList.add(2L);
		testList.add(3L);
		testList.add(4L);
		testList.add(5L);

		assertTrue("contains true", testList.containsAll(Collections
				.unmodifiableList(Arrays.asList(new Long[] { 3L, 2L, 4L }))));

		assertFalse("contains false", testList.containsAll(Collections
				.unmodifiableList(Arrays.asList(new Long[] { 7L, 3L, 4L }))));
	}

	@Test
	public void containsAll() {
		testList.add(1L);
		testList.add(2L);
		testList.add(3L);
		testList.add(4L);
		testList.add(5L);

		final IPrimativeLongList expectTrue = new LongArrayList();
		expectTrue.addAll(Collections.unmodifiableList(Arrays
				.asList(new Long[] { 3L, 2L, 4L })));
		assertTrue("contains true", testList.containsAll(expectTrue));

		final IPrimativeLongList expectFalse = new LongArrayList();
		expectFalse.addAll(Collections.unmodifiableList(Arrays
				.asList(new Long[] { 7L, 2L, 4L })));
		assertFalse("contains false", testList.containsAll(expectFalse));
	}

	@Test
	public void get() {
		testList.add(1L);
		testList.add(2L);
		testList.add(3L);
		testList.add(4L);
		testList.add(5L);

		assertEquals(3L, testList.get(2));
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
		testList.add(1L);
		testList.add(2L);
		testList.add(3L);
		testList.add(3L);
		testList.add(5L);

		assertEquals(2L, testList.indexOf(3L));
	}

	@Test(expected = IllegalArgumentException.class)
	public void indexOfNotFound() {
		testList.add(1L);
		testList.add(2L);
		testList.add(3L);
		testList.add(3L);
		testList.add(5L);

		testList.indexOf(7L);
	}

	@Test
	public void isEmpty() {
		assertTrue(testList.isEmpty());

		testList.add(1L);
		assertFalse(testList.isEmpty());

		testList.clear();
		assertTrue(testList.isEmpty());
	}

	@Test
	public void lastIndexOf() {
		testList.add(1L);
		testList.add(2L);
		testList.add(3L);
		testList.add(3L);
		testList.add(5L);

		assertEquals(3L, testList.lastIndexOf(3L));
	}

	@Test
	public void removeValue() {
		testList.add(1L);
		testList.add(3L);
		testList.add(2L);
		testList.add(3L);
		testList.add(5L);

		assertFalse(testList.removeValue(-4L));
		assertTrue(testList.removeValue(3L));

		assertArrayEquals(new long[] { 1L, 2L, 3L, 5L }, testList.toArray());
	}

	@Test
	public void remove() {
		testList.add(1L);
		testList.add(2L);
		testList.add(3L);
		testList.add(3L);
		testList.add(5L);

		assertTrue(testList.remove(2));

		assertEquals(4L, testList.addPointer);
		assertArrayEquals(new long[] { 1L, 2L, 3L, 5L }, testList.toArray());
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
		testList.add(1L);
		testList.add(2L);
		testList.add(3L);
		testList.add(4L);
		testList.add(5L);

		assertFalse(testList.removeAll(Collections.unmodifiableList(Arrays
				.asList(new Long[] { 20L, 30L, 40L }))));

		assertTrue(testList.removeAll(Collections.unmodifiableList(Arrays
				.asList(new Long[] { 2L, 3L, 4L }))));

		assertArrayEquals(new long[] { 1L, 5L }, testList.toArray());
	}

	@Test
	public void retainAllCollection() {
		testList.add(1L);
		testList.add(2L);
		testList.add(3L);
		testList.add(4L);
		testList.add(5L);

		assertTrue(testList.retainAll(Collections.unmodifiableList(Arrays
				.asList(new Long[] { 2L, 3L, 4L }))));

		assertArrayEquals(new long[] { 2L, 3L, 4L }, testList.toArray());
	}

	@Test
	public void set() {
		testList.add(1L);
		testList.add(2L);
		testList.add(3L);
		testList.add(4L);
		testList.add(5L);

		assertEquals(3L, testList.set(2, 10L));

		assertArrayEquals(new long[] { 1L, 2L, 10L, 4L, 5L },
				testList.toArray());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testSetBelow() {
		try {
			exceptionList.set(-1, 6L);
		} catch (final ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testSetAbove() {
		try {
			exceptionList.set(6, 100L);
		} catch (final ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test
	public void subList() {
		testList.add(1L);
		testList.add(2L);
		testList.add(3L);
		testList.add(4L);
		testList.add(5L);

		assertArrayEquals(new long[] { 2L, 3L, 4L }, testList.subList(1, 4)
				.toArray());
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

	private void testFill(LongArrayList al) {
		for (int i = 0; i < 11; ++i) {
			al.add(i);
		}
	}
}
