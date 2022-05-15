package basketball_1;

import java.awt.Toolkit;

public class Skill_one extends Skill{

	public Skill_one(GameFrame gameFrame) {
		super(gameFrame);
		// TODO Auto-generated constructor stub
		
		skillImage=Toolkit.getDefaultToolkit().getImage("img/Skill/skillone.png");
	}
	public Skill_one(GameFrame gameFrame,int i,int j) {
		super(gameFrame,i,j);
		//setSpeed(20);
	}
	

}
