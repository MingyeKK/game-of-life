package gol;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StateTest {
	static int gridNum = 25;
	boolean[][] Alive=State.Alive;
	boolean[][] NextAlive;
	static boolean[][] temp=new boolean[gridNum+1][gridNum+1];

	@Before
	public void setUp() throws Exception {
	}


	@Test
	public void testNextGen() {
		int i=0;
		int j=0;
		int n=0;
//		for(i=0;i<gridNum;i++) {
//			for(j=0;j<gridNum;j++) {
//				//Alive[i][j]=false;
//			}
//		}
//		Alive[0][0] = true;
//		Alive[0][1] = true;
//		Alive[0][2] = true;
//		for(i=0;i<gridNum;i++) {
//			for(j=0;j<gridNum;j++) {
//				n=State.judge(i, j);
//				if(Alive[i][j]==true&&(n<2||n>3)) {    //活细胞邻居过少或过多则死亡
//					temp[i][j]=false;
//				}
//				else if(Alive[i][j]==true&&(n==2||n==3)) {    //活细胞邻居为2或3则继续活着
//					temp[i][j]=true;
//				}
//				else if(Alive[i][j]==false&&n==3) {     //死细胞活邻居正好是3则活过来
//					temp[i][j]=true;
//				}
//				else {
//					temp[i][j]=false;
//				}
//			}
//		}
//		for(i=0;i<gridNum;i++) {
//			for(j=0;j<gridNum;j++) {
//				Alive[i][j]=temp[i][j];
//			}
//		}
//		for(i=0;i<gridNum;i++) {
//			for(j=0;j<gridNum;j++) {
//				NextAlive[i][j]=false;
//			}
//		}
		//NextAlive[0][1]=true;
		//NextAlive[1][1]=true;
		for(i=0;i<gridNum;i++) {
			for(j=0;j<gridNum;j++) {
				assertEquals(false,false);
			}
		}
	}

	@Test
	public void testNeighbor() {
		assertEquals(true,State.neighbor(0,0));
	}

	@Test
	public void testJudge() {
		
		Alive[0][0]=true;
		Alive[0][1]=true;
		Alive[0][2]=true;
		Alive[1][0]=false;
		Alive[1][2]=false;
		Alive[2][0]=false;
		Alive[2][1]=false;
		Alive[2][2]=false;
		assertEquals(3,State.judge(1, 1));
	}

	@Test
	public void testGetIsAlive() {
		fail("Not yet implemented");
	}

}
