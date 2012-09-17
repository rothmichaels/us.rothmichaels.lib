/*
 * ArrayBuilder.java
 *
 * Feb 26, 2012 
 */
package us.rothmichaels.util;

import java.lang.reflect.Array;



/**
 * Array building tools.
 *
 * @author Roth Michaels (<i><a href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 *
 */
public final class ArrayBuilder {
	
	/**
	 * Do not instantiate this class 
	 * @deprecated
	 */
	@Deprecated
	private ArrayBuilder() {
		throw new AssertionError();
	}
	
	/**
	 * Build an array typed by a given class and filled
	 * with objects of that class instantiated with
	 * a generic constructor.
	 * 
	 * @param c		class to fill array with
	 * @param size	size of array
	 * 
	 * @return
	 * 	array of type {@code T} and length {@code size}
	 * 	and filled with objects constructed from
	 *  {@code Class<T> c}. 
	 * 
	 * @throws InstantiationException
	 * 	if cannot instatiate {@code Class<T> c}
	 * 
	 * @throws IllegalAccessException
	 * 	if do not have access to generic constructor
	 * 	of {@code Class<T> c}
	 */
	public static <T>T[] buildArray(Class<T> c, int size) throws InstantiationException, IllegalAccessException {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) Array.newInstance(c, size);
		for (int i = 0; i < array.length; ++i) {
			array[i] = c.newInstance();
		}
		return array;
	}
	
	public static <T>T[] buildArrayWithFactory(Factory<T> factory, int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) Array.newInstance(factory.getFactoryClass(), size);
		for (int i = 0; i < array.length; ++i) {
			array[i] = 	factory.construct();
		}
		return array;
	}
	
	public static <T extends CloneSupported<T>>T[] buildArrayWithClones(T obj, int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) Array.newInstance(obj.getClass(), size);
		for (int i = 0; i < array.length; ++i) {
			array[i] = (T) obj.clone();
		}
		return array;
	}
}
