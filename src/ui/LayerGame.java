package ui;

import java.awt.Graphics;
import java.awt.Point;
import config.GameConfig;
import entity.GameAct;


public class LayerGame extends Layer {
	
	
	
	/**
	 * ���ó���
	 */
	private static final int ACT_SIZE = 23;
	/**
	 * ��߾�
	 */
	private static final int LEFT_SIDE = 0;
	/**
	 * �ұ߾�
	 */
	private static final int RIGHT_SIDE = GameConfig.getSystemConfig().getMaxX();
	/**
	 * �õ����ѷ��������
	 */
	private static final int LOSE_IDX = GameConfig.getFrameConfig().getLoseIdx();
	
	
	/**
	 * ���캯��
	 */
	public LayerGame(int x,int y,int w,int h){
		super(x,y,w,h);
	}

	
	/**
	 * ���������Ʒ���
	 */
	public void paint(Graphics g) {
		// ��������
		this.createWindow(g);
//		// ����ǿ�ʼ״̬�ͻ��ƶ���
//		if(this.dto.isStart()){
		// �õ��������鼯��
		GameAct act = this.dto.getGameAct();
		if (act != null) {
			Point[] points = act.getActPoints();
			// ��ʾ��Ӱ
			this.drawShadow(points, g);
			// ���ƻ����
			this.drawMainAct(points, g);
		}
		// ������Ϸ��ͼ
		this.drawMap(g);
		// ��ͣ
		if(this.dto.isPause()){
			this.drawImageAtCenter(Img.PAUSE, g);
		}
	}
	
	
	/**
	 * ���ƻ����
	 * @param g
	 */
	private void drawMainAct(Point[] points, Graphics g) {
		// ��÷������ͱ�ţ�0~6��
		int typeCode = this.dto.getGameAct().getTypeCode();
//		typeCode = this.dto.isStart() ? typeCode : LOSE_IDX;
		// ���������Ķ���˹����
		for (int i = 0; i < points.length; i++) {
			this.drawActByPoint(points[i].x, points[i].y, typeCode + 1, g);
		}
	}
	/**
	 * ������Ϸ��ͼ
	 * @param g
	 */
	private void drawMap(Graphics g) {
		// ���Ƶ�ͼ
		boolean[][] map = this.dto.getGameMap();
		// ���㵱ǰ�ѻ���ɫ
		int lv = this.dto.getNowLevel();
		// �жϵȼ����� 0 ����ʱ���ٳ��ֶѻ���ɫΪ��ɫ ʹ��ɫѭ��ֻ�ں�ɫ��ʼ����ɫѭ��
		int imgIdx = lv == 0 ? 0 : (lv - 1) % 7 + 1;
		// TODO �������� imgIdx = 8 �����ѷ���
		// һ��һ�д�ӡ��ͼ
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[x].length; y++) {
				if (map[x][y]) {
					// ����ǿ�ʼ״̬����ʾ imgIdx ������ʾ LOSE_IDX
					this.drawActByPoint(x, y, imgIdx, g);
				}
			}
		}
	}


	/**
	 * ������Ӱ�ķ���
	 */
	private void drawShadow(Point[] points, Graphics g) {
		//���������Ӱ���㷵�ظ÷��������ж���ֱ�������ܹ���ӡ��Ӱ�������ִ�� if ֮��
		if(!this.dto.isShowShadow()){
			return;
		}
		int leftX = RIGHT_SIDE;
		int rightX = LEFT_SIDE;
		for (Point p: points) {
			leftX = p.x<leftX ? p.x : leftX;
			rightX = p.x>rightX ? p.x : rightX;
		}
		g.drawImage(Img.SHADOW, 
				this.x + BORDER + leftX*ACT_SIZE, 
				this.y + BORDER, 
				(rightX-leftX+1) * ACT_SIZE, 
				this.h - BORDER*2,
				null);
	}


	/**
	 * ���������ο�
	 * ��ȡ�õ�ͼƬ�г�һ��С����
	 * ����ImgIdx��ʾ�ڼ��ŷ���ͼƬ���ӵ�0�ſ�ʼ��
	 */
	private void drawActByPoint(int x, int y, int ImgIdx, Graphics g){
		ImgIdx = this.dto.isStart() ? ImgIdx : LOSE_IDX;
			g.drawImage(Img.ACT,
					this.x + x * ACT_SIZE + BORDER, 
					this.y + y * ACT_SIZE + BORDER, 
					this.x + x * ACT_SIZE + ACT_SIZE + BORDER, 
					this.y + y * ACT_SIZE + ACT_SIZE + BORDER, 
					ImgIdx * ACT_SIZE, 0,
					ImgIdx * ACT_SIZE + ACT_SIZE, ACT_SIZE, null);
	}

	
	
}
