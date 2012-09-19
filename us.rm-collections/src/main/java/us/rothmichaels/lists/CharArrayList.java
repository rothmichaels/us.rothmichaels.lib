/*
 * CharacterArrayList.java
 *
 * Oct 28, 2011 
 */
package us.rothmichaels.lists;

import java.util.Arrays;
import java.util.Collection;

/**
 * An {@link java.util.ArrayList}-like structure for storing primative chars.
 *
 * @author Roth Michaels (<i><a href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
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
	 * @param initialSize Initial size
	 */
	public CharArrayList(int initialSize) {
		data = new char[initialSize];
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeCharacterList#add(char)
	 */
	@Override
	public boolean add(char f) {
		// if array is full, double the size
		if (addPointer >= data.length) {
			char tmp[] = data;
			data = new char[tmp.length*2];
			System.arraycopy(tmp, 0, data, 0, tmp.length);
		}
		data[addPointer++] = f;

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeCharacterList#add(int, char)
	 */
	@Override
	public void add(int index, char f) {
		if (index < addPointer) {
			// if array is full, double the size
			if (addPointer >= data.length) {
				char tmp[] = data;
				data = new char[tmp.length*2];
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
	 * @see us.rothmichaels.lists.IPrimativeCharacterList#size()
	 */
	@Override
	public int size() {
		return addPointer;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeCharacterList#clear()
	 */
	@Override
	public void clear() {
		Arrays.fill(data, (char) 0);
		addPointer = 0;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeCharacterList#toArray()
	 */
	@Override
	public final char[] toArray() {
		char out[] = new char[addPointer];
		System.arraycopy(data, 0, out, 0, addPointer);
		return out;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeCharacterList#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<Character> c) {
		for (Character f : c) {
			add(f);
		}
		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeCharacterList#addAll(int, java.util.Collection)
	 */
	@Override
	public boolean addAll(int index, Collection<Character> c) {
		if (index < addPointer) {
			for (char value : c) {
				add(index++, value);
			}
		} else {
			throw new IndexOutOfBoundsException(""+index);
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeCharacterList#addAll(us.rothmichaels.lists.IPrimativeCharacterList)
	 */
	@Override
	public boolean addAll(IPrimativeCharList l) {
		char[] a = l.toArray();
		for (char f : a) {
			add(f);
		}
		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeCharacterList#addAll(int, us.rothmichaels.lists.IPrimativeCharacterList)
	 */
	@Override
	public boolean addAll(int index, IPrimativeCharList l) {
		if (index < addPointer) {
			char[] a = l.toArray();
			for (char value : a) {
				add(index++, value);
			}
		} else {
			throw new IndexOutOfBoundsException(""+index);
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeCharacterList#contains(char)
	 */
	@Override
	public boolean contains(char value) {
		boolean r = false;

		for (char val : data) {
			r |= (val == value);
		}

		return r;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeCharacterList#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<Character> c) {
		for (char value : c) {
			boolean b = false;
			for (char val : data) {
				b |= (val == value);
			}
			if (!b) {
				return false;
			}
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeCharacterList#containsAll(us.rothmichaels.lists.IPrimativeCharacterList)
	 */
	@Override
	public boolean containsAll(IPrimativeCharList c) {
		char[] a = c.toArray();

		for (char value : a) {
			boolean b = false;
			for (char val : data) {
				b |= (val == value);
			}
			if (!b) {
				return false;
			}
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeCharacterList#get(int)
	 */
	@Override
	public char get(int index) {
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
	 * @see us.rothmichaels.lists.IPrimativeCharacterList#indexOf(char)
	 * @throws IllegalArgumentException if input does not exist in list
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
	 * @see us.rothmichaels.lists.IPrimativeCharacterList#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return (addPointer == 0);
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeCharacterList#lastIndexOf(char)
	 * @throws IllegalArgumentException if input does not exist in list
	 */
	@Override
	public int lastIndexOf(char value) throws IllegalArgumentException {
		for (int j = data.length-1; j >= 0 ; --j) {
			if (data[j] == value) {
				return j;
			}
		}

		throw new IllegalArgumentException();
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeCharacterList#removeValue(char)
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
	 * @see us.rothmichaels.lists.IPrimativeCharacterList#remove(int)
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
	 * @see us.rothmichaels.lists.IPrimativeCharacterList#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<Character> c) {
		boolean r = false;
		for (char value : c) {
			r |= removeValue(value);
		}

		return r;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeCharacterList#retainAll(java.util.Collection)
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
	 * @see us.rothmichaels.lists.IPrimativeCharacterList#set(int, char)
	 */
	@Override
	public char set(int index, char element) {
		if (index < addPointer) {
			try {
				char old = data[index];
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
	 * @see us.rothmichaels.lists.IPrimativeCharacterList#subList(int, int)
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
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException(String.format("[%d %d)",fromIndex,toIndex));
		}

		return out;
	}

}
