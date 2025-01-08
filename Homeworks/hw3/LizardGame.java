package hw3;


import static api.Direction.*;

import java.util.ArrayList;

import api.Cell;
import api.Direction;
import api.Exit;
import api.ScoreUpdateListener;
import api.ShowDialogListener;
import api.Wall;
import api.BodySegment;

/**
 * Class that models a game.
 */
public class LizardGame {
	private ShowDialogListener dialogListener;
	private ScoreUpdateListener scoreListener;
	private Cell[][] cell;
	private Wall wall;
	private Exit exit;
	private int height;
	private int width;
	private ArrayList<Lizard> lizards;


	/**
	 * Constructs a new LizardGame object with given grid dimensions.
	 * 
	 * @param width  number of columns
	 * @param height number of rows
	 */
	public LizardGame(int width, int height) {
		//new array list
		this.lizards = new ArrayList<Lizard>();
		//creating a new cell
		this.cell = new Cell[width][height];
		for (int row = 0; row < height; row++) { 
			for (int col = 0; col < width; col++) {
				//intializing the cell
				this.cell[col][row] = new Cell(col,row);
			}
		}
	}

	/**
	 * Get the grid's width.
	 * 
	 * @return width of the grid
	 */
	public int getWidth() {
		width = cell.length; 
		return width;
	}

	/**
	 * Get the grid's height.
	 * 
	 * @return height of the grid
	 */
	public int getHeight() {
		height = cell[0].length;
		return height;
	}

	/**
	 * Adds a wall to the grid.
	 * <p>
	 * Specifically, this method calls placeWall on the Cell object associated with
	 * the wall (see the Wall class for how to get the cell associated with the
	 * wall). This class assumes a cell has already been set on the wall before
	 * being called.
	 * 
	 * @param wall to add
	 */
	public void addWall(Wall wall) {
		wall.getCell().placeWall(wall);
	}

	/**
	 * Adds an exit to the grid.
	 * <p>
	 * Specifically, this method calls placeExit on the Cell object associated with
	 * the exit (see the Exit class for how to get the cell associated with the
	 * exit). This class assumes a cell has already been set on the exit before
	 * being called.
	 * 
	 * @param exit to add
	 */
	public void addExit(Exit exit) {
		exit.getCell().placeExit(exit);
	}

	/**
	 * Gets a list of all lizards on the grid. Does not include lizards that have
	 * exited.
	 * 
	 * @return lizards list of lizards
	 */
	public ArrayList<Lizard> getLizards() {
		//new array list
		ArrayList <Lizard> activeLizards = new ArrayList<>();
		//if there are lizards
		if(lizards != null) { 
			for (Lizard lizard : lizards) {
				//as long as it is present
				if(lizard.getHeadSegment() != null) { 
					//adding the lizards to the arrayList
					activeLizards.add(lizard); 
				}
			}
		}
		return activeLizards;
	}

	/**
	 * Adds the given lizard to the grid.
	 * <p>
	 * The scoreListener to should be updated with the number of lizards.
	 * 
	 * @param lizard to add
	 */
	public void addLizard(Lizard lizard) {
		for (int col=0; col < getWidth(); col++) {
			for (int row=0; row < getHeight(); row++) {
				//find the target cell
				Cell cell = getCell(col,row);
				//make sure it is available
				if (cell.isEmpty()) { 
					//add the new lizard
					cell.placeLizard(lizard); 
					//add it to the array
					lizards.add(lizard); 
					if (scoreListener != null) {
						//update scoreListener
						scoreListener.updateScore(lizards.size()); 
					}
					return;
				}
			}
		}
		
	}

	/**
	 * Removes the given lizard from the grid. Be aware that each cell object knows
	 * about a lizard that is placed on top of it. It is expected that this method
	 * updates all cells that the lizard used to be on, so that they now have no
	 * lizard placed on them.
	 * <p>
	 * The scoreListener to should be updated with the number of lizards using
	 * updateScore().
	 * 
	 * @param lizard to remove
	 */
	public void removeLizard(Lizard lizard) {
		for (int col = 0; col < getWidth(); col++) {
			for (int row = 0; row < getHeight(); row++) {
				//get the cell
				Cell cell = getCell(col,row);
				//make sure there is a lizard there
				if(cell.getLizard() != null) {
					//make it null
					cell.placeLizard(null);
				}
			}
		}
		//remove it from the array
		lizards.remove(lizard); 
		if (scoreListener != null) {
			//update the score
			scoreListener.updateScore(lizards.size());
		}
	}

