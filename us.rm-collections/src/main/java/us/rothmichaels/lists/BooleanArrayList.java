/*
 * BooleanArrayList.java
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
 * An {@link java.util.ArrayList}-like structure for storing primative booleans.
 *
 * @author Roth Michaels (<i><a href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 *
 */
public class BooleanArrayList implements IPrimativeBooleanList {

	boolean data[];
	int addPointer;

	/**
	 * Create an empty BooleanArrayList with size 10.
	 */
	public BooleanArrayList() {
		this(10);
	}

	/**
	 * Create an empty BooleanArrayList with arbitrary size
	 * 
	 * @param initialSize Initial size
	 */
	public BooleanArrayList(int initialSize) {
		data = new boolean[initialSize];
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeBooleanList#add(boolean)
	 */
	@Override
	public boolean add(boolean f) {
		// if array is full, double the size
		if (addPointer >= data.length) {
			boolean tmp[] = data;
			data = new boolean[tmp.length*2];
			System.arraycopy(tmp, 0, data, 0, tmp.length);
		}
		data[addPointer++] = f;

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeBooleanList#add(int, boolean)
	 */
	@Override
	public void add(int index, boolean f) {
		if (index < addPointer) {
			// if array is full, double the size
			if (addPointer >= data.length) {
				boolean tmp[] = data;
				data = new boolean[tmp.length*2];
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
	 * @see us.rothmichaels.lists.IPrimativeBooleanList#size()
	 */
	@Override
	public int size() {
		return addPointer;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeBooleanList#clear()
	 */
	@Override
	public void clear() {
		Arrays.fill(data, false);
		addPointer = 0;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeBooleanList#toArray()
	 */
	@Override
	public final boolean[] toArray() {
		boolean out[] = new boolean[addPointer];
		System.arraycopy(data, 0, out, 0, addPointer);
		return out;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeBooleanList#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<Boolean> c) {
		for (Boolean f : c) {
			add(f);
		}
		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeBooleanList#addAll(int, java.util.Collection)
	 */
	@Override
	public boolean addAll(int index, Collection<Boolean> c) {
		if (index < addPointer) {
			for (boolean value : c) {
				add(index++, value);
			}
		} else {
			throw new IndexOutOfBoundsException(""+index);
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeBooleanList#addAll(us.rothmichaels.lists.IPrimativeBooleanList)
	 */
	@Override
	public boolean addAll(IPrimativeBooleanList l) {
		boolean[] a = l.toArray();
		for (boolean f : a) {
			add(f);
		}
		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeBooleanList#addAll(int, us.rothmichaels.lists.IPrimativeBooleanList)
	 */
	@Override
	public boolean addAll(int index, IPrimativeBooleanList l) {
		if (index < addPointer) {
			boolean[] a = l.toArray();
			for (boolean value : a) {
				add(index++, value);
			}
		} else {
			throw new IndexOutOfBoundsException(""+index);
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeBooleanList#contains(boolean)
	 */
	@Override
	public boolean contains(boolean value) {
		boolean r = false;

		for (boolean val : data) {
			r |= (val == value);
		}

		return r;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeBooleanList#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<Boolean> c) {
		for (boolean value : c) {
			boolean b = false;
			for (boolean val : data) {
				b |= (val == value);
			}
			if (!b) {
				return false;
			}
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeBooleanList#containsAll(us.rothmichaels.lists.IPrimativeBooleanList)
	 */
	@Override
	public boolean containsAll(IPrimativeBooleanList c) {
		boolean[] a = c.toArray();

		for (boolean value : a) {
			boolean b = false;
			for (boolean val : data) {
				b |= (val == value);
			}
			if (!b) {
				return false;
			}
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeBooleanList#get(int)
	 */
	@Override
	public boolean get(int index) {
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
	 * @see us.rothmichaels.lists.IPrimativeBooleanList#indexOf(boolean)
	 * @throws IllegalArgumentException if input does not exist in list
	 */
	@Override
	public int indexOf(boolean i) throws IllegalArgumentException {
		for (int j = 0; j < data.length; ++j) {
			if (data[j] == i) {
				return j;
			}
		}

		throw new IllegalArgumentException();
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeBooleanList#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return (addPointer == 0);
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeBooleanList#lastIndexOf(boolean)
	 * @throws IllegalArgumentException if input does not exist in list
	 */
	@Override
	public int lastIndexOf(boolean value) throws IllegalArgumentException {
		for (int j = data.length-1; j >= 0 ; --j) {
			if (data[j] == value) {
				return j;
			}
		}

		throw new IllegalArgumentException();
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeBooleanList#removeValue(boolean)
	 */
	@Override
	public boolean removeValue(boolean value) {
		for (int i = 0; i < addPointer; ++i) {
			if (value == data[i]) {
				return remove(i);
			}
		}

		return false;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeBooleanList#remove(int)
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
	 * @see us.rothmichaels.lists.IPrimativeBooleanList#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<Boolean> c) {
		boolean r = false;
		for (boolean value : c) {
			r |= removeValue(value);
		}

		return r;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeBooleanList#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<Boolean> c) {
		for (int i = 0; i < addPointer; ++i) {
			if (!c.contains(data[i])) {
				remove(i);
			}
		}

		return true;
	}

	/**
	 * @see us.rothmichaels.lists.IPrimativeBooleanList#set(int, boolean)
	 */
	@Override
	public boolean set(int index, boolean element) {
		if (index < addPointer) {
			try {
				boolean old = data[index];
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
	 * @see us.rothmichaels.lists.IPrimativeBooleanList#subList(int, int)
	 */
	@Override
	public IPrimativeBooleanList subList(int fromIndex, int toIndex) {
		if (toIndex > addPointer || toIndex < fromIndex) {
			throw new IndexOutOfBoundsException();
		}
		final int newSize = toIndex - fromIndex; 
		final BooleanArrayList out = new BooleanArrayList();

		try {
			System.arraycopy(data, fromIndex, out.data, 0, newSize);
			out.addPointer = newSize;
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException(String.format("[%d %d)",fromIndex,toIndex));
		}

		return out;
	}

}
