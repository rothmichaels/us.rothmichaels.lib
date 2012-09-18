/*
 * EasyEnumMapTests.java
 *
 * Sep 19, 2012 
 */
package us.rothmichaels.maps;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@link EasyEnumMap}
 * 
 * @author Roth Michaels (<i><a
 *         href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 * 
 */
public class EasyEnumMapTests {

	enum TestEnum {
		O1, O2;
	}

	@Test
	public void test() {
		Object o1 = new Object();
		Object o2 = new Object();
		EasyEnumMap<TestEnum, Object> testMap = new EasyEnumMap<TestEnum, Object>(
				TestEnum.class);
		
		testMap.p(TestEnum.O1, o1).p(TestEnum.O2, o2);
		
		assertSame(o1, testMap.get(TestEnum.O1));
		assertSame(o2, testMap.get(TestEnum.O2));
	}

}
