package basketball_1;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.bean.Role;
import com.bean.skill;
import com.dao.RoleDao_imp;
import com.dao.SkillDao;

public class Skill extends GameObject{
//�����б�
	ArrayList<Skill> skillslList=new ArrayList<>();
	//����ͼ��
	public Image skillImage;
	//���޴˼���
	private boolean isHave_skill=false;
	
	public boolean isHave_skill() {
		return isHave_skill;
	}

	public void setHave_skill(boolean isHave_skill) {
		this.isHave_skill = isHave_skill;
	}

	public Skill(GameFrame gameFrame) {
		super(gameFrame);
		System.out.println("ok");
          
		// TODO Auto-generated constructor stub
	}

	@Override
	public Rectangle getRec() {
		// TODO Auto-generated method stub
		return null;
	}
	public  Skill(GameFrame gameFrame,int x,int y) {
		super(gameFrame);
		skillslList.add(new Skill_one(gameFrame));
		 skillslList.add(new Skill_two(gameFrame));
		//�õ�ÿ�����ܵ�����
			SkillDao skillDao=new SkillDao();
		
			for(int i=0;i<skillslList.size();i++) {
				skill skill1=skillDao.skill_value(i);
				skillslList.get(i).setName(skill1.getName());

				skillslList.get(i).setPrice(skill1.getPrice());
				
				
			}
	}

	@Override
	public void paintSelt(Graphics g) {
		// TODO Auto-generated method stub
		
	}
      
}
