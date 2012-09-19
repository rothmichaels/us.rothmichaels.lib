/*
 * EasyEnumMap.java
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
 * Sep 16, 2012 
 */
package us.rothmichaels.maps;

import java.util.EnumMap;
import java.util.Map;

/**
 * An {@link java.util.EnumMap} that has a convenience method
 * {@link EasyEnumMap#p(Enum, Object)} to help creating maps.
 * 
 * @author Roth Michaels (<i><a
 *         href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
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
	 * Puts a value in the map and returns itself to simplify map creation.
	 * 
	 * @param key
	 *            key to set
	 * @param value
	 *            the value
	 * 
	 * @return itself
	 */
	public EasyEnumMap<K, V> p(K key, V value) {
		put(key, value);

		return this;
	}
}
