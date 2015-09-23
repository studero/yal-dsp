package ch.sulco.yal.dsp.audio;

/**
 * provides method for audio processing.
 */
public interface Processor {	
	/**
	 * @return an array of sample ids.
	 */
	int[] getSampleIds();

	/**
	 * @return an array of channel ids.
	 */
	int[] getChannelIds();

	/**
	 * @param sampleId the id of the sample.
	 * @return sample data as byte array for provided sample id.
	 */
	byte[] getData(int sampleId);

	/**
	 * start playing current audio setup.
	 */
	void play();

	/** 
	 * pause current audio setup.
	 */
	void pause();

	/** 
	 * create loop.
	 */
	void loop();

	/**
	 * set provided channel id to provided recording state.
	 * 
	 * @param channelId the id of the channel.
	 * @param recording true if channel should be recording.
	 */
	void setChannelRecording(int channelId, boolean recording);

	/**
	 * set provided sample id to provided mute state.
	 * @param sampleId the id of the sample.
	 * @param mute true if the sample should be muted.
	 */
	void setSampleMute(int sampleId, boolean mute);

	/**
	 * set provided sample id to provided volume.
	 * @param sampleId the id of the sample.
	 * @param volume the volume the sample should be set to.
	 */
	void setSampleVolume(int sampleId, int volume);

	/**
	 * remove sample identified by provided sample id.
	 * @param sampleId the id of the sample.
	 */
	void removeSample(int sampleId);
}
