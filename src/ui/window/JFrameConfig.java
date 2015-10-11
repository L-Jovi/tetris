package ui.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import ui.Img;
import util.FrameUtil;
import control.GameControl;


@SuppressWarnings("serial")
public class JFrameConfig extends JFrame{
	
	
	
	private JButton btnOK = new JButton("ȷ��");
	private JButton btnCancel = new JButton("ȡ��");
	private JButton btnUser = new JButton("Ӧ��");
	private JLabel errorMsg = new JLabel();
	// Ƥ���б�
	@SuppressWarnings("rawtypes")
	private JList skinList = null;
	// Ƥ��ͼƬ��Ԥ��ͼ
	private JPanel skinView = null;
	// Ƥ������
	@SuppressWarnings("rawtypes")
	private DefaultListModel skinData = new DefaultListModel(); 
	private GameControl gameControl;
	
	
	/**
	 * ���ð����ı��λ��   
	 */
	private TextCtrl[] keyText = new TextCtrl[8];
	/**
	 * PSP��ͼƬ
	 */
	private static final Image IMG_PSP = new ImageIcon("data/PSP.png").getImage();
	/**
	 * Ƥ��Ԥ��ͼ
	 */
	private Image[] skinViewList = null;
//	private Image temp_skinImg = new ImageIcon("Graphics/eva.png").getImage();
	/**
	 * �����������Ϸ������͹��ܼ�
	 */
	private static final String[] METHOD_NAMES = {
		"keyRight", "keyUp", "keyLeft", "keyDown",
		"keyFunLeft", "keyFunUp", "keyFunRight", "keyFunDown"
	};
	/**
	 * ��ȡ������͹��ܼ���Ϣ�� .dat �ļ�
	 */
	private static final String PATH = "data/control.dat";
	
	
	/**
	 * ������
	 */
	public JFrameConfig(GameControl gameControl) {
		// �����Ϸ����������
		this.gameControl = gameControl;
		// �߽粼��
		this.setLayout(new BorderLayout());
		// ���ñ���
		this.setTitle("��λ����");
		// ��ʼ�����������
		this.initKeyText();
		/**
		 * ��������
		 */
		this.add(createMainPanel(), BorderLayout.CENTER);
		/**
		 * ��Ӱ�ť���
		 */
		this.add(this.createButtonPanel(), BorderLayout.SOUTH);
		//�������û��ı䴰�ڴ�С
		this.setResizable(false);
		//���ô��ڴ�С
		this.setSize(533,300);
		//����
		FrameUtil.setFrameCenter(this);
		
//		// TODO p)������
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//		this.setVisible(true);
	}

	
	/**
	 * ��ʼ�����������
	 */
	private void initKeyText(){
		int x = 0;
		int y = 35;
		int w = 55;
		int h = 20;
		//���һ���а���
		for (int i = 0; i < 4; i++) {
			keyText[i] = new TextCtrl(x, y, w, h, METHOD_NAMES[i]);
			y += 28;
		}
		//�ұ�һ���а���
		x += 467;
		y = 35;
		for (int i = 4; i < 8; i++) {
			keyText[i] = new TextCtrl(x, y, w, h, METHOD_NAMES[i]);
			y += 28;
		}
		try {
			//��ȡ�Ѿ�����İ�����Ϣ���������ܼ��ͷ������
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH));
			@SuppressWarnings("unchecked")
			HashMap<Integer, String> cfgSet = (HashMap<Integer, String>)ois.readObject();
			ois.close();
			//�� HashMap ת������
			Set<Entry<Integer, String>> entryset = cfgSet.entrySet();
			for (Entry<Integer, String> e: entryset) {
				for (TextCtrl tc: keyText) {
					//�Ƚϴ� HashMap ת�������ļ����� Value �Ƿ��� String ����������ȣ��ǵĻ��Ͱ� key ֵ set �� keyText ��
					if(tc.getMethodName().equals(e.getValue())){
						tc.setKeyCode(e.getKey());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	
	/**
	 * ��������壨ѡ���壩
	 */
	private JTabbedPane createMainPanel() {
		JTabbedPane jtp = new JTabbedPane();
		// ��������ѡ�������Ӧ�ķ�����ʾ����
		jtp.addTab("��������", this.createControlPanel());
		jtp.addTab("Ƥ������", this.createSkinPanel());
		return jtp;
	}
	/**
	 * ��ҿ����������
	 */
	private JPanel createControlPanel() {
		// �ڲ��ࣨ������ û�о��������� ��ʾ new ��һ���̳�JPanel ����Ķ��� jp �����Ǵ�ͳ������ֱ�� new ��һ�� JPanel
		// ���� jp��
		// ��ΪҪ��д JPanelGame �е� paintComponent ����
		// �����Ļ�����������һ���� ������ð� ���԰�������һ����Դ��ͺ���
		JPanel jp = new JPanel() {
			// ����ľ��ǹ������� ��Ϊ new ��һ��û�о��������������࣬���Թ�����Ҳû������
			{
			}
			@Override
			public void paintComponent(Graphics g) {
				g.drawImage(IMG_PSP, 0, 0, null);
			}
		};
		// ���ò��ֹ�����
		jp.setLayout(null);
		for (int i = 0; i < keyText.length; i++) {
			jp.add(keyText[i]);
		}
		return jp;
	}
	/**
	 * ���Ƥ�����
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Component createSkinPanel(){
		// ��ʼ�����   ��������߽粼��
		JPanel panel = new JPanel(new BorderLayout());
		// ͨ���ļ���·�����ʵ��ļ�
		File dir = new File(Img.GRAPHICS_PATH);
		// ������ʵ�·�������������Ƥ������ͨ���б���ʾ
		File[] files = dir.listFiles();
		this.skinViewList = new Image[files.length];
		// ��Ƥ��������ӵ�Ƥ���б������
		for(int i=0; i<files.length; i++){
			// ����ѡ��
			this.skinData.addElement(files[i].getName());
			// ����Ԥ��ͼ������Ԥ��ͼ·����
			this.skinViewList[i] = new ImageIcon(files[i].getPath() + "\\eva.png").getImage();
		}
		// ��Ƥ����������ӵ��б���
		this.skinList = new JList(skinData);
		// ����Ĭ��ѡ�е�һ��  Ҳ����default
		this.skinList.setSelectedIndex(0);
		// ����������  �㵽��һ��Ƥ����������ʾ������Ӧ��ͼƬ  �����������  MouseAdapter() �Ѿ�ʵ�������ж�����
		this.skinList.addMouseListener(new MouseAdapter() {
			@Override
			// ��������������ɿ���ʱ��ֻ��һ��ˢ�¶���   �Ϳ��԰�����ͼ�л��ɹ�
			public void mouseReleased(MouseEvent e) {
				repaint();
			}
		});
		
		// �̳���   
		this.skinView = new JPanel() {
			// �����ڴ���Ƥ��Ԥ��ͼ�����ʱ��ʹ�ӡһ��ͼƬ��Ϊ����������ʾ�������
			@Override
			public void paintComponent(Graphics g) {
//				// ����ķ���������ͼƬ���еķ���  
//				Image showImg = skinViewList[skinList.getSelectedIndex()];
//				int x = this.getWidth() - showImg.getWidth(null) >> 1;
//				int y = this.getHeight() - showImg.getHeight(null) >> 1;
//		        g.drawImage(showImg, x, y, null);
				// ѡ����һ��Ƥ�������Ͱ���ӦͼƬ��ӡ����
				g.drawImage(skinViewList[skinList.getSelectedIndex()], 0, 0, null);
			}
		};
		
		// ��������Ƥ���б�  �߽粼�ֵ�����   
		// �����Ƥ���б������һ����������װ  ��������Ӻÿ�һЩ
		panel.add(new JScrollPane(this.skinList), BorderLayout.WEST);
		// ���Ƥ��Ԥ��ͼ�������
		panel.add(this.skinView, BorderLayout.CENTER);
		return panel;
	}


	/**
	 * ������ť���
	 */
	private JPanel createButtonPanel() {
		//������ť���  ��ʽ���֣��Ҷ��룩
		JPanel jp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		/**
		 * "ȷ��"��ť�����¼�
		 * ����ť����¼�������������/�ڲ��ࣩ
		 * ע���:�ڲ��಻��д this
		 */
		this.btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//��"ȷ��"��ʱ���������ķ�����ǰ�� writeConfig Ҫ�ɹ���
				if (writeConfig()) {
					okButtonEvent();
					//ˢ�»���ķ���
					gameControl.setOver();
				}
			}
		});
		//���û��Ĵ�����ʾ
		this.errorMsg.setForeground(Color.RED);
		jp.add(this.errorMsg);
		jp.add(this.btnOK);
		/**
		 * "ȡ��"��ť�����¼�
		 */
		this.btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//��"ȡ��"��ʱ���������ķ���
				cancelButtonEvent();
				//ˢ�»���ķ���
				gameControl.setOver();
			}
		});
		jp.add(this.btnCancel);
		/**
		 * "Ӧ��"��ť�����¼�
		 */
		this.btnUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ͬ�������ǵ��"Ӧ��"��ʱ����÷������¼�
				writeConfig();
				// "Ӧ��"��ˢ��һ��ģ�����Ĵ���
				gameControl.repaint();
			}
		});
		jp.add(this.btnUser);
		return jp;
	}
	
	/**
	 * ���"ȷ��"��ť�����õ��¼�  
	 * ֮���Ժ�"Ӧ��"�ֿ�д����Ϊ���"ȷ��"��ť����Ҫ�رմ���
	 */
	private void okButtonEvent(){
		this.writeConfig();
		this.setVisible(false);
	}
	/**
	 * ���"ȡ��"��ť�������¼�
	 * ����ֻ�ǹرմ��ڣ����ǽ�������
	 */
	private void cancelButtonEvent(){
		this.setVisible(false);
	}
	/**
	 * ���"Ӧ��"��ť�����õ��¼�
	 * ��д����Ϸ���ã�
	 */
	private boolean writeConfig(){
		// ����ӳ�����
		HashMap<Integer,String> keySet = new HashMap<Integer,String>();
		// д������
		for (int i = 0; i < keyText.length; i++) {
			int keyCode = this.keyText[i].getKeyCode();
			// keCode Ϊ 0 ��ʱ�򷵻�Ϊ false
			if(keyCode == 0){
				this.errorMsg.setText("���󰴼�");
				return false;
			}
			keySet.put(keyCode, this.keyText[i].getMethodName());
		}
		// ���û�������жϵ��û�����������ͬ keyCode ��λ��ʱ�򣬺���ļ�λ��Ӧ�� String �Ḳ��ǰ��ļ�λ��Ӧ�� String
		if(keySet.size() != 8){
			this.errorMsg.setText("�������벻ͬ");
			return false;
		}
		try {
			/**
			 * ע��  �������Ҫ   �Ժ���µ�Ƥ�������������ѡ�����
			 * �л�Ƥ�� ��������Ĳ�����ʾ�����  getSelectedIndex ѡ���˵ڼ���Ƥ����
			 */
			Img.setSkin(this.skinData.get(this.skinList.getSelectedIndex()).toString() + "/");
//			this.skinData.get(this.skinList.getSelectedIndex());
			// д���������
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH));
			oos.writeObject(keySet);
			oos.close();
		} catch (Exception e) {
			this.errorMsg.setText(e.getMessage());
			return false;
		}
		this.errorMsg.setText(null);
		return true;
	}
	
	
	
}
