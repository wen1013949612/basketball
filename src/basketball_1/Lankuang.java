package basketball_1;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Lankuang extends GameObject {

	ArrayList<Lankuang> lKlistArrayList=new ArrayList<>();
	public Lankuang rlankuang;
	public Lankuang llankuang1;
	
	
	
	public Lankuang(GameFrame gameFrame) { 
		
		super(gameFrame);
		//setImg("img/leftLanKuang.png");
		// TODO Auto-generated constructor stub
		lKlistArrayList.add(llankuang1 =new RLankuang(440,200, gameFrame) );
		//lKlistArrayList.add(rlankuang=new RLankuang(220, 120, gameFrame));
		
		
		
	} 
	

	public Lankuang(GameFrame gameFrame, int x,int y) {
		super(gameFrame,x,y);
		//setImg("img/rightLank.png");
		//setAttackTime(1000);
		//setDis(100);
		
		
		
	}
	
	@Override
	public Rectangle getRec() {
		// TODO Auto-generated method stub
		System.out.println("111");
		return new Rectangle(440,200,  90, 70);
	}

	@Override
	public void paintSelt(Graphics g) { 
		//System.out.println("getprex="+getX()+"getRec="+getRec().x);
		//attack(gameFrame.blueList);
		//g.drawImage(getImg(), getX(), getY(), null);
		g.fillOval(440,200, 10, 10);
		g.drawRect(440,200, 90, 70);
		//g.drawOval(getX(), getY(), 2000, 2000);
	}

}
