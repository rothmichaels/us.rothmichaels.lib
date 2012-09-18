/*
 * LongArrayList.java
 *
 * Oct 28, 2011 
 */
package us.rothmichaels.lists;

import java.util.Arrays;
import java.util.Collection;

/**
 * An {@link java.util.ArrayList}-like structure for storing primative longs.
 *
 * @author Roth Michaels (<i><a href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 *
 */
public class LongArrayList implements IPrimativeLongList {

	protected long data[];
	protected int addPointer;

	/**
	 * Create an empty LongArrayList with size 10.
	 */
	public LongArrayList() {
		this(10);
	}

	/**
	 * Create an empty LongArrayList with arbitrary size
	 * 
	 * @param initialSize Initial size
	 */
	public LongArrayList(int initialSize) {
		data = new long[initialSize];
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeLongList#add(long)
	 */
	@Override
	public boolean add(long f) {
		// if array is full, double the size
		if (addPointer >= data.length) {
			long tmp[] = data;
			data = new long[tmp.length*2];
			System.arraycopy(tmp, 0, data, 0, tmp.length);
		}
		data[addPointer++] = f;

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeLongList#add(int, long)
	 */
	@Override
	public void add(int index, long f) {
		if (index < addPointer) {
			// if array is full, double the size
			if (addPointer >= data.length) {
				long tmp[] = data;
				data = new long[tmp.length*2];
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
	 * @see us.rothmichaels.lists.IPrimativeLongList#size()
	 */
	@Override
	public int size() {
		return addPointer;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeLongList#clear()
	 */
	@Override
	public void clear() {
		Arrays.fill(data, 0);
		addPointer = 0;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeLongList#toArray()
	 */
	@Override
	public final long[] toArray() {
		long out[] = new long[addPointer];
		System.arraycopy(data, 0, out, 0, addPointer);
		return out;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeLongList#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<Long> c) {
		for (Long f : c) {
			add(f);
		}
		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeLongList#addAll(int, java.util.Collection)
	 */
	@Override
	public boolean addAll(int index, Collection<Long> c) {
		if (index < addPointer) {
			for (long value : c) {
				add(index++, value);
			}
		} else {
			throw new IndexOutOfBoundsException(""+index);
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeLongList#addAll(us.rothmichaels.lists.IPrimativeLongList)
	 */
	@Override
	public boolean addAll(IPrimativeLongList l) {
		long[] a = l.toArray();
		for (long f : a) {
			add(f);
		}
		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeLongList#addAll(int, us.rothmichaels.lists.IPrimativeLongList)
	 */
	@Override
	public boolean addAll(int index, IPrimativeLongList l) {
		if (index < addPointer) {
			long[] a = l.toArray();
			for (long value : a) {
				add(index++, value);
			}
		} else {
			throw new IndexOutOfBoundsException(""+index);
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeLongList#contains(long)
	 */
	@Override
	public boolean contains(long value) {
		boolean r = false;

		for (long val : data) {
			r |= (val == value);
		}

		return r;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeLongList#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<Long> c) {
		for (long value : c) {
			boolean b = false;
			for (long val : data) {
				b |= (val == value);
			}
			if (!b) {
				return false;
			}
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeLongList#containsAll(us.rothmichaels.lists.IPrimativeLongList)
	 */
	@Override
	public boolean containsAll(IPrimativeLongList c) {
		long[] a = c.toArray();

		for (long value : a) {
			boolean b = false;
			for (long val : data) {
				b |= (val == value);
			}
			if (!b) {
				return false;
			}
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeLongList#get(int)
	 */
	@Override
	public long get(int index) {
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
	 * @see us.rothmichaels.lists.IPrimativeLongList#indexOf(long)
	 * @throws IllegalArgumentException if input does not exist in list
	 */
	@Override
	public int indexOf(long i) throws IllegalArgumentException {
		for (int j = 0; j < data.length; ++j) {
			if (data[j] == i) {
				return j;
			}
		}

		throw new IllegalArgumentException();
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeLongList#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return (addPointer == 0);
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeLongList#lastIndexOf(long)
	 * @throws IllegalArgumentException if input does not exist in list
	 */
	@Override
	public int lastIndexOf(long value) throws IllegalArgumentException {
		for (int j = data.length-1; j >= 0 ; --j) {
			if (data[j] == value) {
				return j;
			}
		}

		throw new IllegalArgumentException();
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeLongList#removeValue(long)
	 */
	@Override
	public boolean removeValue(long value) {
		for (int i = 0; i < addPointer; ++i) {
			if (value == data[i]) {
				return remove(i);
			}
		}

		return false;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeLongList#remove(int)
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
	 * @see us.rothmichaels.lists.IPrimativeLongList#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<Long> c) {
		boolean r = false;
		for (long value : c) {
			r |= removeValue(value);
		}

		return r;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeLongList#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<Long> c) {
		for (int i = 0; i < addPointer; ++i) {
			if (!c.contains(data[i])) {
				remove(i);
			}
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeLongList#set(int, long)
	 */
	@Override
	public long set(int index, long element) {
		if (index < addPointer) {
			try {
				long old = data[index];
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
	 * @see us.rothmichaels.lists.IPrimativeLongList#subList(int, int)
	 */
	@Override
	public IPrimativeLongList subList(int fromIndex, int toIndex) {
		if (toIndex > addPointer || toIndex < fromIndex) {
			throw new IndexOutOfBoundsException();
		}
		final int newSize = toIndex - fromIndex; 
		final LongArrayList out = new LongArrayList();

		try {
			System.arraycopy(data, fromIndex, out.data, 0, newSize);
			out.addPointer = newSize;
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException(String.format("[%d %d)",fromIndex,toIndex));
		}

		return out;
	}

}
