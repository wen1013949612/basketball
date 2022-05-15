package basketball_1;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;

import jmp123.PlayBack;

public abstract class GameObject {
	//����
	private int x;
	private int y;
	//
	private int prex;
	private int prey;
   
	//ͼƬ
	Image img;
	
	//��Ϸ����
	GameFrame gameFrame;
	//�ٶ�
	private int speed=50;
	//����
	private int strength;
	//�۸�
	private int price;
	//��������
	private String name;
	
	//�Ƿ�������������
	public boolean hasball=false;
	
	//�������֮�����
	public double getDis(int x1,int y1,int x2,int y2) {
		return Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2));
	}
	//����Ŀ��
	private GameObject target;
	//�Ƿ���
	static public boolean dq=false;
	
	//�Ƿ���Ŀ��
	private boolean hasTarget=false;
	//��������
	private int dis;
	//����ʱ����
	private int attackTime;
	//�����Ƿ���ȴ
	private boolean attackCoolDown=true;
	//�жϵ����Ƿ���Ի����
	static public boolean  ifcomputer=true;
	//���ֵ���
	static	public int score=0;
	static public int presocre=0;
	//���˻��ֵ���
	static public int bluescore=0;
	static public int blueprescore=0;
	//�ж����Ǹ����������
    static boolean  blue_ok=false;
   //��������Ƿ�ɹ�
    static boolean ifrob=false;
    //�Ƿ��Ǵ�����
    static boolean ifgiveball=false;
    //����ֻ�ܴ�һ��
    static int givemount=1;
   
	public GameObject(GameFrame gameFrame) {
		this.gameFrame=gameFrame;
		
	}
	public GameObject(GameFrame gameFrame,int x,int y) {
		
		this.x=x;
		this.y=y;
		this.gameFrame=gameFrame;
	}
	//������ײ���
	public boolean JuXingPZ(Rectangle r1,Rectangle r2) {
		return r1.intersects(r2);
}	
	//Բ����ײ���
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
	
	//��������
	public void attack(ArrayList<GameObject> gameObList) {
		for(GameObject obj:gameObList) {
			//�жϹ�����Χ
	
			if(CirclePZ(obj.getRec(), getX(), getY(), getDis())) {
				//�ҵ�Ŀ��
				target=obj; 
				obj.setX(270);
				obj.setY(150);
			
				hasTarget=true;
				System.out.println("������������������������������������������������������������������������������������");

				//����ѭ��
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
				
				
				System.out.println("���䷢��!!");
				gameFrame.obList.add(bullet);
				//Bullet bullet=new Bullet(gameFrame,this,getTarget(),20);
				//gameFrame.obList.add(bullet);
			    
				//�߳̿�ʼ
				new AttackCd().start();
				
			}
		}
			//�����б�
			
				//����Ƿ��ڹ�����Χ��
				//����Ƿ��ڹ�����Χ��
				//����Ƿ��ڹ�����Χ��
			
		
		
	}
	
	
	//���򷽷�
	public void rob(ArrayList<GameObject> gameObList) {
		 //�Ƿ�����ɹ�
	    Random r1=new Random();
	    Double rob_ball=r1.nextDouble();
	    
	        if(rob_ball<0.3) {
	        	
	        	 for(GameObject obj:gameObList) {
					 if(obj.hasball==true) {
						 if(JuXingPZ(getRec(), obj.getRec()))
						 {
							 gameFrame.player.throw1=false;
							 System.out.println("����ɹ�");
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
				//System.out.println("����ʧ��");
			}
			
		
	} 
	//������
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
				System.out.println("����!!");
				gameFrame.obList.add(bullet);
			
				
				
				}
			}
			
			
		}
		
		
	}
	
	
	
	
	//������ȴʱ��
	class AttackCd extends Thread{
		public void run() {
			setAttackCoolDown(false);
			//�߳�����
			try {
				Thread.sleep(attackTime);
			} catch ( Exception e) {
				e.printStackTrace();
			}
			//��������������Ϊ����״̬
			setAttackCoolDown(true);
		   //�߳���ֹ
			this.stop();
		}
	}
	
	
	//�̳в��Һ�����ײ���
	public abstract Rectangle getRec();
	//������ϷԪ��
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
