package test;

public class Demon {
	public static void main(String[] args){
		���� A=new ���ٷ�("����ķ");
		���� B=new թƭ��("С��ʲ");
		//ǿ������
		���ٷ� C=(���ٷ�)A;
		C.dosomething();
		//ǿ������ת��
		int a=(int)2.25;
	}

}


class ����{
	protected String name;	
	public ����(String name){
		this.name=name;	
	}	
	public void dosomething(){
		System.out.println("�ҽ�" +this.name+ "����һ������" );
	}
}

class ���ٷ� extends ����{
	public ���ٷ�(String name){
		super(name);
		}
		public void dosomething(){
			System.out.println("�ҽ�" +this.name+ "����һ�����ٷ�");
		}	
}

class թƭ�� extends ����{
	public թƭ��(String name){
		super(name);
	}
	public void dosomething(){
		System.out.println("�ҽ�" +this.name+ "����һ��թƭ��");
	}
}
