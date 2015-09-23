package ch.sulco.yal.dsp.audio;

public interface Processor {
	int[] getSampleIds();
	int[] getChannelIds();
	byte[] getData(int sampleId);
	void play();
	void pause();
	void loop();
	void setChannelRecording(int channelId, boolean recording);
	void setSampleMute(int sampleId, boolean mute);
	void setSampleVolume(int sampleId, int volume);
	void removeSample(int sampleId);
}
