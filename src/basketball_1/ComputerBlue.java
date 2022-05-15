package basketball_1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JOptionPane;

import jmp123.PlayBack;

public class ComputerBlue extends Computer {
	//目标
		public GameObject target1;
		boolean turn=false;	
		//随机数
		Random r=new Random();
		int movespeed=r.nextInt(10)+10;
		double strength=10;
		int random=r.nextInt(100)-100;
		int randomx=r.nextInt(500)-100;
		int random_throw=r.nextInt(400)+500;
		
		//移动图片集合
		static String[] imgs=new String[7];
		static String[] imgs2=new String[7];
		//有篮球的移动集合
		static String[] hasballmove=new String[7];
		
		//投篮动作集合
		static String[] imgs3=new String[8];
		int throwcount=1;
		
		int moveCount;
		
		static {
			for(int i=1;i<7;i++) {
				imgs[i]="img/2blueleft/"+i+".png";
				
			}
			for(int i=1;i<7;i++) {
				imgs2[i]="img/2blueright/"+i+".png";
				
			}
			for(int i=1;i<7;i++) {
				hasballmove[i]="img/blueleft/"+i+".png";
				
			}
			for(int i=1;i<8;i++) {
				imgs3[i]="img/bluethrow/"+i+".png";
				
			}
			
			
			}
		
	//每次投篮的冷却时间
		class next_throw extends Thread{
			public void run() {
				try {
					System.out.println("暂停成功");
					Thread.sleep(15000);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				this.stop();
			}
		}
	public ComputerBlue(GameFrame gameFrame) {
		super(gameFrame);
		// TODO Auto-generated constructor stub\
		
		setImg("img/Computer/pose2.png");
		setX(1000+randomx);
		setY(150+random);
		setDis(10000);
		setAttackTime(3000);
		//target1=gameFrame.player;
	}
	//无目标随意跑动
	public void onmove() {
		int random1=-10+r.nextInt(20);
		int xSpeed=random1;
	    int ySpeed=random1;
	    prex1=getX();
	    if(this.getX()>100&getX()<1200) {
	    	if(xSpeed>0) {
	    		this.setX(getX()+xSpeed);
	    	}else {
	    		this.setX(getX()-xSpeed);
			}
	    	
	    }else  {
	    	
	    	this.setX(getX()-xSpeed);
		}
	    if(this.getY()>100&getY()<1000) {
	    	this.setY(getY()+ySpeed);
	    }
	    else {
	    	this.setY(getY()-ySpeed);
	    }
		
	}
	
	public void throwball() {
		           
				     System.out.println("动作"+throwcount);
					  setImg(imgs3[throwcount]);
					  throwcount++;
					  if(throwcount==8) {
						  blue_ok=true;
						  attack(gameFrame.lankuanglist);
						  System.out.println("最后的动作");
						hasball=false;
						
						  throwcount=1;
						// new next_throw().start();
						
					  
				  
			
		  }else {
			
		}
	
		
	}
	//无篮球的动作
	public void moveAcition() {
		//无篮球的动作
		if(turn) {
			setImg(imgs2[moveCount]);
			moveCount++;
			if(moveCount==7) {
				moveCount=1;
			}
		}
		if(!turn) {
			setImg(imgs[moveCount]);
			moveCount++;
			if(moveCount==7) {
				moveCount=1;
			}
		}
	 if(getX()>prex1) {
		 turn=true;
	 }
	 else {
		 turn=false;
	 }
	}
	
