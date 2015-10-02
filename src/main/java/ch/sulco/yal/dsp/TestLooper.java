package ch.sulco.yal.dsp;

import ch.sulco.yal.dsp.audio.onboard.AudioSystemProvider;
import ch.sulco.yal.dsp.audio.onboard.LoopStore;
import ch.sulco.yal.dsp.audio.onboard.OnboardProcessor;
import ch.sulco.yal.dsp.audio.onboard.Player;
import ch.sulco.yal.dsp.audio.onboard.Recorder;
import ch.sulco.yal.dsp.cmd.SocketCommandReceiver;

public class TestLooper {
	public static void main(String[] args) throws Exception {
		AppConfig appConfig = new AppConfig();
		Player player = new Player();
		LoopStore loopStore = new LoopStore(appConfig, new AudioSystemProvider());
		Application application = new Application(appConfig, new SocketCommandReceiver(appConfig), new OnboardProcessor(player, loopStore,
				new Recorder(appConfig, player, loopStore)));
		application.getAudioProcessor().play();
		Thread.sleep(2000);
		application.getAudioProcessor().loop();
		
		System.out.println(application.getAudioProcessor().getSampleVolume(0));
		System.out.println(application.getAudioProcessor().isSampleMute(0));
	}
}
