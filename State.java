package gol;

public class State {
	static int gridNum=Frame.gridNum;
	static boolean[][] Alive;
	static boolean[][] temp=new boolean[gridNum+1][gridNum+1];
	//������һ�����
	public static boolean[][] nextGen()
	{
		Alive=Frame.getIsAlive();   //�õ��������
		int i=0;
		int j=0;
		int n=0;
		for(i=0;i<gridNum;i++) {
			for(j=0;j<gridNum;j++) {
				n=judge(i, j);
				if(Alive[i][j]==true&&(n<2||n>3)) {    //��ϸ���ھӹ��ٻ����������
					temp[i][j]=false;
				}
				else if(Alive[i][j]==true&&(n==2||n==3)) {    //��ϸ���ھ�Ϊ2��3���������
					temp[i][j]=true;
				}
				else if(Alive[i][j]==false&&n==3) {     //��ϸ�����ھ�������3������
					temp[i][j]=true;
				}
				else {
					temp[i][j]=false;
				}
			}
		}
		for(i=0;i<gridNum;i++) {
			for(j=0;j<gridNum;j++) {
				Alive[i][j]=temp[i][j];
			}
		}
		return Alive;
	}
	
	//�ж�ĳ���ھ��Ƿ����
	public static boolean neighbor(int i,int j)  
	{
		if(i>=0&&i<gridNum&&j>=0&&j<gridNum) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//�����ھ���
	public static int judge(int i,int j)
	{
		int n=0;
		if(neighbor(i-1,j-1)==true&&Alive[i-1][j-1]==true) {
			n++;
		}
		if(neighbor(i-1,j)==true&&Alive[i-1][j]==true) {
			n++;
		}
		if(neighbor(i-1,j+1)==true&&Alive[i-1][j+1]==true) {
			n++;
		}
		if(neighbor(i,j-1)==true&&Alive[i][j-1]==true) {
			n++;
		}
		if(neighbor(i,j+1)==true&&Alive[i][j+1]==true) {
			n++;
		}
		if(neighbor(i+1,j-1)==true&&Alive[i+1][j-1]==true) {
			n++;
		}
		if(neighbor(i+1,j)==true&&Alive[i+1][j]==true) {
			n++;
		}
		if(neighbor(i+1,j+1)==true&&Alive[i+1][j+1]==true) {
			n++;
		}
		return n;
	}
	
	public static boolean[][] getIsAlive()
	{
		return nextGen();
	}
}

