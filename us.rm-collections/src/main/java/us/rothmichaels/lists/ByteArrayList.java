/*
 * ByteArrayList.java
 *
 * Oct 28, 2011 
 */
package us.rothmichaels.lists;

import java.util.Arrays;
import java.util.Collection;

/**
 * An {@link java.util.ArrayList}-like structure for storing primative bytes.
 *
 * @author Roth Michaels (<i><a href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 *
 */
public class ByteArrayList implements IPrimativeByteList {

	protected byte data[];
	protected int addPointer;

	/**
	 * Create an empty ByteArrayList with size 10.
	 */
	public ByteArrayList() {
		this(10);
	}

	/**
	 * Create an empty ByteArrayList with arbitrary size
	 * 
	 * @param initialSize Initial size
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
			byte tmp[] = data;
			data = new byte[tmp.length*2];
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
				byte tmp[] = data;
				data = new byte[tmp.length*2];
				System.arraycopy(tmp, 0, data, 0, tmp.length);
			}
			try {
				System.arraycopy(data, index, data, index+1, addPointer-index);
				data[index] = f;
				++addPointer;
			} catch (ArrayIndexOutOfBoundsException e) {
				throw new IndexOutOfBoundsException(""+index);
			}
		} else {
			throw new IndexOutOfBoundsException(""+index);
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
		byte out[] = new byte[addPointer];
		System.arraycopy(data, 0, out, 0, addPointer);
		return out;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeByteList#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<Byte> c) {
		for (Byte f : c) {
			add(f);
		}
		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeByteList#addAll(int, java.util.Collection)
	 */
	@Override
	public boolean addAll(int index, Collection<Byte> c) {
		if (index < addPointer) {
			for (byte value : c) {
				add(index++, value);
			}
		} else {
			throw new IndexOutOfBoundsException(""+index);
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeByteList#addAll(us.rothmichaels.lists.IPrimativeByteList)
	 */
	@Override
	public boolean addAll(IPrimativeByteList l) {
		byte[] a = l.toArray();
		for (byte f : a) {
			add(f);
		}
		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeByteList#addAll(int, us.rothmichaels.lists.IPrimativeByteList)
	 */
	@Override
	public boolean addAll(int index, IPrimativeByteList l) {
		if (index < addPointer) {
			byte[] a = l.toArray();
			for (byte value : a) {
				add(index++, value);
			}
		} else {
			throw new IndexOutOfBoundsException(""+index);
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeByteList#contains(byte)
	 */
	@Override
	public boolean contains(byte value) {
		boolean r = false;

		for (byte val : data) {
			r |= (val == value);
		}

		return r;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeByteList#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<Byte> c) {
		for (byte value : c) {
			boolean b = false;
			for (byte val : data) {
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
		byte[] a = c.toArray();

		for (byte value : a) {
			boolean b = false;
			for (byte val : data) {
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
			} catch (ArrayIndexOutOfBoundsException e) {
				throw new IndexOutOfBoundsException(""+index);
			}
		} else {
			throw new IndexOutOfBoundsException(""+index);
		}

	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeByteList#indexOf(byte)
	 * @throws IllegalArgumentException if input does not exist in list
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
	 * @throws IllegalArgumentException if input does not exist in list
	 */
	@Override
	public int lastIndexOf(byte value) throws IllegalArgumentException {
		for (int j = data.length-1; j >= 0 ; --j) {
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
				System.arraycopy(data, index+1, data, index, addPointer-index);
				--addPointer;
			} catch (ArrayIndexOutOfBoundsException e) {
				throw new IndexOutOfBoundsException(""+index);
			}
		} else {
			throw new IndexOutOfBoundsException(""+index);
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeByteList#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<Byte> c) {
		boolean r = false;
		for (byte value : c) {
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
				byte old = data[index];
				data[index] = element;

				return old; // RETURN

			} catch (ArrayIndexOutOfBoundsException e) {
				throw new IndexOutOfBoundsException(""+index);
			}
		} else {
			throw new IndexOutOfBoundsException(""+index);
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
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException(String.format("[%d %d)",fromIndex,toIndex));
		}

		return out;
	}

}
