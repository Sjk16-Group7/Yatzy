package Model.ScoreBoard;

public class ScoreBoard {
    protected int rows;
    protected int columns;
	protected ScoreBoardCell[][] board;

    private ScoreBoard() {}

	public ScoreBoard(int rows, int columns) {
	    this.createBoard(rows, columns);
	}

	public static ScoreBoard fromRows(ScoreBoardCell[][] rows) {
	    ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.board = rows;

	    return scoreBoard;
    }

	public static ScoreBoard fromColumns(ScoreBoardCell[][] columns) {
	    ScoreBoard scoreBoard = new ScoreBoard();

	    // TODO
        return scoreBoard;
    }

    private void createBoard(int rows, int columns) {
	    this.board = new ScoreBoardCell[rows][columns];
		this.reset();
    }

	public ScoreBoardCell[] getRow(int index) {
	    return this.board[index].clone();
    }

    public ScoreBoardCell[] getColumn(int index) {
        ScoreBoardCell[] column = new ScoreBoardCell[this.rows];

        for (int i = 0; i < rows; i++) {
            ScoreBoardCell cell = this.board[i][index];

            column[i] = cell;
        }

	    return column;
    }

	public ScoreBoardCell getCell(int row, int column) {
		return this.board[row][column];
	}

    public int getSum() {
        int sum = 0;

        for (int row = 0; row < this.rows; row++) {
            for (int column = 0; column < this.columns; columns++) {
                ScoreBoardCell cell = this.getCell(row, column);
                sum += cell.getValue();
            }
        }

        return sum;
    }

    public boolean isFull() {
        for (ScoreBoardCell[] row : this.board) {
            for (ScoreBoardCell cell : row) {
                if (cell.isEmpty()) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isEmpty() {
        for (ScoreBoardCell[] row : this.board) {
            for (ScoreBoardCell cell : row) {
                if (!cell.isEmpty()) {
                    return false;
                }
            }
        }

        return true;
    }

	public void reset() {
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				this.board[i][j] = new ScoreBoardCell();
			}
		}
	}

    public boolean equals(ScoreBoard board) {
        for (int row = 0; row < this.rows; row++) {
            for (int column = 0; column < this.columns; column++) {
                if (!this.getCell(row, column).equals(board.getCell(row, column))) {
                    return false;
                }
            }
        }

        return true;
    }

    public ScoreBoard clone() {
        ScoreBoard copy = new ScoreBoard(this.rows, this.columns);

        for (int row = 0; row < this.rows; row++) {
            for (int column = 0; column < this.columns; column++) {
                copy.board[row][column] = this.getCell(row, column).clone();
            }
        }

        return copy;
    }

    @Override
    public String toString() {
        return "ScoreBoard(rows:" + this.rows + ", columns:" + this.columns + ")";
    }
}
