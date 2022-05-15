package basketball_1;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;

import jmp123.PlayBack;

public abstract class GameObject {
	//坐标
	private int x;
	private int y;
	//
	private int prex;
	private int prey;
   
	//图片
	Image img;
	
	//游戏界面
	GameFrame gameFrame;
	//速度
	private int speed=50;
	//力量
	private int strength;
	//价格
	private int price;
	//技能名字
	private String name;
	
	//是否有篮球在手上
	public boolean hasball=false;
	
	//获得两点之间距离
	public double getDis(int x1,int y1,int x2,int y2) {
		return Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2));
	}
	//攻击目标
	private GameObject target;
	//是否挡球
	static public boolean dq=false;
	
	//是否有目标
	private boolean hasTarget=false;
	//攻击距离
	private int dis;
	//攻击时间间隔
	private int attackTime;
	//攻击是否冷却
	private boolean attackCoolDown=true;
	//判断电脑是否可以获得球
	static public boolean  ifcomputer=true;
	//积分得数
	static	public int score=0;
	static public int presocre=0;
	//敌人积分得数
	static public int bluescore=0;
	static public int blueprescore=0;
	//判断是那个队伍进了球
    static boolean  blue_ok=false;
   //玩家抢球是否成功
    static boolean ifrob=false;
    //是否是传球功能
    static boolean ifgiveball=false;
    //传球只能传一次
    static int givemount=1;
   
	public GameObject(GameFrame gameFrame) {
		this.gameFrame=gameFrame;
		
	}
	public GameObject(GameFrame gameFrame,int x,int y) {
		
		this.x=x;
		this.y=y;
		this.gameFrame=gameFrame;
	}
	//矩形碰撞检测
	public boolean JuXingPZ(Rectangle r1,Rectangle r2) {
		return r1.intersects(r2);
}	
	//圆形碰撞检测
	public boolean CirclePZ(Rectangle r,int x,int y,int dis) {
		if (getDis(x, y, r.x, r.y)<dis
			||getDis(x, y, r.x+r.width, r.y)<dis
			||getDis(x, y, r.x, r.y+r.height)<dis
			||getDis(x, y, r.x+r.width, r.y+r.height)<dis) 
		{
			return true;
		}
		return false;
	}
	
	//攻击方法
	public void attack(ArrayList<GameObject> gameObList) {
		for(GameObject obj:gameObList) {
			//判断攻击范围
	
			if(CirclePZ(obj.getRec(), getX(), getY(), getDis())) {
				//找到目标
				target=obj; 
				obj.setX(270);
				obj.setY(150);
			
				hasTarget=true;
				System.out.println("扎到了扎到了扎到了扎到了扎到了扎到了扎到了扎到了扎到了扎到了扎到了扎到了扎到了扎到了");

				//跳出循环
				break;
			}
		
		}
		if(hasTarget) {
			if(!CirclePZ(target.getRec(), getX(),getY(),getDis())) {
				setHasTarget(false);
			}
			
			else if(isAttackCoolDown()) {
				Bullet bullet=null;
				
					 bullet = new Bullet(gameFrame, this,this.getTarget(),50);;
				
				
				System.out.println("发射发射!!");
				gameFrame.obList.add(bullet);
				//Bullet bullet=new Bullet(gameFrame,this,getTarget(),20);
				//gameFrame.obList.add(bullet);
			    
				//线程开始
				new AttackCd().start();
				
			}
		}
			//遍历列表
			
				//玩家是否在攻击范围内
				//玩家是否在攻击范围内
				//玩家是否在攻击范围内
			
		
		
	}
	
	
	//抢球方法
	public void rob(ArrayList<GameObject> gameObList) {
		 //是否抢球成功
	    Random r1=new Random();
	    Double rob_ball=r1.nextDouble();
	    
	        if(rob_ball<0.3) {
	        	
	        	 for(GameObject obj:gameObList) {
					 if(obj.hasball==true) {
						 if(JuXingPZ(getRec(), obj.getRec()))
						 {
							 gameFrame.player.throw1=false;
							 System.out.println("抢球成功");
							 new Thread("bgm") {
									
									public void run() {
										String filename="bgm/rob.mp3";
										
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
							 ifrob=true;
							obj.hasball=false;
							hasball=true;
							if(obj instanceof ComputerBlue) {
								 blue_ok=false;
							}else {
								 blue_ok=true;
							}
							
							
							
						 }
						 
					 }
					 
					 
					 
				 } 
	        }else {
				//System.out.println("抢球失误");
			}
			
		
	} 
	//传球功能
	public void giveball(ArrayList<GameObject> gameObList) {
		for(GameObject obj:gameObList) {
			
			if(hasball&givemount==1) {
				if(CirclePZ(this.getRec(), obj.getX(), obj.getY(), 600)) {
					this.hasball=false;
					givemount--;
					new Thread("bgm") {
						
						public void run() {
							String filename="bgm/give.MP3";
							
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
					Bullet bullet=null;
					 bullet = new Bullet(gameFrame, this,obj,50);;
				System.out.println("传球!!");
				gameFrame.obList.add(bullet);
			
				
				
				}
			}
			
			
		}
		
		
	}
	
	
	
	
	//技能冷却时间
	class AttackCd extends Thread{
		public void run() {
			setAttackCoolDown(false);
			//线程休眠
			try {
				Thread.sleep(attackTime);
			} catch ( Exception e) {
				e.printStackTrace();
			}
			//将攻击功能设置为攻击状态
			setAttackCoolDown(true);
		   //线程终止
			this.stop();
		}
	}
	
	
	//继承并且后期碰撞检测
	public abstract Rectangle getRec();
	//绘制游戏元素
	public abstract void paintSelt(Graphics g);
	
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Image getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = Toolkit.getDefaultToolkit().getImage(img);
	}
	public GameFrame getGameFrame() {
		return gameFrame;
	}
	public void setGameFrame(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public GameObject getTarget() {
		return target;
	}
	public void setTarget(GameObject target) {
		this.target = target;
	}
	public boolean isHasTarget() {
		return hasTarget;
	}
	public void setHasTarget(boolean hasTarget) {
		this.hasTarget = hasTarget;
	}
	public int getDis() {
		return dis;
	}
	public void setDis(int dis) {
		this.dis = dis;
	}
	public int getAttackTime() {
		return attackTime;
	}
	public void setAttackTime(int attackTime) {
		this.attackTime = attackTime;
	}
	public boolean isAttackCoolDown() {
		return attackCoolDown;
	}
	public void setAttackCoolDown(boolean attackCoolDown) {
		this.attackCoolDown = attackCoolDown;
	}
	
	public int getPrex() {
		return prex;
	}
	public void setPrex(int prex) {
		this.prex = prex;
	}
	public int getPrey() {
		return prey;
	}
	public void setPrey(int prey) {
		this.prey = prey;
	}
	public int getStrength() {
		return strength;
	}
	public void setStrength(int strength) {
		this.strength = strength;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
