package util;


public class GameFunction {

	/**
	 * �����߳�˯��ʱ��
	 */
	public static long getSleepTimeByLevel(int level){
		long sleep = (-200*level + 1000);
		sleep = sleep<100 ? 100 : sleep;
		return sleep;
	}
	
}
