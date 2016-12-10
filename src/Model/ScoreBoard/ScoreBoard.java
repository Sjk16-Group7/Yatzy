 package Model.ScoreBoard;

public class ScoreBoard {
	
	private int rows;
	private int columns;
	private ScoreBoardCell[][] board;

	public ScoreBoard(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
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

	public boolean equals(ScoreBoard board) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (!this.getCell(i, j).equals(board.getCell(i, j)))
					return false;
			}
		}
		return true;
	}

	public ScoreBoard clone() {
		ScoreBoard copyBoard = new ScoreBoard(this.rows, this.columns);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				copyBoard.board[i][j] = this.getCell(i, j).clone();
				// copyBoard.equals(this.board[i][j]);
			}
		}
		return copyBoard;
	}

	public String toString() {
		return "ScoreBoard()";
	}
}
