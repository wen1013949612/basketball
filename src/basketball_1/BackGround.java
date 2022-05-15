package basketball_1;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class BackGround extends GameObject {
	
	
      
	public BackGround(GameFrame gameFrame) {
		super(gameFrame);
		// TODO Auto-generated constructor stub
	}
	Image bgImage=Toolkit.getDefaultToolkit().getImage("img/map.png");
	Image bgImage2=Toolkit.getDefaultToolkit().getImage("img/map2.png");
    //  public void paintSelF(Graphics g) {
    	  //g.drawImage(bgImage, 0, 0, null);
    	  
    	  
     // }
	@Override
	public Rectangle getRec() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void paintSelt(Graphics g) {
		// TODO Auto-generated method stub
		 if(gameFrame.Changemap==1) {
			 g.drawImage(bgImage, 0, 0, null);
		 }else {
			 g.drawImage(bgImage2, 0, 0, null);
		}
		  
		  
	}
}
