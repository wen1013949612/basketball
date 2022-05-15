package basketball_1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public class basketball extends GameObject {

	public boolean ok=true; 
	
	
	
	public basketball(GameFrame gameFrame) {
		
		super(gameFrame);
		this.hasball=true;
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public Rectangle getRec() {
		// TODO Auto-generated method stub
		 return new Rectangle(1475, 425, 60, 40);
	}
   Image ballImage=Toolkit.getDefaultToolkit().getImage("img/ball.png");
    public void keypressed(KeyEvent e) {
    	
    	int key=e.getKeyCode();
    	if(key==KeyEvent.VK_SPACE) {
    		if(ifcomputer) {
    			if(JuXingPZ(getRec(), gameFrame.player.getRec())) {
        			System.out.println("¼ñµ½ÀºÇò");
        			this.hasball=false;
        			gameFrame.ifball=false;
        			gameFrame.obList.remove(gameFrame.ball111);
        			gameFrame.player.hasball=true;  
        			gameFrame.redbList.remove(gameFrame.ball111);
        			gameFrame.blueList.remove(gameFrame.ball111);
        		}
    		}
    		
    		
			
			   
		   }
	}
    public void ballcomputer() {
		for(GameObject obj:gameFrame.allcomputer) {
			if(JuXingPZ(obj.getRec(), getRec())) {
				if(ifcomputer) {
					System.out.println("µçÄÔ¼ñµ½Çò");
					ifcomputer=false;
					this.hasball=false;
	    			gameFrame.ifball=false;
	    			gameFrame.obList.remove(gameFrame.ball111);
	    			obj.hasball=true; 
	    			gameFrame.redbList.remove(gameFrame.ball111);
        			gameFrame.blueList.remove(gameFrame.ball111);
				}
			
    			
    			
			}
		}
		
	}
   
   
   
	@Override
	public void paintSelt(Graphics g) {
		// TODO Auto-generated method stub
		this.setX(1205);
		this.setY(305);
		if(ifcomputer) {
			ballcomputer();
		
		}
	    
		g.setColor(Color.red);
		g.drawRect(1475, 425, 50, 50);
		g.drawImage(ballImage,1300 ,300, gameFrame);
		
	}

}
