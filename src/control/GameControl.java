package control;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import service.GameService;
import service.GameTetris;
import ui.window.JFrameConfig;
import ui.window.JFrameGame;
import ui.window.JFrameSavePoint;
import ui.window.JPanelGame;
import config.DataInterfaceConfig;
import config.GameConfig;
import dao.Data;
import dto.GameDto;
import dto.Player;


/**
 * ������Ҽ����¼�
 * ������Ϸ�߼�
 * 
 * @author J
 */
public class GameControl {
	
	
	
	/**
	 * ���ݷ��ʽӿ�A
	 */
	private Data dataA;
	/**
	 * ���ݷ��ʽӿ�B
	 */
	private Data dataB;
	
	/**
	 * ����GameService�ࣨ��Ϸ�߼���
	 */
	private GameService gameService;
	/**
	 * ��Ϸ�����
	 */
	private JPanelGame panelGame;
	/**
	 * ��Ϸ���ƴ��ڣ�����ģ�������Ǹ�������
	 */
	private JFrameConfig frameConfig;
	/**
	 * ��������Ĵ���
	 */
	private JFrameSavePoint frameSavePoint;
	/**
	 * ͨ��ӳ�������Ϸ������Ϊ 
	 * Integer �൱�� keyCode 
	 */
	private Map<Integer,Method> actionList;
	/**
	 * ��Ϸ����Դ
	 */
	private GameDto dto = null;
	/**
	 * �����̱߳�������Ϸ�̣߳�
	 */
	private Thread gameThread = null;
	
	
	/**
	 * ���췽��
	 */
	public GameControl(){
		// ����GameDto�����������õ���Ϸ�������ݣ�
		this.dto = new GameDto();
		// ����GameService����������Ϸ����Դ��
		this.gameService = new GameTetris(dto);
		// �����ӿ�A����
		this.dataA = createGameObject(GameConfig.getDataConfig().getDataA());
		// �������ݿ��¼����Ϸ
		this.dto.setDbRecode(dataA.loadData());
		// �����ӿ�A����
		this.dataB = createGameObject(GameConfig.getDataConfig().getDataB());
		// ���ñ��ش��̼�¼����Ϸ
		this.dto.setDiskRecode(dataB.loadData());
		// ����һ��JPanelGame�������������õ���Ϸ��壩
		this.panelGame = new JPanelGame(this, dto);
		// ��ȡ�û����Ƽ�����
		this.setControlConfig();
		// ��ʼ���û����ô���
		this.frameConfig = new JFrameConfig(this);
		// ��ʼ�������¼����
		this.frameSavePoint = new JFrameSavePoint(this);
		// ������Ϸ�����ڣ���װ��Ϸ���
		new JFrameGame(this.panelGame);
	}
	
	
	/**
	 * ��ȡ�û����Ƽ�����
	 */
	private void setControlConfig(){
		//���� ������ �� ������ ��ӳ������
		this.actionList = new HashMap<Integer, Method>();
		//��ȡ�Ѿ�����İ�����Ϣ���������ܼ��ͷ������
		try {
			@SuppressWarnings("resource")
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/control.dat"));
			@SuppressWarnings("unchecked")
			HashMap<Integer, String> cfgSet = (HashMap<Integer, String>)ois.readObject();
			//��� HashMap ת���ļ���
			Set<Entry<Integer, String>> entryset = cfgSet.entrySet();
			//������÷������Ի�ȡ��Ӧ����
			//ע����ת����  Set<Entry<Integer, String>> ������ String ��ŵ��Ƿ��������� HashMap<Integer, String> �� String ��ŵ��Ƿ���
			for(Entry<Integer, String> e: entryset){
				actionList.put(e.getKey(), this.gameService.getClass().getMethod(e.getValue()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * �������ݶ���
	 * 
	 * @param cfg
	 * @return
	 */
	private Data createGameObject(DataInterfaceConfig cfg) {
		try {
			// ��������
			Class<?> cls = Class.forName(cfg.getClassName());
			// ��ù�����
			Constructor<?> ctr = cls.getConstructor(HashMap.class);
			// ��������
			return (Data)ctr.newInstance(cfg.getParam());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	/**
	 * ������Ҽ�����������Ӧ��Ϊ
	 */
	public void actionByKeyCode(int keyCode) {
		try {
			// ���淽���ж���Ҽ���İ����Ƿ��������ļ�����ڣ�ֻ�д����˲Ż���þ�����Ӧ����
			// ���û�������ж����û��ֻ��㵽���������Ļ���Ϸ�ᱨ�����������ò�Ҫ����Ϊ�á���
			if (this.actionList.containsKey(keyCode)) {
				// ��������ͨ����Ұ���ֱ�ӵ��÷�������������ע�͵������Ч����ͬ  �������Ӽ�
				this.actionList.get(keyCode).invoke(this.gameService);
//				// ��÷�����
//				String methodName = this.actionList.get(keyCode);
//				// ��÷�������
//				Method actionMethod = this.gameService.getClass().getMethod(methodName);
//				// ���÷���
//				actionMethod.invoke(this.gameService);
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
		this.panelGame.repaint();
	}


	/**
	 * ��ʾ��ҿ��ƴ��ڣ���ʾģ������
	 */
	public void showUserConfig() {
		this.frameConfig.setVisible(true);
	}


	/**
	 * ˢ��һ�»��棨ģ�������ڹر��¼���
	 */
	public void setOver() {
		this.panelGame.repaint();
		// ���ú�ģ������λ���ټ���һ�Σ��൱���ٶ�ȡһ���û���λ���ã�
		this.setControlConfig();
	}


	/**
	 * ��ʼ��ť�¼�
	 * JPanelGame ����  GameControl ���� gameControl ���� start ����
	 */
	public void start() {
		// �����ʼ������Ϸ֮���޷����������ť
		this.panelGame.buttonSwich(false);
		// ��Ϸ��ʼ��ر�ģ�����ͱ��������¼���ڣ�����Ϊ�˷�ֹ��Щ���ڿ���ģ�������ڵ������ֱ�ӿ�ʼ��Ϸ��
		this.frameConfig.setVisible(false);
		this.frameSavePoint.setVisible(false);
		// ��Ϸ���ݳ�ʼ��
		this.gameService.startGame();
		// �����̶߳����ڲ�����Է��ʵ����϶���
		this.gameThread = new MainThread(){};
		// �����̶߳���
		this.gameThread.start();
		// ˢ�»���
		this.panelGame.repaint();
	}
	
	
	/**
	 * ��Ϸʧ��֮��Ĵ�����ʾ�����¼�÷ֵĴ��ں����¿�ʼ��Ϸ��
	 */
	private void afterLose(){
		// ������Ǹ���ҿ�����Ц��  ������������װ�ť  �������޷�����
		if (!this.dto.isCheat()) {
			// ��ʾ "����͵÷ֵĴ���"
			this.frameSavePoint.showWindow(this.dto.getNowPoint());
		}
		// ʹ��ť���Ե��
		this.panelGame.buttonSwich(true);
	}
	
	
	/**
	 * ˢ�»���ķ���
	 */
	public void repaint() {
		this.panelGame.repaint();
	}
	
	
	/**
	 * ��������ķ���
	 * @param name
	 */
	public void savePoint(String name) {
		Player pla = new Player(name, this.dto.getNowPoint());
		// �ѷ�����¼д�����ݿ�ͱ��ؼ�¼֮��
		this.dataA.saveData(pla);
		this.dataB.saveData(pla);
		// �������ݿ��¼����Ϸ ���ѷ�����������ݿ�ͱ���֮�������ٶ�һ�����ݣ�
		this.dto.setDbRecode(dataA.loadData());
		// ���ñ��ؼ�¼����Ϸ
		this.dto.setDiskRecode(dataB.loadData());
		// ˢ�»���
		this.panelGame.repaint();
	}	
	
	
	/**
	 * �������Ȼ���ڲ���  ���Կ�����  GameControl �����һ����Ա
	 */
	private class MainThread extends Thread {
		@Override
		public void run() {
			// ����Ķ��󴴽���ɺ�ˢһ�λ���
			panelGame.repaint();
			// ��ѭ��
			while (dto.isStart()) {
				try {
					// �߳�˯��ʱ��
					Thread.sleep(dto.getSleepTime());
					// ��ͣ״̬��ʱ��� continue ����������  Thread.sleep(500); ���ﲻ�ϵ�ֹͣ����
					if(dto.isPause()){
						continue;
					}
					// ������Ϸ����Ϊ����ʵ���෽���е������µĽ��䣩
					gameService.mainAction();
					// ��һ��ˢһ�λ���
					panelGame.repaint();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// �̲߳����ߵ�ʱ��Ҳ������Ϸʧ���ˣ�   ���ǵ�������ķ�������֮��Ķ���
			afterLose();
		}
	}


	
}
