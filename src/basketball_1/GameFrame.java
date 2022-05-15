package basketball_1;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Menu;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.ChangedCharSetException;
import javax.swing.text.View;

import com.bean.HaveRoles;
import com.bean.User;
import com.dao.AchieveDao;
import com.dao.HavaSkillDao_imp;
import com.dao.HaveRoleDao_imp;
import com.dao.UserDao_Imp;
import com.jdbc.JDBC;

import jmp123.PlayBack;
import jmp123.gui.Player;


public class GameFrame extends JFrame {
	
	
	
	
	//暂停
	static public boolean pause=false;
	//金币数量
	private int gold=305;
	

	//游戏模式 0选择 6登录
	public int state=6;
	//篮球图片
   Image ball=Toolkit.getDefaultToolkit().getImage("img/ball.png"); 
	//背景音乐的关闭
   private static volatile boolean stop=false;
    //窗口尺寸
	private int windowwidth=1000;
	private int windowheight=861;
	//双缓冲
	private Image offScreenImage=null;
	//登陆背景图片
	Image dl=Toolkit.getDefaultToolkit().getImage("img/dl.png");
	//背景音乐
	String filename="bgm/bgm1.mp3";
	//游戏中的音乐
	String gaming="bgm/gaming.mp3";
	static int changemusic=1;
	//开始背景图片
	Image begin1=Toolkit.getDefaultToolkit().getImage("img/begin1.png");
	//游戏背景
	BackGround backGround=new BackGround(this);
	public static int Changemap=1;
	//开始按钮图片
	Image img1=Toolkit.getDefaultToolkit().getImage("img/start.png");
	//按钮
	static public int i1=1;	
	static public int i2=2;
	
	 //是否进球
	static  public boolean hasscore=true;
	//技能元素列表
	Skill skills=new Skill(this,10,10);
	
   //玩家
	role player;
	role playersRoles=new normal_role(this,700,100);
	//玩家的ID
	public static int id; 
	UserDao_Imp userDao_Imp=new UserDao_Imp();
	 User user;
	//电脑玩家
	ComputerBlue c1=new ComputerBlue(this);
	ComputerRed c2=new ComputerRed(this);
	//电脑的难度选择
	static public int level=1;
	
	//篮筐
	Lankuang lankuang=new Lankuang(this);
	//篮球
	basketball ball111=new basketball(this);
	 //是否绘制篮球图片
	static boolean ifball=true;
	
	//游戏元素列表
	//有红色，蓝色方
	ArrayList<GameObject> obList=new ArrayList<GameObject>();
	ArrayList<GameObject> redbList=new ArrayList<GameObject>();
	ArrayList<GameObject> blueList=new ArrayList<GameObject>();
	ArrayList<GameObject> removeList=new ArrayList<>();//删除元素
	ArrayList<GameObject> lankuanglist =new ArrayList<>();	
	ArrayList<GameObject> allcomputer=new ArrayList<>();
	
