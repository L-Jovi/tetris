package service;

import java.awt.Point;
import java.util.Map;
import java.util.Random;
import config.GameConfig;
import dto.GameDto;
import entity.GameAct;


/**
 * ������ �ӿ� GameService ��ʵ����
 * 
 * @author J
 */
public class GameTetris implements GameService {

	
	
	/**
	 * ��Ϸ���ݶ���
	 */
	private GameDto dto;
	/**
	 * �����������
	 */
	private Random random = new Random();
	/**
	 * ���峣�����������������
	 */
	private static final int MAX_TYPE = GameConfig.getSystemConfig().getTypeConfig().size();
	/**
	 * ���д�������������������һ����
	 */
	private static final int LEVEL_UP = GameConfig.getSystemConfig().getLevelUp();
	/**
	 * ����������еļӷ�����
	 */
	private static final Map<Integer, Integer> PLUS_POINT = GameConfig.getSystemConfig().getPlusPoint();

	
	/**
	 *  ���췽��
	 */
	public GameTetris(GameDto dto) {
		this.dto = dto;
	}

	
	/**
	 * ������������ᴥ������Ϊ
	 */
	// �ϣ�0��������-1�������ϣ�
	public boolean keyUp() {
		if(!this.dto.isStart()){
			return true;
		}
		// �����ͣ��֮�����������Ϸ���İ���������ת����
		if (dto.isPause()) {
			return true;
		}
		// Ϊ��ֹ�����߳�ͬһʱ�乲ͬ����ͬһ�����ݶ�������ʹ��ͬ�������� �������ĺô����ṩ���Ӻͻ�����ƣ�һ��ֻ����һ���̷߳��ʸ����ݶ���
		synchronized (this.dto) {
			// ����˹���鲻��Ҫ�����ƶ�������ֱ�ӵ�����ת��ʽ
			this.dto.getGameAct().round(this.dto.getGameMap());
		}
		return true;
	}

	// ��
	public boolean keyDown() {
		if(!this.dto.isStart()){
			return false;
		}
		// �����ͣ��֮�����������·���İ������м����ƶ�����
		if (dto.isPause()) {
			return true;
		}
		synchronized (this.dto) {
			if (this.dto.getGameAct().move(0, 1, this.dto.getGameMap())) {
				return false;
			}
			// ���鵽�������޷��ƶ���ʱ���õ�ͼ����
			boolean[][] map = this.dto.getGameMap();
			// ȡ�÷���ʵ�����
			Point[] act = this.dto.getGameAct().getActPoints();
			// �ڵ�ͼ�ϴ�ӡ�����ײ�ķ���
			for (int i = 0; i < act.length; i++) {
				map[act[i].x][act[i].y] = true;
			}
			// �ж����в��������е���������þ���ֵ
			int plusExp = this.plusExp();
			// ������һ����ֵ���ڣ��Ǿ��Ǵ���0����ʱ��������Ӿ���ֵ�ķ���
			// ˵��һ���������������еĻ��Ż�����������Ӿ���ֵ�ķ���
			if (plusExp > 0) {
				// ���Ӿ���ֵ
				this.plusPoint(plusExp);
			}
			// ������һ�����飨ˢ���µķ��飩
			this.dto.getGameAct().init(this.dto.getNext());
			// �����������һ������
			this.dto.setNext(random.nextInt(MAX_TYPE));
			// �����Ϸ�Ƿ�ʧ��
			if (this.isLose()) {
				// ���֮��ֱ�ӽ�����Ϸ��ֹͣ�߳�������
				this.dto.setStart(false);
			}
		}
		return true;
	}
	/**
	 * ���ӷ����������ķ���
	 */
	private void plusPoint(int plusExp) {
		int lv = this.dto.getNowLevel();
		int rmLine = this.dto.getNowRemoveLine();
		// ���������ڵķ���������Ҫ�ǵ�  set �ӹ�֮��ķ���
		int point = this.dto.getNowPoint();
		// ���������� ռ �������� �ļ���֮20  ���� �»�õľ���   ���ڵ���   �������������Ļ������Ǵ���20�Ļ���
		if(rmLine % LEVEL_UP + plusExp >= LEVEL_UP){
			// �ȼ�+1  ������ set �� dto ��ȥ
			this.dto.setNowLevel(++lv);
		}
		this.dto.setNowRemoveLine(rmLine + plusExp);
		this.dto.setNowPoint(point + PLUS_POINT.get(plusExp));
	}
	/**
	 * ��Ϸʧ�ܵķ���
	 */
	private boolean isLose() {
		// ������ڵĻ����
		Point[] actPoints = this.dto.getGameAct().getActPoints();
		// ������ڵ���Ϸ��ͼ
		boolean[][] map = this.dto.getGameMap();
		// �ж϶���˹�����ϵ�4����Ԫ���Ƿ�͵�ͼ��Ԫ�����غ�
		for (int i = 0; i < actPoints.length; i++) {
			if(map[actPoints[i].x][actPoints[i].y]){
				return true;
			}
		}
		return false;
	}

