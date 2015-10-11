package config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.dom4j.Element;


@SuppressWarnings("serial")
public class FrameConfig implements Serializable{
	
	
	
	private final String title; 
	private final int windowUp;
	private final int width;
	private final int height;
	private final int padding;
	private final int border;
	private final int loseIdx;
	/**
	 * ͼ������
	 */
	private final List<LayerConfig> LayersConfig;
	/**
	 * ��ť����
	 */
	private final ButtonConfig buttonConfig;
	
	
	public FrameConfig(Element frame){
		// ���ó��� ����xml�д������ַ�������ǿ��ת��Ϊ������
		// ��ȡ���ڿ��
		this.width = Integer.parseInt(frame.attributeValue("width"));
		// ��ȡ���ڸ߶�
		this.height = Integer.parseInt(frame.attributeValue("height"));
		// ��ȡ�߿��ڱ߾�
		this.padding = Integer.parseInt(frame.attributeValue("padding"));
		// ��ȡ�߿��ϸ
		this.border = Integer.parseInt(frame.attributeValue("border"));
		// ��ȡ����
		this.title = frame.attributeValue("title");
		// ��ȡ���ڰθ�
		this.windowUp = Integer.parseInt(frame.attributeValue("windowUp"));
		// ���������õ����ѷ���ͼƬ
		this.loseIdx = Integer.parseInt(frame.attributeValue("loseIdx"));
		
		/**
		 * ��ȡ frame �Ĵ�����Ԫ�� layer
		 */
		@SuppressWarnings("unchecked")
		List<Element> Layers = frame.elements("layer");
		LayersConfig = new ArrayList<LayerConfig>();
		// ȡ�õ�����������
		for (Element Layer : Layers) {
			LayerConfig lc = new LayerConfig(Layer.attributeValue("className"),
					Integer.parseInt(Layer.attributeValue("x")),
					Integer.parseInt(Layer.attributeValue("y")),
					Integer.parseInt(Layer.attributeValue("w")),
					Integer.parseInt(Layer.attributeValue("h")));
			LayersConfig.add(lc);
		}
		//��ʼ����ť����
		buttonConfig = new ButtonConfig(frame.element("button"));
	}


	/**
	 * �ṩ get ����
	 * @return
	 */
	public String getTitle() {
		return title;
	}
	public int getWindowUp() {
		return windowUp;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getPadding() {
		return padding;
	}
	public int getBorder() {
		return border;
	}
	public List<LayerConfig> getLayersConfig() {
		return LayersConfig;
	}
	public int getLoseIdx() {
		return loseIdx;
	}
	public ButtonConfig getButtonConfig() {
		return buttonConfig;
	}
	
	
	
}
