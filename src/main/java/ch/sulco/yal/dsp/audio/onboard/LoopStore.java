package ch.sulco.yal.dsp.audio.onboard;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import ch.sulco.yal.dsp.dm.Sample;

public class LoopStore {
	private final Map<Integer, Sample> samples = new HashMap<>();

	public int addSample(byte[] data) {
		int newId = this.samples.size() == 0 ? 0 : Collections.max(this.samples.keySet()) + 1;
		Sample sample = new Sample();
		sample.setId(newId);
		sample.setData(data);
		this.samples.put(newId, sample);
		return newId;
	}

	public Collection<Sample> getSamples() {
		return this.samples.values();
	}

	public Set<Integer> getSampleIds() {
		return this.samples.keySet();
	}
}
