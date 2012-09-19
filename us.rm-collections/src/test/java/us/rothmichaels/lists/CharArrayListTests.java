/*
 * CharArrayListTests.java
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
 * Dec (char) 13, (char) 2011 
 */
package us.rothmichaels.lists;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import us.rothmichaels.lists.CharArrayList;

/**
 * Unit Tests for {@link CharArrayList}
 * 
 * @author Roth Michaels (<i><a
 *		   href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 */
public class CharArrayListTests {
	static final int INITIAL_SIZE = 10;
	static final int TEST_ADD_POINTER_LOC = 11;
	static final int EXPANDED_INTERNAL_SIZE = 20;
	static final char[] TEST_ARRAY = { (char) 0, (char) 1, (char) 2, (char) 3, (char) 4, (char) 5, (char) 6, (char) 7, (char) 8, (char) 9, (char) 10 };
	static final char[] INTERNAL_TEST_ARRAY = new char[EXPANDED_INTERNAL_SIZE];
	static {
		System.arraycopy(TEST_ARRAY, 0, INTERNAL_TEST_ARRAY, 0,
				TEST_ARRAY.length);
	}

	CharArrayList testList;

	CharArrayList exceptionList;

	@Before
	public void setUp() {
		testList = new CharArrayList();
		exceptionList = new CharArrayList();
		exceptionList.add((char) 1);
		exceptionList.add((char) 2);
		exceptionList.add((char) 3);
		exceptionList.add((char) 4);
		exceptionList.add((char) 5);
	}

	@After
	public void tearDown() {
	}

	@Test
	public void defaultConstructor() {
		// test initial internal array
		assertEquals("internal addPointer", 0, testList.addPointer);
		org.junit.Assert.assertArrayEquals("internal array",
				new char[INITIAL_SIZE], testList.data);

		// test initial output
		// TODO move to other tests
		assertEquals("output size", 0, testList.size());
		assertArrayEquals("output array", new char[0], testList.toArray());
	}

	@Test
	public void initialSizeConstructor() {
		final int initialSize = 13;
		@SuppressWarnings("hiding")
		final CharArrayList testList = new CharArrayList(initialSize);

		// test initial internal array
		assertEquals("internal addPointer", 0, testList.addPointer);
		assertArrayEquals("internal array", new char[initialSize],
				testList.data);

		// test initial output
		// TODO move to other tests
		assertEquals("output size", 0, testList.size());
		assertArrayEquals("output array", new char[0], testList.toArray());
	}

