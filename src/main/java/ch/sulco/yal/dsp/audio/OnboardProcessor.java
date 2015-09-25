package ch.sulco.yal.dsp.audio;

import ch.sulco.yal.dsp.audio.onboard.LoopStore;
import ch.sulco.yal.dsp.audio.onboard.Player;
import ch.sulco.yal.dsp.audio.onboard.Recorder;

public class OnboardProcessor implements Processor {

	private final Player player;
	private final Recorder recorder;
	private final LoopStore loopStore;

	public OnboardProcessor() {
		this.player = new Player();
		this.recorder = new Recorder();
		this.loopStore = new LoopStore();
	}

	@Override
	public int[] getSampleIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getChannelIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getData(int sampleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void loop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setChannelRecording(int channelId, boolean recording) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSampleMute(int sampleId, boolean mute) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSampleVolume(int sampleId, int volume) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeSample(int sampleId) {
		// TODO Auto-generated method stub

	}

	@Override
	public int putData(byte[] data) {
		// TODO Auto-generated method stub
		return 0;
	}

}