	/**
	 * Gets the cell for the given column and row.
	 * <p>
	 * If the column or row are outside of the boundaries of the grid the method
	 * returns null.
	 * 
	 * @param col column of the cell
	 * @param row of the cell
	 * @return the cell or null
	 */
	public Cell getCell(int col, int row) {
		//following the parameters
		if (col >= 0 && col < cell.length && row >= 0 && row < cell[0].length) {
			Cell cells = cell[col][row];
            return cells;
        } else {
            return null;
        }
	}

	/**
	 * Gets the cell that is adjacent to (one over from) the given column and row,
	 * when moving in the given direction. For example (1, 4, UP) returns the cell
	 * at (1, 3).
	 * <p>
	 * If the adjacent cell is outside of the boundaries of the grid, the method
	 * returns null.
	 * 
	 * @param col the given column
	 * @param row the given row
	 * @param dir the direction from the given column and row to the adjacent cell
	 * @return the adjacent cell or null
	 */
	public Cell getAdjacentCell(int col, int row, Direction dir) {
	    Cell cellLocation = null;

	    // finding the coordinates of the target call
	    int adjacentCol = col;
	    int adjacentRow = row;
	    //adjusting the row and column accordingly
	    if (dir == UP && row - 1 >= 0) {
	        adjacentRow = row - 1;
	    } else if (dir == DOWN && row + 1 < getHeight()) {
	        adjacentRow = row + 1;
	    } else if (dir == LEFT && col - 1 >= 0) {
	        adjacentCol = col - 1;
	    } else if (dir == RIGHT && col + 1 < getWidth()) {
	        adjacentCol = col + 1;
	    }
	    // find the cell location if it is within boundaries 
	    if (adjacentCol != col || adjacentRow != row) {
	        cellLocation = cell[adjacentCol][adjacentRow];
	    }
	    return cellLocation;
	}

	/**
	 * Resets the grid. After calling this method the game should have a grid of
	 * size width x height containing all empty cells. Empty means cells with no
	 * walls, exits, etc.
	 * <p>
	 * All lizards should also be removed from the grid.
	 * 
	 * @param width  number of columns of the resized grid
	 * @param height number of rows of the resized grid
	 */
	public void resetGrid(int width, int height) {
		//reset to a new cell grid and a new array list
		this.cell = new Cell[width][height];
		this.lizards = new ArrayList<Lizard>();
		//create a temporary cell to swap
		Cell tempCell;
		for (int col = 0; col < width; col++) {
			for (int row = 0; row < height; row++) {
				//swap the new into the tempCell
				tempCell = new Cell(col , row);
				//swap the tempCell into the new cell grid
				cell[col][row] = tempCell;
			}
		}
	}

	/**
	 * Returns true if a given cell location (col, row) is available for a lizard to
	 * move into. Specifically the cell cannot contain a wall or a lizard. Any other
	 * type of cell, including an exit is available.
	 * 
	 * @param row of the cell being tested
	 * @param col of the cell being tested
	 * @return true if the cell is available, false otherwise
	 */
	public boolean isAvailable(int col, int row) {
		//following the parameters
		if (cell[col][row].isEmpty() || cell[row][col].getExit() != null) {
			// the cell is available
			return true;
		}
		else {
			//the cell isnt available
			return false;
		}
	}

