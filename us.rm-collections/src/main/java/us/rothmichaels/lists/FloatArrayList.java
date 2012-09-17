/*
 * FloatArrayList.java
 *
 * Oct 28, 2011 
 */
package us.rothmichaels.lists;

import java.util.Arrays;
import java.util.Collection;

/**
 *
 *
 * @author Roth Michaels (<i><a href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 *
 */
public class FloatArrayList implements IPrimativeFloatList {

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
	 * @see us.rothmichaels.lists.IPrimativeFloatList#add(float)
	 */
	@Override
	public boolean add(float f) {
		// if array is full, double the size
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
		final int addCount = c.size();
		if ((addPointer+addCount-1) >= data.length) {
			float[] tmp = data;
			data = new float[tmp.length+(addCount<<1)];
//			System.arraycopy(data, index, dest, index+addCount, addPointer-index);
		}
		
		return false; // XXX
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeFloatList#addAll(us.rothmichaels.lists.IPrimativeFloatList)
	 */
	@Override
	public boolean addAll(IPrimativeFloatList l) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeFloatList#addAll(int, us.rothmichaels.lists.IPrimativeFloatList)
	 */
	@Override
	public boolean addAll(int index, IPrimativeFloatList l) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeFloatList#contains(float)
	 */
	@Override
	public boolean contains(float value) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeFloatList#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<Float> c) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeFloatList#containsAll(us.rothmichaels.lists.IPrimativeFloatList)
	 */
	@Override
	public boolean containsAll(IPrimativeFloatList c) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeFloatList#get(int)
	 */
	@Override
	public float get(int index) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeFloatList#indexOf(float)
	 */
	@Override
	public int indexOf(float i) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeFloatList#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeFloatList#lastIndexOf(float)
	 */
	@Override
	public int lastIndexOf(float value) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeFloatList#removeValue(float)
	 */
	@Override
	public boolean removeValue(float value) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeFloatList#remove(int)
	 */
	@Override
	public boolean remove(int index) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeFloatList#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeFloatList#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeFloatList#set(int, float)
	 */
	@Override
	public Float set(int index, float element) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeFloatList#subList(int, int)
	 */
	@Override
	public IPrimativeFloatList subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}