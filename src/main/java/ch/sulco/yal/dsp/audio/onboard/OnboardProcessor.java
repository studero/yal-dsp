package ch.sulco.yal.dsp.audio.onboard;

import java.util.Set;

import ch.sulco.yal.dsp.audio.Processor;
import ch.sulco.yal.dsp.audio.RecordingState;
import ch.sulco.yal.dsp.dm.Sample;

public class OnboardProcessor implements Processor {

	private final Player player;
	private final Recorder recorder;
	private final LoopStore loopStore;

	public OnboardProcessor(Player player, Recorder recorder, LoopStore loopStore) {
		this.player = player;
		this.recorder = recorder;
		this.loopStore = loopStore;
	}
	
	public LoopStore getLoopStore(){
		return loopStore;
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
		if(recording){
			recorder.startRecord();
		}else{
			recorder.stopRecord();
		}
	}

	@Override
	public void setSampleMute(int sampleId, boolean mute) {
		Sample sample = loopStore.getSample(sampleId);
		if(sample != null){
			if(mute){
				player.startSample(sample);
			}else{
				player.startSample(sample);
			}
		}

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

	@Override
	public RecordingState getChannelRecordingState(int channelId) {
		return this.recorder.getRecordingState();
	}

}
