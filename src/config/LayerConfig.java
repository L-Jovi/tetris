package config;

import java.io.Serializable;


@SuppressWarnings("serial")
public class LayerConfig implements Serializable{
	
	
	
	/**
	 * ����  xml �� layer Ԫ�ص���Ӧ�����Բ���
	 */
	private String className;
	int x;
	int y;
	int w;
	int h;
	
	
	/**
	 * ���췽����ʼ���������Բ�����ֵ
	 * @param className
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public LayerConfig(String className, int x, int y, int w, int h) {
		this.className = className;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	
	/**
	 * Ϊȡ�õ���������ֵ���� get ����
	 * @return
	 */
	public String getClassName() {
		return className;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getW() {
		return w;
	}
	public int getH() {
		return h;
	}

	
	
}
