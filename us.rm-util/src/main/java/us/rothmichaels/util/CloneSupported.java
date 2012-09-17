package us.rothmichaels.util;


/*
 * CloneSupported.java
 *
 * Feb 28, 2012 
 */

/**
 *
 *
 * @author Roth Michaels (<i><a href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 *
 */
public interface CloneSupported<T> extends Cloneable {
	
	T clone();
}
