package control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class PlayerControl extends KeyAdapter {
	
	
	private GameControl gameControl;
	//���췽��
	public PlayerControl(GameControl ctrl){
		this.gameControl=ctrl;
	}
	
	/**
	 * ���̰��»ᴥ�����¼�
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		this.gameControl.actionByKeyCode(e.getKeyCode());
	}
	
	
}
		
	

	
