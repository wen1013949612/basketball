package basketball_1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


import com.bean.HaveRoles;
import com.bean.Role;
import com.dao.HaveRoleDao_imp;
import com.dao.RoleDao_imp;

import basketball_1.ComputerBlue.next_throw;
import jmp123.PlayBack;

public class role extends GameObject{
	
	//��������
	
	
	//���������ƶ�
	public boolean up,down,left,right;
	public boolean preright=true,preleft=true;
	
	//�������ƶ�ͼƬ����
	static String[] noball_left=new String[7];
	static String[] noball_right=new String[7];
	//����ͼƬ
	 Image ball=Toolkit.getDefaultToolkit().getImage("img/ball.png"); 
	//�ƶ�ͼƬ����
	static String[] imgs=new String[7];
	static String[] imgs2=new String[7];
	//Ͷ����������
	static String[] imgs3=new String[8];
	//��λͼƬ
	int moveCount=1;
	//��λͶ������
	int throwCount=1;
	//Ͷ���ж�
	public boolean throw1=false;
	
	//��ɫλ��
	public int role_x=getX();
	public int role_y=getY();
	//��ͣ������ͼƬ
	Image abilityone;
	Image abilitytwo;
	//��ɫ�б�
	ArrayList<role> rolelist=new ArrayList<>();
	//��ɫͷ��
	Image classical;
	JButton button1=new JButton();
	


	JButton button2=new JButton();
	JButton button3=new JButton();
	//���޴˽�ɫ
	private boolean have_role=false;
	public boolean isHave_role() {
		return have_role;
	}

	public void setHave_role(boolean have_role) {
		this.have_role = have_role;
	}
	//��ɫ����
	
	
	
	//������ȴʱ��
	/*class AttackCd extends Thread{
			public void run() {
				setAttackCoolDown(false);
				//�߳�����
				try {
					Thread.sleep(2000);
				} catch ( Exception e) {
					e.printStackTrace();
				}
			
			   //�߳���ֹ
				this.stop();
			}
		}*/
		
	static {
		for(int i=1;i<7;i++) {
			noball_left[i]="img/2moveleft/"+i+".png";
			
		}for(int i=1;i<7;i++) {
			noball_right[i]="img/2moveright/"+i+".png";
			
		}
		
		
		
		for(int i=1;i<7;i++) {
			imgs[i]="img/moveleft/"+i+".png";
			
		}
		for(int i=1;i<7;i++) {
			imgs2[i]="img/moveright/"+i+".png";
			
		}
		for(int i=1;i<8;i++) {
			imgs3[i]="img/throw/"+i+".png";
			
		}
		
		
	}
	
	
	public void KeyPressed(KeyEvent e) {
		int key=e.getKeyCode();
		if(key==KeyEvent.VK_D) {
			right=true;
		};
		if(key==KeyEvent.VK_A) {
			left=true;
		};
		if(key==KeyEvent.VK_S) {
			down=true;
		};
		if(key==KeyEvent.VK_W) {
			up=true;
		};if(hasball&&key==KeyEvent.VK_SPACE) {
			System.out.println("Ͷ������");
			 throw1=true;
			  
		   }if(!hasball&key==KeyEvent.VK_J&!throw1) {
			    
			   Random r1=new Random();
				double random_rob=r1.nextDouble();
			   double strength1=gameFrame.player.getStrength();
				if(random_rob<(strength1/100)) {
					rob(gameFrame.blueList);
					
				}
			  
		   }
		   if(hasball&key==KeyEvent.VK_K) {
			   ifgiveball=true;
			   if(preleft) {
				   setImg("img/redgive/redleft.png");
			   }else {
				   setImg("img/redgive/redright.png");
			}
			   
			   ArrayList<GameObject> teamArrayList=gameFrame.redbList;
				teamArrayList.remove(this);
			   giveball(teamArrayList);
		   }
		
	   
		
	}
	
	public void KeyReleased(KeyEvent e) {
		int key=e.getKeyCode();
		if(key==KeyEvent.VK_D) {
			right=false;
		};
		if(key==KeyEvent.VK_A) {
			left=false;
		};
		if(key==KeyEvent.VK_S) {
			down=false;
		};
		if(key==KeyEvent.VK_W) {
			up=false;
		};
		if(key==KeyEvent.VK_SPACE) {
			
			
			   
			
		   };
		
	}

