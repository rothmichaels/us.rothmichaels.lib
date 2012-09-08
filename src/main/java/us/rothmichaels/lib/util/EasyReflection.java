/*
 * DataReflector.java
 *
 * Dec 15, 2011 
 */
package us.rothmichaels.lib.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * <p>Static class that provides convenience methods around Jaav Reflection.
 * 
 * <p>Methods in this class catch some possible possible exceptions so if 
 * you are trying to reflect stuff that doesn't exist you could get crazy results.
 *
 * @author Roth Michaels (<i><a href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 *
 */
public final class EasyReflection {

	
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

	public static Field[] getFields(Class<?> c) {
		return c.getDeclaredFields();
	}
	
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
