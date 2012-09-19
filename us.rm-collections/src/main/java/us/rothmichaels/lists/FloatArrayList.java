/*
 * FloatArrayList.java
 *
 * Oct 28, 2011 
 */
package us.rothmichaels.lists;

import java.util.Arrays;
import java.util.Collection;

/**
 * An {@link java.util.ArrayList}-like structure for storing primative floats.
 *
 * @author Roth Michaels (<i><a href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 *
 */
public class FloatArrayList implements IPrimativeFloatList {

	float data[];
	int addPointer;

	/**
	 * Create an empty FloatArrayList with size 10.
	 */
	public FloatArrayList() {
		this(10);
	}

	/**
	 * Create an empty FloatArrayList with arbitrary size
	 * 
	 * @param initialSize Initial size
	 */
	public FloatArrayList(int initialSize) {
		data = new float[initialSize];
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeFloatList#add(float)
	 */
	@Override
	public boolean add(float f) {
		// if array is full, float the size
		if (addPointer >= data.length) {
			float tmp[] = data;
			data = new float[tmp.length*2];
			System.arraycopy(tmp, 0, data, 0, tmp.length);
		}
		data[addPointer++] = f;

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeFloatList#add(int, float)
	 */
	@Override
	public void add(int index, float f) {
		if (index < addPointer) {
			// if array is full, float the size
			if (addPointer >= data.length) {
				float tmp[] = data;
				data = new float[tmp.length*2];
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
	 * @see us.rothmichaels.lists.IPrimativeFloatList#size()
	 */
	@Override
	public int size() {
		return addPointer;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeFloatList#clear()
	 */
	@Override
	public void clear() {
		Arrays.fill(data, 0f);
		addPointer = 0;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeFloatList#toArray()
	 */
	@Override
	public final float[] toArray() {
		float out[] = new float[addPointer];
		System.arraycopy(data, 0, out, 0, addPointer);
		return out;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeFloatList#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<Float> c) {
		for (Float f : c) {
			add(f);
		}
		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeFloatList#addAll(int, java.util.Collection)
	 */
	@Override
	public boolean addAll(int index, Collection<Float> c) {
		if (index < addPointer) {
			for (float value : c) {
				add(index++, value);
			}
		} else {
			throw new IndexOutOfBoundsException(""+index);
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeFloatList#addAll(us.rothmichaels.lists.IPrimativeFloatList)
	 */
	@Override
	public boolean addAll(IPrimativeFloatList l) {
		float[] a = l.toArray();
		for (float f : a) {
			add(f);
		}
		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeFloatList#addAll(int, us.rothmichaels.lists.IPrimativeFloatList)
	 */
	@Override
	public boolean addAll(int index, IPrimativeFloatList l) {
		if (index < addPointer) {
			float[] a = l.toArray();
			for (float value : a) {
				add(index++, value);
			}
		} else {
			throw new IndexOutOfBoundsException(""+index);
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeFloatList#contains(float)
	 */
	@Override
	public boolean contains(float value) {
		boolean r = false;

		for (float val : data) {
			r |= (val == value);
		}

		return r;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeFloatList#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<Float> c) {
		for (float value : c) {
			boolean b = false;
			for (float val : data) {
				b |= (val == value);
			}
			if (!b) {
				return false;
			}
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeFloatList#containsAll(us.rothmichaels.lists.IPrimativeFloatList)
	 */
	@Override
	public boolean containsAll(IPrimativeFloatList c) {
		float[] a = c.toArray();

		for (float value : a) {
			boolean b = false;
			for (float val : data) {
				b |= (val == value);
			}
			if (!b) {
				return false;
			}
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeFloatList#get(int)
	 */
	@Override
	public float get(int index) {
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
	 * @see us.rothmichaels.lists.IPrimativeFloatList#indexOf(float)
	 * @throws IllegalArgumentException if input does not exist in list
	 */
	@Override
	public int indexOf(float i) throws IllegalArgumentException {
		for (int j = 0; j < data.length; ++j) {
			if (data[j] == i) {
				return j;
			}
		}

		throw new IllegalArgumentException();
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeFloatList#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return (addPointer == 0);
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeFloatList#lastIndexOf(float)
	 * @throws IllegalArgumentException if input does not exist in list
	 */
	@Override
	public int lastIndexOf(float value) throws IllegalArgumentException {
		for (int j = data.length-1; j >= 0 ; --j) {
			if (data[j] == value) {
				return j;
			}
		}

		throw new IllegalArgumentException();
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeFloatList#removeValue(float)
	 */
	@Override
	public boolean removeValue(float value) {
		for (int i = 0; i < addPointer; ++i) {
			if (value == data[i]) {
				return remove(i);
			}
		}

		return false;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeFloatList#remove(int)
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
	 * @see us.rothmichaels.lists.IPrimativeFloatList#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<Float> c) {
		boolean r = false;
		for (float value : c) {
			r |= removeValue(value);
		}

		return r;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeFloatList#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<Float> c) {
		for (int i = 0; i < addPointer; ++i) {
			if (!c.contains(data[i])) {
				remove(i);
			}
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeFloatList#set(int, float)
	 */
	@Override
	public float set(int index, float element) {
		if (index < addPointer) {
			try {
				float old = data[index];
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
	 * @see us.rothmichaels.lists.IPrimativeFloatList#subList(int, int)
	 */
	@Override
	public IPrimativeFloatList subList(int fromIndex, int toIndex) {
		if (toIndex > addPointer || toIndex < fromIndex) {
			throw new IndexOutOfBoundsException();
		}
		final int newSize = toIndex - fromIndex; 
		final FloatArrayList out = new FloatArrayList();

		try {
			System.arraycopy(data, fromIndex, out.data, 0, newSize);
			out.addPointer = newSize;
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException(String.format("[%d %d)",fromIndex,toIndex));
		}

		return out;
	}

}
