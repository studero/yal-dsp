package ch.sulco.yal.dsp.audio;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

import ch.sulco.yal.dsp.audio.onboard.LoopStore;

public class OnboardProcessorTest {
	@Test
	public void testPutData() {
		LoopStore loopStore = mock(LoopStore.class);
		when(loopStore.addSample("test".getBytes())).thenReturn(10101);
		OnboardProcessor onboardProcessor = new OnboardProcessor(loopStore);

		int sample1 = onboardProcessor.putData("test".getBytes());

		verify(loopStore).addSample("test".getBytes());
		assertThat(sample1, is(10101));
	}
}