	@Test
	public void add() {
		final char testValue = (char) 6;
		final char[] expectedInternal = new char[INITIAL_SIZE];
		expectedInternal[0] = testValue;

		testList.add(testValue);

		// test internal
		assertEquals("internal addPointer", (char) 1, testList.addPointer);
		assertArrayEquals("internal array", expectedInternal, testList.data);

		// test output
		assertEquals("output size", (char) 1, testList.size());
		assertArrayEquals("output array", new char[] { testValue },
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
		final char[] testArray = { (char) 0, (char) 1, (char) 23, (char) 2, (char) 3, (char) 4, (char) 5, (char) 6, (char) 7, (char) 8, (char) 9, (char) 10 };
		final char[] internalTestArray = new char[EXPANDED_INTERNAL_SIZE];
		System.arraycopy(testArray, 0, internalTestArray, 0, testArray.length);

		testFill(testList);
		testList.add(2, (char) 23);

		// test internal
		assertEquals("internal addPointer", TEST_ADD_POINTER_LOC + (char) 1,
				testList.addPointer);
		assertArrayEquals("internal array", internalTestArray, testList.data);

		// test output
		assertEquals("output size", TEST_ADD_POINTER_LOC + (char) 1, testList.size());
		assertArrayEquals("output array", testArray, testList.toArray());
	}

	@Test
	public void clear() {
		testFill(testList);

		final char[] prevInternalArray = testList.data;
		testList.clear();

		// test internal
		assertEquals("internal addPointer", 0, testList.addPointer);
		assertArrayEquals("internal array", new char[EXPANDED_INTERNAL_SIZE],
				testList.data);
		assertSame(prevInternalArray, testList.data);

		// test external
		assertEquals("output size", 0, testList.size());
		assertArrayEquals("output array", new char[0], testList.toArray());
	}

	@Test
	public void size() {
		// new list size
		assertEquals(0, testList.size());

		testList.add((char) 2);
		testList.add((char) 3);

		// test size
		assertEquals((char) 2, testList.size());
	}

	@Test
	public void toArray() {
		// test initial array
		assertArrayEquals("output array", new char[0], testList.toArray());

		testList.add((char) 2);
		testList.add((char) 3);

		assertArrayEquals(new char[] { (char) 2, (char) 3 }, testList.toArray());
	}

	@Test
	public void addAllCollection() {
		testList.add((char) 1);

		assertTrue(testList.addAll(Collections.unmodifiableList(Arrays
				.asList(new Character[] { (char) 2, (char) 3, (char) 4 }))));

		assertEquals((char) 1, testList.data[0]);
		assertEquals((char) 2, testList.data[1]);
		assertEquals((char) 3, testList.data[2]);
		assertEquals((char) 4, testList.data[3]);
	}

	@Test
	public void addAllCollectionAtIndex() {
		testList.add((char) 10);
		testList.add((char) 10);
		testList.add((char) 10);

		assertTrue(testList.addAll(
				1,
				Collections.unmodifiableList(Arrays.asList(new Character[] { (char) 2,
						(char) 3, (char) 4 }))));

		assertEquals((char) 10, testList.data[0]);
		assertEquals((char) 2, testList.data[1]);
		assertEquals((char) 3, testList.data[2]);
		assertEquals((char) 4, testList.data[3]);
		assertEquals((char) 10, testList.data[4]);
		assertEquals((char) 10, testList.data[5]);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void addAllCollectionAtIndexBelow() {
		try {
			exceptionList.addAll(
					-1,
					Collections.unmodifiableList(Arrays.asList(new Character[] {
							(char) 2, (char) 3, (char) 4 })));
		} catch (ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void addAllCollectionAtIndexAbove() {
		try {
			exceptionList.addAll(
					6,
					Collections.unmodifiableList(Arrays.asList(new Character[] {
							(char) 2, (char) 3, (char) 4 })));
		} catch (ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test
	public void addAll() {
		IPrimativeCharList charList = new CharArrayList();
		charList.addAll(Collections.unmodifiableList(Arrays
				.asList(new Character[] { (char) 2, (char) 3, (char) 4 })));

		testList.add((char) 1);
		assertTrue(testList.addAll(charList));

		assertEquals((char) 1, testList.data[0]);
		assertEquals((char) 2, testList.data[1]);
		assertEquals((char) 3, testList.data[2]);
		assertEquals((char) 4, testList.data[3]);
	}

	@Test
	public void addAllAtIndex() {
		IPrimativeCharList charList = new CharArrayList();
		charList.addAll(Collections.unmodifiableList(Arrays
				.asList(new Character[] { (char) 2, (char) 3, (char) 4 })));

		testList.add((char) 10);
		testList.add((char) 10);
		testList.add((char) 10);
		assertTrue(testList.addAll(1, charList));

		assertEquals((char) 10, testList.data[0]);
		assertEquals((char) 2, testList.data[1]);
		assertEquals((char) 3, testList.data[2]);
		assertEquals((char) 4, testList.data[3]);
		assertEquals((char) 10, testList.data[4]);
		assertEquals((char) 10, testList.data[5]);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void addAllAtIndexBelow() {
		IPrimativeCharList charList = new CharArrayList();
		charList.addAll(Collections.unmodifiableList(Arrays
				.asList(new Character[] { (char) 2, (char) 3, (char) 4 })));

		try {
			exceptionList.addAll(-1, charList);
		} catch (ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void addAllAtIndexAbove() {
		IPrimativeCharList charList = new CharArrayList();
		charList.addAll(Collections.unmodifiableList(Arrays
				.asList(new Character[] { (char) 2, (char) 3, (char) 4 })));

		try {
			exceptionList.addAll(6, charList);
		} catch (ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test
	public void contains() {
		testList.add((char) 1);
		testList.add((char) 2);
		testList.add((char) 3);
		testList.add((char) 4);
		testList.add((char) 5);

		assertTrue("contains true", testList.contains((char) 2));
		assertFalse("contains false", testList.contains((char) -14));
	}

	@Test
	public void containsAllColleciton() {
		testList.add((char) 1);
		testList.add((char) 2);
		testList.add((char) 3);
		testList.add((char) 4);
		testList.add((char) 5);

		assertTrue("contains true", testList.containsAll(Collections
				.unmodifiableList(Arrays.asList(new Character[] { (char) 3, (char) 2, (char) 4 }))));

		assertFalse("contains false", testList.containsAll(Collections
				.unmodifiableList(Arrays.asList(new Character[] { (char) 7, (char) 3, (char) 4 }))));
	}

	@Test
	public void containsAll() {
		testList.add((char) 1);
		testList.add((char) 2);
		testList.add((char) 3);
		testList.add((char) 4);
		testList.add((char) 5);

		IPrimativeCharList expectTrue = new CharArrayList();
		expectTrue.addAll(Collections.unmodifiableList(Arrays
				.asList(new Character[] { (char) 3, (char) 2, (char) 4 })));
		assertTrue("contains true", testList.containsAll(expectTrue));

		IPrimativeCharList expectFalse = new CharArrayList();
		expectFalse.addAll(Collections.unmodifiableList(Arrays
				.asList(new Character[] { (char) 7, (char) 2, (char) 4 })));
		assertFalse("contains false", testList.containsAll(expectFalse));
	}

	@Test
	public void get() {
		testList.add((char) 1);
		testList.add((char) 2);
		testList.add((char) 3);
		testList.add((char) 4);
		testList.add((char) 5);

		assertEquals((char) 3, testList.get(2));
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
		testList.add((char) 1);
		testList.add((char) 2);
		testList.add((char) 3);
		testList.add((char) 3);
		testList.add((char) 5);

		assertEquals((char) 2, testList.indexOf((char) 3));
	}

	@Test(expected = IllegalArgumentException.class)
	public void indexOfNotFound() {
		testList.add((char) 1);
		testList.add((char) 2);
		testList.add((char) 3);
		testList.add((char) 3);
		testList.add((char) 5);

		testList.indexOf((char) 7);
	}

	@Test
	public void isEmpty() {
		assertTrue(testList.isEmpty());

		testList.add((char) 1);
		assertFalse(testList.isEmpty());

		testList.clear();
		assertTrue(testList.isEmpty());
	}

	@Test
	public void lastIndexOf() {
		testList.add((char) 1);
		testList.add((char) 2);
		testList.add((char) 3);
		testList.add((char) 3);
		testList.add((char) 5);

		assertEquals((char) 3, testList.lastIndexOf((char) 3));
	}

	@Test
	public void removeValue() {
		testList.add((char) 1);
		testList.add((char) 3);
		testList.add((char) 2);
		testList.add((char) 3);
		testList.add((char) 5);

		assertFalse(testList.removeValue((char) -4));
		assertTrue(testList.removeValue((char) 3));

		assertArrayEquals(new char[] { (char) 1, (char) 2, (char) 3, (char) 5 }, testList.toArray());
	}

	@Test
	public void remove() {
		testList.add((char) 1);
		testList.add((char) 2);
		testList.add((char) 3);
		testList.add((char) 3);
		testList.add((char) 5);

		assertTrue(testList.remove(2));

		assertEquals((char) 4, testList.addPointer);
		assertArrayEquals(new char[] { (char) 1, (char) 2, (char) 3, (char) 5 }, testList.toArray());
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
		testList.add((char) 1);
		testList.add((char) 2);
		testList.add((char) 3);
		testList.add((char) 4);
		testList.add((char) 5);

		assertFalse(testList.removeAll(Collections.unmodifiableList(Arrays
				.asList(new Character[] { (char) 20, (char) 30, (char) 40 }))));

		assertTrue(testList.removeAll(Collections.unmodifiableList(Arrays
				.asList(new Character[] { (char) 2, (char) 3, (char) 4 }))));

		assertArrayEquals(new char[] { (char) 1, (char) 5 }, testList.toArray());
	}

	@Test
	public void retainAllCollection() {
		testList.add((char) 1);
		testList.add((char) 2);
		testList.add((char) 3);
		testList.add((char) 4);
		testList.add((char) 5);

		assertTrue(testList.retainAll(Collections.unmodifiableList(Arrays
				.asList(new Character[] { (char) 2, (char) 3, (char) 4 }))));

		assertArrayEquals(new char[] { (char) 2, (char) 3, (char) 4 }, testList.toArray());
	}

	@Test
	public void set() {
		testList.add((char) 1);
		testList.add((char) 2);
		testList.add((char) 3);
		testList.add((char) 4);
		testList.add((char) 5);

		assertEquals((char) 3, testList.set(2, (char) 10));

		assertArrayEquals(new char[] { (char) 1, (char) 2, (char) 10, (char) 4, (char) 5 },
				testList.toArray());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testSetBelow() {
		try {
			exceptionList.set(-1, (char) 6);
		} catch (ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testSetAbove() {
		try {
			exceptionList.set(6, (char) 100);
		} catch (ArrayIndexOutOfBoundsException e) {
			fail(e.toString());
		}

	}

	@Test
	public void subList() {
		testList.add((char) 1);
		testList.add((char) 2);
		testList.add((char) 3);
		testList.add((char) 4);
		testList.add((char) 5);

		assertArrayEquals(new char[] { (char) 2, (char) 3, (char) 4 }, testList.subList(1, 4)
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

	private void testFill(CharArrayList al) {
		for (int i = 0; i < 11; ++i) {
			al.add((char) i);
		}
	}
}
