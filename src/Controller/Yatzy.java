package Controller;

public class Yatzy {
	
	public int getBonus(Player player) {
		
		int bonus = 0;
		
		if(this.getUpperSectionScore(player) >= 63) {
			
			bonus = 50;
			
		}
		
		return bonus;
		
	}

}
