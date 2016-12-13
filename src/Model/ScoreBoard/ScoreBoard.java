package Model.ScoreBoard;

/**
 * ScoreBoard is a class representing a real life scoreboard, basically a two-dimensional
 * grid of numbers, where the each cell may be filled with a number, be empty or crossed over.
 * @author Isak
 * @author Marianna
 * @author Gustav
 */
public class ScoreBoard {
    protected int rows;
    protected int columns;
	protected ScoreBoardCell[][] board;

    /**
     * Class default constructor
     */
    private ScoreBoard() {} // is this really necessary for the static constructors?

    /**
     * Class constructor specifying the number of rows and columns the scoreboard should have
     * @param rows    the amount of rows
     * @param columns the amount of columns
     */
	public ScoreBoard(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
	    this.createBoard(rows, columns);
	}

    /**
     * Static constructor specifying the rows of the new ScoreBoard
     * @param rows the rows to use
     * @return     a new ScoreBoard
     */
	public static ScoreBoard fromRows(ScoreBoardCell[][] rows) {
	    ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.board = rows;

        // TODO doesn't clone the cells & doesn't set rows/columns

	    return scoreBoard;
    }

    /**
     * Static constructor specifying the columns of the new ScoreBoard
     * @param columns the columns to use
     * @return        a new ScoreBoard
     */
	public static ScoreBoard fromColumns(ScoreBoardCell[][] columns) {
	    ScoreBoard scoreBoard = new ScoreBoard();

	    // TODO doesn't work

        return scoreBoard;
    }

    /**
     * Creates a new 2-dimensional array of ScoreBoardCell with the default values
     * @param rows    the amount of rows in the scoreboard
     * @param columns the amount of columns in the scoreboard
     */
    private void createBoard(int rows, int columns) {
	    this.board = new ScoreBoardCell[rows][columns];
		this.reset();
    }

    /**
     * Gets a specific row of this scoreboard
     * @param index the index of the row
     * @return      an array representing a row
     */
	public ScoreBoardCell[] getRow(int index) {
	    return this.board[index].clone();
    }

    /**
     * Gets a specific column of this scoreboard
     * @param index the index of the column
     * @return      an array representing a column
     */
    public ScoreBoardCell[] getColumn(int index) {
        ScoreBoardCell[] column = new ScoreBoardCell[this.rows];

        for (int i = 0; i < rows; i++) {
            ScoreBoardCell cell = this.board[i][index];

            column[i] = cell;
        }

	    return column;
    }

    /**
     * Gets a specific cell
     * @param row    the row index of the cell
     * @param column the column index of the cell
     * @return       the cell
     */
	public ScoreBoardCell getCell(int row, int column) {
		return this.board[row][column];
	}

    /**
     * Sums up the values of each cell in the board
     * @return the sum
     */
    public int getSum() {
        int sum = 0;

        for (int row = 0; row < this.rows; row++) {
            for (int column = 0; column < this.columns; column++) {
                ScoreBoardCell cell = this.getCell(row, column);
                sum += cell.getValue();
            }
        }

        return sum;
    }

    /**
     * Checks whether the board is full
     * @return boolean indicating whether the board is full
     */
    public boolean isFull() {
        for (ScoreBoardCell[] row : this.board) {
            for (ScoreBoardCell cell : row) {
                if (cell.isEmpty() && !cell.isCrossed()) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Checks whether the board is empty
     * @return boolean indicating whether the board is empty
     */
    public boolean isEmpty() {
        for (ScoreBoardCell[] row : this.board) {
            for (ScoreBoardCell cell : row) {
                if (!cell.isEmpty() || cell.isCrossed()) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Resets the board to its' default state
     */
	public void reset() {
		for (int row = 0; row < this.rows; row++) {
			for (int column = 0; column < this.columns; column++) {
				this.board[row][column] = new ScoreBoardCell();
			}
		}
	}

    /**
     * Checks whether this and another Object of the same type are equal
     * @param board the other Object to compare with
     * @return      a boolean indicating if this and the other Object are equal
     */
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

    /**
	 * Gets an identical new Object of the type class as this
	 * @return a new instance identical to this
	 */
    public ScoreBoard clone() {
        ScoreBoard copy = new ScoreBoard(this.rows, this.columns);

        for (int row = 0; row < this.rows; row++) {
            for (int column = 0; column < this.columns; column++) {
                copy.board[row][column] = this.getCell(row, column).clone();
            }
        }

        return copy;
    }

    /**
	 * Gets a String representation of this class
	 * @return a String representation of this class
	 */
    @Override
    public String toString() {
        return "ScoreBoard(rows:" + this.rows + ", columns:" + this.columns + ")";
    }
}
