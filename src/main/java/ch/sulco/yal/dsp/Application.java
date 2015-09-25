/**
 * Copyright (C) 2015 studero.
 */
package ch.sulco.yal.dsp;

import java.util.logging.Logger;

import ch.sulco.yal.dsp.audio.Processor;
import ch.sulco.yal.dsp.cmd.Command;
import ch.sulco.yal.dsp.cmd.CommandReceiver;
import ch.sulco.yal.dsp.cmd.CommandReceiver.CommandListener;

public class Application implements CommandListener {

	private final static Logger log = Logger.getLogger(Application.class.getName());

	private final AppConfig appConfig;
	private final CommandReceiver commandReceiver;
	private final Processor audioProcessor;

	public Application(AppConfig appConfig, CommandReceiver commandReceiver, Processor processor) {
		log.info("Initialize Application");
		this.appConfig = appConfig;
		this.commandReceiver = commandReceiver;
		this.commandReceiver.setCommandListener(this);
		this.audioProcessor = processor;
	}

	@Override
	public void onCommand(Command command) {
		log.info("Command received [" + command + "]");
	}
}
