package ch.sulco.yal.dsp.audio.onboard;

import java.util.Set;

import ch.sulco.yal.dsp.audio.Processor;

public class OnboardProcessor implements Processor {

	private final Player player;
	private final Recorder recorder;
	private final LoopStore loopStore;

	public OnboardProcessor(Player player, Recorder recorder, LoopStore loopStore) {
		this.player = player;
		this.recorder = recorder;
		this.loopStore = loopStore;
	}
	
	public Player getPlayer(){
		return player;
	}

	@Override
	public Set<Integer> getSampleIds() {
		return this.loopStore.getSampleIds();
	}

	@Override
	public Set<Integer> getChannelIds() {
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
		return this.loopStore.addSample(data);
	}

}