	/**
	 * Move the lizard specified by its body segment at the given position (col,
	 * row) one cell in the given direction. The entire body of the lizard must move
	 * in a snake like fashion, in other words, each body segment pushes and pulls
	 * the segments it is connected to forward or backward in the path of the
	 * lizard's body. The given direction may result in the lizard moving its body
	 * either forward or backward by one cell.
	 * <p>
	 * The segments of a lizard's body are linked together and movement must always
	 * be "in-line" with the body. It is allowed to implement movement by either
	 * shifting every body segment one cell over or by creating a new head or tail
	 * segment and removing an existing head or tail segment to achieve the same
	 * effect of movement in the forward or backward direction.
	 * <p>
	 * If any segment of the lizard moves over an exit cell, the lizard should be
	 * removed from the grid.
	 * <p>
	 * If there are no lizards left on the grid the player has won the puzzle the
	 * the dialog listener should be used to display (see showDialog) the message
	 * "You win!".
	 * <p>
	 * It is possible that the given direction is not in-line with the body of the
	 * lizard (as described above), in that case this method should do nothing.
	 * <p>
	 * It is possible that the given column and row are outside the bounds of the
	 * grid, in that case this method should do nothing.
	 * <p>
	 * It is possible that there is no lizard at the given column and row, in that
	 * case this method should do nothing.
	 * <p>
	 * It is possible that the lizard is blocked and cannot move in the requested
	 * direction, in that case this method should do nothing.
	 * <p>
	 * <b>Developer's note: You may have noticed that there are a lot of details
	 * that need to be considered when implement this method method. It is highly
	 * recommend to explore how you can use the public API methods of this class,
	 * Grid and Lizard (hint: there are many helpful methods in those classes that
	 * will simplify your logic here) and also create your own private helper
	 * methods. Break the problem into smaller parts are work on each part
	 * individually.</b>
	 * 
	 * @param col the given column of a selected segment
	 * @param row the given row of a selected segment
	 * @param dir the given direction to move the selected segment
	 */
	public void move(int col, int row, Direction dir) {
		// out of bounds and doesn't work
		if (col < 0 || col >= getWidth() || row < 0 || row >= getHeight()) {
			return;
		}
		// if there isn't a lizard in that cell
		Cell currentCell = getCell(col,row);
		if (currentCell == null || currentCell.getLizard() == null) {
			return;
		}
		// finding if the user is moving the head or the tail
		Lizard lizard = currentCell.getLizard();
		//true if they are moving the head, false is they are moving the tail
		boolean movingHead = lizard.getHeadSegment().getCell().equals(currentCell);
		//finding the targetCell to the moving part and making sure it is available
		Cell targetCell = getAdjacentCell(col, row, dir);
		if(targetCell == null || !isAvailable(targetCell.getCol(), targetCell.getRow())) {
			//the cell isn't available
			return;
		}
		// if the user is moving the head
		if (movingHead) {
			BodySegment headSegment = lizard.getHeadSegment();
			
			Cell headCell = headSegment.getCell();
			Cell moveTo = getAdjacentCell(headCell.getCol(), headCell.getRow(), dir);
			
			if (targetCell != null && isAvailable(moveTo.getCol(), moveTo.getRow())) {
				if (targetCell.equals(exit.getCell())) {
					removeLizard(lizard);
					return;
				}
				if (targetCell.getLizard() != null) {
					return;
				}
				// if the targetCell is the exit
				if (targetCell == exit.getCell()) {
					removeLizard(lizard);
				}
				currentCell.placeLizard(null);
				targetCell.placeLizard(lizard);
			}
			
		}
		// if the user is moving the tail
		else {
			BodySegment tailSegment = lizard.getTailSegment();
			
			Cell tailCell = tailSegment.getCell();
			Cell moveTo = getAdjacentCell(tailCell.getCol(), tailCell.getCol(), dir);
			
			if (targetCell != null && isAvailable(moveTo.getCol(), moveTo.getRow())){
				if (targetCell.equals(exit.getCell())) {
					removeLizard(lizard);
					return;
				}
				if (targetCell.getLizard() != null) {
					return;
				}
				// if the targetCell is the exit
				if (targetCell.equals(exit.getCell())) {
					removeLizard(lizard);
				}
				
				currentCell.placeLizard(null);
				targetCell.placeLizard(lizard);
			}
			
		}
		// if the user wins and empties all the lizards into exits
		if (lizards.isEmpty()) {
			dialogListener.showDialog("You Win!");
		}
		
	}

	/**
	 * Sets callback listeners for game events.
	 * 
	 * @param dialogListener listener for creating a user dialog
	 * @param scoreListener  listener for updating the player's score
	 */
	public void setListeners(ShowDialogListener dialogListener, ScoreUpdateListener scoreListener) {
		this.dialogListener = dialogListener;
		this.scoreListener = scoreListener;
	}

	/**
	 * Load the game from the given file path
	 * 
	 * @param filePath location of file to load
	 */
	public void load(String filePath) {
		GameFileUtil.load(filePath, this);
	}

	@Override
	public String toString() {
		String str = "---------- GRID ----------\n";
		str += "Dimensions:\n";
		str += getWidth() + " " + getHeight() + "\n";
		str += "Layout:\n";
		for (int y = 0; y < getHeight(); y++) {
			if (y > 0) {
				str += "\n";
			}
			for (int x = 0; x < getWidth(); x++) {
				str += getCell(x, y);
			}
		}
		str += "\nLizards:\n";
		for (Lizard l : getLizards()) {
			str += l;
		}
		str += "\n--------------------------\n";
		return str;
	}
}