	//只弹出一次比赛结果弹窗
	static boolean outcome=true;
	Thread openThread= new Thread("bgm") {
		
		public void run() {
			String filename="bgm/bgm1.mp3";
			while(!stop) {
				try {
					//BufferedInputStream buffer=new BufferedInputStream(new FileInputStream(filename));
				    PlayBack bgm111=new PlayBack(new jmp123.output.Audio());
				    bgm111.open(filename, "");
				    bgm111.start(true);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
				
			
			
		}
	};
	
	Thread bgmThread;
	
	public void launch() {
		setLayout(null);
		//设置尺寸
		setSize(windowwidth, windowheight);
		//窗口居中
		setLocationRelativeTo(null);
		//关闭事件
		setDefaultCloseOperation(3);
		//用户不能调整大
		setResizable(false);
		//标题
		setTitle("篮球游戏");
		//窗口可见
		setVisible(true);
		
		openThread.start();
		
		//添加键盘监视器
		this.addKeyListener(new GameFrame.KeyMonitor());
		//添加游戏元素
		     lankuanglist.addAll(lankuang.lKlistArrayList);
	
		
		while(true) {
			   // gameFrame1.start();
			
			//c2.create(this, redbList);
				repaint();
				try {
					Thread.sleep(120);
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			
			
			
		}
		
	}
	
	public void paint(Graphics g) {
		//System.out.println(player.getX()+"  "+player.getY());
		if(offScreenImage==null) {
			offScreenImage=this.createImage(6000, 5000);
		}
		//获取画笔
		Graphics gImage=offScreenImage.getGraphics();
		if (state==1) {
			
			c1.create(this, blueList);
			c2.create(this, redbList);
			requestFocus();
			//分数界面
			
			if(ifball&player.score<7&player.bluescore<7) {
				System.out.println("添加成功");
				player.ifrob=false;
				ball111.ifcomputer=true;
				redbList.add(ball111);
				blueList.add(ball111);
				obList.add(ball111);
				ifball=false;
				ball111.hasball=true;
			}
			for(int i=0;i< obList.size();i++) {
			
				if(obList.get(i)!=null) {
					if(!pause) {
						
						obList.get(i).paintSelt(gImage);
					}else {
						Font f1=new Font("宋体",Font.BOLD,25);
						
						  gImage.setFont(f1);
						  gImage.setColor(Color.green);
						gImage.drawString("继续", player.getX()+100, 60);
					}
					
				}
				obList.removeAll(removeList);
				
				
			}
			//判断是否胜利的弹窗
			if(outcome&&GameObject.bluescore==7) {
				outcome=false;
					JOptionPane.showMessageDialog(null, "电脑胜利！", "比赛失败",JOptionPane.PLAIN_MESSAGE);  
			}
		 
			
		}
		
		
		if(state==0) {	 	
			gImage.drawImage(begin1, 0, 0,null);
		
			JButton button1=new JButton();
		
			button1.setContentAreaFilled(false);
			
			button1.setSize(150, 100);
			button1.setLocation(400, 360);	
			
			button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				         i1=1;
						state = 6;
						button1.setVisible(false);
					
						System.out.println("2");
						gImage.drawImage(offScreenImage, 0, 0, null);
						
					}
				});
			
		
	
			if(i1>0) {
				this.add(button1);
				i1--;
			}
		
			gImage.drawImage(img1, 400, 400,null);	
	
			//g.drawImage(img1, 800, 400,null);	
			
			
			
			
		}
		else if (state==2) {
			//选择角色进入游戏
			//选择角色进入游戏
			//选择角色进入游戏
			
			//判断角色是否购买
			 ArrayList<Integer> buyArrayList=new ArrayList<>();
				HaveRoleDao_imp haveRoleDao_imp=new HaveRoleDao_imp();
				buyArrayList=haveRoleDao_imp.haveRoles(id);
				for(int i=0;i<playersRoles.rolelist.size();i++){
					role obj=playersRoles.rolelist.get(i);
					if(buyArrayList.size()>0) {
						if(buyArrayList.get(i)==1) {
							playersRoles.rolelist.get(i).setHave_role(true);
						}else {
							playersRoles.rolelist.get(i).setHave_role(false);
						}
					}}
			
			gImage.drawImage(begin1, 0, 0,null);
			
			 Font f1=new Font("宋体",Font.BOLD,35);
			 gImage.setFont(f1);
			 gImage.setColor(Color.RED);
			 gImage.drawString("返回", 200, 500);
			gImage.drawString("↓↓选择角色进入游戏↓↓", 350, 200);
			ArrayList<JButton> jblist=new ArrayList<>();
			//添加返回键
			   JButton j1=new JButton();
			   j1.setSize(90, 50);
			   j1.setLocation(180, 430);
					
			for(int i=0;i<playersRoles.rolelist.size();i++){
				//判断是否购买角色
				role obj=playersRoles.rolelist.get(i);
				
				//初始化各种变量
				c1.a=0;
				c2.a=0;
				for(int i1=0;i1< obList.size();i1++) {
					obList.remove(i1);
				}
				   for(int i1=0;i1<redbList.size();i1++) {
					   redbList.remove(i1);
					   System.out.println("删除红色电脑"+i1);
				   } for(int i1=0;i1<blueList.size();i1++) {
					   blueList.remove(i1);
					   System.out.println("删除蓝色电脑"+i1);
				   }for(int i1=0;i1<allcomputer.size();i1++) {
					  allcomputer.remove(i1);
					   System.out.println("删除电脑"+i1);
				   }
				 //添加按钮和图片
			   Image classical=playersRoles.rolelist.get(i).classical;
			 
				
				JButton button=new JButton();
				button.setSize(150,150);
				button.setLocation(200+i*200,260);
				int a=i;
				  if(obj.isHave_role()) {
					   gImage.drawImage(classical, i*200, 200, null);
					   jblist.add(button);
				   }
				button.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						 for(int i=0;i<jblist.size();i++) {
			        		   jblist.get(i).setVisible(false);;
			        	   }
						// TODO Auto-generated method stub
						 i1=1;
						state=8;
						player=playersRoles.rolelist.get(a);
						obList.add(backGround);
						pause=false;
						
						obList.addAll(lankuang.lKlistArrayList);
						obList.add(player);
						redbList.add(player);
						player.button1.setVisible(true);
						player.button2.setVisible(true);
						j1.setVisible(false);
						//player.addButton();	
						System.out.println("按下"+a);
						button.setVisible(false);
					}
				});
	            j1.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						 for(int i=0;i<jblist.size();i++) {
			        		   jblist.get(i).setVisible(false);;
			        	   }
						// TODO Auto-generated method stub
						 i1=1;
						state=3;
						j1.setVisible(false);
					}
				});
				
			}
			if(i1>0) {
				   for(int i=0;i<jblist.size();i++) {
	        		   add(jblist.get(i));
	        	   }
				  add(j1);
	        	   i1--;
	        	   
			}
		}
		else if (state==8) {
			//选择难度
			//选择难度
			gImage.drawImage(begin1, 0, 0,null);
			
			 Font f1=new Font("宋体",Font.BOLD,35);
			 gImage.setFont(f1);
			 gImage.setColor(Color.RED);
			 gImage.drawString("返回", 200, 500);
			gImage.drawString("↓↓选择挑战难度↓↓", 350, 200);
			gImage.drawString("简单", 430, 320);
			gImage.drawString("普通", 430, 390);
			gImage.drawString("困难", 430, 460);
			JButton j1=new JButton();
			JButton j2=new JButton();
			JButton j3=new JButton();
			JButton j4=new JButton();
			 j1.setSize(90, 50);
			   j1.setLocation(180, 430);
			   j2.setSize(90, 50);
			   j2.setLocation(410, 250);
			   j3.setSize(90, 50);
			   j3.setLocation(410, 320);
			   j4.setSize(90, 50);
			   j4.setLocation(410, 390);
			   j1.setVisible(true);
			//返回键
			  j1.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						
						// TODO Auto-generated method stub
						 i1=1;
						state=2;
						j1.setVisible(false);
						j2.setVisible(false);
						j4.setVisible(false);
						j3.setVisible(false);
					}
				});
			  j2.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						
						// TODO Auto-generated method stub
						 i1=1;
						state=1;
						level=1;
						j1.setVisible(false);
						j2.setVisible(false);
						j4.setVisible(false);
						j3.setVisible(false);
						player.addButton();
					}
				});
			  j3.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						
						// TODO Auto-generated method stub
						 i1=1;
						state=1;
						level=2;
						j1.setVisible(false);
						j2.setVisible(false);
						j4.setVisible(false);
						j3.setVisible(false);
						player.addButton();
					}
				});
			  j4.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						
						// TODO Auto-generated method stub
						 i1=1;
						state=1;
						level=3;
						j1.setVisible(false);
						j2.setVisible(false);
						j4.setVisible(false);
						j3.setVisible(false);
						player.addButton();
					}
				});
			  
			  
			  
			  if(i1>0) {
				  add(j2);
				  add(j3);
				  add(j4);
				  add(j1);
	        	   i1--;
	        	   
			}
			
		}
		else if (state==3) {
			//主菜单
			//主菜单
			if(openThread!=null) {
				openThread.stop();
			}
		
			JButton b1=new JButton();
			JButton b2=new JButton();
			JButton b3=new JButton();
			JButton b4=new JButton();
			//设置按钮
			JButton b5=new JButton();
			 Font f1=new Font("宋体",Font.BOLD,32);
			 gImage.setFont(f1);
			 gImage.setColor(Color.RED);
			 gImage.drawImage(begin1, 0, 0,null);	
			 
			b1.setBounds(290, 140, 120, 50);
			b1.setContentAreaFilled(false);
			
			gImage.drawString("开始游戏", 300, 200);
			b1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					i1=1;
					b1.setVisible(false);
					b2.setVisible(false);
					b3.setVisible(false);
					b4.setVisible(false);
					b5.setVisible(false);
					// TODO Auto-generated method stub
					state = 2;
					
					g.drawImage(offScreenImage, 0, 0, null);
				}
			});
			gImage.drawString("个人信息", 300, 300);
			b2.setBounds(290, 240, 120, 50);
			
			b2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					i1=1;
					b1.setVisible(false);
					b2.setVisible(false);
					b3.setVisible(false);
					b4.setVisible(false);
					b5.setVisible(false);
					state = 4;
					g.drawImage(offScreenImage, 0, 0, null);
				}
			});
			
			gImage.drawString("商城", 300, 400);
			b3.setBounds(290, 340, 120, 50);
			
			b3.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					i1=1;
					b1.setVisible(false);
					b2.setVisible(false);
					b3.setVisible(false);
					b4.setVisible(false);
					b5.setVisible(false);
					state = 5;
					g.drawImage(offScreenImage, 0, 0, null);
				}
			});
			
			
			gImage.drawString("退出登录", 300, 600);
			gImage.drawString("设置", 300, 500);
			b4.setBounds(290, 540, 120, 50);
			b5.setBounds(290, 440, 120, 50);
			b4.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					i1=1;
					b1.setVisible(false);
					b2.setVisible(false);
					b3.setVisible(false);
					b4.setVisible(false);
					b5.setVisible(false);
					state = 0;
					g.drawImage(offScreenImage, 0, 0, null);
				}
			});
            b5.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					i1=1;
					b1.setVisible(false);
					b2.setVisible(false);
					b3.setVisible(false);
					b4.setVisible(false);
					b5.setVisible(false);
					state = 10;
					g.drawImage(offScreenImage, 0, 0, null);
				}
			});
			
			if(i1>0) {
				System.out.println("添加成功4个按钮");
				add(b1);
				add(b2);
				add(b3);
				add(b4);
				add(b5);
				i1--;
				if(bgmThread!=null) {
					bgmThread.stop();
				}
				bgmThread= new Thread("bgm") {
					
					public void run() {
						
						while(!stop) {
							try {
								//BufferedInputStream buffer=new BufferedInputStream(new FileInputStream(filename));
							    PlayBack bgm111=new PlayBack(new jmp123.output.Audio());
							    bgm111.open(filename, "");
							    bgm111.start(true);
							} catch (Exception e) {
								// TODO: handle exception
							}}}
				};
				bgmThread.start();
			}
		}else if (state==10) {
			//设置
			//设置
			 user=userDao_Imp.user_value(id);
			 Font f1=new Font("宋体",Font.BOLD,28);
			 gImage.setFont(f1);
			 gImage.setColor(Color.GREEN);
			gImage.drawImage(begin1, 0, 0,null);
			gImage.drawString("改变比赛场地: ", 200, 200);
			gImage.drawString("改变比赛音乐: ", 200, 500);
			Image map1=Toolkit.getDefaultToolkit().getImage("img/map.png");
			Image map2=Toolkit.getDefaultToolkit().getImage("img/map2.png");
			gImage.drawImage(map1, 200, 230, 200, 130, null);
			gImage.drawImage(map2, 600, 230, 200, 130, null);
			JButton map1Button=new JButton();
			JButton map2Button=new JButton();
			map1Button.setBounds(200, 200, 200, 130);
			map2Button.setBounds(600, 200, 200, 130);
	       if(Changemap==1) {
	    	   gImage.drawString("已设置", 240, 400);
	       }else {
	    	   gImage.drawString("已设置", 640, 400);
		}
	       JButton bgm1Button=new JButton();
			JButton bgm2Button=new JButton();
			bgm1Button.setBounds(250, 500, 100, 50);
			bgm2Button.setBounds(450, 500, 100, 50);
			 gImage.drawString("歌曲1", 260, 560);
			 gImage.drawString("歌曲2", 460, 560);
	       if(changemusic==1) {
	    	   
	    	   gImage.drawString("已设置", 250, 600);
	       }else {
	    	   gImage.drawString("已设置", 450, 600);
		}
			gImage.drawString("返回", 200, 700);
			
			JButton b1=new JButton();
			b1.setBounds(180, 630, 100, 50);
			
           b1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
				
					i1=1;
					b1.setVisible(false);
					map1Button.setVisible(false);
					map2Button.setVisible(false);
					bgm1Button.setVisible(false);
					bgm2Button.setVisible(false);
					state = 3;
					g.drawImage(offScreenImage, 0, 0, null);
				}
			});
         //更换比赛场地
			 map1Button.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Changemap=1;
						i1=1;
						b1.setVisible(false);
						map1Button.setVisible(false);
						map2Button.setVisible(false);
						bgm1Button.setVisible(false);
						bgm2Button.setVisible(false);
						state = 10;
						g.drawImage(offScreenImage, 0, 0, null);
					}
				});
			 map2Button.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Changemap=2;
						i1=1;
						b1.setVisible(false);
						map1Button.setVisible(false);
						map2Button.setVisible(false);
						bgm1Button.setVisible(false);
						bgm2Button.setVisible(false);
						state = 10;
						g.drawImage(offScreenImage, 0, 0, null);
					}
				});
			 //更换音乐
			 bgm1Button.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						gaming="bgm/gaming.mp3";
						changemusic=1;
						i1=1;
						b1.setVisible(false);
						map1Button.setVisible(false);
						map2Button.setVisible(false);
						bgm1Button.setVisible(false);
						bgm2Button.setVisible(false);
						state = 10;
						g.drawImage(offScreenImage, 0, 0, null);
					}
				});
			 bgm2Button.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						gaming="bgm/gaming2.mp3";
						changemusic=2;
						i1=1;
						b1.setVisible(false);
						map1Button.setVisible(false);
						map2Button.setVisible(false);
						bgm1Button.setVisible(false);
						bgm2Button.setVisible(false);
						state = 10;
						g.drawImage(offScreenImage, 0, 0, null);
					}
				});
          
           if(i1>0) {
        	   add(b1);
        	   add(bgm2Button);
        	   add(bgm1Button);
        	   add(map1Button);
        	   add(map2Button);
        	   i1--;
           }
			
			
		}
		
		else if (state==4) {
			//个人信息
			//个人信息
			//个人信息
			 user=userDao_Imp.user_value(id);
			 Font f1=new Font("宋体",Font.BOLD,25);
			 gImage.setFont(f1);
			 gImage.setColor(Color.GREEN);
			gImage.drawImage(begin1, 0, 0,null);
			gImage.drawString("姓名: "+user.getName(), 200, 300);
			gImage.drawString("ID: "+user.getId(), 200, 400);
			gImage.drawString("金币数名: "+user.getGold(), 200, 500);
			gImage.drawString("累计得分: "+user.getScore(), 200, 600);
			gImage.drawString("返回", 200, 700);
			gImage.drawString("查看成就", 400, 700);
			JButton b1=new JButton();
			JButton b2=new JButton();
			b1.setBounds(180, 630, 100, 50);
			b2.setBounds(380, 630, 100, 50);
           b1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					i1=1;
					b1.setVisible(false);
					b2.setVisible(false);
					state = 3;
					g.drawImage(offScreenImage, 0, 0, null);
				}
			});
           b2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					i1=1;
					b1.setVisible(false);
					b2.setVisible(false);
					state = 9;
					g.drawImage(offScreenImage, 0, 0, null);
				}
			});
           if(i1>0) {
        	   add(b1);
        	   add(b2);
        	   i1--;
           }
			
			
		}else if (state==9) {
			//个人成就
			AchieveDao achieveDao=new AchieveDao();
			 Font f1=new Font("宋体",Font.BOLD,25);
			 gImage.setFont(f1);
			 gImage.setColor(Color.GREEN);
			gImage.drawImage(begin1, 0, 0,null);
			 gImage.setColor(Color.YELLOW);
			gImage.drawString("初出茅庐：累计得分10分 ", 200, 300);
			gImage.drawString("球场新星：累计得分50分 ", 200, 400);
			gImage.drawString("明日之星：累计得分100分", 200, 500);
			gImage.drawString("腰缠万贯：金钱数量到1000 ", 200, 600);
			gImage.drawString("返回", 200, 700);
			if(user.getScore()>=10) {
				gImage.setColor(Color.red);
				gImage.drawString("<-已获得", 600, 300);
				achieveDao.alter_achieve1(user.getId());
				
			}else {
				gImage.setColor(Color.GREEN);
				gImage.drawString("<-未获得", 600, 300);
				
			}
			if(user.getScore()>=50) {
				gImage.setColor(Color.red);
				gImage.drawString("<-已获得", 600, 400);
				achieveDao.alter_achieve2(user.getId());
			}else {
				gImage.setColor(Color.GREEN);
				gImage.drawString("<-未获得", 600, 400);
			}
			if(user.getScore()>=100) {
				gImage.setColor(Color.red);
				gImage.drawString("<-已获得", 600, 500);
				achieveDao.alter_achieve3(user.getId());
			}else {
				gImage.setColor(Color.GREEN);
				gImage.drawString("<-未获得", 600, 500);
			}
			if(user.getGold()>=1000) {
				gImage.setColor(Color.red);
				gImage.drawString("<-已获得", 600, 600);
				achieveDao.alter_achieve4(user.getId());
			}else {
				gImage.setColor(Color.GREEN);
				gImage.drawString("<-未获得", 600, 600);
			}
			
			
			JButton b1=new JButton();
			b1.setBounds(180, 630, 120, 50);
           b1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					i1=1;
					b1.setVisible(false);
					state = 4;
					g.drawImage(offScreenImage, 0, 0, null);
				}
			});
           if(i1>0) {
        	   add(b1);
        	   i1--;
           }
			
			
		}
		
		
		
		
		else if (state==5) {
			//游戏商城界面
			//游戏商城界面
			//得到每个角色是否购买
	  ArrayList<Integer> buyArrayList=new ArrayList<>();
			HaveRoleDao_imp haveRoleDao_imp=new HaveRoleDao_imp();
			buyArrayList=haveRoleDao_imp.haveRoles(id);
			
	
			ArrayList<JButton> buttonlist=new ArrayList<>();
			 Font f1=new Font("宋体",Font.BOLD,35);
			 gImage.drawImage(dl, 0, 0,null);	
			 gImage.setFont(f1);
			 gImage.setColor(Color.YELLOW);
			gImage.drawString("->角色商城-<", 430, 150);
			gImage.drawString("购买技能", 140, 220);
			JButton buyskillButton=new JButton();
			buyskillButton.setBounds(120, 150, 160, 50);
			for(int i=0;i<playersRoles.rolelist.size();i++){
				role obj=playersRoles.rolelist.get(i);
				if(buyArrayList.size()>0) {
					if(buyArrayList.get(i)==1) {
						playersRoles.rolelist.get(i).setHave_role(true);
					}else {
						playersRoles.rolelist.get(i).setHave_role(false);
					}
				}
				Image classical=playersRoles.rolelist.get(i).classical;
				gImage.drawImage(classical, i*250, 200, null);
				JButton button=new JButton();
				//添加按钮
				if(!playersRoles.rolelist.get(i).isHave_role()) {
					
					button.setSize(60,40);
					button.setLocation(230+i*250,700);
					buttonlist.add(button);
					 Font f2=new Font("宋体",Font.BOLD,25);
					 gImage.setFont(f2);
					 gImage.setColor(Color.RED);
					gImage.drawString("购买", 240+i*250, 760);
					
				}
				int a=i;
				
				//购买按钮
				button.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(user.getGold()-obj.getPrice()>=0) {
							JOptionPane.showMessageDialog(null,"购买成功","恭喜！", JOptionPane.PLAIN_MESSAGE);
							playersRoles.rolelist.get(a).setHave_role(true);
							button.setVisible(false);
							user.setGold(user.getGold()-obj.getPrice());
							
							new HaveRoleDao_imp().alter_role(id, a+1);
							new UserDao_Imp().alter_gold(id,user.getGold());
						}else {
							JOptionPane.showMessageDialog(null,"金币不够","失败！", JOptionPane.PLAIN_MESSAGE);
						}
						
					
					}
				});
				
				Font f3=new Font("宋体",Font.CENTER_BASELINE,30);
				 
				 gImage.setFont(f1);
				gImage.setColor(Color.yellow);
				gImage.drawString("速度："+playersRoles.rolelist.get(i).getSpeed(), 230+i*250, 450);
				gImage.drawString("力量："+playersRoles.rolelist.get(i).getStrength(), 230+i*250, 550);
				gImage.drawString("价格："+obj.getPrice(), 230+i*250, 650);
			}
			
			gImage.drawString("返回", 140, 150);
			JButton b1=new JButton();
			b1.setBounds(120, 80, 80, 50);
           b1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					for(int i=0;i<buttonlist.size();i++) {
						System.out.println("删除成功");
		        		   buttonlist.get(i).setVisible(false);
		        	   }
					i1=1;
					buyskillButton.setVisible(false);
					b1.setVisible(false);
					state = 3;
					g.drawImage(offScreenImage, 0, 0, null);
				}
			});
           buyskillButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					for(int i=0;i<buttonlist.size();i++) {
					
		        		   buttonlist.get(i).setVisible(false);
		        	   }
					i1=1;
					b1.setVisible(false);
					buyskillButton.setVisible(false);
					state = 11;
					g.drawImage(offScreenImage, 0, 0, null);
				}
			});
           if(i1>0) {
        	   add(b1);
        	   add(buyskillButton);
        	   for(int i=0;i<buttonlist.size();i++) {
        		   add(buttonlist.get(i));
        	   }
        	   i1--;
           }
			
		}else if (state==11) {
			//购买技能界面
			ArrayList<Integer> buyArrayList=new ArrayList<>();
			HavaSkillDao_imp havaSkillDao_imp=new HavaSkillDao_imp();
			buyArrayList=havaSkillDao_imp.haveSkills(id);
				
				ArrayList<JButton> buttonlist=new ArrayList<>();
				 Font f1=new Font("宋体",Font.BOLD,22);
				 gImage.drawImage(dl, 0, 0,null);	
				 gImage.setFont(f1);
				 gImage.setColor(Color.YELLOW);
				gImage.drawString("->技能商城-<", 430, 150);
				gImage.drawString("三分球", 240, 420);
				gImage.drawString("技能每次比赛使用一次", 240, 470);
				gImage.drawString("使用后下次进球为三分球", 240,520);
				gImage.drawString("大步流星", 590, 420);
				gImage.drawString("技能每次比赛使用一次", 590, 470);
				gImage.drawString("使用后移动速度增加10s", 590,520);
				//gImage.drawString("11", 140, 220);
				JButton buyskillButton=new JButton();
				buyskillButton.setBounds(120, 150, 160, 50);
				for(int i=0;i<skills.skillslList.size();i++){
				
					Skill obj=skills.skillslList.get(i);
					if(buyArrayList.size()>0) {
						if(buyArrayList.get(i)==1) {
							skills.skillslList.get(i).setHave_skill(true);
						}else {
							skills.skillslList.get(i).setHave_skill(false);
						}
					}
					Image classical1=skills.skillslList.get(i).skillImage;
					gImage.drawImage(classical1, i*350+250, 200, null);
					JButton button=new JButton();
					//添加按钮
					if(!skills.skillslList.get(i).isHave_skill()) {
						
						button.setSize(60,40);
						button.setLocation(290+i*300,700);
						buttonlist.add(button);
						 Font f2=new Font("宋体",Font.BOLD,25);
						 gImage.setFont(f2);
						 gImage.setColor(Color.RED);
						gImage.drawString("购买", 300+i*300, 760);
						
					}
					int a=i;
					
					//购买按钮
					button.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							if(user.getGold()-obj.getPrice()>=0) {
								JOptionPane.showMessageDialog(null,"购买成功","恭喜！", JOptionPane.PLAIN_MESSAGE);
								skills.skillslList.get(a).setHave_skill(true);
								button.setVisible(false);
								user.setGold(user.getGold()-obj.getPrice());
								
								new HavaSkillDao_imp().alter_skill(id, a+1);
								new UserDao_Imp().alter_gold(id,user.getGold());
							}else {
								JOptionPane.showMessageDialog(null,"金币不够","失败！", JOptionPane.PLAIN_MESSAGE);
							}
							
						
						}
					});
					
					Font f3=new Font("宋体",Font.CENTER_BASELINE,30);
					 
					 gImage.setFont(f1);
					gImage.setColor(Color.yellow);
					
					gImage.drawString("价格："+obj.getPrice(), 240+i*350, 650);
				}
				
				gImage.drawString("返回", 140, 150);
				JButton b1=new JButton();
				b1.setBounds(120, 80, 80, 50);
	           b1.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						for(int i=0;i<buttonlist.size();i++) {
							System.out.println("删除成功");
			        		   buttonlist.get(i).setVisible(false);
			        	   }
						i1=1;
						buyskillButton.setVisible(false);
						b1.setVisible(false);
						state = 3;
						g.drawImage(offScreenImage, 0, 0, null);
					}
				});
	           buyskillButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						for(int i=0;i<buttonlist.size();i++) {
						
			        		   buttonlist.get(i).setVisible(false);
			        	   }
						i1=1;
						b1.setVisible(false);
						buyskillButton.setVisible(false);
						state = 11;
						g.drawImage(offScreenImage, 0, 0, null);
					}
				});
	           if(i1>0) {
	        	   add(b1);
	        	   //add(buyskillButton);
	        	   for(int i=0;i<buttonlist.size();i++) {
	        		   add(buttonlist.get(i));
	        	   }
	        	   i1--;
	           }
			
			
		}
		
		
		
		else if (state==6) {
			Font f2=new Font("宋体",Font.CENTER_BASELINE,30);
			 
			 gImage.setFont(f2);
			 gImage.setColor(Color.RED);
			//注册登录界面
				//注册登录界面
				//注册登录界面
			gImage.drawImage(dl, 0, 0, null);
			gImage.drawString("JAVA篮球游戏", 300, 100);
			gImage.drawString("ID:", 200, 300);
			TextField jTextField1=new TextField();
		      jTextField1.setSize(220, 20);
		      jTextField1.setLocation(250, 250);
		      TextField jTextField2=new TextField();
		      jTextField2.setSize(200, 20);
		      jTextField2.setLocation(270, 350);
		      gImage.drawString("返回", 200, 700);
				JButton b1=new JButton();
				   JButton b2=new JButton();
		           JButton b3=new JButton();
				b1.setBounds(180, 630, 120, 50);
	           b1.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						i1=1;
						jTextField1.setVisible(false);
						jTextField2.setVisible(false);
						b1.setVisible(false);
						b2.setVisible(false);
						b3.setVisible(false);
						state = 0;
						g.drawImage(offScreenImage, 0, 0, null);
					}
				});
	           //登陆按钮
	           //登陆按钮
	           //登陆按钮
	           gImage.drawString("登录", 200, 500);
	
	           b2.setBounds(170, 440, 110, 45);
	           b2.addActionListener(new ActionListener() {
				User User1=new User(); 	
				UserDao_Imp userDao_Imp1=new UserDao_Imp();
					@Override
					public void actionPerformed(ActionEvent e) {
						
						// TODO Auto-generated method stub
						String idString=jTextField1.getText();
						String codeString=jTextField2.getText();
						User1.setId(Integer.parseInt(idString));
						User1.setPassword(Integer.parseInt(codeString));
						
						if(userDao_Imp1.login(User1)) {
							i1=1;
							jTextField1.setVisible(false);
							jTextField2.setVisible(false);
							b1.setVisible(false);
							b2.setVisible(false);
							b3.setVisible(false);
							state = 3;
							g.drawImage(offScreenImage, 0, 0, null);
							id=User1.getId();
							user= userDao_Imp.user_value(id);
						}else {
							JOptionPane.showMessageDialog(null,"ID或密码错误，请重试","警告", JOptionPane.ERROR_MESSAGE);
						
							gImage.drawString("登录失败", 200, 600);
						}
						
					}
					
				});
	           gImage.drawString("注册", 370, 500);
	           b3.setBounds(340, 440, 110, 45);
		           b3.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							i1=1;
							jTextField1.setVisible(false);
							jTextField2.setVisible(false);
							b1.setVisible(false);
							b2.setVisible(false);
							b3.setVisible(false);
							state = 7;
							g.drawImage(offScreenImage, 0, 0, null);
						}
					});
	
		      if(i1>0) {
		    	  add(b3);
		    	  add(b2);
		    	  add(b1);
		    	  add(jTextField1);
		    	  add(jTextField2);
		    	  i1--;
		      }
			
			gImage.drawString("密码：", 200, 400);
			
		}else if (state==7) {
			//注册界面
			//注册界面
			//注册界面
			Font f2=new Font("宋体",Font.CENTER_BASELINE,25);
			 gImage.setFont(f2);
			 gImage.setColor(Color.blue);
			gImage.drawImage(dl, 0, 0, null);
			gImage.drawString("JAVA篮球游戏", 300, 100);
			gImage.drawString("请输入新ID:", 100, 300);
			gImage.drawString("请输入密码：", 100, 400);
			gImage.drawString("请输入姓名：", 100, 500);
			TextField jTextField1=new TextField();
		      jTextField1.setSize(220, 20);
		      jTextField1.setLocation(250, 250);
		      TextField jTextField2=new TextField();
		      jTextField2.setSize(200, 20);
		      jTextField2.setLocation(270, 350);
		      TextField jTextField3=new TextField();
		      jTextField3.setSize(200, 20);
		      jTextField3.setLocation(270, 450);
		      gImage.drawString("返回", 200, 700);
				JButton b1=new JButton();
				   JButton b2=new JButton();
		           JButton b3=new JButton();
				b1.setBounds(180, 630, 120, 50);
	           b1.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						i1=1;
						jTextField1.setVisible(false);
						jTextField2.setVisible(false);
						jTextField3.setVisible(false);
						b1.setVisible(false);
						b2.setVisible(false);
						b3.setVisible(false);
						
						state = 6;
						g.drawImage(offScreenImage, 0, 0, null);
					}
				});
	           //注册按钮
	           gImage.drawString("确认注册", 270, 600);
	           User user2=new User();
				UserDao_Imp userDao_Imp2=new UserDao_Imp();
				AchieveDao achieveDao=new AchieveDao();
	           b3.setBounds(260, 540, 110, 45);
		           b3.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							
							String idString=jTextField1.getText();
							String codeString=jTextField2.getText();
							String name=jTextField3.getText();
							user2.setId(Integer.parseInt(idString));
							user2.setPassword(Integer.parseInt(codeString));
							user2.setName(name);
							System.out.println(user2.getId());
						   if(userDao_Imp2.insert(user2)) {
							   JOptionPane.showMessageDialog(null,"注册成功！","恭喜", JOptionPane.PLAIN_MESSAGE);
						       userDao_Imp2.sql_i(user2);
						       userDao_Imp2.sql_create_haverole(user2.getId());
						       achieveDao.sql_create_achieve(user2.getId());
						   }else {
							   JOptionPane.showMessageDialog(null,"失败！","该账户已存在！", JOptionPane.PLAIN_MESSAGE);
						}
							
							i1=1;
							jTextField1.setVisible(false);
							jTextField2.setVisible(false);
							jTextField3.setVisible(false);
							b1.setVisible(false);
							b3.setVisible(false);
							
							
							
							state = 6;
							g.drawImage(offScreenImage, 0, 0, null);
						}
					});
	
		      if(i1>0) {
		    	  add(b3);
		    	
		    	  add(b1);
		    	  add(jTextField1);
		    	  add(jTextField2);
		    	  add(jTextField3);
		    	  i1--;
		      }
			
		
		}
		
		 
		 if (state != 1) {
				
			 g.drawImage(offScreenImage, 0, 0, null);
				
			} else  {
				g.drawImage(offScreenImage,-player.getX()+100,0, null);
			}
		 
		
			
		
		
		
		
	}
	
	//main方法
	public static void main(String[] args) throws SQLException {
		//数据库连接
		JDBC dbs=null;
		try {
			dbs=new JDBC();
		} finally {
			// TODO: handle finally clause
		}
		
		
		GameFrame gameFrame=new GameFrame();
		
		//gameFrame.start();
		
	gameFrame.launch();
	}
	 
	//键盘事件
	class KeyMonitor extends KeyAdapter{
		@Override 
		public void keyPressed(KeyEvent e) {
			int key=e.getKeyCode();
			//System.out.println(key);
			player.KeyPressed(e);
			ball111.keypressed(e);
		
		}
		
		public void keyReleased(KeyEvent e) {
			player.KeyReleased(e);
		}
	} 
	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}
}
