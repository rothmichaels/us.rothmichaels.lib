/*
 * SimpleEnvelopeAnalyzer.java
 *
 * Feb 25, 2012 
 */
package us.rothmichaels.lib.dsp.filters;

import us.rothmichaels.lib.dsp.ABufferProcess;
import us.rothmichaels.lib.dsp.IBufferProcess;

/**
 * For analyzing arrays and generating an envelope
 *
 * @author Roth Michaels (<i><a href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 *
 */
public class SimpleEnvelopeAnalyzer extends ABufferProcess {

	final float filterCoef;
	
	public SimpleEnvelopeAnalyzer(float filterCoef) {
		this.filterCoef = filterCoef;
	}
	
	@Override
	public void processInPlace(final float[] inputOutput) {
		float feedback = 0f;
		for (int i = 0, end = inputOutput.length; i < end; ++ i) {
			feedback = (float) Math.sqrt((inputOutput[i] * inputOutput[i]) 
					* (1 - filterCoef) + (filterCoef * feedback)); 
			inputOutput[i] = feedback;
		}
	}
}
