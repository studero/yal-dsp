package ch.sulco.yal.dsp.audio.onboard;

import java.io.IOException;
import java.util.logging.Logger;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

import com.sun.corba.se.impl.ior.ByteBuffer;

import ch.sulco.yal.dsp.AppConfig;
import ch.sulco.yal.dsp.audio.RecordingState;

public class Recorder implements LoopListener{
	private final static Logger log = Logger.getLogger(Recorder.class.getName());

	private final AppConfig appConfig;
	private Player player;
	private LoopStore loopStore;
	private RecordingState recordingState = RecordingState.STOPPED;
	private byte[] recordedSample;
	private ByteBuffer recordingSample;

	public Recorder(AppConfig appConfig, Player player, LoopStore loopStore) {
		this.appConfig = appConfig;
		this.player = player;
		this.loopStore = loopStore;
	}
	
	public RecordingState getRecordingState(){
		return recordingState;
	}
	
	public void startRecord(){
		if(recordingState == RecordingState.STOPPED){
			recordingState = RecordingState.WAITING;
			player.addLoopListerner(this);
		}
	}
	
	public void stopRecord(){
		recordingState = RecordingState.STOPPED;
		player.removeLoopListerner(this);
		if(recordedSample != null){
			loopStore.addSample(recordedSample);
			recordedSample = null;
			recordingSample = null;
		}
	}
	
	public void loopStarted() {
		if(recordingState == RecordingState.WAITING){
			recordingState = RecordingState.RECORDING;
			recordedSample = null;
			recordingSample = new ByteBuffer();
			Thread recordThread = new Thread(){
				public void run() {
					try {
						DataLine.Info info = new DataLine.Info(TargetDataLine.class, appConfig.getAudioFormat());
//						checkState(AudioSystem.isLineSupported(info), "Line not supported");
						TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);
						line.open(appConfig.getAudioFormat());
						line.start();
						log.info("Start capturing...");
						AudioInputStream ais = new AudioInputStream(line);
						log.info("Start recording...");
						while (recordingState == RecordingState.RECORDING) {
							recordingSample.append(ais.read());
						}

					} catch (LineUnavailableException ex) {
						ex.printStackTrace();
					} catch (IOException ioe) {
						ioe.printStackTrace();
					}
				}
			};
			recordThread.start();
		}else if(recordingState == RecordingState.RECORDING){
			recordedSample = recordingSample.toArray();
			recordingSample = new ByteBuffer();
		}else{
			player.removeLoopListerner(this);
		}
	}
}
