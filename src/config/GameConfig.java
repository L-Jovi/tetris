package config;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


/**
 * ��Ϸ������
 * @author J
 */
@SuppressWarnings({ "serial", "resource" })
public class GameConfig implements Serializable{
	
	
	
	private static FrameConfig FRAME_GONFIG = null;
	private static SystemConfig SYSTEM_CONFIG = null;
	private static DataConfig DATA_CONFIG = null;
	
	private static final boolean ISDEBUG = false;
	
	
	/**
	 * ��̬������ȡ����
	 */
	static{
		try {
			// �������˼���ڿ����׶�      false ��ʱ�򲻻�ȥ��  xml �ļ�
			// �����Ҫ�Ĵ���  �Ͱ�  ISDEBUG �ĳ�  true
			if(ISDEBUG){
				// ����XML��ȡ��
				SAXReader reader = new SAXReader();
				// ��ȡXML�ļ�
				Document doc = reader.read("data/cfg.xml");
				// ��� xml �ļ��еĸ��ڵ� game Ԫ��
				Element game = doc.getRootElement();
				// �����������ö��� ����ȡ game Ԫ�ص� frame ��Ԫ�أ�
				FRAME_GONFIG = new FrameConfig(game.element("frame"));
				// ����ϵͳ���� ����ȡ game Ԫ�ص� system ��Ԫ�أ�
				SYSTEM_CONFIG = new SystemConfig(game.element("system"));
				// ����������ʶ��� ����ȡ game Ԫ�ص� data ��Ԫ�أ�
				DATA_CONFIG = new DataConfig(game.element("data"));
			}else{
				// �����Ѿ��洢�������ļ���Ϣ
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/framecfg.dat"));
				FRAME_GONFIG = (FrameConfig)ois.readObject();
				
				ois = new ObjectInputStream(new FileInputStream("data/systemcfg.dat"));
				SYSTEM_CONFIG = (SystemConfig)ois.readObject();
				
				ois = new ObjectInputStream(new FileInputStream("data/datacfg.dat"));
				DATA_CONFIG = (DataConfig)ois.readObject();
				ois.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * ���췽��
	 * 
	 * ������˽�л�֮���޷���������  ����֮��ķ�������ȫ��ʹ�� static ��̬����ֱ�Ӵ���
	 */
	private GameConfig(){
		
	}
	
	
	/**
	 * �ṩ FrameConfig SystemConfig DataConfig ����������� get ����
	 * ����������Ч��װ xml ������Ϣ�������ı�
	 * @return
	 */
	public static FrameConfig getFrameConfig() {
		return FRAME_GONFIG;
	}
	public static SystemConfig getSystemConfig() {
		return SYSTEM_CONFIG;
	}
	public static DataConfig getDataConfig() {
		return DATA_CONFIG;
	}
	
	
	// ===================== ���Զ����� =============================
	/**
	 * ���ǲ��������û����������ļ������Ϣ  �������ǲ�Ҫ���û����������ļ�
	 * 
	 * ���ｫ�����ļ��ö�������ʽд��洢����   ��Ϸ�ܹ��ճ����ص������ļ�  �����û����޷�����
	 * 
	 * Ȼ��Ĺ����ǰ���Ϸ�������õ�����ȫ��ʵ�����л��ӿ�  �������ܱ���ȷ��д��
	 */
//	public static void main(String[] args) throws Exception {
//		@SuppressWarnings("resource")
//		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/framecfg.dat"));
//		oos.writeObject(FRAME_GONFIG);
//		
//		oos = new ObjectOutputStream(new FileOutputStream("data/systemcfg.dat"));
//		oos.writeObject(SYSTEM_CONFIG);
//		
//		oos = new ObjectOutputStream(new FileOutputStream("data/datacfg.dat"));
//		oos.writeObject(DATA_CONFIG);
//	}
	
	
	
}
