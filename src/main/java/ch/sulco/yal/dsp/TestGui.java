package ch.sulco.yal.dsp;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ch.sulco.yal.dsp.audio.onboard.AudioSystemProvider;
import ch.sulco.yal.dsp.audio.onboard.LoopStore;
import ch.sulco.yal.dsp.audio.onboard.OnboardProcessor;
import ch.sulco.yal.dsp.audio.onboard.Player;
import ch.sulco.yal.dsp.audio.onboard.Recorder;

public class TestGui extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private static JFrame frame;
	private OnboardProcessor controller;
	JComboBox<String> fileSelector;
	JPanel loopsPanel;

	public TestGui(){
		AppConfig appConfig = new AppConfig();
		controller = new OnboardProcessor(new Player(), new Recorder(appConfig), new LoopStore(appConfig, new AudioSystemProvider()));

		setLayout ( new GridBagLayout ());
		GridBagConstraints constraints = new GridBagConstraints ();
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		
		fileSelector = new JComboBox<String>(new File(TestGui.class.getClassLoader().getResource("sounds").getFile()).list());
		add(fileSelector, constraints);
		
		JButton add = new JButton("add");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loopsPanel.add(new LoopPanel((String)fileSelector.getSelectedItem()));
				loopsPanel.updateUI();
			}
		});
		constraints.gridx = 2;
		constraints.gridwidth = 1;
		add(add, constraints);

		loopsPanel = new JPanel();
		loopsPanel.setPreferredSize(new Dimension(500, 400));
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 3;
		add(loopsPanel, constraints);
	}
	
	public static void main(String[] args) {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		TestGui contentPane = new TestGui();
		
		contentPane.setOpaque(true);
		
		frame.setTitle("Basic GUI for Looper");
		frame.setContentPane(contentPane);
		
		frame.setSize(600,500);
		frame.setVisible(true);
	}
	
	private class LoopPanel extends JPanel{
		private static final long serialVersionUID = 1L;

		public LoopPanel(String file){
			file = "sounds/"+file;
			setLayout ( new GridBagLayout ());
			GridBagConstraints constraints = new GridBagConstraints ();
			constraints.weightx = 1.0;
			constraints.weighty = 1.0;
			constraints.gridy = 0;
			
			final int id = controller.getLoopStore().addSample(file);
			JLabel label = new JLabel(file);
			constraints.gridx = 0;
			add(label, constraints);
			
			JButton start = new JButton("start");
			start.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					controller.getPlayer().startSample(controller.getLoopStore().getSample(id));
				}
			});
			constraints.gridx = 1;
			add(start, constraints);
			
			JButton stop = new JButton("stop");
			stop.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					controller.getPlayer().stopSample(controller.getLoopStore().getSample(id));
				}
			});
			constraints.gridx = 2;
			add(stop, constraints);
			
			JButton remove = new JButton("remove");
			remove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					controller.getLoopStore().removeSample(id);
					loopsPanel.remove(getPanel());
					loopsPanel.updateUI();
				}
			});
			constraints.gridx = 3;
			add(remove, constraints);
		}
		
		private JPanel getPanel(){
			return this;
		}
	}
}
