package Model.ScoreBoard;

import java.util.ArrayList;

import Model.Dice.DiceCombination;
import Model.Player.*;
import Model.ScoreBoard.*;

public class YatzyScoreBoard extends ScoreBoard {

	public YatzyScoreBoard() {
		super(DiceCombination.values().length, 1);
	}
	
	public int getBonus() {
		int bonus = 0;

		if (getUpperSection().getSum() >= 63) {
			bonus = 50;
		}

		return bonus;
	}
	public ScoreBoard getUpperSection() {
		ScoreBoard scoreBoard = new ScoreBoard(6, 1);
		
		return scoreBoard;
	}
	
	public ScoreBoard getLowerSection() {
		
	}
	
	public int getTotalScore(){
		return this.getSum() + this.getBonus();
	}
}
