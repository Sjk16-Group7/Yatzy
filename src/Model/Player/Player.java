package Model.Player;

import Model.ScoreBoard.ScoreBoard;

public class Player {

	private ScoreBoard scoreBoard;
	private String name;

	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public ScoreBoard getScoreBoard() {
		return this.scoreBoard;
	}

	
	public String toString(){
		return "Player(name: " + this.name + ")";
	}
	
	public boolean equals(Player player){
		if (!this.name.equals(player.name)){
			return false;
		} if (!this.scoreBoard.equals(player.scoreBoard)){
			return false;
		}
		return true;
	}
	
	public Player clone (){
		Player playerCopy = new Player(this.name);
		playerCopy.scoreBoard = this.scoreBoard.clone();
		
		return playerCopy;
	}
	
}
