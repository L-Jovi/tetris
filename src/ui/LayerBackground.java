package ui;

import java.awt.Graphics;


public class LayerBackground extends Layer {

	
	
	//TODO ��ʱ����
//	private static Image IMG_BG_TEMP=new ImageIcon("Graphics/background/eva2.jpg").getImage();
	/**
	 * ���췽��
	 */
	public LayerBackground(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	
	@Override
	public void paint(Graphics g) {
		int bgIdx = this.dto.getNowLevel() % Img.BG_LIST.size();
		g.drawImage(Img.BG_LIST.get(bgIdx), 0, 0, 900, 550, null);
	}

	
	
}
