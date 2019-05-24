package gol;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Frame extends JPanel implements MouseListener,Runnable,ActionListener,MouseMotionListener{
	int length=505;   //界面长度
	int width=600;    //界面宽度
	int x=500;       //界面位置
	int y=250;
	static int gridNum=25;    //格子数
	static int gridSize=20;   //格子大小
	static int maxGridSize=gridNum*gridSize;    //面板的边界值
	static boolean[][] isAlive=new boolean[gridNum+1][gridNum+1];   //细胞存活状态
	JPanel p=new JPanel();
	JButton btn1=new JButton("开始");
	JButton btn2=new JButton("暂停");
	JButton btn3=new JButton("重置");
	boolean running=false;
	Thread myThread;
	JFrame frame;
	
	public Frame() 
	{
		frame=new JFrame("生命游戏");
		frame.setBounds(x,y,length,width);
		btn1.setBounds(70,maxGridSize+10,60,45);
		btn2.setBounds(220,maxGridSize+10,60,45);
		btn3.setBounds(370,maxGridSize+10,60,45);
		btn2.setEnabled(false);
		btn3.setEnabled(false);
		frame.add(btn1);
		frame.add(btn2);
		frame.add(btn3);
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setLayout(null);
		setBounds(0,0,maxGridSize+1, maxGridSize+1);    //放置面板
		frame.add(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
	}

	//初始化  画格子
	public void paint(Graphics g)
	{
		g.setColor(Color.LIGHT_GRAY);
		for(int i=0;i<=gridNum;i++) {
			g.drawLine(0, i*gridSize, maxGridSize, i*gridSize);
		}
		for(int i=0;i<=gridNum;i++) {
			g.drawLine(i*gridSize, 0, i*gridSize, maxGridSize);
		}
	}
	
	public void run()
	{
		while(running) {
			update();
			try {
				Thread.sleep(300L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				break;
			}
		}
	}
	
	//细胞存活 将格子置黑
	public void fill(int px,int py) 
	{
		int n=py/gridSize;
		int m=px/gridSize;
		Graphics g=this.getGraphics();
		if(isAlive[n][m]==true) {
			g.setColor(Color.BLACK);
			g.fillRect(m*gridSize+1, n*gridSize+1, gridSize-1,gridSize-1);
		}
	
	}
	
	public void update()
	{
		isAlive=State.getIsAlive();   //获取下一代细胞状态
		Graphics g=this.getGraphics();
		for(int i=0;i<gridNum;i++) {
			for(int j=0;j<gridNum;j++) {
				if(isAlive[i][j]==true) {   //细胞活着
					g.setColor(Color.BLACK);
					g.fillRect(j*gridSize+1, i*gridSize+1, gridSize-1,gridSize-1);
				}
				else {
					g.setColor(Color.WHITE);
					g.fillRect(j*gridSize+1, i*gridSize+1, gridSize-1,gridSize-1);
				}
			}
		}
	}
	
	public void mousePressed(MouseEvent e) {
		int px=e.getX();
		int py=e.getY();
		if(px>=0&&px<=maxGridSize&&py>=0&&py<=maxGridSize)  
		{
			isAlive[py/gridSize][px/gridSize]=true;   //改变逻辑值
			fill(px,py);
		}
	}
	public void mouseDragged(MouseEvent e) {
		 mousePressed(e); 	
	}
	public void mouseMoved(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn1) {
			myThread=new Thread(this);
			running=true;
			myThread.start();
			btn1.setEnabled(false);
			btn2.setEnabled(true);
			btn3.setEnabled(true);
			btn1.setText("继续");
		}
		else if(e.getSource()==btn2) {
			running=false;
			myThread.interrupt();
			btn1.setEnabled(true);
			btn2.setEnabled(false);
		}
		else if(e.getSource()==btn3) {
			for(int i=0;i<gridNum;i++) {
				for(int j=0;j<gridNum;j++) {
					isAlive[i][j]=false;
				}
			}
			btn1.setEnabled(true);
			btn2.setEnabled(false);
			btn3.setEnabled(false);
			btn1.setText("开始");
			myThread.interrupt();
			update();
		}	
	}
	
	public static boolean[][] getIsAlive()
	{
		return isAlive;
	}
	
	public static int getGridNum()
	{
		return gridNum;
	}
	
	public static int getmaxGridSize()
	{
		return maxGridSize;
	}
}
