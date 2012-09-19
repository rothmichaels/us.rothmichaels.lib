/*
 * IntegerArrayList.java
 *
 * Oct 28, 2011 
 */
package us.rothmichaels.lists;

import java.util.Arrays;
import java.util.Collection;

/**
 * An {@link java.util.ArrayList}-like structure for storing primative ints.
 *
 * @author Roth Michaels (<i><a href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 *
 */
public class IntArrayList implements IPrimativeIntList {

	int data[];
	int addPointer;

	/**
	 * Create an empty IntegerArrayList with size 10.
	 */
	public IntArrayList() {
		this(10);
	}

	/**
	 * Create an empty IntegerArrayList with arbitrary size
	 * 
	 * @param initialSize Initial size
	 */
	public IntArrayList(int initialSize) {
		data = new int[initialSize];
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeIntegerList#add(int)
	 */
	@Override
	public boolean add(int f) {
		// if array is full, double the size
		if (addPointer >= data.length) {
			int tmp[] = data;
			data = new int[tmp.length*2];
			System.arraycopy(tmp, 0, data, 0, tmp.length);
		}
		data[addPointer++] = f;

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeIntegerList#add(int, int)
	 */
	@Override
	public void add(int index, int f) {
		if (index < addPointer) {
			// if array is full, double the size
			if (addPointer >= data.length) {
				int tmp[] = data;
				data = new int[tmp.length*2];
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
	 * @see us.rothmichaels.lists.IPrimativeIntegerList#size()
	 */
	@Override
	public int size() {
		return addPointer;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeIntegerList#clear()
	 */
	@Override
	public void clear() {
		Arrays.fill(data, 0);
		addPointer = 0;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeIntegerList#toArray()
	 */
	@Override
	public final int[] toArray() {
		int out[] = new int[addPointer];
		System.arraycopy(data, 0, out, 0, addPointer);
		return out;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeIntegerList#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<Integer> c) {
		for (Integer f : c) {
			add(f);
		}
		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeIntegerList#addAll(int, java.util.Collection)
	 */
	@Override
	public boolean addAll(int index, Collection<Integer> c) {
		if (index < addPointer) {
			for (int value : c) {
				add(index++, value);
			}
		} else {
			throw new IndexOutOfBoundsException(""+index);
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeIntegerList#addAll(us.rothmichaels.lists.IPrimativeIntegerList)
	 */
	@Override
	public boolean addAll(IPrimativeIntList l) {
		int[] a = l.toArray();
		for (int f : a) {
			add(f);
		}
		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeIntegerList#addAll(int, us.rothmichaels.lists.IPrimativeIntegerList)
	 */
	@Override
	public boolean addAll(int index, IPrimativeIntList l) {
		if (index < addPointer) {
			int[] a = l.toArray();
			for (int value : a) {
				add(index++, value);
			}
		} else {
			throw new IndexOutOfBoundsException(""+index);
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeIntegerList#contains(int)
	 */
	@Override
	public boolean contains(int value) {
		boolean r = false;

		for (int val : data) {
			r |= (val == value);
		}

		return r;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeIntegerList#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<Integer> c) {
		for (int value : c) {
			boolean b = false;
			for (int val : data) {
				b |= (val == value);
			}
			if (!b) {
				return false;
			}
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeIntegerList#containsAll(us.rothmichaels.lists.IPrimativeIntegerList)
	 */
	@Override
	public boolean containsAll(IPrimativeIntList c) {
		int[] a = c.toArray();

		for (int value : a) {
			boolean b = false;
			for (int val : data) {
				b |= (val == value);
			}
			if (!b) {
				return false;
			}
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeIntegerList#get(int)
	 */
	@Override
	public int get(int index) {
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
	 * @see us.rothmichaels.lists.IPrimativeIntegerList#indexOf(int)
	 * @throws IllegalArgumentException if input does not exist in list
	 */
	@Override
	public int indexOf(int i) throws IllegalArgumentException {
		for (int j = 0; j < data.length; ++j) {
			if (data[j] == i) {
				return j;
			}
		}

		throw new IllegalArgumentException();
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeIntegerList#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return (addPointer == 0);
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeIntegerList#lastIndexOf(int)
	 * @throws IllegalArgumentException if input does not exist in list
	 */
	@Override
	public int lastIndexOf(int value) throws IllegalArgumentException {
		for (int j = data.length-1; j >= 0 ; --j) {
			if (data[j] == value) {
				return j;
			}
		}

		throw new IllegalArgumentException();
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeIntegerList#removeValue(int)
	 */
	@Override
	public boolean removeValue(int value) {
		for (int i = 0; i < addPointer; ++i) {
			if (value == data[i]) {
				return remove(i);
			}
		}

		return false;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeIntegerList#remove(int)
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
	 * @see us.rothmichaels.lists.IPrimativeIntegerList#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<Integer> c) {
		boolean r = false;
		for (int value : c) {
			r |= removeValue(value);
		}

		return r;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeIntegerList#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<Integer> c) {
		for (int i = 0; i < addPointer; ++i) {
			if (!c.contains(data[i])) {
				remove(i);
			}
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeIntegerList#set(int, int)
	 */
	@Override
	public int set(int index, int element) {
		if (index < addPointer) {
			try {
				int old = data[index];
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
	 * @see us.rothmichaels.lists.IPrimativeIntegerList#subList(int, int)
	 */
	@Override
	public IPrimativeIntList subList(int fromIndex, int toIndex) {
		if (toIndex > addPointer || toIndex < fromIndex) {
			throw new IndexOutOfBoundsException();
		}
		final int newSize = toIndex - fromIndex; 
		final IntArrayList out = new IntArrayList();

		try {
			System.arraycopy(data, fromIndex, out.data, 0, newSize);
			out.addPointer = newSize;
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException(String.format("[%d %d)",fromIndex,toIndex));
		}

		return out;
	}

}
