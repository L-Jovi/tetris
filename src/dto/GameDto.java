package dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import util.GameFunction;
import config.GameConfig;
import entity.GameAct;


/**
 * ��Ϸ������Ҫ���ʵ�����
 * 
 * @author J
 */
public class GameDto {
	
	
	
	/**
	 * �������
	 */
	// ��Ϸ���ڿ�Ⱥ͸߶�
	public static final int GAMEZONE_W = GameConfig.getSystemConfig().getMaxX() + 1;
	public static final int GAMEZONE_H = GameConfig.getSystemConfig().getMaxY() + 1;
	// ���ݿ� ��Ϸ���Ͻ�����
	private List<Player> dbRecode;
	// �������� ���ݿ��·���������
	private List<Player> diskRecode;
	// ��Ϸ��ͼ
	private boolean[][] gameMap;
	// �������ж�ά�������ݣ���ʾ�����С���飩
	private GameAct gameAct;
	// ��һ��Ҫ���ֵķ���
	private int next;
	// �ȼ�����
	private int nowLevel;
	// ���ڵĳɼ�
	private int nowPoint;
	// �Ƴ��˶�����
	private int nowRemoveLine;
	// ��ʾ��Ϸ�Ƿ��ǿ�ʼ״̬
	private boolean start;
	// �Ƿ���ʾ��Ӱ
	private boolean showShadow;
	// ��ͣ
	private boolean pause;
	// �ж��Ƿ�ʹ�ù�����
	private boolean cheat;
	// �߳�˯��ʱ��
	private long sleepTime;
	
	
	/**
	 * ���캯��
	 */
	public GameDto(){
		dtoInit();
	}
	/**
	 * dto ��ʼ�� 
	 * �������Ҫ  ��Ϊ����һ����Ϸ֮���������ݱ�������
	 */
	public void dtoInit(){
		this.gameMap = new boolean[GAMEZONE_W][GAMEZONE_H];
		// ��ʼ����ʱ��ȼ�����������������ȫ������
		this.nowLevel = 0;
		this.nowPoint = 0;
		this.nowRemoveLine = 0;
		this.pause = false;
		this.cheat = false;
		this.sleepTime = GameFunction.getSleepTimeByLevel(this.nowLevel);
	}
	
	
	/**
	 * ��������������Ҫ�������ݵ�get() set() ����
	 */
	public List<Player> getDbRecode() {
		return dbRecode;
	}
	public void setDbRecode(List<Player> dbRecode) {
		//�������������򷽷�Ȼ�� set �� dbRecode ֮��
		this.dbRecode = this.setFillRecode(dbRecode);
	}
	public List<Player> getDiskRecode() {
		return diskRecode;
	}
	public void setDiskRecode(List<Player> diskRecode) {
		//�������������򷽷�Ȼ�� set �� DiskRecode ֮��
		this.diskRecode = this.setFillRecode(diskRecode);
	}
	
	
	/**
	 * �ж���������Ƿ�����5��  ��������������Ϊ5��     ����֮������ķ���
	 * @param players
	 * @return players
	 * 
	 * �÷�����  setDbRecode ��  setDiskRecode ������
	 */
	private List<Player> setFillRecode(List<Player> players){
		//�����������Ϣ�Ļ��ʹ���
		if(players==null){
			players = new ArrayList<Player>();
		}
		//�������5�������Ϣ  ���Զ�����������
		while(players.size()<5){
			players.add(new Player("No Data", 0));
		}
		// �����ɼ��ŵ��������ϱ� ������Ϻ� set �� players ֮��
		Collections.sort(players);
		return players;
	}
	
	
	public boolean[][] getGameMap() {
		return gameMap;
	}
	public void setGameMap(boolean[][] gameMap) {
		this.gameMap = gameMap;
	}
	
	public GameAct getGameAct() {
		return gameAct;
	}
	public void setGameAct(GameAct gameAct) {
		this.gameAct = gameAct;
	}
	
	public int getNext() {
		return next;
	}
	public void setNext(int next) {
		this.next = next;
	}
	
	public int getNowLevel() {
		return nowLevel;
	}
	public void setNowLevel(int nowLevel) {
		this.nowLevel = nowLevel;
		// �����߳�˯��ʱ�� 
		//����ͨ�����������߳�˯��ʱ��д���������кô���  ���ǲ���Ҫ�ٿ����൱�е��̷߳�����һ�ΰ�һ�ε��ü���  ֻ��Ҫ���������ȼ���ʱ���ټ��������Ҫ�ı���߳�˯��ʱ�䣩
		this.sleepTime = GameFunction.getSleepTimeByLevel(this.nowLevel);
	}
	
	// �߳�˯��ʱ��ֻ�ṩ  get ����   ���ǲ��������Բ�����
	public long getSleepTime() {
		return sleepTime;
	}
	
	public int getNowPoint() {
		return nowPoint;
	}
	public void setNowPoint(int nowPoint) {
		this.nowPoint = nowPoint;
	}
	
	public int getNowRemoveLine() {
		return nowRemoveLine;
	}
	public void setNowRemoveLine(int nowRemoveLine) {
		this.nowRemoveLine = nowRemoveLine;
	}
	
	public boolean isStart() {
		return start;
	}
	public void setStart(boolean start) {
		this.start = start;
	}
	
	public boolean isShowShadow() {
		return showShadow;
	}
	// ע������  ��ת�˲�������   ������Ϊ false   ����پ�Ϊtrue
	// ������Ӱ����ͣ�����ر� ת��
	public void changeShowShadow() {
		this.showShadow = !this.showShadow;
	}
	
	public boolean isPause() {
		return pause;
	}
	public void changePause() {
		this.pause = !this.pause;
	}
	
	public boolean isCheat() {
		return cheat;
	}
	public void setCheat(boolean cheat) {
		this.cheat = cheat;
	}
	
	
	
}
