package service;


public interface GameService {
	
	
	/**
	 * ��Ϸ�����
	 */
	public boolean keyUp();
	public boolean keyDown();
	public boolean keyLeft();
	public boolean keyRight();
	
	/**
	 * ���ܼ�
	 */
	//����
	public boolean keyFunUp();
	//���
	public boolean keyFunDown();
	//����
	public boolean keyFunLeft();
	//ԲȦ
	public boolean keyFunRight();
	
	/**
	 * �������߳̿�ʼ��Ϸ
	 */
	public void startGame();
	
	/**
	 * ��Ϸ����Ϊ
	 */
	public void mainAction();
	

}
