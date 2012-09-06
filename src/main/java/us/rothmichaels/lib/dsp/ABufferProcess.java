/*
 * ABufferProcess.java
 *
 * Feb 26, 2012 
 */
package us.rothmichaels.lib.dsp;

/**
 *
 *
 * @author Roth Michaels (<i><a href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 *
 */
public abstract class ABufferProcess implements IBufferProcess {

	/**
	 * @see us.rothmichaels.lib.dsp.IBufferProcess#process(float[])
	 */
	@Override
	public float[] process(float[] input) {
		final float[] output = new float[input.length];
		System.arraycopy(input, 0, output, 0, input.length);

		processInPlace(output);

		return output;
	}

}
