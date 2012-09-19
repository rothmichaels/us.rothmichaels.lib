/*
 * WeakHashSet.java
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
 * Sep 4, 2012 
 */
package us.rothmichaels.sets;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

/**
 * A Set where items are held by weak reference.
 *
 * @author Roth Michaels (<i><a href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 *
 */
public class WeakHashSet<E> implements Set<E> {

	private final WeakHashMap<E,WeakReference<E>> itsMap;
	
	public WeakHashSet() {
		itsMap = new WeakHashMap<E, WeakReference<E>>();
	}
	
	/**
	 * @see java.util.Set#add(java.lang.Object)
	 */
	@Override
	public boolean add(E arg0) {
		if (itsMap.containsKey(arg0)) {
			return false;
		} else {
			itsMap.put(arg0, new WeakReference<E>(arg0));
			return true;
		}
	}

	/**
	 * @see java.util.Set#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		boolean r = false;
		for (E item : arg0) {
			if (!itsMap.containsKey(item)) {
				itsMap.put(item, new WeakReference<E>(item));
				r = true;
			}
		}
		return r;
	}

	/**
	 * @see java.util.Set#clear()
	 */
	@Override
	public void clear() {
		itsMap.clear();
	}

	/**
	 * @see java.util.Set#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object arg0) {
		return itsMap.containsKey(arg0);
	}

	/**
	 * @see java.util.Set#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<?> arg0) {
		for (Object item : arg0) {
			if (!itsMap.containsKey(arg0)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @see java.util.Set#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return itsMap.isEmpty();
	}

	/**
	 * @see java.util.Set#iterator()
	 */
	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			private final Iterator<E> iterator = itsMap.keySet().iterator();
			
			private E lastKey = null;
			
			@Override
			public boolean hasNext() {
				return iterator.hasNext();
			}

			@Override
			public E next() {
				lastKey = iterator.next();;
				return itsMap.get(lastKey).get();
			}

			@Override
			public void remove() {
				if (lastKey != null) {
					itsMap.remove(lastKey);
					lastKey = null;
				}
			}
		};
	}

	/**
	 * @see java.util.Set#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object arg0) {
		return itsMap.remove(arg0) != null;
	}

	/**
	 * @see java.util.Set#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<?> arg0) {
		boolean r = false;
		
		for (Object item : arg0) {
			r = remove(arg0);
		}
		
		return r;
	}

	/**
	 * @see java.util.Set#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<?> arg0) {
		boolean r = false;
		for (E item : itsMap.keySet()) {
			if (!arg0.contains(item)) {
				r = remove(item);
			}
		}
		
		return r;
	}

	/**
	 * @see java.util.Set#size()
	 */
	@Override
	public int size() {
		return itsMap.size();
	}

	/**
	 * @see java.util.Set#toArray()
	 */
	@Override
	public Object[] toArray() {
		Object[] out = new Object[size()];
		int i = 0;
		for (E item : this) {
			out[i++] = item;
		}
		
		return out;
	}


	@Override
	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] arg0) {
		if (arg0.length < itsMap.size()) {
			arg0 = (T[]) new Object[itsMap.size()];	
		} else {
			Arrays.fill(arg0, null);
		}
		
		int i = 0;
		for (E item : this) {
			arg0[i++] = (T) item;
		}
		
		return arg0;
	}

}