	public void move() {
		if(up&&!throw1&&getY()>100) {
			setY(getY()-getSpeed());
			setPrey(getY());
		}
		if(down&&!throw1&&getY()<600) {
			setY(getY()+getSpeed());
			setPrey(getY());
		}if(left&&!throw1&&getX()>250) {
			setX(getX()-getSpeed());
			//setPrex(getX());a
			
		}if(right&&!throw1&&getX()<1300) {
			setX(getX()+getSpeed());
			//setPrex(getX());
		}
		
		
		
		 
		
		//��������ƶ�����
		if(hasball) {
			if(right||preright) {
				preright=true;
				preleft=false;
				
				if(up||down||right) {
					setImg(imgs2[moveCount]);
					moveCount++;
					if(moveCount==7) {
						moveCount=1;
					}
				}
				else {
					setImg("img/ballpose3.png");
				}
			}
			if(left||preleft) {
				preright=false;
				preleft=true;
				if(up||down||left) {
					setImg(imgs[moveCount]);
					moveCount++;
					if(moveCount==7) {
						moveCount=1;
					}
				}
				else {
					setImg("img/ballpose2.png");
				}
			}
			//Ͷ������
			//Ͷ������
			if(throw1) {
				 
			     System.out.println("����"+throwCount);
				  setImg(imgs3[throwCount]);
				  throwCount++;
				  if(throwCount==8) {
					 blue_ok=false;
					  attack(gameFrame.lankuanglist);
					  System.out.println("���Ķ���");
					  throw1=false;
					  throwCount=1;
					 
				  }
			  
		}
		}else {
			if(right||preright) {
				preright=true;
				preleft=false;
				
				if(up||down||right) {
					setImg(noball_right[moveCount]);
					moveCount++;
					if(moveCount==7) {
						moveCount=1;
					}
				}
				else {
					setImg("img/pose3.png");
				}
			}
			if(left||preleft) {
				preright=false;
				preleft=true;
				if(up||down||left) {
					setImg(noball_left[moveCount]);
					moveCount++;
					if(moveCount==7) {
						moveCount=1;
					}
				}
				else {
					setImg("img/pose2.png");
				}
			}
		}
		
		
		
		
	}
	
