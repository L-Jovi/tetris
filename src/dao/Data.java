package dao;

import java.util.List;
import dto.Player;


/**
 * ���ݳ־ò�ӿ�
 * 
 * @author J
 */
public interface Data {
	
	/**
	 * �������
	 */
	public List<Player> loadData();
	/**
	 * �洢����
	 */
	public void saveData(Player players);

}
