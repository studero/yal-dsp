package ch.sulco.yal.dsp.audio.onboard;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Set;

import org.junit.Test;
import org.mockito.internal.util.collections.Sets;

import ch.sulco.yal.dsp.audio.onboard.LoopStore;
import ch.sulco.yal.dsp.audio.onboard.OnboardProcessor;

public class OnboardProcessorTest {
	@Test
	public void testPutData() {
		LoopStore loopStore = mock(LoopStore.class);
		when(loopStore.addSample("test".getBytes())).thenReturn(10101);
		OnboardProcessor onboardProcessor = new OnboardProcessor(null, null, loopStore);

		int sample1 = onboardProcessor.putData("test".getBytes());

		verify(loopStore).addSample("test".getBytes());
		assertThat(sample1, is(10101));
	}

	@Test
	public void testGetSampleIds() {
		LoopStore loopStore = mock(LoopStore.class);
		when(loopStore.getSampleIds()).thenReturn(Sets.newSet(10101, 20202));
		OnboardProcessor onboardProcessor = new OnboardProcessor(null, null, loopStore);

		Set<Integer> sampleIds = onboardProcessor.getSampleIds();

		assertThat(sampleIds, containsInAnyOrder(10101, 20202));
	}
}
