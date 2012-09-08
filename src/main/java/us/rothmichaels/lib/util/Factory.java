/*
 * Factory.java
 *
 * Feb 24, 2012 
 */
package us.rothmichaels.lib.util;

/**
 *
 *
 * @author Roth Michaels (<i><a href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 *
 */
public interface Factory<T> {
	
	/**
	 * @return the class type to construct
	 */
	Class<T> getFactoryClass();
	
	/**
	 * @return New object of class typed by Factory
	 */
	T construct();
}
