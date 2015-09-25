package ch.sulco.yal.dsp.cmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import ch.sulco.yal.dsp.AppConfig;

public class SocketCommandReceiver implements CommandReceiver {

	private final AppConfig appConfig;

	private CommandListener commandListener;
	private ServerSocket socket;

	public SocketCommandReceiver(AppConfig appConfig) {
		this.appConfig = appConfig;
		try {
			this.socket = new ServerSocket(this.appConfig.getCommandSocketPort());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.start();
	}

	@Override
	public void setCommandListener(CommandListener commandListener) {
		this.commandListener = commandListener;
	}

	private void start() {
		try {
			Socket clientSocket = this.socket.accept();
			BufferedReader inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String commandInput;
			while ((commandInput = inputReader.readLine()) != null) {
				// TODO: handle input
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}