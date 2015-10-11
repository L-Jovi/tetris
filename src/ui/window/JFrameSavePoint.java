package ui.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import control.GameControl;
import util.FrameUtil;


@SuppressWarnings("serial")
public class JFrameSavePoint extends JFrame{
	
	
	
	/**
	 * ��������
	 */
	private JLabel LbPoint = null;
	private JLabel errMsg = null;
	private JTextField txName= null;
	private JButton btnOK = null;
	// �������ﵱȻ��Ҫ���ࣨҲ���Ƿ�����ӱ��洰�ڣ��Ϳ������ཨ����ϵ  ��������������������
	private GameControl gameControl = null;
	
	
	/**
	 * ������
	 */
	public JFrameSavePoint(GameControl gameControl){
		// ��ʼ���ͽ����Ŀ���������
		this.gameControl = gameControl;
		this.setTitle("�����¼");
		this.setSize(300, 128);
		FrameUtil.setFrameCenter(this);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		// ��������ĸ������齨
		this.createCom();
		this.createAction();
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	/**
	 * ��ʾ����
	 */
	public void showWindow(int point){
		this.LbPoint.setText("���ı��ε÷�Ϊ: " + point);
		this.setVisible(true);
	}
	
	
	/**
	 * �����ñ����¼���ڵ��¼�����
	 */
	private void createAction() {
		this.btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ������������һ�������ַ������ȵ��ж�   ��Ȼ�����Լ���������������������֤
				String name = txName.getText();
				if(name.length()>16 || name==null || "".equals(name)){
					errMsg.setText("�������..  �����û����ô");
				}else{
					// �ѷ�����������֮ǰ�ȰѼ�¼�����ĶԻ���ر�
					setVisible(false);
					gameControl.savePoint(name);
				}
			}
		});
	}
	
	
	/**
	 * �÷����������������õ��������������������װ
	 */
	private void createCom() {
		/**
		 * �������
		 */
		// �������������� �������Ϊ��ʽ���֣�����������Ҫ����룩
		JPanel north = new JPanel(new FlowLayout(FlowLayout.LEFT));
		// ��ʼ����������
		this.LbPoint = new JLabel();
		// �����������װ��ǩ
		north.add(this.LbPoint);
		// ��ʼ��������Ϣ�ؼ�
		this.errMsg = new JLabel();
		// ����ǰ��ɫ
		this.errMsg.setForeground(Color.red);
		// ��Ӵ�����Ϣ���������
		north.add(this.errMsg);
		// ����������װ������� �������߽粼�ֵ�����
		this.add(north, BorderLayout.NORTH);
		/**
		 * �м����
		 */
		// �����м������� �������Ϊ��ʽ���֣�����������Ҫ����룩
		JPanel center = new JPanel(new FlowLayout(FlowLayout.LEFT));
		// ��ʼ�������ı������
		this.txName = new JTextField(12);
		// �м��������װ��ǩ����
		center.add(new JLabel("���������Ĵ�����: "));
		// �м��������װ�����
		center.add(this.txName);
		// ����������װ�м����
		this.add(center, BorderLayout.CENTER);
		/**
		 * �ϲ����
		 */
		// ��ʼ����ť����
		this.btnOK = new JButton("ȷ��");
		// �����ϲ�������  �������Ϊ��ʽ���֣�����������Ҫ��ť���ж��룩
		JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER));
		// �ϲ����������װ��ť 
		south.add(btnOK);
		// ������������װ���   �������߽粼�ֵ��ϱ���䴰��
		this.add(south, BorderLayout.SOUTH);
	}
	


}
