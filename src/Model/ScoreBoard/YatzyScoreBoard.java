package Model.ScoreBoard;

import Model.Player.*;

public class YatzyScoreBoard extends ScoreBoard {

	public YatzyScoreBoard(int rows, int columns) {
		super(rows, columns);
	}
	
	public int getBonus(Player player) {
		int bonus = 0;

		if (player.getScoreBoard().getUpperSectionScore().getSum() >= 63) {
			bonus = 50;
		}

		return bonus;
	}

}
