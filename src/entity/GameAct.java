package entity;

import java.awt.Point;
import java.util.List;
import config.GameConfig;


/**
 * �����װ�˶���˹����ʵ��
 * 
 * @author J
 */
public class GameAct {
	
	
	
	/**
	 * ��������
	 */
	private Point[] actPoints=null;
	/**
	 * ������
	 */
	private int typeCode;
	/**
	 * ��ͼ���������С�߽糣��
	 */
	private final static int MIN_X = GameConfig.getSystemConfig().getMinX();
	private final static int MAX_X = GameConfig.getSystemConfig().getMaxX();
	private final static int MIN_Y = GameConfig.getSystemConfig().getMinY();
	private final static int MAX_Y = GameConfig.getSystemConfig().getMaxY();
	/**
	 * ����һ������źܶ������˹����
	 */
	private final static List<Point[]> TYPE_CONFIG = GameConfig.getSystemConfig().getTypeConfig();
	/**
	 * ͨ������ж��Ƿ��ܹ���ת
	 */
	private final static List<Boolean> TYPE_ROUND = GameConfig.getSystemConfig().getTypeRound();
	
	
	public GameAct(int typeCode){
		this.init(typeCode);
	}
	
	
	/**
	 * ȡ��һ�ֶ���˹����
	 */
	public void init(int typeCode){
		this.typeCode = typeCode;
		Point[] points = TYPE_CONFIG.get(typeCode);
		actPoints = new Point[points.length];
		//���е�һ������˹��������������ĸ�С����Ļ������꣬ѭ����ӡ�ĸ�С�����Ϊһ�������Ķ���˹����
		for (int i = 0; i < points.length; i++) {
			actPoints[i] = new Point(points[i].x, points[i].y);
		}
	}
	
	
	/**
	 * ��װȡ����һ�ֶ���˹����ʵ��
	 */
	public Point[] getActPoints() {
		return actPoints;
	}
	
	
	/**
	 * �����ƶ��ķ���
	 * 
	 * @param moveX:x��ƫ����
	 * @param moveY:y��ƫ����
	 */
	public boolean move(int moveX, int moveY, boolean[][] gameMap){
		//�÷��鶯����
		for(int i=0;i<actPoints.length;i++){
			int newX = actPoints[i].x + moveX;
			int newY = actPoints[i].y + moveY;
			//�ƶ��߽��ж�
			if(isOverZone(newX, newY, gameMap)){
				return false;
			}
		}
		for(int i=0;i<actPoints.length;i++){
			actPoints[i].x += moveX;
			actPoints[i].y += moveY;
		}
		return true;
	}
	
	
	/**
	 * ������ת�ķ���
	 * 
	 * ˳ʱ�루ע����Ļ����ϵ��ѿ�������ϵ�෴��
	 * A.x=O.y+O.x-B.y
	 * A.y=O.y-O.x+B.x
	 * ��ʱ��
	 * A.x=O.x-O.y+B.y
	 * A.y=O.x+O.y-B.x
	 */
	public void round(boolean[][] gameMap){
		//typeCode ��Ϊ�±���� TYPE_ROUND �����ж��Ƿ���ת    ���������ļ��� true ����ת  false �㲻��ת
		if(!TYPE_ROUND.get(typeCode)){
			return;
		}
		/*ע��i��1��ʼ������0��
		��Ϊ����Ҫ�����ĵ������仯
		ֻ��Ҫ���actPoints[1]��ʼ��actPoints[3]��С����������ת�仯*/
		for(int i=1; i<actPoints.length; i++){
			//˳ʱ�루����˹����ֻ����һ��������ת��
			int newX=actPoints[0].y+actPoints[0].x-actPoints[i].y;
			int newY=actPoints[0].y-actPoints[0].x+actPoints[i].x;
			//��ת�߽��ж�
			if(this.isOverZone(newX, newY, gameMap)){
				return;
			}
		}
		for(int i=1; i<actPoints.length; i++){
			//�߽��ж������ִ����ת
		    int newX=actPoints[0].y+actPoints[0].x-actPoints[i].y;
			int newY=actPoints[0].y-actPoints[0].x+actPoints[i].x;
			actPoints[i].x=newX;
			actPoints[i].y=newY;
		}
	}
	
	
	/**
	 * �߽��ж�����������ﹲͬ�ж�
	 * �ƶ�����ת����һ�ױ߽��ж�����
	 * �ж��Ƿ񳬳��߽�
	 */
	private boolean isOverZone(int x,int y,boolean[][] gameMap){
		return x<MIN_X || x>MAX_X || y<MIN_Y || y>MAX_Y || gameMap[x][y];
	}


	/**
	 * ��÷������ͱ��
	 */
	public int getTypeCode() {
		return typeCode;
	}

	
	
}
