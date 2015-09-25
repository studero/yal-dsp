package ch.sulco.yal.dsp;

import ch.sulco.yal.dsp.audio.onboard.Player;

public class Controller {
	private Player player;
	
	public Controller(){
		player = new Player();
	}
	
	public Player getPlayer(){
		return player;
	}

//	public static void main(String[] args) throws Exception {
////		for(Info info : AudioSystem.getMixerInfo()){
////			System.out.println(info.getName()+" "+info.getDescription());
////		}
////		Recorder recorder = new Recorder();
//		player = new Player();
//		int id1 = player.addLoop("sounds/fm_edd_109_05.wav");
//		int id2 = player.addLoop("sounds/dx5_110_01.wav");
////		int id2 = player.addLoop("sounds/Buzzy_b_03.wav");
//		player.startLoop(id1);
//		player.startLoop(id2);
//		JOptionPane.showMessageDialog(null, "Close to exit!");
//		player.shutDown();
//	}

}
