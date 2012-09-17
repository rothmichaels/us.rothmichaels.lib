/*
 * IFilter.java
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
public interface IBufferProcess {
	/**
	 * 
	 * @param inputOutput
	 */
	void processInPlace(final float[] inputOutput);
	
	/**
	 * 
	 * @param input
	 * @return
	 */
	float[] process(final float[] input);
}
