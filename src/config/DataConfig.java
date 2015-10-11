package config;

import java.io.Serializable;
import org.dom4j.Element;


@SuppressWarnings("serial")
public class DataConfig implements Serializable{
	
	

	private final int maxRow;
	private DataInterfaceConfig dataA;
	private DataInterfaceConfig dataB;
	
	public DataConfig(Element data){
		this.maxRow = Integer.parseInt(data.attributeValue("maxRow"));
		this.dataA = new DataInterfaceConfig(data.element("dataA"));
		this.dataB = new DataInterfaceConfig(data.element("dataB"));
		data.element("dataB");
		
	}

	/**
	 * �ṩ DataInterfaceConfig ������� get ����
	 * �������� GameConfig �����ṩ�������� get ������ͬ����Ϊ���ǲ�ϣ���ı䴰��������⡢��Ȼ��߸߶���Щ��Ϣ������ data Ԫ�ص���Ԫ��   dataA �� dataB ����Ϣ��Ҫ��ʱ�ı�ģ�
	 * @return
	 */
	public DataInterfaceConfig getDataA() {
		return dataA;
	}
	public DataInterfaceConfig getDataB() {
		return dataB;
	}
	public int getMaxRow() {
		return maxRow;
	}

	
}