	public role(GameFrame gameFrame,int x,int y) {
		super(gameFrame);
		setImg("img/pose2.png");
		setX(x);
		setY(y);
		//������ҹ�������͹�������
		setDis(10000);
		setAttackTime(1000);
		rolelist.add(new normal_role(gameFrame));
		rolelist.add(new highrole(gameFrame));
		rolelist.add(new super_role(gameFrame));
	//�õ�ÿ����ɫ������
		RoleDao_imp roleDao_imp=new RoleDao_imp();
	
		for(int i=0;i<rolelist.size();i++) {
			Role role=roleDao_imp.role_value(i);
			rolelist.get(i).setSpeed(role.getSpeed());
			rolelist.get(i).setStrength(role.getStength());
			rolelist.get(i).setPrice(role.getPrice());
			
			
		}
		
		
	}
	
	
	public role(GameFrame gameFrame) {
		super(gameFrame);
		setImg("img/pose2.png");
		setX(500);
		setY(300);
		//������ҹ�������͹�������
		setDis(10000);
		setAttackTime(3000);
	
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public Rectangle getRec() {
		// TODO Auto-generated method stub
		return new Rectangle(getX()+230, getY()+80, 50, 130);
	}

	/*��ӷ��غ���ͣ��ť*/
	public void addButton() {
		score=0;
		ArrayList<JButton> jButtons=new ArrayList<>();
		int a=gameFrame.skills.skillslList.size();
		for(int i=0;i<a;i++) {
			if(gameFrame.skills.skillslList.get(i).isHave_skill()) {
				JButton skillButton=new JButton();
				//skillButton.setBounds(a, a, i, i);
				
				jButtons.add(new JButton());
			}
		}
		
		button1.setSize(60, 50);
		button1.setLocation(50,0);
		button2.setSize(60,50);
		button2.setLocation(120,0);
		button3.setSize(60,50);
		button3.setLocation(190,0);
		button3.setVisible(false);
		button1.addActionListener(new ActionListener() {
			
			//���ؼ�
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			   
			  
			
			  gameFrame.lankuanglist.get(0).bluescore=0;
			  gameFrame.lankuanglist.get(0).blueprescore=0;
			  gameFrame.ifball=true;
			   score=0;
			   presocre=0;
			   
			   gameFrame.i1=1;
				gameFrame.state=3;
				button1.setVisible(false);
				button2.setVisible(false);
			}
		});
		
		//��ͣ
          button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gameFrame.pause=true;
				button2.setVisible(false);
				button1.setVisible(false);
				button3.setVisible(true);
				
				
			}
		});
         //������Ϸ
          button3.addActionListener(new ActionListener() {
  			
  			@Override
  			public void actionPerformed(ActionEvent e) {
  				// TODO Auto-generated method stub
  				gameFrame.pause=false;
  				button2.setVisible(true);
  				button1.setVisible(true);
  				button3.setVisible(false);
  			}
  		});
          
          
          if(gameFrame.i1>0) {
        	  gameFrame.bgmThread.stop();
        	  gameFrame.bgmThread= new Thread("bgm") {
					
					public void run() {
						
						while(true) {
							try {
								//BufferedInputStream buffer=new BufferedInputStream(new FileInputStream(filename));
							    PlayBack bgm111=new PlayBack(new jmp123.output.Audio());
							    bgm111.open(gameFrame.gaming, "");
							    bgm111.start(true);
							} catch (Exception e) {
								// TODO: handle exception
							}}}
				};
			gameFrame.bgmThread.start();
        	 
        		gameFrame.add(button1);
        		gameFrame.add(button2);
        		gameFrame.add(button3);
        		gameFrame.i1--;
          }
	
	}
	
	
	@Override
	public void paintSelt(Graphics g) {
		
		
		// TODO Auto-generated method stub
		 Font f1=new Font("����",Font.BOLD,25);
		 Font f2=new Font("����",Font.BOLD,20);
		  g.setFont(f1);
		  g.setColor(Color.WHITE);
		  g.drawString("��� |", 770, 160);
			g.drawString("����", 870, 160);
			
			 g.drawString(Integer.toString(score), 785, 210);
			 g.drawString(Integer.toString(bluescore), 885, 210);
				g.drawString("����", 870, 160);
		  if(score<7) {
			  if(gameFrame.hasscore&&(score-presocre)==2) {
				  g.setFont(f2);
				  g.drawString("���Ѷ�!�ҷ��÷�"+score,getX()+220, getY()+75);
				 
			  }
			  else if(gameFrame.hasscore&&(score-presocre)==1) {
				  g.setFont(f2);
				  //g.drawString("�ҷ��÷�"+score,getX()+220, getY()+75);
				
			} else if(!gameFrame.hasscore&!blue_ok) {
				  g.setFont(f2);
				  g.drawString("�ҷ�����ʧ��",getX()+220, getY()+75);
				  
			}
			  
			 
			  
		  }
		  else if(score==7){
			  if (gameFrame.outcome) {
				  gameFrame.outcome=false;
					
					new Thread("bgm") {
						
						public void run() {
							gameFrame.bgmThread.stop();
							String filename="bgm/winbgm.MP3";
							
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
					JOptionPane.showMessageDialog(null, "��ϲӮ�±�����", "����ʤ��",JOptionPane.PLAIN_MESSAGE);  
					if(gameFrame.outcome) {
						System.out.println("���ʤ��");
					}
				}
			  gameFrame.userDao_Imp.alter_score(gameFrame.id, gameFrame.user.getScore()+7);
			  if(gameFrame.level==1) {
				  g.drawString("����ʤ������ý�ң�100",getX()+200, getY()+50);
				  gameFrame.userDao_Imp.alter_gold(gameFrame.id, gameFrame.user.getGold()+100);
			  }
			  if(gameFrame.level==2) {
				  g.drawString("����ʤ������ý�ң�200",getX()+200, getY()+50);
				  gameFrame.userDao_Imp.alter_gold(gameFrame.id, gameFrame.user.getGold()+200);
			  }
			  if(gameFrame.level==3) {
				  g.drawString("����ʤ������ý�ң�300",getX()+200, getY()+50);
				  gameFrame.userDao_Imp.alter_gold(gameFrame.id, gameFrame.user.getGold()+300);
			  }
		}
		  else {
			  g.drawString("����ʤ�������������Ч",getX()+200, getY()+50);
		}
		  if(!blue_ok&dq) {
			  g.drawString("�������£�������Ч",getX()+200, getY()+30);
		  }if(!blue_ok&ifrob) {
			  g.drawString("����ɹ���",getX()+200, getY()+30);
		  }
		//����ͼƬ
	
		g.drawImage(getImg(),getX(),getY(),null);
		//�ı仭����ɫ
		g.setColor(Color.GREEN);
		//���ƾ���
		g.drawRect(getX()+230, getY()+80, 50, 130);
	
		//��ͣ�ͷ��ؼ�
		int button_y=100;
		
		g.drawString("����",getX()-30,60);
		g.drawString("��ͣ", getX()+30,60);
		
		move();
	}
    
}
