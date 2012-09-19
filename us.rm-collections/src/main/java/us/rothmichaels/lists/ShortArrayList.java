/*
 * ShortArrayList.java
 *
 * Oct 28, 2011 
 */
package us.rothmichaels.lists;

import java.util.Arrays;
import java.util.Collection;

/**
 * An {@link java.util.ArrayList}-like structure for storing primative shorts.
 *
 * @author Roth Michaels (<i><a href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 *
 */
public class ShortArrayList implements IPrimativeShortList {

	short data[];
	int addPointer;

	/**
	 * Create an empty ShortArrayList with size 10.
	 */
	public ShortArrayList() {
		this(10);
	}

	/**
	 * Create an empty ShortArrayList with arbitrary size
	 * 
	 * @param initialSize Initial size
	 */
	public ShortArrayList(int initialSize) {
		data = new short[initialSize];
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeShortList#add(short)
	 */
	@Override
	public boolean add(short f) {
		// if array is full, double the size
		if (addPointer >= data.length) {
			short tmp[] = data;
			data = new short[tmp.length*2];
			System.arraycopy(tmp, 0, data, 0, tmp.length);
		}
		data[addPointer++] = f;

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeShortList#add(int, short)
	 */
	@Override
	public void add(int index, short f) {
		if (index < addPointer) {
			// if array is full, double the size
			if (addPointer >= data.length) {
				short tmp[] = data;
				data = new short[tmp.length*2];
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
	 * @see us.rothmichaels.lists.IPrimativeShortList#size()
	 */
	@Override
	public int size() {
		return addPointer;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeShortList#clear()
	 */
	@Override
	public void clear() {
		Arrays.fill(data, (short) 0);
		addPointer = 0;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeShortList#toArray()
	 */
	@Override
	public final short[] toArray() {
		short out[] = new short[addPointer];
		System.arraycopy(data, 0, out, 0, addPointer);
		return out;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeShortList#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<Short> c) {
		for (Short f : c) {
			add(f);
		}
		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeShortList#addAll(int, java.util.Collection)
	 */
	@Override
	public boolean addAll(int index, Collection<Short> c) {
		if (index < addPointer) {
			for (short value : c) {
				add(index++, value);
			}
		} else {
			throw new IndexOutOfBoundsException(""+index);
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeShortList#addAll(us.rothmichaels.lists.IPrimativeShortList)
	 */
	@Override
	public boolean addAll(IPrimativeShortList l) {
		short[] a = l.toArray();
		for (short f : a) {
			add(f);
		}
		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeShortList#addAll(int, us.rothmichaels.lists.IPrimativeShortList)
	 */
	@Override
	public boolean addAll(int index, IPrimativeShortList l) {
		if (index < addPointer) {
			short[] a = l.toArray();
			for (short value : a) {
				add(index++, value);
			}
		} else {
			throw new IndexOutOfBoundsException(""+index);
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeShortList#contains(short)
	 */
	@Override
	public boolean contains(short value) {
		boolean r = false;

		for (short val : data) {
			r |= (val == value);
		}

		return r;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeShortList#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<Short> c) {
		for (short value : c) {
			boolean b = false;
			for (short val : data) {
				b |= (val == value);
			}
			if (!b) {
				return false;
			}
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeShortList#containsAll(us.rothmichaels.lists.IPrimativeShortList)
	 */
	@Override
	public boolean containsAll(IPrimativeShortList c) {
		short[] a = c.toArray();

		for (short value : a) {
			boolean b = false;
			for (short val : data) {
				b |= (val == value);
			}
			if (!b) {
				return false;
			}
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeShortList#get(int)
	 */
	@Override
	public short get(int index) {
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
	 * @see us.rothmichaels.lists.IPrimativeShortList#indexOf(short)
	 * @throws IllegalArgumentException if input does not exist in list
	 */
	@Override
	public int indexOf(short i) throws IllegalArgumentException {
		for (int j = 0; j < data.length; ++j) {
			if (data[j] == i) {
				return j;
			}
		}

		throw new IllegalArgumentException();
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeShortList#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return (addPointer == 0);
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeShortList#lastIndexOf(short)
	 * @throws IllegalArgumentException if input does not exist in list
	 */
	@Override
	public int lastIndexOf(short value) throws IllegalArgumentException {
		for (int j = data.length-1; j >= 0 ; --j) {
			if (data[j] == value) {
				return j;
			}
		}

		throw new IllegalArgumentException();
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeShortList#removeValue(short)
	 */
	@Override
	public boolean removeValue(short value) {
		for (int i = 0; i < addPointer; ++i) {
			if (value == data[i]) {
				return remove(i);
			}
		}

		return false;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeShortList#remove(int)
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
	 * @see us.rothmichaels.lists.IPrimativeShortList#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<Short> c) {
		boolean r = false;
		for (short value : c) {
			r |= removeValue(value);
		}

		return r;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeShortList#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<Short> c) {
		for (int i = 0; i < addPointer; ++i) {
			if (!c.contains(data[i])) {
				remove(i);
			}
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeShortList#set(int, short)
	 */
	@Override
	public short set(int index, short element) {
		if (index < addPointer) {
			try {
				short old = data[index];
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
	 * @see us.rothmichaels.lists.IPrimativeShortList#subList(int, int)
	 */
	@Override
	public IPrimativeShortList subList(int fromIndex, int toIndex) {
		if (toIndex > addPointer || toIndex < fromIndex) {
			throw new IndexOutOfBoundsException();
		}
		final int newSize = toIndex - fromIndex; 
		final ShortArrayList out = new ShortArrayList();

		try {
			System.arraycopy(data, fromIndex, out.data, 0, newSize);
			out.addPointer = newSize;
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException(String.format("[%d %d)",fromIndex,toIndex));
		}

		return out;
	}

}
