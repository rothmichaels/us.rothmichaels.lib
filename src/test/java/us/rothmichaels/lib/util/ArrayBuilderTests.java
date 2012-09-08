/*
 * ArrayBuilderTests.java
 *
 * Feb 27, 2012 
 */
package us.rothmichaels.lib.util;

import static org.junit.Assert.*;

import us.rothmichaels.lib.util.Factory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 *
 * @author Roth Michaels (<i><a href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 *
 */
public class ArrayBuilderTests {

	static final int TEST_SIZE = 10;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBuildArray() {
		final MyObjectType[] array;
		{
			MyObjectType[] tmp = null;
			try {
				 tmp = ArrayBuilder.buildArray(MyObjectType.class, TEST_SIZE);
			} catch (InstantiationException e) {
				e.printStackTrace();
				fail("InstatiationException");
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				fail("IllegalAccessException");
			}
			array = tmp;
		}
		runBuildAssertions(array);
	}
	
	@Test
	public void testBuildArrayWithFactory() {
		final MyObjectType[] array = ArrayBuilder.buildArrayWithFactory(
				new MyObjectFactory(), TEST_SIZE);
		runBuildAssertions(array);
	}
	
	private <T> void runBuildAssertions(T[] array) {
		assertEquals(TEST_SIZE, array.length);
		for (int i = 0; i < TEST_SIZE; ++i) {
			assertTrue(array[i] instanceof MyObjectType);
		}
	}
	
	public static class MyObjectType {
		
	}
	
	public static class MyObjectFactory implements Factory<MyObjectType> {

		/**
		 * @see mi.interf.Factory#getFactoryClass()
		 */
		@Override
		public Class<MyObjectType> getFactoryClass() {
			return MyObjectType.class;
		}

		/**
		 * @see mi.interf.Factory#construct()
		 */
		@Override
		public MyObjectType construct() {
			return new MyObjectType();
		}
		
	}

}
