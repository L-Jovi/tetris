package ui;

import java.awt.Graphics;


public class LayerLevel extends Layer {
	
	
	//"�ȼ�"������ͼƬ�Ŀ�ȣ���Ϊ�����õ��������Գ�������
	private static final int IMG_LV_W = Img.LEVEl.getWidth(null);

	//���췽��
	public LayerLevel(int x,int y,int w,int h){
		super(x,y,w,h);
	}
	
	/**
	 * ��ӡ����
	 */
	public void paint(Graphics g){
		this.createWindow(g);
		int centerX=this.w - IMG_LV_W>>1;
		//����"�ȼ�"�������ڴ�����Ӧλ��
		g.drawImage(Img.LEVEl,this.x+centerX, this.y+PADDING-5, null);
		//��ʾ�ȼ�
		this.drawNumberLeftPad(centerX, 40, this.dto.getNowLevel(), 2, g);
	}
	
	
}
