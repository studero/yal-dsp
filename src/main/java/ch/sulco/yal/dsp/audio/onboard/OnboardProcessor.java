package ch.sulco.yal.dsp.audio.onboard;

import java.util.Set;
import java.util.logging.Logger;

import ch.sulco.yal.dsp.audio.Processor;
import ch.sulco.yal.dsp.audio.RecordingState;
import ch.sulco.yal.dsp.dm.Sample;

import com.google.common.collect.Sets;

public class OnboardProcessor implements Processor {

	private final static Logger log = Logger.getLogger(OnboardProcessor.class.getName());

	private final Player player;
	private final Recorder recorder;
	private final LoopStore loopStore;

	public OnboardProcessor(Player player, Recorder recorder, LoopStore loopStore) {
		this.player = player;
		this.recorder = recorder;
		this.loopStore = loopStore;
	}

	public LoopStore getLoopStore() {
		return this.loopStore;
	}

	public Player getPlayer() {
		return this.player;
	}

	@Override
	public Set<Integer> getSampleIds() {
		return this.loopStore.getSampleIds();
	}

	@Override
	public Set<Integer> getChannelIds() {
		return Sets.newHashSet(0);
	}

	@Override
	public byte[] getData(int sampleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void play() {
		log.info("Play");
		this.recorder.startRecord();
		this.recorder.loopStarted();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void loop() {
		log.info("Loop");
		this.recorder.stopRecord();

		log.info("Start Sample");
		this.player.startSample(this.loopStore.getSample(0));
	}

	@Override
	public void setChannelRecording(int channelId, boolean recording) {
		if (recording) {
			this.recorder.startRecord();
		} else {
			this.recorder.stopRecord();
		}
	}

	@Override
	public void setSampleMute(int sampleId, boolean mute) {
		Sample sample = this.loopStore.getSample(sampleId);
		if (sample != null) {
			if (mute) {
				this.player.startSample(sample);
			} else {
				this.player.startSample(sample);
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