	// ��
	public boolean keyLeft() {
		// �����ͣ��֮��������������İ��������ƶ�����
		if (dto.isPause()) {
			return true;
		}
		synchronized (this.dto) {
			this.dto.getGameAct().move(-1, 0, this.dto.getGameMap());
			return true;
		}
	}

	// ��
	public boolean keyRight() {
		// �����ͣ��֮�����������ҷ���İ��������ƶ�����
		if (dto.isPause()) {
			return true;
		}
		synchronized (this.dto) {
			this.dto.getGameAct().move(1, 0, this.dto.getGameMap());
			return true;
		}
	}

	
	/**
	 * �󶨹��ܼ�����
	 */
	// ���׼���ÿ������10�֣�
	@Override
	public boolean keyFunUp() {
		// ��Ϸ��ʼ��֮����������ʹ�����׼ӷֵĹ���
		if (this.dto.isStart()) {
			// ������һ�������趨  ������������Ϸ�Ĺ�����ʹ�ù�����  ������Ϊtrue
			this.dto.setCheat(true);
			this.plusPoint(4);
//			 // ����ķ����ǲ������׼��ķ��� ���һ�μ�10��
//			 int point = this.dto.getNowPoint();
//			 int rmline = this.dto.getNowRemoveLine();
//			 int lv = this.dto.getNowLevel();
//			 point += 10;
//			 rmline += 1;
//			 if (rmline % 20 == 0) {
//			 lv += 1;
//			 }
//			 this.dto.setNowPoint(point);
//			 this.dto.setNowLevel(lv);
//			 this.dto.setNowRemoveLine(rmline);
		}
		return true;
	}
	
	// ���
	// ˲������
	@Override
	public boolean keyFunDown() {
		// ��Ϸ��ʼ��֮����������ʹ��˲������Ĺ���
		if (this.dto.isStart()) {
			// �����ͣ��֮��������˲������Ĺ��ܰ������в���
			if (dto.isPause()) {
				return true;
			}
			while (!keyDown());
		}
		return true;
	}
	
	// ����
	// ��Ӱ����
	@Override
	public boolean keyFunLeft() {
		this.dto.changeShowShadow();
		return true;
	}
	
	// ԲȦ
	// ��Ϸ��ͣ
	@Override
	public boolean keyFunRight() {
		// ֻ�е���Ϸ��ʼ��ʱ��Ҳ�����������̵߳�ʱ�򣩲�������ҵ�����ͣ���� 
		if (this.dto.isStart()) {
			this.dto.changePause();
		}
		return true;
	}

	
	/**
	 * �������е���������þ���ֵ�ķ���
	 */
	private int plusExp() {
		// ���õ����飨�����Ϸ��ͼ��
		boolean map[][] = this.dto.getGameMap();
		int exp = 0;
		// ����Yɨ���ͼ���鿴�Ƿ��������
		for (int y = 0; y < GameDto.GAMEZONE_H; y++) {
			// �����ж��Ƿ�������еķ��� �ֿ�д���� for �� if �����Ƕ��
			if (isCanRemoveLine(y, map)) {
				// �������еĻ����¸ҵ����а�!
				this.removeLine(y, map);
				// ���������Ӿ���ֵ
				exp++;
			}
		}
		return exp;
	}
	/**
	 * �ж�ĳһ���Ƿ����       ������ y ���ڼ��У� �� ��Ϸ��ͼ map
	 */
	private boolean isCanRemoveLine(int y, boolean map[][]) {
		// �Ե�ͼ��ÿһ����Ԫ��ɨ�� ��23*23������
		for (int x = 0; x < GameDto.GAMEZONE_W; x++) {
			if (!map[x][y]) {
				// �����һ����Ԫ���ǿ�϶�Ļ� ������һ��ѭ�������ж���
				return false;
			}
		}
		return true;
	}
	/**
	 * ���в���
	 */
	private void removeLine(int rowNumber, boolean[][] map) {
		for (int x = 0; x < GameDto.GAMEZONE_W; x++) {
			// �ӵ� y �п�ʼ�������������ǵ� 14 �п������еĻ��Ͱ���13�и�14  12��13��˳��������鸳ֵ
			for (int y = rowNumber; y > 0; y--) {
				map[x][y] = map[x][y-1];
			}
			map[x][0] = false;
		}
	}
	

	/**
	 * ���߳�������Ϸ��ʵ��
	 */
	@Override
	public void startGame() {
		// ���������һ������
		this.dto.setNext(random.nextInt(MAX_TYPE));
		// ����������ڷ���
		this.dto.setGameAct(new GameAct(random.nextInt(MAX_TYPE)));
		// ������Ϸ״̬Ϊ��ʼ
		this.dto.setStart(true);
		// ���¿�ʼ��Ϸ��ʱ��Ҫ��  dto ��ʼ��
		this.dto.dtoInit();
	}

	
	/**
	 * ʵ����Ϸ����Ϊ
	 */
	@Override
	public void mainAction(){
		this.keyDown();
	}
	
	
}
