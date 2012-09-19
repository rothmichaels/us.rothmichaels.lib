/*
 * DoubleArrayList.java
 *
 * Copyright (c) 2011 Roth Michaels. All rights reserved.
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
 * Oct 28, 2011 
 */
package us.rothmichaels.lists;

import java.util.Arrays;
import java.util.Collection;

/**
 * An {@link java.util.ArrayList}-like structure for storing primative doubles.
 *
 * @author Roth Michaels (<i><a href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 *
 */
public class DoubleArrayList implements IPrimativeDoubleList {

	double data[];
	int addPointer;

	/**
	 * Create an empty DoubleArrayList with size 10.
	 */
	public DoubleArrayList() {
		this(10);
	}

	/**
	 * Create an empty DoubleArrayList with arbitrary size
	 * 
	 * @param initialSize Initial size
	 */
	public DoubleArrayList(int initialSize) {
		data = new double[initialSize];
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeDoubleList#add(double)
	 */
	@Override
	public boolean add(double f) {
		// if array is full, double the size
		if (addPointer >= data.length) {
			double tmp[] = data;
			data = new double[tmp.length*2];
			System.arraycopy(tmp, 0, data, 0, tmp.length);
		}
		data[addPointer++] = f;

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeDoubleList#add(int, double)
	 */
	@Override
	public void add(int index, double f) {
		if (index < addPointer) {
			// if array is full, double the size
			if (addPointer >= data.length) {
				double tmp[] = data;
				data = new double[tmp.length*2];
				System.arraycopy(tmp, 0, data, 0, tmp.length);
			}
			try {
				System.arraycopy(data, index, data, index+1, addPointer-index);
				data[index] = f;
				++addPointer;
			} catch (ArrayIndexOutOfBoundsException e) {
				throw new IndexOutOfBoundsException(""+index);
			}
		} else {
			throw new IndexOutOfBoundsException(""+index);
		}
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeDoubleList#size()
	 */
	@Override
	public int size() {
		return addPointer;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeDoubleList#clear()
	 */
	@Override
	public void clear() {
		Arrays.fill(data, 0f);
		addPointer = 0;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeDoubleList#toArray()
	 */
	@Override
	public final double[] toArray() {
		double out[] = new double[addPointer];
		System.arraycopy(data, 0, out, 0, addPointer);
		return out;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeDoubleList#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<Double> c) {
		for (Double f : c) {
			add(f);
		}
		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeDoubleList#addAll(int, java.util.Collection)
	 */
	@Override
	public boolean addAll(int index, Collection<Double> c) {
		if (index < addPointer) {
			for (double value : c) {
				add(index++, value);
			}
		} else {
			throw new IndexOutOfBoundsException(""+index);
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeDoubleList#addAll(us.rothmichaels.lists.IPrimativeDoubleList)
	 */
	@Override
	public boolean addAll(IPrimativeDoubleList l) {
		double[] a = l.toArray();
		for (double f : a) {
			add(f);
		}
		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeDoubleList#addAll(int, us.rothmichaels.lists.IPrimativeDoubleList)
	 */
	@Override
	public boolean addAll(int index, IPrimativeDoubleList l) {
		if (index < addPointer) {
			double[] a = l.toArray();
			for (double value : a) {
				add(index++, value);
			}
		} else {
			throw new IndexOutOfBoundsException(""+index);
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeDoubleList#contains(double)
	 */
	@Override
	public boolean contains(double value) {
		boolean r = false;

		for (double val : data) {
			r |= (val == value);
		}

		return r;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeDoubleList#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<Double> c) {
		for (double value : c) {
			boolean b = false;
			for (double val : data) {
				b |= (val == value);
			}
			if (!b) {
				return false;
			}
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeDoubleList#containsAll(us.rothmichaels.lists.IPrimativeDoubleList)
	 */
	@Override
	public boolean containsAll(IPrimativeDoubleList c) {
		double[] a = c.toArray();

		for (double value : a) {
			boolean b = false;
			for (double val : data) {
				b |= (val == value);
			}
			if (!b) {
				return false;
			}
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeDoubleList#get(int)
	 */
	@Override
	public double get(int index) {
		if (index < addPointer) {
			try {
				return data[index];				
			} catch (ArrayIndexOutOfBoundsException e) {
				throw new IndexOutOfBoundsException(""+index);
			}
		} else {
			throw new IndexOutOfBoundsException(""+index);
		}

	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeDoubleList#indexOf(double)
	 * @throws IllegalArgumentException if input does not exist in list
	 */
	@Override
	public int indexOf(double i) throws IllegalArgumentException {
		for (int j = 0; j < data.length; ++j) {
			if (data[j] == i) {
				return j;
			}
		}

		throw new IllegalArgumentException();
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeDoubleList#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return (addPointer == 0);
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeDoubleList#lastIndexOf(double)
	 * @throws IllegalArgumentException if input does not exist in list
	 */
	@Override
	public int lastIndexOf(double value) throws IllegalArgumentException {
		for (int j = data.length-1; j >= 0 ; --j) {
			if (data[j] == value) {
				return j;
			}
		}

		throw new IllegalArgumentException();
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeDoubleList#removeValue(double)
	 */
	@Override
	public boolean removeValue(double value) {
		for (int i = 0; i < addPointer; ++i) {
			if (value == data[i]) {
				return remove(i);
			}
		}

		return false;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeDoubleList#remove(int)
	 */
	@Override
	public boolean remove(int index) {
		if (index < addPointer) {
			try {
				System.arraycopy(data, index+1, data, index, addPointer-index);
				--addPointer;
			} catch (ArrayIndexOutOfBoundsException e) {
				throw new IndexOutOfBoundsException(""+index);
			}
		} else {
			throw new IndexOutOfBoundsException(""+index);
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeDoubleList#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<Double> c) {
		boolean r = false;
		for (double value : c) {
			r |= removeValue(value);
		}

		return r;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeDoubleList#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<Double> c) {
		for (int i = 0; i < addPointer; ++i) {
			if (!c.contains(data[i])) {
				remove(i);
			}
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeDoubleList#set(int, double)
	 */
	@Override
	public double set(int index, double element) {
		if (index < addPointer) {
			try {
				double old = data[index];
				data[index] = element;

				return old; // RETURN

			} catch (ArrayIndexOutOfBoundsException e) {
				throw new IndexOutOfBoundsException(""+index);
			}
		} else {
			throw new IndexOutOfBoundsException(""+index);
		}
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeDoubleList#subList(int, int)
	 */
	@Override
	public IPrimativeDoubleList subList(int fromIndex, int toIndex) {
		if (toIndex > addPointer || toIndex < fromIndex) {
			throw new IndexOutOfBoundsException();
		}
		final int newSize = toIndex - fromIndex; 
		final DoubleArrayList out = new DoubleArrayList();

		try {
			System.arraycopy(data, fromIndex, out.data, 0, newSize);
			out.addPointer = newSize;
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException(String.format("[%d %d)",fromIndex,toIndex));
		}

		return out;
	}

}
