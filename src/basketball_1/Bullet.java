package basketball_1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.lang.annotation.Target;
import java.util.ArrayList;

import jmp123.PlayBack;



public class Bullet extends GameObject{
   //�޸ĵ���
	
	//�����ӵ�����ϷԪ��
	GameObject attacker1;
	//Ŀ��
	public GameObject target1;
	  int x1 = 0;
   String img="img/ball.png";
   //�������ж�
   boolean predis1=false;
	//

   
    //�����ж�
	  int pengdi=0;

	    int w1 = 6;

	      int A1 = 55;// ���

	      int t1 = 1;// ʱ��
	
	public Bullet(GameFrame gameFrame,GameObject attacker1,GameObject target1,int speed) {
		super(gameFrame,attacker1.getX(),attacker1.getY());
		System.out.println("�����ߵ�x:"+getX());
		this.attacker1=attacker1;
		this.target1=target1;
		//this.target1.setX(target1.getX()-140);
		//this.target1.setY(target1.getY()-90);
		System.out.println("target��x��"+target1.getX());
		System.out.println("target��y��"+target1.getY());
		setSpeed(speed);
		dq=false;
		gameFrame.player.hasball=false;
		// TODO Auto-generated constructor stub
	}
	public Boolean DangQiu(ArrayList<GameObject> oblList) {
		for(GameObject obj:oblList) {
			if(JuXingPZ(getRec(), obj.getRec())) {
				return true;
			}
		}return false;
		
	}
	
	
	public void move() {
		double dis=getDis(getX(), getY(), target1.getX(),target1.getY());
		double predis=500;
		
		if(dis>predis) {
			predis1=true;
		}
		setImg(img);
		 int xSpeed=(int)(getSpeed()*(target1.getX()-getX())/dis);
		    //System.out.println("����x��"+(target1.getX()-getX()));
		   // System.out.println("����:"+dis);
		    int ySpeed=(int)(getSpeed()*(target1.getY()-getY())/dis);
		 	//System.out.println("���أ�"+pengdi);	
  			
  			
		    x1 =(int)(A1 * Math.sin(w1 * t1));
	      // System.out.println("y���յĵ�ֵ"+(ySpeed+x1));
	        t1 += 1;
	        
		    this.setX(getX()+xSpeed);
		    
		   this.setY(getY()+ySpeed+x1);
		   //�ֵܷ�7�ֻ��ʤ��
		   if(target1.score==7) {
			   
		   }
	   
	   
		//������Ŀ����ײ��������ʧ�����ּ�
		  if(blue_ok&!ifgiveball) {
				if(JuXingPZ(getRec(), target1.getRec())&&!predis1){
					new Thread("bgm") {
						
						public void run() {
							String filename="bgm/score.mp3";
							
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
		
					gameFrame.removeList.add(this);
					System.out.println("��ɫ����ɹ�����һ��");
					 gameFrame.hasscore=true;
					 gameFrame.ifball=true;
					 gameFrame.player.hasball=false;
					 
					blueprescore=target1.bluescore;
					target1.bluescore++;
					System.out.println("Ŀǰ�ܷ�:"+target1.bluescore);
				}else if (JuXingPZ(getRec(), target1.getRec())&&predis1) {
					gameFrame.removeList.add(this);
					new Thread("bgm") {
						
						public void run() {
							String filename="bgm/score.mp3";
							
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
					System.out.println("���ѶȽ��򣬼�����");
					 gameFrame.hasscore=true;
					 gameFrame.ifball=true;
					 gameFrame.player.hasball=false;
					blueprescore=target1.bluescore;
					target1.bluescore++;
					target1.bluescore++;
					
					System.out.println("Ŀǰ�ܷ�:"+target1.bluescore);
				}//�жϵ���
				else if (DangQiu(gameFrame.redbList)) {
					gameFrame.removeList.add(this);
					dq=true;
					 gameFrame.ifball=true;
				} //�������أ�������ʧ�����ӷ�
				  else  if(pengdi>0&&(ySpeed+x1)<0) {
						  gameFrame.hasscore=false;
						  gameFrame.ifball=true;
							 gameFrame.player.hasball=false;
				 			System.out.println("����ʧ��");
				 			gameFrame.removeList.add(this);
				 		}
				  pengdi=x1+ySpeed;
		  }else if(!blue_ok&!ifgiveball){
			   
					if(JuXingPZ(getRec(), target1.getRec())&&!predis1){
						new Thread("bgm") {
							
							public void run() {
								String filename="bgm/score.mp3";
								
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
						gameFrame.removeList.add(this);
						System.out.println("����ɹ�����һ��");
						 gameFrame.hasscore=true;
						 gameFrame.ifball=true;
						 gameFrame.player.hasball=false;
						 
						presocre=target1.score;
						target1.score++;
						System.out.println("Ŀǰ�ܷ�:"+target1.score);
					}else if (JuXingPZ(getRec(), target1.getRec())&&predis1) {
						gameFrame.removeList.add(this);
						System.out.println("���ѶȽ��򣬼�����");
                        new Thread("bgm") {
							
							public void run() {
								String filename="bgm/score.mp3";
								
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
						 gameFrame.hasscore=true;
						 gameFrame.ifball=true;
						 gameFrame.player.hasball=false;
						presocre=target1.score;
						target1.score++;
						target1.score++;
						
						System.out.println("Ŀǰ�ܷ�:"+target1.score);
					}//�жϵ���
					else if (DangQiu(gameFrame.blueList)) {
						gameFrame.removeList.add(this);
						gameFrame.ifball=true;
						gameFrame.player.hasball=false;
						dq=true;
					} //�������أ�������ʧ�����ӷ�
					  else  if(pengdi>0&&(ySpeed+x1)<0) {
							  gameFrame.hasscore=false;
							  gameFrame.ifball=true;
								 gameFrame.player.hasball=false;
					 			System.out.println("����ʧ��");
					 			gameFrame.removeList.add(this);
					 		}
					  pengdi=x1+ySpeed;
			  
		}else  if(ifgiveball){
			if(JuXingPZ(getRec(), target1.getRec())){
				gameFrame.removeList.add(this);
				target1.hasball=true;
				attacker1.hasball=false;
				ifgiveball=false;
				System.out.println("����ɹ�����");
				givemount=1;
				
			}
		}
	
		
		
		//System.out.println("dis��ֵ"+dis); 

	   // System.out.println("x���ٶ�:"+xSpeed);
	   // System.out.println("y���ٶ�:"+ySpeed);
	  
	    
	 
	
	 
	}

	@Override
	public Rectangle getRec() {
		// TODO Auto-generated method stub
		return new Rectangle(getX()+230, getY()+80,10, 10);
	}

	@Override
	public void paintSelt(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(getImg(),getX()+35, getY()-60,null);
		//�ı仭����ɫ
		g.setColor(Color.yellow);
		//����Բ��;���
		g.fillOval(getX()+230, getY()+80,10, 10);
		g.drawRect(getX()+230, getY()+80,10, 10);
		move();
	}
	
	
	

}
