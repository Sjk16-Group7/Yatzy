package Model.ScoreBoard;

import java.util.ArrayList;

import Model.Player.*;
import Model.ScoreBoard.*;

public class YatzyScoreBoard extends ScoreBoard {
	
	private ScoreBoard scoreBoard;
	private ArrayList<Player> players = new ArrayList<Player>();

	public YatzyScoreBoard(int rows, int columns) {
		super(rows, columns);
	}
	
	public int getUpperSectionScore(Player player) {
		int sum = 0;
		int numberOfUpperIndex = 6;
		int indexOfPlayer = this.players.indexOf(player);

		for (int i = 0; i < numberOfUpperIndex; i++) {
			ScoreBoardCell cell = this.scoreBoard.getCell(i, indexOfPlayer);
			String value = cell.getValue();

			if (!cell.isCrossed()) {
				sum += Integer.parseInt(value);
			}
		}

		return sum;
	}
	
	public int getLowerSectionScore(Player player){
		int sum = 0;
		int numberOfUpperIndex = 6;
		int numberOfLowerSection = 9;
		int indexOfPlayer = this.players.indexOf(player);
		
		for (int i = numberOfUpperIndex; i < numberOfUpperIndex + numberOfLowerSection; i++){
			ScoreBoardCell cell = this.scoreBoard.getCell(i, indexOfPlayer);
			String value = cell.getValue();
			
			if (!cell.isCrossed()) {
				sum += Integer.parseInt(value);
			}
		}
		
		return sum;
	}
	
	public int getTotalScore(Player player){
		int totalScore = 0;
		
		totalScore += getUpperSectionScore(player) + getLowerSectionScore(player);
		totalScore += getBonus(player);
		
		return totalScore;
	}
}
