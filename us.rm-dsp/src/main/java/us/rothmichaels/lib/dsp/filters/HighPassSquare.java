/*
 * SimpleHighPass.java
 *
 * Feb 26, 2012 
 */
package us.rothmichaels.lib.dsp.filters;

import us.rothmichaels.lib.dsp.ABufferProcess;

/**
 * Simple high pass filter originally written for rm.slice~
 *
 * @author Roth Michaels (<i><a href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 *
 */
public class HighPassSquare extends ABufferProcess implements IFilter {
	
	final float coefA;
	final float coefB;
	
	/**
	 * @param coefA
	 * @param coefB
	 */
	public HighPassSquare(float coefA, float coefB) {
		this.coefA = coefA;
		this.coefB = coefB;
	}

	/**
	 * @see us.rothmichaels.lib.dsp.IBufferProcess#processInPlace(float[])
	 */
	@Override
	public void processInPlace(float[] inputOutput) {
		float feedback = 0.0f;
		
		for (int i = 0, end = inputOutput.length; i < end; ++i) {
			feedback = (coefA * inputOutput[i] - coefB * feedback);
			inputOutput[i] = feedback * feedback;
		}
	}
}
