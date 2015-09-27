package ch.sulco.yal.dsp.audio.onboard;

import static com.google.common.base.Preconditions.checkState;

import java.io.IOException;
import java.util.logging.Logger;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

import ch.sulco.yal.dsp.AppConfig;

public class Recorder extends Thread {
	private final static Logger log = Logger.getLogger(Recorder.class.getName());

	private final AppConfig appConfig;
	private TargetDataLine line;
	private ChannelListener channelListener;

	public Recorder(AppConfig appConfig) {
		this.appConfig = appConfig;
	}

	@Override
	public void run() {
		try {
			DataLine.Info info = new DataLine.Info(TargetDataLine.class, this.appConfig.getAudioFormat());
			checkState(AudioSystem.isLineSupported(info), "Line not supported");
			this.line = (TargetDataLine) AudioSystem.getLine(info);
			this.line.open(this.appConfig.getAudioFormat());
			this.line.start();
			log.info("Start capturing...");
			AudioInputStream ais = new AudioInputStream(this.line);
			log.info("Start recording...");

			while (true) {
				byte[] buffer = new byte[1024];
				ais.read(buffer);
				this.channelListener.onData(buffer);
			}

		} catch (LineUnavailableException ex) {
			ex.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void setChannelListener(ChannelListener channelListener) {
		this.channelListener = channelListener;
	}

	public interface ChannelListener {
		void onData(byte[] data);
	}
}
