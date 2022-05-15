package basketball_1;

import java.awt.Toolkit;

public class normal_role  extends role {
 
	public normal_role(GameFrame gameFrame) {
		super(gameFrame);
		//abilityone=Toolkit.getDefaultToolkit().getImage("img/ball.png");
		//abilitytwo=Toolkit.getDefaultToolkit().getImage("img/ball.png");
		// TODO Auto-generated constructor stub
		classical=Toolkit.getDefaultToolkit().getImage("img/normal.png");
		//setSpeed(20);
		//setStrength(10);
		//setPrice(100);
		//setHave_role(false);
		
	}
	public normal_role(GameFrame gameFrame,int i,int j) {
		super(gameFrame,i,j);
		//setSpeed(20);
	}
    
}
