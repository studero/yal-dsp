package ch.sulco.yal.dsp;

import ch.sulco.yal.dsp.audio.onboard.LoopStore;
import ch.sulco.yal.dsp.audio.onboard.OnboardProcessor;
import ch.sulco.yal.dsp.audio.onboard.Player;
import ch.sulco.yal.dsp.audio.onboard.Recorder;
import ch.sulco.yal.dsp.cmd.SocketCommandReceiver;

public class Main {
	public static void main(String[] args) {
		AppConfig appConfig = new AppConfig();
		new Application(appConfig, new SocketCommandReceiver(appConfig), new OnboardProcessor(new Player(),
				new Recorder(appConfig), new LoopStore()));
	}
}
