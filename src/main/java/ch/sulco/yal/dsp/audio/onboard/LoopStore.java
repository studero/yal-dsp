package ch.sulco.yal.dsp.audio.onboard;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;

import ch.sulco.yal.dsp.AppConfig;
import ch.sulco.yal.dsp.dm.Sample;

public class LoopStore {
	private final AudioSystemProvider audioSystemProvider;
	private final AppConfig appConfig;
	private int sampleLength;
	private Map<Integer, Sample> samples = new HashMap<Integer, Sample>();
	
	public LoopStore(AppConfig appConfig, AudioSystemProvider audioSystemProvider){
		this.appConfig = appConfig;
		this.audioSystemProvider = audioSystemProvider;
	}
	
	public int addSample(String fileName) {
		try {
			File file = new File(fileName);
			AudioInputStream ais = audioSystemProvider.getAudioInputStream(file);
			byte[] data = new byte[(int)file.length()];
			ais.read(data);
			return addSample(ais.getFormat(), data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int addSample(byte[] data){
		return addSample(appConfig.getAudioFormat(), data);
	}

	public int addSample(AudioFormat format, byte[] data) {
		int newId = -1;
		try {
			if(samples.isEmpty()){
				sampleLength = data.length;
			}else if(data.length < sampleLength){
				byte[] longerData = Arrays.copyOf(data, sampleLength);
				data = longerData;
			}
			Clip clip = audioSystemProvider.getClip(format, data, 0, sampleLength);
			newId = this.samples.size() == 0 ? 0 : Collections.max(this.samples.keySet()) + 1;
			Sample sample = new Sample(newId, clip);
			this.samples.put(newId, sample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newId;
	}
	
	public void removeSample(int id){
		Sample sample = samples.remove(id);
		final Clip clip = sample.getClip();
		if(clip != null){
			new Thread(){
				public void run() {
					clip.loop(0);
					clip.drain();
					clip.close();
				}
				
			}.start();
		}
	}

	public Collection<Sample> getSamples() {
		return this.samples.values();
	}

	public Set<Integer> getSampleIds() {
		return this.samples.keySet();
	}
	
	public Sample getSample(int id){
		return this.samples.get(id);
	}
}
