package Model;

public class ScoreBoard {
	private ScoreBoardCell[][] board;

	public ScoreBoard(int rows, int columns) {
		this.board = new ScoreBoardCell[rows][columns];

		this.reset();
	}
	
	public ScoreBoardCell getCell(int row, int column) {
		return this.board[row][column];
	}
	
	public void reset() {
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[i].length; j++) {
				this.board[i][j] = new ScoreBoardCell(); 
			}
		}
	}

}
