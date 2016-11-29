package Controller;

import javax.swing.*;

public class Yatzy {
	private JFrame window;
    private int rollsLeft;
    private ScoreBoard scoreBoard;
    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<Dice> dice = new ArrayList<Dice>();
	
	public int getBonus(Player player) {
		
		int bonus = 0;
		
		if(this.getUpperSectionScore(player) >= 63) {
			
			bonus = 50;
			
		}
		
		return bonus;
		
	}

}
