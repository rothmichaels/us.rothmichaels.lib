/*
 * IShortList.java
 *
 * Sep 9, 2012 
 */
package us.rothmichaels.lib.lists;

import java.util.Collection;

/**
 * An interface for Lists of primative shorts.
 * 
 * @see java.lang.List
 * 
 * @author Roth Michaels (<i><a
 *		   href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 */
public interface IPrimativeShortList {

	/**
	 * Appends an short to the end of the list.
	 *
	 * @param e short to append to the list
	 *
	 * @return {@code true} (as specified by {@link Collection#add})
	 */
	boolean add(short e);

	/**
	 * 
	 * @param index
	 * @param element
	 */
	void add(int index, short element);

	/**
	 * Appends the specified collection to the list.
	 *
	 * @param c the collection to append
	 *
	 * @return {@code true} if list was modified as a result of the call
	 */
	boolean addAll(Collection<Short> c);

	/**
	 * Insert the elements in the specified collection into
	 * the list at the specified index.
	 *
	 * @param index the index at which to insert new members
	 * @param c the collection to insert
	 * 
	 * @return {@code true} if list was modified as a result of the call
	 */
	boolean addAll(int index, Collection<Short> c);

	/**
	 * Appends the specified list to the list.
	 *
	 * @param c the list to append
	 *
	 * @return {@code true} if list was modified as a result of the call
	 */
	boolean addAll(IPrimativeShortList l);

	/**
	 * Insert the elements in the specified input into
	 * the list at the specified index.
	 *
	 * @param index the index at which to insert new members
	 * @param l the list to insert
	 * 
	 * @return {@code true} if list was modified as a result of the call
	 */
	boolean addAll(int index, IPrimativeShortList l);

	/**
	 * Removes all elements from the list.
	 */
	void clear();

	/**
	 * Returns true if the list contains the specified short.
	 * 
	 * @param value		value to test presence in list
	 * 
	 * @return			{@code true} if {@code value} is in list.
	 */
	boolean contains(short value);

	/**
	 * Returns true if list contains all the elements of the specified
	 * collection.
	 *
	 * @param c collection to compare with list
	 *
	 * @return {@code true} if list contained all members of input collection
	 */
	boolean containsAll(Collection<Short> c);

	/**
	 * Returns true if list contains all the elements of the specified
	 * list.
	 *
	 * @param c list to compare with list
	 *
	 * @return {@code true} if list contained all members of input list
	 */
	boolean containsAll(IPrimativeShortList c);

	/**
	 * Returns the element at the specified index.
	 *
	 * @param index the index of the element to return
	 *
	 * @return the element at {@code index}
	 */
	short get(int index);

	/**
	 * Returns the index of the first occurrence of the specified value
	 * in the list.
	 *
	 * @param i value to search for
	 *
	 * @return index of first occurance of {@code i}
	 */
	int indexOf(short i);

	/**
	 * Returns {@code true} if empty.
	 * 
	 * @return {@code true} if empty.
	 */
	boolean isEmpty();

	/**
	 * Returns the index of the last occurrence of the specified value
	 * in the list.
	 *
	 * @param i value to search for
	 *
	 * @return index of last occurance of {@code i}
	 */
	int lastIndexOf(short value);

	/**
	 * Removes the first occurance of {@code value} in the list.
	 *
	 * @param value the value to remove.
	 *
	 * @return {@code true} if list was modified by this operation.
	 */
	boolean removeValue(short value);

	/**
	 * Remove the element at the specified index.
	 *
	 * @param index The index of the element to be reomved.
	 *
	 * @return {@code true} if list was modified by this operation.
	 */
	boolean remove(int index);

	/**
	 * 
	 * @param c
	 *
	 * @return {@code true} if list was modified by this operation.
	 */
	boolean removeAll(Collection<?> c);

	/**
	 * 
	 * @param c
	 * @return
	 */
	boolean retainAll(Collection<?> c);

	/**
	 * Set the value at a specific index in the list.
	 * 
	 * @param index the index to set
	 * @param element the value to set
	 * 
	 * @return the old value at index (null if no value).
	 */
	Short set(int index, short element);

	/**
	 * Returns the number of elements in this list. Does not overflow,
	 * but returns {@code Integer.MAX_VALUE} if the result
	 * will ont fit in an {@code int}
	 * 
	 * @return the number of elements in this list
	 */
	int size();

	/**
	 * Returns a sublist. 
	 *
	 * @param fromIndex starting index of the sublist (inclusive)
	 * @param toIndex end index of the sublist (exclusive)
	 *
	 * @return the sublist
	 */
	IPrimativeShortList subList(int fromIndex, int toIndex);

	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence (from first to last element).
	 *
	 * <p>The returned array should be safe copy free to be modified by
	 * the caller even if the internal datastructure of the implementing
	 * list is an array.
	 *
	 * @return an array containing all of the elements in this list in proper
	 *		   sequence
	 */
	short[] toArray();

	/**
	 * Compares the specified object with this list for equality.
	 * Returns <tt>true</tt> if and only if the specified object is also
	 * a list, both lists have the same size, and all corresponding
	 * pairs of elements in the two lists are <i>equal</i>.	 In other
	 * words, two lists are defined to be equal if they contain the same
	 * elements in the same order.	The type of the list does not matter
	 * if both all elements satisfy numerical equality.
	 *
	 * @param o the object to be compared for equality with this list
	 *
	 * @return {@code true} if the specified object is equal to this list
	 */
	@Override
	boolean equals(Object o);
	
	/**
	 * Returns the hash code value for this list.  The hash code of a list
	 * is defined to be the result of the following calculation:
	 * <pre>
	 *	int hashCode = 1;
	 *	for (E e : list)
	 *		hashCode = 31*hashCode + (e==null ? 0 : e.hashCode());
	 * </pre>
	 * This ensures that <tt>list1.equals(list2)</tt> implies that
	 * <tt>list1.hashCode()==list2.hashCode()</tt> for any two lists,
	 * <tt>list1</tt> and <tt>list2</tt>, as required by the general
	 * contract of {@link Object#hashCode}.
	 *
	 * @return the hash code value for this list
	 *
	 * @see Object#equals(Object)
	 * @see #equals(Object)
	 */
	@Override
	int hashCode();

}