	//有篮球的运动
	public void moveAction2() {
		setImg(hasballmove[moveCount]);
		moveCount++;
		if(moveCount==7) {
			moveCount=1;
		}
	}
	
	
	
	
	public void move(ArrayList<GameObject> obList) {
    if(hasball) {
    	
			
			if(getX()<=random_throw) {
				throwball();
				
			}else {
				
				//传球方法
				Random r2=new Random();
				double random_give=r2.nextDouble();
				ArrayList<GameObject> teamArrayList=new ArrayList<>();
				Iterator<GameObject> iterator=gameFrame.blueList.iterator();
				while (iterator.hasNext()) {
					GameObject gameObject = (GameObject) iterator.next();
					teamArrayList.add(gameObject);
				}
				
				
				teamArrayList.remove(this);
				if(random_give<0.01) {
					 ifgiveball=true;
					giveball(teamArrayList);
					System.out.println("传球ing");
				}
					movetolankuang();
				if(!ifgiveball) {
					moveAction2();
				}
				
			}
			
		
			
		}	
	else {
		Random r1=new Random();
		double random_rob=r1.nextDouble();
	
		if(random_rob<(strength/100)) {
			rob(gameFrame.redbList);
		}
	if( isIfFindTarget()) {
		//rob(gameFrame.redbList);
		//离开检测范围
		if(!getTarget().hasball||!CirclePZ(getTarget().getRec(), getX(), getY(), 900)) {
			setIfFindTarget(false);    
			//System.out.println("目标丢失");
		}
		else {
			
			//System.out.println("正在移动");
				setSpeed(movespeed);
				if(move==0&gameFrame.ball111.hasball) {
					movetoball();
				}else if (move==0&!gameFrame.ball111.hasball) {
					onmove();
				}
				else if(move!=0){
					movetoTarget();
				}
				moveAcition();
			
		}
		
	}else {
	findtarget(obList);
		onmove();
		moveAcition();
	}
	
	}
		
	 
	   
	   }
	private Thread next_throw() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void paintSelt(Graphics g) {
		// TODO Auto-generated method stub
		super.paintSelt(g);
		move(gameFrame.redbList);
		 Font f1=new Font("宋体",Font.BOLD,25);
		 Font f2=new Font("宋体",Font.BOLD,20);
		  g.setFont(f1);
		  g.setColor(Color.BLUE);
		  if(bluescore<7) {
			  if(gameFrame.hasscore&&(bluescore-blueprescore)==2) {
				  g.setFont(f2);
				  g.drawString("高难度!蓝方得分"+bluescore,getX()+220, getY()+75);
				 
			  }
			  else if(gameFrame.hasscore&&(bluescore-blueprescore)==1) {
				  g.setFont(f2);
				  //g.drawString("敌方得分"+bluescore,getX()+220, getY()+75);
				
			} else if(blue_ok& !gameFrame.hasscore) {
				  g.setFont(f2);
				  g.drawString("蓝方进球失误！",getX()+220, getY()+75);
				  
			}
			  
			 
			  
		  }
		  else if(bluescore==7){
			  if (gameFrame.outcome) {
				  gameFrame.outcome=false;
					
					new Thread("bgm") {
						
						public void run() {
							gameFrame.bgmThread.stop();
							String filename="bgm/losebgm.MP3";
							
								try {
									//BufferedInputStream buffer=new BufferedInputStream(new FileInputStream(filename));
								    PlayBack bgm111=new PlayBack(new jmp123.output.Audio());
								    bgm111.open(filename, "");
								    bgm111.start(true);
								} catch (Exception e) {
									// TODO: handle exception
								}
							
							
						}
					}.start();;
					JOptionPane.showMessageDialog(null, "输掉比赛！", "比赛结束",JOptionPane.PLAIN_MESSAGE);  
					
				}
			  gameFrame.userDao_Imp.alter_score(gameFrame.id, gameFrame.user.getScore()+score);
			  gameFrame.userDao_Imp.alter_gold(gameFrame.id, gameFrame.user.getGold()+50);
		  }
		  else {
			  g.drawString("比赛胜利！多余进球无效",getX()+200, getY()+50);
		}
		  if(blue_ok&dq) {
			  g.drawString("该球被拦下！进球无效",getX()+200, getY()+30);
		  }if(blue_ok&ifrob) {
			  g.drawString("蓝方抢球成功！",getX()+200, getY()+30);
		  }
		
		
		
	}
	
	
}
