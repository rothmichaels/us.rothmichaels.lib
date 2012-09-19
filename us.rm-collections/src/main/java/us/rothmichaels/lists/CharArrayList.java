/*
 * CharacterArrayList.java
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
 * Oct 28, 2011 
 */
package us.rothmichaels.lists;

import java.util.Arrays;
import java.util.Collection;

/**
 * An {@link java.util.ArrayList}-like structure for storing primative chars.
 * 
 * @author Roth Michaels (<i><a
 *         href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 * 
 */
public class CharArrayList implements IPrimativeCharList {

	char data[];
	int addPointer;

	/**
	 * Create an empty CharacterArrayList with size 10.
	 */
	public CharArrayList() {
		this(10);
	}

	/**
	 * Create an empty CharacterArrayList with arbitrary size
	 * 
	 * @param initialSize
	 *            Initial size
	 */
	public CharArrayList(int initialSize) {
		data = new char[initialSize];
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeCharList#add(char)
	 */
	@Override
	public boolean add(char f) {
		// if array is full, double the size
		if (addPointer >= data.length) {
			final char tmp[] = data;
			data = new char[tmp.length * 2];
			System.arraycopy(tmp, 0, data, 0, tmp.length);
		}
		data[addPointer++] = f;

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeCharList#add(int, char)
	 */
	@Override
	public void add(int index, char f) {
		if (index < addPointer) {
			// if array is full, double the size
			if (addPointer >= data.length) {
				final char tmp[] = data;
				data = new char[tmp.length * 2];
				System.arraycopy(tmp, 0, data, 0, tmp.length);
			}
			try {
				System.arraycopy(data, index, data, index + 1, addPointer
						- index);
				data[index] = f;
				++addPointer;
			} catch (final ArrayIndexOutOfBoundsException e) {
				throw new IndexOutOfBoundsException("" + index);
			}
		} else {
			throw new IndexOutOfBoundsException("" + index);
		}
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeCharList#size()
	 */
	@Override
	public int size() {
		return addPointer;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeCharList#clear()
	 */
	@Override
	public void clear() {
		Arrays.fill(data, (char) 0);
		addPointer = 0;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeCharList#toArray()
	 */
	@Override
	public final char[] toArray() {
		final char out[] = new char[addPointer];
		System.arraycopy(data, 0, out, 0, addPointer);
		return out;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeCharList#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<Character> c) {
		for (final Character f : c) {
			add(f);
		}
		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeCharList#addAll(int,
	 *      java.util.Collection)
	 */
	@Override
	public boolean addAll(int index, Collection<Character> c) {
		if (index < addPointer) {
			for (final char value : c) {
				add(index++, value);
			}
		} else {
			throw new IndexOutOfBoundsException("" + index);
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeCharList#addAll(us.rothmichaels.lists.IPrimativeCharList)
	 */
	@Override
	public boolean addAll(IPrimativeCharList l) {
		final char[] a = l.toArray();
		for (final char f : a) {
			add(f);
		}
		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeCharList#addAll(int,
	 *      us.rothmichaels.lists.IPrimativeCharList)
	 */
	@Override
	public boolean addAll(int index, IPrimativeCharList l) {
		if (index < addPointer) {
			final char[] a = l.toArray();
			for (final char value : a) {
				add(index++, value);
			}
		} else {
			throw new IndexOutOfBoundsException("" + index);
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeCharList#contains(char)
	 */
	@Override
	public boolean contains(char value) {
		boolean r = false;

		for (final char val : data) {
			r |= (val == value);
		}

		return r;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeCharList#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<Character> c) {
		for (final char value : c) {
			boolean b = false;
			for (final char val : data) {
				b |= (val == value);
			}
			if (!b) {
				return false;
			}
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeCharList#containsAll(us.rothmichaels.lists.IPrimativeCharList)
	 */
	@Override
	public boolean containsAll(IPrimativeCharList c) {
		final char[] a = c.toArray();

		for (final char value : a) {
			boolean b = false;
			for (final char val : data) {
				b |= (val == value);
			}
			if (!b) {
				return false;
			}
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeCharList#get(int)
	 */
	@Override
	public char get(int index) {
		if (index < addPointer) {
			try {
				return data[index];
			} catch (final ArrayIndexOutOfBoundsException e) {
				throw new IndexOutOfBoundsException("" + index);
			}
		} else {
			throw new IndexOutOfBoundsException("" + index);
		}

	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeCharList#indexOf(char)
	 * @throws IllegalArgumentException
	 *             if input does not exist in list
	 */
	@Override
	public int indexOf(char i) throws IllegalArgumentException {
		for (int j = 0; j < data.length; ++j) {
			if (data[j] == i) {
				return j;
			}
		}

		throw new IllegalArgumentException();
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeCharList#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return (addPointer == 0);
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeCharList#lastIndexOf(char)
	 * @throws IllegalArgumentException
	 *             if input does not exist in list
	 */
	@Override
	public int lastIndexOf(char value) throws IllegalArgumentException {
		for (int j = data.length - 1; j >= 0; --j) {
			if (data[j] == value) {
				return j;
			}
		}

		throw new IllegalArgumentException();
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeCharList#removeValue(char)
	 */
	@Override
	public boolean removeValue(char value) {
		for (int i = 0; i < addPointer; ++i) {
			if (value == data[i]) {
				return remove(i);
			}
		}

		return false;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeCharList#remove(int)
	 */
	@Override
	public boolean remove(int index) {
		if (index < addPointer) {
			try {
				System.arraycopy(data, index + 1, data, index, addPointer
						- index);
				--addPointer;
			} catch (final ArrayIndexOutOfBoundsException e) {
				throw new IndexOutOfBoundsException("" + index);
			}
		} else {
			throw new IndexOutOfBoundsException("" + index);
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeCharList#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<Character> c) {
		boolean r = false;
		for (final char value : c) {
			r |= removeValue(value);
		}

		return r;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeCharList#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<Character> c) {
		for (int i = 0; i < addPointer; ++i) {
			if (!c.contains(data[i])) {
				remove(i);
			}
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeCharList#set(int, char)
	 */
	@Override
	public char set(int index, char element) {
		if (index < addPointer) {
			try {
				final char old = data[index];
				data[index] = element;

				return old; // RETURN

			} catch (final ArrayIndexOutOfBoundsException e) {
				throw new IndexOutOfBoundsException("" + index);
			}
		} else {
			throw new IndexOutOfBoundsException("" + index);
		}
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeCharList#subList(int, int)
	 */
	@Override
	public IPrimativeCharList subList(int fromIndex, int toIndex) {
		if (toIndex > addPointer || toIndex < fromIndex) {
			throw new IndexOutOfBoundsException();
		}
		final int newSize = toIndex - fromIndex;
		final CharArrayList out = new CharArrayList();

		try {
			System.arraycopy(data, fromIndex, out.data, 0, newSize);
			out.addPointer = newSize;
		} catch (final ArrayIndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException(String.format("[%d %d)",
					fromIndex, toIndex));
		}

		return out;
	}

}
