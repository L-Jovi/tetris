package ui;

import java.awt.Graphics;


public class LayerDataBase extends LayerData {

		
	/**
	 * ���췽��
	 */
	public LayerDataBase(int x,int y,int w,int h){
		super(x,y,w,h);
	}
	
	/**
	 * "���ݿ�"����
	 */
	public void paint(Graphics g){
		this.createWindow(g);
		/**
		 * �̳�  �Ѿ��̳�Layer��  LayerData  
		 * �� LayerDisk �ֱ�̳� LayerData
		 */
		this.showData(Img.DB, this.dto.getDbRecode(), g);
	}

	
}
