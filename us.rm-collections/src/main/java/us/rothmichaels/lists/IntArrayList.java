/*
 * IntArrayList.java
 *
 * Dec 19, 2011 
 */
package us.rothmichaels.lists;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Behaves like an {@link ArrayList} for storing primative type ints
 *
 * @author Roth Michaels (<i><a href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 *
 */
public class IntArrayList {
	protected int[] data;
	protected int addPointer;

	/**
	 * Create an empty FloatArrayList with size 10.
	 */
	public IntArrayList() {
		this(10);
	}
	
	/**
	 * Create an empty FloatArrayList with arbitrary size
	 * 
	 * @param initialSize Initial size
	 */
	public IntArrayList(int initialSize) {
		data = new int[initialSize];
	}
	
	/**
	 * Store a float at the end of the list
	 * 
	 * @param value
	 */
	public final void add(int value) {
		// if array is full, double the size
		if (addPointer >= data.length) {
			int tmp[] = data;
			data = new int[tmp.length*2];
			System.arraycopy(tmp, 0, data, 0, tmp.length);
		}
		data[addPointer++] = value;
	}
	
	/**
	 * Store a float at a specific index
	 * 
	 * @param index
	 * @param value
	 */
	public void add(int index, int value) {
		// if array is full, double the size
		if (addPointer >= data.length) {
			int tmp[] = data;
			data = new int[tmp.length*2];
			System.arraycopy(tmp, 0, data, 0, tmp.length);
		}
		System.arraycopy(data, index, data, index+1, addPointer-index);
		data[index] = value;
		++addPointer;
	}
	
	public void set(int index, int value) {
		if (index >= addPointer || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		data[index] = value;
	}
	
	/**
	 * Lookup a value in the IntArrayList 
	 * 
	 * @param index
	 */
	public int get(int index) {
		if (index >= addPointer || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		return data[index];
	}
	
	public void remove(int index) {
		if (index >= addPointer || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		System.arraycopy(data, index+1, data, index, (--addPointer)-index);
	}

	/**
	 * @return current size
	 */
	public int size() {
		return addPointer;
	}

	/**
	 * Clear the FloatArrayList while maintaining current size.
	 */
	public final void clear() {
		Arrays.fill(data, 0);
		addPointer = 0;
	}

	/**
	 * @return the FloatArrayList as float[]
	 */
	public final int[] toArray() {
		int[] out = new int[addPointer];
		System.arraycopy(data, 0, out, 0, addPointer);
		return out;
	}
}
