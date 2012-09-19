/*
 * ArrayBuilderTests.java
 *
 * Copyright (c) 2012 Roth Michaels. All rights reserved.
 *
 * The use and distribution terms for this software are covered by the
 * Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php) 
 * which can be found in the file epl-v10.html at the root of this
 * distribution. By using this software in any fashion, you are agreeing
 * to be bound by the terms of this license.
 *
 * EXCEPT AS EXPRESSLY SET FORTH IN THIS AGREEMENT, THE PROGRAM IS
 * PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, EITHER EXPRESS OR IMPLIED INCLUDING, WITHOUT LIMITATION, ANY
 * WARRANTIES OR CONDITIONS OF TITLE, NON-INFRINGEMENT, MERCHANTABILITY
 * OR FITNESS FOR A PARTICULAR PURPOSE. Each Recipient is solely
 * responsible for determining the appropriateness of using and
 * distributing the Program and assumes all risks associated with its
 * exercise of rights under this Agreement , including but not limited
 * to the risks and costs of program errors, compliance with applicable
 * laws, damage to or loss of data, programs or equipment, and
 * unavailability or interruption of operations.
 *
 * EXCEPT AS EXPRESSLY SET FORTH IN THIS AGREEMENT, NEITHER RECIPIENT
 * NOR ANY CONTRIBUTORS SHALL HAVE ANY LIABILITY FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING WITHOUT LIMITATION LOST PROFITS), HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
 * TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF
 * THE USE OR DISTRIBUTION OF THE PROGRAM OR THE EXERCISE OF ANY RIGHTS
 * GRANTED HEREUNDER, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH
 * DAMAGES.
 *
 * You must not remove this notice, or any other, from this software.
 *
 * Feb 27, 2012 
 */
package us.rothmichaels.util;

import static org.junit.Assert.*;

import us.rothmichaels.util.ArrayBuilder;
import us.rothmichaels.util.Factory;

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
