package ui.window;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import ui.Img;
import ui.Layer;
import config.FrameConfig;
import config.GameConfig;
import config.LayerConfig;
import control.GameControl;
import control.PlayerControl;
import dto.GameDto;


@SuppressWarnings("serial")
public class JPanelGame extends JPanel{
	
	
	
	/**
	 * �����������
	 */
	//�����ļ��õ���ť���õ����Բ���  
	private static final int BTN_SIZE_W = GameConfig.getFrameConfig().getButtonConfig().getButtonW();
	private static final int BTN_SIZE_H = GameConfig.getFrameConfig().getButtonConfig().getButtonH();
	//��List������������ΪList����ʹ��add
	private List<Layer> Layers = null;
	private JButton btnStart;
	private JButton btnConfig;
	private GameControl gameControl = null;
	
	
	/**
	 * ������
	 * @param dto
	 */
	public JPanelGame(GameControl gameControl, GameDto dto){
		//������Ϸ������
		this.gameControl = gameControl;
		//���ò��ֹ�����Ϊ���ɲ���
		this.setLayout(null);
		//��ʼ���齨
		this.initComponent();
		//��ʼ����
		this.initLayer(dto);
		//��װ���̼�����
		this.addKeyListener(new PlayerControl(gameControl));
		
		// ��������  ���ǹ��Ӱ���
//		Method m = this.getClass().getMethod("init", null);
//		m.invoke(this, null);
		
	}
	
	
	/**
	 * ��ʼ���齨����ť"��ʼ" "����"��
	 */
	private void initComponent(){
		// ��ʼ����ʼ��ť
		this.btnStart = new JButton(Img.BTN_START);
		// ���ÿ�ʼ��ťλ�ã��������ļ� get �����������ݿ���
		this.btnStart.setBounds(GameConfig.getFrameConfig().getButtonConfig()
				.getStartX(), GameConfig.getFrameConfig().getButtonConfig()
				.getStartY(), BTN_SIZE_W, BTN_SIZE_H);
		// ����ʼ��ť�����¼�����
		this.btnStart.addActionListener(new ActionListener() {
			// ��ʼ�¼�����
			@Override
			public void actionPerformed(ActionEvent e) {
				gameControl.start();
				// ���ؽ��㣨����Ҫ����仰  ��Ȼ����ڵ���˿�ʼ֮��ͻ��޷�������Ϸ��������Ϊ��  ��Ϊû�н������
				requestFocus();
			}
		});
		// ��Ӱ�ť�����
		this.add(btnStart);
		// ��ʼ�����ð�ť
		this.btnConfig = new JButton(Img.BTN_CONFIG);
		// �������ð�ťλ�ã��������ļ� get �����������ݿ���
		this.btnConfig.setBounds(GameConfig.getFrameConfig().getButtonConfig()
				.getUserConfigX(), GameConfig.getFrameConfig()
				.getButtonConfig().getUserConfigY(), BTN_SIZE_W, BTN_SIZE_H);
		// ��"��ʼ"��"����"��ť���ü���
		this.btnConfig.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameControl.showUserConfig();
			}
		});
		// ��Ӱ�ť�����
		this.add(btnConfig);
	}
	
	/**
	 * ��ʼ���㣨��ÿ��ģ�鴰�ڣ�
	 */
	private void initLayer(GameDto dto){
		try{
			/**
			 * ȡ�� xml �����ļ��� game ��Ԫ�� frame ���������� 
			 * ���Ѿ��� GameConfig ����������������� get ������ֱ�ӵ��ø÷���ȡ�����úõĲ���ֵ��
			 */
			FrameConfig fCfg = GameConfig.getFrameConfig();
			//ȡ������һ��LayerConfigԪ�ص�����
			List<LayerConfig> LayersCfg = fCfg.getLayersConfig();
			//�����˼���Layer�ʹ����������ȵ�����
			Layers=new ArrayList<Layer>(LayersCfg.size());
			//ѭ������ÿ��Layer���������в�Ķ���
			for (LayerConfig LayerCfg : LayersCfg) {
				//��������
				Class<?> cls = Class.forName(LayerCfg.getClassName());
				//���ȡ����Ĺ��캯��
				Constructor<?> ctr = cls.getConstructor(int.class, int.class, int.class, int.class);
				//���ù��캯��������Ķ���ǿ���ö���һ������Layer
				Layer layer = (Layer)ctr.newInstance(
						LayerCfg.getX(),
						LayerCfg.getY(),
						LayerCfg.getW(),
						LayerCfg.getH()
					);
				//��ÿ��Layer�������֮ǰ������Ϸ���ݶ���
				layer.setDto(dto);
				//�Ѵ�����Layer������뼯��
				Layers.add(layer);
				}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		// ���ñ�����ˢ�»���ȷ������
		super.paintComponent(g);
		// ������Ϸ����
		// ˢ�²㴰�� paint��Ϊ����ÿ����������paint�������������LayButton�е�paint����
		for (int i = 0; i < Layers.size(); Layers.get(i++).paint(g));
	}

	
	/**
	 * ���ư�ť�Ƿ�ɵ��
	 */
	public void buttonSwich(boolean onOff){
		this.btnConfig.setEnabled(onOff);
		this.btnStart.setEnabled(onOff);
	}

	
	
}
