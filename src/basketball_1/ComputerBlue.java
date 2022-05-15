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
	//Ŀ��
		public GameObject target1;
		boolean turn=false;	
		//�����
		Random r=new Random();
		int movespeed=r.nextInt(10)+10;
		double strength=10;
		int random=r.nextInt(100)-100;
		int randomx=r.nextInt(500)-100;
		int random_throw=r.nextInt(400)+500;
		
		//�ƶ�ͼƬ����
		static String[] imgs=new String[7];
		static String[] imgs2=new String[7];
		//��������ƶ�����
		static String[] hasballmove=new String[7];
		
		//Ͷ����������
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
		
	//ÿ��Ͷ������ȴʱ��
		class next_throw extends Thread{
			public void run() {
				try {
					System.out.println("��ͣ�ɹ�");
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
	//��Ŀ�������ܶ�
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
		           
				     System.out.println("����"+throwcount);
					  setImg(imgs3[throwcount]);
					  throwcount++;
					  if(throwcount==8) {
						  blue_ok=true;
						  attack(gameFrame.lankuanglist);
						  System.out.println("���Ķ���");
						hasball=false;
						
						  throwcount=1;
						// new next_throw().start();
						
					  
				  
			
		  }else {
			
		}
	
		
	}
	//������Ķ���
	public void moveAcition() {
		//������Ķ���
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
	
	//��������˶�
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
				
				//���򷽷�
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
					System.out.println("����ing");
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
		//�뿪��ⷶΧ
		if(!getTarget().hasball||!CirclePZ(getTarget().getRec(), getX(), getY(), 900)) {
			setIfFindTarget(false);    
			//System.out.println("Ŀ�궪ʧ");
		}
		else {
			
			//System.out.println("�����ƶ�");
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
		 Font f1=new Font("����",Font.BOLD,25);
		 Font f2=new Font("����",Font.BOLD,20);
		  g.setFont(f1);
		  g.setColor(Color.BLUE);
		  if(bluescore<7) {
			  if(gameFrame.hasscore&&(bluescore-blueprescore)==2) {
				  g.setFont(f2);
				  g.drawString("���Ѷ�!�����÷�"+bluescore,getX()+220, getY()+75);
				 
			  }
			  else if(gameFrame.hasscore&&(bluescore-blueprescore)==1) {
				  g.setFont(f2);
				  //g.drawString("�з��÷�"+bluescore,getX()+220, getY()+75);
				
			} else if(blue_ok& !gameFrame.hasscore) {
				  g.setFont(f2);
				  g.drawString("��������ʧ��",getX()+220, getY()+75);
				  
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
					JOptionPane.showMessageDialog(null, "���������", "��������",JOptionPane.PLAIN_MESSAGE);  
					
				}
			  gameFrame.userDao_Imp.alter_score(gameFrame.id, gameFrame.user.getScore()+score);
			  gameFrame.userDao_Imp.alter_gold(gameFrame.id, gameFrame.user.getGold()+50);
		  }
		  else {
			  g.drawString("����ʤ�������������Ч",getX()+200, getY()+50);
		}
		  if(blue_ok&dq) {
			  g.drawString("�������£�������Ч",getX()+200, getY()+30);
		  }if(blue_ok&ifrob) {
			  g.drawString("��������ɹ���",getX()+200, getY()+30);
		  }
		
		
		
	}
	
	
}
