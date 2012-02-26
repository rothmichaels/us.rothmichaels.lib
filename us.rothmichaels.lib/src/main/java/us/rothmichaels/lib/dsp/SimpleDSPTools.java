/*
 * Tools.java
 *
 * Feb 25, 2012 
 */
package us.rothmichaels.lib.dsp;

/**
 * Basic signal processing tools.
 *
 * @author Roth Michaels (<i><a href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 *
 */
public final class SimpleDSPTools {
	
	/**
	 * Normalize the values in an array (to 1.0) performing calculation in place.
	 * 
	 * @param inputOutput	Array to normalize
	 */
	public static void normalizeInPlace(float[] inputOutput) {
		final int end = inputOutput.length;
		float maximum = 0.0f;
		// find maximum
		for (int i = 0; i < end; ++i) {
			if (inputOutput[i] > maximum) {
				maximum = inputOutput[i];
			}
		}
		// normalize
		final float normalizeFactor = 1.0f / maximum;
		for (int i = 0; i < end; ++i) {
			inputOutput[i] *= normalizeFactor;
		}
	}
	
	/**
	 * Normalize the values in an array (to 1.0).
	 * 
	 * @param input		Array to normalize
	 * 
	 * @return			new array containing normalized values
	 */
	public static float[] normalize(float[] input) {
		final float[] output = new float[input.length];
		 System.arraycopy(input, 0, output, 0, input.length);
		 
		 normalizeInPlace(output);
		 
		 return output;
	}
}
