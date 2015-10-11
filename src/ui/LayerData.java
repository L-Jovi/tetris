package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import config.GameConfig;
import dto.Player;


/**
 * ��ǰ�̳�Layer   ��� LayerDataBase �� LayerDisk ���̳�
 * 
 * ���ֶ������ļ̳з���������Ч���ٸ����ƴ���Ķ�����д
 */
public abstract class LayerData extends Layer{
	
	
	
	/**
	 * TODO  ���������  �����ļ�
	 */
		private static final int MAX_ROW = GameConfig.getDataConfig().getMaxRow();
	/**
	 *  ��ʼY����
	 */
	private static int START_Y = 0;
	/**
	 *  ֵ���⾶
	 */
	private static int RECT_H = IMG_RECT_H;
	/**
	 * ֵ�ۼ��
	 */
	private static int SPA=0;
	
	
	/**
	 * ���췽����ʼ��
	 */
	public LayerData(int x, int y, int w, int h) {
		super(x, y, w, h);
		SPA=(this.h - RECT_H*5 - PADDING*2 - Img.DB.getHeight(null)) / MAX_ROW;
		START_Y=PADDING+5 + Img.DB.getHeight(null) + SPA;
	}

	
	/**
	 * ��������ֵ��
	 * 
	 * @param imgTitle  ����ͼƬ
	 * @param players  ����Դ
	 * @param g  ����
	 */
	public void showData(Image imgTitle, List<Player> players, Graphics g){
		g.drawImage(imgTitle,this.x+PADDING, this.y+PADDING, null);
		//�������ձ���Ϸ���������
//		List<Player> players=this.dto.getDbRecode();
		int nowPoint=this.dto.getNowPoint();
		for (int i = 0; i < MAX_ROW; i++) {
			Player pla=players.get(i);
			//ȡ��������
			int recodePoint=pla.getPoint();
			//��ǰ�����������������ֵ
			double percent=(double)nowPoint / recodePoint;
			percent=percent>1 ? 1.0 : percent;
			//ѭ����ӡ��gameDto�л�õĸ������������ڵĵȼ����������������ȵȣ�
			//nowPoints�������   p.getPoint()�����ĸ
			String strPoint = recodePoint==0 ? null : Integer.toString(recodePoint);
			this.drawRect(START_Y + i*(RECT_H+SPA), 
					pla.getName(), strPoint, 
					percent, g);
		}
	}
	
	
	/**
	 * �������޷���ʵ����
	 */
	@Override
	abstract public void paint(Graphics g);


	
}
