package ui;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import config.GameConfig;


/**
 * ������ȫ��ͼƬ
 * 
 * @author J
 */
public class Img {
	
	
	
	private Img(){}
	
	
	/**
	 * ͼƬ·�� 
	 */
	public static final String GRAPHICS_PATH = "Graphics/";
	private static final String DEFAULT_PATH = "default/";
	
	
	/**
	 * ��ô���·��Ϊһ���������뷨���ں��ڿ�����ʱ�ı�Ƥ��   ʵ�ַ������ǸĻ�·��
	 * @param path
	 */
	public static void setSkin(String path){
		String skinPath = GRAPHICS_PATH + path;
		
		// ����ǩ��
		SIGN = new ImageIcon(skinPath + "" + "string/zuozhe.png").getImage();
		// ����ģ�鴰��
		WINDOW = new ImageIcon(skinPath + "window/Windows.png").getImage();
		// ֵ��
		RECT = new ImageIcon(skinPath + "window/rect.png").getImage();
		// "0~9"����ͼƬ     260 36
		NUMBER = new ImageIcon(skinPath + "string/num.png").getImage();
		// ���ڱ���  ���ݿ�
		DB = new ImageIcon(skinPath + "string/shujuku.png").getImage();
		// ���ڱ���  ���ؼ�¼
		DISK = new ImageIcon(skinPath + "string/bendijilu.png").getImage();
		// ȡ����9��С�����ͼƬ��һ���ţ�
		ACT = new ImageIcon(skinPath + "game/rects.png").getImage();
		// ���ڱ���  �ȼ�
		LEVEl = new ImageIcon(skinPath + "string/dengji.png").getImage();
		// ���ڱ���  ����
		POINT = new ImageIcon(skinPath + "string/fenshu.png").getImage();
		// ���ڱ���  ����
		RMLINE = new ImageIcon(skinPath + "string/xiaohang.png").getImage();
		// ��Ӱ��1���أ�
		SHADOW = new ImageIcon(skinPath + "game/shadows.png").getImage();
		
		// "��ʼ"��ť   ���� Button �����ʱ�򷵻�����Ϊ ImageIcon �����Ժ��治�� getImage����
		BTN_START = new ImageIcon(skinPath + "string/kaishi1.png");
		// "����"��ť  ����ͬ��
		BTN_CONFIG = new ImageIcon(skinPath + "string/shezhi1.png");
		// ��ͣ
		PAUSE = new ImageIcon(skinPath + "string/zanting.png").getImage();
		
		// �������ļ����õ����鳤�Ȳ���
		NEXT_ACT = new Image[GameConfig.getSystemConfig().getTypeConfig().size()];
		// ��һ������ͼƬ
		for (int i = 0; i < NEXT_ACT.length; i++) {
			NEXT_ACT[i] = new ImageIcon(skinPath + "game/"+ i +".png").getImage();
		}
		
		// ����ͼƬ����
		// ����ļ���
		File dir = new File(skinPath + "background");
		// �ļ�����
		File[] files = dir.listFiles();
		BG_LIST = new ArrayList<Image>();
		for (File file : files) {
			// �Ѹ��ļ����ļ��ж��޳�����ֻ��ʾͼƬ
			if(!file.isDirectory()){
				BG_LIST.add(new ImageIcon(file.getPath()).getImage());
			}
	    }
		
    }
	
	
	/**
	 * ����ǩ��
	 */
	public static Image SIGN = null;
	/**
	 * ����ģ�鴰��
	 */
	public static Image WINDOW = null;
	/**
	 * ֵ��
	 */
	public static Image RECT = null;
	/**
	 * "0~9"����ͼƬ     260 36
	 */
	public static Image NUMBER = null;
	/**
	 * ���ڱ���  ���ݿ�
	 */
	public static Image DB = null;
	/**
	 * ���ڱ���  ���ؼ�¼
	 */
	public static Image DISK = null;
	/**
	 * ȡ����9��С�����ͼƬ��һ���ţ�
	 */
	public static Image ACT = null;
	/**
	 * ���ڱ���  �ȼ�
	 */
	public static Image LEVEl = null;
	/**
	 * ���ڱ���  ����
	 */
	public static Image POINT = null;
	/**
	 * ���ڱ���  ����
	 */
	public static Image RMLINE = null;
	/**
	 * ��Ӱ��1���أ�
	 */
	public static Image SHADOW = null;
	/**
	 * "��ʼ"��ť
	 * ���� Button �����ʱ�򷵻�����Ϊ ImageIcon �����Ժ��治�� getImage����
	 */
	public static ImageIcon BTN_START = null;
	/**
	 * "����"��ť
	 * ����ͬ��
	 */
	public static ImageIcon BTN_CONFIG = null;
	/**
	 * ��ͣ
	 */
	public static Image PAUSE = null;

	/**
	 * ��һ��ͼƬ����
	 */
	public static Image[] NEXT_ACT = null;
	
	/**
	 * ����
	 */
	public static List<Image> BG_LIST = null;
	
	
	/**
	 * ��ʼ��ͼƬ
	 */
	static {
		setSkin(DEFAULT_PATH);
	}
	
	
	
}
