package ch.sulco.yal.dsp;

import ch.sulco.yal.dsp.audio.OnboardProcessor;
import ch.sulco.yal.dsp.audio.onboard.LoopStore;
import ch.sulco.yal.dsp.cmd.SocketCommandReceiver;

public class Main {
	public static void main(String[] args) {
		AppConfig appConfig = new AppConfig();
		new Application(appConfig, new SocketCommandReceiver(appConfig), new OnboardProcessor(new LoopStore()));
	}
}
