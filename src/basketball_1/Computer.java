package basketball_1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import com.bean.computer;
import com.dao.ComputerDao_imp;



public abstract class Computer extends GameObject{

	//电脑数量
	public int a=0;
	Random r=new Random();
	//电脑移动
	public int move=0;
	//是否检测到目标
	private boolean ifFindTarget=false;
	
	 public int prex1=0;
	

	public Computer(GameFrame gameFrame) {
		super(gameFrame);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Rectangle getRec() {
		// TODO Auto-generated method stub
		
		return new Rectangle(getX()+230, getY()+80, 55, 130);
	}

	
	@Override
	public void paintSelt(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(getImg(), getX(), getY(), null);
		g.setColor(Color.RED);
		
		g.drawRect(getX()+230, getY()+80, 50, 130);
		//move();
		
	}
	public void create(GameFrame gameFrame,ArrayList<GameObject> cList) {
		
		ComputerDao_imp computerDao_imp=new ComputerDao_imp();
	
		
		if(a!=3) {
			
			if(cList==this.gameFrame.blueList) {
				ComputerBlue cb=new ComputerBlue(gameFrame) ;
				cb.move=a;
				computer com1=computerDao_imp.computer_value(gameFrame.level);
				cb.strength=com1.getStrenth();
				cb.movespeed=com1.getSpeed();
				gameFrame.obList.add(cb);
				gameFrame.allcomputer.add(cb);
				System.out.println("创建成功");
				//gameFrame.blueList.add(cb);
				cList.add(cb);
				
			}else {
				if(a<2) {
					ComputerRed cb=new ComputerRed(gameFrame) ;
					cb.move=a;
					computer com1=computerDao_imp.computer_value2(gameFrame.level);
					cb.strength=com1.getStrenth();
					cb.movespeed=com1.getSpeed();
					gameFrame.obList.add(cb);
					gameFrame.allcomputer.add(cb);
					//gameFrame.blueList.add(cb);
					cList.add(cb);
				}
			
			}
		
			a++;
			
		}
		
	}
	
	public abstract void move(ArrayList<GameObject> oblList);
	
	public void findtarget(ArrayList<GameObject> obList) {
		for(GameObject obj:obList) {
			if(obj.hasball) {
				if(CirclePZ(obj.getRec(), getX(), getY(), 900)) {
					System.out.println("找到目标");
					setTarget(obj);
					setIfFindTarget(true);
				}
			}
			
		}
	
		
	}
	public void movetoTarget() {
		//System.out.println("移动中");
		
		
		double dis=getDis(getX(), getY(), getTarget().getX(),getTarget().getY());
		int xSpeed=(int)(getSpeed()*(getTarget().getX()-getX())/dis);
	  
	    int ySpeed=(int)(getSpeed()*(getTarget().getY()-getY())/dis);
	   prex1=getX();
	   
	    
	    	 this.setX(getX()+xSpeed);
	    	this.setY(getY()+ySpeed);
	    
		   
	}
	public void movetolankuang() {
		setTarget(gameFrame.lankuanglist.get(0));
		double dis=getDis(getX(), getY(), getTarget().getX(),getTarget().getY());
		int xSpeed=(int)(getSpeed()*(getTarget().getX()-getX())/dis);
	  
	    int ySpeed=(int)(getSpeed()*(getTarget().getY()-getY())/dis);
	   prex1=getX();
	   
	    
	    	 this.setX(getX()+xSpeed);
	  
	   
	    	this.setY(getY()+ySpeed);
	    
		
		
	}
	public void movetoball() {
		setTarget(gameFrame.ball111);
		double dis=getDis(getX(), getY(), getTarget().getX(),getTarget().getY());
		int xSpeed=(int)(getSpeed()*(getTarget().getX()-getX())/dis);
	  
	    int ySpeed=(int)(getSpeed()*(getTarget().getY()-getY())/dis);
	   prex1=getX();
	   
	    
	    	 this.setX(getX()+xSpeed);
	  
	   
	    	this.setY(getY()+ySpeed);
	    
		
		
	}
	
	
	
	
	public boolean isIfFindTarget() {
		return ifFindTarget;
	}

	public void setIfFindTarget(boolean ifFindTarget) {
		this.ifFindTarget = ifFindTarget;
	}

}
