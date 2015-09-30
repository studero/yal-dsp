package ch.sulco.yal.dsp.audio.onboard;

import java.util.LinkedList;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

import ch.sulco.yal.dsp.dm.Sample;

public class Player {
	private LineListener lineListener;
	private LinkedList<LoopListener> loopListeners = new LinkedList<LoopListener>();
	private LinkedList<Clip> playingClips = new LinkedList<Clip>();
	
	public void startSample(Sample sample){
		Clip clip = sample.getClip();
		if(clip != null){
			if(!playingClips.contains(clip)){
				if(playingClips.isEmpty()){
					clip.loop(Clip.LOOP_CONTINUOUSLY);
				}else{
					checkLine();
				}
				playingClips.add(clip);
			}
		}
	}
	
	private void checkLine(){
		if(lineListener != null){
			lineListener = new LineListener() {
				public void update(LineEvent event) {
					playingClips.getFirst().removeLineListener(this);
					lineListener = null;
					for(LoopListener loopListener : loopListeners){
						loopListener.loopStarted();
					}
					startAllSamples();
				}
			};
			playingClips.getFirst().addLineListener(lineListener);
			playingClips.getFirst().loop(0);
		}
	}
	
	private void startAllSamples(){
		for(Clip clip : playingClips){
			clip.setFramePosition(0);
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

	public void addLoopListerner(LoopListener loopListerer) {
		if(playingClips.isEmpty()){
			loopListerer.loopStarted();
		}else{
			checkLine();
		}
		loopListeners.add(loopListerer);
	}

	public void removeLoopListerner(LoopListener loopListerer) {
		loopListeners.remove(loopListerer);
	}
}
