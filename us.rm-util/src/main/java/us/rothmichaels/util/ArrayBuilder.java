/*
 * ArrayBuilder.java
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
 * Feb 26, 2012 
 */
package us.rothmichaels.util;

import java.lang.reflect.Array;

/**
 * Array building tools.
 * 
 * @author Roth Michaels (<i><a
 *         href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 * 
 */
public final class ArrayBuilder {
	/**
	 * Build an array typed by a given class and filled with objects of that
	 * class instantiated with a generic constructor.
	 * 
	 * @param c
	 *            class to fill array with
	 * @param size
	 *            size of array
	 * 
	 * @return array of type {@code T} and length {@code size} and filled with
	 *         objects constructed from {@code Class<T> c}.
	 * 
	 * @throws InstantiationException
	 *             if cannot instatiate {@code Class<T> c}
	 * 
	 * @throws IllegalAccessException
	 *             if do not have access to generic constructor of
	 *             {@code Class<T> c}
	 */
	public static <T> T[] buildArray(Class<T> c, int size)
			throws InstantiationException, IllegalAccessException {
		@SuppressWarnings("unchecked")
		final T[] array = (T[]) Array.newInstance(c, size);
		for (int i = 0; i < array.length; ++i) {
			array[i] = c.newInstance();
		}
		return array;
	}

	/**
	 * Construct an array of type {@code T} using the supplied factory.
	 * 
	 * @param factory
	 *            factory to use to construct the array
	 * @param size
	 *            size of the array
	 * 
	 * @return the new array
	 */
	public static <T> T[] buildArrayWithFactory(Factory<T> factory, int size) {
		@SuppressWarnings("unchecked")
		final T[] array = (T[]) Array.newInstance(factory.getFactoryClass(),
				size);
		for (int i = 0; i < array.length; ++i) {
			array[i] = factory.construct();
		}
		return array;
	}

	/**
	 * Construct an array of cloned objects.
	 * 
	 * @param obj
	 *            the object to clone into the array
	 * @param size
	 *            size of the array
	 * 
	 * @return the new array
	 */
	public static <T extends CloneSupported<T>> T[] buildArrayWithClones(T obj,
			int size) {
		@SuppressWarnings("unchecked")
		final T[] array = (T[]) Array.newInstance(obj.getClass(), size);
		for (int i = 0; i < array.length; ++i) {
			array[i] = obj.clone();
		}
		return array;
	}

	/**
	 * Do not instantiate this class
	 * 
	 * @deprecated
	 */
	@Deprecated
	private ArrayBuilder() {
		throw new AssertionError();
	}
}
