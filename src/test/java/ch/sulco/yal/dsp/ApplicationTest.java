package ch.sulco.yal.dsp;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import ch.sulco.yal.dsp.audio.Processor;
import ch.sulco.yal.dsp.cmd.CommandReceiver;

public class ApplicationTest {
	@Test
	public void shouldInitializeApplication() {
		AppConfig appConfig = new AppConfig();
		CommandReceiver commandReceiver = mock(CommandReceiver.class);
		Processor processor = mock(Processor.class);
		Application application = new Application(appConfig, commandReceiver, processor);

		org.junit.Assert.assertThat(application, is(not(nullValue())));
	}
}