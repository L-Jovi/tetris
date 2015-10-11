package util;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;


/**
 * ��ȡ�������ڴ�С��ʹҪ��ʾ�Ĵ��ھ��з���
 * x y �����������ڱ߿� frame �����Ͻ���ʼ����
 */
public class FrameUtil {
	
	public static void setFrameCenter(JFrame jf){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();
		//Ϊ��ʾ���м�λ��ʹ�ñ��������ȥFrame�������2�õ�λ�ƾ���
		int x = screen.width - jf.getWidth()>>1;
		int y = screen.height - jf.getHeight()>>1 - 32;
		jf.setLocation(x,y);
	}

}
