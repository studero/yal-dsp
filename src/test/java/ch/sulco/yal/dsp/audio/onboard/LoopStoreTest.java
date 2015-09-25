package ch.sulco.yal.dsp.audio.onboard;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.Set;

import org.junit.Test;

import ch.sulco.yal.dsp.dm.Sample;

public class LoopStoreTest {
	@Test
	public void testAddSample() {
		LoopStore loopStore = new LoopStore();

		int id1 = loopStore.addSample("test".getBytes());
		int id2 = loopStore.addSample("test".getBytes());

		assertThat(id1, is(0));
		assertThat(id2, is(1));
	}

	@Test
	public void testGetSamples() {
		LoopStore loopStore = new LoopStore();
		loopStore.addSample("test".getBytes());
		loopStore.addSample("test".getBytes());

		Collection<Sample> samples = loopStore.getSamples();

		assertThat(samples, hasSize(2));
	}

	@Test
	public void testGetSampleIds() {
		LoopStore loopStore = new LoopStore();
		int id1 = loopStore.addSample("test".getBytes());
		int id2 = loopStore.addSample("test".getBytes());

		Set<Integer> sampleIds = loopStore.getSampleIds();

		assertThat(sampleIds, containsInAnyOrder(id1, id2));
	}

}
