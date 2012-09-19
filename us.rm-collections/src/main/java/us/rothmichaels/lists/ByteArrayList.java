/*
 * ByteArrayList.java
 *
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
 * An {@link java.util.ArrayList}-like structure for storing primative bytes.
 * 
 * @author Roth Michaels (<i><a
 *         href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 * 
 */
public class ByteArrayList implements IPrimativeByteList {

	byte data[];
	int addPointer;

	/**
	 * Create an empty ByteArrayList with size 10.
	 */
	public ByteArrayList() {
		this(10);
	}

	/**
	 * Create an empty ByteArrayList with arbitrary size
	 * 
	 * @param initialSize
	 *            Initial size
	 */
	public ByteArrayList(int initialSize) {
		data = new byte[initialSize];
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeByteList#add(byte)
	 */
	@Override
	public boolean add(byte f) {
		// if array is full, double the size
		if (addPointer >= data.length) {
			final byte tmp[] = data;
			data = new byte[tmp.length * 2];
			System.arraycopy(tmp, 0, data, 0, tmp.length);
		}
		data[addPointer++] = f;

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeByteList#add(int, byte)
	 */
	@Override
	public void add(int index, byte f) {
		if (index < addPointer) {
			// if array is full, double the size
			if (addPointer >= data.length) {
				final byte tmp[] = data;
				data = new byte[tmp.length * 2];
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
	 * @see us.rothmichaels.lists.IPrimativeByteList#size()
	 */
	@Override
	public int size() {
		return addPointer;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeByteList#clear()
	 */
	@Override
	public void clear() {
		Arrays.fill(data, (byte) 0);
		addPointer = 0;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeByteList#toArray()
	 */
	@Override
	public final byte[] toArray() {
		final byte out[] = new byte[addPointer];
		System.arraycopy(data, 0, out, 0, addPointer);
		return out;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeByteList#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<Byte> c) {
		for (final Byte f : c) {
			add(f);
		}
		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeByteList#addAll(int,
	 *      java.util.Collection)
	 */
	@Override
	public boolean addAll(int index, Collection<Byte> c) {
		if (index < addPointer) {
			for (final byte value : c) {
				add(index++, value);
			}
		} else {
			throw new IndexOutOfBoundsException("" + index);
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeByteList#addAll(us.rothmichaels.lists.IPrimativeByteList)
	 */
	@Override
	public boolean addAll(IPrimativeByteList l) {
		final byte[] a = l.toArray();
		for (final byte f : a) {
			add(f);
		}
		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeByteList#addAll(int,
	 *      us.rothmichaels.lists.IPrimativeByteList)
	 */
	@Override
	public boolean addAll(int index, IPrimativeByteList l) {
		if (index < addPointer) {
			final byte[] a = l.toArray();
			for (final byte value : a) {
				add(index++, value);
			}
		} else {
			throw new IndexOutOfBoundsException("" + index);
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeByteList#contains(byte)
	 */
	@Override
	public boolean contains(byte value) {
		boolean r = false;

		for (final byte val : data) {
			r |= (val == value);
		}

		return r;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeByteList#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<Byte> c) {
		for (final byte value : c) {
			boolean b = false;
			for (final byte val : data) {
				b |= (val == value);
			}
			if (!b) {
				return false;
			}
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeByteList#containsAll(us.rothmichaels.lists.IPrimativeByteList)
	 */
	@Override
	public boolean containsAll(IPrimativeByteList c) {
		final byte[] a = c.toArray();

		for (final byte value : a) {
			boolean b = false;
			for (final byte val : data) {
				b |= (val == value);
			}
			if (!b) {
				return false;
			}
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeByteList#get(int)
	 */
	@Override
	public byte get(int index) {
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
	 * @see us.rothmichaels.lists.IPrimativeByteList#indexOf(byte)
	 * @throws IllegalArgumentException
	 *             if input does not exist in list
	 */
	@Override
	public int indexOf(byte i) throws IllegalArgumentException {
		for (int j = 0; j < data.length; ++j) {
			if (data[j] == i) {
				return j;
			}
		}

		throw new IllegalArgumentException();
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeByteList#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return (addPointer == 0);
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeByteList#lastIndexOf(byte)
	 * @throws IllegalArgumentException
	 *             if input does not exist in list
	 */
	@Override
	public int lastIndexOf(byte value) throws IllegalArgumentException {
		for (int j = data.length - 1; j >= 0; --j) {
			if (data[j] == value) {
				return j;
			}
		}

		throw new IllegalArgumentException();
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeByteList#removeValue(byte)
	 */
	@Override
	public boolean removeValue(byte value) {
		for (int i = 0; i < addPointer; ++i) {
			if (value == data[i]) {
				return remove(i);
			}
		}

		return false;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeByteList#remove(int)
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
	 * @see us.rothmichaels.lists.IPrimativeByteList#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<Byte> c) {
		boolean r = false;
		for (final byte value : c) {
			r |= removeValue(value);
		}

		return r;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeByteList#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<Byte> c) {
		for (int i = 0; i < addPointer; ++i) {
			if (!c.contains(data[i])) {
				remove(i);
			}
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeByteList#set(int, byte)
	 */
	@Override
	public byte set(int index, byte element) {
		if (index < addPointer) {
			try {
				final byte old = data[index];
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
	 * @see us.rothmichaels.lists.IPrimativeByteList#subList(int, int)
	 */
	@Override
	public IPrimativeByteList subList(int fromIndex, int toIndex) {
		if (toIndex > addPointer || toIndex < fromIndex) {
			throw new IndexOutOfBoundsException();
		}
		final int newSize = toIndex - fromIndex;
		final ByteArrayList out = new ByteArrayList();

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
