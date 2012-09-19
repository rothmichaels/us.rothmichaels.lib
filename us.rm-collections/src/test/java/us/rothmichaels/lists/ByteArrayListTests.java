/*
 * ByteArrayListTests.java
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
 * Dec (byte) 13, (byte) 2011 
 */
package us.rothmichaels.lists;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import us.rothmichaels.lists.ByteArrayList;

/**
 * Unit Tests for {@link ByteArrayList}
 * 
 * @author Roth Michaels (<i><a
 *		   href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 */
public class ByteArrayListTests {
	static final int INITIAL_SIZE = 10;
	static final int TEST_ADD_POINTER_LOC = 11;
	static final int EXPANDED_INTERNAL_SIZE = 20;
	static final byte[] TEST_ARRAY = { (byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10 };
	static final byte[] INTERNAL_TEST_ARRAY = new byte[EXPANDED_INTERNAL_SIZE];
	static {
		System.arraycopy(TEST_ARRAY, 0, INTERNAL_TEST_ARRAY, 0,
				TEST_ARRAY.length);
	}

	ByteArrayList testList;

	ByteArrayList exceptionList;

	@Before
	public void setUp() {
		testList = new ByteArrayList();
		exceptionList = new ByteArrayList();
		exceptionList.add((byte) 1);
		exceptionList.add((byte) 2);
		exceptionList.add((byte) 3);
		exceptionList.add((byte) 4);
		exceptionList.add((byte) 5);
	}

	@After
	public void tearDown() {
	}

	@Test
	public void defaultConstructor() {
		// test initial internal array
		assertEquals("internal addPointer", 0, testList.addPointer);
		org.junit.Assert.assertArrayEquals("internal array",
				new byte[INITIAL_SIZE], testList.data);

		// test initial output
		// TODO move to other tests
		assertEquals("output size", 0, testList.size());
		assertArrayEquals("output array", new byte[0], testList.toArray());
	}

	@Test
	public void initialSizeConstructor() {
		final int initialSize = 13;
		@SuppressWarnings("hiding")
		final ByteArrayList testList = new ByteArrayList(initialSize);

		// test initial internal array
		assertEquals("internal addPointer", 0, testList.addPointer);
		assertArrayEquals("internal array", new byte[initialSize],
				testList.data);

		// test initial output
		// TODO move to other tests
		assertEquals("output size", 0, testList.size());
		assertArrayEquals("output array", new byte[0], testList.toArray());
	}

	@Test
	public void add() {
		final byte testValue = (byte) 6;
		final byte[] expectedInternal = new byte[INITIAL_SIZE];
		expectedInternal[0] = testValue;

		testList.add(testValue);

		// test internal
		assertEquals("internal addPointer", (byte) 1, testList.addPointer);
		assertArrayEquals("internal array", expectedInternal, testList.data);

		// test output
		assertEquals("output size", (byte) 1, testList.size());
		assertArrayEquals("output array", new byte[] { testValue },
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
		final byte[] testArray = { (byte) 0, (byte) 1, (byte) 23, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10 };
		final byte[] internalTestArray = new byte[EXPANDED_INTERNAL_SIZE];
		System.arraycopy(testArray, 0, internalTestArray, 0, testArray.length);

		testFill(testList);
		testList.add(2, (byte) 23);

		// test internal
		assertEquals("internal addPointer", TEST_ADD_POINTER_LOC + (byte) 1,
				testList.addPointer);
		assertArrayEquals("internal array", internalTestArray, testList.data);

		// test output
		assertEquals("output size", TEST_ADD_POINTER_LOC + (byte) 1, testList.size());
		assertArrayEquals("output array", testArray, testList.toArray());
	}

	@Test
	public void clear() {
		testFill(testList);

		final byte[] prevInternalArray = testList.data;
		testList.clear();

		// test internal
		assertEquals("internal addPointer", 0, testList.addPointer);
		assertArrayEquals("internal array", new byte[EXPANDED_INTERNAL_SIZE],
				testList.data);
		assertSame(prevInternalArray, testList.data);

		// test external
		assertEquals("output size", 0, testList.size());
		assertArrayEquals("output array", new byte[0], testList.toArray());
	}

	@Test
	public void size() {
		// new list size
		assertEquals(0, testList.size());

		testList.add((byte) 2);
		testList.add((byte) 3);

		// test size
		assertEquals((byte) 2, testList.size());
	}

	@Test
	public void toArray() {
		// test initial array
		assertArrayEquals("output array", new byte[0], testList.toArray());

		testList.add((byte) 2);
		testList.add((byte) 3);

		assertArrayEquals(new byte[] { (byte) 2, (byte) 3 }, testList.toArray());
	}

	@Test
	public void addAllCollection() {
		testList.add((byte) 1);

		assertTrue(testList.addAll(Collections.unmodifiableList(Arrays
				.asList(new Byte[] { (byte) 2, (byte) 3, (byte) 4 }))));

		assertEquals((byte) 1, testList.data[0]);
		assertEquals((byte) 2, testList.data[1]);
		assertEquals((byte) 3, testList.data[2]);
		assertEquals((byte) 4, testList.data[3]);
	}

	@Test
	public void addAllCollectionAtIndex() {
		testList.add((byte) 10);
		testList.add((byte) 10);
		testList.add((byte) 10);

		assertTrue(testList.addAll(
				1,
				Collections.unmodifiableList(Arrays.asList(new Byte[] { (byte) 2,
						(byte) 3, (byte) 4 }))));

		assertEquals((byte) 10, testList.data[0]);
		assertEquals((byte) 2, testList.data[1]);
		assertEquals((byte) 3, testList.data[2]);
		assertEquals((byte) 4, testList.data[3]);
		assertEquals((byte) 10, testList.data[4]);
		assertEquals((byte) 10, testList.data[5]);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void addAllCollectionAtIndexBelow() {
		try {
			exceptionList.addAll(
					-1,
					Collections.unmodifiableList(Arrays.asList(new Byte[] {
							(byte) 2, (byte) 3, (byte) 4 })));
		} catch (ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void addAllCollectionAtIndexAbove() {
		try {
			exceptionList.addAll(
					6,
					Collections.unmodifiableList(Arrays.asList(new Byte[] {
							(byte) 2, (byte) 3, (byte) 4 })));
		} catch (ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test
	public void addAll() {
		IPrimativeByteList byteList = new ByteArrayList();
		byteList.addAll(Collections.unmodifiableList(Arrays
				.asList(new Byte[] { (byte) 2, (byte) 3, (byte) 4 })));

		testList.add((byte) 1);
		assertTrue(testList.addAll(byteList));

		assertEquals((byte) 1, testList.data[0]);
		assertEquals((byte) 2, testList.data[1]);
		assertEquals((byte) 3, testList.data[2]);
		assertEquals((byte) 4, testList.data[3]);
	}

	@Test
	public void addAllAtIndex() {
		IPrimativeByteList byteList = new ByteArrayList();
		byteList.addAll(Collections.unmodifiableList(Arrays
				.asList(new Byte[] { (byte) 2, (byte) 3, (byte) 4 })));

		testList.add((byte) 10);
		testList.add((byte) 10);
		testList.add((byte) 10);
		assertTrue(testList.addAll(1, byteList));

		assertEquals((byte) 10, testList.data[0]);
		assertEquals((byte) 2, testList.data[1]);
		assertEquals((byte) 3, testList.data[2]);
		assertEquals((byte) 4, testList.data[3]);
		assertEquals((byte) 10, testList.data[4]);
		assertEquals((byte) 10, testList.data[5]);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void addAllAtIndexBelow() {
		IPrimativeByteList byteList = new ByteArrayList();
		byteList.addAll(Collections.unmodifiableList(Arrays
				.asList(new Byte[] { (byte) 2, (byte) 3, (byte) 4 })));

		try {
			exceptionList.addAll(-1, byteList);
		} catch (ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void addAllAtIndexAbove() {
		IPrimativeByteList byteList = new ByteArrayList();
		byteList.addAll(Collections.unmodifiableList(Arrays
				.asList(new Byte[] { (byte) 2, (byte) 3, (byte) 4 })));

		try {
			exceptionList.addAll(6, byteList);
		} catch (ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test
	public void contains() {
		testList.add((byte) 1);
		testList.add((byte) 2);
		testList.add((byte) 3);
		testList.add((byte) 4);
		testList.add((byte) 5);

		assertTrue("contains true", testList.contains((byte) 2));
		assertFalse("contains false", testList.contains((byte) -14));
	}

	@Test
	public void containsAllColleciton() {
		testList.add((byte) 1);
		testList.add((byte) 2);
		testList.add((byte) 3);
		testList.add((byte) 4);
		testList.add((byte) 5);

		assertTrue("contains true", testList.containsAll(Collections
				.unmodifiableList(Arrays.asList(new Byte[] { (byte) 3, (byte) 2, (byte) 4 }))));

		assertFalse("contains false", testList.containsAll(Collections
				.unmodifiableList(Arrays.asList(new Byte[] { (byte) 7, (byte) 3, (byte) 4 }))));
	}

	@Test
	public void containsAll() {
		testList.add((byte) 1);
		testList.add((byte) 2);
		testList.add((byte) 3);
		testList.add((byte) 4);
		testList.add((byte) 5);

		IPrimativeByteList expectTrue = new ByteArrayList();
		expectTrue.addAll(Collections.unmodifiableList(Arrays
				.asList(new Byte[] { (byte) 3, (byte) 2, (byte) 4 })));
		assertTrue("contains true", testList.containsAll(expectTrue));

		IPrimativeByteList expectFalse = new ByteArrayList();
		expectFalse.addAll(Collections.unmodifiableList(Arrays
				.asList(new Byte[] { (byte) 7, (byte) 2, (byte) 4 })));
		assertFalse("contains false", testList.containsAll(expectFalse));
	}

	@Test
	public void get() {
		testList.add((byte) 1);
		testList.add((byte) 2);
		testList.add((byte) 3);
		testList.add((byte) 4);
		testList.add((byte) 5);

		assertEquals((byte) 3, testList.get(2));
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
		testList.add((byte) 1);
		testList.add((byte) 2);
		testList.add((byte) 3);
		testList.add((byte) 3);
		testList.add((byte) 5);

		assertEquals((byte) 2, testList.indexOf((byte) 3));
	}

	@Test(expected = IllegalArgumentException.class)
	public void indexOfNotFound() {
		testList.add((byte) 1);
		testList.add((byte) 2);
		testList.add((byte) 3);
		testList.add((byte) 3);
		testList.add((byte) 5);

		testList.indexOf((byte) 7);
	}

	@Test
	public void isEmpty() {
		assertTrue(testList.isEmpty());

		testList.add((byte) 1);
		assertFalse(testList.isEmpty());

		testList.clear();
		assertTrue(testList.isEmpty());
	}

	@Test
	public void lastIndexOf() {
		testList.add((byte) 1);
		testList.add((byte) 2);
		testList.add((byte) 3);
		testList.add((byte) 3);
		testList.add((byte) 5);

		assertEquals((byte) 3, testList.lastIndexOf((byte) 3));
	}

	@Test
	public void removeValue() {
		testList.add((byte) 1);
		testList.add((byte) 3);
		testList.add((byte) 2);
		testList.add((byte) 3);
		testList.add((byte) 5);

		assertFalse(testList.removeValue((byte) -4));
		assertTrue(testList.removeValue((byte) 3));

		assertArrayEquals(new byte[] { (byte) 1, (byte) 2, (byte) 3, (byte) 5 }, testList.toArray());
	}

	@Test
	public void remove() {
		testList.add((byte) 1);
		testList.add((byte) 2);
		testList.add((byte) 3);
		testList.add((byte) 3);
		testList.add((byte) 5);

		assertTrue(testList.remove(2));

		assertEquals((byte) 4, testList.addPointer);
		assertArrayEquals(new byte[] { (byte) 1, (byte) 2, (byte) 3, (byte) 5 }, testList.toArray());
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
		testList.add((byte) 1);
		testList.add((byte) 2);
		testList.add((byte) 3);
		testList.add((byte) 4);
		testList.add((byte) 5);

		assertFalse(testList.removeAll(Collections.unmodifiableList(Arrays
				.asList(new Byte[] { (byte) 20, (byte) 30, (byte) 40 }))));

		assertTrue(testList.removeAll(Collections.unmodifiableList(Arrays
				.asList(new Byte[] { (byte) 2, (byte) 3, (byte) 4 }))));

		assertArrayEquals(new byte[] { (byte) 1, (byte) 5 }, testList.toArray());
	}

	@Test
	public void retainAllCollection() {
		testList.add((byte) 1);
		testList.add((byte) 2);
		testList.add((byte) 3);
		testList.add((byte) 4);
		testList.add((byte) 5);

		assertTrue(testList.retainAll(Collections.unmodifiableList(Arrays
				.asList(new Byte[] { (byte) 2, (byte) 3, (byte) 4 }))));

		assertArrayEquals(new byte[] { (byte) 2, (byte) 3, (byte) 4 }, testList.toArray());
	}

	@Test
	public void set() {
		testList.add((byte) 1);
		testList.add((byte) 2);
		testList.add((byte) 3);
		testList.add((byte) 4);
		testList.add((byte) 5);

		assertEquals((byte) 3, testList.set(2, (byte) 10));

		assertArrayEquals(new byte[] { (byte) 1, (byte) 2, (byte) 10, (byte) 4, (byte) 5 },
				testList.toArray());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testSetBelow() {
		try {
			exceptionList.set(-1, (byte) 6);
		} catch (ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testSetAbove() {
		try {
			exceptionList.set(6, (byte) 100);
		} catch (ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test
	public void subList() {
		testList.add((byte) 1);
		testList.add((byte) 2);
		testList.add((byte) 3);
		testList.add((byte) 4);
		testList.add((byte) 5);

		assertArrayEquals(new byte[] { (byte) 2, (byte) 3, (byte) 4 }, testList.subList(1, 4)
				.toArray());
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

	private void testFill(ByteArrayList al) {
		for (byte i = 0; i < 11; ++i) {
			al.add(i);
		}
	}
}
