package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import dto.Player;


public class DataTest implements Data{
	
	
	public DataTest(HashMap<String,String> param){
		
	}
	
	@Override
	public List<Player> loadData() {
		List<Player> players=new ArrayList<Player>();
		players.add(new Player("����",100));
		players.add(new Player("�ն�",2200));
		players.add(new Player("����",1088));
		players.add(new Player("����",2475));
		players.add(new Player("����",1800));
		return players;
	}

	@Override
	public void saveData(Player players) {
		
	}

	
}
