package ui;

import java.awt.Graphics;
import config.GameConfig;


public class LayerPoint extends Layer {
	
	
	
	/**
	 * �������λ��
	 */
	private final int POINT_BIT = 5;
	/**
	 * ���д�������������������һ����
	 */
	private static final int LEVEL_UP = GameConfig.getSystemConfig().getLevelUp();
	/**
	 * ����y����
	 */
	private final int rmlineY;
	/**
	 * ����y����
	 */
	private final int  pointY;
	/**
	 * ���õ�x����
	 */
	private final int comX;
	/**
	 * ����ֵy����
	 */
	private final int expY;
	
	
	/**
	 * ���췽��
	 */
	public LayerPoint(int x,int y,int w,int h){
		super(x,y,w,h);
		//��ʼ����ͨ��x���� 
		this.comX=this.w - IMG_NUMBER_W * POINT_BIT - PADDING;
		//��ʼ��������ʾ��y���� 
		this.pointY=PADDING;
		//��ʼ��������ʾ��y���� 
		this.rmlineY=this.pointY + Img.POINT.getHeight(null)+PADDING;
		//��ʼ��exp��ʾ��y����
		this.expY=this.rmlineY + Img.RMLINE.getHeight(null)+PADDING*2;
	}
	
	
	public void paint(Graphics g){
		this.createWindow(g);
		//���ڱ��⣨������
		g.drawImage(Img.POINT,this.x+PADDING*2, this.y+pointY, null);
		//��ʾ����
		this.drawNumberLeftPad(comX, pointY, this.dto.getNowPoint(), POINT_BIT, g);

		//���ڱ��⣨���У�
		g.drawImage(Img.RMLINE,this.x+PADDING*2, this.y+rmlineY, null);
		//��ʾ������
		this.drawNumberLeftPad(comX, rmlineY, this.dto.getNowRemoveLine(), POINT_BIT, g);
		//����ֵ�ۣ�����ֵ��
		int rmline=this.dto.getNowRemoveLine();
		//�����ֵ��Ϊ��������
		this.drawRect(expY, "��һ��", null, (double)(rmline % LEVEL_UP) / (double)LEVEL_UP,g);
		
//		//��Ѫ������Ư������ɫ��
//		Color MY_COLOR = new Color(0x90EE90);
//		g.setColor(MY_COLOR);
		//Ѫ����ɫ����Ч�����Ϸ�����
//		g.setColor(this.getNowColor((double)(rmline%LEVEL_UP), (double)LEVEL_UP));
//		g.fillRect(this.x+PADDING*2 + 2, this.y+expY-PADDING + 2, w, h-4);
	}
	
	
	/**
	 * ֵ����ɫ���䷽����Ѫ��Ϊ����
	 */
//	private Color getNowColor(double hp,double maxHp){
//		int colorR=0;
//		int colorG=255;
//		int colorB=0;
//		double hpHarf=maxHp/2;
//		if(hp>hpHarf){
//			colorR=255-(int)((hp-hpHarf)/(maxHp/2)*255);
//			colorG=255;
//		}else{
//			colorR=255;
//			colorG=(int)(hp/(maxHp/2)*255);
//		}
//		return new Color(colorR,colorG,colorB);
//	}
	
	

}
