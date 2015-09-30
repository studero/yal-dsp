package ch.sulco.yal.dsp.audio.onboard;

import java.util.HashMap;
import java.util.LinkedList;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

import ch.sulco.yal.dsp.dm.Sample;

public class Player {
	private LinkedList<Clip> playingClips = new LinkedList<Clip>();
	
	public void startSample(Sample sample){
		Clip clip = sample.getClip();
		if(clip != null){
			if(!playingClips.contains(clip)){
				if(playingClips.isEmpty()){
					clip.loop(Clip.LOOP_CONTINUOUSLY);
				}else{
					playingClips.getFirst().addLineListener(new LineListener() {
						public void update(LineEvent event) {
							playingClips.getFirst().removeLineListener(this);
							startAllSamples();
						}
					});
					playingClips.getFirst().loop(0);
				}
				playingClips.add(clip);
			}
		}
	}
	
	private void startAllSamples(){
		for(Clip clip : playingClips){
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}
	
	public void stopSample(Sample sample){
		Clip clip = sample.getClip();
		if(clip != null){
			clip.loop(0);
			playingClips.remove(clip);
		}
	}
}
