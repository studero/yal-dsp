package ch.sulco.yal.dsp;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class Player {
	private int length;
	private HashMap<Integer, Clip> clips = new HashMap<Integer, Clip>();
	private LinkedList<Clip> playingClips = new LinkedList<Clip>();

	public int addLoop(String fileName) {
		try {
			File file = new File(fileName);
			AudioInputStream ais = AudioSystem.getAudioInputStream(file);
			byte[] data = new byte[(int)file.length()];
			ais.read(data);
			return addLoop(ais.getFormat(), data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int addLoop(AudioFormat format, byte[] data){
		try {
			if(clips.isEmpty()){
				length = data.length;
			}else if(data.length < length){
				byte[] longerData = Arrays.copyOf(data, length);
				data = longerData;
			}
			Clip clip = AudioSystem.getClip();
			clip.open(format, data, 0, length);
			int id = clips.size()+1;
			clips.put(id, clip);
			return id;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void startLoop(int id){
		Clip clip = clips.get(id);
		if(clip != null){
			if(!playingClips.contains(clip)){
				if(playingClips.isEmpty()){
					clip.loop(Clip.LOOP_CONTINUOUSLY);
				}else{
					playingClips.getFirst().addLineListener(new LineListener() {
						public void update(LineEvent event) {
							playingClips.getFirst().removeLineListener(this);
							startAllLoops();
						}
					});
					playingClips.getFirst().loop(0);
				}
				playingClips.add(clip);
			}
		}
	}
	
	private void startAllLoops(){
		for(Clip clip : playingClips){
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}
	
	public void stopLoop(int id){
		Clip clip = clips.get(id);
		if(clip != null){
			clip.loop(0);
			playingClips.remove(clip);
		}
	}

	public void shutDown() {
		LinkedList<Integer> ids = new LinkedList<Integer>(clips.keySet());
		for(int id : ids){
			removeLoop(id);
		}
	}
	
	public void removeLoop(int id){
		final Clip clip = clips.remove(id);
		if(clip != null){
			new Thread(){
				public void run() {
					clip.loop(0);
					playingClips.remove(clip);
					clip.drain();
					clip.close();
				}
				
			}.start();
		}
	}

}
