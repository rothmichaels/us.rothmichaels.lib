/*
 * DataReflector.java
 *
 * Dec 15, 2011 
 */
package us.rothmichaels.lib.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

/**
 * <p>Static class that provides convenience methods around Java Reflection.
 *
 * @author Roth Michaels
 *(<i><a href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 */
public final class EasyReflection {

	/**
	 * Get a static field value from a class.
	 * 
	 * @param c the class to query
	 * @param field the field to query
	 * 
	 * @return the field value
	 * 
	 * @throws IllegalAccessException 
	 * Should not be thrown since since field will be set accessible 
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getFieldValue(Class<?> c, String field) throws IllegalAccessException {
		Field theField = null;
		try {
			theField = getField(c,field);
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		theField.setAccessible(true);
		
		return (T) theField.get(null);
	}
	
	/**
	 * Set a static field.
	 * 
	 * @param c the class whose field will be set.
	 * @param field the field to set.
	 * @param value the value to set
	 * 
	 * @throws IllegalArgumentException
	 * thrown if {@code value} is of the wrong type.
	 * @throws IllegalAccessException
	 * Should not be thrown since since field will be set accessible 
	 */
	public static <T> void  setFieldValue(Class<?> c, String field, T value) throws IllegalArgumentException, IllegalAccessException {
		Field theField = null;
		try {
			theField = getField(c,field);
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		theField.setAccessible(true);
		makeModifiable(theField);
		
		theField.set(null, value);
	}
	
	/**
	 * Get a field value from an object.
	 * 
	 * @param obj the object to query
	 * @param field the field to query
	 * 
	 * @return the field value
	 * 
	 * @throws IllegalAccessException 
	 * Should not be thrown since since field will be set accessible 
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getFieldValue(Object obj, String field) throws IllegalAccessException {
		Field theField = null;
		try {
			theField = getField(obj.getClass(),field);
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		theField.setAccessible(true);
		makeModifiable(theField);
		
		return (T) theField.get(obj);
	}
	
	/**
	 * Set a field.
	 * 
	 * @param obj the object whose field will be set.
	 * @param field the field to set.
	 * @param value the value to set
	 * 
	 * @throws IllegalArgumentException
	 * thrown if {@code value} is of the wrong type.
	 * @throws IllegalAccessException
	 * Should not be thrown since since field will be set accessible 
	 */
	public static <T> void  setFieldValue(Object obj, String field, T value) throws IllegalArgumentException, IllegalAccessException {
		Field theField = null;
		try {
			theField = getField(obj.getClass(),field);
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		theField.setAccessible(true);
		
		theField.set(obj, value);
	}

	/**
	 * Get all the fields declared in {@code c} and its superclasses.
	 * 
	 * @param c class whose fields will be retrieved
	 * 
	 * @return list of all fields in class {@code c}'s hierarchy.
	 */
	public static List<Field> getFields(Class<?> c) {
		List<Field> fields = Arrays.asList(c.getDeclaredFields());
		Class<?> superClass = c.getSuperclass();
		
		if (superClass != null) {
			fields.addAll(getFields(superClass));
		}
		
		return fields;
	}
	
	/**
	 * Retrieve a field by name.
	 * 
	 * <p>If the field is not declared for class {@code c}
	 * the class hierarchy will be searched until the field is found.
	 * 
	 * @param c class to query
	 * @param field field to get
	 * 
	 * @return the field, or {@code null} if no field matching {@code field} was found.
	 */
	private static Field getField(Class<?> c, String field) {
		try {
			return c.getDeclaredField(field);
		} catch (NoSuchFieldException e) {
			Class<?> superClass = c.getSuperclass();
			if (superClass != null) {
				return getField(superClass, field);
			} else {
				e.printStackTrace();
				return null;
			}
		}
	}
	
	/**
	 * Removes the {@code final} modifier from a field.
	 * 
	 * @param field field to remove {@code final} flag.
	 */
	private static void makeModifiable(Field field) {
		Field modifiersField = getField(Field.class, "modifiers");
		modifiersField.setAccessible(true);
		try {
			modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * You can't instantiate this class
	 * @deprecated
	 */
	@Deprecated
	private EasyReflection() {
		throw new AssertionError();
	}
}