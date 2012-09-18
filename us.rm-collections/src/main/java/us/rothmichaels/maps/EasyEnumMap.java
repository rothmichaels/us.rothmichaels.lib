/*
 * EasyEnumMap.java
 *
 * Sep 16, 2012 
 */
package us.rothmichaels.maps;

import java.util.EnumMap;
import java.util.Map;

/**
 * An {@link java.util.EnumMap} that has a convenience method 
 * {@link EasyEnumMap#p(Enum, Object) to help creating maps.
 *
 * @author Roth Michaels (<i><a href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 *
 */
public class EasyEnumMap<K extends Enum<K>, V> extends EnumMap<K, V> {
	private static final long serialVersionUID = 1L;

	/**
	 * @see EnumMap#EnumMap(Class)
	 */
	public EasyEnumMap(Class<K> keyType) {
		super(keyType);
	}

	/**
	 * @see EnumMap#EnumMap(EnumMap)
	 */
	public EasyEnumMap(EnumMap<K, ? extends V> m) {
		super(m);
	}

	/**
	 * @see EnumMap#EnumMap(Map)
	 */
	public EasyEnumMap(Map<K, ? extends V> m) {
		super(m);
	}

	/**
	 * Puts a value in the map and returns itself to 
	 * simplify map creation.
	 * 
	 * @param key key to set
	 * @param value the value
	 * 
	 * @return itself
	 */
	public EasyEnumMap<K, V> p(K key, V value) {
		put(key, value);
		
		return this;
	}
}
