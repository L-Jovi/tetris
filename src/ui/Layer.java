package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import config.FrameConfig;
import config.GameConfig;
import dto.GameDto;


/**
 * ���ƴ���
 * @param g ����
 * @author J
 */
public abstract class Layer {
	
	
	
	/**
	 * �ڱ߾�
	 */
	protected static final int PADDING;
	/**
	 * �߿���
	 */
	protected static final int BORDER;
	
	
	static{
		/**
		 * ȡ�� xml �����ļ��� game ��Ԫ�� frame ��     �ڱ߾�   ��   �߿���      ���� 
		 * ���Ѿ��� GameConfig ����������������� get ������ֱ�ӵ��ø÷���ȡ�����úõĲ���ֵ��
		 */
		FrameConfig fCfg = GameConfig.getFrameConfig();
		PADDING = fCfg.getPadding();
		BORDER = fCfg.getBorder();
	}
	
	
	/**
	 * ��Ϸ8���������  ��Ⱥ͸߶�
	 */
	private static int WINDOW_W = Img.WINDOW.getWidth(null);
	private static int WINDOW_H = Img.WINDOW.getHeight(null);
	/**
	 * ������Ƭ�Ŀ�Ⱥ͸߶�
	 */
	protected static final int IMG_NUMBER_W = Img.NUMBER.getWidth(null)/10;
	private static final int IMG_NUMBER_H = Img.NUMBER.getHeight(null);
	/**
	 * ֵ�۸߶�
	 */
	protected static final int IMG_RECT_H=15;
	/**
	 * ֵ�ۿ�ȣ������
	 */
	private static final int IMG_RECT_W = Img.RECT.getWidth(null);
	/**
	 * ����ֵ�ۿ��
	 */
	private final int RectW;
	/**
	 * "��һ��"����
	 */
	private static final Font DEF_FONT = new Font("����", Font.BOLD, 15);
	/**
	 * ����x y w h
	 */
	/*�������Ͻ�x����*/
	protected final int x;
	/*�������Ͻ�y����*/
	protected final int y;
	/*���ڿ��*/
	protected final int w;
	/*���ڸ߶�*/
	protected final int h;
	/*��Ϸ����*/
	protected GameDto dto=null;
	
	
	/**
	 * ���췽����ʼ��
	 */
	protected Layer(int x,int y,int w,int h){
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		RectW=this.w - PADDING*3;
	}
	
	
	/**
	 * ���ƴ���
	 * @param g ���ʶ���
	 */
	protected void createWindow(Graphics g){
		/**
		 * ��Ƭ����
		 * ǰ�ĸ���ֵ������ʾ��λ��������ĩ
		 * ���ĸ���ֵ��������ͼƬ��Ƭ��������ĩ
		 */
		//����
		g.drawImage(Img.WINDOW, x, y, x+BORDER, y+BORDER, 0, 0, BORDER, BORDER, null);
		//����
		g.drawImage(Img.WINDOW, x+BORDER, y, x+w-BORDER, y+BORDER, BORDER, 0, WINDOW_W-BORDER, BORDER, null );
		//����
		g.drawImage(Img.WINDOW, x+w-BORDER, y, x+w, y+BORDER, WINDOW_W-BORDER, 0, WINDOW_W, BORDER, null);
		//����
		g.drawImage(Img.WINDOW, x, y+BORDER, x+BORDER, y+h-BORDER, 0, BORDER, BORDER, WINDOW_H-BORDER, null);
		//��
		g.drawImage(Img.WINDOW, x+BORDER, y+BORDER, x+w-BORDER, y+h-BORDER, BORDER, BORDER, WINDOW_W-BORDER, WINDOW_H-BORDER, null);
		//����
		g.drawImage(Img.WINDOW, x+w-BORDER, y+BORDER, x+w, y+h-BORDER, WINDOW_W-BORDER, BORDER, WINDOW_W, WINDOW_H-BORDER, null);
		//����
		g.drawImage(Img.WINDOW, x, y+h-BORDER, x+BORDER, y+h, 0, WINDOW_H-BORDER, BORDER, WINDOW_H, null);
		//����
		g.drawImage(Img.WINDOW, x+BORDER, y+h-BORDER, x+w-BORDER, y+h, BORDER, WINDOW_H-BORDER, WINDOW_W-BORDER, WINDOW_H, null);
		//����
		g.drawImage(Img.WINDOW, x+w-BORDER, y+h-BORDER, x+w, y+h, WINDOW_W-BORDER, WINDOW_H-BORDER, WINDOW_W, WINDOW_H, null);
	}

	
	public void setDto(GameDto dto) {
		this.dto = dto;
	}
	
	
	/**
	 * ��ʾ���ֵķ���
	 * 
	 * @param x ���Ͻ�x����
	 * @param y ���Ͻ�y����
	 * @param num Ҫ��ʾ������
	 * @param bitCount ����λ��
	 * @param g ���ʶ���
	 */
	protected void drawNumberLeftPad(int x,int y,int num,int maxBit,Graphics g){
		//��Ҫ��ӡ������ת��Ϊ�ַ���
		String strNum = Integer.toString(num);
		//ѭ����������
		for (int i = 0; i < maxBit; i++) {
			//�ж��Ƿ������������
			if(maxBit-i <= strNum.length()){
				//����������ַ����е��±�
				int idx = i-maxBit+strNum.length();
				//�������е�ÿһλȡ��
				int bit = strNum.charAt(idx)-'0';
				//��������
				g.drawImage(Img.NUMBER, 
						this.x+x+IMG_NUMBER_W*i, this.y+y, 
						this.x+x+IMG_NUMBER_W*(i+1), this.y+y+IMG_NUMBER_H, 
						bit * IMG_NUMBER_W, 0, 
						(bit+1)* IMG_NUMBER_W, IMG_NUMBER_H, 
						null);
			}
		}
	}
	
	
	/**
	 * ֵ�ۣ�����ֵ����ɫ��Ⱥ���ɫ���䣩
	 */
	protected void drawRect(int y, String title, String number, double percent, Graphics g){
		//�������ظ������ֵ��ȡ�����Թ�ʹ��
		int rect_x=this.x+PADDING*2;
		int rect_y=this.y+y-PADDING;
		//���Ʋ�ֵ��������������������"ֵ��"��ͼ��
		g.setColor(Color.BLACK);
		g.fillRect(rect_x, rect_y, this.RectW, IMG_RECT_H+4);
		g.setColor(Color.WHITE);
		g.fillRect(rect_x +1, rect_y +1, this.RectW-2, IMG_RECT_H+2);
		g.setColor(Color.BLACK);
		g.fillRect(rect_x +2, rect_y +2, this.RectW-4, IMG_RECT_H);
		/**
		 * ����ֵ��
		 */
		//���ֵ�ۿ��
		int w=(int)((percent * (this.RectW-4)));
		//�����ɫ��ע�����-1Ϊ�˽�������¼�ķ�����ʾ��Ϊ���������-1�Ļ�ֵ�ۻ���ʾ�գ�
		int subIdx=(int)(percent * IMG_RECT_W) -1;
		//��ֵ������
		g.drawImage(Img.RECT,
				rect_x +2, rect_y +2, 
				rect_x +w+2, rect_y +2+IMG_RECT_H, 
				subIdx, 0,
				subIdx+1, IMG_RECT_H, 
				null);
		Color MY_COLOR = new Color(0xF5F5F5);
		g.setColor(MY_COLOR);
		g.setFont(DEF_FONT);
		g.drawString(title, rect_x+2, rect_y+15);
		if(number!=null){
			g.drawString(number, rect_x+165, rect_y+15);
		}
	} 
	
	
	/**
	 * ��ʾ���л�ͼ����
	 */
	protected void drawImageAtCenter(Image img,Graphics g){
		int imgW=img.getWidth(null);
		int imgH=img.getHeight(null);
		g.drawImage(img, this.x+(this.w-imgW>>1), this.y+(this.h-imgH>>1), null);
	}
	
	
	/**
	 * @author J
	 * @param g ����
	 * ˢ����Ϸ��������   ��������󷽷���Ϊ��������þ�����Ϊ
	 * */
	abstract public void paint(Graphics g);
	
	
	
}