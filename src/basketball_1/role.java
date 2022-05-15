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
	
	//有无篮球
	
	
	//上下左右移动
	public boolean up,down,left,right;
	public boolean preright=true,preleft=true;
	
	//无篮球移动图片集合
	static String[] noball_left=new String[7];
	static String[] noball_right=new String[7];
	//篮球图片
	 Image ball=Toolkit.getDefaultToolkit().getImage("img/ball.png"); 
	//移动图片集合
	static String[] imgs=new String[7];
	static String[] imgs2=new String[7];
	//投篮动作集合
	static String[] imgs3=new String[8];
	//定位图片
	int moveCount=1;
	//定位投篮动作
	int throwCount=1;
	//投篮判定
	public boolean throw1=false;
	
	//角色位置
	public int role_x=getX();
	public int role_y=getY();
	//暂停，反回图片
	Image abilityone;
	Image abilitytwo;
	//角色列表
	ArrayList<role> rolelist=new ArrayList<>();
	//角色头像
	Image classical;
	JButton button1=new JButton();
	


	JButton button2=new JButton();
	JButton button3=new JButton();
	//有无此角色
	private boolean have_role=false;
	public boolean isHave_role() {
		return have_role;
	}

	public void setHave_role(boolean have_role) {
		this.have_role = have_role;
	}
	//角色生成
	
	
	
	//技能冷却时间
	/*class AttackCd extends Thread{
			public void run() {
				setAttackCoolDown(false);
				//线程休眠
				try {
					Thread.sleep(2000);
				} catch ( Exception e) {
					e.printStackTrace();
				}
			
			   //线程终止
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
			System.out.println("投出篮球");
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
		
		
		
		 
		
		//有篮球的移动方法
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
			//投篮动作
			//投篮动作
			if(throw1) {
				 
			     System.out.println("动作"+throwCount);
				  setImg(imgs3[throwCount]);
				  throwCount++;
				  if(throwCount==8) {
					 blue_ok=false;
					  attack(gameFrame.lankuanglist);
					  System.out.println("最后的动作");
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
		//设置玩家攻击间隔和攻击距离
		setDis(10000);
		setAttackTime(1000);
		rolelist.add(new normal_role(gameFrame));
		rolelist.add(new highrole(gameFrame));
		rolelist.add(new super_role(gameFrame));
	//得到每个角色的属性
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
		//设置玩家攻击间隔和攻击距离
		setDis(10000);
		setAttackTime(3000);
	
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public Rectangle getRec() {
		// TODO Auto-generated method stub
		return new Rectangle(getX()+230, getY()+80, 50, 130);
	}

	/*添加返回和暂停按钮*/
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
			
			//返回键
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
		
		//暂停
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
         //继续游戏
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
		 Font f1=new Font("宋体",Font.BOLD,25);
		 Font f2=new Font("宋体",Font.BOLD,20);
		  g.setFont(f1);
		  g.setColor(Color.WHITE);
		  g.drawString("红队 |", 770, 160);
			g.drawString("蓝队", 870, 160);
			
			 g.drawString(Integer.toString(score), 785, 210);
			 g.drawString(Integer.toString(bluescore), 885, 210);
				g.drawString("蓝队", 870, 160);
		  if(score<7) {
			  if(gameFrame.hasscore&&(score-presocre)==2) {
				  g.setFont(f2);
				  g.drawString("高难度!我方得分"+score,getX()+220, getY()+75);
				 
			  }
			  else if(gameFrame.hasscore&&(score-presocre)==1) {
				  g.setFont(f2);
				  //g.drawString("我方得分"+score,getX()+220, getY()+75);
				
			} else if(!gameFrame.hasscore&!blue_ok) {
				  g.setFont(f2);
				  g.drawString("我方进球失误！",getX()+220, getY()+75);
				  
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
					JOptionPane.showMessageDialog(null, "恭喜赢下比赛！", "比赛胜利",JOptionPane.PLAIN_MESSAGE);  
					if(gameFrame.outcome) {
						System.out.println("玩家胜利");
					}
				}
			  gameFrame.userDao_Imp.alter_score(gameFrame.id, gameFrame.user.getScore()+7);
			  if(gameFrame.level==1) {
				  g.drawString("比赛胜利！获得金币：100",getX()+200, getY()+50);
				  gameFrame.userDao_Imp.alter_gold(gameFrame.id, gameFrame.user.getGold()+100);
			  }
			  if(gameFrame.level==2) {
				  g.drawString("比赛胜利！获得金币：200",getX()+200, getY()+50);
				  gameFrame.userDao_Imp.alter_gold(gameFrame.id, gameFrame.user.getGold()+200);
			  }
			  if(gameFrame.level==3) {
				  g.drawString("比赛胜利！获得金币：300",getX()+200, getY()+50);
				  gameFrame.userDao_Imp.alter_gold(gameFrame.id, gameFrame.user.getGold()+300);
			  }
		}
		  else {
			  g.drawString("比赛胜利！多余进球无效",getX()+200, getY()+50);
		}
		  if(!blue_ok&dq) {
			  g.drawString("该球被拦下！进球无效",getX()+200, getY()+30);
		  }if(!blue_ok&ifrob) {
			  g.drawString("抢球成功！",getX()+200, getY()+30);
		  }
		//绘制图片
	
		g.drawImage(getImg(),getX(),getY(),null);
		//改变画笔颜色
		g.setColor(Color.GREEN);
		//绘制矩形
		g.drawRect(getX()+230, getY()+80, 50, 130);
	
		//暂停和返回键
		int button_y=100;
		
		g.drawString("返回",getX()-30,60);
		g.drawString("暂停", getX()+30,60);
		
		move();
	}
    
}
