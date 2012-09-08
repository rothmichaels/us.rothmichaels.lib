/*
 * FloatArrayList.java
 *
 * Oct 28, 2011 
 */
package us.rothmichaels.lib.util;

import java.util.Arrays;

/**
 *
 *
 * @author Roth Michaels (<i><a href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 *
 */
public class FloatArrayList {

	protected float data[];
	protected int addPointer;

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
	 * Store a float at the end of the list
	 * 
	 * @param f
	 */
	public final void add(float f) {
		// if array is full, double the size
		if (addPointer >= data.length) {
			float tmp[] = data;
			data = new float[tmp.length*2];
			System.arraycopy(tmp, 0, data, 0, tmp.length);
		}
		data[addPointer++] = f;
	}
	
	/**
	 * Store a float at a specific index
	 * 
	 * @param index
	 * @param f
	 */
	public void add(int index, float f) {
		// if array is full, double the size
		if (addPointer >= data.length) {
			float tmp[] = data;
			data = new float[tmp.length*2];
			System.arraycopy(tmp, 0, data, 0, tmp.length);
		}
		System.arraycopy(data, index, data, index+1, addPointer-index);
		data[index] = f;
		++addPointer;
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
		Arrays.fill(data, 0f);
		addPointer = 0;
	}

	/**
	 * @return the FloatArrayList as float[]
	 */
	public final float[] toArray() {
		float out[] = new float[addPointer];
		System.arraycopy(data, 0, out, 0, addPointer);
		return out;
	}

}